import java.io.*;
import java.util.Vector;
import java.util.Comparator;

/**
 * Created by amon on 22.4.2017.
 */
public class Drevo23 <Tip extends Comparable> implements Seznam<Tip> {

    private abstract class Node implements Comparable{
        public Node parent;

        Node(Node parent) {
            this.parent = parent;
        }
    }

    private class InnerNode extends Node {
        public Tip valueLeft;
        public Tip valueRight;

        public Node left;
        public Node middle;
        public Node right;

        InnerNode(Node parent) {
            super(parent);
        }

        private boolean canInsert() {
            return right == null;
        }

        @Override
        public int compareTo(Object o) {
            if (o.getClass() == this.getClass()) {
                InnerNode in = (InnerNode)o;
                //return valueLeft.compareTo(in.valueLeft);
                return comparator.compare(valueLeft, in.valueLeft);
            } else if (o.getClass() == LeafNode.class) {
                LeafNode ln = (LeafNode)o;

                // ln is smaller than valueLeft
                //if (valueLeft.compareTo(ln.value) > 0) {
                if (comparator.compare(valueLeft, ln.value) > 0) {
                    return -1;
                }
                // if valueRight is set and ln is larger, go right
                //else if (valueRight != null && valueRight.compareTo(ln.value) <= 0) {
                else if (valueRight != null && comparator.compare(valueRight, ln.value) <= 0) {
                    return 1;
                }
                // go middle
                else {
                    return 0;
                }

            }
            return 0;
        }

    }

    private class LeafNode extends Node {

        public Tip value;

        LeafNode(Node parent, Tip value) {
            super(parent);
            this.value = value;
        }

        @Override
        public int compareTo(Object o) {
            if (o.getClass() == this.getClass()) {
                LeafNode ln = (LeafNode)o;
                //return value.compareTo(ln.value);
                return comparator.compare(value, ln.value);
            } else if (o.getClass() == InnerNode.class) {
                InnerNode in = (InnerNode)o;
                //return value.compareTo(in.valueLeft);
                return comparator.compare(value, in.valueLeft);
            }
            return 0;
        }

        public String toString() {
            return this.value.toString();
        }
    }

    private Node root;
    private int size;
    private Comparator<Tip> comparator;

    Drevo23(Comparator<Tip> comparator) {
        this.comparator = comparator;
        root = null;
        size = 0;
    }

    private InnerNode getRoot() {
        return (InnerNode) root;
    }


    @Override
    public void add(Tip e) {
        if (exists(e)) {
            throw new java.lang.IllegalArgumentException();
        }

        if (size == 0) {
            root = new LeafNode(null, e);
        } else if (size == 1) {
            LeafNode l1 = (LeafNode) root;
            LeafNode l2 = new LeafNode(null, e);

            root = new InnerNode(null);

            l1.parent = root;
            l2.parent = root;

            if (comparator.compare(l1.value, l2.value) < 0) {
                getRoot().valueLeft = l2.value;
                getRoot().left = l1;
                getRoot().middle = l2;
            } else {
                getRoot().valueLeft = l1.value;
                getRoot().left = l2;
                getRoot().middle = l1;
            }

        } else {
            LeafNode newNode = new LeafNode(null, e);

            InnerNode in = getLastInnerNode(e);

            // right leaf is empty, we can insert in this node
            if (in.canInsert()) {
                newNode.parent = in;

                // new element is larger than middle, insert it to right
                if (newNode.compareTo(in.middle) > 0) {
                    in.right = newNode;
                    in.valueRight = newNode.value;
                } else {
                    // middle element is the largest, move it to right
                    in.right = in.middle;
                    in.valueRight = ((LeafNode)in.middle).value;

                    // new element is larger than left, insert it to middle
                    if(newNode.compareTo(in.left) > 0) {
                        in.middle = newNode;
                        in.valueLeft = newNode.value;
                    }
                    // new element is the smallest of elements in node
                    else {
                        // move left node to middle and update valueLeft
                        in.middle = in.left;
                        in.valueLeft = ((LeafNode)in.left).value;

                        // set new element to left
                        in.left = newNode;

                        // update upper nodes
                        upperUpdate(in);
                    }
                }
            }
            // inner node is full, split it into two nodes
            else {
                Vector<LeafNode> lv = new Vector<>(4);

                // add already inserted nodes to vector
                lv.add((LeafNode) in.left);
                lv.add((LeafNode) in.middle);
                lv.add((LeafNode) in.right);

                // add new node to vector
                for (int i = 2; i >= 0; i--) {
                    // if node is larger than current element, insert it on index after current element
                    if (newNode.compareTo(lv.get(i)) > 0) {
                        lv.insertElementAt(newNode, i+1);
                        break;
                    }
                }
                // if new node wasn't inserted insert it to index 0
                if (lv.size() < 4) {
                    lv.insertElementAt(newNode, 0);
                }

                // create two new inner nodes
                InnerNode leftN = createInnerNode(lv.elementAt(0), lv.elementAt(1));
                InnerNode rightN = createInnerNode(lv.elementAt(2), lv.elementAt(3));
                InnerNode parent = (InnerNode) in.parent;

                leftN.parent = parent;
                rightN.parent = parent;

                if (parent == null) {
                    root = createInnerNode(leftN, rightN);
                } else if (parent.canInsert()) {
                    insertToParent(parent, in, leftN, rightN);
                } else {
                    // not enough space, do a split <- this could repeat to top of the tree!

                    while (true) {
                        InnerNode left;
                        InnerNode right;

                        if (parent.left == in) {
                            left = createInnerNode(leftN, rightN);
                            right = createInnerNode(parent.middle, parent.right);
                        } else if (parent.middle == in) {
                            left = createInnerNode(parent.left, leftN);
                            right = createInnerNode(rightN, parent.right);
                        } else {
                            left = createInnerNode(parent.left, parent.middle);
                            right = createInnerNode(leftN, rightN);
                        }

                        // there is nothing above this nodes, create new node and make it root
                        if (parent.parent == null) {
                            root = createInnerNode(left, right);
                            left.parent = root;
                            right.parent = root;
                            break;
                        }
                        // parent exists, but could be full
                        else {
                            leftN = left;
                            rightN = right;
                            in = parent;
                            parent = (InnerNode) parent.parent;

                            // there is enough space to insert in parent
                            if (parent.canInsert()) {
                                insertToParent(parent, in, leftN, rightN);
                                break;
                            }

                        }
                    }
                }
            }
        }
        size++;
    }

    private InnerNode getLastInnerNode(Tip e) {
        Node n = getRoot();
        LeafNode ln = new LeafNode(null, e);
        while (true) {
            InnerNode in = (InnerNode) n;
            if (in.left.getClass() == LeafNode.class) {
                break;
            }

            int cmp = in.compareTo(ln);
            if (cmp < 0) {
                n = in.left;
            } else if (cmp == 0) {
                n = in.middle;
            } else {
                n = in.right;
            }
        }

        return (InnerNode) n;
    }

    private void insertToParent(InnerNode parent, InnerNode beforeSplit, InnerNode left, InnerNode right) {
        // check if split node was left or middle, insert new nodes to place where they belong
        if (parent.left == beforeSplit) {
            // move middle to right
            parent.right = parent.middle;
            parent.valueRight = parent.valueLeft;

            // insert split nodes
            parent.left = left;
            parent.middle = right;

            // update left value with middle minimum
            parent.valueLeft = getMin(parent.middle);
        } else {
            // insert split nodes
            parent.middle = left;
            parent.right = right;

            // update values in parent node
            parent.valueLeft = getMin(parent.middle);
            parent.valueRight = getMin(parent.right);
        }

        left.parent = parent;
        right.parent = parent;
    }

    private Tip getMin(Node n) {
        while (n.getClass() == InnerNode.class) {
            n = ((InnerNode)n).left;
        }
        return ((LeafNode)n).value;
    }

    private InnerNode createInnerNode(Node left, Node middle) {
        InnerNode in = new InnerNode(null);

        in.valueLeft = getMin(middle);
        in.left = left;
        in.middle = middle;

        in.left.parent = in;
        in.middle.parent = in;

        return in;
    }

    private void upperUpdate(InnerNode in) {
        Tip value = ((LeafNode)in.left).value;

        InnerNode before = in;

        while (in.parent != null) {
            InnerNode parent = (InnerNode) in.parent;

            // if node is parents left node, go upper
            if (parent.left == in) {
                in = parent;
            }
            // if node is parents middle node, update parents left value
            else if (parent.middle == in) {
                parent.valueLeft = value;
                break;
            }
            // if node is parent right node, update parents right value
            else if (parent.right == in) {
                parent.valueRight = value;
                break;
            }
        }
    }

    @Override
    public Tip removeFirst() {
        return remove(getFirst());
    }

    @Override
    public Tip getFirst() {
        if (size == 0) {
            throw new java.util.NoSuchElementException();
        }
        return getMin(root);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public int depth() {
        if (size == 0) {
            return 0;
        } else if (size == 1) {
            return 1;
        }
        int depth = 1;
        Node n = root;
        while (n.getClass() == InnerNode.class) {
            n = ((InnerNode)n).left;
            depth++;
        }

        return depth;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public Tip remove(Tip e) {
        if (!exists(e)) {
            throw new java.util.NoSuchElementException();
        }
        Tip  value;
        if (size == 1) {
            value = ((LeafNode)root).value;
            root = null;
        } else if (size == 2) {
//            if (getRoot().valueLeft.equals(e)) {
            if(comparator.compare(getRoot().valueLeft, e) == 0) {
                value = (((LeafNode)getRoot().middle).value);
                root = getRoot().left;
            } else {
                value = (((LeafNode)getRoot().left).value);
                root = getRoot().middle;
            }
        } else {
            InnerNode in = getLastInnerNode(e);

            // if node is full just delete value and update inner nodes
            if (!in.canInsert()) {
                // deleted element is right in node
                if (in.valueRight == e) {
                    value = ((LeafNode)in.right).value;

                    in.valueRight = null;
                    in.right = null;
                }
                // deleted element is middle in node
                else if (in.valueLeft == e) {
                    value = ((LeafNode)in.middle).value;

                    // update inner node values
                    in.valueLeft = in.valueRight;
                    in.valueRight = null;

                    // move right element to middle
                    in.middle = in.right;
                    in.right = null;
                }
                // deleted element is left in node
                else {
                    value = ((LeafNode)in.left).value;

                    // update inner node values
                    in.valueLeft = in.valueRight;
                    in.valueRight = null;

                    // move middle to left
                    in.left = in.middle;

                    // move right element to middle
                    in.middle = in.right;
                    in.right = null;

                    // update upper inner nodes
                    upperUpdate(in);
                }

            }
            // only two elements in inner node, move remaining element to other node, remove this node
            else {
                InnerNode parent = (InnerNode) in.parent;

                LeafNode keep;

                if (in.valueLeft == e) {
                    value = ((LeafNode) in.middle).value;
                    keep = (LeafNode) in.left;

                    // remove element from InnerNode
                    in.middle = null;
                    in.valueLeft = null;
                } else {
                    value = ((LeafNode) in.left).value;
                    keep = (LeafNode) in.middle;

                    // remove element from InnerNode
                    in.left = null;
                }

                if (parent.right == in) {
                    parent.valueRight = null;

                    InnerNode middle = (InnerNode) parent.middle;

                    // insert remaining leaf from right to middle inner node
                    if (middle.canInsert()) {
                        moveRemaining(middle, keep);

                        // remove inner node
                        removeInnerNode(in);
                    }
                    // take largest leaf from middle to right
                    else {
                        moveLargest(middle, in, keep);
                    }
                } else if (parent.middle == in) {
                    // check if right inner node exists
                    if (!parent.canInsert()) {
                        InnerNode right = (InnerNode) parent.right;

                        // take left leaf from right InnerNode
                        if (!right.canInsert()) {
                            moveSmallest(in, right, keep);
                        }
                        // take both leafs from right InnerNode
                        else {
                            takeBothLeafs(in, right, keep);
                        }
                    }
                    // there are only left and middle nodes
                    else {
                        InnerNode left = (InnerNode) parent.left;

                        // take right leaf from left
                        if (!left.canInsert()) {
                            moveLargest(left, in, keep);
                        }
                        // move remaining leaf to left node
                        else {
                            moveRemaining(left, keep);
                            removeInnerNode(in);
                        }
                    }
                } else if (parent.left == in) {
                    InnerNode middle = (InnerNode) parent.middle;

                    if (!middle.canInsert()) {
                        moveSmallest(in, middle, keep);
                    } else {
                        takeBothLeafs(in, middle, keep);
                        removeInnerNode(middle);
                    }
                }
            }
        }

        size--;
        return value;
    }

    private void clearNode(InnerNode n) {
        n.valueLeft = null;
        n.valueRight = null;
        n.left = null;
        n.middle = null;
        n.right = null;

    }

    private void takeBothLeafs(InnerNode a, InnerNode b, LeafNode keep) {
        clearNode(a);

        a.left = keep;

        LeafNode bLeft = (LeafNode) b.left;
        LeafNode bMiddle = (LeafNode) b.middle;

        a.valueLeft = bLeft.value;
        a.valueRight = bMiddle.value;
        a.middle = bLeft;
        a.right = bMiddle;
        bLeft.parent = a;
        bMiddle.parent = a;

        removeInnerNode(b);
    }

    private void moveLargest(InnerNode a, InnerNode b, LeafNode keep) {
        clearNode(b);

        a.valueRight = null;
        LeafNode largestLeaf = (LeafNode) a.right;

        // insert largest leaf from middle
        b.left = largestLeaf;
        largestLeaf.parent = b;

        // insert remaining leaf to middle
        b.middle = keep;
        b.valueLeft = keep.value;

        // update upper inner nodes
        upperUpdate(b);
    }

    private void moveSmallest(InnerNode a, InnerNode b, LeafNode keep) {
        clearNode(a);

        a.left = keep;

        LeafNode smallest = (LeafNode) b.left;

        // shift elements for one place
        b.left = b.middle;
        b.middle = b.right;
        b.right = null;
        b.valueLeft = b.valueRight;
        b.valueRight = null;

        // move smallest to other InnerNode
        a.valueLeft = smallest.value;
        a.middle = smallest;
        smallest.parent = a;

        // update upper inner nodes
        upperUpdate(a);
        upperUpdate(b);
    }

    private void moveRemaining(InnerNode a, LeafNode keep) {
        a.valueRight = keep.value;
        a.right = keep;
        keep.parent = a;
    }

    private void removeInnerNode(InnerNode r) {
        InnerNode parent = (InnerNode) r.parent;

        if (parent.right == r) {
            parent.valueRight = null;
            parent.right = null;
        } else if (parent.middle == r) {
            // move right to middle
            if (parent.right != null) {
                parent.middle = parent.right;
                parent.right = null;
                parent.valueLeft = parent.valueRight;
                parent.valueRight= null;
            } else {
                if (parent.parent == null) {
                    root = parent.left;
                    root.parent = null;
                } else {
                    // delete node and fix tree
                    parent.middle = null;
                    parent.valueLeft = null;
                    fixTree((InnerNode) parent.parent);
                }
            }
        } else if (parent.left == r) {
            parent.left = null;

            parent.left = parent.middle;
            parent.middle = parent.right;

            parent.valueLeft = parent.valueRight;
            parent.valueRight = null;

            if (parent.parent == null && parent.middle == null) {
                root = parent.left;
                root.parent = null;
            } else {
                fixTree((InnerNode) parent.parent);
            }

        }
    }

    private void fixTree(InnerNode n) {
        while (n != null) {
            InnerNode left = (InnerNode) n.left;
            InnerNode middle = (InnerNode) n.middle;
            InnerNode right = (InnerNode) n.right;

            if (left.middle == null) {
                if (middle.canInsert()) {
                    InnerNode m = (InnerNode) middle.left;
                    InnerNode r = (InnerNode) middle.middle;

                    left.middle = m;
                    left.right = r;

                    m.parent = left;
                    m.parent = right;

                    n.middle = null;

                    // TODO upper update!!

                    // move right to middle if exists
                    if (right != null) {
                        n.middle = n.right;
                        n.right = null;

                        n.valueLeft = n.valueRight;
                        n.valueRight = null;

                        return;
                    }
                    else if (n.parent == null) {
                        // make left as root
                        left.parent = null;
                        root = left;
                        return;
                    }
                    // fix tree once again
                    else {
                        n = (InnerNode) n.parent;
                        continue;
                    }
                }
                // take left child from middle
                else {
                    InnerNode m = (InnerNode) middle.left;
                    left.middle = m;
                    m.parent = left;

                    // move leafs in middle
                    middle.left = middle.middle;
                    middle.middle = middle.right;
                    middle.right = null;

                    // update values in middle and n
                    n.valueLeft = middle.valueLeft;
                    middle.valueLeft =middle.valueRight;
                    middle.valueRight = null;

                    return;
                }
            }

            else if (middle.middle == null) {
                if (right == null) {
                    if (left.canInsert()) {
                        InnerNode r = (InnerNode) middle.left;
                        left.right = r;
                        r.parent = left;

                        n.middle = null;
                        n.valueLeft = null;

                        // TODO upper update

                        if (n.parent == null) {
                            // make left as root
                            left.parent = null;
                            root = left;
                            return;
                        }
                        // fix tree once again
                        else {
                            n = (InnerNode) n.parent;
                            continue;
                        }

                    }
                }
                else if (right.canInsert()) {
                    InnerNode m = (InnerNode) right.left;
                    InnerNode r = (InnerNode) right.middle;

                    left.middle = m;
                    left.right = r;

                    m.parent = left;
                    m.parent = right;

                    n.right = null;
                    n.valueRight = null;
                } else {
                    InnerNode m = (InnerNode) right.left;
                    middle.middle = m;
                    m.parent = left;

                    // move leafs in middle
                    right.left = right.middle;
                    right.middle = right.right;
                    right.right = null;

                    // update values in middle and n
                    n.valueRight = right.valueLeft;
                    right.valueLeft = right.valueRight;
                    right.valueRight = null;

                    return;
                }
            } else if (right != null && right.middle == null) {
                if (middle.canInsert()) {
                    InnerNode r = (InnerNode) right.left;
                    middle.right = r;
                    r.parent = middle;

                    n.right = null;
                    n.valueRight = null;

                    //TODO upper update!!!

                    return;
                }
                // take largest child from middle
                else {
                    InnerNode l = (InnerNode) middle.right;
                    middle.right = null;
                    middle.valueRight = null;

                    right.middle = right.left;
                    right.left = l;
                    l.parent = right;

                    //TODO upper update!!!

                    return;
                }
            } else {
                return;
            }


        }
    }

    @Override
    public boolean exists(Tip e) {
        if (size == 0) {
            return false;
        } else if (size == 1) {
//            return e.equals(((LeafNode)root).value);
            return comparator.compare(((LeafNode)root).value, e) == 0;
        }

        LeafNode leafE = new LeafNode(null, e);

        Node n = root;

        while (n.getClass() == InnerNode.class) {
            int cmp = n.compareTo(leafE);
            if (cmp < 0) {
                n = ((InnerNode)n).left;
            } else if (cmp == 0) {
                n = ((InnerNode)n).middle;
            } else {
                n = ((InnerNode)n).right;
            }
        }

        return leafE.compareTo(n) == 0;

    }


    public Tip get(Tip e) {
        if (size == 0) {
            return null;
        } else if (size == 1) {
//            return e.equals(((LeafNode)root).value);
            return comparator.compare(((LeafNode)root).value, e) == 0 ? ((LeafNode) root).value : null;
        }

        LeafNode leafE = new LeafNode(null, e);

        Node n = root;

        while (n.getClass() == InnerNode.class) {
            int cmp = n.compareTo(leafE);
            if (cmp < 0) {
                n = ((InnerNode)n).left;
            } else if (cmp == 0) {
                n = ((InnerNode)n).middle;
            } else {
                n = ((InnerNode)n).right;
            }
        }

        return leafE.compareTo(n) == 0 ? ((LeafNode)n).value : null;

    }

    @Override
    public String asList() {
        if (isEmpty()) {
            return "";
        }
        if (size() == 1) {
            return "\t" + ((LeafNode)root);
        }
        String list = asListElements(root);
        list = list.substring(0, list.length()-1);
        return list;
    }

    private String asListElements(Node n) {
        if (n == null) {
            return "";
        }
        if (n.getClass() == LeafNode.class) {
            return "\t" + n.toString() + "\n";
        }
        InnerNode in = (InnerNode) n;

        return asListElements(in.left) + asListElements(in.middle) + asListElements(in.right);
    }


    @Override
    public void save(OutputStream outputStream) throws IOException {
        ObjectOutputStream out = new ObjectOutputStream(outputStream);
        out.writeInt(this.size());
        save(root, out);
    }
    private void save(Node node, ObjectOutputStream out) throws IOException {
        if (node == null)
            return;

        if (node.getClass() == LeafNode.class) {
            out.writeObject(((LeafNode)node).value);
            return;
        }
        InnerNode in = (InnerNode) node;

        save(in.left, out);
        save(in.middle, out);
        save(in.right, out);
    }

    @Override
    public void restore(InputStream inputStream) throws IOException, ClassNotFoundException {
        ObjectInputStream in = new ObjectInputStream(inputStream);
        int count = in.readInt();
        for (int i = 0; i < count; i++) {
            add((Tip) in.readObject());
        }
    }

}

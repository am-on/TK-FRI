import java.util.Vector;

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
                return valueLeft.compareTo(in.valueLeft);
            } else if (o.getClass() == LeafNode.class) {
                LeafNode ln = (LeafNode)o;

                // ln is smaller than valueLeft
                if (valueLeft.compareTo(ln.value) > 0) {
                    return -1;
                }
                // if valueRight is set and ln is larger, go right
                else if (valueRight != null && valueRight.compareTo(ln.value) <= 0) {
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
                return value.compareTo(ln.value);
            } else if (o.getClass() == InnerNode.class) {
                InnerNode in = (InnerNode)o;
                return value.compareTo(in.valueLeft);
            }
            return 0;
        }

        public String toString() {
            return this.value.toString();
        }
    }

    private Node root;
    private int size;

    Drevo23() {
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

            if (l1.compareTo(l2) < 0) {
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

            Node n = getRoot();
            while (true) {
                InnerNode in = (InnerNode) n;
                if (in.left.getClass() == LeafNode.class) {
                    break;
                }

                int cmp = in.compareTo(newNode);
                if (cmp < 0) {
                    n = in.left;
                } else if (cmp == 0) {
                    n = in.middle;
                } else {
                    n = in.right;
                }
            }

            InnerNode in = (InnerNode) n;

            // right leaf is empty, we can insert in this node
            if (in.canInsert()) {
                newNode.parent = in;

                // new element is larger than middle, insert it to right
                if (newNode.compareTo(in.middle) > 0) {
                    System.out.println("HERE" + in.valueLeft);
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
            if (getRoot().valueLeft.equals(e)) {
                value = (((LeafNode)getRoot().middle).value);
                root = getRoot().left;
            } else {
                value = (((LeafNode)getRoot().left).value);
                root = getRoot().middle;
            }
        } else {
            throw new UnsupportedOperationException("TODO");
        }

        size--;
        return value;
    }

    @Override
    public boolean exists(Tip e) {
        if (size == 0) {
            return false;
        } else if (size == 1) {
            return e.equals(((LeafNode)root).value);
        }

        LeafNode leafE = new LeafNode(null, e);

        Node n = root;

        while (n.getClass() == InnerNode.class) {
            int cmp = n.compareTo(leafE);
            System.out.println(cmp + " " + ((InnerNode)n).left + "" + ((InnerNode)n).middle + " " + ((InnerNode)n).right  + " "+ leafE);
            if (cmp < 0) {
                n = ((InnerNode)n).left;
            } else if (cmp == 0) {
                n = ((InnerNode)n).middle;
            } else {
                n = ((InnerNode)n).right;
            }
        }

        System.out.println(leafE.value + ((LeafNode)n).value.toString() + "");

        return leafE.compareTo(n) == 0;

    }

    @Override
    public String asList() {
        if (isEmpty()) {
            return "[ ]";
        }
        if (size() == 1) {
            return "[\"" + ((LeafNode)root) + "\", ]";
        }
        String list = asListElements(root);
        list = list.substring(0, list.length()-2);
        return "[" + list + "]";
    }

    private String asListElements(Node n) {
        if (n == null) {
            return "";
        }
        if (n.getClass() == LeafNode.class) {
            return "\"" + n.toString() + "\", ";
        }
        InnerNode in = (InnerNode) n;

        return asListElements(in.left) + asListElements(in.middle) + asListElements(in.right);
    }
}

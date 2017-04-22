

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

        public Node parent;
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
            LeafNode ln = new LeafNode(null, e);

            Node n = getRoot();
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

            InnerNode in = (InnerNode) n;

            // right leaf is empty, we can insert in this node
            if (in.canInsert()) {
                ln.parent = in;

                // new element is larger than middle, insert it to right
                if (ln.compareTo(in.middle) > 0) {
                    System.out.println("HERE" + in.valueLeft);
                    in.right = ln;
                    in.valueRight = ln.value;
                } else {
                    // middle element is the largest, move it to right
                    in.right = in.middle;
                    in.valueRight = ((LeafNode)in.middle).value;

                    // new element is larger than left, insert it to middle
                    if(ln.compareTo(in.left) > 0) {
                        in.middle = ln;
                        in.valueLeft = ln.value;
                    }
                    // new element is the smallest of elements in node
                    else {
                        // move left node to middle and update valueLeft
                        in.middle = in.left;
                        in.valueLeft = ((LeafNode)in.left).value;

                        // set new element to left
                        in.left = ln;

                    }

                }

                //TODO ce vstavljas v in.left, bo mogoce treba visje posodobiti in.leftValue


            } else {
                throw new UnsupportedOperationException("TODO");
            }




        }

        size++;

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

        Node n = root;

        while (n.getClass() == InnerNode.class) {
            n = ((InnerNode)n).left;
        }

        return ((LeafNode)n).value;
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

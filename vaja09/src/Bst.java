import java.io.*;

public class Bst<Tip extends Comparable> implements Seznam<Tip> {

    class ElementBST {

        public Tip value;
        public ElementBST left, right;

        public ElementBST(Tip e) {
            this(e, null, null);
        }

        public ElementBST(Tip e, ElementBST lt, ElementBST rt) {
            value = e;
            left = lt;
            right = rt;
        }
    }

    ElementBST rootNode;
    private Tip minNodeValue;

    public Bst() {
        rootNode = null;
    }

    private boolean member(Tip e) {
        return member(e, rootNode);
    }

    private boolean member(Tip e, ElementBST node) {
        if (node == null) {
            return false;
        } else if (e.compareTo(node.value) == 0) {
            return true;
        } else if (e.compareTo(node.value) < 0) {
            return member(e, node.left);
        } else {
            return member(e, node.right);
        }
    }

    private void insert(Tip e) {
        rootNode = insertLeaf(e, rootNode);
    }

    private void delete(Tip e) {
        rootNode = delete(e, rootNode);
    }

    private ElementBST insertLeaf(Tip e, ElementBST node) {
        if (node == null) {
            node = new ElementBST(e);
        } else if (e.compareTo(node.value) < 0) {
            node.left = insertLeaf(e, node.left);
        } else if (e.compareTo(node.value) > 0) {
            node.right = insertLeaf(e, node.right);
        } else {
            throw new java.lang.IllegalArgumentException(); //element ze obstaja
        }
        return node;
    }

    private ElementBST delete(Tip e, ElementBST node) {
        if (node != null) {
            if (e.compareTo(node.value) == 0) {
                if (node.left == null) {
                    // trenutno vozlisce postane koren desnega poddrevesa
                    return node.right;
                } else if (node.right == null) {
                    // trenutno vozlisce postane koren levega poddrevesa
                    return node.left;
                } else {
                    // trenutno vozlisce dobi vrednost MIN(desno poddrevo)
                    // brisi MIN(desno poddrevo)
                    node.right = deleteMin(node.right);
                    node.value = minNodeValue;
                }
            } else if (e.compareTo(node.value) < 0) {
                node.left = delete(e, node.left);
            } else {
                node.right = delete(e, node.right);
            }
        }
        return node;
    }

    private ElementBST deleteMin(ElementBST node) {
        if (node.left != null) {
            node.left = deleteMin(node.left);
            return node;
        } else {
            // PRIMER: manjka prenos minimalne vrednosti
            minNodeValue = node.value;
            return node.right;
        }
    }

    private int getDepth(ElementBST node) {
        if (node == null) {
            return 0;
        }
        return 1 + Math.max(getDepth(node.left), getDepth(node.right));
    }

    private int countNodes(ElementBST node) {
        if (node == null) {
            return 0;
        }
        int i = 1 + countNodes(node.left) + countNodes(node.right);
        return i;
    }

    @Override
    public void add(Tip e) {
        insert(e);
    }

    @Override
    public Tip removeFirst() {
        if (this.isEmpty()) {
            throw new java.util.NoSuchElementException();
        }
        Tip el = rootNode.value;
        delete(rootNode.value);
        return el;
    }

    @Override
    public Tip getFirst() {
        if (this.isEmpty()) {
            throw new java.util.NoSuchElementException();
        }
        return rootNode.value;
    }

    @Override
    public int size() {
        int i = countNodes(rootNode);
        return i;
    }

    @Override
    public int depth() {
        return getDepth(rootNode);
    }

    @Override
    public boolean isEmpty() {
        return (rootNode == null);
    }

    @Override
    public Tip remove(Tip e) {
        if (!this.exists(e)) {
            throw new java.util.NoSuchElementException();
        } else {
            delete(e);
        }
        return e;
    }

    @Override
    public boolean exists(Tip e) {
        return member(e);
    }
    
    // manjka toList

    @Override
    public void print() {
        print(rootNode, 0);
    }
    private void print(ElementBST node, int numTabs) {
        if (node == null)
            return;
        print(node.right, numTabs+1);
        for (int i = 0; i < numTabs; i++)
            System.out.print('\t');
        System.out.println(node.value);
        print(node.left, numTabs + 1);
    }

    @Override
    public void save(OutputStream outputStream) throws IOException {
        ObjectOutputStream out = new ObjectOutputStream(outputStream);
        out.writeInt(this.size());
        save(rootNode, out);
    }
    private void save(ElementBST node, ObjectOutputStream out) throws
            IOException {
        if (node == null)
            return;
        save(node.left, out);
        out.writeObject(node.value);
        save(node.right, out);
    }

    @Override
    public void restore(InputStream inputStream) throws IOException,
            ClassNotFoundException {
        ObjectInputStream in = new ObjectInputStream(inputStream);
        int count = in.readInt();
        rootNode = restore(in, count);
    }
    private ElementBST restore(ObjectInputStream in, int count) throws
            IOException, ClassNotFoundException {
        if (count == 0) return null;
        ElementBST nodeLeft = restore(in, count / 2);
        ElementBST node = new ElementBST((Tip) in.readObject());
        node.left = nodeLeft;
        node.right = restore(in, (count - 1) / 2);
        return node;
    }
}

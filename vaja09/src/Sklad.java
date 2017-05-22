import java.io.*;

class Element<Tip> {

    public Tip vrednost;
    public Element<Tip> vezava;

    public Element(Tip e) {
        this.vrednost = e;
    }
}

public class Sklad<Tip> implements Seznam<Tip> {

    private Element<Tip> vrh;

    public Sklad() {
    }

    public void push(Tip e) {
        Element<Tip> novVrh = new Element<>(e);
        novVrh.vezava = vrh;
        vrh = novVrh;
    }

    public Tip pop() {
        if (vrh == null) {
            throw new java.util.NoSuchElementException();
        }
        Tip e = vrh.vrednost;
        vrh = vrh.vezava;
        return e;
    }

    @Override
    public boolean isEmpty() {
        return (vrh == null);
    }

    public Tip peek() {
        if (isEmpty()) {
            throw new java.util.NoSuchElementException();
        }
        return vrh.vrednost;
    }

    public int count() {
        if (isEmpty()) {
            return 0;
        }

        int stElementov = 0;
        Element<Tip> tmp = vrh;
        while (tmp != null) {
            stElementov++;
            tmp = tmp.vezava;
        }
        return stElementov;
    }

    public boolean top(Tip e) {
        if (vrh == null) {
            throw new java.util.NoSuchElementException();
        }
        return vrh.vrednost.equals(e);
    }

    public int search(Tip e) {
        Element<Tip> tmp = vrh;
        int i = 0;
        while (null != tmp) {
            if (tmp.vrednost.equals(e)) {
                return i;
            }
            i++;
            tmp = tmp.vezava;
        }
        return -1;
    }

    @Override
    public void add(Tip e) {
        this.push(e);
    }

    @Override
    public Tip removeFirst() {
        return this.pop();
    }

    @Override
    public Tip getFirst() {
        return peek();
    }

    @Override
    public int size() {
        return this.count();
    }

    @Override
    public int depth() {
        return this.count();
    }

    @Override
    public Tip remove(Tip e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean exists(Tip e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    // manjka toList

    @Override
    public void print() {
        print(vrh);
    }

    private void print(Element node) {
        if (node == null) return;
        System.out.print(node.vrednost + " ");
        print(node.vezava);
    }

    @Override
    public void save(OutputStream outputStream) throws IOException {
        ObjectOutputStream out = new ObjectOutputStream(outputStream);
        out.writeInt(this.size());
        Element<Tip> tmp = vrh;
        save(tmp, out);
    }
    private void save(Element node, ObjectOutputStream out) throws
            IOException {
        if (node == null)
            return;

        out.writeObject(node.vrednost);
        save(node.vezava, out);
    }

    @Override
    public void restore(InputStream inputStream) throws IOException,
            ClassNotFoundException {
        ObjectInputStream in = new ObjectInputStream(inputStream);
        int count = in.readInt();
        vrh = null;
        restore(in, count);
    }

    private void restore(ObjectInputStream in, int count) throws
            IOException, ClassNotFoundException {
        if (count == 0) return;

        Element node = new Element((Tip) in.readObject());
        node.vezava = vrh;
        vrh = node;
        restore(in, count-1);
    }
}

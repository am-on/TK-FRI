
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Comparator;


class Element<Tip> {

    public Tip vrednost;
    public Element<Tip> vezava;

    public Element(Tip e) {
        vrednost = e;
    }
}

public class Sklad<Tip> implements Seznam<Tip>{

    private Element<Tip> vrh;
    private Comparator<Tip> comparator;

    public Sklad(Comparator<Tip> comparator) {
        this.comparator = comparator;
    }

    public void push(Tip e) {
        Element<Tip> novVrh = new Element<>(e);
        novVrh.vezava = vrh;
        vrh = novVrh;
    }

    public Tip pop() {
        if (null == vrh) {
            throw new java.util.NoSuchElementException();
        }

        Tip e = vrh.vrednost;
        vrh = vrh.vezava;
        return e;
    }

    public Tip peek() {
        if (null == vrh) {
            throw new java.util.NoSuchElementException();
        }
        Tip e = vrh.vrednost;
        return e;
    }

    public boolean isEmpty() {
        return (null == vrh);
    }

    public int search(Tip e) {
        Element<Tip> temp = vrh;
        int i = 1;
        while (null != temp) {
            if (0 == comparator.compare(temp.vrednost, e)) {
                return i;
            }
            i++;
            temp = temp.vezava;
        }
        return -1;
    }

    public int count() {
        int result = 0;
        Element<Tip> temp = vrh;
        while (null != temp) {
            ++result;
            temp = temp.vezava;
        }
        return result;
    }

     @Override
    public void add(Tip e) {
        push(e);
    }

    @Override
    public Tip removeFirst() {
        return pop();
    }

    @Override
    public Tip remove(Tip e) {
        throw new java.lang.UnsupportedOperationException();
    }

    @Override
    public Tip getFirst() {
        return peek();
    }

    @Override
    public int size() {
        return count();
    }

    @Override
    public int depth() {
        return count();
    }

    @Override
    public boolean exists(Tip e) {
        if (search(e) == -1){
            return false;
        }
        return true;
    }

    @Override
    public void print() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void save(OutputStream outputStream) throws IOException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void restore(InputStream inputStream) throws IOException, ClassNotFoundException {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}

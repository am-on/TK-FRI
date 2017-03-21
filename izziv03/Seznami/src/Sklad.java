
class Element<Tip> {

    public Tip vrednost;
    public Element<Tip> vezava;

    public Element(Tip e) {
        vrednost = e;
    }
}

public class Sklad<Tip> {

    private Element<Tip> vrh;

    public Sklad() {
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

    public boolean isEmpty() {
        return (null == vrh);
    }

    public int count() {
        int result = 0;
        Element<Tip> temp = vrh;
        while (null != temp)  {
            ++result;
            temp = temp.vezava;
        }
        return result;
    }

    public boolean top(Tip e) {
        if (null == vrh) {
            throw new java.util.NoSuchElementException();
        }
        return vrh.vrednost.equals(e);
    }

    public int search(Tip e) {
        Element<Tip> temp = vrh;
        int result = -1;

        while (null != temp)  {

            ++result;

            if(e.equals(temp.vrednost)) {
                break;
            }

            temp = temp.vezava;

        }

        if(temp == null) {
            result = -1;
        }

        return result;
    }
}
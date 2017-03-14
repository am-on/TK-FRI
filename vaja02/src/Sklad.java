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
        if(vrh == null) {
            throw new java.util.NoSuchElementException();
        }
        Element<Tip> starVrh = vrh;
        vrh = vrh.vezava;
        return starVrh.vrednost;
    }

    public Tip peek() {
        if(vrh == null) {
            throw new java.util.NoSuchElementException();
        }
        return vrh.vrednost;
    }

    public int count() {
        Element vrh = this.vrh;
        int n = 0;
        while(vrh != null) {
            vrh = vrh.vezava;
            n++;
        }
        return n;
    }

    public boolean isEmpty() {
        return vrh == null;
    }

}

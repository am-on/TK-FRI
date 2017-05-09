import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.*;

public interface Seznam<Tip> {

    // dodajanje elementa v podatkovno strukturo
    void add(Tip e);

    // odstranjevanje (in vračanje) prvega elementa iz pod. strukture
    Tip removeFirst();

    // vracanje prvega elementa iz podatkovne strukture
    Tip getFirst();

    // število elementov v podatkovni strukturi
    int size();

    // globina podatkovne strukture
    int depth();

    // ali je podakovna struktura prazna
    boolean isEmpty(); 
      
    // Odstranjevanje (in vraÄŤanje) doloÄŤenega elementa iz strukture
    Tip remove(Tip e);
   
    // Ali element obstaja v strukturi
    boolean exists(Tip e);

    void print();

    void save(OutputStream outputStream) throws IOException;

    void restore(InputStream inputStream) throws IOException,
                    ClassNotFoundException;

    // Prepis v seznam
    //List<Tip> toList();

}

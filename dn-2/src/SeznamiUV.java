
import java.util.Scanner;
import java.util.HashMap;

public class SeznamiUV {
    Seznam<String> seznam;

    public SeznamiUV() {
        seznam = new Drevo23<String>();
    }

    public String processInput(String input) {
        Scanner sc = new Scanner(input);
        String token;
        String result = "OK";

        if (sc.hasNext()) {
            token = sc.next();
        } else {
            return "Error: enter command";
        }
        if (!token.equals("use") && (null == seznam)) {
            return "Error: please specify a data structure (use {pv|sk|bst})";
        }
        try {
            if (token.equals("add")) {
                if (sc.hasNext()) {
                    seznam.add(sc.next());
                } else {
                    result = "Error: please specify a string";
                }
            } else if (token.equals("remove_first")) {
                result = seznam.removeFirst();
            } else if (token.equals("get_first")) {
                result = seznam.getFirst();
            } else if (token.equals("size")) {
                result = seznam.size() + "";
            } else if (token.equals("depth")) {
                result = seznam.depth() + "";
            } else if (token.equals("is_empty")) {
                result = "Data structure is " + (seznam.isEmpty() ? "" : "not ") + "empty.";
            } else if (token.equals("reset")) {
                while (!seznam.isEmpty()) {
                    seznam.removeFirst();
                }
            } else if (token.equals("exists")) {
                if (sc.hasNext()) {
                    result = "Element " + (seznam.exists(sc.next()) ? "exists " : "doesn't exist ") + "in data structure.";
                } else {
                    result = "Error: please specify a string";
                }
            } else if (token.equals("remove")) {
                if (sc.hasNext()) {
                    String next = sc.next();
                    if (!seznam.exists(next))
                        result = "Error: can't remove element that doesn't exist in data structure.";
                    else
                        result = seznam.remove(next);
                } else {
                    result = "Error: please specify a string";
                }
            } else if (token.equals("asList")) {
                result = seznam.asList();
            } else {
                result = "Error: invalid command";
            }
        }  catch (IllegalArgumentException e) {
            result = "Error: Duplicated entry";
        } catch (java.util.NoSuchElementException e) {
            result = "Error: data structure is empty";
        }
        return result;
    }
}

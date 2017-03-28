
import java.util.Scanner;

public class SeznamiUV {

    private Sklad<String> sklad;
    private PrioritetnaVrsta<String> vrsta;

    public SeznamiUV() {
        sklad = new Sklad<>();
        vrsta = new PrioritetnaVrsta<>();
    }

    public String processInput(String input) {
        Scanner sc = new Scanner(input);
        String token = sc.next();
        String result = "OK";
        switch (token) {
            case "s_add":
                if (sc.hasNext()) {
                    String tmp = sc.next();
                    if (tmp.startsWith("\"")) {
                        tmp = tmp.substring(1);
                        while (sc.hasNext()) {
                            tmp = tmp + " " + sc.next();
                        }
                        tmp = tmp.substring(0, tmp.length() - 1);
                    }
                    sklad.add(tmp);
                } else {
                    result = "Error: please specify a string";
                }
                break;
            case "s_remove_first":
                if (!sklad.isEmpty()) {
                    result = sklad.removeFirst();
                } else {
                    result = "Error: stack is empty";
                }
                break;
            case "s_size":
                result = String.format("%d", sklad.count());
                break;
            case "s_reset":
                while (!sklad.isEmpty()) {
                    sklad.removeFirst();
                }
                break;
            case "s_search":
                if (sc.hasNext()) {
                    result = String.format("%d", sklad.search(sc.next()));
                } else {
                    result = "Error: please specify a string";
                }
                break;
            case "s_get_first":
                if (sc.hasNext()) {
                    if (!sklad.isEmpty()) {
                        if (sklad.top(sc.next())) {
                            result = "OK";
                        } else {
                            result = "Error: wrong element";
                        }

                    } else {
                        result = "Error: stack is empty";
                    }
                } else {
                    result = "Error: please specify a string";
                }
                break;
            case "pq_add":
                if (sc.hasNext()) {
                    String val = sc.next();
                    vrsta.add(val);
                }
                else
                    result = "Error: please specify a string";
                break;
            case "pq_remove_first":
                if (!vrsta.isEmpty())
                    result = vrsta.removeFirst();
                else
                    result = "Error: priority queue is empty";
                break;
            case "pq_get_first":
                if (!vrsta.isEmpty())
                    result = vrsta.getFirst();
                else
                    result = "Error: priority queue is empty";
                break;
            case "pq_size":
                return vrsta.size() + "";
            case "pq_depth":
                return vrsta.depth() + "";
            case "pq_isEmpty":
                if(vrsta.isEmpty())
                    return "Priority queue is empty";
                return "Priority queue is not empty";
            default:
                break;
        }
        return result;
    }
}

/**
 * Created by amon on 3/21/17.
 */
import java.util.NoSuchElementException;
import java.util.Scanner;

public class SeznamiUV {
    Sklad <String> sklad;

    public SeznamiUV()  {
        sklad = new Sklad<String>();
    }

    public String processInput(String input) {
        Scanner sc = new Scanner(input);
        String token = sc.next();
        String result = "OK";
        switch (token) {
            case "push":
                if (sc.hasNextLine())
                    sklad.push(sc.nextLine().trim().replace("\"", ""));
                else
                    result = "Error: please specify a string";
                break;
            case "pop":
                if (!sklad.isEmpty())
                    result = sklad.pop();
                else
                    result = "Error: stack is empty";
                break;
            case "reset":
                while (!sklad.isEmpty()) sklad.pop();
                break;
            case "count":
                result = String.format("%d", sklad.count());
                break;
            case "top":
                boolean r;
                try {
                    if (sc.hasNext()) {
                        r = sklad.top(sc.next());
                        if (r)
                            return "OK";
                        else
                            return "Error: wrong element";
                    }
                    else
                        return "Error: please specify a string";
                } catch (NoSuchElementException e) {
                    return "Error: stack is empty";
                }
            case "search":
                if (sc.hasNext())
                    return sklad.search(sc.next()) + "";
                else
                    return "Error: please specify a string";
        }

        return result;
    }
}

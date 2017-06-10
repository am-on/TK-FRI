
import java.util.Scanner;
import java.util.HashMap;

public class SeznamiUV {
    Seznam<String> seznam;

    Seznam<Student> seznamByName;
    Seznam<Student> seznamById;

    String[] studentBuffer;
    int addPhase = -1;
    boolean inputError = false;

    public SeznamiUV() {
        seznamById = new Drevo23<Student>(new StudentCompareID());
        seznamByName = new Drevo23<Student>(new StudentCompareNames());
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
        try {
            if (addPhase >= 0) {
                String inputStr = sc.hasNext() ? sc.next() : "";
                studentBuffer[addPhase++] = inputStr;
                switch (addPhase) {
                    case 1:
                        result = "First name: ";
                        break;
                    case 2:
                        result = "Last name: ";
                        break;
                    case 3:
                        result = "Avg. grade: ";
                        break;
                    default:
                        break;
                }

                if (addPhase >= 4) {
                    Student st = new Student(studentBuffer);
                    if(st.validate()) {
                        if (seznamById.exists(st) || seznamByName.exists(st)) {
                            result = "Student already exists";
                        } else {
                            seznamById.add(st);
                            seznamByName.add(st);
                            result = "OK";
                        }
                    } else {
                        result = "Invalid input data";
                    }
                    addPhase = -1;
                }

            } else if (token.equals("add")) {
                addPhase = 0;
                studentBuffer = new String[4];
                result = "Student ID: ";

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

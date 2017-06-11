
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;
import java.util.HashMap;

public class SeznamiUV {

    Seznam<Student> seznamByName;
    Seznam<Student> seznamById;

    String[] studentBuffer = new String[4];
    int addPhase = -1;
    int resetPhase = -1;
    int removePhase = -1;
    int searchPhase = -1;


    public SeznamiUV() {
        seznamById = new Drevo23<Student>(new StudentCompareID());
        seznamByName = new Drevo23<Student>(new StudentCompareNames());
    }

    public void setMockObj(Seznam<Student> mock) {
        seznamByName = mock;
        seznamById = mock;
    }

    public String processInput(String input) {
        Scanner sc = new Scanner(input);
        String token;
        String result = "OK";

        if (sc.hasNext()) {
            token = sc.next();
        } else {
            if (addPhase < 0 && removePhase < 0 && searchPhase < 0)
                return "Invalid command";
            else
                token = "";
        }
        try {
            if (addPhase >= 0) {
                while (sc.hasNextLine()) {
                    token += sc.nextLine();
                }
                studentBuffer[addPhase++] = token;
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

            } else if (resetPhase > 0) {
                if (token.equals("y")) {
                    while (!seznamByName.isEmpty()) {
                        seznamByName.removeFirst();
                    }
                    while (!seznamById.isEmpty()) {
                        seznamById.removeFirst();
                    }
                    result = "OK";
                } else if (token.equals("n")) {
                    result = "OK";
                } else {
                    result = "Invalid command";
                }
                resetPhase = -1;
            } else if (removePhase > 0) {
                while (sc.hasNextLine()) {
                    token += sc.nextLine();
                }
                studentBuffer[removePhase++] = token;
                if (removePhase == 2) {
                    return "Last name: ";
                }
                studentBuffer[0] = "6315";
                studentBuffer[3] = "5.2";
                removePhase = -1;
                Student toRemove = new Student(studentBuffer);

                if (!toRemove.validate()) {
                    return "Invalid input data";
                } else if (seznamByName.exists(toRemove)) {
                    toRemove = seznamByName.remove(toRemove);
                    seznamById.remove(toRemove);
                } else {
                    return "Student does not exists";
                }

            } else if (searchPhase > 0) {
                while (sc.hasNextLine()) {
                    token += sc.nextLine();
                }
                    studentBuffer[searchPhase++] = token;
                    if (searchPhase == 2) {
                        return "Last name: ";
                    }
                    studentBuffer[0] = "6315";
                    studentBuffer[3] = "5.2";
                    searchPhase = -1;
                    Student toFind = new Student(studentBuffer);

                    if (!toFind.validate()) {
                        return "Invalid input data";
                    } else if (seznamByName.exists(toFind)) {
                        toFind = (Student) ((Drevo23)seznamByName).get(toFind);
                        return "\t" + toFind;
                    } else {
                        return "Student does not exists";
                    }

            } else if (token.equals("add")) {
                if (sc.hasNext()) {
                    return "Invalid argument";
                }
                addPhase = 0;
                studentBuffer = new String[4];
                result = "Student ID: ";

            } else if (token.equals("count")) {
                if (sc.hasNext()) {
                    return "Invalid argument";
                }
                result = "No. of students: " + seznamByName.size();
            } else if (token.equals("reset")) {
                if (sc.hasNext()) {
                    return "Invalid argument";
                }
                result = "Are you sure (y|n): ";
                resetPhase = 1;
            } else if (token.equals("search")) {
                if (sc.hasNext()) {
                    String id = sc.next();
                    Student toFind = new Student(id, "fn", "ln", "5.2");
                    if (!toFind.validate()) {
                        return "Invalid input data";
                    }
                    if (seznamById.exists(toFind)) {
                        toFind = (Student) ((Drevo23)seznamById).get(toFind);
                        return "\t" + toFind.toString();
                    } else {
                        return "Student does not exists";
                    }
                } else {
                    searchPhase = 1;
                    return "First name: ";
                }
            } else if (token.equals("remove")) {
                if (sc.hasNext()) {
                    String id = sc.next();
                    Student toRemove = new Student(id, "fn", "ln", "5.2");
                    if (!toRemove.validate()) {
                        return "Invalid input data";
                    } else if(seznamById.exists(toRemove)) {
                        toRemove = seznamById.remove(toRemove);
                        seznamByName.remove(toRemove);
                        return "OK";
                    } else {
                        return "Student does not exists";
                    }
                } else {
                    removePhase = 1;
                    return "First name: ";
                }
            } else if (token.equals("print")) {
                if (sc.hasNext()) {
                    return "Invalid argument";
                }
                result = "No. of students: " + seznamByName.size();
                result += seznamByName.size() > 0 ? "\n" + seznamByName.asList() : "";
            }else if (token.equals("save")) {
                if (sc.hasNext()) {
                    seznamByName.save(new FileOutputStream(sc.next()));
                } else {
                    result = "Invalid command";
                }

            } else if (token.equals("restore")) {
                if (sc.hasNext()) {
                    String filename = sc.next();
                    seznamByName.restore(new FileInputStream(filename));
                    seznamById.restore(new FileInputStream(filename));
                } else {
                    result = "Invalid command";
                }
            } else {
                result = "Invalid command";
            }
        }  catch (IllegalArgumentException e) {
            result = "Error: Duplicated entry";
        } catch (java.util.NoSuchElementException e) {
            result = "Error: data structure is empty";
        } catch (FileNotFoundException e) {
            result = "I/O Error: " + e.getMessage();
        } catch (IOException e) {
            result = "I/O Error: " + e.getMessage();
        } catch (ClassNotFoundException e) {
            result = "Error: " + e.getMessage();
        }
        return result;
    }
}

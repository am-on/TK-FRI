import java.io.*;
public class Studenti {
    public static void main(String[] args) {
        SeznamiUV seznamiUV = new SeznamiUV();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input;
        String output;
        try {
            input = br.readLine();
            while (!input.equalsIgnoreCase("exit")) {
                output = seznamiUV.processInput(input);
                switch (output) {
                    case "First name: ":
                    case "Last name: ":
                    case "Avg. grade: ":
                    case "Student ID: ":
                    case "Are you sure (y|n): ":
                        System.out.print(output);
                        break;
                    default:
                        System.out.println(output);

                }
                input = br.readLine();
            }
            System.out.println("Goodbye");
        }
        catch (IOException e) {
            System.err.println("Failed to retrieve the next command.");
            System.exit(1);
        }
    }
}
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

class Barista {

    public static void main(String[] args) {
        System.out.println("Hello dear customer :)");
        System.out.println("Do you need a resource controller? Just tell the name of your resource:");

        try(Scanner scanner = new Scanner(System.in)) {
            String resourceName = scanner.nextLine();

            String fileContent = new String(
                Files.readAllBytes(
                    Paths.get("templates/Controller.java")
                )
            );

            String newFileContent = fileContent.replace("${Resource}", resourceName);

            System.out.println("Writing " + resourceName + "Controller.java for you...");
            Files.write(Paths.get(resourceName + "Controller.java"), newFileContent.getBytes());
        }
        catch(Exception e) {
            e.printStackTrace();
        }

        System.out.println("Have a nice day :)");
    }
}

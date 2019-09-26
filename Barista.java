import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

class Barista {

    static String baseInputDirectoryName = "src/main/resources/templates/barista";
    static String baseOutputDirectoryName = "src/main/java";
    final static String LINE = "\n";

    public static void main(String[] args) {
        
        System.out.println(
            "Hello dear customer :)"
            + LINE + "What can I do for you today?"
            + LINE + "1 - I want an entity"
            + LINE + "2 - I want a controller"
        );

        Scanner scanner = new Scanner(System.in);

        int choice = 0;
        
        try {
            choice = Integer.parseInt(scanner.nextLine());
        }
        catch(Exception e) {
            e.printStackTrace();
        }

        switch(choice) {
            case 1:
                baseOutputDirectoryName += "/entities";
                System.out.println("Do you need an entity? Just tell its name:");

                String entityName = scanner.nextLine();
                if(Character.isLowerCase(entityName.charAt(0))) {
                    System.out.println("First letter must be upper case for class name. Nevertheless,");
                    break;
                }

                try {
                    String fileContent = getRenamedClassFileContent("Entity", entityName);
                    generateClassFile(fileContent, entityName);
                }
                catch(Exception e) {
                    e.printStackTrace();
                }
                break;
            case 2:
                baseOutputDirectoryName += "/controllers";
                System.out.println("Do you need a resource controller? Just tell the name of your resource:");

                String resourceName = scanner.nextLine();

                try {
                    String fileContent = getRenamedClassFileContent("Controller", resourceName);
                    resourceName += "Controller";
                    generateClassFile(fileContent, resourceName);
                }
                catch(Exception e) {
                    e.printStackTrace();
                }
                break;
            default:
                System.out.println("Sorry, but I didn't understand your choice");
        }

        scanner.close();

        System.out.println("Have a nice day :)");
    }

    private static String getRenamedClassFileContent(String fileName, String className) throws IOException {
        return new String(
            Files.readAllBytes(
                Paths.get(baseInputDirectoryName + "/" + fileName + ".java")
            )
        ).replace("${ClassName}", className);
    }

    private static void generateClassFile(String content, String fileName) throws IOException {
        fileName += ".java";
           
        System.out.println("Writing " + fileName + " for you...");
        Files.createDirectories(Paths.get(baseOutputDirectoryName));
        Files.write(Paths.get(baseOutputDirectoryName + "/" + fileName), content.getBytes());
    }

}

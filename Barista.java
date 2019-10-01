import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

class Barista {

    public static void main(String[] args) {
        String baseInputDirectoryName = "src/main/resources/templates/barista";
        String baseOutputDirectoryName = "src/main/java";

        System.out.println("Hello dear customer :)");
        System.out.println("What can I do for you today?");
        System.out.println("1 - I want an entity");
        System.out.println("2 - I want a controller");

        Scanner scanner = new Scanner(System.in);

        int choice = 0;
        
        try {
            choice = Integer.parseInt(scanner.nextLine());
        }
        catch(Exception e) {
            e.printStackTrace();
        }

        String outputDirectoryName = baseOutputDirectoryName;

        switch(choice) {
            case 1:
                outputDirectoryName += "/entities";
                System.out.println("Do you need an entity? Just tell its name:");

                String entityName = scanner.nextLine();

                try {
                    String fileContent = new String(
                        Files.readAllBytes(
                            Paths.get(baseInputDirectoryName + "/Entity.java")
                        )
                    );
        
                    String newFileContent = fileContent.replace("${Entity}", entityName);

                    this.writeResourceFile(choice, entityName, outputDirectoryName, newFileContent);
                }
                catch(Exception e) {
                    e.printStackTrace();
                }
                break;
            case 2:
                outputDirectoryName += "/controllers";
                System.out.println("Do you need a resource controller? Just tell the name of your resource:");

                String resourceName = scanner.nextLine();

                try {
                    String fileContent = new String(
                        Files.readAllBytes(
                            Paths.get(baseInputDirectoryName + "/Controller.java")
                        )
                    );
        
                    String newFileContent = fileContent.replace("${Resource}", resourceName);
                    
                    this.writeResourceFile(choice, resourceName, outputDirectoryName, newFileContent);
                }
                catch(Exception e) {
                    e.printStackTrace();
                }
                break;
            default:
                System.out.println("Sorry, but I didn't understand your choice");
        }

        private void writeResourceFile(int resourceNumber, String resourceName, String outputDirectoryName, String fileContent) {
            String remainResourceName = "";
            
            switch(resourceNumber) {
                case 1: remainResourceName = ".java";
                        break;

                case 2: remainResourceName = "Controller.java";
                        break;

                default: System.out.println("Sorry, invalid resource");
            }

            System.out.println("Writing " + resourceName + remainResourceName + " for you...");
            Files.createDirectories(Paths.get(outputDirectoryName));
            Files.write(Paths.get(outputDirectoryName + "/" + resourceName + remainResourceName), newFileContent.getBytes());
        }

        scanner.close();

        System.out.println("Have a nice day :)");
    }
}

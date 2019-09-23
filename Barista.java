import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

class Barista {

    public static void main(String[] args) {
        String baseInputDirectoryName = "src/main/resources/templates/barista";
        String baseOutputDirectoryName = "src/main/java";
        String resourceMapping = "";

        System.out.println("Hello dear customer :)");
        System.out.println("What can I do for you today?");
        System.out.println("1 - I want an entity");
        System.out.println("2 - I want a restController");

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
        
                    System.out.println("Writing " + entityName + ".java for you...");
                    Files.createDirectories(Paths.get(outputDirectoryName));
                    Files.write(Paths.get(outputDirectoryName + "/" + entityName + ".java"), newFileContent.getBytes());
                }
                catch(Exception e) {
                    e.printStackTrace();
                }
                break;
            case 2:
                outputDirectoryName += "/controllers";
                System.out.println("Do you need a resource restController? Just tell the name of your resource:");

                String resourceName = scanner.nextLine();
                String resourceNameNormalize = resourceName.substring(0,1).toUpperCase()+resourceName.substring(1);
                String resourceLowerCase = resourceName.toLowerCase();

                if(resourceLowerCase.endsWith("y")) {
                    resourceMapping = resourceLowerCase.substring(0, resourceLowerCase.length() - 1) + "ies";
                } else {
                        resourceMapping = resourceLowerCase + "s";
                };

                try {
                    String fileContent = new String(
                        Files.readAllBytes(
                            Paths.get(baseInputDirectoryName + "/RestController.java")
                        )
                    );
        
                    String newFileContentPathOne = fileContent.replace("${Resource}", resourceNameNormalize);
                    String newFileContentPathTwo = newFileContentPathOne.replace("${resource}", resourceLowerCase);
                    String newFileContentPathThree = newFileContentPathTwo.replace("${resourceMapping}", resourceMapping );

        
                    System.out.println("Writing " + resourceNameNormalize + "RestController.java for you...");
                    Files.createDirectories(Paths.get(outputDirectoryName));
                    Files.write(Paths.get(outputDirectoryName + "/" + resourceNameNormalize + "RestController.java"), newFileContentPathThree.getBytes());
                    
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
}

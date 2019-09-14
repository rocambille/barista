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
        System.out.println("3 - I want a repository");

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
                System.out.println("Do you need a resource controller? Just tell the name of your resource:");

                String resourceName = scanner.nextLine();

                try {
                    String fileContent = new String(
                        Files.readAllBytes(
                            Paths.get(baseInputDirectoryName + "/Controller.java")
                        )
                    );
        
                    String newFileContent = fileContent.replace("${Resource}", resourceName);
        
                    System.out.println("Writing " + resourceName + "Controller.java for you...");
                    Files.createDirectories(Paths.get(outputDirectoryName));
                    Files.write(Paths.get(outputDirectoryName + "/" + resourceName + "Controller.java"), newFileContent.getBytes());
                }
                catch(Exception e) {
                    e.printStackTrace();
                }
                break;
                case 3:
                outputDirectoryName += "/repositories";
                System.out.println("Do you need a repository? Just say the name of his entity:");

                String repositoryName = scanner.nextLine();

                try {
                    String fileContent = new String(
                        Files.readAllBytes(
                            Paths.get(baseInputDirectoryName + "/Repository.java")
                        )
                    );
        
                    String newFileContent = fileContent.replace("${Repository}", repositoryName);
        
                    System.out.println("Writing " + repositoryName + "Repository.java for you...");
                    Files.createDirectories(Paths.get(outputDirectoryName));
                    Files.write(Paths.get(outputDirectoryName + "/" + repositoryName + "Repository.java"), newFileContent.getBytes());
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

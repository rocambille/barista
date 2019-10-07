import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

class Barista {

    public static void main(String[] args) {
        String baseInputDirectoryName = "src/main/resources/templates/barista";
        String baseOutputDirectoryName = "src/main/java";
        String resourceSlugs = "";

        System.out.println("Hello dear customer :)");
        System.out.println("What can I do for you today?");
        System.out.println("1 - I want an entity");
        System.out.println("2 - I want a resource controller");
        System.out.println("3 - I want a resource REST controller");
        System.out.println("4 - I want a repository");

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
            case 2: {
                outputDirectoryName += "/controllers";
                System.out.println("Do you need a resource controller? Just tell the name of your resource:");

                String resourceName = scanner.nextLine();
                String resourceNameNormalize = resourceName.substring(0,1).toUpperCase()+resourceName.substring(1);
                String resourceLowerCase = resourceName.toLowerCase();

                if(resourceLowerCase.endsWith("y")) {
                    resourceSlugs = resourceLowerCase.substring(0, resourceLowerCase.length() - 1) + "ies";
                } else if(!resourceLowerCase.endsWith("s")){
                    resourceSlugs = resourceLowerCase + "s";
                }

                try {
                    String fileContent = new String(
                        Files.readAllBytes(
                            Paths.get(baseInputDirectoryName + "/Controller.java")
                        )
                    );

                    String newFileContent = fileContent.replace("${UpperName}", resourceNameNormalize);
                    newFileContent = newFileContent.replace("${lowerName}", resourceLowerCase);
                    newFileContent = newFileContent.replace("${slugs}", resourceSlugs );

                    this.writeResourceFile(choice, resourceName, outputDirectoryName, newFileContent);
                }
                catch(Exception e) {
                    e.printStackTrace();
                }
            } break;
            case 3: {
                outputDirectoryName += "/controllers";
                System.out.println("Do you need a resource restController? Just tell the name of your resource:");

                String resourceName = scanner.nextLine();
                String resourceNameNormalize = resourceName.substring(0,1).toUpperCase()+resourceName.substring(1);
                String resourceLowerCase = resourceName.toLowerCase();

                if(resourceLowerCase.endsWith("y")) {
                    resourceSlugs = resourceLowerCase.substring(0, resourceLowerCase.length() - 1) + "ies";
                } else if(!resourceLowerCase.endsWith("s")){
                    resourceSlugs = resourceLowerCase + "s";
                }

                try {
                    String fileContent = new String(
                        Files.readAllBytes(
                            Paths.get(baseInputDirectoryName + "/RestController.java")
                        )
                    );

                    String newFileContent = fileContent.replace("${UpperName}", resourceNameNormalize);
                    newFileContent = newFileContent.replace("${lowerName}", resourceLowerCase);
                    newFileContent = newFileContent.replace("${slugs}", resourceSlugs );

                    this.writeResourceFile(choice, resourceNameNormalize, outputDirectoryName, newFileContent);
                }
                catch(Exception e) {
                    e.printStackTrace();
                }
            } break;
            case 4:
                outputDirectoryName += "/repositories";
                System.out.println("Do you need a repository? Just say the name of his entity:");

                String repositoryName = scanner.nextLine();
                String repositoryNameNormalize = repositoryName.substring(0,1).toUpperCase()+repositoryName.substring(1);

                try {
                    String fileContent = new String(
                        Files.readAllBytes(
                            Paths.get(baseInputDirectoryName + "/Repository.java")
                        )
                    );

                    String newFileContent = fileContent.replace("${Repository}", repositoryNameNormalize);

                    this.writeResourceFile(choice, repositoryNameNormalize, outputDirectoryName, newFileContent);
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

    private void writeResourceFile(int resourceNumber, String resourceName, String outputDirectoryName, String fileContent) {
        String remainResourceName = "";

        switch(resourceNumber) {
            case 1: remainResourceName = ".java";
                    break;

            case 2: remainResourceName = "Controller.java";
                    break;

            case 3: remainResourceName = "RestController.java";
                    break;

            case 4: remainResourceName = "Repository.java";
                    break;

            default: System.out.println("Sorry, invalid resource");
        }

        System.out.println("Writing " + resourceName + remainResourceName + " for you...");
        Files.createDirectories(Paths.get(outputDirectoryName));
        Files.write(Paths.get(outputDirectoryName + "/" + resourceName + remainResourceName), fileContent.getBytes());
    }
}

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.ArrayList;

class Barista {

    private static class Choice{
        String outputDirectoryName;
        String message;
        String inputDirectory;
        String newFileReplacement;
        String newFileExtention;

        Choice(int i){
            if(i == 1){
                outputDirectoryName = "/entities";
                message = "Do you need an entity? Just tell its name:";
                inputDirectory = "/Entity.java";
                newFileReplacement = "${Entity}";
                newFileExtention = ".java";
            }
            if(i == 2){
                outputDirectoryName = "/controllers";
                message = "Do you need a resource controller? Just tell the name of your resource:";
                inputDirectory = "/Controller.java";
                newFileReplacement = "${Resource}";
                newFileExtention = "Controller.java";
            }
        }

    }

    public static void main(String[] args) {
        String baseInputDirectoryName = "src/main/resources/templates/barista";
        String baseOutputDirectoryName = "src/main/java";

        System.out.println("Hello dear customer :)");
        System.out.println("What can I do for you today?");
        System.out.println("1 - I want an entity");
        System.out.println("2 - I want a controller");

        ArrayList<Integer> choicePool = new ArrayList();
        // new options could be added, but Choice class will require modification.
        choicePool.add(1);
        choicePool.add(2);

        Scanner scanner = new Scanner(System.in);

        int choice = 0;
        
        try {
            choice = Integer.parseInt(scanner.nextLine());
        }
        catch(Exception e) {
            e.printStackTrace();
        }

        String outputDirectoryName = baseOutputDirectoryName;

        if (choicePool.contains(choice)){
            Choice choiceObj = new Choice(choice);
            outputDirectoryName += choiceObj.outputDirectoryName;
            System.out.println(choiceObj.message);

            String resourceName = scanner.nextLine();

            try{
                String fileContent = new String(
                        Files.readAllBytes(
                            Paths.get(baseInputDirectoryName + choiceObj.inputDirectory)
                    )
                );

                String newFileContent = fileContent.replace(choiceObj.newFileReplacement, resourceName);
                System.out.println("Writing" + resourceName + choiceObj.newFileExtention + " for you...");
                Files.createDirectories(Paths.get(outputDirectoryName));
                Files.write(Paths.get(outputDirectoryName + "/" + resourceName + choiceObj.newFileExtention), newFileContent.getBytes());

            }
            catch (Exception e){
                e.printStackTrace();
            }

        } else {
            System.out.println("Sorry, but I didn't understand your choice");
        }

        scanner.close();

        System.out.println("Have a nice day :)");
    }
}

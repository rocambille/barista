import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

class Barista {

    public static void main(String[] args) {
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

        FileCreator fileCreator = null;

        switch(choice) {
            case 1:
                fileCreator = new EntityCreator();
                break;
            case 2:
                fileCreator = new ControllerCreator();
                break;
            case 3:
                fileCreator = new RestControllerCreator();
                break;
            case 4:
                fileCreator = new RepositoryCreator();
                break;
            default:
                System.out.println("Sorry, but I didn't understand your choice");
        }

        if (fileCreator != null) {
            fileCreator.createFile();
        }
        scanner.close();

        System.out.println("Have a nice day :)");
    }
}

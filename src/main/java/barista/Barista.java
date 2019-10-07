package barista;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import org.apache.commons.cli.ParseException;
import org.codehaus.plexus.util.ReaderFactory;

class Barista {

    public static void main(String[] args) throws ParseException{
        System.out.println("Hello dear customer :)");
        System.out.println("What can I do for you today?");
        System.out.println("1 - I want an entity");
        System.out.println("2 - I want a controller");

        Scanner scanner = new Scanner(System.in);
        Optional<BaristaClassType> instanceFromChoice = BaristaClassType.getInstanceFromChoice(scanner.nextLine());
        
//        ReaderFactory.newPlatformReader(file)
        
        if (instanceFromChoice.isPresent()) {
        	BaristaClassType baristaClassType = instanceFromChoice.get();
        	generateType(baristaClassType, askForName(baristaClassType, scanner));
        }else {
        	printChoiceError();
        }

        scanner.close();

        System.out.println("Have a nice day :)");
    }
    
    private static void printChoiceError() {
    	System.out.println("Sorry, but I didn't understand your choice");
    }
    
    private static String askForName(BaristaClassType type, Scanner scanner) {
    	System.out.println("You need a " + type.getSuffixName() + ". Just tell the Name:");
    	return scanner.nextLine();
    }
    private static void generateType(BaristaClassType type, String name) {
    	System.out.println("Writing " + name + type.getSuffixName() + ".java for you");
    	new Barista().generate(Arrays.asList(new BaristaClass(type, name)));
    }
    
    public void generate(List<BaristaClass> baristaClassesToGenerate) {
    	String baseInputDirectoryName = "src/main/resources/templates/barista";
        String baseOutputDirectoryName = "src/main/java";
        
        for (BaristaClass baristaClass : baristaClassesToGenerate) {
        	String outputDirectory = baseOutputDirectoryName + "/" + baristaClass.getPackagename();
        	try {
        		String contentOfFileTemplate = new String(Files.readAllBytes(Paths.get(baseInputDirectoryName + "/" + baristaClass.getSuffixName() + ".java")));
        		String newFileContent = contentOfFileTemplate.replace("${" + baristaClass.getParameternameTemplate() + "}", baristaClass.getName()).replace("#####", baristaClass.getPackagename());
        		Files.createDirectories(Paths.get(outputDirectory));
        		Files.write(Paths.get(outputDirectory + "/" + baristaClass.getSimpleClassName() + ".java"), newFileContent.getBytes());
        	}catch(Exception e) {
        		e.printStackTrace();
        	}
        }
    }
}

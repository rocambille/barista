import java.util.Scanner;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.IOException;


abstract class FileCreator {

    public static String baseOutputDirectoryName = "src/main/java";
    public static String baseInputDirectoryName = "src/main/resources/templates/barista";
    public static Scanner scanner = new Scanner(System.in);

    public void createFile() {
        readInputLog();
        String resourceName = scanner.nextLine();
        try {
            String resourceType = getResourceType();
            String fileContent = readTemplate(resourceType);
            String newFileContent = getNewFileContent(fileContent, resourceName);
            String newFileName = getNewFileName(resourceName);
            writeJavaFile(newFileName, getOutputDirectoryName(), newFileContent);
        }
        catch(Exception e) {
            e.printStackTrace();
        }

    }

    public abstract void readInputLog();

    public abstract String getResourceType();

    public abstract String getNewFileName(String resourceName);

    public abstract String getOutputDirectoryName();

    private static String readTemplate(String componentType) throws IOException {
        return new String(
            Files.readAllBytes(
                Paths.get(baseInputDirectoryName + "/"+componentType+".java")
            )
        );
    }

    private static void writeJavaFile(String entityName, String outputDirectoryName, String newFileContent) throws IOException{
        System.out.println("Writing " + entityName + ".java for you...");
        Files.createDirectories(Paths.get(outputDirectoryName));
        Files.write(Paths.get(outputDirectoryName + "/" + entityName + ".java"), newFileContent.getBytes());
    }

    private String getResourceSlugs(String resourceLowerCase) {
        String resourceSlugs = "";
        if(resourceLowerCase.endsWith("y")) {
            resourceSlugs = resourceLowerCase.substring(0, resourceLowerCase.length() - 1) + "ies";
        } else if(!resourceLowerCase.endsWith("s")){
            resourceSlugs = resourceLowerCase + "s";
        }
        return resourceSlugs;
    }

    public String getResourceNameNormalize(String resourceName) {
        return resourceName.substring(0,1).toUpperCase()+resourceName.substring(1);
    }

    public String getNewFileContent(String fileContent, String resourceName) {
        String resourceNameNormalize = getResourceNameNormalize(resourceName);
        String resourceLowerCase = resourceName.toLowerCase();
        String resourceSlugs = getResourceSlugs(resourceLowerCase);

        String newFileContent = fileContent.replace("${UpperName}", resourceNameNormalize);
        newFileContent = newFileContent.replace("${lowerName}", resourceLowerCase);
        newFileContent = newFileContent.replace("${slugs}", resourceSlugs );

        return newFileContent;
    }


}
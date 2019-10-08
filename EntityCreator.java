import java.util.Scanner;


class EntityCreator extends FileCreator {

    @Override
    public void readInputLog() {
        System.out.println("Do you need an entity? Just tell its name:");
    }

    @Override
    public String getNewFileContent(String fileContent, String entityName) {
        return fileContent.replace("${Entity}", entityName);
    }

    @Override
    public String getOutputDirectoryName() {
        return baseOutputDirectoryName + "/entities";
    }

    @Override
    public String getResourceType() {
        return "Entity";
    }

    @Override
    public String getNewFileName(String resourceName) {
        return resourceName;
    }
}
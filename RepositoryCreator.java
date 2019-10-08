import java.util.Scanner;


class RepositoryCreator extends FileCreator {

    @Override
    public void readInputLog() {
        System.out.println("Do you need a repository? Just say the name of his entity:");
    }

    @Override
    public String getNewFileContent(String fileContent, String resourceName) {
        String newFileContent = fileContent.replace("${Repository}", getResourceNameNormalize(resourceName));
        return newFileContent;
    }

    @Override
    public String getOutputDirectoryName() {
        return baseOutputDirectoryName + "/repositories";
    }

    @Override
    public String getResourceType() {
        return "Repository";
    }

    @Override
    public String getNewFileName(String resourceName) {
        String resourceNameNormalize = getResourceNameNormalize(resourceName);
        return resourceNameNormalize + "Repository";
    }
}
import java.util.Scanner;


class ControllerCreator extends FileCreator {

    @Override
    public void readInputLog() {
        System.out.println("Do you need a resource controller? Just tell the name of your resource:");
    }

    @Override
    public String getNewFileContent(String fileContent, String resourceName) {
        return super.getNewFileContent(fileContent, resourceName);
    }

    @Override
    public String getOutputDirectoryName() {
        return baseOutputDirectoryName + "/controllers";
    }

    @Override
    public String getResourceType() {
        return "Controller";
    }

    @Override
    public String getNewFileName(String resourceName) {
        String resourceNameNormalize = getResourceNameNormalize(resourceName);
        return resourceNameNormalize + "Controller";
    }
}
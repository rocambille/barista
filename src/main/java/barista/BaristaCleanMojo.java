package barista;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

@Mojo(name = "clean", defaultPhase = LifecyclePhase.CLEAN)
public class BaristaCleanMojo extends AbstractMojo {

	@Parameter(readonly = true, required = true, defaultValue = "${project.basedir}")
	private File output;

	public void cleanForType(BaristaClassType baristaClassType, String outputPath) {
		String resourcePath = outputPath + "/" + baristaClassType.getPackagename();
		
		try {
			if (Files.exists(Paths.get(resourcePath))){
				Files.list(Paths.get(resourcePath)).forEach(BaristaCleanMojo::delete);
				Files.deleteIfExists(Paths.get(resourcePath));
				getLog().info("delete directory (and contents) of " + resourcePath);
			}
		}catch(Exception e) {
			getLog().error("problems deleting directory (and/or contents) of " + resourcePath, e);
		}
	}
	public static void delete(Path p) {
		try {
			Files.delete(p);
		}catch(IOException e) {}
	}
	@Override
	public void execute() throws MojoExecutionException, MojoFailureException {
		String outputPath = output.getAbsolutePath() + "/src/main/java";
		
		Arrays.stream(BaristaClassType.values()).forEach((c)->cleanForType(c, outputPath));
	}

}

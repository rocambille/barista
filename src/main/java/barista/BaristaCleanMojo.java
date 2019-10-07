package barista;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

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

	@Override
	public void execute() throws MojoExecutionException, MojoFailureException {
		String outputPath = output.getAbsolutePath() + "/src/main/java";
		String entityPath = outputPath + "/entities";
		String controllerPath = outputPath + "/controllers";

		try {
			if (Files.exists(Paths.get(entityPath))) {
				Files.list(Paths.get(entityPath)).forEach((p) -> {
					try {
						Files.delete(p);
					} catch (IOException e) {
					}
				});
				Files.deleteIfExists(Paths.get(entityPath));
				getLog().info("delete all generated entities");
			}
		}catch(IOException e) {
			getLog().error("couldn't delete all generated entities", e);
		}
		
		try {
			if (Files.exists(Paths.get(controllerPath))) {
				Files.list(Paths.get(controllerPath)).forEach((p) -> {
					try {
						Files.delete(p);
					} catch (IOException e) {
					}
				});
				Files.deleteIfExists(Paths.get(controllerPath));
				getLog().info("deleted all generated controllers");
			}

		} catch (IOException e) {
			getLog().error("couldn't delete all generated entities", e);
		}
	}

}

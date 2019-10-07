package barista;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

@Mojo(name = "generate", defaultPhase = LifecyclePhase.COMPILE)
public class BaristaGenerateMojo extends AbstractMojo {

	@Parameter(name = "baristaClasses")
	private BaristaClass[] baristaClasses;

	@Parameter(name = "baristaClass")
	public BaristaClass baristaClass;
	

	@Parameter(readonly = true, required = true, defaultValue = "${project.basedir}")
	private File output;

	@Override
	public void execute() throws MojoExecutionException, MojoFailureException {
		if (baristaClass != null) {
			generate(baristaClass, output);
		}

		if (baristaClasses != null) {
			generate(Arrays.asList(baristaClasses), output);
		}

	}

	public static void generate(BaristaClass baristaClass, File output) {
		generate(Arrays.asList(baristaClass), output);
	}

	public static void generate(List<BaristaClass> baristaClasses, File output) {
		Barista barista = Barista.getBaristaForPlugin(output);
		barista.generate(baristaClasses);
	}

	public static void logError() {
		System.out.println("problem with generating");
	}
}

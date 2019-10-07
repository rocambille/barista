package barista;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

@Mojo(name = "generate")
public class BaristaMojo extends AbstractMojo {
	
	@Parameter(name = "classType", defaultValue="Entity", required = true)
	private String classType;
	
	@Parameter(name="className", defaultValue="Your", required=true)
	private String className;

	@Override
	public void execute() throws MojoExecutionException, MojoFailureException {
		Optional<BaristaClass> baristaClass = Arrays.stream(BaristaClassType.values()).filter((t) -> t.getSuffixName().equals(classType)).findAny().map((t) -> new BaristaClass(t, className));
		
		baristaClass.ifPresent(BaristaMojo::generate);
		
		if (!baristaClass.isPresent()) {
			logError();
		}
	}

	public static void generate(BaristaClass baristaClass) {
		generate(Arrays.asList(baristaClass));
	}
	
	public static void generate(List<BaristaClass> baristaClasses) {
		Barista barista = new Barista();
		barista.generate(baristaClasses);
	}
	
	public static void logError() {
		System.out.println("problem with generating");
	}
}

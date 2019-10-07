package barista;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Stream;

import org.apache.commons.cli.ParseException;

class Barista {
	String baseInputDirectoryName = "src/main/resources/templates/barista";
	String baseOutputDirectoryName = "src/main/java";
	boolean plugin = false;

	private Barista() {
	}

	public static Barista getBaristaForPlugin(File output) {
		Barista barista = new Barista();
		barista.baseInputDirectoryName = "/templates/barista";
		barista.baseOutputDirectoryName = output.getAbsolutePath() + "/src/main/java";
		barista.plugin = true;
		return barista;
	}

	public static void main(String[] args) throws ParseException {
		System.out.println("Hello dear customer :)");
		System.out.println("What can I do for you today?");
		Stream.of(BaristaClassType.values()).forEach((c) -> {System.out.println("" + c.getChoice() + " - I want " + ("aeiou".indexOf(c.getPackagename().toLowerCase().charAt(0))==-1?"a ":"an ")  + c.name());});

		Scanner scanner = new Scanner(System.in);
		Optional<BaristaClassType> instanceFromChoice = BaristaClassType.getInstanceFromChoice(scanner.nextLine());

		if (instanceFromChoice.isPresent()) {
			BaristaClassType baristaClassType = instanceFromChoice.get();
			generateType(baristaClassType, askForName(baristaClassType, scanner));
		} else {
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
		for (BaristaClass baristaClass : baristaClassesToGenerate) {
			String outputDirectory = baseOutputDirectoryName + "/" + baristaClass.getPackagename();

			try {
				String contentOfFileTemplate = "hello";
				if (plugin) {
					InputStream inputStream = this.getClass()
							.getResourceAsStream(baseInputDirectoryName + "/" + baristaClass.getType().name() + ".java");
					contentOfFileTemplate = readFromStream(inputStream);
				} else {
					contentOfFileTemplate = readFromPath(Paths.get(baseInputDirectoryName + "/" + baristaClass.getType().name() + ".java"));
				}

				writeFile(contentOfFileTemplate, baristaClass, outputDirectory);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	private boolean writeFile(String originalContent, BaristaClass baristaClass, String outputDirectory) {
		String resourceLowerCase = baristaClass.getName().toLowerCase();
		String resourceNameNormalize = resourceLowerCase.substring(0,1).toUpperCase()+resourceLowerCase.substring(1);
		String resourceSlugs = "";
		if (resourceLowerCase.endsWith("y")) {
			resourceSlugs = resourceLowerCase.substring(0, resourceLowerCase.length()-1) + "ies";
		}else if (!resourceLowerCase.endsWith("s")) {
			resourceSlugs = resourceLowerCase + "s";
		}
		
		String newFileContent = originalContent
				.replace("${" + baristaClass.getParameternameTemplate() + "}", baristaClass.getName())
				.replace("${UpperName}", resourceNameNormalize)
				.replace("${lowerName}", resourceLowerCase)
				.replace("${slugs}", resourceSlugs)
				.replace("#####", baristaClass.getPackagename());
		try {
			Path outputPath = Paths.get(outputDirectory);
			Files.createDirectories(outputPath);
			Path filePath = Paths.get(outputDirectory + "/" + baristaClass.getSimpleClassName() + ".java");
			Files.write(filePath, newFileContent.getBytes());
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	private String readFromPath(Path path) throws IOException{
		return new String(Files.readAllBytes(path));
	}
	private String readFromStream(InputStream inputStream) throws IOException {
		StringBuilder stringBuilder = new StringBuilder();
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
		String line = "";
		while ((line = bufferedReader.readLine()) != null) {
			stringBuilder.append(line).append("\n");
		}
		bufferedReader.close();
		return stringBuilder.toString();
	}
}

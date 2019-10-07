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
		System.out.println("1 - I want an entity");
		System.out.println("2 - I want a controller");

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
							.getResourceAsStream(baseInputDirectoryName + "/" + baristaClass.getSuffixName() + ".java");
					if (inputStream == null) {
						System.out.println("INPUTSTREAM is null!!!");
					} else {
						contentOfFileTemplate = readFromStream(inputStream);
						String newFileContent = contentOfFileTemplate
								.replace("${" + baristaClass.getParameternameTemplate() + "}", baristaClass.getName())
								.replace("#####", baristaClass.getPackagename());

						Path outputPath = Paths.get(outputDirectory);
						System.out.println("outputPath = " + outputPath.toString());
						Files.createDirectories(outputPath);
						Path filePath = Paths.get(outputDirectory + "/" + baristaClass.getSimpleClassName() + ".java");
						System.out.println("filePath = " + filePath.toString());
						Files.write(filePath, newFileContent.getBytes());

					}
				} else {
					contentOfFileTemplate = new String(Files.readAllBytes(
							Paths.get(baseInputDirectoryName + "/" + baristaClass.getSuffixName() + ".java")));

					String newFileContent = contentOfFileTemplate
							.replace("${" + baristaClass.getParameternameTemplate() + "}", baristaClass.getName())
							.replace("#####", baristaClass.getPackagename());
					Path outputDir = Paths.get(outputDirectory);
					System.out.println("output = " + outputDir.toUri().toString());
					Files.createDirectories(Paths.get(outputDirectory));
					Files.write(Paths.get(outputDirectory + "/" + baristaClass.getSimpleClassName() + ".java"),
							newFileContent.getBytes());
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

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

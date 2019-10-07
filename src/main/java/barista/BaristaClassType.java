package barista;

import java.util.Arrays;
import java.util.Optional;

public enum BaristaClassType {

	ENTITY("1", "entities", "Entity"), CONTROLLER("2", "controllers", "Resource");
	private String choice;
	private String packagename;
	private String replaceInTemplate;
	private BaristaClassType(String choice, String packagename, String replaceInTemplate) {
		this.choice = choice;
		this.packagename = packagename;
		this.replaceInTemplate = replaceInTemplate;
	}
	
	public static Optional<BaristaClassType> getInstanceFromChoice(String choice) {
		return Arrays.stream(values()).filter((t) -> t.choice.contentEquals(choice)).findFirst();
	}
	
	public String getSuffixName() {
		return name().charAt(0) + name().toLowerCase().substring(1);
	}

	public String getChoice() {
		return choice;
	}

	public String getPackagename() {
		return packagename;
	}

	public String getReplaceInTemplate() {
		return replaceInTemplate;
	}
	
	
}

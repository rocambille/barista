package barista;

import java.util.Arrays;
import java.util.Optional;

public enum BaristaClassType {

	Entity("entities", "Entity"), Controller("controllers", "Resource"), RestController("rest", "RestController"), Repository("repository", "Repository");
	private String packagename;
	private String replaceInTemplate;
	private BaristaClassType(String packagename, String replaceInTemplate) {
		this.packagename = packagename;
		this.replaceInTemplate = replaceInTemplate;
	}
	
	public static Optional<BaristaClassType> getInstanceFromChoice(String choice) {
		return Arrays.stream(values()).filter((t) -> ("" + (t.ordinal() + 1)).contentEquals(choice)).findFirst();
	}
	
	public String getSuffixName() {
		return name().charAt(0) + name().toLowerCase().substring(1);
	}


	public String getPackagename() {
		return packagename;
	}

	public String getReplaceInTemplate() {
		return replaceInTemplate;
	}
	
	
}

package barista;

public class BaristaClass {

	private BaristaClassType type;
	private String name;
	
	public BaristaClass(BaristaClassType type, String name) {
		super();
		this.type = type;
		this.name = name;
	}
	public BaristaClass() {}
	public BaristaClassType getType() {
		return type;
	}
	public String getName() {
		return name;
	}
	
	public String getPackagename() {
		return type.getPackagename();
	}
	
	public String getSuffixName() {
		return type.getSuffixName();
	}
	
	public String getParameternameTemplate() {
		return type.getReplaceInTemplate();
	}
	
	public String getSimpleClassName() {
		return name + getSuffixName();
	}
	
}


public enum SortFieldEnum {
	DEFAULT("firstName"),
	FIRSTNAME("firstName"),
	LASTNAME("lastName"),
	DOB("dateOfBirth"),
	SSN("ssn"),
	HEIGHT("height"),
	WEIGHT("weight");
	
	private String value;
	
	SortFieldEnum(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return this.value;
	}
}

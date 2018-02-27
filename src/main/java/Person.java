import java.util.Date;

public class Person {
	private String ssn;
	private Date dateOfBirth;
	private String firstName;
	private String lastName;
	private double heightIn;
	private double weightLb;

	public String getSsn() {
		return this.ssn;
	}

	public void setSsn(String ssn) {
		this.ssn = ssn;
	}

	public Date getDateOfBirth() {
		return this.dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public double getHeightIn() {
		return this.heightIn;
	}

	public void setHeightIn(double heightIn) {
		this.heightIn = heightIn;
	}

	public double getWeightLb() {
		return this.weightLb;
	}

	public void setWeightLb(double weightLb) {
		this.weightLb = weightLb;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("<div>");
		sb.append(firstName).append(" " + lastName);
		sb.append("<br/>");
		sb.append(dateOfBirth);
		sb.append("<br/>");
		sb.append(ssn);
		sb.append("<br/>");
		sb.append(heightIn);
		sb.append("<br/>");
		sb.append(weightLb);
		sb.append("<br/>");
		sb.append("</div>");
		return sb.toString();
	}
}

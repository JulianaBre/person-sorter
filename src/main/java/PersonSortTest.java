import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class PersonSortTest {
	private Iterable<Person> people = populatePeopleList();
	private String ascending = "true";
	private String descending = "false";
	private static final double DELTA = 0;
	private Date dob1;
	private Date dob2;
	private Date dob3;

	@Test
	public void shouldSortFirstNameAsc() {
		// given
		List<Person> result = PersonSort.sort(people, SortFieldEnum.FIRSTNAME.getValue(), ascending);
		// then
		assertEquals("Alfonso", result.get(0).getFirstName());
	}

	@Test
	public void shouldSortFirstNameDesc() {
		// given
		List<Person> result = PersonSort.sort(people, SortFieldEnum.FIRSTNAME.getValue(), descending);
		// then
		assertEquals("Crissy", result.get(0).getFirstName());
	}

	@Test
	public void shouldSortLastNameAsc() {
		// given
		List<Person> result = PersonSort.sort(people, SortFieldEnum.LASTNAME.getValue(), ascending);
		// then
		assertEquals("Camel", result.get(0).getLastName());
	}

	@Test
	public void shouldSortLastNameDesc() {
		// given
		List<Person> result = PersonSort.sort(people, SortFieldEnum.LASTNAME.getValue(), descending);
		// then
		assertEquals("Jones", result.get(0).getLastName());
	}

	@Test
	public void shouldSortDobAsc() {
		// given
		List<Person> result = PersonSort.sort(people, SortFieldEnum.DOB.getValue(), ascending);
		// then
		assertEquals(dob3, result.get(0).getDateOfBirth());
	}

	@Test
	public void shouldSortDobDesc() {
		// given
		List<Person> result = PersonSort.sort(people, SortFieldEnum.DOB.getValue(), descending);
		// then
		assertEquals(dob1, result.get(0).getDateOfBirth());
	}

	@Test
	public void shouldSortSsnAsc() {
		// given
		List<Person> result = PersonSort.sort(people, SortFieldEnum.SSN.getValue(), ascending);
		// then
		assertEquals("123", result.get(0).getSsn());
	}

	@Test
	public void shouldSorSsnDesc() {
		// given
		List<Person> result = PersonSort.sort(people, SortFieldEnum.SSN.getValue(), descending);
		// then
		assertEquals("789", result.get(0).getSsn());
	}

	@Test
	public void shouldSortHeightAsc() {
		// given
		List<Person> result = PersonSort.sort(people, SortFieldEnum.HEIGHT.getValue(), ascending);
		// then
		assertEquals(55.5, result.get(0).getHeightIn(), DELTA);
	}

	@Test
	public void shouldSortHeightDesc() {
		// given
		List<Person> result = PersonSort.sort(people, SortFieldEnum.HEIGHT.getValue(), descending);
		// then
		assertEquals(70.1, result.get(0).getHeightIn(), DELTA);
	}

	@Test
	public void shouldSortWeightAsc() {
		// given
		List<Person> result = PersonSort.sort(people, SortFieldEnum.WEIGHT.getValue(), ascending);
		// then
		assertEquals(120.2, result.get(0).getWeightLb(), DELTA);
	}

	@Test
	public void shouldSortWeightDesc() {
		// given
		List<Person> result = PersonSort.sort(people, SortFieldEnum.WEIGHT.getValue(), descending);
		// then
		assertEquals(170.7, result.get(0).getWeightLb(), DELTA);
	}

	@Test
	public void shouldSortDefaultIfNotFound() {
		// given
		List<Person> result = PersonSort.sort(people, SortFieldEnum.FIRSTNAME.getValue(), ascending);
		// then
		assertEquals("Alfonso", result.get(0).getFirstName());
	}

	private Iterable<Person> populatePeopleList() {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -1);
		dob1 = cal.getTime();
		cal.add(Calendar.DATE, -2);
		dob2 = cal.getTime();
		cal.add(Calendar.DATE, -3);
		dob3 = cal.getTime();
		Person person1 = new Person();
		Person person2 = new Person();
		Person person3 = new Person();
		person1.setFirstName("Alfonso");
		person1.setLastName("Jones");
		person1.setDateOfBirth(dob1);
		person1.setSsn("123");
		person1.setHeightIn(60.1);
		person1.setWeightLb(150.1);
		person2.setFirstName("Beth");
		person2.setLastName("Camel");
		person2.setDateOfBirth(dob2);
		person2.setSsn("456");
		person2.setHeightIn(55.5);
		person2.setWeightLb(120.2);
		person3.setFirstName("Crissy");
		person3.setLastName("Field");
		person3.setDateOfBirth(dob3);
		person3.setSsn("789");
		person3.setHeightIn(70.1);
		person3.setWeightLb(170.7);
		return Arrays.asList(person1, person2, person3);
	}

	public static void main(String[] args) {
		Result result = JUnitCore.runClasses(PersonSortTest.class);
		for (Failure failure : result.getFailures()) {
			System.out.println(failure.toString());
		}
		if (result.getFailureCount() < 1) {
			System.out.println("All tests passed!");
		}
	}
}

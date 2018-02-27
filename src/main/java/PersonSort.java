import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class PersonSort {

	private List<Person> people = null;

	public PersonSort(Iterable<Person> people) {
		this.people = (List<Person>) people;
	}

	public List<Person> sort(String sortField, String ascending) {
		boolean isAscending = ascending.equalsIgnoreCase("true") ? true : false;
		List<Person> sortedList = null;
		switch (sortField) {
		case "firstName":
			sortedList = people.stream().sorted(Comparator.comparing(Person::getFirstName))
					.collect(Collectors.toList());
			break;
		case "lastName":
			sortedList = people.stream().sorted(Comparator.comparing(Person::getLastName)).collect(Collectors.toList());
			break;
		case "height":
			sortedList = people.stream().sorted(Comparator.comparingDouble(Person::getHeightIn))
					.collect(Collectors.toList());
			break;
		case "weight":
			sortedList = people.stream().sorted(Comparator.comparingDouble(Person::getWeightLb))
					.collect(Collectors.toList());
			break;
		case "dateOfBirth":
			sortedList = people.stream().sorted(Comparator.comparing(Person::getDateOfBirth))
					.collect(Collectors.toList());
			break;
		case "ssn":
			sortedList = people.stream().sorted(Comparator.comparing(Person::getSsn)).collect(Collectors.toList());
			break;
		default:
			sortedList = people.stream().sorted(Comparator.comparing(Person::getFirstName))
					.collect(Collectors.toList());
		}
		if (!isAscending) {
			Collections.reverse(sortedList);
		}
		return sortedList;
	}
}

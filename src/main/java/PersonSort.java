import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Utility class for sorting a list of Person.
 * @author jbreith
 *
 */
public class PersonSort {
	
	// Sort Person List for given criteria
	public static List<Person> sort(Iterable<Person> people, String sortField, String ascending) {
		boolean isAscending = ascending.equalsIgnoreCase("true") ? true : false;
		List<Person> peopleList = (List<Person>) people;
		switch (sortField) {
		case "firstName":
			peopleList = peopleList.stream().sorted(Comparator.comparing(Person::getFirstName))
					.collect(Collectors.toList());
			break;
		case "lastName":
			peopleList = peopleList.stream().sorted(Comparator.comparing(Person::getLastName)).collect(Collectors.toList());
			break;
		case "height":
			peopleList = peopleList.stream().sorted(Comparator.comparingDouble(Person::getHeightIn))
					.collect(Collectors.toList());
			break;
		case "weight":
			peopleList = peopleList.stream().sorted(Comparator.comparingDouble(Person::getWeightLb))
					.collect(Collectors.toList());
			break;
		case "dateOfBirth":
			peopleList = peopleList.stream().sorted(Comparator.comparing(Person::getDateOfBirth))
					.collect(Collectors.toList());
			break;
		case "ssn":
			peopleList = peopleList.stream().sorted(Comparator.comparing(Person::getSsn)).collect(Collectors.toList());
			break;
		default:
			peopleList = peopleList.stream().sorted(Comparator.comparing(Person::getFirstName))
					.collect(Collectors.toList());
		}
		if (!isAscending) {
			Collections.reverse(peopleList);
		}
		return peopleList;

	}
}

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.AbstractHandler;
import org.eclipse.jetty.server.handler.ContextHandler;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

public class PersonHandler extends AbstractHandler {
	private static final String SORT_FIELD = "sortField";
	private static final String ASCENDING = "ascending";

	@Override
	public void handle(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		// Declare response encoding and types
		response.setContentType("text/html; charset=utf-8");

		String fieldDefault = SortFieldEnum.DEFAULT.getValue();
		String ascDefault = "true";

		// Get param values or set to defaults
		String sortField = request.getParameter(SORT_FIELD) != null ? request.getParameter(SORT_FIELD)
				: SortFieldEnum.DEFAULT.getValue();
		String ascending = request.getParameter(ASCENDING) != null ? request.getParameter(ASCENDING) : ascDefault;

		Iterable<Person> people = getPeople();

		List<Person> sortedPeople = sort(people, sortField, ascending);

		// Declare response status code
		response.setStatus(HttpServletResponse.SC_OK);

		// Write back response
		response.getWriter()
				.println(getContentOptions() + getContentSortHeader(sortField, ascending) + sortedPeople.toString());

		// Inform jetty that this request has now been handled
		baseRequest.setHandled(true);
	}

	// Sort Person List for given criteria
	static List<Person> sort(Iterable<Person> people, String sortField, String ascending) {
		PersonSort personSort = new PersonSort(people);
		return personSort.sort(sortField, ascending);

	}

	private String getContentOptions() {
		return "<h3>Options</h3>" + "<a href='/people?sortField=firstName&ascending=true'>first name ASC</a><br/>"
				+ "<a href='people?sortField=dateOfBirth&ascending=true'>date of birth ASC</a><br/>"
				+ "<a href='people?sortField=ssn&ascending=true'>ssn ASC</a><br/>"
				+ "<a href='people?sortField=height&ascending=true'>height ASC</a><br/>"
				+ "<a href='people?sortField=weight&ascending=true'>weight ASC</a><br/>"
				+ "<a href='people?sortField=firstName&ascending=false'>first name DESC</a><br/>"
				+ "<a href='people?sortField=dateOfBirth&ascending=false'>date of birth DESC</a><br/>"
				+ "<a href='people?sortField=ssn&ascending=false'>ssn DESC</a><br/>"
				+ "<a href='people?sortField=height&ascending=false'>height DESC</a><br/>"
				+ "<a href='people?sortField=weight&ascending=false'>weight DESC</a><br/>";
	}

	private String getContentSortHeader(String sortField, String ascending) {
		return "<h3> Sorting by: " + sortField + " Ascending: " + ascending + "</h3>";
	}

	// Populate Person objects via Gson
	private Iterable<Person> getPeople() {
		List<Person> people = null;
		String content = null;
		URL url = this.getClass().getResource("/Person.json");
		File jsonFile = new File(url.getPath());
		try {
			content = new String(Files.readAllBytes(jsonFile.toPath()));
		} catch (IOException e) {
			e.getMessage();
			e.printStackTrace();
		}
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		people = gson.fromJson(content, new TypeToken<List<Person>>() {
		}.getType());
		return people;
	}

	// Set context and init Jetty server
	public static void main(String[] args) throws Exception {
		Server server = new Server(8080);
		ContextHandler context = new ContextHandler();
		context.setContextPath("/people");
		context.setHandler(new PersonHandler());
		server.setHandler(context);

		server.start();
		server.join();
	}
}

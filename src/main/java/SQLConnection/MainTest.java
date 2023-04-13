package SQLConnection;

import Controllers.PrimaryController;
import MVC_Components.Model;
import MVC_Components.View;

/**
 * Main executable class.
 * @author Jorel Louie Chim
 */

public class MainTest {
	
	/**
	 * Main executable method to launch the program.
	 * @param args main arguments
	 */

	public static void main(String[] args) {
		
		String url = "jdbc:mysql://localhost:3306/echodata";
		String username = "root";
		String password = "password";
		
		Model model = Model.createConnection(url, username, password);
		View view = new View();
		PrimaryController controller = new PrimaryController(view, model);
	}

}

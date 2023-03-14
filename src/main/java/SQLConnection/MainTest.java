package SQLConnection;

import MVC_Components.Controller;
import MVC_Components.Model;
import MVC_Components.View;

public class MainTest {

	public static void main(String[] args) {
		
		/*
		 * Set your url, username and password below for your database (SQL):
		 */
		
		String url = "jdbc:mysql://localhost:3306/echodata";
		String username = "root";
		String password = "password";
		
		Model model = Model.createConnection(url, username, password);
		View view = new View();
		Controller controller = new Controller(view, model);
	}

}

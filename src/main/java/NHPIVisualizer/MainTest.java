package NHPIVisualizer;

public class MainTest {

	public static void main(String[] args) {
		
		/*
		 * Set your url, username and password below for your database (SQL):
		 */
		
		String url = "jdbc:mysql://localhost:3306/echodata";
		String username = "root";
		String password = "123456jj";
		
		GUIModel model = GUIModel.createConnection(url, username, password);
		GUIView view = new GUIView();
		GUIController controller = new GUIController(view, model);
		System.out.println(controller);
	}

}

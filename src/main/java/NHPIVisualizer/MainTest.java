package NHPIVisualizer;

public class MainTest {

	public static void main(String[] args) {
		GUIModel model = GUIModel.createConnection("jdbc:mysql://localhost:3306/echodata", 
				"root", 
				"123456jj"
		);
		GUIView view = new GUIView();
		GUIController controller = new GUIController(view);
	}

}

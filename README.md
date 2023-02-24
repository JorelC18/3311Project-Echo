# Description

NHPI Visualizer is a project for a course in software design. It uses Java Swing, the Apache Commons Math Library, WEKA, JFreeChart and a SQL database.

## How To Use:

The main executable java file is MainTest.java. 

Input your own SQL username and passwor

Then run MainTest.java to see the GUI and start the program.

```java
package NHPIVisualizer;

// Run this class to start the program / software

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
```

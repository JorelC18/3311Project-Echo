# Description

NHPI Visualizer is a project for a York University course in software design called EECS 3311. It uses Java Swing, the Apache Commons Math Library, WEKA, JFreeChart and a SQL database.

## How To Use:

The main executable java file is MainTest.java. 

Input your own SQL url, username, and password in both the MainTest class and the SQLConnection class.

Then run MainTest.java to see the GUI and start the program.

```java
package SQLConnection;

import MVC_Components.Controller;
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
		Controller controller = new Controller(view, model);
	}

}
```

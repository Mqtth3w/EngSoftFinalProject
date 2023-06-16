/*
 * @author MATTEO GIANVENUTI
 * @license all rights reserved.
 */
package wineshop;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/*
 * javafx 
 * 
 * --module-path "C:\Program Files\Java\javafx-sdk-19\lib" --add-modules javafx.controls,javafx.fxml
 * 
 */

/**
*
* The class {@code ClientView} defines the application.
*
**/

public class ClientView extends Application
{	
	private Client client = new Client();
	
	@Override
	public void start(final Stage primaryStage)
	{			
		Scene scene = new Scene(new GridPane(), 900, 550);
		HandlePages pageHandler = new HandlePages(scene, primaryStage, client);
		pageHandler.loadLogin();
		primaryStage.setScene(scene);
		primaryStage.show();	
		
	}
	
	@Override
	public void stop()
	{		
		client.logout();
		client.close();
		System.exit(0);
	}
	
	/**
	 * Starts the client interface.
	 *
	 * @param args the method does not requires arguments.
	 *
	**/
	public static void main(final String[] args)
	{
		launch(args);
	}
}
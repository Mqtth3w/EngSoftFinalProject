/*
 * @author MATTEO GIANVENUTI
 * @license all rights reserved.
 */
package wineshop;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.Window;

/**
*
* The class {@code SearchWineController} defines a controller for search wines page interface.
*
**/

public class SearchWineController implements Initializable
{

	private Client client;
	
	@FXML
	private TableView<PropertyWine> tvData;
	@FXML
	private TableColumn<String, String> colname;
	@FXML
	private TableColumn<String, String> colyear;
	@FXML
	private TableColumn<String, String> colproductor;
	@FXML
	private TableColumn<String, String> colorigin;
	@FXML
	private TableColumn<String, String> colnotes;
	@FXML
	private TableColumn<String, String> colgrape;
	@FXML
	private TableColumn<String, String> colquality;
	@FXML
	private TableColumn<String, String> colprice;
	@FXML
	private TableColumn<String, String> colsupplierprice;
	@FXML
	private TableColumn<String, String> colquantity;
	@FXML
	private TableColumn<String, String> colsales;

	private ObservableList<PropertyWine> tvObservableList = FXCollections.observableArrayList();
	
	@FXML
	private TextField wineName;
	@FXML
	private TextField wineYear;
	
	/**
	 * Sets the client.
	 *
	 * @param c the client.
	 *
	**/
	void setClient(Client c)
	{
		this.client = c;
	}
	
	/**
	 * Create an alert.
	 *
	 * @param alertType the alert type.
	 * @param owner the window.
	 * @param title the alert title.
	 * @param message the alert message.
	 *
	**/
	private void showAlert(Alert.AlertType alertType, Window owner, String title, String message) 
	{
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.initOwner(owner);
        alert.show();
    }
	
	/**
	 * Handle home button.
	 *
	 * @param e the event.
	 *
	**/
	@FXML
	public void handleHomeButton(final ActionEvent e)
	{
		Scene scene = ((Node)e.getSource()).getScene();
		Stage primaryStage = (Stage) scene.getWindow();
		HandlePages pageHandler = new HandlePages(scene, primaryStage, client);
		String role = client.getUser().getRole();
		if(role.equals("employee"))
		{
			pageHandler.loadEmployeemenu();
		}
		else if(role.equals("admin"))
		{
			pageHandler.loadAdminmenu();
		}
	}
	
	/**
	 * Handle search wines button.
	 *
	 * @param e the event.
	 *
	**/
	@FXML
	public void handleSearchButton(final ActionEvent e)
	{
		Scene scene = ((Node)e.getSource()).getScene();
		Stage primaryStage = (Stage) scene.getWindow();
		
		if(wineName.getText().isEmpty())
		{
			showAlert(Alert.AlertType.ERROR, primaryStage, "Errore", "Nome vino obbligatorio.");
		}
		else  
		{
			int number = -1;
			if(!wineYear.getText().isEmpty())
			{
				try {
					
		            number = Integer.parseInt(wineYear.getText());
		        }
		        catch (NumberFormatException ex){
		            ex.printStackTrace();
		            showAlert(Alert.AlertType.ERROR, primaryStage, "Errore", "Anno deve essere un numero.");
		            number = -1;
		        }
			}
			
			tvObservableList.clear();
			List<Wine> wines = client.searchWine(wineName.getText(), number);
			if(wines.size() > 0)
			{
				for(Wine w : wines)
				{
					PropertyWine wine = new PropertyWine(w.getName(),w.getProductor(),w.getOrigin(),w.getYear(),
							w.getTechnicalNotes(),w.getGrapeVariety(),w.getQuality(),w.getPrice(),
							w.getSupplierPrice(),w.getQuantity(),w.getSalesNumber());
					tvObservableList.add(wine);
				}
				tvData.setItems(tvObservableList);
			}
			else
			{
				showAlert(Alert.AlertType.INFORMATION, primaryStage, "Info", "Nessun risultato.");
			}
		}
		
	}
	
	/** {@inheritDoc} **/
	@Override
	public void initialize(final URL location, final ResourceBundle resources)
	{
	    colname.setCellValueFactory(new PropertyValueFactory<>("name"));
	    colyear.setCellValueFactory(new PropertyValueFactory<>("year"));
	    colproductor.setCellValueFactory(new PropertyValueFactory<>("productor"));
	    colorigin.setCellValueFactory(new PropertyValueFactory<>("origin"));
	    colnotes.setCellValueFactory(new PropertyValueFactory<>("TechnicalNotes"));
	    colgrape.setCellValueFactory(new PropertyValueFactory<>("GrapeVariety"));
	    colquality.setCellValueFactory(new PropertyValueFactory<>("quality"));
	    colprice.setCellValueFactory(new PropertyValueFactory<>("price"));
	    colsupplierprice.setCellValueFactory(new PropertyValueFactory<>("supplierPrice"));
	    colquantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
	    colsales.setCellValueFactory(new PropertyValueFactory<>("SalesNumber"));
	    tvData.setItems(tvObservableList);
	}
}

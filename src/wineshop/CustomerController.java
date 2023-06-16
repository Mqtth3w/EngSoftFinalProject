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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
*
* The class {@code CustomerController} defines a controller for customer menu page interface.
*
**/

public class CustomerController implements Initializable
{
	private Client client;
	
	@FXML
	private TableView<PropertyPromo> tvDataPromotions;
	@FXML
	private TableColumn<String, String> colwname;
	@FXML
	private TableColumn<String, String> colwyear;
	@FXML
	private TableColumn<String, String> colindate;
	@FXML
	private TableColumn<String, String> colfindate;
	@FXML
	private TableColumn<String, String> coldiscount;
	@FXML
	private TableView<PropertyNotify> tvDataNotifications;
	@FXML
	private TableColumn<String, String> colwname2;
	@FXML
	private TableColumn<String, String> colwyear2;
	@FXML
	private TableColumn<String, String> colquantity;

	private ObservableList<PropertyPromo> tvObservableListPromotions = FXCollections.observableArrayList();
	private ObservableList<PropertyNotify> tvObservableListNotifications = FXCollections.observableArrayList();
	
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
	 * Handle exit button.
	 *
	 * @param e the event.
	 *
	**/
	@FXML
	void handleExit(final ActionEvent e)
	{
		client.cancelProposalOrder(false); 
		client.cancelProposalOrder(true); 
		Scene scene = ((Node)e.getSource()).getScene();
        Stage primaryStage = (Stage)((Node)e.getSource()).getScene().getWindow();
		HandlePages pageHandler = new HandlePages(scene, primaryStage, client);
		pageHandler.loadLogin();
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
		pageHandler.loadCustomermenu();
	}
	
	/**
	 * Handle search and buy button.
	 *
	 * @param e the event.
	 *
	**/
	@FXML
	public void handleSearchBuy(final ActionEvent e)
	{
		Scene scene = ((Node)e.getSource()).getScene();
		Stage primaryStage = (Stage) scene.getWindow();
		HandlePages pageHandler = new HandlePages(scene, primaryStage, client);
		pageHandler.loadSearchbuy();
	}
	
	/**
	 * Handle history button.
	 *
	 * @param e the event.
	 *
	**/
	@FXML
	public void handleHistory(final ActionEvent e)
	{
		Scene scene = ((Node)e.getSource()).getScene();
		Stage primaryStage = (Stage) scene.getWindow();
		HandlePages pageHandler = new HandlePages(scene, primaryStage, client);
		pageHandler.loadHistoryorders();
	}
	
	/**
	 * Initialize tables(promotions & notifications).
	 *
	 *
	**/
	void startTables()
	{
		List<Promotion> promos = client.getPromotions();
		for(Promotion p : promos)
		{
			PropertyPromo pr = new PropertyPromo(p.getInday(),p.getFinday(),
					p.getDiscount(),p.getWineName(),p.getWineYear());
			tvObservableListPromotions.add(pr);
		}
	    tvDataPromotions.setItems(tvObservableListPromotions);
	    
	    List<Notification> notify = client.getNotifications();
    	for(Notification n : notify)
    	{
    		PropertyNotify nf = new PropertyNotify(n.getQuantity(),n.getWineName(),n.getWineYear());
    		tvObservableListNotifications.add(nf);
    	}
	    tvDataNotifications.setItems(tvObservableListNotifications);
	}
	
	/** {@inheritDoc} **/
	@Override
	public void initialize(final URL location, final ResourceBundle resources)
	{
		colwname.setCellValueFactory(new PropertyValueFactory<>("wineName"));
		colwyear.setCellValueFactory(new PropertyValueFactory<>("wineYear"));
		colindate.setCellValueFactory(new PropertyValueFactory<>("inday"));
		colfindate.setCellValueFactory(new PropertyValueFactory<>("finday"));
		coldiscount.setCellValueFactory(new PropertyValueFactory<>("discount"));	    
		colwname2.setCellValueFactory(new PropertyValueFactory<>("wineName"));
		colwyear2.setCellValueFactory(new PropertyValueFactory<>("wineYear"));
		colquantity.setCellValueFactory(new PropertyValueFactory<>("quantity")); 
		
		new java.util.Timer().schedule( 
		        new java.util.TimerTask() 
		        {
		            @Override
		            public void run() 
		            {
		            	startTables();
		            }
		        },25);
	}
}

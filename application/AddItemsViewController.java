/*
* @author David W. Clendenning Jr.
*/
package application;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import InventorySystem.Item;
import InventorySystem.ItemImp;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class AddItemsViewController implements Initializable{

	@FXML private TableView<Item> itemTable;
	
	@FXML private TextField textFieldprinterModel;
	@FXML private TextField textFieldbrand;
	@FXML private TextField textFieldmodel;
	@FXML private TextField textFieldminStock;
	@FXML private TextField textFieldcurStock;
	
	@FXML private TableColumn<Item, String> printerModel;
	@FXML private TableColumn<Item, String> brand;
	@FXML private TableColumn<Item, String> model;
	@FXML private TableColumn<Item, Integer> minStock;
	@FXML private TableColumn<Item, Integer> curStock;
	
	@FXML private Button addItemButton;
	@FXML private Button returnButton;
	@FXML private Button deleteButton;
	
	@FXML private static ArrayList<Item> iList = new ArrayList<Item>();
	
	public ObservableList<Item> itemList = MainController.itemList;
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		printerModel.setCellValueFactory(new PropertyValueFactory<Item, String>("printerModel"));
		brand.setCellValueFactory(new PropertyValueFactory<Item, String>("brand"));
		model.setCellValueFactory(new PropertyValueFactory<Item, String>("model"));
		minStock.setCellValueFactory(new PropertyValueFactory<Item, Integer>("minStock"));
		curStock.setCellValueFactory(new PropertyValueFactory<Item, Integer>("currentStock"));
		itemTable.setItems(itemList);
		
	}
	
	@FXML
	public void buttonHandling() {
		addItemButton.setOnAction(e-> {
			Item selectedItem = new ItemImp(textFieldprinterModel.getText(), textFieldbrand.getText(), textFieldmodel.getText()
					, Integer.valueOf(textFieldminStock.getText()), Integer.valueOf(textFieldcurStock.getText()));
			MainController.dao.addItem(selectedItem);
			itemTable.getItems().add(selectedItem);
		});
		deleteButton.setOnAction(e-> {
			Item selectedItem = itemTable.getSelectionModel().getSelectedItem();
//			if(selectedItem instanceof Printer) {
//			    printerTable.getItems().remove(selectedItem);
//			}
			MainController.dao.deleteItem(selectedItem.getModel());
			itemTable.getItems().remove(selectedItem);
			System.out.println(selectedItem);
		});
		returnButton.setOnAction(e->{
			try {
				//Stage primaryStage = new Stage();
	    		Parent root = FXMLLoader.load(getClass().getResource("Main.fxml"));
				Scene scene = new Scene(root,1100,550);
				scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
				//primaryStage.setScene(scene);
				Stage appStage = (Stage) ((Node) e.getSource()).getScene().getWindow();	
				appStage.setScene(scene);
				appStage.show();
			}
			catch(IOException ee) {
				ee.printStackTrace();
		}});
    }
}

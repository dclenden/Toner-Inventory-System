/*
* @author David W. Clendenning Jr.
*/
package application;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;

import InventorySystem.Item;
import InventorySystem.ItemImp;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class AddItemsViewController implements Initializable{

	@FXML private TableView<Item> itemTable;
	
	@FXML private Label incorrectInputs;
	
	@FXML private TextField textFieldprinterModel;
	@FXML private TextField textFieldbrand;
	@FXML private TextField textFieldmodel;
	@FXML private TextField textFieldminStock;
	@FXML private TextField textFieldcurStock;
	@FXML private TextField textFieldOrdered;
	@FXML private TextField textFieldnumPrinters;
	
	@FXML private TextField[] textFieldIsEmpty = {textFieldbrand, textFieldcurStock, textFieldminStock, textFieldmodel
			, textFieldnumPrinters, textFieldOrdered, textFieldprinterModel};
	
	@FXML private TableColumn<Item, String> printerModel;
	@FXML private TableColumn<Item, String> brand;
	@FXML private TableColumn<Item, String> model;
	@FXML private TableColumn<Item, Integer> minStock;
	@FXML private TableColumn<Item, Integer> curStock;
	@FXML private TableColumn<Item, Integer> numPrinters;
	@FXML private TableColumn<Item, String> ordered;
	@FXML private TableColumn<Item, Integer> needed;
	
	@FXML private Button addItemButton;
	@FXML private Button returnButton;
	@FXML private Button deleteButton;
	
	@FXML private static ArrayList<Item> iList = new ArrayList<Item>();
	
	public ObservableList<Item> itemList = MainController.itemList;
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// creates a table to easily view the data from each item in the itemList array
		printerModel.setCellValueFactory(new PropertyValueFactory<Item, String>("printerModel"));
		brand.setCellValueFactory(new PropertyValueFactory<Item, String>("brand"));
		model.setCellValueFactory(new PropertyValueFactory<Item, String>("model"));
		numPrinters.setCellValueFactory(new PropertyValueFactory<Item, Integer>("quantityPrinters"));
		minStock.setCellValueFactory(new PropertyValueFactory<Item, Integer>("minStock"));
		curStock.setCellValueFactory(new PropertyValueFactory<Item, Integer>("currentStock"));
		ordered.setCellValueFactory(new PropertyValueFactory<Item, String>("onOrder"));
		//needed.setCellValueFactory(new PropertyValueFactory<Item, Integer>("needed"));
		itemTable.setItems(itemList);
		itemTable.setPlaceholder(new Label("Please enter a Item CSV to the application"));
		
	}
	
	@FXML
	public void buttonHandling() {
		//adds item to the table and dao
		addItemButton.setOnAction(e-> {
			Item selectedItem = new ItemImp(textFieldprinterModel.getText(), textFieldbrand.getText(), textFieldmodel.getText()
					, Integer.valueOf(textFieldminStock.getText()), Integer.valueOf(textFieldcurStock.getText()), textFieldOrdered.getText()
					, /*(Integer.valueOf(textFieldcurStock.getText()) < Integer.valueOf(textFieldminStock.getText())) 
					? Math.abs(Integer.valueOf(textFieldcurStock.getText()) - Integer.valueOf(textFieldminStock.getText())) 
							: 0, */Integer.valueOf(textFieldnumPrinters.getText()));
//			boolean isEmpty = false;
//			if(textFieldbrand.getText().isEmpty() == true || textFieldcurStock.getText().isEmpty() == true || textFieldminStock.getText().isEmpty() == true 
//					|| textFieldmodel.getText().isEmpty() == true || textFieldnumPrinters.getText().isEmpty() == true 
//					|| textFieldOrdered.getText().isEmpty() == true || textFieldprinterModel.getText().isEmpty() == true) {
//				incorrectInputs.setText("Invalid input: please check your data entrys");
//			}
//			for(TextField t : textFieldIsEmpty) {
//				if(t.getText().trim().isEmpty()) {
//					isEmpty = true;
//				}
//			}
//			
//			if(isEmpty) {
//				incorrectInputs.setText("Invalid input: please check your data entrys");
//			}
			
			
			//incorrectInputs.setText("");
			MainController.dao.addItem(selectedItem);
			itemTable.getItems().add(selectedItem);
			MainController.iList.add(selectedItem);
			
			try {
				MainController.dao.updateCSVs();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			
			itemTable.refresh();
		});
		//deletes item from table and dao
		deleteButton.setOnAction(e-> {
			Item selectedItem = itemTable.getSelectionModel().getSelectedItem();
			MainController.dao.deleteItem(selectedItem.getModel());
			itemTable.getItems().remove(selectedItem);
			MainController.iList.remove(selectedItem);
			System.out.println(selectedItem);
			System.out.println(Arrays.asList(MainController.dao.itemList));
			try {
				MainController.dao.updateCSVs();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		});
		//returns you to the mainView form
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

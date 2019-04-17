/*
* @author David W. Clendenning Jr.
*/
package application;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;

import InventorySystem.CSV_DBIMP;
import InventorySystem.Item;
import InventorySystem.Printer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
public class MainController implements Initializable{
	
	@FXML private Main sceneChanger;
	
	@FXML private TextField textFieldprinterModel;
	@FXML private TextField textFieldbrand;
	@FXML private TextField textFieldmodel;
	@FXML private TextField textFieldminStock;
	@FXML private TextField textFieldcurStock;
	@FXML private TextField textFieldCSVEntry;
	
	@FXML private RadioButton itemCSV;
	@FXML private RadioButton printerCSV;
	
	@FXML private Label csvEntry;
    
	@FXML private TableView<Printer> printerTable;
	@FXML private TableView<Item> itemTable;
	@FXML private TableColumn<Printer, Integer> assetTag;
	@FXML private TableColumn<Printer, String> plocation;
	@FXML private TableColumn<Printer, String> dept;
	@FXML private TableColumn<Printer, String> manufacturer;
	@FXML private TableColumn<Printer, String> description;
	@FXML private TableColumn<Printer, String> category;
	@FXML private TableColumn<Printer, String> serialNumber;
	@FXML private TableColumn<Item, String> printerModel;
	@FXML private TableColumn<Item, String> brand;
	@FXML private TableColumn<Item, String> model;
	@FXML private TableColumn<Item, Integer> minStock;
	@FXML private TableColumn<Item, Integer> curStock;
	@FXML private Button deleteButton;
	@FXML private Button addItemButton;
	@FXML private Button addItemView;
	@FXML private Button pullCSVs;
	@FXML private Button addCSVview;
	@FXML private static ArrayList<Printer> pList = new ArrayList<Printer>();
	@FXML private static ArrayList<Item> iList = new ArrayList<Item>();
	static CSV_DBIMP dao = new CSV_DBIMP();
	//static{init();}
	public static void init() {
		//dao.storePrinterCSV("printers.csv");
		dao.readPrinterCSV();
		for(Printer p: dao.printerList) {
			pList.add(p);
		}
		dao.readItemCSV();
		for(Item i: dao.itemList) {
			iList.add(i);
		}
	}
	//observable lists that contain the data from the dao that will communicate with the tableView objects
	public static ObservableList<Printer> printerList = FXCollections.observableArrayList(pList);
	public static ObservableList<Item> itemList = FXCollections.observableArrayList(iList);
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// initializes the tables of the "mainView" form
		if(!printerList.isEmpty()) {
			assetTag.setCellValueFactory(new PropertyValueFactory<Printer, Integer>("assetTag"));
			plocation.setCellValueFactory(new PropertyValueFactory<Printer, String>("location"));
			dept.setCellValueFactory(new PropertyValueFactory<Printer, String>("department"));
			manufacturer.setCellValueFactory(new PropertyValueFactory<Printer, String>("manufacturer"));
			description.setCellValueFactory(new PropertyValueFactory<Printer, String>("description"));
			category.setCellValueFactory(new PropertyValueFactory<Printer, String>("category"));
			serialNumber.setCellValueFactory(new PropertyValueFactory<Printer, String> ("serialNumber"));
	        System.out.println(Arrays.toString(dao.printerList));
			printerTable.setItems(printerList);
		}
		if(!itemList.isEmpty()) {
			printerModel.setCellValueFactory(new PropertyValueFactory<Item, String>("printerModel"));
			brand.setCellValueFactory(new PropertyValueFactory<Item, String>("brand"));
			model.setCellValueFactory(new PropertyValueFactory<Item, String>("model"));
			minStock.setCellValueFactory(new PropertyValueFactory<Item, Integer>("minStock"));
			curStock.setCellValueFactory(new PropertyValueFactory<Item, Integer>("currentStock"));
			itemTable.setItems(itemList);
		}
		
		
	}
	//TODO: Add function to update CSV Files
	@FXML
	public void buttonHandling() throws Exception, FileNotFoundException {
//indecisive function, keeping in case we would like to go a different route with the mainView form		
//		deleteButton.setOnAction(e-> {
//			Item selectedItem = itemTable.getSelectionModel().getSelectedItem();
//			dao.deleteItem(selectedItem.getModel());
//			itemTable.getItems().remove(selectedItem);
//			System.out.println(selectedItem);
//		});
		//This will take you to the "EditItem" form in which you can add / delete various items
		addItemView.setOnAction(e-> {
			try {
				//Stage primaryStage = new Stage();
	    		Parent root = FXMLLoader.load(getClass().getResource("AddItem.fxml"));
				Scene scene = new Scene(root,500,350);
				scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
				//primaryStage.setScene(scene);
				Stage appStage = (Stage) ((Node) e.getSource()).getScene().getWindow();	
				appStage.setScene(scene);
				appStage.show();
			}
			catch(IOException ee) {
				ee.printStackTrace();
			}
		});
		//a single button on the main form to pull the various data from the CSV's indicated
		pullCSVs.setOnAction(e->{
			init();
			printerList = FXCollections.observableArrayList(pList);
			itemList = FXCollections.observableArrayList(iList);
//			itemTable.getColumns().get(0).setVisible(false);
//			itemTable.getColumns().get(0).setVisible(true);
//			printerTable.getColumns().get(0).setVisible(false);
//			printerTable.getColumns().get(0).setVisible(true);
			 
		});
		//view to open the form in which you can add CSV for more info please see the CSVEntryController.java class
		addCSVview.setOnAction(e-> {
			try {
				Stage primaryStage = new Stage();
				Parent root = FXMLLoader.load(getClass().getResource("CSVEntry.fxml"));
				Scene scene = new Scene(root,600,100);
				scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
				primaryStage.setScene(scene);
				primaryStage.show();
			} catch(Exception ee) {
				ee.printStackTrace();
			}
		});
        
	}
}

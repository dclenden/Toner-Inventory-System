/*
* @author David W. Clendenning Jr.
*/
package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
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
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
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
	@FXML private Button addPrinterCSV;
	@FXML private Button addItemCSV;
	@FXML private Button refreshButton;
	@FXML private Button updateCSVButton;
	@FXML private Button orderItemList;
	@FXML public static ArrayList<Item> iList = new ArrayList<Item>();
	static CSV_DBIMP dao = new CSV_DBIMP();
	@FXML public static ArrayList<Printer> pList = new ArrayList<Printer>();
	
	public static void init() {
		
		if(dao.printerList.length != 0) {
			pList.clear();
		}
		
		if(dao.itemList.length != 0) {
			iList.clear();
		}
		
		dao.readPrinterCSV();
		dao.readItemCSV();
		
		if(pList.isEmpty()) { //BUG: duplicates entry's when clicking the pull button in mainView [FIXED: (4/22/2019)]
			if(!dao.printerFilePath.trim().isEmpty()) {
				for(Printer p: dao.printerList) {
					if(p != null) {
					    pList.add(p);
					}
				}
			}
		}
		
		if(iList.isEmpty()) {
			if(!dao.itemFilePath.trim().isEmpty()) {
				for(Item i: dao.itemList) {
					if(i != null) {
					    iList.add(i);
					}
				}
			}
		}
	}
	//observable lists that contain the data from the dao that will communicate with the tableView objects
	public static ObservableList<Printer> printerList = FXCollections.observableArrayList(pList);
	public static ObservableList<Item> itemList = FXCollections.observableArrayList(iList);
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// initializes the tables of the "mainView" form
		
			assetTag.setCellValueFactory(new PropertyValueFactory<Printer, Integer>("assetTag"));
			plocation.setCellValueFactory(new PropertyValueFactory<Printer, String>("location"));
			dept.setCellValueFactory(new PropertyValueFactory<Printer, String>("department"));
			manufacturer.setCellValueFactory(new PropertyValueFactory<Printer, String>("manufacturer"));
			description.setCellValueFactory(new PropertyValueFactory<Printer, String>("description"));
			category.setCellValueFactory(new PropertyValueFactory<Printer, String>("category"));
			serialNumber.setCellValueFactory(new PropertyValueFactory<Printer, String> ("serialNumber"));
	        System.out.println(Arrays.toString(dao.printerList));
			printerTable.setItems(printerList);
			printerTable.setPlaceholder(new Label("Please enter a Printer CSV to the application"));

			printerModel.setCellValueFactory(new PropertyValueFactory<Item, String>("printerModel"));
			brand.setCellValueFactory(new PropertyValueFactory<Item, String>("brand"));
			model.setCellValueFactory(new PropertyValueFactory<Item, String>("model"));
			minStock.setCellValueFactory(new PropertyValueFactory<Item, Integer>("minStock"));
			curStock.setCellValueFactory(new PropertyValueFactory<Item, Integer>("currentStock"));
			itemTable.setItems(itemList);
			itemTable.setPlaceholder(new Label("Please enter a Item CSV to the application"));
		
		
	}
	
	@FXML
	public void buttonHandling() throws Exception, FileNotFoundException {
//indecisive function, keeping in case we would like to go a different route with the mainView form		
		//This will take you to the "EditItem" form in which you can add / delete various items
		addItemView.setOnAction(e-> {
			try {
				//Stage primaryStage = new Stage();
	    		Parent root = FXMLLoader.load(getClass().getResource("AddItem.fxml"));
				Scene scene = new Scene(root,700,349);
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
			//removeAll();
			if(dao.itemFilePath.trim().isEmpty()) {
				itemTable.getItems().clear();
			}
			if(dao.printerFilePath.trim().isEmpty()) {
				printerTable.getItems().clear();
			}
			init();

			//itemTable.setItems(null);
			printerList = FXCollections.observableArrayList(pList);
			itemList = FXCollections.observableArrayList(iList);
			printerTable.setItems(printerList);
			itemTable.setItems(itemList);
			 
		});
		//view to open the form in which you can add CSV for more info please see the CSVEntryController.java class
		addCSVview.setOnAction(e-> {
			try {
				Stage primaryStage = new Stage();
				Parent root = FXMLLoader.load(getClass().getResource("CSVEntry.fxml"));
				Scene scene = new Scene(root,600,100);
				scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
				primaryStage.setTitle("DTCC Toner Inventory System");
				primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("/images/teamCoffeeHouse.png")));
				primaryStage.setScene(scene);
				primaryStage.show();
			} catch(Exception ee) {
				ee.printStackTrace();
			}
		});
		addPrinterCSV.setOnAction(e->{
			FileChooser fc = new FileChooser();
			File selectedFile = fc.showOpenDialog(null);
			if(selectedFile != null) {
				dao.storePrinterCSV(selectedFile.getAbsolutePath());
			}
			else {
				try {
					throw new FileNotFoundException("File not found");
				} catch (FileNotFoundException ee) {
					ee.printStackTrace();
				}
			}
		});
		addItemCSV.setOnAction(e->{
			FileChooser fc = new FileChooser();
			File selectedFile = fc.showOpenDialog(null);
			if(selectedFile != null) {
				dao.storeItemCSV(selectedFile.getAbsolutePath());
			}
			else {
				try {
					throw new FileNotFoundException("File not found");
				} catch (FileNotFoundException ee) {
					ee.printStackTrace();
				}
			}
		});
        
		orderItemList.setOnAction(e->{
			orderViewController.itemsList = FXCollections.observableArrayList(dao.orderList());
			try {
				Stage primaryStage = new Stage();
				Parent root = FXMLLoader.load(getClass().getResource("OrderList.fxml"));
				Scene scene = new Scene(root,700,500);
				scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
				primaryStage.setTitle("DTCC Toner Inventory System");
				primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("/images/teamCoffeeHouse.png")));
				primaryStage.setScene(scene);
				primaryStage.show();
			} catch(Exception ee) {
				ee.printStackTrace();
			}
		});
	}
}

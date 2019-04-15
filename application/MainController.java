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
		//dao.storeItemCSV("Wilmington Toner Database.csv");
		dao.readItemCSV();
		for(Item i: dao.itemList) {
			iList.add(i);
		}
	}
	public static ObservableList<Printer> printerList = FXCollections.observableArrayList(pList);
	public static ObservableList<Item> itemList = FXCollections.observableArrayList(iList);
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
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
	
	@FXML
	public void buttonHandling() throws Exception, FileNotFoundException {
		deleteButton.setOnAction(e-> {
			Item selectedItem = itemTable.getSelectionModel().getSelectedItem();
//			if(selectedItem instanceof Printer) {
//			    printerTable.getItems().remove(selectedItem);
//			}
			dao.deleteItem(selectedItem.getModel());
			itemTable.getItems().remove(selectedItem);
			System.out.println(selectedItem);
		});
//		addItemButton.setOnAction(e-> {
//			Item selectedItem = new ItemImp(textFieldprinterModel.getText(), textFieldbrand.getText(), textFieldmodel.getText()
//					, Integer.valueOf(textFieldminStock.getText()), Integer.valueOf(textFieldcurStock.getText()));
//			dao.addItem(selectedItem);
//			itemTable.getItems().add(selectedItem);
//		});
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
		pullCSVs.setOnAction(e->{
			init();
			printerList = FXCollections.observableArrayList(pList);
			itemList = FXCollections.observableArrayList(iList);
			itemTable.getColumns().get(0).setVisible(false);
			itemTable.getColumns().get(0).setVisible(true);
			printerTable.getColumns().get(0).setVisible(false);
			printerTable.getColumns().get(0).setVisible(true);
			 
		});
//		addPrinterView.setOnAction(e-> {
//			try {
//				//Stage primaryStage = new Stage();
//	    		Parent root = FXMLLoader.load(getClass().getResource("AddPrinter.fxml"));
//				Scene scene = new Scene(root,500,350);
//				scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
//				//primaryStage.setScene(scene);
//				Stage appStage = (Stage) ((Node) e.getSource()).getScene().getWindow();	
//				appStage.setScene(scene);
//				appStage.show();
//			}
//			catch(IOException ee) {
//				ee.printStackTrace();
//			}
//		});
		
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
//	@FXML
//    private Label lblStatus;
//    @FXML	
//    private TextField txtUserName;
//    @FXML
//    private TextField txtPassword;
//    
//    public void Login(ActionEvent event) throws Exception {
//    	if(txtUserName.getText().equals("user") && txtPassword.getText().equals("password")) {
//    		lblStatus.setText("Login Success");
//    		Stage primaryStage = new Stage();
//    		Parent root = FXMLLoader.load(getClass().getResource("Main.fxml"));
//			Scene scene = new Scene(root,400,400);
//			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
//			primaryStage.setScene(scene);
//			primaryStage.show();
//    	}
//    	else {
//    		lblStatus.setText("Login Failed");
//    	}
//    }
}

package application.mayPossiblyUse;
///*
//* @author David W. Clendenning Jr.
//*/
//package application;
//	/*
//	* @author David W. Clendenning Jr.
//	*/
//                                                  KEEPING IN CASE PROJECT GOES A DIFFERENT ROUTE FOR PRINTER ADDITION / DELETION
//	import java.io.IOException; TODO: WIP
//	import java.net.URL;
//	import java.util.ArrayList;
//	import java.util.Arrays;
//	import java.util.ResourceBundle;
//
//	import InventorySystem.CSV_DBIMP;
//	import InventorySystem.Item;
//	import InventorySystem.ItemImp;
//	import InventorySystem.Printer;
//	import javafx.collections.FXCollections;
//	import javafx.collections.ObservableList;
//	import javafx.event.ActionEvent;
//	import javafx.fxml.FXML;
//	import javafx.fxml.FXMLLoader;
//	import javafx.fxml.Initializable;
//	import javafx.scene.Node;
//	import javafx.scene.Parent;
//	import javafx.scene.Scene;
//	import javafx.scene.control.Button;
//	import javafx.scene.control.TableColumn;
//	import javafx.scene.control.TableView;
//	import javafx.scene.control.TextField;
//	import javafx.scene.control.cell.PropertyValueFactory;
//	import javafx.stage.Stage;
//	public class AddPrintersViewController implements Initializable {
//		
//		@FXML private Main sceneChanger;
//		
//		@FXML private TextField textFieldassetTag;
//		@FXML private TextField textFieldlocation;
//		@FXML private TextField textFielddepartment;
//		@FXML private TextField textFieldmanufacturer;
//		@FXML private TextField textFielddescription;
//		@FXML private TextField textFieldcategory;
//		@FXML private TextField textFieldserialNumber;
//		
//	    
//		@FXML private TableView<Printer> printerTable;
//		@FXML private TableColumn<Printer, Integer> assetTag;
//		@FXML private TableColumn<Printer, String> plocation;
//		@FXML private TableColumn<Printer, String> dept;
//		@FXML private TableColumn<Printer, String> manufacturer;
//		@FXML private TableColumn<Printer, String> description;
//		@FXML private TableColumn<Printer, String> category;
//		@FXML private TableColumn<Printer, String> serialNumber;
//		@FXML private Button deleteButton;
//		@FXML private Button addPrinterButton;
//		public static ObservableList<Printer> printerList = MainController.printerList;
//		@Override
//		public void initialize(URL location, ResourceBundle resources) {
//			// TODO Auto-generated method stub
//			if(!printerList.isEmpty()) {
//				assetTag.setCellValueFactory(new PropertyValueFactory<Printer, Integer>("assetTag"));
//				plocation.setCellValueFactory(new PropertyValueFactory<Printer, String>("location"));
//				dept.setCellValueFactory(new PropertyValueFactory<Printer, String>("department"));
//				manufacturer.setCellValueFactory(new PropertyValueFactory<Printer, String>("manufacturer"));
//				description.setCellValueFactory(new PropertyValueFactory<Printer, String>("description"));
//				category.setCellValueFactory(new PropertyValueFactory<Printer, String>("category"));
//				serialNumber.setCellValueFactory(new PropertyValueFactory<Printer, String> ("serialNumber"));
//				printerTable.setItems(printerList);
//			}
//			
//			
//		}
//		
//		@FXML
//		public void buttonHandling() {
//			addPrinterButton.setOnAction(e-> {
//				Printer selectedItem = new PrinterImp(Integer.valueOf(textFieldassetTag.getText()), textFieldlocation.getText()
//						, textFielddepartment.getText(), textFieldmanufacturer.getText(), textFielddescription.getText()
//						, textFieldcategory.getText(), textFieldserialNumber.getText());
//				MainController.dao.a;
//				itemTable.getItems().add(selectedItem);
//			});
//		}
//}

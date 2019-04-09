/*
* @author David W. Clendenning Jr.
*/
package application;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;

import InventorySystem.CSV_DBIMP;
import InventorySystem.Printer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
public class MainController implements Initializable{
    
	@FXML private TableView<Printer> printerTable;
	@FXML private TableColumn<Printer, Integer> assetTag;
	@FXML private TableColumn<Printer, String> plocation;
	@FXML private TableColumn<Printer, String> dept;
	@FXML private TableColumn<Printer, String> manufacturer;
	@FXML private TableColumn<Printer, String> description;
	@FXML private TableColumn<Printer, String> category;
	@FXML private TableColumn<Printer, String> serialNumber;
	@FXML private Button deleteButton;
	@FXML private static ArrayList<Printer> pList = new ArrayList<Printer>();
	static CSV_DBIMP dao = new CSV_DBIMP();
	static{init();}
	public static void init() {
		dao.storePrinterCSV("printers.csv");
		dao.readPrinterCSV();
		for(Printer p: dao.printerList) {
			pList.add(p);
		}
	}
	public ObservableList<Printer> list = FXCollections.observableArrayList(pList);
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
		assetTag.setCellValueFactory(new PropertyValueFactory<Printer, Integer>("assetTag"));
		plocation.setCellValueFactory(new PropertyValueFactory<Printer, String>("location"));
		dept.setCellValueFactory(new PropertyValueFactory<Printer, String>("department"));
		manufacturer.setCellValueFactory(new PropertyValueFactory<Printer, String>("manufacturer"));
		description.setCellValueFactory(new PropertyValueFactory<Printer, String>("description"));
		category.setCellValueFactory(new PropertyValueFactory<Printer, String>("category"));
		serialNumber.setCellValueFactory(new PropertyValueFactory<Printer, String> ("serialNumber"));
        System.out.println(Arrays.toString(dao.printerList));
		printerTable.setItems(list);
	}
	
	@FXML
	public void buttonHandling() {
		deleteButton.setOnAction(e-> {
			Printer selectedItem = printerTable.getSelectionModel().getSelectedItem();
			printerTable.getItems().remove(selectedItem);
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

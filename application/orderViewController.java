/*
* @author David W. Clendenning Jr.
*/
package application;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ResourceBundle;

import com.sun.prism.paint.Color;

import InventorySystem.Item;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

public class orderViewController implements Initializable{
	@FXML private TableView<Item> itemTable;
	
	@FXML private TableColumn<Item, String> printerModel;
	@FXML private TableColumn<Item, String> brand;
	@FXML private TableColumn<Item, String> model;
	@FXML private TableColumn<Item, Integer> minStock;
	@FXML private TableColumn<Item, Integer> curStock;
	@FXML private TableColumn<Item, Integer> numPrinters;
	@FXML private TableColumn<Item, String> ordered;
	@FXML private TableColumn<Item, Integer> needed;
	
	@FXML private TextField fileName;
	
	@FXML private Label filePathConfirmation;
	
	@FXML private Button returnButton;
	@FXML private Button exportButton;
	
	public static ArrayList<Item> orderList = new ArrayList<>(MainController.dao.orderList());
	public static ObservableList<Item> itemsList = FXCollections.observableArrayList(orderList);
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// initializes the tables of the "mainView" form
            // initialized by FX ID, then the string is the attribute from the object created from its respective class
			printerModel.setCellValueFactory(new PropertyValueFactory<Item, String>("printerModel"));
			brand.setCellValueFactory(new PropertyValueFactory<Item, String>("brand"));
			model.setCellValueFactory(new PropertyValueFactory<Item, String>("model"));
			numPrinters.setCellValueFactory(new PropertyValueFactory<Item, Integer>("quantityPrinters"));
			minStock.setCellValueFactory(new PropertyValueFactory<Item, Integer>("minStock"));
			curStock.setCellValueFactory(new PropertyValueFactory<Item, Integer>("currentStock"));
			ordered.setCellValueFactory(new PropertyValueFactory<Item, String>("onOrder"));
			needed.setCellValueFactory(new PropertyValueFactory<Item, Integer>("needed"));
			itemTable.setItems(itemsList);
			itemTable.setPlaceholder(new Label("Please enter a Item CSV to the application"));
			
	}
	
	public void buttonHandling() {
		returnButton.setOnAction(e->{
			Stage stage = (Stage) returnButton.getScene().getWindow();
			stage.close();
	    });
		
	    exportButton.setOnAction(e->{
	       //saves to C:/users/user 
	       String userHomeFolder = System.getProperty("user.home");
	       System.out.println(userHomeFolder);
	       File f = new File(userHomeFolder, "orderList.csv");
	  	   if(f.exists()) {
	  		   f.delete();
	  	   }
	  		   try {
	  			   f.createNewFile();
	  		   }
	  		   catch(IOException ee) {
	  			   ee.printStackTrace();
	  		   }
	  		try (FileWriter writer = new FileWriter(f);
	  		             BufferedWriter bw = new BufferedWriter(writer)) {
	          	StringBuilder sb = new StringBuilder();
	  	        Iterator<Item> it = MainController.iList.iterator();
	  	        //System.out.println(it);
	  	        //appends header to csv file
	  	        sb.append("PrinterModel" + "," + "Brand" + "," + "Model" + "," + "Printers" + "," 
	  	        + "MinStock" + "," + "CurStock" + "," + "Order" + "," + "Needed" +"\n");
	  	        while(it.hasNext()) {
	  	        	
	  	            Item i = (Item)it.next();
	  	            if(i != null) {
	  		            sb.append(i.getPrinterModel().toString());
	  		            sb.append(",");
	  		            sb.append(i.getBrand().toString());
	  		            sb.append(",");
	  		            sb.append(i.getModel().toString());
	  		            sb.append(",");
	  		            sb.append(String.valueOf(i.getQuantityPrinters()));
	  		            sb.append(",");
	  		            sb.append(String.valueOf(i.getMinStock()));
	  		            sb.append(",");
	  		            sb.append(String.valueOf(i.getCurrentStock()));
	  		            sb.append(",");
	  		            sb.append(i.getOnOrder());
	  		            sb.append(",");
	  		            sb.append(String.valueOf(i.getDeficit()));
	  		            sb.append("\n");
	  	            }
	  	        }
	  	        bw.write(sb.toString());
	  	        filePathConfirmation.setText("Contents written to " + userHomeFolder + " successfully");
	  	    } catch (Exception ex) {
	  	    	filePathConfirmation.setText("Error: contents could not be written to file");
	  	        ex.printStackTrace();
	  	      }
	  	   });
	
    }
}

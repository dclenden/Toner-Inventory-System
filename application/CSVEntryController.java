package application;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

/*
* @author David W. Clendenning Jr.
*/

public class CSVEntryController {
	
	@FXML private TextField textFieldCSVEntry;
	
	@FXML private RadioButton itemCSV;
	@FXML private RadioButton printerCSV;
	
	@FXML private Label csvEntry;
	
	@FXML private Button addCSV;
	
	public void buttonHandling() {
		addCSV.setOnAction(e->{
			if(itemCSV.isSelected() && printerCSV.isSelected()) {
				//do nothing
			}
			else if(itemCSV.isSelected() && !printerCSV.isSelected()) {
				String filePath = textFieldCSVEntry.getText();
				System.out.println(filePath);
				MainController.dao.storeItemCSV(filePath);
				System.out.println("item select, printer not");
				//MainController.dao.readItemCSV();
			}
			else if(!itemCSV.isSelected() && printerCSV.isSelected()) {
				String filePath = textFieldCSVEntry.getText();
				System.out.println(filePath);
				MainController.dao.storePrinterCSV(filePath);
				//MainController.dao.readPrinterCSV();
				System.out.println("printer select, item not");
			}
			else {
				
			}
		});
	}
}

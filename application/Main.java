package application;
	
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;


public class Main extends Application {
	private Stage primaryStage;
	@Override
	public void start(Stage primaryStage) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("Main.fxml"));
			Scene scene = new Scene(root,1100,550);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
//	public void itemView() {
//		try {
//			//Stage primaryStage = new Stage();
//    		Parent root = FXMLLoader.load(getClass().getResource("AddPrinter.fxml"));
//			Scene scene = new Scene(root,500,350);
//			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
//			//primaryStage.setScene(scene);
//			Stage appStage = (Stage) ((Node) e.getSource()).getScene().getWindow();	
//			appStage.setScene(scene);
//			appStage.show();
//		}
//		catch(IOException ee) {
//			ee.printStackTrace();
//		}
//	}
	
	public static void main(String[] args) {
		launch(args);
	}
}

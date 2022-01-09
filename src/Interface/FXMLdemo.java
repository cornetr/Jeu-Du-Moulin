package Interface;

import java.io.IOException;
import java.net.URL;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class FXMLdemo extends Application {
	
	public String path="Menu.fxml";

	
	public Parent loadFXML(String path) throws IOException {
		FXMLLoader loader = new FXMLLoader();
		URL fxmlFileUrl = getClass().getResource(path);
		if (fxmlFileUrl == null) {
			System.out.println("Impossible de charger le fichier fxml");
			System.exit(-1);
			
		}else this.path=path;
		loader.setLocation(fxmlFileUrl);
		Parent root = loader.load();
		return root;
	}
	

	public void setPath(String path) {
		this.path = path;
	}

	public void start(Stage stage) throws IOException {
		

		Scene scene = new Scene(loadFXML(path));
		stage.setScene(scene);
		stage.getIcons().add(new Image("Interface/favicon.png"));
		stage.setTitle("Line Up 3");
		stage.setResizable(false);
		stage.show();
//		System.out.println("java version: "+System.getProperty("java.version"));
//		System.out.println("javafx.version: " + System.getProperty("javafx.version"));
	}




	public static void main(String[] args) {
		Application.launch(args);
	}

}

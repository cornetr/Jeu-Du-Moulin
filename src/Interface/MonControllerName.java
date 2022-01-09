package Interface;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class MonControllerName {
	
        public static boolean wasSelectedJVSIA=false;
        public static boolean wasSelectedJVSJ=false;
        
        public static boolean wasSelectedCarre=false;
        public static boolean wasSelectedTriangle=false;
        
        @FXML
        TextField nameJ1;
        @FXML
        TextField nameJ2;
        @FXML
        Label labelAdvert;
        @FXML
        Button playButton;

        public void initialize() {
                System.out.println("Initialisation...");
                if(MonControllerName.wasSelectedJVSIA) {
                	nameJ2.setText("BOT");
                	nameJ2.setDisable(true);
                }
                
        }
		
		public void playPressed(ActionEvent event) throws IOException {
		//System.out.println("jvsia: "+MonControllerName.wasSelectedJVSIA+"   jvj: "+MonControllerName.wasSelectedJVJ);
				
		if (wasSelectedJVSIA && nameJ1.getText().trim().isEmpty()) {
			labelAdvert.setText("Veuillez entrer votre nom");
		}else if (wasSelectedJVSJ && (nameJ1.getText().trim().isEmpty() || nameJ2.getText().trim().isEmpty())){
			labelAdvert.setText("Veuillez entrer votre nom");
		}else if(wasSelectedCarre){
			Stage stage = (Stage) playButton.getScene().getWindow();
			stage.close();
			
			MonControllerJeuCarre.nomJ1=nameJ1.getText();
			MonControllerJeuCarre.nomJ2=nameJ2.getText();
			
			FXMLdemo name =new FXMLdemo();
			name.setPath("JeuCarre.fxml");
			name.start(stage);
		}else if(wasSelectedTriangle) {
			Stage stage = (Stage) playButton.getScene().getWindow();
			stage.close();
			
			MonControllerJeuTriangle.nomJ1=nameJ1.getText();
			MonControllerJeuTriangle.nomJ2=nameJ2.getText();
			
			FXMLdemo name =new FXMLdemo();
			name.setPath("JeuTriangle.fxml");
			name.start(stage);
		}
			
			
		}
		
		
		
		
}

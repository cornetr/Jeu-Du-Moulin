package Interface;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class MonControllerMenu {
        @FXML
        CheckBox checkBoxJVSIA;
        @FXML
        CheckBox checkBoxJVSJ;
        @FXML
        CheckBox checkBoxCarre;
        @FXML
        CheckBox checkBoxTriangle;
        @FXML
        Button commencer;
        @FXML
        Label labelAdvertMenu;

        public void initialize() {
                System.out.println("Initialisation...");
        }
		
		public void checkJVSIA(ActionEvent event) {
				MonControllerName.wasSelectedJVSIA=true;

				checkBoxJVSJ.setSelected(false);
				MonControllerName.wasSelectedJVSJ=false;
			
		}
		
		public void checkJVSJ(ActionEvent event) {
				MonControllerName.wasSelectedJVSJ=true;

				checkBoxJVSIA.setSelected(false);
				MonControllerName.wasSelectedJVSIA=false;
			
		}
		
		public void checkCarre(ActionEvent event) {
				checkBoxTriangle.setSelected(false);
		}
		
		public void checkTriangle(ActionEvent event) {
				checkBoxCarre.setSelected(false);
		}
		
		public void commencerPressed(ActionEvent event) throws IOException {
//			if((checkBoxCarre.isSelected() || checkBoxTriangle.isSelected()) 
//					&& (checkBoxJVSIA.isSelected() || checkBoxJVSJ.isSelected())) {
//				Stage stage = (Stage) commencer.getScene().getWindow();
//				stage.close();
//				
//				FXMLdemo name =new FXMLdemo();
//				name.setPath("Name.fxml");
//				name.start(stage);
//			}
			if(checkBoxJVSJ.isSelected() &&checkBoxCarre.isSelected()) {
				Stage stage = (Stage) commencer.getScene().getWindow();
				stage.close();
				
				MonControllerName.wasSelectedCarre=true;
				FXMLdemo name =new FXMLdemo();
				name.setPath("Name.fxml");
				name.start(stage);
			}else if(checkBoxJVSJ.isSelected() &&checkBoxTriangle.isSelected()) {
				Stage stage = (Stage) commencer.getScene().getWindow();
				stage.close();
				
				MonControllerName.wasSelectedTriangle=true;
				FXMLdemo name =new FXMLdemo();
				name.setPath("Name.fxml");
				name.start(stage);
			}else labelAdvertMenu.setText("Veuillez faire votre choix");
			
			
		}
		

		
		
}

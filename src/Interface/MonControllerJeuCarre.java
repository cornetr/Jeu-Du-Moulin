package Interface;

import java.util.Optional;
import java.util.Random;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.Pane;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

public class MonControllerJeuCarre {
		@FXML
        Pane plateau;
		@FXML
        Label labelJ1Restants;
        @FXML
        Label labelJ2Restants;
        @FXML
        Label lbNbTours;
        @FXML
        Label joueurCourant;
        @FXML
        Button boutonPiege;
        @FXML
        Button boutonBloquer;
        @FXML
        Button boutonQuitter;
        @FXML 
        Label advertJeu;
        @FXML
        Button boutonSauvegarder;
        @FXML
        Circle circle11,circle12,circle13,circle14,circle15,circle16,circle17,circle18;
        @FXML
        Circle circle21,circle22,circle23,circle24,circle25,circle26,circle27,circle28;
        @FXML
        Circle circle31,circle32,circle33,circle34,circle35,circle36,circle37,circle38;
        @FXML
        Line line31_32,line32_33,line33_34,line34_35,line35_36,line36_37,line37_38,line38_31;
        @FXML
        Line line21_22,line22_23,line23_24,line24_25,line25_26,line26_27,line27_28,line28_21,line22_32,line24_34,line26_36,line28_38;
        @FXML
        Line line11_12,line12_13,line13_14,line14_15,line15_16,line16_17,line17_18,line18_11,line12_22,line14_24,line16_26,line18_28;
        
        
        public static Circle[][] sommets;
        public static Line[] lignes;
        
        public boolean piege =false;
        public int nbPieges=0;
        
        public boolean eliminer=false;
        
        public boolean blocage =false;
        public int nbBlocages=0;
        
    	private int nbPionsAPoser = 6;
    	
    	private int nbPionsJ1=0;
    	private int nbPionsJ2=0;
    	private int nbTours=0;
    	
    	public static String nomJ1;
    	public static String nomJ2;
    	

        public void initialize() {
                System.out.println("Initialisation...");
                
               sommets =new Circle[][]
                    {
                            {circle11,circle12,circle13,circle14,circle15,circle16,circle17,circle18}, 
                            {circle21,circle22,circle23,circle24,circle25,circle26,circle27,circle28},
                            {circle31,circle32,circle33,circle34,circle35,circle36,circle37,circle38},
                        };
                        
               lignes=new Line[] 
            		{  line31_32,line32_33,line33_34,line34_35,line35_36,line36_37,line37_38,line38_31,
            		   line22_32,line24_34,line26_36,line28_38,
            		   line21_22,line22_23,line23_24,line24_25,line25_26,line26_27,line27_28,line28_21,
            		   line12_22,line14_24,line16_26,line18_28,
            		   line11_12,line12_13,line13_14,line14_15,line15_16,line16_17,line17_18,line18_11
            		   };
             
	           for (int i = 0; i <3; i++) {
	            	for(int j = 0; j <8; j++) {
	        			int position = i;
	        			int position2 =j;
	        			sommets[i][j].setCursor(Cursor.HAND);
	        			
	        			sommets[i][j].setOnMouseClicked(e -> {
	        				if(eliminer) {
	        					if( joueurCourant.getText().equals("J2") && verifColor(position, position2, Color.BLACK)) {
	        						nbPionsJ1--;
	        						labelJ1Restants.setText(""+nbPionsJ1);
	        						eliminer =false;
	        						sommets[position][position2].setFill(Color.valueOf("0x1e90ffff"));
	        					}else if(joueurCourant.getText().equals("J1") && verifColor(position, position2, Color.WHITE)) {
	        						nbPionsJ2--;
	        						labelJ2Restants.setText(""+nbPionsJ2);
	        						eliminer =false;
	        						sommets[position][position2].setFill(Color.valueOf("0x1e90ffff"));
	        					}
	        					if(!eliminer) {
	        						swapJoueurs();
	        					}
	        					testFin();
	        					
	        					
	        				}else if(this.piege && this.nbPieges==0 && estVide(position, position2)) {
	        						
	        					 this.nbPieges++;
	        					 sommets[position][position2].setFill(Color.RED);
	        					 this.piege=false;
	        					 advertJeu.setText("");
	        					 
	        					 swapJoueurs();
	        					 
	        				}else if(nbPionsAPoser>0 && joueurCourant.getText().equals("J1")&& estVide(position, position2)) {
	        					sommets[position][position2].setFill(Color.BLACK);
	        					advertJeu.setText(nomJ2+" pose un pion !");
	        					joueurCourant.setText("J2");
	        					this.nbPionsJ1++;
	        					this.nbPionsAPoser--;
	        					labelJ1Restants.setText(""+nbPionsJ1);
	        					if(nbPionsAPoser==0) {
	        						advertJeu.setText("Que le meilleur gagne !");
	        					}
	        					
	        				}else if(nbPionsAPoser>0 && joueurCourant.getText().equals("J2") && estVide(position, position2)) {
	        					sommets[position][position2].setFill(Color.WHITE);
	        					advertJeu.setText(nomJ1+" pose un pion !");
	        					joueurCourant.setText("J1");
	        					this.nbPionsAPoser--;
	        					this.nbPionsJ2++;
	        					labelJ2Restants.setText(""+nbPionsJ2);
	        					if(nbPionsAPoser==0) {
	        						advertJeu.setText("Que le meilleur gagne !");
	        					}
	        				}
	        				
	        			});
	        			
	        			sommets[i][j].setOnDragDetected(e -> {
	        				//
	        				if(!this.piege && !this.blocage && !this.eliminer) {
	        			        if((joueurCourant.getText().equals("J1") &&  verifColor(position, position2, Color.BLACK))
	        			        		||(joueurCourant.getText().equals("J2") &&  verifColor(position, position2, Color.WHITE))) {
	        			        	Dragboard db = sommets[position][position2].startDragAndDrop(TransferMode.MOVE);
		        			        ClipboardContent content = new ClipboardContent();
		        			        content.putString(sommets[position][position2].getFill().toString());
		        			        db.setContent(content);
		        			        e.consume();
		        			        
	        			        }
	        				}  
	        				
	        			});
	        			
	        			sommets[i][j].setOnDragOver(e -> {
	        				if(areNeighbors(sommets[position][position2],(Circle) e.getGestureSource())) {
	        					if (!(e.getGestureSource().equals(sommets[position][position2])) && (estVide(position, position2) || verifColor(position, position2, Color.RED))) {
	        						e.acceptTransferModes(TransferMode.MOVE);
	        						e.consume();
	        					}
	        				}
	        			});
	        				        			
	        			sommets[i][j].setOnDragDropped(e -> {
	        				boolean wasPiege=false;
	        				Circle c=(Circle)e.getGestureSource();
	        				if(verifColor(position, position2, Color.RED)){
	        					wasPiege=true;
	        					Random r=new Random();
	        					int aleaX=r.nextInt(3);
	        					int aleaY=r.nextInt(8);
	        					while(!estVide(aleaX, aleaY)) {
	        						aleaX=r.nextInt(3);
		        					aleaY=r.nextInt(8);
	        					}
	        					
	        					sommets[aleaX][aleaY].setFill(c.getFill());
	        					sommets[position][position2].setFill(Color.valueOf("0x1e90ffff"));
	        					nbPieges--;
	        				}
	        				
	        				Dragboard db = e.getDragboard();
	        				Circle c2=(Circle) e.getGestureSource();
	        				Color was=(Color) c2.getFill();
	        				c2.setFill(Color.valueOf("0x1e90ffff"));
	        				if (db.hasString()) {			
	        					e.setDropCompleted(true);
	        					e.consume();
	        				} else {
	        					e.setDropCompleted(false);
	        					e.consume();
	        				}
	        				if(!wasPiege) {
	        					sommets[position][position2].setFill(Color.valueOf(db.getString()));
	        				}
	        				
	        				nbTours++;
	        				lbNbTours.setText(""+nbTours);
	        				advertJeu.setText("");
	        				piege=false;
	        				testAlign(was);
        					if(!eliminer) {
        						swapJoueurs();
        					}
        					
	        			});
	        			
	            	}
	    		}
	           
	           for (Line line : lignes) {
	        	   line.setCursor(Cursor.DEFAULT);
	        	   line.setOnMouseClicked(e -> {
       				if(this.blocage && this.nbBlocages==0) {
       					
       					this.nbBlocages++;
       					line.setStyle("-fx-stroke: red");
       					this.blocage=false;
       					advertJeu.setText("");
       					
       					swapJoueurs();
       				}
       				
       			});
	           }
	           advertJeu.setText(nomJ1+" pose un pion !");
        	}         

        
        public void quitter(ActionEvent event	) {
        	Alert alert=new Alert(AlertType.CONFIRMATION);
        	alert.setHeaderText("Êtes-vous sûr de vouloir quitter le jeu ?\n\nToutes les données non sauvegardées seront perdues.");
        	Optional<ButtonType> option=alert.showAndWait();
        	
        	if(option.get()==ButtonType.OK) {
            	Stage stage = (Stage) boutonQuitter.getScene().getWindow();
    			stage.close();
        	}

        }
        
        public void sauvegarder(ActionEvent event	) {
        	

    	}
        
        public void ajouterPiege(ActionEvent event) {
        	if(!eliminer && nbPionsAPoser==0) {
	        	if(nbPieges!=0){
	        		advertJeu.setText("Pi�ge d�j� en place !");
	        	}else {
	        		this.blocage=false;
	            	this.piege=true;
	            	advertJeu.setText("Choisissez un sommet o� poser votre pi�ge !");
	            }
        	}
    	}
        	
        
        public void bloquerLigne(ActionEvent event) {
        	if(!eliminer && nbPionsAPoser==0) {
	        	if(nbBlocages!=0){
	        		advertJeu.setText("Blocage d�j� en place !");
	        	}else {
	        		this.piege=false;
	            	this.blocage=true;
	            	advertJeu.setText("Choisissez une ligne o� poser votre blocage !");
	            	for (Line line : lignes) {
	            		line.setCursor(Cursor.HAND);
	    			}
	        	}
        	}
        }
        
        public boolean estVide(int position,int position2) {
        	return sommets[position][position2].getFill().equals(Color.valueOf("0x1e90ffff"));
        }
        
        public boolean verifColor(int position,int position2,Color c) {
        	return sommets[position][position2].getFill().equals(c);
        }
        
        public void swapJoueurs() {
        	if(joueurCourant.getText().equals("J1")) {
					joueurCourant.setText("J2");
				}else joueurCourant.setText("J1");
        }
        
        public boolean estPiege(int position,int position2) {
        	return sommets[position][position2].getFill().equals(Color.RED);
        }
        
        
        public int getX(Circle c) {
        	  for (int i = 0; i <3; i++) {
	            	for(int j = 0; j <8; j++) {
	            		if(sommets[i][j].equals(c)) {
	            			return i+1;
	            		}
	            		
	            	}
        	  }
        	  return 0;
        }
        
		public int getY(Circle c) {
			for (int i = 0; i <3; i++) {
            	for(int j = 0; j <8; j++) {
            		if(sommets[i][j].equals(c)) {
            			return j+1;
            		}
            		
            	}
    	  }
    	  return 0; 	
        }
        
        public boolean areNeighbors(Circle c1,Circle c2 ) {
        	if(getX(c1)==getX(c2)) { //meme couche
    			if( getY(c2)==getY(c1)+1 || getY(c2)==getY(c1)-1 || (getY(c2)==1 && getY(c1)==(8)) || (getY(c1)==1 && getY(c2)==(8)) ) {
    				return true;
    			}
    		}else if(getX(c2)==getX(c1)+1 || getX(c2)==getX(c1)-1) { //couche diff
    			if((getY(c1)==getY(c2) && getY(c1)%2==0 && getY(c2)%2==0)){ 
    				return true;
    			}
    		}	
    		return false;
    		
    		
        }
        
        public void testAlign(Color c) {
        	
        	if( c.equals(Color.BLACK) && areLinedUp(Color.BLACK)) {
				eliminer=true;
				advertJeu.setText(nomJ1+" elimine un pion");
			}else if(c.equals(Color.WHITE) && areLinedUp(Color.WHITE)){
				eliminer=true;
				advertJeu.setText(nomJ2+" elimine un pion");
			}
        }
        
        
        public void testFin() {
        
        	if(Integer.parseInt(labelJ1Restants.getText())<3) {
        		advertJeu.setText(nomJ2+" gagne la partie !\nMerci d'avoir jou� :)");
        	}else if(Integer.parseInt(labelJ2Restants.getText())<3) {
        		advertJeu.setText(nomJ1+" gagne la partie !\nMerci d'avoir jou� :)");
        	}
        	
        }
        
        public boolean areLinedUp(Color c) {

        	if(verifColor(0,0, c) && verifColor(0,1, c) && verifColor(0,2, c)) return true;
        	if(verifColor(0,2, c) && verifColor(0,3, c) && verifColor(0,4, c)) return true;
        	if(verifColor(0,4, c) && verifColor(0,5, c) && verifColor(0,6, c)) return true;
        	if(verifColor(0,6, c) && verifColor(0,7, c) && verifColor(0,0, c)) return true;

        	if(verifColor(1,0, c) && verifColor(1,1, c) && verifColor(1,2, c)) return true;
        	if(verifColor(1,2, c) && verifColor(1,3, c) && verifColor(1,4, c)) return true;
        	if(verifColor(1,4, c) && verifColor(1,5, c) && verifColor(1,6, c)) return true;
        	if(verifColor(1,6, c) && verifColor(1,7, c) && verifColor(1,0, c)) return true;
        	
         	if(verifColor(2,0, c) && verifColor(2,1, c) && verifColor(2,2, c)) return true;
        	if(verifColor(2,2, c) && verifColor(2,3, c) && verifColor(2,4, c)) return true;
        	if(verifColor(2,4, c) && verifColor(2,5, c) && verifColor(2,6, c)) return true;
        	if(verifColor(2,6, c) && verifColor(2,7, c) && verifColor(2,0, c)) return true;

        	if(verifColor(0,1, c) && verifColor(1,1, c) && verifColor(2,1, c)) return true;
        	if(verifColor(0,3, c) && verifColor(1,3, c) && verifColor(2,3, c)) return true;
        	if(verifColor(0,5, c) && verifColor(1,5, c) && verifColor(2,5, c)) return true;
        	if(verifColor(0,7, c) && verifColor(1,7, c) && verifColor(2,7, c)) return true;

        	return false;
        }
        
        //gestion interfaces diff menu
        //bot
        //bloquage ligne
        // save
        
        

}

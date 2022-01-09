package Plateau;

import Jeu.Joueur;

public class Sommet{
	private int x;
	private int y;
	private Pion pion;
	private Piege piege = null;
	
	public Sommet(int x, int y) {
		super();
		if(x>0 && x<=PlateauCarre.NB_COUCHES)this.x=x;
		if(y>0 && y<=PlateauCarre.NB_SOMMETS)this.y=y;
		
	}

	public String getCoordonnees() {
		return x+","+y;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public Pion getPion() {
		return pion;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}
	
	/**
	 * ajouter un pion sur le sommet
	 * @param pion le pion à placer
	 */
	public void setPion(Pion pion) {
		this.pion = pion;
	}
	
	/**
	 * retirer le pion du sommet
	 */
	public void removePion() {
		this.pion = null;
	}
	
	/**
	 * placer le piège du joueur sur le sommet
	 * @param j le joueur en question
	 */
	public void setPiege(Joueur j) {
		this.piege = new Piege(j);
	}
	
	public Piege getPiege() {
		return piege;
	}

	public void removePiege() {
		this.piege = null;
	}
	
	/**
	 * Cette méthode permet si le sommet est piégé
	 * @return vrai si il y a un piège sinon faux
	 */
	public boolean isTrapped() {
		if(piege != null) {
			return true;
		}
		return false;
	}
	
	/**
	 * Cette méthode permet de savoir si la case est libre
	 * @return vrai si elle est libre sinon faux
	 */
	public boolean isFree() {
		if( this.pion==null) {
			return true;
		}else return false;
	}
	
	/**
	 * Cette méthode permet de savoir si des coordonnées existent pour le carré
	 * @param x 
	 * @param y
	 * @return vrai si les coord existent sinon faux
	 */
	public static boolean cooExistent(int x, int y) {
		if(x>0 && x<=PlateauCarre.NB_COUCHES && y>0 && y<=PlateauCarre.NB_SOMMETS) {
			return true;
		}else return false;
	}
	
	/**
	 * Cette méthode permet de savoir si des coordonnées existent pour le triangle
	 * @param x 
	 * @param y
	 * @return vrai si les coord existent sinon faux
	 */
	public static boolean cooExistentTriangle(int x, int y) {
		if(x>0 && x<=PlateauTriangle.NB_COUCHES && y>0 && y<=PlateauTriangle.NB_SOMMETS) {
			return true;
		}else return false;
	}
	
	@Override
	public String toString() {
		String res = "";
		if(pion != null) { res = "" + pion.getJ().getNumeroJoueur(); }
		else res = "0" ;
		return res;
	}
	
	public String toString1() {
		return x + ", " +y;
	}
}

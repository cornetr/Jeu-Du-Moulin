package Plateau;

import Jeu.Joueur;

public class Pion {
	private Joueur j;
	
	public Pion(Joueur j) {
		super();
		this.j = j;
	}
	
	public Pion() {
		this(null);
	}
	
	/**
	 * @return le joueur poss�dant le pion
	 */
	public Joueur getJ() {
		return j;
	}

	/**
	 * 
	 * @param j le joueur � affecter au pion
	 */
	public void setJ(Joueur j) {
		this.j = j;
	}

	@Override
	public String toString() {
		return "appartient au joueur: " + j;
	}
	
}

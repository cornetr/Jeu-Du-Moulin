package Plateau;

import Jeu.Joueur;

public class Piege {
	private Joueur j;

	
	public Piege(Joueur j) {
		super();
		this.j = j;

	}
	
	/**
	 * 
	 * @return le joueur poss�dant le pi�ge
	 */
	public Joueur getJ() {
		return j;
	}

	
}

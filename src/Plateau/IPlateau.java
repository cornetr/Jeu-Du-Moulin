package Plateau;

import Jeu.Joueur;

public interface IPlateau {

	public Sommet[][] initSommet();
	public Sommet[][] getPlateau();
	public boolean areNeighbors(Sommet s1,Sommet s2);
	public void affichePlateau();
	public void initSommet2(Pion p, Joueur joueur);
	public int getNbTour();
	public boolean areLinedUp(Joueur j);
	public void deplacerPion(Joueur joueur);
	public Pion retirerPion(Joueur j);
	public void placerPiege(Joueur j);
	public void deplacementAleatoire(Sommet s);
	public boolean isMovable(Sommet s);
	public Sommet[] destinationBot(Sommet s);
	public void deplacementBot(Joueur j);
	public void initPionsBot(Pion p, Joueur joueur);
	public Pion retirerPionBot(Joueur j);
}

package Plateau;

import java.util.Random;

import Jeu.Joueur;
import Jeu.Outils;

public class PlateauTriangle implements IPlateau {

	private static final int NB_COTES = 3;
	public static final int NB_COUCHES = 3;
	public static final int NB_SOMMETS = NB_COTES*2;
	public final int NB_PIONS_PAR_JOUEUR = 6;
	private Sommet[][] plateau = new Sommet[NB_COUCHES][NB_SOMMETS];
	private int nbTour = 0;

	public PlateauTriangle() {
		plateau = initSommet();
		initSommet();
	}

	/**
	 * Cette méthode permet d'initialiser les sommets du plateau en fonction du nombre défini par les attributs finaux.
	 * Elle renvoie ces sommets dans un tableau à deux dimensions
	 */
	@Override
	public Sommet[][] initSommet() {
		for(int x = 0; x < NB_COUCHES; x++) {
			for(int y = 0; y < NB_SOMMETS; y++) 
				plateau[x][y] = new Sommet(x+1,y+1);
		}
		return plateau;
	}

	@Override
	public Sommet[][] getPlateau() {
		return plateau;
	}

	/**
	 * Cette méthode permet de vérifier si deux sommets sont voisins
	 * @param s1 premier sommet à comparer
	 * @param s2 deuxième sommet à comparer
	 */
	@Override
	public boolean areNeighbors(Sommet s1, Sommet s2) {
		if(s1.getX()==s2.getX()) { //meme couche
			if( s2.getY()==s1.getY()+1 || s2.getY()==s1.getY()-1 || (s2.getY()==1 && s1.getY()==(NB_SOMMETS)) || (s1.getY()==1 && s2.getY()==(NB_SOMMETS)) ) {
				return true;
			}
		}else if(s2.getX()==s1.getX()+1 || s2.getX()==s1.getX()-1) { //couche diff
			if((s1.getY()==s2.getY() && s1.getY()%2==0 && s2.getY()%2==0)){ 
				return true;
			}
		}	
		return false;
	}

	//méthode en dur à refaire pour optimisation
	/**
	 * Cette méthode permet d'afficher le plateau en textuel
	 */
	@Override
	public void affichePlateau() {
		String res="";

		res += "                " + plateau[2][0] + "\n";
		res += "               / \\" + "\n";
		res += "              /   \\" + "\n"; 
		res += "             /     \\" + "\n";
		res += "            /   " + plateau[1][0] + "   \\ " + "\n";
		res += "           /   / \\   \\ " + "\n";
		res += "          /   /   \\   \\" + "\n";
		res += "         /   /     \\   \\" + "\n";
		res += "        /   /   " + plateau[0][0] + "   \\   \\" + "\n";
		res += "       /   /   / \\   \\   \\" + "\n";
		res += "      " + plateau[2][5] + "---" + plateau[1][5] + "---" + plateau[0][5] + "   " + plateau[0][1] + "---" + plateau[1][1] + "---" + plateau[2][1] + "\n";
		res += "     /   /   /     \\   \\   \\" + "\n";
		res += "    /   /   " + plateau[0][4] + "---" + plateau[0][3] + "---" + plateau[0][2] + "   \\   \\" + "\n";
		res += "   /   /        |        \\   \\" + "\n";
		res += "  /   " + plateau[1][4] + "---------" + plateau[1][3] + "---------" + plateau[1][2] + "   \\" + "\n";
		res += " /              |              \\" + "\n";
		res += plateau[2][4] + "---------------" + plateau[2][3] + "---------------" + plateau[2][2];




		System.out.println(res);
	}

	public String toString() {
		String res = "";
		for (int i = 0; i < NB_COUCHES; i++) {
			for (int j = 0; j < NB_SOMMETS; j++) {
				res += plateau[i][j].toString() + "\n";
			}
		}
		return res;
	}

	/**
	 * Cette méthode permet d'effectuer la saisie nécessaire pour placer le pion d'un joueur sur le plateau,
	 * de l'ajouter à la liste de pion du joueur
	 * et de définir l'attribut joueur du pion
	 * @param p le pion du joueur à ajouter au plateau
	 * @param joueur le joueur possédant le pion
	 */
	@Override
	public void initSommet2(Pion p, Joueur joueur) {
		System.out.println("Choisissez ou placer le pion");
		int[] tab = Outils.scannerChoixPion();
		int x = tab[0]; int y = tab[1];

		if(Sommet.cooExistentTriangle(x, y)) {
			if(plateau[x-1][y-1].isFree()) {
				plateau[x-1][y-1].setPion(p);
				joueur.add(p);
				p.setJ(joueur);
				System.out.println("Entrée validée !\n");
			}
			else {
				System.out.println("La case est deja occupee");
				initSommet2(p, joueur);
			}
		}
		else {
			System.out.println("Coordonnées inexistantes !");
			initSommet2(p, joueur);
		}

	}

	//Utile plus tard pour implémenter le nombre de tour dans le score
	@Override
	public int getNbTour() {
		return nbTour;
	}

	//méthode en dur à refaire pour optimisation
	/**
	 * Cette méthode permet de vérifier si 3 pions d'un joueur sont alignés sur le plateau
	 * @param j le joueur à vérifier
	 */
	@Override
	public boolean areLinedUp(Joueur j) {
		if(j.getListePions().contains(plateau[0][0].getPion()) && j.getListePions().contains(plateau[0][1].getPion()) && j.getListePions().contains(plateau[0][2].getPion())) return true;
		if(j.getListePions().contains(plateau[0][2].getPion()) && j.getListePions().contains(plateau[0][3].getPion()) && j.getListePions().contains(plateau[0][4].getPion())) return true;
		if(j.getListePions().contains(plateau[0][4].getPion()) && j.getListePions().contains(plateau[0][5].getPion()) && j.getListePions().contains(plateau[0][0].getPion())) return true;

		if(j.getListePions().contains(plateau[1][0].getPion()) && j.getListePions().contains(plateau[1][1].getPion()) && j.getListePions().contains(plateau[1][2].getPion())) return true;
		if(j.getListePions().contains(plateau[1][2].getPion()) && j.getListePions().contains(plateau[1][3].getPion()) && j.getListePions().contains(plateau[1][4].getPion())) return true;
		if(j.getListePions().contains(plateau[1][4].getPion()) && j.getListePions().contains(plateau[1][5].getPion()) && j.getListePions().contains(plateau[1][0].getPion())) return true;

		if(j.getListePions().contains(plateau[2][0].getPion()) && j.getListePions().contains(plateau[2][1].getPion()) && j.getListePions().contains(plateau[2][2].getPion())) return true;
		if(j.getListePions().contains(plateau[2][2].getPion()) && j.getListePions().contains(plateau[2][3].getPion()) && j.getListePions().contains(plateau[2][4].getPion())) return true;
		if(j.getListePions().contains(plateau[2][4].getPion()) && j.getListePions().contains(plateau[2][5].getPion()) && j.getListePions().contains(plateau[2][0].getPion())) return true;

		if(j.getListePions().contains(plateau[0][1].getPion()) && j.getListePions().contains(plateau[1][1].getPion()) && j.getListePions().contains(plateau[2][1].getPion())) return true;
		if(j.getListePions().contains(plateau[0][3].getPion()) && j.getListePions().contains(plateau[1][3].getPion()) && j.getListePions().contains(plateau[2][3].getPion())) return true;
		if(j.getListePions().contains(plateau[0][5].getPion()) && j.getListePions().contains(plateau[1][5].getPion()) && j.getListePions().contains(plateau[2][5].getPion())) return true;

		return false;
	}

	/**
	 * Cette méthode permet d'effectuer deux saisies
	 * La première pour sélectionner le pion à déplacer
	 * et la deuxième pour choisir le sommet où placer le pion
	 * @param joueur le joueur à qui l'ont veut déplacer le pion
	 */
	public void deplacerPion(Joueur joueur) {
		System.out.println("Choisissez un pion");
		int[] tab = Outils.scannerChoixPion();
		int x = tab[0], y = tab[1];
		int i = 0, j = 0;

		if(Sommet.cooExistentTriangle(x, y) && !plateau[x-1][y-1].isFree() && plateau[x-1][y-1].getPion().getJ().getNAME().equals(joueur.getNAME())) {
			Sommet tmp = plateau[x-1][y-1];
			System.out.println("choisissez ou placer le pion");
			tab = Outils.scannerChoixPion();
			i = tab[0]; j = tab[1];
			if(Sommet.cooExistentTriangle(i, j)) {
				if(plateau[i-1][j-1].isFree() && areNeighbors(tmp, plateau[i-1][j-1])) {
					plateau[i-1][j-1].setPion(tmp.getPion());
					System.out.println("Pion deplacé !");
					plateau[x-1][y-1].removePion();
				}
				else if(!areNeighbors(tmp, plateau[x-1][y-1])){
					System.out.println("La case n'est pas voisine");
					deplacerPion(joueur);
				}
				else {
					System.out.println("La case est deja occupee");
					deplacerPion(joueur);
				}
			}
			else {
				System.out.println("Coordonnées inexistantes !");
				deplacerPion(joueur);
			}
		}
		else if(plateau[x-1][y-1].isFree()) {
			System.out.println("La case séléctionnée ne contient aucun pion");
			deplacerPion(joueur);
		}
		else if (!plateau[x-1][y-1].getPion().getJ().getNAME().equals(joueur.getNAME())) {
			System.out.println("Ce n'est pas votre pion !");
			deplacerPion(joueur);
		}
		else {
			System.out.println("Coordonnées inexistantes !");
			deplacerPion(joueur);
		}
		if(plateau[i-1][j-1].isTrapped() /*&& !plateau[i-1][j-1].getPiege().getJ().equals(joueur)*/) {
			deplacementAleatoire(plateau[i-1][j-1]);
		}
	}

	/**
	 * Cette méthode permet de retirer un pion au joueur adverse en effectuant une saisie pour le retirer aussi du plateau 
	 * @param j le joueur adverse
	 */
	public Pion retirerPion(Joueur j) {
		System.out.println("Choisissez un pion");
		int[] tab = Outils.scannerChoixPion();
		Pion p;
		int x = tab[0], y = tab[1];
		System.out.println(""+x+","+y);
		if(Sommet.cooExistentTriangle(x, y)) {
			if(!plateau[x-1][y-1].isFree() && plateau[x-1][y-1].getPion().getJ().equals(j)) {
				p = plateau[x-1][y-1].getPion();
				plateau[x-1][y-1].removePion();
				return p;
			}
			else {
				System.out.println("Case vide !");
				retirerPion(j);
			}
		}
		else if (!plateau[x-1][y-1].getPion().getJ().equals(j)) {
			System.out.println("Vous ne pouvez pas retirer votre pion");
		}
		else {
			System.out.println("Coordonnées inexistantes !");
			retirerPion(j);
		}
		return null;
	}

	/**
	 * Cette méthode sert à placer un piège sur le plateau
	 * @param j le joueur placant le piège
	 */
	public void placerPiege(Joueur j) {
		System.out.println("Choisissez où placer le piège");
		int[] tab = Outils.scannerChoixPion();
		int x = tab[0], y = tab[1];
		System.out.println(x + " "+ y);
		if(Sommet.cooExistentTriangle(x, y) && !plateau[x-1][y-1].isTrapped() && j.getNbPionRestant() != 0) {
			System.out.println("Piège placé");
			plateau[x-1][y-1].setPiege(j);
			j.setNbPionRestant(0);
		}
		else if(!Sommet.cooExistentTriangle(x, y)) {
			System.out.println("Coordonnées inexistantes !");
			placerPiege(j);
		}
		else if(plateau[x-1][y-1].isTrapped()) {
			System.out.println("Case déjà piégée !");
			placerPiege(j);
		}
		else System.out.println("Vous ne pouvez plus placer de piège");
	}

	/**
	 * Cette méthode permet de téléporter alétoirement le pion sur une autre case en déclenchant un piège
	 * @param s le sommet piégé afin d'y retirer le piège
	 */
	public void deplacementAleatoire(Sommet s) {
		Random r = new Random();
		int x = 0, y = 0;

		if(s.isTrapped()) {
			s.removePiege();
			while(!Sommet.cooExistentTriangle(x, y) && !plateau[x][y].isFree()) {
				x = r.nextInt(NB_COTES);
				y = r.nextInt(NB_SOMMETS);
			}
			plateau[x][y].setPion(s.getPion());
			System.out.println("Vous avez declenché un piège !");
			s.removePion();
		}
	}
	
	//Méthode du bot non implémenté car non fonctionnel sur le plateau carré
	@Override
	public boolean isMovable(Sommet s) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Sommet[] destinationBot(Sommet s) {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public void initPionsBot(Pion p, Joueur joueur) {
		// TODO Auto-generated method stub

	}

	@Override
	public Pion retirerPionBot(Joueur j) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deplacementBot(Joueur j) {
		// TODO Auto-generated method stub

	}

}

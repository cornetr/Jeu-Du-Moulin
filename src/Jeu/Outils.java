package Jeu;

import java.util.Scanner;

public class Outils {
	
	//Classe outil regroupant des méthodes static pour les saisies de l'utilisateur
	
	/**
	 * saisie pour le menu
	 * @return le numéro du choix de l'utilisateur
	 */
	public static int scannerChoixMenu() {
		Scanner sc = new Scanner(System.in);
		String saisie = "";

		while(!saisie.equals("1") || !saisie.equals("2") || !saisie.equals("3")) {
			saisie = sc.next();
			if(saisie.equals("1")) return 1;
			else if(saisie.equals("2")) return 2;
			else if(saisie.equals("3")) return 3;
		}
		return 0;
	}

	/**
	 * saisie du nom de joueur
	 * @return le nom choisi
	 */
	public static String scannerName() {
		Scanner sc = new Scanner(System.in);
		String saisie ="";
		saisie = sc.next();
		if(saisie.equals("BOT"))
			scannerName();
		return saisie;
	}

	public static int scannerChoixJeu() {
		Scanner sc = new Scanner(System.in);
		String saisie = "";

		while(!saisie.equals("1") || !saisie.equals("2") || !saisie.equals("3")) {
			saisie = sc.next();
			if(saisie.equals("1")) return 1;
			else if(saisie.equals("2")) return 2;
		}
		return 0;
	}
	
	/**
	 * saisie des coordonnées + verification qu'il n'y aie aucun caractère autre qu'un chiffre saisi
	 * @return un tableau contenant la coordonnée x et la coordonnée y
	 */
	public static int[] scannerChoixPion() {
		Scanner sc = new Scanner(System.in);
		int x=0,y=0;
		boolean saisieCorrecte = false;
		while (!saisieCorrecte) {
			try {
				System.out.print("x=");
				x=Integer.parseInt(sc.next());
				System.out.print("y=");
				y=Integer.parseInt(sc.next());
				saisieCorrecte = true;
			} catch (NumberFormatException e) {
				System.out.println("Ne pas mettre de caractère");
			} 
		}

		int[] res = {x,y};
		return res;
	}
}


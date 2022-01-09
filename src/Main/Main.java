package Main;

import Jeu.Joueur;
import Jeu.Menu;
import Jeu.Outils;
import Plateau.IPlateau;
import Plateau.Pion;
import Plateau.PlateauCarre;
import Plateau.PlateauTriangle;

public class Main {

	public static void main(String[] args) {

		Menu m = new Menu();
		Joueur joueurCourant = null;
		Joueur joueurNonCourant = null;
		IPlateau p = null;

		m.start();
		int saisie = m.choix();
		if(saisie == 1) {
			p = new PlateauCarre();
			m.menuCarre();
		}
		else if(saisie == 2) {
			p = new PlateauTriangle();
			m.menuTriangle();
		}
		saisie = m.choix();
		if(saisie == 1) {
			String s;
			System.out.println("J1, choisissez un nom:");
			s = Outils.scannerName();
			joueurCourant = new Joueur(s);
			System.out.println("J2, choisissez un nom:");
			s = Outils.scannerName();
			while(s.equals(joueurCourant.getNAME())) {
				System.out.println("Veuillez choisir un autre nom:");
				s = Outils.scannerName();	
			}
			joueurNonCourant = new Joueur(s);

		}
		else if(saisie == 2) {
			System.out.println("J1, choisissez un nom:");
			joueurCourant = new Joueur(Outils.scannerName());
			joueurNonCourant  = new Joueur("BOT");
		}

		Joueur tmp;
		p.initSommet();

		if(!joueurNonCourant.getNAME().equals("BOT")) {
			while(!joueurCourant.finPhaseTest()) {
				p.affichePlateau();
				System.out.println(joueurCourant.getNAME() + " place un pion");
				p.initSommet2(new Pion(), joueurCourant);
				tmp = joueurCourant;
				joueurCourant = joueurNonCourant;
				joueurNonCourant = tmp;

			}
			while(!joueurCourant.finJeu()) {
				p.affichePlateau();
				System.out.println(joueurCourant.getNAME() + " à ton tour !");
				m.choixActionDeJeu();
				saisie = m.choix();
				if(saisie == 1) {
					p.deplacerPion(joueurCourant);
				}
				if(saisie == 2) {
					p.placerPiege(joueurCourant);
				}
				p.affichePlateau();
				if(p.areLinedUp(joueurCourant)) {
					System.out.println("Vous pouvez retirer un pion à votre adversaire");
					Pion pionTmp = p.retirerPion(joueurNonCourant);
					joueurNonCourant.remove(pionTmp);
				}
				tmp = joueurCourant;
				joueurCourant = joueurNonCourant;
				joueurNonCourant = tmp;
			}
			System.out.println(joueurCourant.getNAME() + " n'a plus assez de pions pour jouer...");
			System.out.println(joueurNonCourant.getNAME() + " a remporté la partie");
		}

		if(joueurNonCourant.getNAME().equals("BOT")) {
			while(!joueurCourant.finPhaseTest()) {
				p.affichePlateau();
				System.out.println(joueurCourant.getNAME() + " place un pion");
				if(!joueurCourant.getNAME().equals("BOT"))
					p.initSommet2(new Pion(), joueurCourant);
				else 
					p.initPionsBot(new Pion(), joueurCourant);
				tmp = joueurCourant;
				joueurCourant = joueurNonCourant;
				joueurNonCourant = tmp;
			}
			while(!joueurCourant.finJeu()) {
				p.affichePlateau();
				if(!joueurCourant.getNAME().equals("BOT")) {
					System.out.println(joueurCourant.getNAME() + " à ton tour !");
					m.choixActionDeJeu();
					saisie = m.choix();
					if(saisie == 1) {
						p.deplacerPion(joueurCourant);
					}
					if(saisie == 2) {
						p.placerPiege(joueurCourant);
					}
					p.affichePlateau();
				}
				else 
					p.deplacementBot(joueurCourant);
				if(p.areLinedUp(joueurCourant)) {
					if(!joueurCourant.getNAME().equals("BOT")) {
						System.out.println("Vous pouvez retirer un pion à votre adversaire");
						Pion pionTmp = p.retirerPion(joueurNonCourant);
						joueurNonCourant.remove(pionTmp);
					}
					else {
						Pion pionTmp = p.retirerPionBot(joueurNonCourant);
						joueurNonCourant.remove(pionTmp);
					}
						
				}
				tmp = joueurCourant;
				joueurCourant = joueurNonCourant;
				joueurNonCourant = tmp;
			}
			System.out.println(joueurCourant.getNAME() + " n'a plus assez de pions pour jouer...");
			System.out.println(joueurNonCourant.getNAME() + " a remporté la partie");
		}
	}
}

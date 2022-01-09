package Jeu;

import java.util.ArrayList;
import java.util.Scanner;

import Plateau.Pion;
import Plateau.PlateauCarre;
import Plateau.Sommet;

public class Joueur {
	private final String NAME;
	private final int NUMERO_JOUEUR;
	private static int cpt = 0;
	private ArrayList<Pion>  listePions;
	private int nbPionRestant = 1;
	



	public Joueur(String nAME) {
		super();
		NAME = nAME;
		this.listePions = new ArrayList<Pion>();
		NUMERO_JOUEUR = cpt;
		cpt++;
	}

	public String getNAME() {
		return NAME;
	}

	public int getNumeroJoueur() {
		return NUMERO_JOUEUR;
	}

	public void setNbPionRestant(int nbPionRestant) {
		this.nbPionRestant = nbPionRestant;
	}

	public int getNbPionRestant() {
		return nbPionRestant;
	}

	public ArrayList<Pion> getListePions() {
		return listePions;
	}

	public Pion searchPion(int idx){
		return listePions.get(idx);
	}

	public void remove(Pion p){
		listePions.remove(p);
	}
	
	public void add(Pion p){
		listePions.add(p);
	}
	
	public void initPion() {
		int i = 0;
		while(i < 9) {
			listePions.add(new Pion(this));
			i++;
		}
	}
	
	public boolean finPhaseTest() {
		while(this.getListePions().size() < 3) {
			return false;
		}
		return true;
	}
	
	public boolean finJeu() {
		if(getListePions().size() < 3) return true;
		return false;
	}
	
	@Override
	public String toString() {
		return this.NAME;
	}


}

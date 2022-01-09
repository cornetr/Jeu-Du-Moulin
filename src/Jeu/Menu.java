package Jeu;

public class Menu {
	
	//certaines méthodes de cette classe sont juste de l'affichage
	//Il faudrait par la suite les mettre dans un fichier afin de les recuperer via une methode qui
	//Cela éviterait d'avoir trop de sysout 
	public void start() {
		System.out.println("Line Up 3\n\n");
		System.out.println("Veuillez faire votre choix:\n");
		System.out.println("1- Plateau carre");
		System.out.println("2- Plateau triangle");
		System.out.println("3- Quitter le jeu");
	}
	
	/**
	 * Cette méthode permet d'appeler une méthode de saisie  pour ensuite naviguer dans le menu
	 * @return la saisie de l'utilisateur
	 */
	public int choix() {
		int choix = 0;
		while(choix != 1 || choix != 2 || choix != 3) {
			choix = Outils.scannerChoixMenu(); 
			if(choix == 1 || choix == 2)
				return choix;
			else if(choix == 3)
				System.exit(0);
		}
		return 0;
	}
	
	public void menuCarre() {
		System.out.println("Mode de jeu choisi: Plateau Carre\n");
		System.out.println("Veuillez faire votre choix:\n");
		System.out.println("1- Joueur vs Joueur");
		System.out.println("2- Joueur vs Bot");
		System.out.println("3- Quitter le jeu");
	}
	
	public void menuTriangle() {
		System.out.println("Mode de jeu choisi: Plateau Triangle\n");
		System.out.println("Veuillez faire votre choix:\n");
		System.out.println("1- Joueur vs Joueur");
		System.out.println("2- Joueur vs Bot");
		System.out.println("3- Retour");
	}
	
	public void choixActionDeJeu() {
		System.out.println("Veuillez choisir une action");
		System.out.println("1- Deplacer un pion");
		System.out.println("2- Placer un piege");
		System.out.println("3- Bloquer une ligne");
	}
}

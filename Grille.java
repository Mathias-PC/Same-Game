import java.io.*;

/**
 * La classe <code>Grille</code> sert à représenter en mémoire la grille de jeu.
 * Elle est définie par un tableau à double entrée de 10 lignes et 15 colonnes,
 * chacune des cases de la grille est représentée par un objet de la classe <code>Bloc</code>.
 * 
 * @version 1.5
 * @author Lukas Simoes
 */
public class Grille {


	/** Tableau à double entrée d'objets de la classe <code>Bloc</code> 
	 * qui sert à représenter la grille interne.
	 */
	private Bloc[][] grille;


	/** Compte le nombre de blocs ayant été supprimés lorsque la méthode 
	 * {@link #supprimer(int, int)} est appelée (utile pour le score).
	 */
	private int nbBlocsSupprimes;


	/** Constante qui représente le nombre de lignes de la grille.
	 */
	public static final int NOMBRE_DE_LIGNES = 10;


	/** Constante qui représente le nombre de colonnes de la grille.
	 */
	public static final int NOMBRE_DE_COLONNES = 15;


	/**
	 * Constructeur qui remplit chaque case de la grille avec un objet de la classe <code>Bloc</code>.
	 * La couleur de chaque bloc est choisie aléatoirement (Rouge, Vert ou Bleu).
	 */
	public Grille() {
		this.nbBlocsSupprimes = 0;
		this.grille = new Bloc[this.NOMBRE_DE_LIGNES][this.NOMBRE_DE_COLONNES];

		for (int i = 0; i != this.NOMBRE_DE_LIGNES; i++) {
			for (int j = 0; j != this.NOMBRE_DE_COLONNES; j++) {
				this.grille[i][j] = new Bloc();
			}
		}
		
	}

	/**
	 * Constructeur qui prend en argument un nom de fichier et remplit chaque case 
	 * de la grille avec un objet de la classe <code>Bloc</code>.
	 * La couleur de chaque bloc est choisie par rapport au contenu du fichier passé en argument 
	 * de ce constructeur.
	 * 
	 * @param fichier Nom du fichier décrivant les couleurs de chaque bloc
	 */
	public Grille(String fichier) {
		this.nbBlocsSupprimes = 0;
		this.grille = new Bloc[this.NOMBRE_DE_LIGNES][this.NOMBRE_DE_COLONNES];
		

		// Ouverutre
		try {
			BufferedReader lecture = new BufferedReader(new FileReader(fichier));


			// Lecture
			try {
				for (int i = 0; i != this.NOMBRE_DE_LIGNES; i++) {
					String ligne = lecture.readLine();
					for (int j = 0; j != this.NOMBRE_DE_COLONNES; j++) {
						this.grille[i][j] = new Bloc(ligne.charAt(j));
					}
				}
			} catch (IOException e2) {
				System.err.println("Erreur de lecture du fichier de la grille.");
			}

			// Fermeture
			try {
				lecture.close();
			} catch (IOException e3) {
				System.err.println("Erreur de fermeture du fichier de la grille.");
			}

		} catch (IOException e1) {
			System.err.println("Erreur d'ouverture du fichier de la grille.");
		}
	}

	/**
	 * Affiche le contenu de la grille de la façon suivante :<br>
	 * <code>    V B R B R V B V R B B R B R B <br>
	 *     R R B B V R B B V B V V B B V <br>
	 *     V B R R B V V B B R V V R B R <br>
	 *     V R R V B R V R V V B V B B R <br>
	 *     B R R V V B V B B B B R B R B <br>
	 *     V B B R V B B B R V B V R R B <br>
	 *     R R R R V R B B R B V B R R V <br>
	 *     R B V V B R R B V B V B B V R <br>
	 *     R V V R R R R R R B R R B B V <br>
	 *     V B B B V B B B R B R B V V V <br>
	 * </code>
	 * @return la chaîne de caractères qui représente l'affichage de la grille
	 */
	public String toString() {
		String resultat = "";
		for (Bloc[] ligne : this.grille) {
			for (Bloc bloc : ligne) {
				resultat += bloc.getCouleur() + " " ;
			}
			resultat += "\n";
		}
		return resultat;
	}


	/** Renvoie la grille de jeu interne.
	 * 
	 * @return La grille de jeu (tableau à double entrée de la classe <code>Bloc</code>);
	 */
	public Bloc[][] getGrille() {
		return this.grille;
	}


	/** Vérifie s'il est possible ou non de supprimer un bloc à l'emplacement passé en argument
	 * en vérifiant que celui-ci possède des voisins de même couleur.
	 * 
	 * @param x Abscisse du bloc
	 * @param y Ordonnée du bloc
	 * @return Booléen qui désigne si le bloc est supprimable ou non
	 */
	public boolean suppressionPossible(int x, int y) {

		if (this.grille[x][y].getCouleur() == ' '){
			return false;
		}

		if (x-1 >= 0 && this.grille[x][y].getCouleur() == this.grille[x-1][y].getCouleur()) {
			return true;
		}

		if (x+1 < this.NOMBRE_DE_LIGNES && this.grille[x][y].getCouleur() == this.grille[x+1][y].getCouleur()) {
			return true;
		}

		if (y-1 >= 0 && this.grille[x][y].getCouleur() == this.grille[x][y-1].getCouleur()) {
			return true;
		}

		if (y+1 < this.NOMBRE_DE_COLONNES && this.grille[x][y].getCouleur() == this.grille[x][y+1].getCouleur()) {
			return true;
		}

		return false;
	}


	/** Méthode recursive qui supprime tous les blocs de même couleur autour du bloc de coordonnées (x, y)
	 * et qui renvoie le nombre de blocs supprimés.
	 * 
	 * @param x Abscisse du bloc
	 * @param y Ordonnée du bloc
	 * @param couleur Couleur du bloc
	 */
	public void supprimerGroupeDeMemeCouleur(int x, int y, char couleur) {


		char couleurCaseActuelle = this.grille[x][y].getCouleur();
		this.grille[x][y].setCouleur(' ');

		int casePrecedenteX = x-1;
		int casePrecedenteY = y-1;
		int caseSuivanteX = x+1;
		int caseSuivanteY = y+1;

		if (casePrecedenteX >= 0 && couleurCaseActuelle == this.grille[casePrecedenteX][y].getCouleur()) {
			this.nbBlocsSupprimes++;
			this.supprimerGroupeDeMemeCouleur(casePrecedenteX, y, couleur);
		}

		if (caseSuivanteX < this.NOMBRE_DE_LIGNES && couleurCaseActuelle == this.grille[caseSuivanteX][y].getCouleur()) {
			this.nbBlocsSupprimes++;
			this.supprimerGroupeDeMemeCouleur(caseSuivanteX, y, couleur);
		}

		if (casePrecedenteY >= 0 && couleurCaseActuelle == this.grille[x][casePrecedenteY].getCouleur()) {
			this.nbBlocsSupprimes++;
			this.supprimerGroupeDeMemeCouleur(x, casePrecedenteY, couleur);
		}

		if (caseSuivanteY < this.NOMBRE_DE_COLONNES && couleurCaseActuelle == this.grille[x][caseSuivanteY].getCouleur()) {
			this.nbBlocsSupprimes++;
			this.supprimerGroupeDeMemeCouleur(x, caseSuivanteY, couleur);
		}
		
	}


	/** Méthode recursive qui surligne tous les blocs de même couleur autour du bloc de coordonnées (x, y).
	 * 
	 * @param x Abscisse du bloc
	 * @param y Ordonnée du bloc
	 * @param couleur Couleur du bloc
	 */
	public void surlignerGroupeDeMemeCouleur(int x, int y, char couleur) {


		char couleurCaseActuelle = this.grille[x][y].getCouleur();
		this.grille[x][y].setCouleur('S'); // On note la couleur "S" pour marquer la surbrillance mais 
										   // on se base toujours sur la couleur de base du bloc pour
										   // rechercher ses voisins 
		this.grille[x][y].setASurligner(true);

		int casePrecedenteX = x-1;
		int casePrecedenteY = y-1;
		int caseSuivanteX = x+1;
		int caseSuivanteY = y+1;

		if (casePrecedenteX >= 0 && couleurCaseActuelle == this.grille[casePrecedenteX][y].getCouleur()) {
			this.surlignerGroupeDeMemeCouleur(casePrecedenteX, y, couleur);
		}

		if (caseSuivanteX < this.NOMBRE_DE_LIGNES && couleurCaseActuelle == this.grille[caseSuivanteX][y].getCouleur()) {
			this.surlignerGroupeDeMemeCouleur(caseSuivanteX, y, couleur);
		}

		if (casePrecedenteY >= 0 && couleurCaseActuelle == this.grille[x][casePrecedenteY].getCouleur()) {
			this.surlignerGroupeDeMemeCouleur(x, casePrecedenteY, couleur);
		}

		if (caseSuivanteY < this.NOMBRE_DE_COLONNES && couleurCaseActuelle == this.grille[x][caseSuivanteY].getCouleur()) {
			this.surlignerGroupeDeMemeCouleur(x, caseSuivanteY, couleur);
		}
		
	}


	/** Indique si des groupes de couleurs sont encore présents dans la grille, 
	 * permettant de savoir, par extension, si la partie est finie ou non.
	 * 
	 * @return Booléen qui renvoie <i>true</i> s'il existe encore des groupes, <i>false</i> sinon.
	 */
	public boolean existeGroupesRestants() {
		for (int i = 0; i != this.NOMBRE_DE_LIGNES; i++) {
			for (int j = 0; j != this.NOMBRE_DE_COLONNES; j++) {
				if (this.suppressionPossible(i, j)) {
					return true;
				}
			}
		}
		return false;
	}


	/** Réinitialise le surlignement des blocs de même couleur.
	 * 
	 * @param couleurDeBase La couleur initiale des blocs ayant été surlignés.
	 */
	public void reinitialiserSurlignement(char couleurDeBase) {
		for (int i = 0; i != this.NOMBRE_DE_LIGNES; i++) {
			for (int j = 0; j != this.NOMBRE_DE_COLONNES; j++) {
				if (this.grille[i][j].getCouleur() == 'S') {
					this.grille[i][j].setCouleur(couleurDeBase);
					this.grille[i][j].setASurligner(false);
				} 
			}
		}
	}


	/**
	 * Permet de simuler la gravité pour faire descendre les colonnes lors de la suppression des blocs.<br>
	 * <code>Ex : (on supprime les blocs verts tout en bas)<br>
	 * &nbsp;&nbsp;&nbsp;&nbsp;B R B                 <br>
	 * &nbsp;&nbsp;&nbsp;&nbsp;B B V&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;B R  <br>
	 * &nbsp;&nbsp;&nbsp;&nbsp;R B R&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;B B B<br>
	 * &nbsp;&nbsp;&nbsp;&nbsp;B B R&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;R B V<br>
	 * &nbsp;&nbsp;&nbsp;&nbsp;B R B&nbsp;&nbsp;&nbsp;-----\&nbsp;&nbsp;&nbsp;B B R<br>
	 * &nbsp;&nbsp;&nbsp;&nbsp;R R B&nbsp;&nbsp;&nbsp;-----/&nbsp;&nbsp;&nbsp;B R R<br>
	 * &nbsp;&nbsp;&nbsp;&nbsp;R R V&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;R R B<br>
	 * &nbsp;&nbsp;&nbsp;&nbsp;B V R&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;R R B<br>
	 * &nbsp;&nbsp;&nbsp;&nbsp;B B V&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;B V V<br>
	 * &nbsp;&nbsp;&nbsp;&nbsp;V V V&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;B B R<br>
	 * </code>
	 */
	public void descendreColonnes() {
		for (int i = 0; i != this.NOMBRE_DE_LIGNES; i++) {
			for (int j = 0; j != this.NOMBRE_DE_COLONNES; j++) {
				if (this.grille[i][j].getCouleur() == ' ') {
					Bloc temp;
					for (int k = i; k != 0; k--) {
						if (this.grille[k-1][j].getCouleur() != ' ') {
							temp = this.grille[k][j];
							this.grille[k][j] = this.grille[k-1][j];
							this.grille[k-1][j] = temp; 
						}
					}
				}
			}
		}
	}

	/**
	 * Décale les colonnes vers la gauche lorsque celles-ci sont situées à droite d'une colonne vide.<br>
	 * <code>Ex : <br>
	 *                         <br>
	 *                         <br>
	 *&nbsp;&nbsp;&nbsp;&nbsp;R&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;R<br>
	 *&nbsp;&nbsp;&nbsp;&nbsp;B&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;B  <br>
	 *&nbsp;&nbsp;&nbsp;&nbsp;B&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;-----\&nbsp;&nbsp;&nbsp;B  <br>
	 *&nbsp;&nbsp;&nbsp;&nbsp;R&nbsp;&nbsp;&nbsp;B&nbsp;&nbsp;&nbsp;-----/&nbsp;&nbsp;&nbsp;R&nbsp;B<br>
	 *&nbsp;&nbsp;&nbsp;&nbsp;R&nbsp;&nbsp;&nbsp;V&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;R&nbsp;V<br>
	 *&nbsp;&nbsp;&nbsp;&nbsp;B&nbsp;&nbsp;&nbsp;R&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;B&nbsp;R<br>
	 *&nbsp;&nbsp;&nbsp;&nbsp;B&nbsp;&nbsp;&nbsp;V&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;B&nbsp;V<br>
	 *&nbsp;&nbsp;&nbsp;&nbsp;V&nbsp;&nbsp;&nbsp;V&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;V&nbsp;V<br>
	 * </code>
	 */
	public void decalerColonnes() {
		for (int i = 0; i != this.NOMBRE_DE_COLONNES-1; i++) {
			if (this.grille[9][i].getCouleur() == ' ') {
				for (int j = i+1; j != this.NOMBRE_DE_COLONNES; j++) {
					for (int k = 0; k != this.NOMBRE_DE_LIGNES; k++) {
						Bloc temp = this.grille[k][j];
						this.grille[k][j] = this.grille[k][j-1];
						this.grille[k][j-1] = temp;
					}
				}
			}
		}
	}

	/** Utilise l'ensemble des méthodes de cette classe pour effectuer la suppression du groupe de même
	 * couleur que le bloc de coordonnées (x, y).
	 * Renvoie un booléen qui atteste de la bonne réalisation de la suppression.
	 * 
	 * @param x L'abscisse du bloc dans la grille.
	 * @param y L'ordonnée du bloc dans la grille.
	 * @return Un booléen qui renvoie <i>true</i> si la suppression était possible, <i>false</i> sinon.
	 */
	public boolean supprimer(int x, int y) {
		if (this.suppressionPossible(x, y)) {
			this.supprimerGroupeDeMemeCouleur(x, y, this.grille[x][y].getCouleur());
			this.nbBlocsSupprimes++; // On rajoute 1 pour compter le bloc "de base"
			this.descendreColonnes();
			// On vérifie la dernière ligne de la grille pour savoir combien de fois on doit décaler les colonnes
			for (int i = this.NOMBRE_DE_COLONNES-1; i != 0; i--) {
				if (this.grille[this.NOMBRE_DE_LIGNES-1][i].getCouleur() != ' ' &&
					this.grille[this.NOMBRE_DE_LIGNES-1][i-1].getCouleur() == ' ') {
					this.decalerColonnes();
				}
			}
			return true;
		}
		return false; 
	}

	/** Utilise l'ensemble des méthodes de cette classe pour effectuer le surlignement du groupe de même
	 * couleur du le bloc de coordonnées (x, y).
	 * 
	 * @param x L'abscisse du bloc dans la grille.
	 * @param y L'ordonnée du bloc dans la grille.
	 */
	public void surligner(int x, int y) {
		if (this.suppressionPossible(x, y)) {
			this.surlignerGroupeDeMemeCouleur(x, y, this.grille[x][y].getCouleur());
			for (int i = 0; i != this.NOMBRE_DE_LIGNES; i++) {
				for (int j = 0; j != this.NOMBRE_DE_COLONNES; j++) {
					if (this.grille[i][j].getCouleur() == 'S') {
						this.grille[i][j].setASurligner(true);
					}
				}
			}
		}
	}

	/**
	 * Renvoie le nombre de blocs supprimés lors du dernier appel de {@link #supprimer(int, int)}.
	 * 
	 * @return Le nombre de blocs supprimés lors du dernier appel de {@link #supprimer(int, int)}.
	 */
	public int getNbBlocsSupprimes() {
		return this.nbBlocsSupprimes;
	}


	/** Permet de modifier la valeur correspondant au nombre de blocs supprimés.
	 * (On se sert de cette méthode uniquement pour remettre la valeur à 0 dans d'autres classes).
	 * 
	 * @param n L'entier qui correspond au nouveau nombre de blocs supprimés.
	 */
	public void setNbBlocsSupprimes(int n) {
		this.nbBlocsSupprimes = n;
	}
	
}
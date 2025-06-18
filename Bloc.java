import java.util.Random;

/**
 * La classe <code>Bloc</code> sert à représenter en mémoire les blocs présents à l'intérieur
 * de la grille du jeu. Chaque bloc est identifié par sa couleur (Rouge, Vert ou Bleu).
 * 
 * @version 1.2
 * @author Lukas Simoes
 */
public class Bloc {


	/**
	 * La couleur du <code>Bloc</code> (R, V ou B).
	 */
	private char couleur;
	

	/**
	 * Désigne si le <code>Bloc</code> est destiné à être surligné dans la grille.
	 */
	private boolean aSurligner;


	/**
	 * Constructeur qui crée un <code>Bloc</code> avec la couleur fournie en argument (R, V ou B).
	 * 
	 * @param couleur Couleur du <code>Bloc</code> (R, V ou B)
	 */
	public Bloc(char couleur) {
		this.couleur = couleur;
		this.aSurligner = false;
	}


	/**
	 * Constructeur qui crée un <code>Bloc</code> avec une couleur aléatoire (R, V ou B).
	 */
	public Bloc() {
		char[] couleurs = {'R', 'V', 'B'};
		Random aleatoire = new Random();
		this.couleur = couleurs[Math.abs(aleatoire.nextInt()) % 3];
		this.aSurligner = false;
	}


	/** 
	 * Renvoie la couleur associée à ce <code>Bloc</code>.
	 * 
	 * @return La couleur associée à ce <code>Bloc</code> (R, V ou B).
	 */
	public char getCouleur() {
		return this.couleur;
	}


	/** 
	 * Permet de modifier la couleur associée à ce <code>Bloc</code>.
	 * 
	 * @param couleur Future couleur du <code>Bloc</code> (R, V ou B).
	 */
	public char setCouleur(char couleur) {
		return this.couleur = couleur;
	}


	/**
	 * Renvoie si ce <code>Bloc</code> est à surligner ou non.
	 * 
	 * @return Booléen qui indique si ce <code>Bloc</code> est à surligner ou non.
	 */
	public boolean getASurligner() {
		return this.aSurligner;
	}

	
	/**
	 * Permet de désigner le <code>Bloc</code> comme étant à surligner ou non.
	 * 
	 * @param b Booléen qui désigne le <code>Bloc</code> commme étant à surligner ou non.
	 */
	public void setASurligner(boolean b) {
		this.aSurligner = b;
	}

}
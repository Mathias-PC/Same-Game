import java.awt.*;
import javax.swing.*;


/**
 * La classe <code>FenetreJeuPrincipal</code> hérite de la classe <code>JFrame</code>
 * et permet de gérer l'affichage de la fenêtre du jeu principal. C'est dans cette classe
 * que l'on initialise les paramètres de base du jeu (la grille et le score).
 * 
 * @version 1.3
 * @author Lukas Simoes
 */
public class FenetreJeuPrincipal extends JFrame {


	/**
	 * Le nom du fichier avec lequel on charge la grille.
	 */
	private String fichier;

	/**
	 * La grille graphique.
	 */
	private JPanel[][] grilleGraphique;
	
	/**
	 * Constructeur qui crée une fenêtre contenant le jeu principal. La grille
	 * est chargée à l'aide du nom du fichier fourni en argument.
	 * 
	 * @param fichier Le nom du fichier avec lequel on définit la grile.
	 */
	public FenetreJeuPrincipal(String fichier) {
		this.setSize(1600, 900);
		this.setLocation(100, 100);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		Grille grille = new Grille(fichier);
		JPanel panneauGrille = new JPanel();
		JPanel panneauScore = new JPanel();
		JLabel score = new JLabel("<html><div style='text-align: center'>Score<br>0</div></html>", JLabel.CENTER);
		score.setFont(new Font("Arial", Font.BOLD, 40));

		this.setLayout(new BorderLayout());
		panneauGrille.setLayout(new GridLayout(Grille.NOMBRE_DE_LIGNES, Grille.NOMBRE_DE_COLONNES));
		panneauScore.setLayout(new BorderLayout());

		this.dessineGrille(grille, panneauGrille, score);

		panneauScore.setPreferredSize(new Dimension(200, 500));
		panneauScore.add(score, BorderLayout.CENTER);

		this.add(panneauScore, BorderLayout.EAST);
		this.add(panneauGrille, BorderLayout.CENTER);

	}

	/**
	 * Constructeur qui crée une fenêtre contenant le jeu principal. La grille
	 * est chargée aléatoirement.
	 */
	public FenetreJeuPrincipal() {
		this.setSize(1600, 900);
		this.setLocation(100, 100);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		Grille grille = new Grille();
		JPanel panneauGrille = new JPanel();
		JPanel panneauScore = new JPanel();
		JLabel score = new JLabel("<html><div style='text-align: center'>Score<br>0</div></html>", JLabel.CENTER);
		score.setForeground(Color.BLACK);
		score.setFont(new Font("Arial", Font.BOLD, 40));


		this.setLayout(new BorderLayout());
		panneauGrille.setLayout(new GridLayout(Grille.NOMBRE_DE_LIGNES, Grille.NOMBRE_DE_COLONNES));
		panneauScore.setLayout(new BorderLayout());

		this.dessineGrille(grille, panneauGrille, score);

		panneauScore.setPreferredSize(new Dimension(200, 500));
		panneauScore.setBackground(Color.WHITE);
		panneauScore.add(score, BorderLayout.CENTER);

		this.add(panneauScore, BorderLayout.EAST);
		this.add(panneauGrille, BorderLayout.CENTER);

	}


	/**
	 * Rajoute l'ensemble des éléments à mettre dans la grille graphique en fonction
	 * de la grille interne (de la classe <code>Grille</code>).
	 * 
	 * @param grille La grille interne.
	 * @param panneauPrincipal Le panneau qui contiendra la grille graphique.
	 * @param score Le score du joueur.
	 */
	public void dessineGrille(Grille grille, JPanel panneauPrincipal, JLabel score) {
		this.grilleGraphique = new JPanel[Grille.NOMBRE_DE_LIGNES][Grille.NOMBRE_DE_COLONNES];

		for (int i = 0; i != Grille.NOMBRE_DE_LIGNES; i++) {
			for(int j = 0; j != Grille.NOMBRE_DE_COLONNES; j++) {
				// Chaque JBloc sera placé dans un JPanel
				JPanel panneau = new JPanel();
				this.grilleGraphique[i][j] = panneau;

				if (grille.getGrille()[i][j].getCouleur() == ' ') {
					panneau.setBackground(Color.BLACK);
				}

				else {
					panneau.setLayout(new GridLayout(1, 1));
					JBloc bloc = new JBloc(grille.getGrille()[i][j].getCouleur());
					panneau.add(bloc);
					panneau.addMouseListener(new ControleurJeuPrincipal(grille, this, panneauPrincipal, i, j, score, bloc));
				}
				panneauPrincipal.add(panneau);

			}
		}

	}

	/**
	 * Renvoie la grille graphique.
	 * 
	 * @return La grille graphique.
	 */
	public JPanel[][] getGrilleGraphique() {
		return this.grilleGraphique;
	}
}
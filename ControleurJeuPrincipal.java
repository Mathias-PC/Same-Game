import java.awt.*;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import javax.swing.*;

/**
 * La classe <code>ControleurJeuPrincipal</code> réalise l'interface <code>MouseListener</code> et
 * permet de gérer l'ensemble des fonctionnalités du jeu. Elle intéragit avec la grille en interne 
 * lors des actions du joueur puis gère l'affichage en conséquence.
 * Chaque <code>JPanel</code> de la grille est contrôlé par un objet de cette classe.
 * 
 * @version 1.4
 * @author Lukas Simoes
 */
public class ControleurJeuPrincipal implements MouseListener {

	/**
	 * La grille de jeu interne.
	 */
	private Grille grille;

	/**
	 * La fenêtre de jeu, l'affichage global de l'interface de jeu.
	 */
	private FenetreJeuPrincipal fenetre;

	/**
	 * La case de la grille <b>graphique</b> (<code>JPanel</code>) associée à ce contrôleur.
	 */
	private JPanel panneau;

	/**
	 * L'abscisse x de la case de la grille correspondant à ce contrôleur.
	 */
	private int posGrilleX;

	/**
	 * L'ordonnée y de la case de la grille correspondant à ce contrôleur.
	 */
	private int posGrilleY;

	/**
	 * Le score du joueur.
	 */
	private JLabel score;

	/**
	 * Le bloc (<code>JBloc</code>) présent à la case (<code>JPanel</code>) liée à ce contrôleur. 
	 */
	private JBloc bloc;


	/**
	 * La couleur du bloc.
	 */
	private char couleurBloc;


	/**
	 * Constructeur qui initialise l'ensemble des arguments nécessaires pour gérer le fonctionnement de l'affichage
	 * graphique ainsi que la gestion interne de la grille.
	 * 
	 * @param grille La grille de jeu interne.
	 * @param fenetre La fenêtre de jeu.
	 * @param panneau La case de la grille <b>graphique</b> (<code>JPanel</code>) associée à ce contrôleur.
	 * @param posGrilleX L'abscisse x de la case de la grille correspondant à ce contrôleur.
	 * @param posGrilleY L'ordonnée y de la case de la grille correspondant à ce contrôleur.
	 * @param score Le score du joueur.
	 * @param bloc Le bloc (<code>JBloc</code>) présent à la case (<code>JPanel</code>) liée à ce contrôleur.
	 */
	public ControleurJeuPrincipal(Grille grille, FenetreJeuPrincipal fenetre, JPanel panneau, int posGrilleX, int posGrilleY, JLabel score, JBloc bloc) {
		this.grille = grille;
		this.fenetre = fenetre;
		this.panneau = panneau;
		this.posGrilleX = posGrilleX;
		this.posGrilleY = posGrilleY;
		this.score = score;
		this.bloc = bloc;
	}

	/**
	 * Vérifie si le bloc cliqué est supprimable (s'il fait partie d'un groupe), auquel cas
	 * lie le code interne de la grille avec l'affichage graphique.
	 * Cette méthode modifie à la fois la grille interne, la grille graphique et gère le score à 
	 * l'aide des méthodes de la classe <code>Grille</code>.
	 * 
	 * @param evenement L'évènement lié à cette méthode.
	 */
	@Override
	public void mouseClicked(MouseEvent evenement) {
		if (this.grille.supprimer(this.posGrilleX, this.posGrilleY)) {

			this.panneau.removeAll();

			// Calcul Score
			int valeurScore = Integer.parseInt(this.score.getText().substring(47, this.score.getText().length()-13)); 
			valeurScore += Math.pow(grille.getNbBlocsSupprimes() - 2, 2);
			this.score.setText("<html><div style='text-align: center'>Score<br>" + valeurScore + "</div></html>");
			this.grille.setNbBlocsSupprimes(0);


			this.fenetre.dessineGrille(this.grille, this.panneau, this.score);
			this.fenetre.revalidate();
			this.fenetre.repaint();

			// On vérifie si le jeu est terminé ou non après avoir joué le coup
			if (!this.grille.existeGroupesRestants()) {
				FenetreMenuDeFin fenetreFin = new FenetreMenuDeFin();
				fenetreFin.setVisible(true);
			}
		}
	}        

	/**
	 * Surligne un groupe de même couleur lorsque l'utilisateur survole un des blocs de ce groupe.
	 * Cette méthode agit, elle aussi, à la fois sur la grille interne et sur la grille graphique.
	 * 
	 * @param evenement L'évènement lié à cette méthode.
	 */
	@Override
	public void mouseEntered(MouseEvent evenement){
		char couleurBloc = this.grille.getGrille()[this.posGrilleX][this.posGrilleY].getCouleur();
		this.grille.surligner(this.posGrilleX, this.posGrilleY); // Identifie chaque bloc à surligner dans la grille interne

		for (int i = 0; i != Grille.NOMBRE_DE_LIGNES; i++) {
			for (int j = 0; j != Grille.NOMBRE_DE_COLONNES; j++) {

				// Si un bloc est marqué comme "A surligner" en interne
				if (this.grille.getGrille()[i][j].getASurligner()) {  

					// On récupère les composants du JPanel liés à ce bloc
					for (Component composant : this.fenetre.getGrilleGraphique()[i][j].getComponents()) { 

						// Si le composant est un JBloc
						if (composant instanceof JBloc) {
							((JBloc) composant).setEstSurvole(true); // On le passe en survol
							composant.repaint(); // Et on rappelle paintComponent qui affiche son sprite en version survolée 

						}
					}
				}
			}
		}

		this.grille.reinitialiserSurlignement(couleurBloc); // On remet le surlignement actuel à 0 une fois l'affichage terminé
		
	}         


	/** Désactive le surlignement lorsque l'utilisateur ne survole plus un bloc faisant partie
	 * d'un groupe de même couleur. Cette méthode agit, elle aussi, à la fois sur la grille interne et sur la grille graphique.
	 * 
	 * @param evenement L'évènement lié à cette méthode.
	 */
	@Override
	public void mouseExited(MouseEvent evenement){
		// Même logique que pour la méthode mouseEntered 
		for (int i = 0; i != Grille.NOMBRE_DE_LIGNES; i++) {
			for (int j = 0; j != Grille.NOMBRE_DE_COLONNES; j++) {
				if (!this.grille.getGrille()[i][j].getASurligner()) {
					for (Component composant : this.fenetre.getGrilleGraphique()[i][j].getComponents()) {
						if (composant instanceof JBloc) {
							((JBloc) composant).setEstSurvole(false);
							composant.repaint();
						}
					}
				}
			}
		}
	} 


	public void mousePressed(MouseEvent evenement){
	}
 

	public void mouseReleased(MouseEvent evenement){
	} 

}
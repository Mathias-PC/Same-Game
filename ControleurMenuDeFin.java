import java.awt.*;
import javax.swing.*;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;


/**
 * La classe <code>ControleurMenuDeFin</code> réalise l'interface
 * <code>MouseListener</code> et permet de gérer le clic sur les
 * deux boutons "Voir Score" et "Quitter" du menu de fin.
 * 
 * @version 1.0
 * @author Mathias Placide--Contreras
 */
public class ControleurMenuDeFin implements MouseListener {

    /**
     * La fenêtre du menu de fin
     */
    private FenetreMenuDeFin fenetre;


    /**
     * Constructeur qui crée un objet de cette classe permettant de gérer
     * les boutons du menu de fin.
     * 
     * @param fenetre La fenêtre du menu de fin
     */
    public ControleurMenuDeFin(FenetreMenuDeFin fenetre) {
        this.fenetre = fenetre;
    }


    /**
     * Réalise les différentes actions du menu de fin (Voir Score et Quitter) en fonction
     * de l'endroit cliqué par le joueur.
     * 
     * @param e L'évènement lié à cette méthode.
     */
    @Override
    public void mouseClicked(MouseEvent e) {
                
        int x = e.getX();
        int y = e.getY();

        // VOIR SCORE
        if (x >= 107 && x <= 400 && y >= 280 && y <= 351) {
            this.fenetre.setVisible(false);
        }

        // QUITTER
        else if (x >= 451 && x <= 668 && y >= 280 && y <= 351) {
            System.exit(0);
        }
    }

    @Override
	public void mousePressed(MouseEvent e) {

	}

    @Override
	public void mouseReleased(MouseEvent e) {

	}

    @Override
	public void mouseEntered(MouseEvent e) {

	}

    @Override
	public void mouseExited(MouseEvent e) {

	}
}
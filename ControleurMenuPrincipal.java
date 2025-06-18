import java.awt.*;
import javax.swing.*;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;


/**
 * La classe <code>ControleurMenuPrincipal</code> réalise l'interface
 * <code>MouseListener</code> et permet de gérer le clic sur les
 * deux boutons "Jouer" et "Quitter" du menu principal.
 * 
 * @version 1.0
 * @author Mathias Placide--Contreras
 */
public class ControleurMenuPrincipal implements MouseListener {


    /**
     * La fenêtre du menu principal.
     */
    private FenetreMenuPrincipal fenetre;



    /**
     * Constructuer qui crée un objet de cette classe permettant de gérer
     * les boutons du menu principal. 
     * 
     * @param fenetre La fenêtre du menu principal.
     */
    public ControleurMenuPrincipal (FenetreMenuPrincipal fenetre) {
        this.fenetre = fenetre;
    }


    /**
     * Réalise les différentes actions du menu princinpal (Jouer et Quitter) en fonction
     * de l'endroit cliqué par le joueur. Le bouton "Jouer" lance un sous-menu permettant
     * de sélectionner la manière de charger la grille.
     * 
     * @param e L'évènement lié à cette méthode.
     */
    @Override
    public void mouseClicked(MouseEvent e) {
                
        int x = e.getX();
        int y = e.getY();

        // JOUER
        if (x >= 212 && x <= 702 && y >= 490 && y <= 609) {
            this.fenetre.setVisible(false);
            SousMenu sousmenu = new SousMenu();
            sousmenu.setVisible(true);
        }

        // QUITTER
        if (x >= 212 && x <= 900 && y >= 688 && y <= 800) {
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
import java.awt.*;
import javax.swing.*;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;


/**
 * La classe <code>ControleurSousMenu</code> réalise l'interface
 * <code>MouseListener</code> et permet de gérer le clic sur les
 * trois boutons "Charger Grille", "Grille Aléatoire" et "Quitter" du sous-menu.
 * 
 * @version 1.0
 * @author Mathias Placide--Contreras
 */
public class ControleurSousMenu implements MouseListener {

    /**
     * La fenêtre du sous-menu.
     */
    private SousMenu sousMenu;


    /**
     * Constructuer qui crée un objet de cette classe permettant de gérer
     * les boutons du sous-menu. 
     * 
     * @param sousMenu La fenêtre du sous-menu.
     */
    public ControleurSousMenu(SousMenu sousMenu) {
        this.sousMenu = sousMenu;
    } 

    /**
     * Réalise les différentes actions du sous-menu (Charger Grille, Grille Aléatoire et Quitter) en fonction
     * de l'endroit cliqué par le joueur.
     * 
     * @param e L'évènement lié à cette méthode.
     */
    @Override
    public void mouseClicked(MouseEvent e) {
                
        int x = e.getX();
        int y = e.getY();

        // QUITTER
        if (x >= 224 && x <= 590 && y >= 735 && y <= 847) {
            System.exit(0);
        }

        // GRILLE ALEATOIRE
        else if (x >= 224 && x <= 590 && y >= 581 && y <= 693) {
            FenetreJeuPrincipal jeuPrincipal = new FenetreJeuPrincipal();
            this.sousMenu.setVisible(false);
            jeuPrincipal.setVisible(true);
        }

        // CHARGER GRILLE
        else if (x >= 224 && x <= 590 && y >= 419 && y <= 531) {
            JFileChooser selectionFichier = new JFileChooser();
            int choix = selectionFichier.showOpenDialog(this.sousMenu);

            if (choix == JFileChooser.APPROVE_OPTION) {
                FenetreJeuPrincipal jeuPrincipal = new FenetreJeuPrincipal(selectionFichier.getSelectedFile().getPath());
                this.sousMenu.setVisible(false);
                jeuPrincipal.setVisible(true);
            }
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
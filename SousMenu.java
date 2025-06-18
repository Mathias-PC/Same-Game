import java.awt.*;
import javax.swing.*;

/**
 * La classe <code>SousMenu</code> hérite de la classe <code>JFrame</code>
 * et permet de gérer l'affichage du sous-menu.
 * 
 * @version 1.0
 * @author Mathias Placide--Contreras
 */
public class SousMenu extends JFrame {

	/**
	 * Constructeur qui crée une fenêtre contenant le sous-menu.
	 */
	public SousMenu() {

		this.setSize(800, 1000);
		this.setLocation(500, 0);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	    FondSousMenu sousmenu = new FondSousMenu();
	    this.add(sousmenu);
	    this.addMouseListener(new ControleurSousMenu(this));

	}
}
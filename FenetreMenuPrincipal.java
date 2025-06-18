import java.awt.*;
import javax.swing.*;

/**
 * La classe <code>FenetreMenuPrincipal</code> hérite de la classe <code>JFrame</code>
 * et permet de gérer l'affichage du menu principal.
 * 
 * @version 1.0
 * @author Mathias Placide--Contreras
 */
public class FenetreMenuPrincipal extends JFrame {

	/**
	 * Constructeur qui crée une fenêtre contenant le menu principal.
	 */
	public FenetreMenuPrincipal() {
		this.setSize(800, 1000);
		this.setLocation(500, 0);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        FondMenuPrincipal menu = new FondMenuPrincipal();
        this.add(menu);
       	this.addMouseListener(new ControleurMenuPrincipal(this));
	}
}
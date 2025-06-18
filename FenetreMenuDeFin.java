import java.awt.*;
import javax.swing.*;

/**
 * La classe <code>FenetreMenuDeFin</code> hérite de la classe <code>JFrame</code>
 * et permet de gérer l'affichage du menu de fin.
 * 
 * @version 1.0
 * @author Mathias Placide--Contreras
 */
public class FenetreMenuDeFin extends JFrame {

	/**
	 * Constructeur qui crée une fenêtre contenant le menu de fin.
	 */
	public FenetreMenuDeFin() {
		this.setSize(800, 450);
		this.setLocation(500, 325);
		this.setResizable(false);
		this.setLayout(new GridLayout(1, 1));
		this.add(new ImageMenuDeFin());
		this.addMouseListener(new ControleurMenuDeFin(this));
	}
	
}
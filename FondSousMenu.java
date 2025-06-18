import java.awt.*;
import javax.swing.*;


/**
 * La classe <code>FondSousMenu</code> hérite de la classe <code>JComponent</code>
 * et permet de créer un composant graphique qui contient l'image de fond du sous-menu.
 * 
 * @version 1.0
 * @author Mathias Placide--Contreras
 */
public class FondSousMenu extends JComponent {


	/**
	 * Affiche ce composant, soit le fond du sous-menu.
	 */
	@Override
	protected void paintComponent(Graphics pinceau) {
		Graphics secondPinceau = pinceau.create();

    	if (this.isOpaque()) {
      		secondPinceau.setColor(this.getBackground());
      		secondPinceau.fillRect(0, 0, this.getWidth(), this.getHeight());
    	}
    	
    	Image img = Toolkit.getDefaultToolkit().getImage("Image/SousMenu.png");
    	secondPinceau.drawImage(img, 0, 0, this);
	}
}
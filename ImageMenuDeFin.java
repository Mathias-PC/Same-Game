import java.awt.*;
import javax.swing.*;


/**
 * La classe <code>ImageMenuDeFin</code> hérite de la classe <code>JComponent</code>
 * et permet de créer un composant graphique qui contient l'image de fond du menu de fin.
 * 
 * @version 1.0
 * @author Mathias Placide--Contreras
 */
public class ImageMenuDeFin extends JComponent {


	/**
	 * Affiche ce composant, soit le fond du menu de fin.
	 */
	@Override
	public void paintComponent(Graphics pinceau) {
		Graphics secondPinceau = pinceau.create();

		if (this.isOpaque()) {
			secondPinceau.setColor(this.getBackground());
			secondPinceau.fillRect(0, 0, this.getWidth(), this.getHeight());
		}

		Image image = Toolkit.getDefaultToolkit().getImage("Image/PartieTerminee.png");
		secondPinceau.drawImage(image, 0, 0, this);
	}
}
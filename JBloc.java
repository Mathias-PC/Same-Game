import java.awt.*;
import javax.swing.*;


/**
 * La classe <code>JBloc</code> hérite de la classe <code>JComponent</code>
 * et permet de créer un composant qui représente graphiquement un objet
 * de la classe <code>Bloc</code>.
 * 
 * @version 1.2
 * @author Lukas Simoes
 */
public class JBloc extends JComponent {

	/**
	 * Le sprite qui représente le bloc dans son état de base (non surligné).
	 */
	private Image image;


	/**
	 * Le sprite qui représente le bloc lorsqu'il est survolé par la souris (surligné).
	 */
	private Image imageSurvol;

	/**
	 * L'état de survol du bloc
	 */
	private boolean estSurvole;


	/**
	 * Constructeur qui crée un composant graphique représentant un bloc
	 * avec la couleur passée en argument.
	 * 
	 * @param couleur La couleur du bloc.
	 */
	public JBloc(char couleur) {
		this.estSurvole = false;

		switch (couleur) {
			case 'R': // Rouge
				this.image = Toolkit.getDefaultToolkit().getImage("Image/triangle.png");
				this.imageSurvol = Toolkit.getDefaultToolkit().getImage("Image/triangleSurvole.png");
				break;

			case 'V': // Vert
				this.image = Toolkit.getDefaultToolkit().getImage("Image/etoile.png");
				this.imageSurvol = Toolkit.getDefaultToolkit().getImage("Image/etoileSurvole.png");
				break;
				
			case 'B': // Bleu
				this.image = Toolkit.getDefaultToolkit().getImage("Image/croix.png");
				this.imageSurvol = Toolkit.getDefaultToolkit().getImage("Image/croixSurvole.png");
				break;
		}
	}


	/**
	 * Affiche ce composant.
	 */
	@Override
	public void paintComponent(Graphics pinceau) {
		Graphics secondPinceau = pinceau.create();

		if (this.isOpaque()) {
			secondPinceau.setColor(this.getBackground());
			secondPinceau.fillRect(0, 0, this.getWidth(), this.getHeight());
		}

		if (!this.estSurvole) {
			secondPinceau.drawImage(this.image, 0, 0, this.getWidth(), this.getHeight(), this);
		}
		else {
			secondPinceau.drawImage(this.imageSurvol, 0, 0, this.getWidth(), this.getHeight(), this);
		}

	}


	/**
	 * Renvoie l'état de survol actuel du bloc.
	 * 
	 * @return L'état de survol actuel du bloc.
	 */
	public boolean getEstSurvole() {
		return this.estSurvole;
	}


	/**
	 * Permet de modifier l'état de survol actuel du bloc.
	 * 
	 * @param b Le nouvel état de survol actuel du bloc.
	 */
	public void setEstSurvole(boolean b) {
		this.estSurvole = b;
	}

}
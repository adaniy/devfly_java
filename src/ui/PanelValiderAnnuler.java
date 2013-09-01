package ui;

import javax.swing.JPanel;
import javax.swing.JButton;

public class PanelValiderAnnuler extends JPanel {
	private JButton btnValider;
	private JButton btnAnnuler;

	/**
	 * Create the panel.
	 */
	public PanelValiderAnnuler() {
		
		btnValider = new JButton("valider");
		add(btnValider);
		
		btnAnnuler = new JButton("annuler");
		add(btnAnnuler);

		
	}

	public JButton getBtnValider() {
		return btnValider;
	}

	public void setBtnValider(JButton btnValider) {
		this.btnValider = btnValider;
	}

	public JButton getBtnAnnuler() {
		return btnAnnuler;
	}

	public void setBtnAnnuler(JButton btnAnnuler) {
		this.btnAnnuler = btnAnnuler;
	}

}

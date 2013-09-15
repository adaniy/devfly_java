package ui;

import javax.swing.JPanel;

import java.awt.GridBagLayout;

import javax.swing.JLabel;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class PanelModifVol extends JPanel {
	private JTextField textFieldNdeVol;
	private JTextField textFieldVilleDeDepart;
	private JTextField textFieldPaysDeDepart;
	private JTextField textFieldCodeDep;
	private JTextField textFieldVilleDarrivee;
	private JTextField textFieldPaysDarrivee;
	private JTextField textFieldCodeArriv;
	private JTextField textFieldDateDep;
	private JTextField textFieldHeureDep;
	private JTextField textFieldDateArriv;
	private JTextField textFieldHeureArriv;
	private JTextField textFieldDuree;
	private JTextField textFieldTarif;
	private JTextField textFieldPilote;
	private JTextField textFieldCopilote;
	private JTextField textFieldHotesseSt1;
	
	public JButton getBtnMettreAJour() {
		return btnMettreAJour;
	}

	public JButton getBtnReinitialiser() {
		return btnReinitialiser;
	}

	public JButton getBtnSupprimer() {
		return btnSupprimer;
	}

	private JTextField textFieldHotesseSt2;
	private JTextField textFieldHotesseSt3;
	private JLabel lblMessage;
	private JButton btnMettreAJour;
	private JButton btnReinitialiser;
	private JButton btnSupprimer;

	public JTextField getTextFieldNdeVol() {
		return textFieldNdeVol;
	}

	public JTextField getTextFieldVilleDeDepart() {
		return textFieldVilleDeDepart;
	}

	public JTextField getTextFieldPaysDeDepart() {
		return textFieldPaysDeDepart;
	}

	public JTextField getTextFieldCodeDep() {
		return textFieldCodeDep;
	}

	public JTextField getTextFieldVilleDarrivee() {
		return textFieldVilleDarrivee;
	}

	public JTextField getTextFieldPaysDarrivee() {
		return textFieldPaysDarrivee;
	}

	public JTextField getTextFieldCodeArriv() {
		return textFieldCodeArriv;
	}

	public JTextField getTextFieldDateDep() {
		return textFieldDateDep;
	}

	public JTextField getTextFieldHeureDep() {
		return textFieldHeureDep;
	}

	public JTextField getTextFieldDateArriv() {
		return textFieldDateArriv;
	}

	public JTextField getTextFieldHeureArriv() {
		return textFieldHeureArriv;
	}

	public JTextField getTextFieldDuree() {
		return textFieldDuree;
	}

	public JTextField getTextFieldTarif() {
		return textFieldTarif;
	}

	public JTextField getTextFieldPilote() {
		return textFieldPilote;
	}

	public JTextField getTextFieldCopilote() {
		return textFieldCopilote;
	}

	public JTextField getTextFieldHotesseSt1() {
		return textFieldHotesseSt1;
	}

	public JTextField getTextFieldHotesseSt2() {
		return textFieldHotesseSt2;
	}

	public JTextField getTextFieldHotesseSt3() {
		return textFieldHotesseSt3;
	}

	public JLabel getLblMessage() {
		return lblMessage;
	}

	/**
	 * Create the panel.
	 */
	public PanelModifVol() {
		// on joue sur les dimensions de la grille pour positionner les éléments :
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 50, 70, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 21, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		// Label qui pourra contenir les différents messages à afficher :
		lblMessage = new JLabel("");
		lblMessage.setHorizontalAlignment(SwingConstants.CENTER);
		lblMessage.setForeground(Color.RED);
		GridBagConstraints gbc_lblMessage = new GridBagConstraints();
		gbc_lblMessage.gridwidth = 6;
		gbc_lblMessage.insets = new Insets(0, 0, 5, 5);
		gbc_lblMessage.gridx = 0;
		gbc_lblMessage.gridy = 0;
		add(lblMessage, gbc_lblMessage);
		
		// Les différents éléments du formulaires :
		
		JLabel lblNdeVol = new JLabel("n°de vol");
		GridBagConstraints gbc_lblNdeVol = new GridBagConstraints();
		gbc_lblNdeVol.anchor = GridBagConstraints.EAST;
		gbc_lblNdeVol.insets = new Insets(0, 0, 5, 5);
		gbc_lblNdeVol.gridx = 1;
		gbc_lblNdeVol.gridy = 1;
		add(lblNdeVol, gbc_lblNdeVol);
		
		textFieldNdeVol = new JTextField();
		textFieldNdeVol.setEditable(false); // on empêche la modification du numéro de vol
		GridBagConstraints gbc_textFieldNdeVol = new GridBagConstraints();
		gbc_textFieldNdeVol.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldNdeVol.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldNdeVol.gridx = 2;
		gbc_textFieldNdeVol.gridy = 1;
		add(textFieldNdeVol, gbc_textFieldNdeVol);
		textFieldNdeVol.setColumns(10);
		
		JLabel lblDateArriv = new JLabel("date d'arrivée");
		GridBagConstraints gbc_lblDateArriv = new GridBagConstraints();
		gbc_lblDateArriv.anchor = GridBagConstraints.EAST;
		gbc_lblDateArriv.insets = new Insets(0, 0, 5, 5);
		gbc_lblDateArriv.gridx = 4;
		gbc_lblDateArriv.gridy = 1;
		add(lblDateArriv, gbc_lblDateArriv);
		
		textFieldDateArriv = new JTextField();
		GridBagConstraints gbc_textFieldDateArriv = new GridBagConstraints();
		gbc_textFieldDateArriv.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldDateArriv.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldDateArriv.gridx = 5;
		gbc_textFieldDateArriv.gridy = 1;
		add(textFieldDateArriv, gbc_textFieldDateArriv);
		textFieldDateArriv.setColumns(10);
		
		JLabel lblVilleDeDepart = new JLabel("ville de départ");
		GridBagConstraints gbc_lblVilleDeDepart = new GridBagConstraints();
		gbc_lblVilleDeDepart.anchor = GridBagConstraints.EAST;
		gbc_lblVilleDeDepart.insets = new Insets(0, 0, 5, 5);
		gbc_lblVilleDeDepart.gridx = 1;
		gbc_lblVilleDeDepart.gridy = 2;
		add(lblVilleDeDepart, gbc_lblVilleDeDepart);
		
		textFieldVilleDeDepart = new JTextField();
		GridBagConstraints gbc_textFieldVilleDeDepart = new GridBagConstraints();
		gbc_textFieldVilleDeDepart.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldVilleDeDepart.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldVilleDeDepart.gridx = 2;
		gbc_textFieldVilleDeDepart.gridy = 2;
		add(textFieldVilleDeDepart, gbc_textFieldVilleDeDepart);
		textFieldVilleDeDepart.setColumns(10);
		
		JLabel lblHeureArriv = new JLabel("heure d'arrivée");
		GridBagConstraints gbc_lblHeureArriv = new GridBagConstraints();
		gbc_lblHeureArriv.anchor = GridBagConstraints.EAST;
		gbc_lblHeureArriv.insets = new Insets(0, 0, 5, 5);
		gbc_lblHeureArriv.gridx = 4;
		gbc_lblHeureArriv.gridy = 2;
		add(lblHeureArriv, gbc_lblHeureArriv);
		
		textFieldHeureArriv = new JTextField();
		GridBagConstraints gbc_textFieldHeureArriv = new GridBagConstraints();
		gbc_textFieldHeureArriv.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldHeureArriv.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldHeureArriv.gridx = 5;
		gbc_textFieldHeureArriv.gridy = 2;
		add(textFieldHeureArriv, gbc_textFieldHeureArriv);
		textFieldHeureArriv.setColumns(10);
		
		JLabel lblPaysDeDepart = new JLabel("pays de départ");
		GridBagConstraints gbc_lblPaysDeDepart = new GridBagConstraints();
		gbc_lblPaysDeDepart.anchor = GridBagConstraints.EAST;
		gbc_lblPaysDeDepart.insets = new Insets(0, 0, 5, 5);
		gbc_lblPaysDeDepart.gridx = 1;
		gbc_lblPaysDeDepart.gridy = 3;
		add(lblPaysDeDepart, gbc_lblPaysDeDepart);
		
		textFieldPaysDeDepart = new JTextField();
		GridBagConstraints gbc_textFieldPaysDeDepart = new GridBagConstraints();
		gbc_textFieldPaysDeDepart.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldPaysDeDepart.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldPaysDeDepart.gridx = 2;
		gbc_textFieldPaysDeDepart.gridy = 3;
		add(textFieldPaysDeDepart, gbc_textFieldPaysDeDepart);
		textFieldPaysDeDepart.setColumns(10);
		
		JLabel lblDuree = new JLabel("durée en min");
		GridBagConstraints gbc_lblDuree = new GridBagConstraints();
		gbc_lblDuree.anchor = GridBagConstraints.EAST;
		gbc_lblDuree.insets = new Insets(0, 0, 5, 5);
		gbc_lblDuree.gridx = 4;
		gbc_lblDuree.gridy = 3;
		add(lblDuree, gbc_lblDuree);
		
		textFieldDuree = new JTextField();
		GridBagConstraints gbc_textFieldDuree = new GridBagConstraints();
		gbc_textFieldDuree.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldDuree.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldDuree.gridx = 5;
		gbc_textFieldDuree.gridy = 3;
		add(textFieldDuree, gbc_textFieldDuree);
		textFieldDuree.setColumns(10);
		
		JLabel lblCodeDep = new JLabel("code aéroport de départ");
		GridBagConstraints gbc_lblCodeDep = new GridBagConstraints();
		gbc_lblCodeDep.anchor = GridBagConstraints.EAST;
		gbc_lblCodeDep.insets = new Insets(0, 0, 5, 5);
		gbc_lblCodeDep.gridx = 1;
		gbc_lblCodeDep.gridy = 4;
		add(lblCodeDep, gbc_lblCodeDep);
		
		textFieldCodeDep = new JTextField();
		GridBagConstraints gbc_textFieldCodeDep = new GridBagConstraints();
		gbc_textFieldCodeDep.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldCodeDep.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldCodeDep.gridx = 2;
		gbc_textFieldCodeDep.gridy = 4;
		add(textFieldCodeDep, gbc_textFieldCodeDep);
		textFieldCodeDep.setColumns(10);
		
		JLabel lblTarif = new JLabel("tarif en euros");
		GridBagConstraints gbc_lblTarif = new GridBagConstraints();
		gbc_lblTarif.anchor = GridBagConstraints.EAST;
		gbc_lblTarif.insets = new Insets(0, 0, 5, 5);
		gbc_lblTarif.gridx = 4;
		gbc_lblTarif.gridy = 4;
		add(lblTarif, gbc_lblTarif);
		
		textFieldTarif = new JTextField();
		GridBagConstraints gbc_textFieldTarif = new GridBagConstraints();
		gbc_textFieldTarif.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldTarif.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldTarif.gridx = 5;
		gbc_textFieldTarif.gridy = 4;
		add(textFieldTarif, gbc_textFieldTarif);
		textFieldTarif.setColumns(10);
		
		JLabel lblVilleDarrivee = new JLabel("ville d'arrivée");
		GridBagConstraints gbc_lblVilleDarrivee = new GridBagConstraints();
		gbc_lblVilleDarrivee.anchor = GridBagConstraints.EAST;
		gbc_lblVilleDarrivee.insets = new Insets(0, 0, 5, 5);
		gbc_lblVilleDarrivee.gridx = 1;
		gbc_lblVilleDarrivee.gridy = 5;
		add(lblVilleDarrivee, gbc_lblVilleDarrivee);
		
		textFieldVilleDarrivee = new JTextField();
		GridBagConstraints gbc_textFieldVilleDarrivee = new GridBagConstraints();
		gbc_textFieldVilleDarrivee.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldVilleDarrivee.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldVilleDarrivee.gridx = 2;
		gbc_textFieldVilleDarrivee.gridy = 5;
		add(textFieldVilleDarrivee, gbc_textFieldVilleDarrivee);
		textFieldVilleDarrivee.setColumns(10);
		
		JLabel lblPilote = new JLabel("pilote");
		GridBagConstraints gbc_lblPilote = new GridBagConstraints();
		gbc_lblPilote.anchor = GridBagConstraints.EAST;
		gbc_lblPilote.insets = new Insets(0, 0, 5, 5);
		gbc_lblPilote.gridx = 4;
		gbc_lblPilote.gridy = 5;
		add(lblPilote, gbc_lblPilote);
		
		textFieldPilote = new JTextField();
		GridBagConstraints gbc_textFieldPilote = new GridBagConstraints();
		gbc_textFieldPilote.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldPilote.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldPilote.gridx = 5;
		gbc_textFieldPilote.gridy = 5;
		add(textFieldPilote, gbc_textFieldPilote);
		textFieldPilote.setColumns(10);
		
		JLabel lblPaysDarrivee = new JLabel("pays d'arrivée");
		GridBagConstraints gbc_lblPaysDarrivee = new GridBagConstraints();
		gbc_lblPaysDarrivee.anchor = GridBagConstraints.EAST;
		gbc_lblPaysDarrivee.insets = new Insets(0, 0, 5, 5);
		gbc_lblPaysDarrivee.gridx = 1;
		gbc_lblPaysDarrivee.gridy = 6;
		add(lblPaysDarrivee, gbc_lblPaysDarrivee);
		
		textFieldPaysDarrivee = new JTextField();
		GridBagConstraints gbc_textFieldPaysDarrivee = new GridBagConstraints();
		gbc_textFieldPaysDarrivee.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldPaysDarrivee.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldPaysDarrivee.gridx = 2;
		gbc_textFieldPaysDarrivee.gridy = 6;
		add(textFieldPaysDarrivee, gbc_textFieldPaysDarrivee);
		textFieldPaysDarrivee.setColumns(10);
		
		JLabel lblCopilote = new JLabel("copilote");
		GridBagConstraints gbc_lblCopilote = new GridBagConstraints();
		gbc_lblCopilote.anchor = GridBagConstraints.EAST;
		gbc_lblCopilote.insets = new Insets(0, 0, 5, 5);
		gbc_lblCopilote.gridx = 4;
		gbc_lblCopilote.gridy = 6;
		add(lblCopilote, gbc_lblCopilote);
		
		textFieldCopilote = new JTextField();
		GridBagConstraints gbc_textFieldCopilote = new GridBagConstraints();
		gbc_textFieldCopilote.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldCopilote.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldCopilote.gridx = 5;
		gbc_textFieldCopilote.gridy = 6;
		add(textFieldCopilote, gbc_textFieldCopilote);
		textFieldCopilote.setColumns(10);
		
		JLabel lblCodeArriv = new JLabel("code aéroport d'arrivée");
		GridBagConstraints gbc_lblCodeArriv = new GridBagConstraints();
		gbc_lblCodeArriv.anchor = GridBagConstraints.EAST;
		gbc_lblCodeArriv.insets = new Insets(0, 0, 5, 5);
		gbc_lblCodeArriv.gridx = 1;
		gbc_lblCodeArriv.gridy = 7;
		add(lblCodeArriv, gbc_lblCodeArriv);
		
		textFieldCodeArriv = new JTextField();
		GridBagConstraints gbc_textFieldCodeArriv = new GridBagConstraints();
		gbc_textFieldCodeArriv.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldCodeArriv.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldCodeArriv.gridx = 2;
		gbc_textFieldCodeArriv.gridy = 7;
		add(textFieldCodeArriv, gbc_textFieldCodeArriv);
		textFieldCodeArriv.setColumns(10);
		
		JLabel lblHotesseSt1 = new JLabel("hôtesse ou steward N°1");
		GridBagConstraints gbc_lblHotesseSt1 = new GridBagConstraints();
		gbc_lblHotesseSt1.anchor = GridBagConstraints.EAST;
		gbc_lblHotesseSt1.insets = new Insets(0, 0, 5, 5);
		gbc_lblHotesseSt1.gridx = 4;
		gbc_lblHotesseSt1.gridy = 7;
		add(lblHotesseSt1, gbc_lblHotesseSt1);
		
		textFieldHotesseSt1 = new JTextField();
		GridBagConstraints gbc_textFieldHotesseSt1 = new GridBagConstraints();
		gbc_textFieldHotesseSt1.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldHotesseSt1.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldHotesseSt1.gridx = 5;
		gbc_textFieldHotesseSt1.gridy = 7;
		add(textFieldHotesseSt1, gbc_textFieldHotesseSt1);
		textFieldHotesseSt1.setColumns(10);
		
		JLabel lblDateDep = new JLabel("date de départ");
		GridBagConstraints gbc_lblDateDep = new GridBagConstraints();
		gbc_lblDateDep.anchor = GridBagConstraints.EAST;
		gbc_lblDateDep.insets = new Insets(0, 0, 5, 5);
		gbc_lblDateDep.gridx = 1;
		gbc_lblDateDep.gridy = 8;
		add(lblDateDep, gbc_lblDateDep);
		
		textFieldDateDep = new JTextField();
		GridBagConstraints gbc_textFieldDateDep = new GridBagConstraints();
		gbc_textFieldDateDep.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldDateDep.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldDateDep.gridx = 2;
		gbc_textFieldDateDep.gridy = 8;
		add(textFieldDateDep, gbc_textFieldDateDep);
		textFieldDateDep.setColumns(10);
		
		JLabel lblHotesseSt2 = new JLabel("hôtesse ou steward N°2");
		GridBagConstraints gbc_lblHotesseSt2 = new GridBagConstraints();
		gbc_lblHotesseSt2.anchor = GridBagConstraints.EAST;
		gbc_lblHotesseSt2.insets = new Insets(0, 0, 5, 5);
		gbc_lblHotesseSt2.gridx = 4;
		gbc_lblHotesseSt2.gridy = 8;
		add(lblHotesseSt2, gbc_lblHotesseSt2);
		
		textFieldHotesseSt2 = new JTextField();
		GridBagConstraints gbc_textFieldHotesseSt2 = new GridBagConstraints();
		gbc_textFieldHotesseSt2.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldHotesseSt2.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldHotesseSt2.gridx = 5;
		gbc_textFieldHotesseSt2.gridy = 8;
		add(textFieldHotesseSt2, gbc_textFieldHotesseSt2);
		textFieldHotesseSt2.setColumns(10);
		
		JLabel lblHeureDep = new JLabel("heure de départ");
		GridBagConstraints gbc_lblHeureDep = new GridBagConstraints();
		gbc_lblHeureDep.anchor = GridBagConstraints.EAST;
		gbc_lblHeureDep.insets = new Insets(0, 0, 5, 5);
		gbc_lblHeureDep.gridx = 1;
		gbc_lblHeureDep.gridy = 9;
		add(lblHeureDep, gbc_lblHeureDep);
		
		textFieldHeureDep = new JTextField();
		GridBagConstraints gbc_textFieldHeureDep = new GridBagConstraints();
		gbc_textFieldHeureDep.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldHeureDep.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldHeureDep.gridx = 2;
		gbc_textFieldHeureDep.gridy = 9;
		add(textFieldHeureDep, gbc_textFieldHeureDep);
		textFieldHeureDep.setColumns(10);
		
		JLabel lblHotesseSt3 = new JLabel("hôtesse ou steward N°3");
		GridBagConstraints gbc_lblHotesseSt3 = new GridBagConstraints();
		gbc_lblHotesseSt3.anchor = GridBagConstraints.EAST;
		gbc_lblHotesseSt3.insets = new Insets(0, 0, 5, 5);
		gbc_lblHotesseSt3.gridx = 4;
		gbc_lblHotesseSt3.gridy = 9;
		add(lblHotesseSt3, gbc_lblHotesseSt3);
		
		textFieldHotesseSt3 = new JTextField();
		GridBagConstraints gbc_textFieldHotesseSt3 = new GridBagConstraints();
		gbc_textFieldHotesseSt3.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldHotesseSt3.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldHotesseSt3.gridx = 5;
		gbc_textFieldHotesseSt3.gridy = 9;
		add(textFieldHotesseSt3, gbc_textFieldHotesseSt3);
		textFieldHotesseSt3.setColumns(10);
		
		btnMettreAJour = new JButton("mettre à jour");
		GridBagConstraints gbc_btnMettreAJour = new GridBagConstraints();
		gbc_btnMettreAJour.anchor = GridBagConstraints.WEST;
		gbc_btnMettreAJour.insets = new Insets(0, 0, 5, 5);
		gbc_btnMettreAJour.gridx = 5;
		gbc_btnMettreAJour.gridy = 10;
		add(btnMettreAJour, gbc_btnMettreAJour);
		
		btnReinitialiser = new JButton("réinitialiser");
		GridBagConstraints gbc_btnReinitialiser = new GridBagConstraints();
		gbc_btnReinitialiser.anchor = GridBagConstraints.WEST;
		gbc_btnReinitialiser.insets = new Insets(0, 0, 5, 0);
		gbc_btnReinitialiser.gridx = 6;
		gbc_btnReinitialiser.gridy = 10;
		add(btnReinitialiser, gbc_btnReinitialiser);
		
		btnSupprimer = new JButton("supprimer");
		btnSupprimer.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnSupprimer.setBackground(Color.PINK);
		btnSupprimer.setForeground(Color.RED);
		GridBagConstraints gbc_btnSupprimer = new GridBagConstraints();
		gbc_btnSupprimer.anchor = GridBagConstraints.WEST;
		gbc_btnSupprimer.insets = new Insets(0, 0, 5, 5);
		gbc_btnSupprimer.gridx = 5;
		gbc_btnSupprimer.gridy = 11;
		add(btnSupprimer, gbc_btnSupprimer);

	}

}

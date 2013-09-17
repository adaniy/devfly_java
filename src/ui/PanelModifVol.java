package ui;

import javax.swing.JPanel;

import java.awt.GridBagLayout;
import java.awt.Point;

import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.table.TableModel;
import javax.swing.*;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.SwingConstants;

import dao.MysqlDao;
import model.Aeroport;
import model.Vol;

import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@SuppressWarnings("serial")
public class PanelModifVol extends JPanel {
	private JTextField textFieldNdeVol;
	@SuppressWarnings("rawtypes")
	private JComboBox comboBoxVilleDeDepart;
	private JTextField textFieldPaysDeDepart;
	private JTextField textFieldCodeDep;
	@SuppressWarnings("rawtypes")
	private JComboBox comboBoxVilleDarrivee;
	private JTextField textFieldPaysDarrivee;
	private JTextField textFieldCodeArriv;
	private JTextField textFieldDateDep;
	private JTextField textFieldHeureDep;
	private JTextField textFieldDateArriv;
	private JTextField textFieldHeureArriv;
	private JTextField textFieldDuree;
	private JTextField textFieldTarif;
	@SuppressWarnings("rawtypes")
	private JComboBox comboBoxPilote;
	@SuppressWarnings("rawtypes")
	private JComboBox comboBoxCopilote;
	@SuppressWarnings("rawtypes")
	private JComboBox comboBoxHotesseSt1;
	@SuppressWarnings("rawtypes")
	private JComboBox comboBoxHotesseSt2;
	@SuppressWarnings("rawtypes")
	private JComboBox comboBoxHotesseSt3;
	private JLabel lblMessage;
	private JButton btnMettreAJour;
	private JButton btnReinitialiser;
	private JButton btnSupprimer;
	private MysqlDao dao = new MysqlDao();
	
	@SuppressWarnings("rawtypes")
	public JComboBox getComboBoxVilleDeDepart() {
		return comboBoxVilleDeDepart;
	}

	@SuppressWarnings("rawtypes")
	public JComboBox getComboBoxVilleDarrivee() {
		return comboBoxVilleDarrivee;
	}

	@SuppressWarnings("rawtypes")
	public JComboBox getComboBoxPilote() {
		return comboBoxPilote;
	}

	@SuppressWarnings("rawtypes")
	public JComboBox getComboBoxCopilote() {
		return comboBoxCopilote;
	}

	@SuppressWarnings("rawtypes")
	public JComboBox getComboBoxHotesseSt1() {
		return comboBoxHotesseSt1;
	}

	@SuppressWarnings("rawtypes")
	public JComboBox getComboBoxHotesseSt2() {
		return comboBoxHotesseSt2;
	}

	@SuppressWarnings("rawtypes")
	public JComboBox getComboBoxHotesseSt3() {
		return comboBoxHotesseSt3;
	}

	public JButton getBtnMettreAJour() {
		return btnMettreAJour;
	}

	public JButton getBtnReinitialiser() {
		return btnReinitialiser;
	}

	public JButton getBtnSupprimer() {
		return btnSupprimer;
	}
	
	public JTextField getTextFieldNdeVol() {
		return textFieldNdeVol;
	}

	@SuppressWarnings("rawtypes")
	public JComboBox getJComboBoxVilleDeDepart() {
		return comboBoxVilleDeDepart;
	}

	public JTextField getTextFieldPaysDeDepart() {
		return textFieldPaysDeDepart;
	}

	public JTextField getTextFieldCodeDep() {
		return textFieldCodeDep;
	}

	@SuppressWarnings("rawtypes")
	public JComboBox getJComboBoxVilleDarrivee() {
		return comboBoxVilleDarrivee;
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

	public JLabel getLblMessage() {
		return lblMessage;
	}

	/**
	 * Create the panel.
	 * @throws SQLException 
	 */
	@SuppressWarnings("rawtypes")
	public PanelModifVol() throws SQLException {
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

		// Les données dans la combobox vont provenir des données en base.
		// On récupère les villes proposées par la compagnie
		String[] villes = null;
		try {
			 villes = Aeroport.getVillesProposees();
		}
		catch(SQLException e) {
			getLblMessage().setText(e.getMessage());
		}
		
		comboBoxVilleDeDepart = new JComboBox();
		// pour créer la comboxBox avec les villes prévues par la compagnie :
		Vol.comboBoxCreation(villes, comboBoxVilleDeDepart);
		comboBoxVilleDeDepart.addActionListener (new ActionListener () {
			// lorsqu'une ville est sélectionnée dans la combobox, le code et le pays affichés changent en fonction
			public void actionPerformed(ActionEvent e) {
				MysqlDao dao = new MysqlDao();
				try {
					// on récupère le pays + le code associés à la ville récupérée
					Aeroport a = dao.getAeroportByVille( 
						(String) PanelModifVol.this
						.getJComboBoxVilleDeDepart()
						.getSelectedItem() // renvoie un objet qu'on caste en String
					);
					// on place la ville récupérée dans le textField
					PanelModifVol.this.getTextFieldPaysDeDepart().setText(a.getPays());

					// on place le code récupéré dans le textField
					PanelModifVol.this.getTextFieldCodeDep().setText(a.getCodeAeroport());
				}
				catch(SQLException ex) {
					getLblMessage().setText(ex.getMessage());
				}
			}
		});

		GridBagConstraints gbc_comboBoxVilleDeDepart = new GridBagConstraints();
		gbc_comboBoxVilleDeDepart.insets = new Insets(0, 0, 5, 5);
		gbc_comboBoxVilleDeDepart.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxVilleDeDepart.gridx = 2;
		gbc_comboBoxVilleDeDepart.gridy = 2;
		add(comboBoxVilleDeDepart, gbc_comboBoxVilleDeDepart);

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
		textFieldPaysDeDepart.setEditable(false); // on empêche la modification du pays
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
		textFieldCodeDep.setEditable(false); // on empêche la modification du code aéroport
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

		comboBoxVilleDarrivee = new JComboBox();
		// pour créer la comboxBox avec les villes prévues par la compagnie :
		Vol.comboBoxCreation(villes, comboBoxVilleDarrivee);
		comboBoxVilleDarrivee.addActionListener (new ActionListener () {
			// lorsqu'une ville est sélectionnée dans la combobox, le code et le pays affichés changent en fonction
			public void actionPerformed(ActionEvent e) {
				MysqlDao dao = new MysqlDao();
				try {
					// on récupère le pays + le code associés à la ville récupérée
					Aeroport a = dao.getAeroportByVille( 
						(String) PanelModifVol.this
						.getJComboBoxVilleDarrivee()
						.getSelectedItem() // renvoie un objet qu'on caste en String
						);
					
					// on place la ville récupérée dans le textField
					PanelModifVol.this.getTextFieldPaysDarrivee().setText(a.getPays());

					// on place le code récupéré dans le textField
					PanelModifVol.this.getTextFieldCodeArriv().setText(a.getCodeAeroport());
				}
				catch(SQLException ex) {
					getLblMessage().setText(ex.getMessage());
				}
			}
		});

		GridBagConstraints gbc_comboBoxVilleDarrivee = new GridBagConstraints();
		gbc_comboBoxVilleDarrivee.insets = new Insets(0, 0, 5, 5);
		gbc_comboBoxVilleDarrivee.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxVilleDarrivee.gridx = 2;
		gbc_comboBoxVilleDarrivee.gridy = 5;
		add(comboBoxVilleDarrivee, gbc_comboBoxVilleDarrivee);

		JLabel lblPilote = new JLabel("pilote");
		GridBagConstraints gbc_lblPilote = new GridBagConstraints();
		gbc_lblPilote.anchor = GridBagConstraints.EAST;
		gbc_lblPilote.insets = new Insets(0, 0, 5, 5);
		gbc_lblPilote.gridx = 4;
		gbc_lblPilote.gridy = 5;
		add(lblPilote, gbc_lblPilote);

		comboBoxPilote = new JComboBox<>();
		// pour créer la comboxBox avec les pilotes de la compagnie :
		List<String> listePilotes = dao.getPilotes();
		listePilotes.add(0,"Choisissez un employé"); // on rajoute cette mention en première position dans la liste
		// on transforme la liste en tableau de String
		String[]pilotes = getTableauEmployes(listePilotes);
		Vol.comboBoxCreation(pilotes, comboBoxPilote);
		GridBagConstraints gbc_comboBoxPilote = new GridBagConstraints();
		gbc_comboBoxPilote.insets = new Insets(0, 0, 5, 5);
		gbc_comboBoxPilote.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxPilote.gridx = 5;
		gbc_comboBoxPilote.gridy = 5;
		add(comboBoxPilote, gbc_comboBoxPilote);

		JLabel lblPaysDarrivee = new JLabel("pays d'arrivée");
		GridBagConstraints gbc_lblPaysDarrivee = new GridBagConstraints();
		gbc_lblPaysDarrivee.anchor = GridBagConstraints.EAST;
		gbc_lblPaysDarrivee.insets = new Insets(0, 0, 5, 5);
		gbc_lblPaysDarrivee.gridx = 1;
		gbc_lblPaysDarrivee.gridy = 6;
		add(lblPaysDarrivee, gbc_lblPaysDarrivee);

		textFieldPaysDarrivee = new JTextField();
		textFieldPaysDarrivee.setEditable(false); // on empêche la modification du pays
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

		comboBoxCopilote = new JComboBox<>();
		// pour créer la comboxBox avec les copilotes de la compagnie :
		List<String> listeCopilotes = dao.getCopilotes();
		listeCopilotes.add(0,"Choisissez un employé"); // on rajoute cette mention en première position dans la liste
		// on transforme la liste en tableau de String
		String[]copilotes = getTableauEmployes(listeCopilotes);
		Vol.comboBoxCreation(copilotes, comboBoxCopilote);
		GridBagConstraints gbc_comboBoxCopilote = new GridBagConstraints();
		gbc_comboBoxCopilote.insets = new Insets(0, 0, 5, 5);
		gbc_comboBoxCopilote.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxCopilote.gridx = 5;
		gbc_comboBoxCopilote.gridy = 6;
		add(comboBoxCopilote, gbc_comboBoxCopilote);

		JLabel lblCodeArriv = new JLabel("code aéroport d'arrivée");
		GridBagConstraints gbc_lblCodeArriv = new GridBagConstraints();
		gbc_lblCodeArriv.anchor = GridBagConstraints.EAST;
		gbc_lblCodeArriv.insets = new Insets(0, 0, 5, 5);
		gbc_lblCodeArriv.gridx = 1;
		gbc_lblCodeArriv.gridy = 7;
		add(lblCodeArriv, gbc_lblCodeArriv);

		textFieldCodeArriv = new JTextField();
		textFieldCodeArriv.setEditable(false); // on empêche la modification du code aéroport
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

		comboBoxHotesseSt1 = new JComboBox<>();
		// pour créer la comboxBox avec les hôtesses/stewards de la compagnie :
		List<String> listeHotessesSt = dao.getHotessesSt();
		listeHotessesSt.add(0,"Choisissez un employé"); // on rajoute cette mention en première position dans la liste
		// on transforme la liste en tableau de String
		String[]hotessesSt = getTableauEmployes(listeHotessesSt);
		Vol.comboBoxCreation(hotessesSt, comboBoxHotesseSt1);
		GridBagConstraints gbc_comboBoxHotesseSt1 = new GridBagConstraints();
		gbc_comboBoxHotesseSt1.insets = new Insets(0, 0, 5, 5);
		gbc_comboBoxHotesseSt1.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxHotesseSt1.gridx = 5;
		gbc_comboBoxHotesseSt1.gridy = 7;
		add(comboBoxHotesseSt1, gbc_comboBoxHotesseSt1);

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

		comboBoxHotesseSt2 = new JComboBox<>();
		// On utilise les mêmes données que pour la comboBoxHotesseSt1
		Vol.comboBoxCreation(hotessesSt, comboBoxHotesseSt2);
		GridBagConstraints gbc_comboBoxHotesseSt2 = new GridBagConstraints();
		gbc_comboBoxHotesseSt2.insets = new Insets(0, 0, 5, 5);
		gbc_comboBoxHotesseSt2.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxHotesseSt2.gridx = 5;
		gbc_comboBoxHotesseSt2.gridy = 8;
		add(comboBoxHotesseSt2, gbc_comboBoxHotesseSt2);

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

		comboBoxHotesseSt3 = new JComboBox<>();
		// On utilise les mêmes données que pour la comboBoxHotesseSt1
		Vol.comboBoxCreation(hotessesSt, comboBoxHotesseSt3);
		GridBagConstraints gbc_comboBoxHotesseSt3 = new GridBagConstraints();
		gbc_comboBoxHotesseSt3.insets = new Insets(0, 0, 5, 5);
		gbc_comboBoxHotesseSt3.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxHotesseSt3.gridx = 5;
		gbc_comboBoxHotesseSt3.gridy = 9;
		add(comboBoxHotesseSt3, gbc_comboBoxHotesseSt3);

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

	// au clic, remplit le formulaire avec les données du tableau
	// prend en paramètre le panel de type PanelModifVol, le pointeur, et la JTable
	public static void fillInForm(PanelModifVol panelModifVol, Point p, JTable tableVols){
		// on récupère l'endroit où a eu lieu l'événement (= le clic)
		int row = tableVols.rowAtPoint(p); // renvoie la ligne sous le pointeur
		// On convertit les row du tableau en row du modèle pour maintenir la cohérence 
		// entre les cellules de la présentation et les cellules du model (source de données)
		int modelRow = tableVols.convertRowIndexToModel(row);
		TableModel model = tableVols.getModel();
		String numVol = (String) model.getValueAt(modelRow, 0); // String qui représente la valeur récupérée
		String villeDep = (String) model.getValueAt(modelRow, 1);
		String paysDep = (String) model.getValueAt(modelRow, 2);
		String codeDep = (String) model.getValueAt(modelRow, 3);
		String villeArriv = (String) model.getValueAt(modelRow, 4);
		String paysArriv = (String) model.getValueAt(modelRow, 5);
		String codeArriv = (String) model.getValueAt(modelRow, 6);
		String dateHeureDep = (String) model.getValueAt(modelRow, 7);
		String dateHeureArriv = (String) model.getValueAt(modelRow, 8);
		int duree = (int) model.getValueAt(modelRow, 9);
		String tarif = (String) model.getValueAt(modelRow, 10);
		String pilote = (String) model.getValueAt(modelRow, 11);
		String copilote = (String) model.getValueAt(modelRow, 12);
		String hotesseSt1 = (String) model.getValueAt(modelRow, 13);
		String hotesseSt2 = (String) model.getValueAt(modelRow, 14);
		String hotesseSt3 = (String) model.getValueAt(modelRow, 15);

		// On place les valeurs récupérées dans les champs du formulaire
		panelModifVol.getTextFieldNdeVol().setText(numVol);
		panelModifVol.getTextFieldPaysDeDepart().setText(paysDep);
		panelModifVol.getTextFieldCodeDep().setText(codeDep);
		panelModifVol.getTextFieldPaysDarrivee().setText(paysArriv);
		panelModifVol.getTextFieldCodeArriv().setText(codeArriv);
		// pour avoir la date uniquement, on récupère la sous-chaîne correspondante
		panelModifVol.getTextFieldDateDep().setText(dateHeureDep.substring(0,10));
		// pour avoir l'heure uniquement, on commence à l'index 13
		panelModifVol.getTextFieldHeureDep().setText(dateHeureDep.substring(13));
		// pour avoir la date uniquement, on récupère la sous-chaîne correspondante
		panelModifVol.getTextFieldDateArriv().setText(dateHeureArriv.substring(0,10));
		// pour avoir l'heure uniquement, on commence à l'index 13
		panelModifVol.getTextFieldHeureArriv().setText(dateHeureArriv.substring(13));
		// pour la durée, on passe la valeur de l'entier en chaîne de caractères
		panelModifVol.getTextFieldDuree().setText(String.valueOf(duree));
		panelModifVol.getTextFieldTarif().setText(tarif);
		// pour les comboBoxes :
		panelModifVol.getJComboBoxVilleDeDepart().setSelectedItem(villeDep);
		panelModifVol.getJComboBoxVilleDarrivee().setSelectedItem(villeArriv); 
		panelModifVol.getComboBoxPilote().setSelectedItem(pilote);
		panelModifVol.getComboBoxCopilote().setSelectedItem(copilote);
		panelModifVol.getComboBoxHotesseSt1().setSelectedItem(hotesseSt1);
		panelModifVol.getComboBoxHotesseSt2().setSelectedItem(hotesseSt2);
		panelModifVol.getComboBoxHotesseSt3().setSelectedItem(hotesseSt3);
	}
	
	// au clic, réinitialise les champs du formulaire de modification d'un vol
	// prend en paramètre le panel de type PanelModifVol dont on doit réinitialiser les champs,
	// et un objet Vol dont on obtient les données à remettre dans le formulaire
	public static void formReset(PanelModifVol panelModifVol, Vol v){
		// pour la comboBox ville de départ :
		panelModifVol.getJComboBoxVilleDeDepart().setSelectedItem(v.getAeroportDepart().getVille());
		panelModifVol.getTextFieldPaysDeDepart().setText(v.getAeroportDepart().getPays());
		panelModifVol.getTextFieldCodeDep().setText(v.getAeroportDepart().getCodeAeroport());
		// pour la comboBox ville d'arrivée :
		panelModifVol.getJComboBoxVilleDarrivee().setSelectedItem(v.getAeroportArrivee().getVille());
		panelModifVol.getTextFieldPaysDarrivee().setText(v.getAeroportArrivee().getPays());
		panelModifVol.getTextFieldCodeArriv().setText(v.getAeroportArrivee().getCodeAeroport());
		
		Date dateHeureDep = v.getDateHeureDepart();
		Date dateHeureArriv = v.getDateHeureArrivee();
		// On passe les dates récupérées en chaînes de caractères
		String dateDepStr = new SimpleDateFormat("dd/MM/yyyy").format(dateHeureDep);
		String heureDepStr = new SimpleDateFormat("HH:mm").format(dateHeureDep);
		String dateArrivStr = new SimpleDateFormat("dd/MM/yyyy").format(dateHeureArriv);
		String heureArrivStr = new SimpleDateFormat("HH:mm").format(dateHeureArriv);
		
		panelModifVol.getTextFieldDateDep().setText(dateDepStr);
		panelModifVol.getTextFieldHeureDep().setText(heureDepStr);
		panelModifVol.getTextFieldDateArriv().setText(dateArrivStr);
		panelModifVol.getTextFieldHeureArriv().setText(heureArrivStr);
		
		// pour la durée, on passe la valeur de l'entier en chaîne de caractères
		panelModifVol.getTextFieldDuree().setText(String.valueOf(v.getDuree()));
		
		// Pour le tarif, on passe la valeur du float en chaîne de caractères
		// On utilise NumberFormat pour demander explicitement 2 chiffres après la virgule
		NumberFormat formatter = new DecimalFormat("#0.00");
		String tarifStr = formatter.format(v.getTarif());
		panelModifVol.getTextFieldTarif().setText(tarifStr);
		
		// si il n'y avait pas d'employés enregistrés, on revient sur "Choisissez un employé"
		if(v.getCodePilote().isEmpty()){
			panelModifVol.getComboBoxPilote().setSelectedItem("Choisissez un employé");
		}else{
			panelModifVol.getComboBoxPilote().setSelectedItem(v.getCodePilote());
		}
		if(v.getCodeCopilote().isEmpty()){
			panelModifVol.getComboBoxCopilote().setSelectedItem("Choisissez un employé");
		}else{
			panelModifVol.getComboBoxCopilote().setSelectedItem(v.getCodeCopilote());
		}
		if(v.getCodeHotesseSt1().isEmpty()){
			panelModifVol.getComboBoxHotesseSt1().setSelectedItem("Choisissez un employé");
		}else{
			panelModifVol.getComboBoxHotesseSt1().setSelectedItem(v.getCodeHotesseSt1());
		}
		if(v.getCodeHotesseSt2().isEmpty()){
			panelModifVol.getComboBoxHotesseSt2().setSelectedItem("Choisissez un employé");
		}else{
			panelModifVol.getComboBoxHotesseSt2().setSelectedItem(v.getCodeHotesseSt2());
		}
		if(v.getCodeHotesseSt3().isEmpty()){
			panelModifVol.getComboBoxHotesseSt3().setSelectedItem("Choisissez un employé");
		}else{
			panelModifVol.getComboBoxHotesseSt3().setSelectedItem(v.getCodeHotesseSt3());
		}
	}
	
	// méthode appelée après la mise à jour ou la suppression d'un vol
	// prend en paramètre une liste de vols (cf vols en attente ou vols programmés)
	// et la JTable dans laquelle on affiche les vols
	public void rafraichirDonnees(List<Vol> listeVols, JTable maJTable) throws SQLException{
		// On vide les champs du formulaire, et on réinitialise les comboBoxes :
		textFieldNdeVol.setText("");
		textFieldPaysDeDepart.setText("");
		textFieldCodeDep.setText("");
		textFieldPaysDarrivee.setText("");
		textFieldCodeArriv.setText("");
		textFieldDateDep.setText("");
		textFieldHeureDep.setText("");
		textFieldDateArriv.setText("");
		textFieldHeureArriv.setText("");
		textFieldDuree.setText("");
		textFieldTarif.setText("");
		comboBoxPilote.setSelectedItem("Choisissez un employé");
		comboBoxCopilote.setSelectedItem("Choisissez un employé");
		comboBoxHotesseSt1.setSelectedItem("Choisissez un employé");
		comboBoxHotesseSt2.setSelectedItem("Choisissez un employé");
		comboBoxHotesseSt3.setSelectedItem("Choisissez un employé");
		
		// On récupère les villes proposées par la compagnie pour les comboboxes
		String[] villes = null;
		try {
			 villes = Aeroport.getVillesProposees();
		}
		catch(SQLException e) {
			getLblMessage().setText(e.getMessage());
		}
		Vol.comboBoxCreation(villes, comboBoxVilleDeDepart);
		Vol.comboBoxCreation(villes, comboBoxVilleDarrivee);
		
		// On crée le model avec les bonnes données et on le donne à la JTable
		// On utilise pour cela la méthode statique définie dans Vol
		Vol.TableCreation(listeVols, maJTable);
	}
	
	// transforme une liste de numéros d'employés de la compagnie
	// en un tableau de chaînes de caractères
	private static String[] getTableauEmployes(List<String> listeEmployes) throws SQLException{
		// On initialise un tableau de chaînes de caractères de la taille
		// de la liste, on y placera les code employés.
		String[]tableau = new String[listeEmployes.size()];
		
		// On parcourt la liste :
		for(int i = 0 ; i < listeEmployes.size(); i++){
			// On place les codes employés dans le tableau au fur et à mesure
			tableau[i] = listeEmployes.get(i);
		}
		
		return tableau;
	}
}

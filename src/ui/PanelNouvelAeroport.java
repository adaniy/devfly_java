package ui;

import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.SwingUtilities;

import java.awt.GridBagLayout;

import javax.swing.JLabel;

import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.JTextField;

import model.Aeroport;
import dao.MysqlDao;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.util.List;
import java.awt.Color;

@SuppressWarnings("serial")
public class PanelNouvelAeroport extends JPanel {
	private JTextField textFieldVille;
	private JTextField textFieldPays;
	private JTextField textFieldAita;
	private JLabel labelMessage;
	private PanelValiderAnnuler panelValiderAnnuler;
	private MysqlDao dao = new MysqlDao();

	public JTextField getTextFieldVille() {
		return textFieldVille;
	}

	public void setTextFieldVille(JTextField textFieldVille) {
		this.textFieldVille = textFieldVille;
	}

	public JTextField getTextFieldPays() {
		return textFieldPays;
	}

	public JLabel getLabelMessage() {
		return labelMessage;
	}

	public void setLabelMessage(JLabel labelMessage) {
		this.labelMessage = labelMessage;
	}

	public void setTextFieldPays(JTextField textFieldPays) {
		this.textFieldPays = textFieldPays;
	}

	public JTextField getTextFieldAita() {
		return textFieldAita;
	}

	public void setTextFieldAita(JTextField textFieldAita) {
		this.textFieldAita = textFieldAita;
	}

	/**
	 * Create the panel.
	 */
	public PanelNouvelAeroport() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		// on joue sur les dimensions de la grille pour positionner les éléments :
		gridBagLayout.columnWidths = new int[]{365, 0, 145, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 55, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		// Label qui pourra contenir les différents messages à afficher :
		labelMessage = new JLabel("");
		labelMessage.setForeground(Color.RED);
		GridBagConstraints gbc_labelMessage = new GridBagConstraints();
		gbc_labelMessage.insets = new Insets(0, 0, 5, 0);
		gbc_labelMessage.gridx = 2;
		gbc_labelMessage.gridy = 0;
		add(labelMessage, gbc_labelMessage);
		
		// Le titre de la page :
		JLabel lblNouvelleDestination = new JLabel("Nouvel aéroport");
		lblNouvelleDestination.setFont(new Font("Dialog", Font.BOLD, 18));
		GridBagConstraints gbc_lblNouvelleDestination = new GridBagConstraints();
		gbc_lblNouvelleDestination.gridwidth = 2;
		gbc_lblNouvelleDestination.insets = new Insets(0, 0, 5, 0);
		gbc_lblNouvelleDestination.gridx = 1;
		gbc_lblNouvelleDestination.gridy = 2;
		add(lblNouvelleDestination, gbc_lblNouvelleDestination);
		
		// Les différents éléments du formulaires :
		
		JLabel lblCodeAita = new JLabel("Code AITA");
		GridBagConstraints gbc_lblCodeAita = new GridBagConstraints();
		gbc_lblCodeAita.anchor = GridBagConstraints.EAST;
		gbc_lblCodeAita.insets = new Insets(0, 0, 5, 5);
		gbc_lblCodeAita.gridx = 1;
		gbc_lblCodeAita.gridy = 3;
		add(lblCodeAita, gbc_lblCodeAita);
		
		textFieldAita = new JTextField();
		GridBagConstraints gbc_textFieldAita = new GridBagConstraints();
		gbc_textFieldAita.anchor = GridBagConstraints.WEST;
		gbc_textFieldAita.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldAita.gridx = 2;
		gbc_textFieldAita.gridy = 3;
		add(textFieldAita, gbc_textFieldAita);
		textFieldAita.setColumns(10);
		
		JLabel lblVille = new JLabel("Ville");
		GridBagConstraints gbc_lblVille = new GridBagConstraints();
		gbc_lblVille.anchor = GridBagConstraints.EAST;
		gbc_lblVille.insets = new Insets(0, 0, 5, 5);
		gbc_lblVille.gridx = 1;
		gbc_lblVille.gridy = 4;
		add(lblVille, gbc_lblVille);
		
		textFieldVille = new JTextField();
		GridBagConstraints gbc_textFieldVille = new GridBagConstraints();
		gbc_textFieldVille.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldVille.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldVille.gridx = 2;
		gbc_textFieldVille.gridy = 4;
		add(textFieldVille, gbc_textFieldVille);
		textFieldVille.setColumns(10);
		
		JLabel lblPays = new JLabel("Pays");
		GridBagConstraints gbc_lblPays = new GridBagConstraints();
		gbc_lblPays.anchor = GridBagConstraints.EAST;
		gbc_lblPays.insets = new Insets(0, 0, 5, 5);
		gbc_lblPays.gridx = 1;
		gbc_lblPays.gridy = 5;
		add(lblPays, gbc_lblPays);
		
		textFieldPays = new JTextField();
		GridBagConstraints gbc_textFieldPays = new GridBagConstraints();
		gbc_textFieldPays.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldPays.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldPays.gridx = 2;
		gbc_textFieldPays.gridy = 5;
		add(textFieldPays, gbc_textFieldPays);
		textFieldPays.setColumns(10);
		
		panelValiderAnnuler = new PanelValiderAnnuler();
		panelValiderAnnuler.getBtnAnnuler().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Un clic sur le bouton "annuler" efface les éléments saisis.
				getTextFieldAita().setText("");
				getTextFieldVille().setText("");
				getTextFieldPays().setText("");
			}
		});
		
		panelValiderAnnuler.getBtnValider().addActionListener(new ActionListener() {
			@SuppressWarnings("rawtypes")
			public void actionPerformed(ActionEvent arg0) {
				// au clic sur le bouton "valider", on ajoute un aéroport en base.
				
				// On réinitialise le message éventuellement inscrit :
				getLabelMessage().setText("");
				
				// On récupère les données saisies
				// Le code aéroport est passé en majuscules
				String codeAita = getTextFieldAita().getText().toUpperCase();
				String ville = getTextFieldVille().getText();
				String pays = getTextFieldPays().getText();
				
				// TODO : page à optimiser ?
				
				// On définit des expressions régulières.
				String regexLettresAccentsTirets = "^[A-Za-zàâäéèêëìîïôöòùûüçÀÂÄÉÈËÏÎÌÔÖÙÛÜÇ-]+$";
				String regexLettres = "^[A-Z]+$";
				
				// On vérifie que les 3 champs ne sont pas vides,
				// qu'ils contiennent des lettres (accentuées ou non) ou tirets,
				// et que le code aéroport est composé de 3 lettres
				// (on utilise les regex).
				if(codeAita.length() == 3 && ville.length()!=0 && pays.length()!=0
						&& codeAita.matches(regexLettres) && ville.matches(regexLettresAccentsTirets)
						&& pays.matches(regexLettresAccentsTirets)){
					// On met la première lettre de la ville et du pays en majuscule
					// (uniformisé + permet que le tri des villes par ordre alphabétique soit correct dans le formulaire de création d'un vol)
					String villeBonFormat = UpperFirstLetter(ville);
					String paysBonFormat = UpperFirstLetter(pays);
					
					// On crée un objet Aeroport avec ces données
					Aeroport nouvelAeroport = new Aeroport(codeAita, villeBonFormat, paysBonFormat);
					
					// On ajoute l'aéroport à la base
					try {
						boolean ajout = dao.addNewAeroport(nouvelAeroport);
						
						// On vérifie que l'ajout s'est bien passé
						// et on affiche un message en conséquence.
						if(ajout){
							getLabelMessage().setText("L'aéroport " + codeAita + " a bien été ajouté !");
							// On vide les champs texte :
							getTextFieldAita().setText("");
							getTextFieldVille().setText("");
							getTextFieldPays().setText("");
							
							// on va recharger la liste des aéroports pour que le nouvel aéroport apparaisse dans la section "aéroports"
							// On récupère les aéroports
							List<Aeroport> aeroports = dao.getAllAeroports();
							// on récupère la frame principale
							FenetrePrincipale frame = (FenetrePrincipale) SwingUtilities.getRoot(PanelNouvelAeroport.this);
							// on récupère la JTable
							JTable table = frame.getPanelAeroports().getTableAeroports();
							// On crée le model avec les bonnes données et on le donne à la JTable
							// On utilise pour cela la méthode statique définie dans Aeroport
							Aeroport.TableCreation(aeroports, table);
							
							// on va également recharger la liste des villes proposées dans le formulaire de création d'un vol
							String[]villes = Aeroport.getVillesProposees();
							// On insère les villes dans les comboBox
							JComboBox comboBoxDepart = frame.getPanelNouveauVol().getComboBoxVilleDeDepart();
							JComboBox comboBoxArrivee = frame.getPanelNouveauVol().getComboBoxVilleDarrivee();
							Aeroport.comboBoxCreation(villes, comboBoxDepart);
							Aeroport.comboBoxCreation(villes, comboBoxArrivee);
							
						}else{
							getLabelMessage().setText("Le code aéroport " + codeAita + " existe déjà.");
						}
						
					} catch (SQLException e) {
						getLabelMessage().setText(e.getMessage());
					}
				}else{
					// si tous les champs ne sont pas ok, on affiche un
					// message (et on ne vide pas les champs !)
					getLabelMessage().setText("<html><p>Attention, veuillez vous assurer que :<br>"
							+ "- tous les champs sont remplis<br>"
							+ "- la ville et le pays sont constitués de lettres<br>"
							+ "- le code aéroport contient 3 lettres.</p></html>");
				}
			}
		});
		GridBagConstraints gbc_panelValiderAnnuler = new GridBagConstraints();
		gbc_panelValiderAnnuler.insets = new Insets(0, 0, 5, 0);
		gbc_panelValiderAnnuler.fill = GridBagConstraints.BOTH;
		gbc_panelValiderAnnuler.gridx = 2;
		gbc_panelValiderAnnuler.gridy = 8;
		add(panelValiderAnnuler, gbc_panelValiderAnnuler);

	}

	// passe la première lettre de la chaîne en majuscules
	private String UpperFirstLetter(String chaine){
		char[] charTable = chaine.toCharArray(); // on récupère un tableau de caractères
		charTable[0] = Character.toUpperCase(charTable[0]); // on passe le premier en majuscule
		return new String(charTable); // on construit 1 String à partir du tableau
	}
}

package gui;

import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import gui.LoginForm;
import bl.ProdusManager;
import models.Produs;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AdminForm {

	public JFrame frame;
	private JButton btnNewButton;
	private JComboBox<Produs> comboBox;
	private JButton btnStergeProdus;
	private JButton btnEditareProdus;
	private JButton btnNewButton_1;
	private JLabel background;
	private ProdusManager produsManager = new ProdusManager();
	private JButton btnExportCsv;
	private JButton btnExportJson;

	/**
	 * Create the application.
	 */
	public AdminForm() {
		initialize();
		initActions();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 650, 517);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		btnNewButton = new JButton("Produs Nou");
		btnNewButton.setBounds(122, 38, 131, 41);
		frame.getContentPane().add(btnNewButton);
		
		comboBox = new JComboBox<Produs>();
		comboBox.setBounds(86, 285, 453, 41);
		addToCombobox();
		frame.getContentPane().add(comboBox);
		
		btnStergeProdus = new JButton("Sterge Produs");
		btnStergeProdus.setBounds(122, 123, 131, 41);
		frame.getContentPane().add(btnStergeProdus);
		
		btnEditareProdus = new JButton("Editare Produs");
		btnEditareProdus.setBounds(122, 211, 131, 41);
		frame.getContentPane().add(btnEditareProdus);
		
		btnNewButton_1 = new JButton("Log out");
		btnNewButton_1.setBounds(408, 401, 131, 41);
		frame.getContentPane().add(btnNewButton_1);
		
		btnExportCsv = new JButton("Export Csv");
		btnExportCsv.setBounds(342, 73, 131, 41);
		frame.getContentPane().add(btnExportCsv);
		
		btnExportJson = new JButton("Export Json");
		btnExportJson.setBounds(342, 168, 131, 41);
		frame.getContentPane().add(btnExportJson);
		
		background = new JLabel(new ImageIcon("cioco.png"));
		background.setBounds(0, 0, 652, 520);
		frame.getContentPane().add(background);
		
		frame.repaint();
	}
	
	private void initActions() {
		// TODO Auto-generated method stub
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JTextField nume = new JTextField();
				JTextField pret = new JTextField();
				JTextField stoc = new JTextField();
				
				Object[] message = {
					    "Nume:", nume,
					    "Pret:", pret,
					    "Stoc:", stoc
					  };
					int option = JOptionPane.showConfirmDialog(null, message, "Date", JOptionPane.OK_CANCEL_OPTION);
					if (option == JOptionPane.OK_OPTION) {
					Produs produs = new Produs();
					produs.setNume(nume.getText());
					produs.setPret(Integer.valueOf(pret.getText()));
					produs.setStoc(Integer.valueOf(stoc.getText()));
					produsManager.addProdus(produs);
					addToCombobox();
					}
			}
		});
		
		btnStergeProdus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Produs produsSelectat = (Produs) comboBox.getSelectedItem();
				produsManager.deleteProdus(produsSelectat);
				addToCombobox();
			}
		});
		
		btnEditareProdus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JTextField nume = new JTextField();
				nume.setEditable(false);
				JTextField pret = new JTextField();
				pret.setEditable(false);
				JTextField stoc = new JTextField();
				Produs produsSelectat = (Produs) comboBox.getSelectedItem();
				nume.setText(produsSelectat.getNume());
				pret.setText(String.valueOf(produsSelectat.getPret()));
				stoc.setText(String.valueOf(produsSelectat.getStoc()));
				
				Object[] message = { "Nume:", nume, "Pret:", pret,
						"Stoc:", stoc };
				int option = JOptionPane.showConfirmDialog(null, message,
						"Date", JOptionPane.OK_CANCEL_OPTION);
				if (option == JOptionPane.OK_OPTION) {
					int stocNou = Integer.valueOf(stoc.getText());
					produsManager.updateProdus(produsSelectat, stocNou);
					addToCombobox();
				}
			}
		});
		
		btnExportCsv.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				produsManager.exportBilete("CSV");
			}
		});
		
		btnExportJson.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				produsManager.exportBilete("JSON");
			}
		});
		
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				LoginForm loginForm=new LoginForm();
				loginForm.frame.setVisible(true);
			}
		});
		
	}
	
	public void addToCombobox(){
		comboBox.removeAllItems();
		List<Produs> listaproduse = produsManager.getProduse();
		for(Produs sp :listaproduse){
			comboBox.addItem(sp);
		}
	}

}

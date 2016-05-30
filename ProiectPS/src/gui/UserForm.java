package gui;

import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import bl.CosCumparaturiManager;
import bl.ProdusManager;
import models.CosCumparaturi;
import models.Produs;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.List;


public class UserForm {

	public JFrame frame;
	private JTextField textField_Cantitate;
	private JButton btnAdaugaInCos;
	private JButton btnLogOut;
	private JLabel background;
	private JLabel lblProduse;
	private JLabel lblCantitate;
	private JLabel lblCosCumparaturi;
	private JComboBox<Produs> comboBox;
	private JComboBox<Produs> comboBox_Cos;
	private JTextField textField_Pret;
	private JButton btnEliminaDinCos;
	private JButton btnTrimiteComanda;
	private JLabel lblPretTotalComanda;
	private JTextField textField_PretTotal;
	
	private ProdusManager produsManager = new ProdusManager();
	private CosCumparaturiManager cosManager = new CosCumparaturiManager();

	/**
	 * Create the application.
	 */
	public UserForm() {
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
		
		textField_Cantitate = new JTextField();
		textField_Cantitate.setColumns(10);
		textField_Cantitate.setBounds(184, 142, 116, 35);
		frame.getContentPane().add(textField_Cantitate);
		
		btnAdaugaInCos = new JButton("Adauga in cos");
		btnAdaugaInCos.setBounds(87, 343, 130, 40);
		frame.getContentPane().add(btnAdaugaInCos);
		
		lblProduse = new JLabel("Produse: ");
		lblProduse.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblProduse.setForeground(Color.WHITE);
		lblProduse.setBounds(87, 81, 91, 50);
		frame.getContentPane().add(lblProduse);
		
		comboBox = new JComboBox<Produs>();
		comboBox.setBounds(184, 81, 349, 35);
		addToCombobox();
		frame.getContentPane().add(comboBox);
		
		lblCantitate = new JLabel("Cantitate: ");
		lblCantitate.setForeground(Color.WHITE);
		lblCantitate.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblCantitate.setBounds(84, 142, 95, 50);
		frame.getContentPane().add(lblCantitate);
		
		lblCosCumparaturi = new JLabel("Cos cumparaturi: ");
		lblCosCumparaturi.setForeground(Color.WHITE);
		lblCosCumparaturi.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblCosCumparaturi.setBounds(85, 203, 181, 50);
		frame.getContentPane().add(lblCosCumparaturi);
		
		comboBox_Cos = new JComboBox<Produs>();
		comboBox_Cos.setBounds(276, 203, 257, 35);
		frame.getContentPane().add(comboBox_Cos);
		
		btnLogOut = new JButton("Log out");
		btnLogOut.setBounds(417, 406, 116, 41);
		frame.getContentPane().add(btnLogOut);
		
		JLabel lblPret = new JLabel("Pret: ");
		lblPret.setForeground(Color.WHITE);
		lblPret.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblPret.setBounds(335, 142, 72, 50);
		frame.getContentPane().add(lblPret);
		
		textField_Pret = new JTextField();
		textField_Pret.setColumns(10);
		textField_Pret.setEditable(false);
		textField_Pret.setBounds(417, 142, 116, 35);
		frame.getContentPane().add(textField_Pret);
		
		btnEliminaDinCos = new JButton("Elimina din cos");
		btnEliminaDinCos.setBounds(226, 343, 130, 40);
		frame.getContentPane().add(btnEliminaDinCos);
		
		btnTrimiteComanda = new JButton("Trimite comanda");
		btnTrimiteComanda.setBounds(373, 343, 160, 40);
		frame.getContentPane().add(btnTrimiteComanda);
		
		lblPretTotalComanda = new JLabel("Pret total comanda:");
		lblPretTotalComanda.setForeground(Color.WHITE);
		lblPretTotalComanda.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblPretTotalComanda.setBounds(87, 264, 197, 50);
		frame.getContentPane().add(lblPretTotalComanda);
		
		textField_PretTotal = new JTextField();
		textField_PretTotal.setEditable(false);
		textField_PretTotal.setColumns(10);
		textField_PretTotal.setBounds(310, 264, 145, 35);
		frame.getContentPane().add(textField_PretTotal);
		
		background = new JLabel(new ImageIcon("cioco.png"));
		background.setBounds(0, 0, 634, 479);
		frame.getContentPane().add(background);
	}
	
	private void initActions() {
		textField_Cantitate.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent e) {
				if(!textField_Cantitate.getText().equals("")){
					int cantitate = Integer.valueOf(textField_Cantitate.getText());
					Produs produsSelectat = (Produs) comboBox.getSelectedItem();
					int pret = produsSelectat.getPret();
					textField_Pret.setText(String.valueOf(cantitate*pret));
				}
				
			}
			
			@Override
			public void focusGained(FocusEvent e) {
				if(!textField_Cantitate.getText().equals("")){
					int cantitate = Integer.valueOf(textField_Cantitate.getText());
					Produs produsSelectat = (Produs) comboBox.getSelectedItem();
					int pret = produsSelectat.getPret();
					textField_Pret.setText(String.valueOf(cantitate*pret));
				}
			}
		});
		
		btnEliminaDinCos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String cantitateText = textField_Cantitate.getText();
				if(!cantitateText.equals("") || !textField_PretTotal.getText().equals("")){
					Produs produsSelectat = (Produs) comboBox_Cos.getSelectedItem();
					int pretFinal=updateCosCombobox(produsSelectat, "DELETE");
					if(pretFinal>0){
						textField_PretTotal.setText(String.valueOf(pretFinal));
					}else{
						textField_PretTotal.setText("");
					}
					int cantitate = produsSelectat.getStoc();
					List<Produs> p = produsManager.getProduse();
					int stocVechi = 0;
					for(Produs pr : p){
						if(pr.getNume().equals(produsSelectat.getNume())){
							stocVechi = pr.getStoc();
							produsManager.updateProdus(pr, stocVechi+cantitate);
							break;
						}
					}
					addToCombobox();
				}
			}
		});
		
		btnAdaugaInCos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String cantitateText = textField_Cantitate.getText();
				if(!cantitateText.equals("")){
					Produs produsSelectat = (Produs) comboBox.getSelectedItem();
					int pretVechi = produsSelectat.getPret();
					int stocVechi = produsSelectat.getStoc();
					int cantitate = Integer.valueOf(textField_Cantitate.getText());
					int stocNou = stocVechi - cantitate;
					if(stocNou < 0){
						JOptionPane.showMessageDialog(null,"Cantitatea e mai mare decat stocul!!!");
					}else{
						Produs produsNou = new Produs();
						produsNou.setNume(produsSelectat.getNume());
						produsNou.setStoc(cantitate);
						produsNou.setPret(cantitate*pretVechi);
						int pretFinal =updateCosCombobox(produsNou, "ADD");
						if(pretFinal>0){
							produsManager.updateProdus(produsSelectat, stocNou);
							addToCombobox();
							textField_PretTotal.setText(String.valueOf(pretFinal));
						}
					}
					textField_Cantitate.setText("");
					textField_Pret.setText("");
				}else{
					JOptionPane.showMessageDialog(null,"Trebuie introdusa si o cantitate!!!");
				}
			}
		});
		
		btnTrimiteComanda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CosCumparaturi cos = new CosCumparaturi();
				if(!textField_PretTotal.getText().equals("")){
					cos.setPretTotal(Integer.valueOf(textField_PretTotal.getText()));
					int idComanda = cosManager.addComanda(cos);
					int nr = comboBox_Cos.getItemCount();
					for(int i = 0; i<nr; i++){
						Produs pr = (Produs) comboBox_Cos.getItemAt(i);
						cosManager.addInTabelaDeLegatura(idComanda, pr.getNume());
					}
					comboBox_Cos.removeAllItems();
					textField_PretTotal.setText("");
					textField_Cantitate.setText("");
					textField_Pret.setText("");
					JOptionPane.showMessageDialog(null,"Comanda a fost trimisa cu succes!!!");
				}else{
					JOptionPane.showMessageDialog(null,"Comanda goala!!!");
				}
			}
		});
		
		btnLogOut.addActionListener(new ActionListener() {
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
	
	public int updateCosCombobox(Produs produs, String type){
		int nr = comboBox_Cos.getItemCount();
		int pretTotal = 0;
		if(nr > 0 ){
			for(int i = 0; i < nr; i++){
				Produs pr = (Produs) comboBox_Cos.getItemAt(i);
				if(pr.getNume().equals(produs.getNume())){
					if(type.equals("ADD")){
						JOptionPane.showMessageDialog(null,"Produsul se afla deja in stoc, "
								+ "eliminati pentru a introduce o noua cantitate");
						return 0;
					}else{
						comboBox_Cos.removeItemAt(i);
					}
					break;
				}
			}
		}
		if(type.equals("ADD")){
			comboBox_Cos.addItem(produs);
		}
		nr = comboBox_Cos.getItemCount();
		if(nr > 0 ){
			for(int i = 0; i < nr; i++){
				Produs pr = (Produs) comboBox_Cos.getItemAt(i);
				pretTotal += pr.getPret();
				System.out.println(pretTotal);
			}
		}
		return pretTotal;
	}
	
}

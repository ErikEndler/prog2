package visao;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import controle.ConectaBanco;
import controle.ControleCidade;
import controle.ControleExemplo;
import modelo.ModeloCidade;
import modelo.ModeloExemplo;
import modelo.ModeloTabela;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.JTextPane;
import javax.swing.JComboBox;
import java.awt.Color;

public class Exemplo extends JFrame {
	
	ModeloExemplo mod = new ModeloExemplo();
	ControleExemplo control = new ControleExemplo();
	
   
	
	private JTextField textFieldNome;
	private JTextField textFieldCidade;
	private JTextField textFieldEstado;
	private JTextField textFieldBairro;
	private JTextField textFieldTelefone;
	private JButton btnSalvar;
	private JScrollPane scrollPane;
	private JTable tabela;
	private JTable table;
	public Exemplo(){
		super();
		initComponents();
		control.LerArquivo(mod);
	}
	
	public void initComponents() {
		getContentPane().setLayout(null);
		setBounds(100, 100, 436, 508);
		setResizable(false);
		
		
		JLabel lblCadastro = new JLabel("Cadastro");
		lblCadastro.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblCadastro.setBounds(217, 11, 122, 27);
		getContentPane().add(lblCadastro);
		
		JLabel lblNome = new JLabel("Nome :");
		lblNome.setBounds(10, 74, 46, 14);
		getContentPane().add(lblNome);
		
		JLabel lblCidade = new JLabel("Cidade :");
		lblCidade.setBounds(10, 106, 46, 14);
		getContentPane().add(lblCidade);
		
		textFieldNome = new JTextField();
		textFieldNome.setBounds(66, 71, 206, 20);
		getContentPane().add(textFieldNome);
		textFieldNome.setColumns(10);
		
		textFieldCidade = new JTextField();
		textFieldCidade.setBounds(66, 103, 206, 20);
		getContentPane().add(textFieldCidade);
		textFieldCidade.setColumns(10);
		
		JLabel lblEstado = new JLabel("Estado :");
		lblEstado.setBounds(282, 106, 46, 14);
		getContentPane().add(lblEstado);
		
		textFieldEstado = new JTextField();
		textFieldEstado.setBounds(338, 103, 58, 20);
		getContentPane().add(textFieldEstado);
		textFieldEstado.setColumns(10);
		
		textFieldBairro = new JTextField();
		textFieldBairro.setBounds(66, 142, 206, 20);
		getContentPane().add(textFieldBairro);
		textFieldBairro.setColumns(10);
		
		JLabel lblBairro = new JLabel("Bairro");
		lblBairro.setBounds(10, 145, 46, 14);
		getContentPane().add(lblBairro);
		
		JLabel lblTelefone = new JLabel("Telefone");
		lblTelefone.setBounds(10, 183, 46, 14);
		getContentPane().add(lblTelefone);
		
		textFieldTelefone = new JTextField();
		textFieldTelefone.setBounds(66, 180, 206, 20);
		getContentPane().add(textFieldTelefone);
		textFieldTelefone.setColumns(10);
		
		btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//JOptionPane.showMessageDialog(null, textFieldNome.getText());
				mod.setNome(textFieldNome.getText());
				mod.setCidade(textFieldCidade.getText());
				mod.setEstado(textFieldEstado.getText());
				mod.setBairro(textFieldBairro.getText());
				mod.setTelefone(textFieldTelefone.getText());
				control.PrepararSalvar();
				textFieldBairro.setText("");
				textFieldCidade.setText("");
				textFieldEstado.setText("");
				textFieldNome.setText("");
				textFieldTelefone.setText("");
			}
		});
		btnSalvar.setBounds(10, 228, 89, 23);
		getContentPane().add(btnSalvar);
		
		JButton btnEditar = new JButton("Editar");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//control.Editar(mod);
				control.LerArquivo(mod);
				textFieldBairro.setText(mod.getBairro());
				textFieldCidade.setText(mod.getCidade());
				textFieldEstado.setText(mod.getEstado());
				textFieldNome.setText(mod.getNome());
				textFieldTelefone.setText(mod.getTelefone());
				
			}
		});
		btnEditar.setBounds(127, 228, 89, 23);
		getContentPane().add(btnEditar);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10,279,386,190);
		getContentPane().add(scrollPane);
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(
				new Object[][]{
					{},
					{},
					{},
					{}
				},
				new String[] {"Nome","Cidade","Estado","Bairro","Telefone"}
				));
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBackground(Color.PINK);
		comboBox.setBounds(311, 160, 89, 20);
		comboBox.addItem("animal");
		comboBox.addItem("caju");
		getContentPane().add(comboBox);
		
		//table.setBounds(10,293,386,103);
		//getContentPane().add(table);

	
			setVisible(true);
			
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Exemplo();
	}
}

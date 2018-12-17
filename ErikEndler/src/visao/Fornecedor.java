package visao;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;

import controle.ControleFornecedor;
import modelo.ModeloFornecedor;
import modelo.ModeloTabela;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;
import java.util.ArrayList;

public class Fornecedor extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldID;
	private JTextField textFieldNome;
	private JFormattedTextField textFieldTelefone;
	private JButton btnNovo, btnSalvar, btnEditar, btnExcluir, btnCancelar, btnSair;
	private JScrollPane scrollPane;
	private JTable tabelaFornecedor;
	private JLabel lblTelefone, lblNome, lblID;
	int flag = 1;

	ModeloFornecedor mod = new ModeloFornecedor();
	//ControleFornecedor control = new ControleFornecedor();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Fornecedor frame = new Fornecedor();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Fornecedor() {
		initialize();
		
		try {
			MaskFormatter Ftel = new MaskFormatter("(##)####-####");
			textFieldTelefone.setFormatterFactory(new DefaultFormatterFactory(Ftel));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ListarTabela();
	}

	public void initialize() {
		setTitle("Fornecedores");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 547, 493);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		lblID = new JLabel("ID");
		lblID.setBounds(10, 41, 46, 14);
		contentPane.add(lblID);

		textFieldID = new JTextField();
		textFieldID.setToolTipText("SOMENTE NUMEROS");
		textFieldID.setEnabled(false);
		textFieldID.setBounds(66, 38, 86, 20);
		contentPane.add(textFieldID);
		textFieldID.setColumns(10);

		lblNome = new JLabel("Nome");
		lblNome.setBounds(10, 72, 46, 14);
		contentPane.add(lblNome);

		textFieldNome = new JTextField();
		textFieldNome.setEnabled(false);
		textFieldNome.setBounds(66, 69, 311, 20);
		contentPane.add(textFieldNome);
		textFieldNome.setColumns(10);

		lblTelefone = new JLabel("Telefone");
		lblTelefone.setBounds(10, 103, 46, 14);
		contentPane.add(lblTelefone);

		textFieldTelefone = new JFormattedTextField();
		textFieldTelefone.setEnabled(false);
		textFieldTelefone.setBounds(66, 100, 156, 20);
		contentPane.add(textFieldTelefone);
		textFieldTelefone.setColumns(10);

		btnNovo = new JButton("Novo");
		btnNovo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				LimparTextos();
				HabilitarTextos();
				btnNovo.setEnabled(false);
				btnSalvar.setEnabled(true);
				btnCancelar.setEnabled(true);

			}
		});
		btnNovo.setBounds(10, 143, 76, 23);
		contentPane.add(btnNovo);

		btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (flag == 1) {
					mod.setID(Integer.parseInt(textFieldID.getText()));
					mod.setNome(textFieldNome.getText());
					mod.setTelefone(textFieldTelefone.getText());
					//control.Salvar(mod);
				} else {
					mod.setID(Integer.parseInt(textFieldID.getText()));
					mod.setNome(textFieldNome.getText());
					mod.setTelefone(textFieldTelefone.getText());
					//control.Editar(mod);
				}
				DesabilitarBotoes();
				DesabilitarTextos();
				LimparTextos();
				btnNovo.setEnabled(true);
				flag = 1;
				ListarTabela();
			}
		});
		btnSalvar.setEnabled(false);
		btnSalvar.setBounds(88, 143, 76, 23);
		contentPane.add(btnSalvar);

		btnEditar = new JButton("Editar");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				flag = 2;
				DesabilitarBotoes();
				HabilitarTextos();
				btnSalvar.setEnabled(true);
				btnCancelar.setEnabled(true);
			}
		});
		btnEditar.setEnabled(false);
		btnEditar.setBounds(166, 143, 76, 23);
		contentPane.add(btnEditar);

		btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DesabilitarBotoes();
				DesabilitarTextos();
				LimparTextos();
				btnNovo.setEnabled(true);
			}
		});
		btnExcluir.setEnabled(false);
		btnExcluir.setBounds(246, 143, 76, 23);
		contentPane.add(btnExcluir);

		btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				DesabilitarBotoes();
				LimparTextos();
				DesabilitarTextos();
				btnNovo.setEnabled(true);
			}
		});
		btnCancelar.setEnabled(false);
		btnCancelar.setBounds(326, 143, 86, 23);
		contentPane.add(btnCancelar);

		btnSair = new JButton("Sair");
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnSair.setBounds(432, 143, 89, 23);
		contentPane.add(btnSair);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 195, 511, 249);
		contentPane.add(scrollPane);
		tabelaFornecedor = new JTable();
		tabelaFornecedor.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				textFieldID.setText(""+tabelaFornecedor.getValueAt(tabelaFornecedor.getSelectedRow(), 0));
				textFieldNome.setText("" + tabelaFornecedor.getValueAt(tabelaFornecedor.getSelectedRow(), 1));
				textFieldTelefone.setText("" + tabelaFornecedor.getValueAt(tabelaFornecedor.getSelectedRow(), 2));
				DesabilitarBotoes();
				DesabilitarTextos();
				btnCancelar.setEnabled(true);
				btnEditar.setEnabled(true);
			}
		});
		scrollPane.setViewportView(tabelaFornecedor);
		tabelaFornecedor.setModel(new DefaultTableModel(new Object[][] { {}, {}, {}, {} }, new String[] {}));
	}

	private void LimparTextos() {
		textFieldID.setText("");
		textFieldNome.setText("");
		textFieldTelefone.setText("");
	}

	private void DesabilitarBotoes() {
		btnCancelar.setEnabled(false);
		btnEditar.setEnabled(false);
		btnExcluir.setEnabled(false);
		btnNovo.setEnabled(false);
		btnSalvar.setEnabled(false);
	}

	private void HabilitarTextos() {
		textFieldID.setEnabled(true);
		textFieldNome.setEnabled(true);
		textFieldTelefone.setEnabled(true);
	}

	private void DesabilitarTextos() {
		textFieldID.setEnabled(false);
		textFieldNome.setEnabled(false);
		textFieldTelefone.setEnabled(false);
	}

	public void ListarTabela() {
	//	control.PreencherTabela(mod);
		String[] col = mod.getCol();
		ArrayList lin = mod.getLin();
		ModeloTabela modelo = new ModeloTabela(lin, col);

		tabelaFornecedor.setModel(modelo);
		tabelaFornecedor.getColumnModel().getColumn(0).setPreferredWidth(115);
		tabelaFornecedor.getColumnModel().getColumn(0).setResizable(false);
		tabelaFornecedor.getColumnModel().getColumn(1).setPreferredWidth(280);
		tabelaFornecedor.getColumnModel().getColumn(1).setResizable(false);
		tabelaFornecedor.getColumnModel().getColumn(2).setPreferredWidth(115);
		tabelaFornecedor.getColumnModel().getColumn(2).setResizable(false);
		tabelaFornecedor.getTableHeader().setReorderingAllowed(false);
		tabelaFornecedor.setAutoResizeMode(tabelaFornecedor.AUTO_RESIZE_OFF);
		tabelaFornecedor.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	}
}

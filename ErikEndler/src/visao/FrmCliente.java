package visao;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.DefaultFormatter;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;

import controle.ConectaBanco;
import controle.ControleCliente;
import modelo.ModeloCliente;
import modelo.ModeloTabela;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JFormattedTextField;
import java.awt.Color;

public class FrmCliente extends JFrame {

	private JPanel contentPane;
	private JTextField jTextFielNome;
	private JTextField jTextFielCodigo;
	private JFormattedTextField jTextFielTelefone;
	private JFormattedTextField jTextFielCPF;
	private JLabel jLabelTitulo;
	private JLabel jLabelNome;
	private JLabel jLabelCodigo;
	private JLabel jLabelTelefone;
	private JLabel jLabelEstado;
	private JLabel jLabelCidade;
	private JLabel jLabelBairro;
	private JLabel jLabelCpf;
	private JComboBox comboBoxEstado;
	private JComboBox comboBoxCidade;
	private JComboBox comboBoxBairro;
	private JButton jButtonSalvar;
	private JButton jButtonNovo;
	private JButton JbuttonEditar;
	private JButton jButtonCancelar;
	private JButton jButtonExcluir;
	private JButton jButtonSair;
	private JButton jButtonAddEstado;
	private JButton jButtonAddCidade;
	private JButton jButtonAddBairro;
	private JTable jTableCliente;
	private JScrollPane scrollPane;
	int flag = 1;

	ModeloCliente mod = new ModeloCliente();
	ConectaBanco connCliente = new ConectaBanco();
	ConectaBanco connCidade = new ConectaBanco();
	ConectaBanco connEstado = new ConectaBanco();
	ConectaBanco connBairro = new ConectaBanco();
	ControleCliente control = new ControleCliente();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		new FrmCliente();
	}

	/**
	 * Create the frame.
	 */
	public FrmCliente() {
		super();
		setTitle("Tela Cadastro Cidades");
		initialize();
		try {
			MaskFormatter Fcpf = new MaskFormatter("###.###.###-##");
			jTextFielCPF.setFormatterFactory(new DefaultFormatterFactory(Fcpf));
			MaskFormatter Ftel = new MaskFormatter("(##)####-####");
			jTextFielTelefone.setFormatterFactory(new DefaultFormatterFactory(Ftel));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		preencherTabela(
				"select * from cliente inner join estados on cliente.id_estado = estados.id_estado inner join cidade on cliente.id_cidade = cidade.id_cidade inner join bairro on cliente.id_bairro = bairro.id_bairro");

	}

	private void initialize() {

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 645, 445);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		jLabelTitulo = new JLabel("Formulario Clientes");
		jLabelTitulo.setFont(new Font("Tahoma", Font.BOLD, 18));
		jLabelTitulo.setBounds(112, 11, 184, 25);
		contentPane.add(jLabelTitulo);

		jLabelNome = new JLabel("Nome");
		jLabelNome.setBounds(10, 89, 46, 14);
		contentPane.add(jLabelNome);

		jTextFielNome = new JTextField();
		jTextFielNome.setBackground(Color.WHITE);
		jTextFielNome.setDisabledTextColor(Color.BLACK);
		jTextFielNome.setEnabled(false);
		jTextFielNome.setBounds(68, 86, 274, 20);
		contentPane.add(jTextFielNome);
		jTextFielNome.setColumns(10);

		jLabelCodigo = new JLabel("Codigo");
		jLabelCodigo.setBounds(10, 58, 46, 14);
		contentPane.add(jLabelCodigo);

		jTextFielCodigo = new JTextField();
		jTextFielCodigo.setDisabledTextColor(Color.BLACK);
		jTextFielCodigo.setEnabled(false);
		jTextFielCodigo.setBounds(68, 55, 86, 20);
		contentPane.add(jTextFielCodigo);
		jTextFielCodigo.setColumns(10);

		jLabelTelefone = new JLabel("Telefone");
		jLabelTelefone.setBounds(10, 123, 56, 14);
		contentPane.add(jLabelTelefone);

		jTextFielTelefone = new JFormattedTextField();
		jTextFielTelefone.setBackground(Color.WHITE);
		jTextFielTelefone.setDisabledTextColor(Color.BLACK);
		jTextFielTelefone.setEnabled(false);
		jTextFielTelefone.setBounds(68, 117, 184, 20);
		contentPane.add(jTextFielTelefone);
		jTextFielTelefone.setColumns(10);

		comboBoxEstado = new JComboBox();
		comboBoxEstado.setEnabled(false);
		comboBoxEstado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (comboBoxEstado.isPopupVisible()) {
					comboBoxCidade.setEnabled(true);
					AtualizaCOmboBoxCidade();
				}
			}
		});
		comboBoxEstado.setBounds(419, 55, 147, 20);
		contentPane.add(comboBoxEstado);

		comboBoxCidade = new JComboBox();
		comboBoxCidade.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (comboBoxCidade.isPopupVisible()) {
					comboBoxBairro.setEnabled(true);
					AtualizaCOmboBoxBairro();
				}
			}
		});
		comboBoxCidade.setEnabled(false);
		comboBoxCidade.setBounds(419, 89, 147, 20);
		contentPane.add(comboBoxCidade);

		comboBoxBairro = new JComboBox();
		comboBoxBairro.setEnabled(false);
		comboBoxBairro.setBounds(419, 126, 147, 20);
		contentPane.add(comboBoxBairro);

		jLabelEstado = new JLabel("Estado");
		jLabelEstado.setBounds(363, 58, 46, 14);
		contentPane.add(jLabelEstado);

		jLabelCidade = new JLabel("Cidade");
		jLabelCidade.setBounds(360, 95, 46, 14);
		contentPane.add(jLabelCidade);

		jLabelBairro = new JLabel("Bairro");
		jLabelBairro.setBounds(363, 129, 46, 14);
		contentPane.add(jLabelBairro);

		jButtonSalvar = new JButton("Salvar");
		jButtonSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (flag == 1) {
					mod.setNome(jTextFielNome.getText());
					mod.setCpf(jTextFielCPF.getText());
					mod.setTelefone(jTextFielTelefone.getText());
					mod.setBairro("" + comboBoxBairro.getSelectedItem());
					mod.setCidade("" + comboBoxCidade.getSelectedItem());
					mod.setEstado("" + comboBoxEstado.getSelectedItem());
					control.Salvar(mod);
				} else {
					mod.setCodigo(Integer.parseInt(jTextFielCodigo.getText()));
					mod.setNome(jTextFielNome.getText());
					mod.setCpf(jTextFielCPF.getText());
					mod.setTelefone(jTextFielTelefone.getText());
					mod.setBairro("" + comboBoxBairro.getSelectedItem());
					mod.setCidade("" + comboBoxCidade.getSelectedItem());
					mod.setEstado("" + comboBoxEstado.getSelectedItem());
					control.Editar(mod);
					flag = 1;

				}
				desabilitarBotoes();
				jButtonNovo.setEnabled(true);
				limparTextos();
				desabilitarTextos();
				desabilitarComboBox();
				comboBoxBairro.removeAllItems();
				comboBoxCidade.removeAllItems();
				comboBoxEstado.removeAllItems();
				preencherTabela(
						"select * from cliente inner join estados on cliente.id_estado = estados.id_estado inner join cidade on cliente.id_cidade = cidade.id_cidade inner join bairro on cliente.id_bairro = bairro.id_bairro");
			}
		});
		jButtonSalvar.setEnabled(false);
		jButtonSalvar.setBounds(85, 232, 71, 23);
		contentPane.add(jButtonSalvar);

		jButtonNovo = new JButton("Novo");
		jButtonNovo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				flag = 1;
				comboBoxEstado.setEnabled(true);
				AtualizaCOmboBoxEstado();
				habilitarTextos();

				jButtonNovo.setEnabled(false);
				jButtonSalvar.setEnabled(true);
				jButtonCancelar.setEnabled(true);
			}
		});
		jButtonNovo.setBounds(10, 232, 71, 23);
		contentPane.add(jButtonNovo);

		JbuttonEditar = new JButton("Editar");
		JbuttonEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				flag = 2;
				JbuttonEditar.setEnabled(false);
				jButtonSalvar.setEnabled(true);
				jTextFielTelefone.setEnabled(true);
				jTextFielNome.setEnabled(true);
				jTextFielCPF.setEnabled(true);
				comboBoxEstado.setEnabled(true);
				comboBoxCidade.setEnabled(true);
				comboBoxBairro.setEnabled(true);

			}
		});
		JbuttonEditar.setEnabled(false);
		JbuttonEditar.setBounds(161, 232, 71, 23);
		contentPane.add(JbuttonEditar);

		jButtonCancelar = new JButton("Cancelar");
		jButtonCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limparTextos();
				desabilitarComboBox();
				desabilitarBotoes();
				jButtonNovo.setEnabled(true);
				desabilitarTextos();
				flag = 1;

			}
		});
		jButtonCancelar.setEnabled(false);
		jButtonCancelar.setBounds(237, 232, 87, 23);
		contentPane.add(jButtonCancelar);

		jButtonExcluir = new JButton("Excluir");
		jButtonExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mod.setCodigo(Integer.parseInt(jTextFielCodigo.getText()));
				control.Excluir(mod);
				desabilitarBotoes();
				jButtonNovo.setEnabled(true);
				desabilitarTextos();
				limparTextos();
				desabilitarComboBox();
				preencherTabela(
						"select * from cliente inner join estados on cliente.id_estado = estados.id_estado inner join cidade on cliente.id_cidade = cidade.id_cidade inner join bairro on cliente.id_bairro = bairro.id_bairro");

			}
		});
		jButtonExcluir.setEnabled(false);
		jButtonExcluir.setBounds(330, 232, 75, 23);
		contentPane.add(jButtonExcluir);

		jButtonSair = new JButton("Sair");
		jButtonSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		jButtonSair.setBounds(544, 232, 75, 23);
		contentPane.add(jButtonSair);

		jLabelCpf = new JLabel("CPF");
		jLabelCpf.setBounds(10, 155, 46, 14);
		contentPane.add(jLabelCpf);

		jTextFielCPF = new JFormattedTextField();
		jTextFielCPF.setBackground(Color.WHITE);
		jTextFielCPF.setDisabledTextColor(Color.BLACK);
		jTextFielCPF.setEnabled(false);
		jTextFielCPF.setBounds(68, 150, 184, 20);
		contentPane.add(jTextFielCPF);
		jTextFielCPF.setColumns(10);

		jButtonAddEstado = new JButton("+");
		jButtonAddEstado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FrmEstado frm = new FrmEstado();
			}
		});
		jButtonAddEstado.setBounds(578, 54, 41, 23);
		contentPane.add(jButtonAddEstado);

		jButtonAddCidade = new JButton("+");
		jButtonAddCidade.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				FrmCidades frm = new FrmCidades();
			}
		});
		jButtonAddCidade.setBounds(578, 88, 41, 23);
		contentPane.add(jButtonAddCidade);

		jButtonAddBairro = new JButton("+");
		jButtonAddBairro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FrmBairro frm = new FrmBairro();
			}
		});
		jButtonAddBairro.setBounds(578, 125, 41, 23);
		contentPane.add(jButtonAddBairro);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 279, 609, 117);
		getContentPane().add(scrollPane);
		jTableCliente = new JTable();
		jTableCliente.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				String IDcliente = "" + jTableCliente.getValueAt(jTableCliente.getSelectedRow(), 0);
				JbuttonEditar.setEnabled(true);
				jButtonSalvar.setEnabled(false);
				jButtonExcluir.setEnabled(true);
				jButtonCancelar.setEnabled(true);
				jButtonNovo.setEnabled(false);
				AtualizaCOmboBoxEstado();
				connEstado.conexao();
				connCliente.conexao();
				connCliente.executaSQL("select * from cliente where id_cliente='" + IDcliente + "'");
				try {
					connCliente.rs.first();
					jTextFielCodigo.setText(String.valueOf(connCliente.rs.getInt("id_cliente")));
					jTextFielCPF.setText(connCliente.rs.getString("cpf"));
					jTextFielNome.setText(connCliente.rs.getString("nome_cliente"));
					jTextFielTelefone.setText(connCliente.rs.getString("telefone_cliente"));

					connEstado.executaSQL(
							"select * from estados where id_estado='" + connCliente.rs.getInt("id_estado") + "'");
					connEstado.rs.first();
					comboBoxEstado.setSelectedItem(connEstado.rs.getString("nome_estado"));
					AtualizaCOmboBoxCidade();
					connCidade.conexao();
					connCidade.executaSQL("select * from cidade where id_cidade=" + connCliente.rs.getInt("id_cidade"));
					connCidade.rs.first();
					comboBoxCidade.setSelectedItem(connCidade.rs.getString("nome_cidade"));
					AtualizaCOmboBoxBairro();
					connBairro.conexao();
					connBairro.executaSQL("select * from bairro where id_bairro=" + connCliente.rs.getInt("id_bairro"));
					connBairro.rs.first();
					comboBoxBairro.setSelectedItem(connBairro.rs.getString("nome_bairro"));

					connCliente.desconecta();
					connEstado.desconecta();
					connCidade.desconecta();
					connBairro.desconecta();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		});
		scrollPane.setViewportView(jTableCliente);
		jTableCliente.setModel(new DefaultTableModel(new Object[][] { {}, {}, {}, {} }, new String[] {}));

		setVisible(true);
	}

	public void AtualizaCOmboBoxEstado() {
		connEstado.conexao();
		connEstado.executaSQL("select * from estados order by nome_estado");
		comboBoxEstado.removeAllItems();
		try { 
			connEstado.rs.first();
			do {
				comboBoxEstado.addItem(connEstado.rs.getString("nome_estado"));
			} while (connEstado.rs.next());
		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(rootPane, "Erro ao preencher combobox: " + ex);
		}
		connEstado.desconecta();
	}

	public void AtualizaCOmboBoxCidade() {
		connEstado.conexao();
		connCidade.conexao();

		try { 
			connEstado
					.executaSQL("select * from estados where nome_estado ='" + comboBoxEstado.getSelectedItem() + "'");
			connEstado.rs.first();
			mod.setI(connEstado.rs.getInt("id_estado"));
			connCidade.executaSQL("select * from cidade where id_estado='" + mod.getI() + "'");
			comboBoxCidade.removeAllItems();// remove todos os item da combobox
			connCidade.rs.first();
			do {
				comboBoxCidade.addItem(connCidade.rs.getString("nome_cidade"));
			} while (connCidade.rs.next());
		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(rootPane, "Erro ao preencher comboboxcidade: " + ex);
		}
		connCidade.desconecta();
		connEstado.desconecta();
	}

	public void AtualizaCOmboBoxBairro() {

		connCidade.conexao();
		connBairro.conexao();

		try { // aqui a combobox é preenchida			
			connCidade.executaSQL("select * from cidade where nome_cidade ='" + comboBoxCidade.getSelectedItem() + "'");
			connCidade.rs.first();		
			int cod = connCidade.rs.getInt("id_cidade");
			connBairro.executaSQL("select * from bairro where id_cidade='" + cod + "'");
			comboBoxBairro.removeAllItems();// remove todos os item da combobox
			connBairro.rs.first();
			do {
				comboBoxBairro.addItem(connBairro.rs.getString("nome_bairro"));
			} while (connBairro.rs.next());
		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(rootPane, "Erro ao preencher comboboxcidade: " + ex);
		}
		connBairro.desconecta();
		connCidade.desconecta();
	}

	public void preencherTabela(String SQL) {
		ArrayList dados = new ArrayList();
		String[] Colunas = new String[] { "ID", "Nome", "cpf", "Telefone", "Estado", "Cidade" };
		connCliente.conexao();
		connCliente.executaSQL(SQL);
		try {
			connCliente.rs.first();	
			do {
				dados.add(new Object[] { connCliente.rs.getInt("id_cliente"), connCliente.rs.getString("nome_cliente"),
						connCliente.rs.getString("cpf"), connCliente.rs.getString("telefone_cliente"),
						connCliente.rs.getString("nome_estado"), connCliente.rs.getString("nome_cidade") });
			} while (connCliente.rs.next());
		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(rootPane, "Erro ao preencher o ArrayList!\n Erro: " + ex);
		}
		ModeloTabela modelo = new ModeloTabela(dados, Colunas);
		jTableCliente.setModel(modelo);
		jTableCliente.getColumnModel().getColumn(0).setPreferredWidth(30);
		jTableCliente.getColumnModel().getColumn(0).setResizable(false);
		jTableCliente.getColumnModel().getColumn(1).setPreferredWidth(200);
		jTableCliente.getColumnModel().getColumn(1).setResizable(false);
		jTableCliente.getColumnModel().getColumn(2).setPreferredWidth(100);
		jTableCliente.getColumnModel().getColumn(2).setResizable(false);
		jTableCliente.getColumnModel().getColumn(3).setPreferredWidth(100);
		jTableCliente.getColumnModel().getColumn(3).setResizable(false);
		jTableCliente.getColumnModel().getColumn(4).setPreferredWidth(100);
		jTableCliente.getColumnModel().getColumn(4).setResizable(false);
		jTableCliente.getTableHeader().setReorderingAllowed(false);
		jTableCliente.setAutoResizeMode(jTableCliente.AUTO_RESIZE_OFF);
		jTableCliente.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		connCliente.desconecta();

	}

	public void limparTextos() {
		jTextFielCodigo.setText("");
		jTextFielCPF.setText("");
		jTextFielNome.setText("");
		jTextFielTelefone.setText("");
	}

	public void habilitarTextos() {
		jTextFielCPF.setEnabled(true);
		jTextFielNome.setEnabled(true);
		jTextFielTelefone.setEnabled(true);
	}

	public void desabilitarTextos() {
		jTextFielCodigo.setEnabled(false);
		jTextFielCPF.setEnabled(false);
		jTextFielNome.setEnabled(false);
		jTextFielTelefone.setEnabled(false);
	}

	public void desabilitarBotoes() {
		jButtonCancelar.setEnabled(false);
		JbuttonEditar.setEnabled(false);
		jButtonExcluir.setEnabled(false);
		jButtonNovo.setEnabled(false);
		jButtonSalvar.setEnabled(false);
	}

	public void desabilitarComboBox() {
		comboBoxBairro.setEnabled(false);
		comboBoxCidade.setEnabled(false);
		comboBoxEstado.setEnabled(false);
	}

}

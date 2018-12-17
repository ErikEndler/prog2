package visao;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import controle.ConectaBanco;
import controle.ControleBairro;
import modelo.ModeloBairro;
import modelo.ModeloTabela;

import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;

public class FrmBairro extends JFrame {

	private JTextField jTextFieldCodigo;
	private JTextField jTextFieldNome;
	private JButton jButtonNovo, jButtonSalvar, jButtonEditar, jButtonExcluir, jButtonPrimeiro;
	private JButton jButtonSair, JButtonCancelar;
	private JComboBox jComboBoxCidade;
	private JLabel jLabelCodigo, jLabeldNome, jLabelCidade, jLAbelTitulo;
	private JTable jTableBairro;
	private JScrollPane scrollPane;

	ModeloBairro mod = new ModeloBairro();

	ControleBairro control = new ControleBairro();

	ConectaBanco connBairro = new ConectaBanco();
	ConectaBanco connCidade = new ConectaBanco();
	int flag = 1;
	private JButton BotaoAtualizar;

	public FrmBairro() {

		super();
		setTitle("Tela Cadastro Bairros");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		initComponents();
		preencherTabela("select * from bairro inner join cidade on bairro.id_cidade = cidade.id_cidade");
		AtualizaCombo();
	}

	public void initComponents() {

		getContentPane().setLayout(null);
		setBounds(100, 100, 794, 463);
		setResizable(false);

		jLAbelTitulo = new JLabel("Cadastro Bairros");
		jLAbelTitulo.setFont(new Font("Tahoma", Font.BOLD, 24));
		jLAbelTitulo.setBounds(117, 29, 219, 27);
		getContentPane().add(jLAbelTitulo);

		jLabelCodigo = new JLabel("C\u00F3digo");
		jLabelCodigo.setBounds(10, 96, 46, 14);
		getContentPane().add(jLabelCodigo);

		jLabeldNome = new JLabel("Nome");
		jLabeldNome.setBounds(10, 139, 46, 14);
		getContentPane().add(jLabeldNome);

		jTextFieldCodigo = new JTextField();
		jTextFieldCodigo.setEnabled(false);
		jTextFieldCodigo.setBounds(66, 93, 86, 20);
		getContentPane().add(jTextFieldCodigo);
		jTextFieldCodigo.setColumns(10);

		jTextFieldNome = new JTextField();
		jTextFieldNome.setEnabled(false);
		jTextFieldNome.setBounds(66, 136, 270, 20);
		getContentPane().add(jTextFieldNome);
		jTextFieldNome.setColumns(10);

		jLabelCidade = new JLabel("Cidade");
		jLabelCidade.setBounds(389, 136, 46, 14);
		getContentPane().add(jLabelCidade);

		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FrmCidades frm = new FrmCidades();
				frm.setVisible(true);
			}
		});
		btnAdd.setBounds(710, 132, 68, 23);
		getContentPane().add(btnAdd);

		jButtonNovo = new JButton("Novo");
		jButtonNovo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				jComboBoxCidade.setEnabled(true);
				jButtonSalvar.setEnabled(true);
				jButtonNovo.setEnabled(false);
				jButtonEditar.setEnabled(true);
				JButtonCancelar.setEnabled(true);
				jButtonExcluir.setEnabled(true);
				jTextFieldNome.setEnabled(true);
				jTextFieldNome.setText("");
				jTextFieldCodigo.setText("");
				AtualizaCombo();
			}
		});
		jButtonNovo.setBounds(10, 187, 73, 23);
		getContentPane().add(jButtonNovo);

		jButtonSalvar = new JButton("Salvar");
		jButtonSalvar.setEnabled(false);
		jButtonSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (flag == 1) {
					mod.setNome(jTextFieldNome.getText());
					mod.setCidade("" + jComboBoxCidade.getSelectedItem());
					control.Grava(mod);
					//
				} else {
					mod.setCod(Integer.parseInt(jTextFieldCodigo.getText()));
					mod.setNome(jTextFieldNome.getText());
					mod.setCidade("" + jComboBoxCidade.getSelectedItem());
					control.Editar(mod);
					flag = 1;
				}
				jTextFieldNome.setText("");
				jTextFieldCodigo.setText("");
				jTextFieldNome.setEnabled(false);
				jButtonEditar.setEnabled(false);
				jButtonSalvar.setEnabled(false);
				JButtonCancelar.setEnabled(false);
				jButtonExcluir.setEnabled(false);
				jButtonNovo.setEnabled(true);
				jComboBoxCidade.setEnabled(false);
				preencherTabela("select * from bairro inner join cidade on bairro.id_cidade = cidade.id_cidade");
			}
		});
		jButtonSalvar.setBounds(82, 187, 73, 23);
		getContentPane().add(jButtonSalvar);

		jButtonEditar = new JButton("Editar");
		jButtonEditar.setEnabled(false);
		jButtonEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				flag = 2;
				jTextFieldNome.setEnabled(true);
				jButtonNovo.setEnabled(false);
				jButtonEditar.setEnabled(false);
				jButtonSalvar.setEnabled(true);
				JButtonCancelar.setEnabled(true);
				jButtonExcluir.setEnabled(true);
				jComboBoxCidade.setEnabled(true);
			}
		});
		jButtonEditar.setBounds(154, 187, 73, 23);
		getContentPane().add(jButtonEditar);

		jButtonExcluir = new JButton("Excluir");
		jButtonExcluir.setEnabled(false);
		jButtonExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mod.setCod(Integer.parseInt(jTextFieldCodigo.getText()));
				control.Excluir(mod);
				jButtonSalvar.setEnabled(true);
				jButtonNovo.setEnabled(false);
				jButtonEditar.setEnabled(true);
				JButtonCancelar.setEnabled(true);
				jButtonExcluir.setEnabled(true);
				jTextFieldNome.setEnabled(true);
				jTextFieldNome.setText("");
				jTextFieldCodigo.setText("");
				preencherTabela("select * from bairro inner join cidade on bairro.id_cidade = cidade.id_cidade");
			}
		});
		jButtonExcluir.setBounds(226, 187, 73, 23);
		getContentPane().add(jButtonExcluir);

		JButtonCancelar = new JButton("Cancelar");
		JButtonCancelar.setEnabled(false);
		JButtonCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jTextFieldNome.setText("");
				jTextFieldCodigo.setText("");
				jTextFieldNome.setEnabled(false);
				jButtonEditar.setEnabled(false);
				jButtonNovo.setEnabled(true);
				jButtonSalvar.setEnabled(false);
				jButtonExcluir.setEnabled(false);
				JButtonCancelar.setEnabled(false);
				jComboBoxCidade.setEnabled(false);
			}
		});
		JButtonCancelar.setBounds(299, 187, 85, 23);
		getContentPane().add(JButtonCancelar);

		jButtonSair = new JButton("Sair");
		jButtonSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		jButtonSair.setBounds(713, 187, 65, 23);
		getContentPane().add(jButtonSair);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 279, 768, 145);
		getContentPane().add(scrollPane);
		jTableBairro = new JTable();
		jTableBairro.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				String IDbairro = "" + jTableBairro.getValueAt(jTableBairro.getSelectedRow(), 0);
				jButtonEditar.setEnabled(true);
				jButtonExcluir.setEnabled(true);
				JButtonCancelar.setEnabled(true);
				jButtonSalvar.setEnabled(false);
				jButtonNovo.setEnabled(false);
				connBairro.conexao();
				connCidade.conexao();
				connBairro.executaSQL("select * from bairro where id_bairro='" + IDbairro + "'");
				try {
					connBairro.rs.first();
					jTextFieldCodigo.setText(String.valueOf(connBairro.rs.getInt("id_bairro")));
					jTextFieldNome.setText(connBairro.rs.getString("nome_bairro"));
					connCidade.executaSQL("select * from cidade where id_cidade=" + connBairro.rs.getInt("id_cidade"));
					connCidade.rs.first();
					jComboBoxCidade.setSelectedItem(connCidade.rs.getString("nome_cidade"));
					connBairro.desconecta();
					connCidade.desconecta();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		scrollPane.setViewportView(jTableBairro);
		jTableBairro.setModel(new DefaultTableModel(new Object[][] { {}, {}, {}, {} }, new String[] {}));

		BotaoAtualizar = new JButton("");
		BotaoAtualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				preencherTabela("select * from bairro inner join cidade on bairro.id_cidade = cidade.id_cidade");
				AtualizaCombo();
			}
		});
		BotaoAtualizar.setIcon(new ImageIcon(FrmBairro.class.getResource("/imagens/Elegantes_0037_Refresh.png")));
		BotaoAtualizar.setToolTipText("ATUALIZAR");
		BotaoAtualizar.setBounds(674, 64, 59, 53);
		getContentPane().add(BotaoAtualizar);

		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] { "a", "b", "c", "d" }));
		comboBox.setBounds(38, 234, 28, 20);
		getContentPane().add(comboBox);

		jComboBoxCidade = new JComboBox();
		jComboBoxCidade.setEnabled(false);
		jComboBoxCidade.setBounds(346, 29, 405, 20);
		getContentPane().add(jComboBoxCidade);

		setVisible(true);
	}

	public void preencherTabela(String SQL) {
		ArrayList dados = new ArrayList();

		String[] Colunas = new String[] { "ID", "Nome", "Nome Cidade" };

		connBairro.conexao();
		connBairro.executaSQL(SQL);
		try {
			connBairro.rs.first();
			do {
				dados.add(new Object[] { connBairro.rs.getInt("id_bairro"), connBairro.rs.getString("nome_bairro"),
						connBairro.rs.getString("nome_cidade") });
			} while (connBairro.rs.next());
		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(rootPane, "Erro ao preencher o ArrayList!\n Erro: " + ex);
		}
		ModeloTabela modelo = new ModeloTabela(dados, Colunas);
		jTableBairro.setModel(modelo);
		jTableBairro.getColumnModel().getColumn(0).setPreferredWidth(115);
		jTableBairro.getColumnModel().getColumn(0).setResizable(false);
		jTableBairro.getColumnModel().getColumn(1).setPreferredWidth(380);
		jTableBairro.getColumnModel().getColumn(1).setResizable(false);
		jTableBairro.getColumnModel().getColumn(2).setPreferredWidth(115);
		jTableBairro.getColumnModel().getColumn(2).setResizable(false);
		jTableBairro.getTableHeader().setReorderingAllowed(false);
		jTableBairro.setAutoResizeMode(jTableBairro.AUTO_RESIZE_OFF);
		jTableBairro.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		connBairro.desconecta();

	}

	public void AtualizaCombo() {
		control.ListarCidades(mod);
		String[] lista;
		String[] strings = (String[]) mod.getListaCidades().toArray(new String[0]);
		jComboBoxCidade.removeAllItems();
	//	jComboBoxCidade.setModel(new DefaultComboBoxModel(strings));
		for(int i =0; i< mod.getListaCidades().size();i++){
			jComboBoxCidade.addItem( mod.getListaCidades().get(i));
			
		}
		

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new FrmBairro();
	}
}

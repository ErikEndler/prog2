package visao;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Rectangle;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import controle.ConectaBanco;
import controle.ControleCidade;
import modelo.ModeloCidade;
import modelo.ModeloTabela;

import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.table.DefaultTableModel;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class FrmCidades extends JFrame {

	ConectaBanco connCidade = new ConectaBanco();
	ModeloCidade mod = new ModeloCidade();
	ControleCidade control = new ControleCidade();
	ConectaBanco connEstado = new ConectaBanco();

	private JButton jButtonNovo, jButtonSalvar, jButtonEditar, jButtonExcluir, jButtonPrimeiro, jButtonUltimo,
			jButtonProximo, jButtonSair,BotaoAtualizar;
	private JButton jButtonAnterior, jButtonCancelar;
	private JTextField jTextFieldNome;
	private JTextField jTextFieldCodigo;
	private JLabel jLabelNome;
	private JLabel jLabelCodigo;
	private JLabel jLabelEstado;
	private JPanel contentPane;
	private JComboBox jComboBoxEstado;
	private JTable jTableCidade;
	private JScrollPane scrollPane;
	int flag = 1;

	public FrmCidades() {
		super();
		setTitle("Tela Cadastro Cidades");
		initialize();
		connEstado.conexao();
		connCidade.conexao();
		preencherTabela("select * from cidade inner join estados on cidade.id_estado = estados.id_estado");
		AtualizaCOmboBox();

	}

	private void initialize() {

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		// setBounds(x, y, largura, altura);
		setBounds(100, 100, 451, 520);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setVisible(true);

		jTextFieldCodigo = new JTextField();
		jTextFieldCodigo.setEditable(false);
		jTextFieldCodigo.setBounds(10, 61, 50, 20);
		contentPane.add(jTextFieldCodigo);
		jTextFieldCodigo.setColumns(20);

		jButtonSalvar = new JButton("");
		jButtonSalvar.setEnabled(false);
		jButtonSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (flag == 1) {
					mod.setNome(jTextFieldNome.getText());
					mod.setEstado(""+jComboBoxEstado.getSelectedItem());
					control.InserirCidade(mod);
				} else {
					mod.setCod(Integer.parseInt(jTextFieldCodigo.getText()));
					mod.setNome(jTextFieldNome.getText());
					mod.setEstado(""+jComboBoxEstado.getSelectedItem());
					control.AlterarCidade(mod);
					flag=1;
				}
				jButtonNovo.setEnabled(true);
				jTextFieldCodigo.setText("");
				jTextFieldNome.setText("");
				preencherTabela("select * from cidade inner join estados on cidade.id_estado = estados.id_estado");
			}
		});
		jButtonSalvar.setToolTipText("SALVAR");
		jButtonSalvar.setIcon(new ImageIcon(FrmCidades.class.getResource("/imagens/Botoes_5117_floppy_disk_48.png")));
		jButtonSalvar.setBounds(69, 147, 60, 50);
		contentPane.add(jButtonSalvar);

		jLabelCodigo = new JLabel("Codigo:");
		jLabelCodigo.setBounds(10, 42, 50, 20);
		contentPane.add(jLabelCodigo);

		jLabelNome = new JLabel("Nome");
		jLabelNome.setBounds(10, 81, 50, 20);
		contentPane.add(jLabelNome);

		jTextFieldNome = new JTextField();
		jTextFieldNome.setEnabled(false);
		jTextFieldNome.setBounds(10, 100, 176, 20);
		contentPane.add(jTextFieldNome);
		jTextFieldNome.setColumns(20);

		jComboBoxEstado = new JComboBox();
		jComboBoxEstado.setEnabled(false);
		jComboBoxEstado.setBounds(206, 100, 113, 20);
		contentPane.add(jComboBoxEstado);

		jLabelEstado = new JLabel("Estado");
		jLabelEstado.setBounds(206, 79, 50, 20);
		contentPane.add(jLabelEstado);

		jButtonNovo = new JButton("");
		jButtonNovo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				flag=1;
				jTextFieldCodigo.setText("");
				jTextFieldNome.setText("");
				jTextFieldNome.setEnabled(true);
				jButtonSalvar.setEnabled(true);
				jButtonEditar.setEnabled(false);
				jButtonExcluir.setEnabled(false);
				jButtonNovo.setEnabled(false);
				jButtonCancelar.setEnabled(true);
				jComboBoxEstado.setEnabled(true);
			}
		});
		jButtonNovo.setToolTipText("NOVO");
		jButtonNovo.setIcon(new ImageIcon(FrmCidades.class.getResource("/imagens/Botoes_5122_paper_48.png")));
		jButtonNovo.setBounds(10, 147, 60, 50);
		contentPane.add(jButtonNovo);

		jButtonEditar = new JButton("");
		jButtonEditar.setEnabled(false);
		jButtonEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jTextFieldNome.setEnabled(true);
				jComboBoxEstado.setEnabled(true);
				jButtonSalvar.setEnabled(true);
				jButtonNovo.setEnabled(false);
				jButtonExcluir.setEnabled(true);
				jButtonCancelar.setEnabled(true);
				jButtonEditar.setEnabled(false);

			}
		});
		jButtonEditar.setIcon(new ImageIcon(FrmCidades.class.getResource("/imagens/Botoes_5121_paperpencil_48.png")));
		jButtonEditar.setBounds(128, 147, 60, 50);
		contentPane.add(jButtonEditar);

		jButtonExcluir = new JButton("");
		jButtonExcluir.setEnabled(false);
		jButtonExcluir.setIcon(new ImageIcon(FrmCidades.class.getResource("/imagens/Lixeiras_1511_6127_32x32.png")));
		jButtonExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mod.setCod(Integer.parseInt(jTextFieldCodigo.getText()));
				control.ExcluirCidade(mod);
				jButtonEditar.setEnabled(false);
				jButtonExcluir.setEnabled(false);
				jButtonNovo.setEnabled(true);
				jButtonSalvar.setEnabled(false);
				jButtonCancelar.setEnabled(false);
				preencherTabela("select * from cidade inner join estados on cidade.id_estado = estados.id_estado");
			}
		});
		jButtonExcluir.setToolTipText("EXCLUIR");
		jButtonExcluir.setBounds(188, 147, 60, 50);
		contentPane.add(jButtonExcluir);

		jButtonCancelar = new JButton("");
		jButtonCancelar.setEnabled(false);
		jButtonCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				flag=1;
				jTextFieldCodigo.setText("");
				jTextFieldNome.setText("");
				jTextFieldNome.setEnabled(false);
				jButtonSalvar.setEnabled(false);
				jButtonEditar.setEnabled(false);
				jButtonExcluir.setEnabled(false);
				jButtonNovo.setEnabled(true);
				jButtonCancelar.setEnabled(false);
				jComboBoxEstado.setEnabled(false);
			}
		});
		jButtonCancelar.setToolTipText("CANCELAR");
		jButtonCancelar
				.setIcon(new ImageIcon(FrmCidades.class.getResource("/imagens/Botoes_Site_5750_Knob_Cancel.png")));
		jButtonCancelar.setBounds(248, 147, 60, 50);
		contentPane.add(jButtonCancelar);

		jButtonPrimeiro = new JButton("");
		jButtonPrimeiro.setVisible(false);
		jButtonPrimeiro.setEnabled(false);
		jButtonPrimeiro
				.setIcon(new ImageIcon(FrmCidades.class.getResource("/imagens/Botoes_Site_5740_Knob_Fast_Rewind.png")));
		jButtonPrimeiro.setToolTipText("Primeiro");
		jButtonPrimeiro.setBounds(96, 31, 50, 45);
		contentPane.add(jButtonPrimeiro);
		jButtonPrimeiro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jButtonEditar.setEnabled(true);
				jButtonExcluir.setEnabled(true);
				jTextFieldNome.setEnabled(true);
				jComboBoxEstado.setEnabled(true);
				try {
					connCidade.executaSQL("select * from cidade order by id_cidade");
					connCidade.rs.first();
					jTextFieldCodigo.setText(String.valueOf(connCidade.rs.getInt("id_cidade")));
					jTextFieldNome.setText(connCidade.rs.getString("nome_cidade"));
					connEstado.executaSQL(
							"select * from estados where id_estado='" + connCidade.rs.getInt("id_estado") + "'");
					connEstado.rs.first();
					jComboBoxEstado.setSelectedItem(connEstado.rs.getString("nome_estado"));
				} catch (SQLException ex) {
					JOptionPane.showMessageDialog(rootPane, "Erro ao setar o primeiro registro!\n Eroo : " + ex);
				}
			}
		});

		jButtonUltimo = new JButton("");
		jButtonUltimo.setVisible(false);
		jButtonUltimo.setEnabled(false);
		jButtonUltimo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jButtonEditar.setEnabled(true);
				jButtonExcluir.setEnabled(true);
				jTextFieldNome.setEnabled(true);
				jComboBoxEstado.setEnabled(true);
				try {
					connCidade.executaSQL("select * from cidade order by id_cidade");
					connCidade.rs.last();
					jTextFieldCodigo.setText(String.valueOf(connCidade.rs.getInt("id_cidade")));
					jTextFieldNome.setText(connCidade.rs.getString("nome_cidade"));
					connEstado.executaSQL(
							"select * from estados where id_estado='" + connCidade.rs.getInt("id_estado") + "'");
					connEstado.rs.first();
					jComboBoxEstado.setSelectedItem(connEstado.rs.getString("nome_estado"));
				} catch (SQLException ex) {
					JOptionPane.showMessageDialog(rootPane, "Erro ao setar o primeiro registro!\n Eroo : " + ex);
				}
			}
		});
		jButtonUltimo.setIcon(
				new ImageIcon(FrmCidades.class.getResource("/imagens/Botoes_Site_5742_Knob_Fast_Forward.png")));
		jButtonUltimo.setToolTipText("Ultimo");
		jButtonUltimo.setBounds(243, 31, 50, 45);
		contentPane.add(jButtonUltimo);

		jButtonProximo = new JButton("");
		jButtonProximo.setVisible(false);
		jButtonProximo.setEnabled(false);
		jButtonProximo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jButtonEditar.setEnabled(true);
				jButtonExcluir.setEnabled(true);
				jTextFieldNome.setEnabled(true);
				jComboBoxEstado.setEnabled(true);
				try {
					// connCidade.executaSQL("select * from cidade order by
					// id_cidade");
					connCidade.rs.next();
					jTextFieldCodigo.setText(String.valueOf(connCidade.rs.getInt("id_cidade")));
					jTextFieldNome.setText(connCidade.rs.getString("nome_cidade"));
					connEstado.executaSQL(
							"select * from estados where id_estado='" + connCidade.rs.getInt("id_estado") + "'");
					connEstado.rs.first();
					jComboBoxEstado.setSelectedItem(connEstado.rs.getString("nome_estado"));
				} catch (SQLException ex) {
					JOptionPane.showMessageDialog(rootPane, "Erro ao setar o primeiro registro!\n Eroo : " + ex);
				}
			}
		});
		jButtonProximo
				.setIcon(new ImageIcon(FrmCidades.class.getResource("/imagens/Botoes_Site_5749_Knob_Forward.png")));
		jButtonProximo.setToolTipText("Proximo");
		jButtonProximo.setBounds(194, 31, 50, 45);
		contentPane.add(jButtonProximo);

		jButtonAnterior = new JButton("");
		jButtonAnterior.setVisible(false);
		jButtonAnterior.setEnabled(false);
		jButtonAnterior.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jButtonEditar.setEnabled(true);
				jButtonExcluir.setEnabled(true);
				jTextFieldNome.setEnabled(true);
				jComboBoxEstado.setEnabled(true);
				try {
					// connCidade.executaSQL("select * from cidade order by
					// id_cidade");
					connCidade.rs.previous();
					jTextFieldCodigo.setText(String.valueOf(connCidade.rs.getInt("id_cidade")));
					jTextFieldNome.setText(connCidade.rs.getString("nome_cidade"));
					connEstado.executaSQL(
							"select * from estados where id_estado='" + connCidade.rs.getInt("id_estado") + "'");
					connEstado.rs.first();
					jComboBoxEstado.setSelectedItem(connEstado.rs.getString("nome_estado"));
				} catch (SQLException ex) {
					JOptionPane.showMessageDialog(rootPane, "Erro ao setar o primeiro registro!\n Eroo : " + ex);
				}
			}
		});
		jButtonAnterior.setIcon(new ImageIcon(FrmCidades.class.getResource("/imagens/Botoes_Site_5752_Knob_Left.png")));
		jButtonAnterior.setToolTipText("Anterior");
		jButtonAnterior.setBounds(145, 31, 50, 45);
		contentPane.add(jButtonAnterior);

		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				FrmEstado frm = new FrmEstado();
				frm.setVisible(true);
			}
		});
		btnAdd.setBounds(336, 99, 60, 23);
		contentPane.add(btnAdd);

		JLabel lblCadastroCidades = new JLabel("Cadastro Cidades");
		lblCadastroCidades.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblCadastroCidades.setBounds(108, 11, 211, 23);
		contentPane.add(lblCadastroCidades);

		scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(10, 224, 415, 245);
		getContentPane().add(scrollPane);
		jTableCidade = new JTable();
		jTableCidade.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				String IDcidade = "" + jTableCidade.getValueAt(jTableCidade.getSelectedRow(), 0);
				jButtonEditar.setEnabled(true);
				jButtonExcluir.setEnabled(true);
				jButtonCancelar.setEnabled(true);
				jButtonSalvar.setEnabled(false);
				jTextFieldNome.setEnabled(true);
				jComboBoxEstado.setEnabled(true);
				connCidade.conexao();
				connEstado.conexao();
				connCidade.executaSQL("select * from cidade where id_cidade='" + IDcidade + "'");
				try {
					connCidade.rs.first();
					jTextFieldCodigo.setText(String.valueOf(connCidade.rs.getInt("id_cidade")));
					jTextFieldNome.setText(connCidade.rs.getString("nome_cidade"));
					connEstado.executaSQL("select * from estados where id_estado=" + connCidade.rs.getInt("id_estado"));
					connEstado.rs.first();
					jComboBoxEstado.setSelectedItem(connEstado.rs.getString("nome_estado"));
					connEstado.desconecta();
					connCidade.desconecta();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		scrollPane.setViewportView(jTableCidade);
		jTableCidade.setModel(new DefaultTableModel(new Object[][] { {}, {}, {}, {} }, new String[] {}));

		jButtonSair = new JButton("Sair");
		jButtonSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		jButtonSair.setBounds(333, 174, 89, 23);
		contentPane.add(jButtonSair);
		
		BotaoAtualizar = new JButton("");
		BotaoAtualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AtualizaCOmboBox();
				preencherTabela("select * from cidade inner join estados on cidade.id_estado = estados.id_estado");
			}
		});
		BotaoAtualizar.setToolTipText("ATUALIZAR");
		BotaoAtualizar.setIcon(new ImageIcon(FrmCidades.class.getResource("/imagens/Elegantes_0037_Refresh.png")));
		BotaoAtualizar.setBounds(336, 36, 60, 45);
		contentPane.add(BotaoAtualizar);

	}

	public void preencherTabela(String SQL) {
		// cria arraylist q ira receber os dados do banco de dados
		ArrayList dados = new ArrayList();
		// string q recebe nome das colunas
		String[] Colunas = new String[] { "ID", "Cidade", "Estado", "ID" };
		connCidade.desconecta();
		connCidade.conexao();
		connCidade.executaSQL(SQL);
		try {
			connCidade.rs.first();
			// percorre banco dados inserindo os dados no arraylist
			do {
				dados.add(new Object[] { connCidade.rs.getInt("id_cidade"), connCidade.rs.getString("nome_cidade"),
						connCidade.rs.getString("nome_estado"), connCidade.rs.getInt("id_estado") });
			} while (connCidade.rs.next());
		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(rootPane, "Erro ao preencher o ArrayList!\n Erro: " + ex);
		}
		// seta o modelo da tabela q sera formada por "dados" e "colunas" q
		// foram feitos acima
		ModeloTabela modelo = new ModeloTabela(dados, Colunas);
		jTableCidade.setModel(modelo);
		jTableCidade.getColumnModel().getColumn(0).setPreferredWidth(50);
		jTableCidade.getColumnModel().getColumn(0).setResizable(false);
		jTableCidade.getColumnModel().getColumn(1).setPreferredWidth(200);
		jTableCidade.getColumnModel().getColumn(1).setResizable(false);
		jTableCidade.getColumnModel().getColumn(2).setPreferredWidth(115);
		jTableCidade.getColumnModel().getColumn(2).setResizable(false);
		jTableCidade.getColumnModel().getColumn(3).setPreferredWidth(50);
		jTableCidade.getColumnModel().getColumn(3).setResizable(false);
		jTableCidade.getTableHeader().setReorderingAllowed(false);
		jTableCidade.setAutoResizeMode(jTableCidade.AUTO_RESIZE_OFF);
		jTableCidade.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		connCidade.desconecta();

	}

	public void AtualizaCOmboBox() {
		connEstado.executaSQL("select * from estados order by nome_estado");
		jComboBoxEstado.removeAllItems();// remove todos os item da combobox
		try { // aqui a combobox é preenchida
			connEstado.rs.first();
			do {
				jComboBoxEstado.addItem(connEstado.rs.getString("nome_estado"));
			} while (connEstado.rs.next());
		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(rootPane, "Erro ao preencher combobox: " + ex);
		}
		connEstado.desconecta();
	}

	public static void main(String[] args) {
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new FrmCidades().setVisible(true);
			}
		});
	}
}

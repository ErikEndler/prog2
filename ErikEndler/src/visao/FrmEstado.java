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
import controle.ControleEstado;
import modelo.ModeloEstado;
import modelo.ModeloTabela;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

import javax.swing.ImageIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Color;

public class FrmEstado extends JFrame {
	private ArrayList lin;
	private JTextField jTextFielCodigo;
	private JTextField jTextFielNome;
	private JLabel jLabelSigla;
	private JTextField jTextFieldSigla;
	private JButton jButtonNovo, jButtonSalvar, jButtonEditar, jButtonExcluir, jButtonSair,jButtonCancelar;
	private JTable jTableEstado;
	private JScrollPane scrollPane;
	int flag = 1;
	
	ConectaBanco conecta = new ConectaBanco();
	ControleEstado control = new ControleEstado();
	ModeloEstado mod = new ModeloEstado();
	
	public FrmEstado() {
		super();
		setTitle("Tela Cadastro Estados");
		initialize();
		preencherTabela();
	}

	private void initialize() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);
		setBounds(100, 100, 541, 518);
		setResizable(false);

		JLabel jLabelCodigo = new JLabel("C\u00F3digo");
		jLabelCodigo.setFont(new Font("Tahoma", Font.PLAIN, 13));
		jLabelCodigo.setBounds(10, 57, 50, 20);
		getContentPane().add(jLabelCodigo);

		jTextFielCodigo = new JTextField();
		jTextFielCodigo.setDisabledTextColor(Color.BLACK);
		jTextFielCodigo.setEnabled(false);
		jTextFielCodigo.setBounds(10, 78, 86, 20);
		getContentPane().add(jTextFielCodigo);
		jTextFielCodigo.setColumns(10);

		JLabel jLabelNome = new JLabel("Nome");
		jLabelNome.setFont(new Font("Tahoma", Font.PLAIN, 13));
		jLabelNome.setBounds(10, 109, 50, 20);
		getContentPane().add(jLabelNome);

		jTextFielNome = new JTextField();
		jTextFielNome.setDisabledTextColor(Color.BLACK);
		jTextFielNome.setEnabled(false);
		jTextFielNome.setBounds(10, 130, 151, 20);
		getContentPane().add(jTextFielNome);
		jTextFielNome.setColumns(10);

		jLabelSigla = new JLabel("Sigla");
		jLabelSigla.setFont(new Font("Tahoma", Font.PLAIN, 13));
		jLabelSigla.setBounds(211, 113, 46, 14);
		getContentPane().add(jLabelSigla);

		jTextFieldSigla = new JTextField();
		jTextFieldSigla.setDisabledTextColor(Color.BLACK);
		jTextFieldSigla.setEnabled(false);
		jTextFieldSigla.setBounds(211, 130, 86, 20);
		getContentPane().add(jTextFieldSigla);
		jTextFieldSigla.setColumns(10);
		
		jButtonNovo = new JButton("");
		jButtonNovo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				jTextFielNome.setEnabled(true);
				jTextFieldSigla.setEnabled(true);
				jTextFielCodigo.setText("");
				jTextFieldSigla.setText("");
				jTextFielNome.setText("");
				jButtonSalvar.setEnabled(true);
				jButtonEditar.setEnabled(false);
				jButtonExcluir.setEnabled(false);
			}
		});
		jButtonNovo.setIcon(new ImageIcon(FrmEstado.class.getResource("/imagens/Botoes_5122_paper_48.png")));
		jButtonNovo.setToolTipText("NOVO");
		jButtonNovo.setBounds(10, 161, 60, 50);
		getContentPane().add(jButtonNovo);

		jButtonSalvar = new JButton("");
		jButtonSalvar.setEnabled(false);
		jButtonSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (flag == 1) {
					mod.setNome_estado(jTextFielNome.getText());
					mod.setSigla(jTextFieldSigla.getText());
					control.InserirEstado(mod);
				} else {
					mod.setCodigo(Integer.parseInt(jTextFielCodigo.getText()));
					mod.setNome_estado(jTextFielNome.getText());
					mod.setSigla(jTextFieldSigla.getText());
					control.ALterarEstado(mod);
					flag = 1;
				}
				jButtonNovo.setEnabled(true);
				jTextFieldSigla.setText("");
				jTextFielNome.setText("");
				jTextFielCodigo.setText("");
				jButtonSalvar.setEnabled(false);
				preencherTabela();
			}
		});
		jButtonSalvar.setIcon(new ImageIcon(FrmEstado.class.getResource("/imagens/Botoes_5117_floppy_disk_48.png")));
		jButtonSalvar.setToolTipText("SALVAR");
		jButtonSalvar.setBounds(69, 161, 60, 50);
		getContentPane().add(jButtonSalvar);

		jButtonEditar = new JButton("");
		jButtonEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				flag = 2;
				jButtonNovo.setEnabled(false);
				jButtonSalvar.setEnabled(true);
				jButtonExcluir.setEnabled(false);
				jButtonEditar.setEnabled(false);

			}
		});
		jButtonEditar.setIcon(new ImageIcon(FrmEstado.class.getResource("/imagens/Botoes_5121_app_48.png")));
		jButtonEditar.setToolTipText("EDITAR");
		jButtonEditar.setEnabled(false);
		jButtonEditar.setBounds(128, 161, 60, 50);
		getContentPane().add(jButtonEditar);

		jButtonExcluir = new JButton("");
		jButtonExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mod.setCodigo(Integer.parseInt(jTextFielCodigo.getText()));
				control.ExcluirEstado(mod);
				preencherTabela();
				jButtonEditar.setEnabled(false);
				jButtonSalvar.setEnabled(false);
				jButtonExcluir.setEnabled(false);
				jButtonNovo.setEnabled(true);
				jTextFielCodigo.setText("");
				jTextFieldSigla.setText("");
				jTextFielNome.setText("");

			}
		});
		jButtonExcluir.setIcon(new ImageIcon(FrmEstado.class.getResource("/imagens/Lixeiras_1511_6127_32x32.png")));
		jButtonExcluir.setToolTipText("EXCLUIR");
		jButtonExcluir.setEnabled(false);
		jButtonExcluir.setBounds(188, 161, 60, 50);
		getContentPane().add(jButtonExcluir);

		jButtonSair = new JButton("Sair");
		jButtonSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		jButtonSair.setToolTipText("SAIR");
		jButtonSair.setBounds(458, 181, 60, 30);
		getContentPane().add(jButtonSair);

		JLabel jLabelTitulo = new JLabel("Formulario Cadastro Estados");
		jLabelTitulo.setFont(new Font("Tahoma", Font.PLAIN, 18));
		jLabelTitulo.setBounds(33, 11, 239, 30);
		getContentPane().add(jLabelTitulo);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 233, 508, 236);
		getContentPane().add(scrollPane);
		jTableEstado = new JTable();
		jTableEstado.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				String id_estado = "" + jTableEstado.getValueAt(jTableEstado.getSelectedRow(), 0);
				jButtonEditar.setEnabled(true);
				jButtonExcluir.setEnabled(true);
				jTextFieldSigla.setEnabled(true);
				jTextFielNome.setEnabled(true);
				jButtonSalvar.setEnabled(false);
				conecta.conexao();
				conecta.executaSQL("select * from estados where id_estado='" + id_estado + "'");
				try {
					conecta.rs.first();
					jTextFielCodigo.setText(String.valueOf(conecta.rs.getInt("id_estado")));
					jTextFieldSigla.setText(conecta.rs.getString("sigla_estado"));
					jTextFielNome.setText(conecta.rs.getString("nome_estado"));
					conecta.desconecta();
				} catch (SQLException e) {
					JOptionPane.showMessageDialog(rootPane, "Erro ao selecionar dados!\n Erro : " + e);
				}

			}
		});
		scrollPane.setViewportView(jTableEstado);
		jTableEstado.setModel(new DefaultTableModel(new Object[][] { {}, {}, {}, {} }, new String[] {}));
		
		jButtonCancelar = new JButton("");
		jButtonCancelar.setEnabled(false);
		jButtonCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jTextFielCodigo.setText("");
				jTextFieldSigla.setText("");
				jTextFielNome.setText("");
				jButtonNovo.setEnabled(true);
				jButtonSalvar.setEnabled(false);
				jButtonEditar.setEnabled(false);
				jButtonExcluir.setEnabled(false);
				jButtonCancelar.setEnabled(false);
			}
		});
		jButtonCancelar.setToolTipText("Cancelar");
		jButtonCancelar.setIcon(new ImageIcon(FrmEstado.class.getResource("/imagens/Botoes_Site_5750_Knob_Cancel.png")));
		jButtonCancelar.setBounds(252, 161, 60, 50);
		getContentPane().add(jButtonCancelar);

		setVisible(true);
	}

	public void preencherTabela() {
		control.AA(mod);
		
		String[] col = mod.getCol();
		ArrayList lin = mod.getLin();
		ModeloTabela modelo = new ModeloTabela(lin, col);
		
		jTableEstado.setModel(modelo);
		jTableEstado.getColumnModel().getColumn(0).setPreferredWidth(115);
		jTableEstado.getColumnModel().getColumn(0).setResizable(false);
		jTableEstado.getColumnModel().getColumn(1).setPreferredWidth(280);
		jTableEstado.getColumnModel().getColumn(1).setResizable(false);
		jTableEstado.getColumnModel().getColumn(2).setPreferredWidth(115);
		jTableEstado.getColumnModel().getColumn(2).setResizable(false);
		jTableEstado.getTableHeader().setReorderingAllowed(false);
		jTableEstado.setAutoResizeMode(jTableEstado.AUTO_RESIZE_OFF);
		jTableEstado.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new FrmEstado();
	}
}

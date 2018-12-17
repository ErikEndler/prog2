package visao;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;

//import controle.ConectaBanco;

public class TelaPrincipal extends JFrame {

	//ConectaBanco conecta = new ConectaBanco();

	private JMenuBar jMenuBar1;
	private JMenu jMenu1, jMenu2, jMenu3, jMenu4;
	private JMenuItem jMenuItem1, jMenuItem2, jMenuItem3,jMenuItem4;

	public TelaPrincipal() {
		super();
		initialize();
		// conecta.conexao();
	}

	private void initialize() {

		setTitle("Tela inicial");
		setJMenuBar(getBarraMenu());
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setSize(500, 400);
		// setLocationRelativeTo(null);
		setVisible(true);
	}

	private JMenuBar getBarraMenu() {
		jMenuBar1 = new JMenuBar();
		jMenuBar1.add(getjMenu1());
		jMenuBar1.add(getjMenu2());
		jMenuBar1.add(getjMenu3());
		jMenuBar1.add(getjMenu4());

		return jMenuBar1;
	}

	private JMenu getjMenu1() {
		jMenu1 = new JMenu();
		jMenu1.setText("Cadastro");
		jMenu1.add(getjMenuItem1());
		jMenu1.add(getjMenuItem2());
		jMenu1.add(getjMenuItem3());
		jMenu1.add(getjMenuItem4());
		return jMenu1;
	}

	private JMenu getjMenu2() {
		jMenu2 = new JMenu();
		jMenu2.setText("Compra");
		return jMenu2;
	}

	private JMenu getjMenu3() {
		jMenu3 = new JMenu();
		jMenu3.setText("Vendas");
		return jMenu3;
	}

	private JMenu getjMenu4() {
		jMenu4 = new JMenu();
		jMenu4.setText("Sair");
		jMenu4.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent evt) {
				// conecta.desconecta();
				System.exit(0);
			}
		});
		return jMenu4;
	}

	private JMenuItem getjMenuItem1() {
		jMenuItem1 = new JMenuItem();
		jMenuItem1.setText("Estados");
		jMenuItem1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				//FrmEstado frm = new FrmEstado();
			}
		});
		return jMenuItem1;
	}

	private JMenuItem getjMenuItem2() {
		jMenuItem2 = new JMenuItem();
		jMenuItem2.setText("Cidades");
		jMenuItem2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				//FrmCidades frm1 = new FrmCidades();
			}
		});
		return jMenuItem2;
	}
	
	private JMenuItem getjMenuItem3() {
		jMenuItem3 = new JMenuItem();
		jMenuItem3.setText("Bairros");
		jMenuItem3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				//FrmBairro frm = new FrmBairro();
			}
		});
		return jMenuItem3;
	}
	
	private JMenuItem getjMenuItem4() {
		jMenuItem4 = new JMenuItem();
		jMenuItem4.setText("Cliente");
		jMenuItem4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				//FrmCliente frm2 = new FrmCliente();
			}
		});
		return jMenuItem4;
	}

	/*
	 * { jMenuBar1.add(jMenu2); jMenu2.setText("Compra");
	 * 
	 * jMenuBar1.add(jMenu3); jMenu3.setText("Vendas");
	 * 
	 * jMenuBar1.add(jMenu4); jMenu4.setText("Sair");
	 * jMenu4.addMouseListener(new MouseAdapter() { public void
	 * mouseClicked(MouseEvent evt){ conecta.desconecta(); System.exit(0); } });
	 * 
	 * jMenu1.add(jMenuItem1); jMenuItem1.setText("Estados");
	 * jMenuItem1.addActionListener(new ActionListener() { public void
	 * actionPerformed(ActionEvent evt) { FrmEstado frm = new FrmEstado(); } });
	 * 
	 * jMenu1.add(jMenuItem2); jMenuItem2.setText("Cidades");
	 * jMenuItem1.addActionListener(new ActionListener() { public void
	 * actionPerformed(ActionEvent evt) { FrmCidades frm = new FrmCidades(); }
	 * });
	 * 
	 * }
	 */

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new TelaPrincipal();
	}

}

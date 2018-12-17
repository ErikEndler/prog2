package visao;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import java.awt.event.*;
import javax.swing.border.*;

import controle.SalvarCrud;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import javax.swing.*;
import visao.DadosAgenda;

public class Principal extends JFrame{
	private JMenuBar BarraMenu = null;
	private JMenu mnuArquivo = null;
	private JMenuItem mnuEditar = null;
	private JMenuItem mnuSair = null;
	private JMenuItem mnuSalvar = null;
	private DadosAgenda agenda;
	public Principal() {
		super();
		// TODO Auto-generated constructor stub
		initialize();
	}
	private void initialize(){
		this.setTitle("Aplicação com menu");
		this.setJMenuBar(getBarraMenu());
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		this.setSize(360,280);
		//agenda = new DadosAgenda();
		//getContentPane().add(agenda);
		this.setVisible(true);
		
	}
	private JMenuBar getBarraMenu() {
		if (BarraMenu == null){
			BarraMenu = new JMenuBar();
			BarraMenu.add(getMnuArquivo());
		}
		return BarraMenu;
	}
	
	private JMenu getMnuArquivo() {
		if (mnuArquivo == null){
			mnuArquivo = new JMenu();
			mnuArquivo.setText("Arquivo");
			mnuArquivo.add(getMnuSalvar());
			mnuArquivo.add(getMnuEditar());
			mnuArquivo.add(getMnuSair());
		}
		return mnuArquivo;
	}
	
	private JMenuItem getMnuEditar() {
		if (mnuEditar == null){
			mnuEditar = new JMenuItem();
			mnuEditar.setText("Editar");
		}
		return mnuEditar;
	}
	
	private JMenuItem getMnuSalvar() {
		if (mnuSalvar == null){
			mnuSalvar = new JMenuItem();
			mnuSalvar.setText("Salvar");
			mnuSalvar.addActionListener(new ActionListener() { 
				public void actionPerformed(ActionEvent f) {
					JOptionPane.showMessageDialog(null,"Arquivo Salvo");				   
					SalvarCrud salvar;
					salvar = new SalvarCrud();
					salvar.Receber(agenda.getFieldNome().getText(),
							agenda.gettEndereço().getText(),
							agenda.gettBairro().getText(),
							agenda.gettCidade().getText(),
							agenda.gettEstado().getText(),
							agenda.gettTelefone().getText(),
							agenda.gettCelular().getText(),
							agenda.gettEmail().getText());
					try {
						salvar.SalvarDados();
					}catch(Exception erro){
						JOptionPane.showMessageDialog(null,"Erro ao salvar");
					}
				}
			});
		}
		return mnuSalvar;
	}
	
	private JMenuItem getMnuSair() {
		if (mnuSair == null){
			mnuSair = new JMenuItem();
			mnuSair.setText("Sair");
			
			mnuSair.addActionListener(new ActionListener() { 
				public void actionPerformed(ActionEvent e) {
				    System.out.println("fechando");
				    System.exit(0);
				}
			});
		}
		return mnuSair;
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
                new Principal();
	}
}

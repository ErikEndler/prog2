package visao;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.*;

import controle.ConectaBanco;

class DadosAgenda extends JPanel {
	 ConectaBanco conecta = new ConectaBanco(); // instacia da classe de conexao
	
	JButton jButtonSalvar,jButtonCancelar,jButtonNovo,jButtonEditar;
	
	JTextField caixaEntrada,jTextFieldNome,jTextFieldEndereço,jTextFieldBairro,jTextFieldCidade;
	JTextField jTextFieldEstado,jTextFieldTelefone,jTextFieldCelular,jTextFieldEmail;
	JLabel texto;
    JLabel[] labels;
    JTextField[] fields;
    //atentar para boxLayout
	public DadosAgenda() {
		conecta.conexao(); // chamada do metodo de conexao
		texto = new JLabel("AGENDA - CADASTRO",SwingConstants.CENTER);
		JPanel grid = new JPanel(new GridLayout(8, 2));
		
		labels = new JLabel[8];
	    labels[0] = new JLabel("Nome");
	    labels[1] = new JLabel("Endereco");
	    labels[2] = new JLabel("Bairro");
	    labels[3] = new JLabel("Cidade");
	    labels[4] = new JLabel("Estado");
	    labels[5] = new JLabel("Telefone");
	    labels[6] = new JLabel("Celular");
	    labels[7] = new JLabel("Email");
	    
	    jTextFieldNome = new JTextField(50);
	    grid.add(labels[0]);
	    grid.add(jTextFieldNome);
	    jTextFieldEndereço = new JTextField(50);
	    grid.add(labels[1]);
	    grid.add(jTextFieldEndereço);
	    jTextFieldBairro = new JTextField(50);
	    grid.add(labels[2]);
	    grid.add(jTextFieldBairro);
	    jTextFieldCidade = new JTextField(50);
	    grid.add(labels[3]);
	    grid.add(jTextFieldCidade);
	    jTextFieldEstado = new JTextField(50);
	    grid.add(labels[4]);
	    grid.add(jTextFieldEstado);
	    jTextFieldTelefone = new JTextField(50);
	    grid.add(labels[5]);
	    grid.add(jTextFieldTelefone);
	    jTextFieldCelular = new JTextField(50);
	    grid.add(labels[6]);
	    grid.add(jTextFieldCelular);
	    jTextFieldEmail = new JTextField(50);
	    grid.add(labels[7]);
	    grid.add(jTextFieldEmail);
	   
		jButtonSalvar = new JButton("Salvar");
		jButtonCancelar = new JButton("Cancelar");
		jButtonNovo = new JButton("Novo");
		jButtonEditar = new JButton("Editar");
		JPanel pBotoes = new JPanel(new GridLayout(1, 4));
		pBotoes.add(jButtonNovo);
		pBotoes.add(jButtonSalvar);
		pBotoes.add(jButtonEditar);
		pBotoes.add(jButtonCancelar);
		
		setLayout(new BorderLayout());
		add("North",texto);
		add("Center", grid);
		add("South", pBotoes);
	
		jButtonNovo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
			}
		});
		                     
		//AcaoBotao acao = new AcaoBotao();
		jButtonSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent f) {
				JOptionPane.showMessageDialog(null,"Arquivo Salvo");	
				try {
		            PreparedStatement pst = conecta.conn.prepareStatement("insert into cliente (nome_cliente,telefone_cliente)values(?,?)");//passagem do sql para inserçao
		            pst.setString(1, jTextFieldNome.getText()); // passagem dos parametros
		            pst.setString(2, jTextFieldTelefone.getText());
		            pst.execute(); // executa a inserçao
		            JOptionPane.showMessageDialog(null, "Salvo com sucesso!");
		           // preencherTabela("select * from estados order by id_estado");
		        } catch (SQLException ex) {
		            JOptionPane.showMessageDialog(null, "Erro ao cadastrar!\n Erro: " + ex);
		        }
				/*SalvarCrud salvar;
				salvar = new SalvarCrud();
				salvar.Receber(getFieldNome().getText(),
						gettEndereço().getText(),
						gettBairro().getText(),
						gettCidade().getText(),
						gettEstado().getText(),
						gettTelefone().getText(),
						gettCelular().getText(),
						gettEmail().getText());
				try {
					salvar.SalvarDados();
					//botao2.doClick();
				}catch(Exception erro){
					JOptionPane.showMessageDialog(null,"Erro ao salvar");
				}*/
			}
		});
		jButtonCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent f) {
				jTextFieldNome.setText(" ");
				jTextFieldEndereço.setText("");
				jTextFieldBairro.setText(" ");
				jTextFieldCidade.setText(" ");
				jTextFieldEstado.setText(" ");
				jTextFieldTelefone.setText(" ");
				jTextFieldCelular.setText(" ");
				jTextFieldEmail.setText(" ");
				jButtonSalvar.setEnabled(false);
				
			}
		});
		}
/*private class AcaoBotao implements ActionListener {
		
		public void actionPerformed(ActionEvent evento) {
			if(evento.getSource()==botao1){
				
				JOptionPane.showMessageDialog(null,"Arquivo Salvo");				   
				SalvarCrud salvar;
				salvar = new SalvarCrud();
				salvar.Receber(getFieldNome().getText(),
						gettEndereço().getText(),
						gettBairro().getText(),
						gettCidade().getText(),
						gettEstado().getText(),
						gettTelefone().getText(),
						gettCelular().getText(),
						gettEmail().getText());
				try {
				salvar.SalvarDados();
				botao2.doClick();
			}catch(Exception erro){
				JOptionPane.showMessageDialog(null,"Ago de errado N esta certo");
			}
			
			
			
		}
		if(evento.getSource()==botao2){
			int i;
			for(i=0;i<8;i++){
				fields[i].setText(" ");
			}
		}
		}
	}*/
	public JTextField getFieldNome(){
		return jTextFieldNome;
	}
	public JTextField gettEndereço() {
		return jTextFieldEndereço;
	}
	public JTextField gettBairro() {
		return jTextFieldBairro;
	}
	public JTextField gettCidade() {
		return jTextFieldCidade;
	}
	public JTextField gettEstado() {
		return jTextFieldEstado;
	}
	public JTextField gettTelefone() {
		return jTextFieldTelefone;
	}
	public JTextField gettCelular() {
		return jTextFieldCelular;
	}
	public JTextField gettEmail() {
		return jTextFieldEmail;
	}

		
}
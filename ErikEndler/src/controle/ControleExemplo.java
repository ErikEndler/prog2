package controle;

import java.io.*;
import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

//transferir cada modelo a ser salvo para um vetor
//inserir esse vetor no arquivo txt /vetor estara numa array list

import javax.swing.JOptionPane;
import javax.swing.JTextPane;

import modelo.ModeloExemplo;
import visao.Exemplo;

public class ControleExemplo {

	ModeloExemplo mod = new ModeloExemplo();

	File arquivo;
	BufferedReader br;
	BufferedWriter bw;
	PrintWriter pw;
	FileReader fr;
	FileWriter fw;
	ArrayList<ModeloExemplo> cadastro = new ArrayList<ModeloExemplo>();
	
	public void inserir(ModeloExemplo mod){
		cadastro.add(mod);
		
	}

	public void PrepararSalvar() {
		inserir(mod);
		try {
			fw = new FileWriter("C:\\erikteste\\fxdd.txt", true);
			bw = new BufferedWriter(fw);
			pw = new PrintWriter(fw);

			pw.println(cadastro);
			pw.flush();
			pw.close();
			fw.close();
			JOptionPane.showMessageDialog(null, "Dados inseridos com sucesso!");
			for (ModeloExemplo m : cadastro) {
				JOptionPane.showMessageDialog(null, m.toString());
			}

		} catch (IOException ex) {
			JOptionPane.showMessageDialog(null, "Erro ao Salvar!\n Erro :  " + ex);
		}

	}

	public void Salvar(ModeloExemplo mod) {
		// String caminho = JOptionPane.showInputDialog("DIgite o caminho do
		// arquivo");
		// arquivo = new File("C:\\erikteste\\fxdd.txt");
		try {
			// if(!arquivo.exists()){
			// arquivo.createNewFile();
			// }
			fw = new FileWriter("C:\\erikteste\\fxdd.txt");
			bw = new BufferedWriter(fw);

			bw.write(mod.getNome() + "; ");
			// bw.newLine();
			bw.write(mod.getEstado() + "; ");
			// bw.newLine();
			bw.write(mod.getCidade() + "; ");
			// bw.newLine();
			bw.write(mod.getBairro() + "; ");
			// bw.newLine();
			bw.write(mod.getTelefone() + "; ");

			bw.close();
			fw.close();
			JOptionPane.showMessageDialog(null, "Dados inseridos com sucesso!");
		} catch (IOException ex) {
			JOptionPane.showMessageDialog(null, "Erro ao Salvar!\n Erro :  " + ex);
		}

	}

	public void LerArquivo(ModeloExemplo mod) {
		try {
			fr = new FileReader("C:\\erikteste\\fxdd.txt");
			br = new BufferedReader(fr);
			List<String> result = new ArrayList<String>();
			String linha="";
			while ((linha = br.readLine()) != null){
				result.add(linha);
				JOptionPane.showMessageDialog(null,"linha contem : "+ linha);
			}
			fr.close();
			br.close();
			//JOptionPane.showMessageDialog(null, result.toString());
			for (String s : result) {
                //Usamos o método split da classe String
                // para separar as partes entre os ponto e vírgulas.
                //Guardamos o resultado em um array
                String[] user = s.split("#");
             //   JOptionPane.showMessageDialog(null,"user é = "+ user.toString());
                

                //Criamos um objeto User e setamos em seus atributos
                //as posições correspondentes do array
                mod.setNome(user[0]);
                mod.setCidade(user[2]);
                mod.setEstado(user[1]);
                mod.setBairro(user[3]);  
                mod.setTelefone(user[4]); 
                JOptionPane.showMessageDialog(null, user.toString());
                
                inserir(mod);   
                JOptionPane.showMessageDialog(null, "cadastro é : "+cadastro);
            }
	

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private JTextPane Appendable(ModeloExemplo modeloExemplo) {
		// TODO Auto-generated method stub
		return null;
	}

	public void Editar(ModeloExemplo mod) {
		try {
			fr = new FileReader("C:\\erikteste\\fxdd.txt");
			br = new BufferedReader(fr);
			while (br.ready()) {
				mod.setNome(br.readLine());
				mod.setEstado(br.readLine());
				mod.setCidade(br.readLine());
				mod.setBairro(br.readLine());
				mod.setTelefone(br.readLine());

			}
			br.close();
			fr.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}

package controle;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class SalvarCrud {

	private String p[],nome,telefone,bairro,endereco,celular,cidade,estado,email;
	
	public SalvarCrud(){
		
	}
	
	public void SalvarDados()throws IOException{
		FileWriter arq =new  FileWriter("C:\\erikteste\\fxdd.txt");
	    PrintWriter gravarArq = new PrintWriter(arq);
	    gravarArq.println("Nome.......: "+this.nome);
	    gravarArq.println("Endereco...: "+this.endereco);
	    gravarArq.println("Bairro.....: "+this.bairro);
	    gravarArq.println("Cidade.....: "+this.cidade);
	    gravarArq.println("Estado.....: "+this.estado);
	    gravarArq.println("Telefone...: "+this.telefone);
	    gravarArq.println("Celular....: "+this.celular);
	    gravarArq.println("Email......: "+this.email);
        gravarArq.close();
	}
	public void Receber(String nome,String endereco,String bairro,String cidade
			,String estado,String telefone,String celular,String email){
		
		this.nome = nome;
		this.endereco = endereco;
		this.bairro = bairro;
		this.cidade = cidade;
		this.estado = estado;
		this.telefone = telefone;
		this.celular = celular;
		this.email = email;
	}


}

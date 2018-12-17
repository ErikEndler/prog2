package modelo;

import java.util.ArrayList;
import java.util.List;

public class ModeloBairro {
	private int cod;
    private String nome;
    private String cidade;
    private ArrayList listaCidades;
    
	public int getCod() {
		return cod;
	}
	public void setCod(int cod) {
		this.cod = cod;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCidade() {
		return cidade;
	}
	public	 void setCidade(String cidade) {
		this.cidade = cidade;
	}
	public ArrayList getListaCidades() {
		return listaCidades;
	}
	public void setListaCidades(ArrayList listaCidades) {
		this.listaCidades = listaCidades;
	}
	
}

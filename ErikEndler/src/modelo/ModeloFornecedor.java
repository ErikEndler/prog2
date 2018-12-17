package modelo;

import java.util.ArrayList;

public class ModeloFornecedor {
	private int ID;
	private String nome;
	private String telefone;
	private ArrayList lin;
	private String[] col;
	
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public String[] getCol() {
		return col;
	}
	public void setCol(String[] col) {
		this.col = col;
	}
	public ArrayList getLin() {
		return lin;
	}
	public void setLin(ArrayList lin) {
		this.lin = lin;
	}

}

package modelo;

import java.util.ArrayList;

public class ModeloEstado {
	
	private int codigo;
	private String nome_estado;
	private String sigla;
	private ArrayList lin;
	private String[] col;
	
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public String getNome_estado() {
		return nome_estado;
	}
	public void setNome_estado(String nome_estado) {
		this.nome_estado = nome_estado;
	}
	public String getSigla() {
		return sigla;
	}
	public void setSigla(String sigla) {
		this.sigla = sigla;
	}
	public ArrayList getLin() {
		return lin;
	}
	public void setLin(ArrayList lin) {
		this.lin = lin;
	}
	public String[] getCol() {
		return col;
	}
	public void setCol(String[] col) {
		this.col = col;
	}

}

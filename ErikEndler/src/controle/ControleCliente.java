package controle;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import modelo.ModeloCliente;

public class ControleCliente {

	ConectaBanco connEstado = new ConectaBanco();
	ConectaBanco connCidade = new ConectaBanco();
	ConectaBanco connBairro = new ConectaBanco();
	ConectaBanco conex = new ConectaBanco();

	int codCIdade, codEstado, codBairro;

	public void Salvar(ModeloCliente mod) {
		connEstado.conexao();
		connCidade.conexao();
		connBairro.conexao();
		conex.conexao();
		try {
			connEstado.executaSQL("select * from estados where nome_estado ='" + mod.getEstado() + "'");
			connEstado.rs.first();
			codEstado = connEstado.rs.getInt("id_estado");
			// JOptionPane.showMessageDialog(null, codEstado);
			connCidade.executaSQL("select * from cidade where nome_cidade ='" + mod.getCidade() + "'");
			connCidade.rs.first();
			codCIdade = connCidade.rs.getInt("id_cidade");
			connBairro.executaSQL("select * from bairro where nome_bairro ='" + mod.getBairro() + "'");
			connBairro.rs.first();
			codBairro = connBairro.rs.getInt("id_bairro");
			PreparedStatement pst = conex.conn.prepareStatement(
					"insert into cliente(nome_cliente, telefone_cliente, cpf, id_estado,id_cidade, id_bairro)values(?,?,?,?,?,?)");
			pst.setString(1, mod.getNome());
			pst.setString(2, mod.getTelefone());
			pst.setString(3, mod.getCpf());
			pst.setInt(4, codEstado);
			pst.setInt(5, codCIdade);
			pst.setInt(6, codBairro);
			pst.execute();
			JOptionPane.showMessageDialog(null, "Dados inseridos com sucesso!");

		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "Erro na inserçao dos dados!\n Erro : " + ex);
		}
		connEstado.desconecta();
		connCidade.desconecta();
		connBairro.desconecta();
		conex.desconecta();

	}
	
	public void Editar(ModeloCliente mod){
		connEstado.conexao();
		connCidade.conexao();
		connBairro.conexao();
		conex.conexao();
		try {
			connEstado.executaSQL("select * from estados where nome_estado ='" + mod.getEstado() + "'");
			connEstado.rs.first();
			codEstado = connEstado.rs.getInt("id_estado");
			// JOptionPane.showMessageDialog(null, codEstado);
			connCidade.executaSQL("select * from cidade where nome_cidade ='" + mod.getCidade() + "'");
			connCidade.rs.first();
			codCIdade = connCidade.rs.getInt("id_cidade");
			connBairro.executaSQL("select * from bairro where nome_bairro ='" + mod.getBairro() + "'");
			connBairro.rs.first();
			codBairro = connBairro.rs.getInt("id_bairro");
			PreparedStatement pst = conex.conn.prepareStatement(
					"update cliente set nome_cliente=?, telefone_cliente=?, cpf=?, id_estado=?,id_cidade=?, id_bairro=? where id_cliente=?");
			pst.setString(1, mod.getNome());
			pst.setString(2, mod.getTelefone());
			pst.setString(3, mod.getCpf());
			pst.setInt(4, codEstado);
			pst.setInt(5, codCIdade);
			pst.setInt(6, codBairro);
			pst.setInt(7, mod.getCodigo());
			pst.execute();
			JOptionPane.showMessageDialog(null, "Dados Alterados com sucesso!");

		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "Erro na alteraçao dos dados!\n Erro : " + ex);
		}
		connEstado.desconecta();
		connCidade.desconecta();
		connBairro.desconecta();
		conex.desconecta();
		
	}
	public void Excluir(ModeloCliente mod){
		conex.conexao();
		PreparedStatement pst;
		try {
			pst = conex.conn.prepareStatement("delete from cliente where id_cliente=?");
			pst.setInt(1, mod.getCodigo());
			pst.execute();
			JOptionPane.showMessageDialog(null, "Dados Excluidos com sucesso!");
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro ao excluir dados : " + e);
		}
		conex.desconecta();
	}
}

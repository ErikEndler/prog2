package controle;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import modelo.ModeloFornecedor;

public class ControleFornecedor {
	ConectaBanco connForn = new ConectaBanco();
	ArrayList lin;
	String[] col;

	public void Salvar(ModeloFornecedor mod) {
		connForn.conexao();
		try {
			PreparedStatement pst = connForn.conn
					.prepareStatement("insert into fornecedor(id_fornecedor,nome,telefone)values(?,?,?)");
			pst.setInt(1, mod.getID());
			pst.setString(2, mod.getNome());
			pst.setString(3, mod.getTelefone());
			pst.execute();
			JOptionPane.showMessageDialog(null, "Dados inseridos com sucesso!");
			JOptionPane.showMessageDialog(null, mod.getID());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "ERRO ao inserir dados!\n" + e);
		}
		connForn.desconecta();
	}
	public void Editar(ModeloFornecedor mod){
		connForn.conexao();
		try {
			PreparedStatement pst = connForn.conn
					.prepareStatement("update fornecedor set nome=?, telefone=?, id_fornecedor=? where id_fornecedor=?");
			pst.setString(1, mod.getNome());
			pst.setString(2, mod.getTelefone());
			pst.setInt(4, mod.getID());
			pst.setInt(3, mod.getID());
			pst.execute();
			JOptionPane.showMessageDialog(null, "Alterado com sucesso!");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "Erro ao Editar dados!\n Erro: " + e);
		}
		connForn.desconecta();
	}
	
	public void PreencherTabela(ModeloFornecedor mod){
		lin = new ArrayList();
		col = new String[] { "ID", "Nome", "Telefone" };
		connForn.conexao();
		connForn.executaSQL("select * from fornecedor order by id_fornecedor");
		try {
			connForn.rs.first();
			do{
				lin.add(new Object[]{connForn.rs.getInt("id_fornecedor"),connForn.rs.getString("nome"),connForn.rs.getString("telefone")});
			}while(connForn.rs.next());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		mod.setCol(col);
		mod.setLin(lin);
		connForn.desconecta();
		
	}

}

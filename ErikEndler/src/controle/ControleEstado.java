package controle;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import modelo.ModeloEstado;

public class ControleEstado {
	ArrayList lin;
	String[] col;
	ConectaBanco connEstado = new ConectaBanco();
	//ModeloEstado modT = new ModeloEstado();

	public void InserirEstado(ModeloEstado mod) {
		connEstado.conexao();
		try {
			PreparedStatement pst = connEstado.conn
					.prepareStatement("insert into estados(nome_estado,sigla_estado)values(?,?)");
			pst.setString(1, mod.getNome_estado());
			pst.setString(2, mod.getSigla());
			pst.execute();
			JOptionPane.showMessageDialog(null, "Dados inseridos com sucesso!");
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro ao inserir dados!\n Erro: " + e);
		}
		connEstado.desconecta();
	}

	public void ExcluirEstado(ModeloEstado mod) {
		connEstado.conexao();
		try {
			PreparedStatement pst = connEstado.conn.prepareStatement("delete from estados where id_estado= ?");
			pst.setInt(1, mod.getCodigo());
			pst.execute();
			JOptionPane.showMessageDialog(null, "Excluido com sucesso!");
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro ao Excluir dados!\n Erro: " + e);
		}
		connEstado.desconecta();
	}

	public void ALterarEstado(ModeloEstado mod) {
		connEstado.conexao();
		try {
			PreparedStatement pst = connEstado.conn
					.prepareStatement("update estados set nome_estado =?, sigla_estado=? where id_estado=?");
			pst.setString(1, mod.getNome_estado());
			pst.setString(2, mod.getSigla());
			pst.setInt(3, mod.getCodigo());
			pst.execute();
			JOptionPane.showMessageDialog(null, "Alterado com sucesso!");
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro ao Editar dados!\n Erro: " + e);
		}
		connEstado.desconecta();
	}

	public  void AA(ModeloEstado mod) {
		lin = new ArrayList();
		col = new String[] { "ID", "Nome", "Sigla" };

		connEstado.conexao();
		connEstado.executaSQL("select * from estados order by id_estado");
		try {
			connEstado.rs.first();
			do {
				
				lin.add(new Object[] { connEstado.rs.getInt("id_estado"), connEstado.rs.getString("nome_estado"),
						connEstado.rs.getString("sigla_estado") });
			} while (connEstado.rs.next());
		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "Erro ao preencher o ArrayList!\n Erro: " + ex);
		}
		mod.setCol(col);
		mod.setLin(lin);
		connEstado.desconecta();
	}

}
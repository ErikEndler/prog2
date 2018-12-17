package controle;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import modelo.ModeloCidade;

public class ControleCidade {

	ConectaBanco connCidade = new ConectaBanco();
	int codEst;

	public void InserirCidade(ModeloCidade mod) {
		//conecta com banco de dados
		connCidade.conexao();
		try {
			connCidade.executaSQL("select * from estados where nome_estado='"+mod.getEstado()+"'");
			connCidade.rs.first();
			codEst = connCidade.rs.getInt("id_estado");
			PreparedStatement pst = connCidade.conn.prepareStatement("insert into cidade(nome_cidade,id_estado)values(?,?)");
			pst.setString(1, mod.getNome());
			pst.setInt(2, codEst);
			pst.execute();
			JOptionPane.showMessageDialog(null, "Dados inseridos com sucesso!");
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro ao inserir dados!\n Erro: " + e);
		}
		connCidade.desconecta();
	}

	public void ExcluirCidade(ModeloCidade mod) {
		connCidade.conexao();
		try {
			PreparedStatement pst = connCidade.conn.prepareStatement("delete from cidade  where  id_cidade=?");
			pst.setInt(1, mod.getCod());
			pst.execute();
			JOptionPane.showMessageDialog(null, "Dados Excluidos com sucesso!");
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro ao Excluir dados!\n Erro: " + e);
		}
	}

	public void AlterarCidade(ModeloCidade mod) {
		connCidade.conexao();
		try {
			connCidade.executaSQL("select * from estados where nome_estado='"+mod.getEstado()+"'");
			connCidade.rs.first();
			codEst = connCidade.rs.getInt("id_estado");
			PreparedStatement pst = connCidade.conn.prepareStatement("update cidade set nome_cidade = ?, id_estado = ? where id_cidade = ?");
			pst.setString(1, mod.getNome());
			pst.setInt(2, codEst);
			pst.setInt(3, mod.getCod());
			pst.execute();
			JOptionPane.showMessageDialog(null, "Dados Alterados com sucesso!");
		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "Erro na Alteração dos dados!\n Erro : " + ex);
		}
		connCidade.desconecta();
	}

}

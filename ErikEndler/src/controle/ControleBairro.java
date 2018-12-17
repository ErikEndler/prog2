package controle;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import modelo.ModeloBairro;

public class ControleBairro {
	ArrayList cidades;
	String[] a;
	ConectaBanco conex = new ConectaBanco();
	ConectaBanco conexPesq = new ConectaBanco();
	int codCid;
	String cidade;

	public void Grava(ModeloBairro mod) {
		conex.conexao();
		try {
			conex.executaSQL("select * from cidade where nome_cidade='" + mod.getCidade() + "'");
			conex.rs.first();
			codCid = conex.rs.getInt("id_cidade");
			PreparedStatement pst = conex.conn.prepareStatement("insert into bairro(nome_bairro,id_cidade)values(?,?)");
			pst.setString(1, mod.getNome());
			pst.setInt(2, codCid);
			pst.execute();
			JOptionPane.showMessageDialog(null, "Dados inseridos com sucesso!");
		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "Erro ao inserir dados!" + ex);
		}
		conex.desconecta();
	}

	public void Editar(ModeloBairro mod) {
		conex.conexao();
		conexPesq.conexao();
		try {
			conexPesq.executaSQL("select * from cidade where nome_cidade='" + mod.getCidade() + "'");
			conexPesq.rs.first();
			codCid = conexPesq.rs.getInt("id_cidade");
			PreparedStatement pst = conex.conn
					.prepareStatement("update bairro set nome_bairro=?, id_cidade=? where id_bairro=?");
			pst.setString(1, mod.getNome());
			pst.setInt(2, codCid);
			pst.setInt(3, mod.getCod());
			pst.execute();
			JOptionPane.showMessageDialog(null, "Dados ALterados com sucesso!");
		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "Erro ao aLterar dados! " + ex);
		}
		conex.desconecta();
		conexPesq.desconecta();
	}

	public void Excluir(ModeloBairro mod) {
		conex.conexao();
		try {
			PreparedStatement pst = conex.conn.prepareStatement("delete from bairro where id_bairro=?");
			pst.setInt(1, mod.getCod());
			pst.execute();
			JOptionPane.showMessageDialog(null, "Dados Excluidos com sucesso!");
		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "Erro ao excluir dados : " + ex);
		}
		conex.desconecta();
	}
	

	public void ListarCidades(ModeloBairro mod){
    	cidades = new ArrayList();
    	 try {
    		 conex.conexao();
    		 conex.executaSQL("select * from cidade order by nome_cidade ");
    		 conex.rs.first();
             do {
            	 cidades.add(conex.rs.getString("nome_cidade"));
             } while (conex.rs.next());
         } catch (SQLException ex) {
             JOptionPane.showMessageDialog(null, "Erro preencher combobox : " + ex);
         }
    	 JOptionPane.showMessageDialog(null, cidades);
    	 mod.setListaCidades(cidades);
    	 conex.desconecta();
    }

}
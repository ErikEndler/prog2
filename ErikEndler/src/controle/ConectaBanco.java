/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import java.sql.*;
import javax.swing.JOptionPane;

/**
 *
 * @author Erik
 */
public class ConectaBanco {
	
    public Statement stm; // responsavel por preparar e realizar pesquisas no banco de dados
    public ResultSet rs; // responsavel por armazenar o resultado de uma pesquisa passada ao Statement
    private String driver = "org.postgresql.Driver"; // responsavel por identificar serviço de banco de dados
    private String caminho = "jdbc:postgresql://localhost:5433/loja"; // responsavel por setar o local do banco de dados
    private String usuario = "postgres";
    private String senha = null;
    public Connection conn; // responsavel por realizar a conecçao com o banco de dados

    public void conexao() {// metodo responsavel por realizar a conecçao com o banco
        try {
            System.setProperty("jdbc.Drivers", driver); // seta a propriedade do driver de conexao
            conn = DriverManager.getConnection(caminho, usuario, senha); // realiza a conexao com o banco de dados
           // JOptionPane.showMessageDialog(null, "conectado com sucesso!"); // imprimi uma caixa de mensagens
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro de conexão!/n Erro:" + ex.getMessage());
        }
        
    }

    public void executaSQL(String sql) {
        try {
            stm = conn.createStatement(rs.TYPE_SCROLL_INSENSITIVE, rs.CONCUR_READ_ONLY);
            rs = stm.executeQuery(sql);
        } catch (SQLException ex) {
           // JOptionPane.showMessageDialog(null, "Erro de ExecutaSQL!/n Erro:" + ex.getMessage());
        }
    }
    
    public void desconecta() { // metodo para fechar a conexao com o banco de dados
        try {
            conn.close(); // fecha a conexao
            //JOptionPane.showMessageDialog(null, "Desconectado do Banco de dados com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao fechar conexão!%n Erro:" + ex.getMessage());
        }
        
    }
    
}

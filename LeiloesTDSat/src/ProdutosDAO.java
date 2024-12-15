/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Adm
 */

import java.sql.PreparedStatement;
import java.sql.Connection;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.SQLException;

public class ProdutosDAO {
    
    Connection conn;
    PreparedStatement prep;
    ResultSet resultset;
    ArrayList<ProdutosDTO> listagem = new ArrayList<>();
    
    public void cadastrarProduto (ProdutosDTO produto){
        Connection conn = null;
        PreparedStatement pstm = null;

        try {
            // Estabelecendo conexão com o banco de dados
            conn = new conectaDAO().connectDB();

            // Comando SQL para inserir os dados no banco
            String sql = "INSERT INTO produtos (nome, valor, status) VALUES (?, ?, ?)";
            pstm = conn.prepareStatement(sql);
            pstm.setString(1, produto.getNome());
            pstm.setInt(2, produto.getValor());
            pstm.setString(3, produto.getStatus());

            // Executar o comando
            int rowsAffected = pstm.executeUpdate();

            // Mensagem de sucesso
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(null, "Produto cadastrado com sucesso!");
            } else {
                JOptionPane.showMessageDialog(null, "Falha ao cadastrar o produto.");
            }

        } catch (SQLException e) {
            // Mensagem de erro em caso de falha no banco de dados
            JOptionPane.showMessageDialog(null, "Erro no cadastro: " + e.getMessage());
        } finally {
            // Fechar recursos para evitar vazamento de memória
            try {
                if (pstm != null) pstm.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Erro ao fechar conexão: " + e.getMessage());
            }
        }
    
    }
    
    public ArrayList<ProdutosDTO> listarProdutos(){
        
        return listagem;
    }
    
    
    
        
}


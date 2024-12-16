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
ArrayList<ProdutosDTO> listagem = new ArrayList<>();
    Connection conn = null;
    PreparedStatement pstm = null;
    ResultSet rs = null;

    try {
    
        conn = new conectaDAO().connectDB();

        
        String sql = "SELECT id, nome, valor, status FROM produtos";
        pstm = conn.prepareStatement(sql);
        rs = pstm.executeQuery();

      
        while (rs.next()) {
            ProdutosDTO produto = new ProdutosDTO();
            produto.setId(rs.getInt("id"));
            produto.setNome(rs.getString("nome"));
            produto.setValor(rs.getInt("valor"));
            produto.setStatus(rs.getString("status"));

            listagem.add(produto);
        }
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Erro ao listar produtos: " + e.getMessage());
    } finally {
        
        try {
            if (rs != null) rs.close();
            if (pstm != null) pstm.close();
            if (conn != null) conn.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao fechar conexão: " + e.getMessage());
        }
    }

    return listagem;
    }
    
    public void venderProduto(int idProduto) {
    Connection conn = null;
    PreparedStatement pstm = null;

    try {
        // Conexão com o banco
        conn = new conectaDAO().connectDB();

        // Atualizando o status do produto para "Vendido"
        String sql = "UPDATE produtos SET status = ? WHERE id = ?";
        pstm = conn.prepareStatement(sql);
        pstm.setString(1, "Vendido");
        pstm.setInt(2, idProduto);

        int rowsAffected = pstm.executeUpdate();

        // Verifica se o produto foi atualizado
        if (rowsAffected > 0) {
            JOptionPane.showMessageDialog(null, "Produto vendido com sucesso!");
        } else {
            JOptionPane.showMessageDialog(null, "Produto não encontrado ou já vendido.");
        }

    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Erro ao vender produto: " + e.getMessage());
    } finally {
        // Fechando recursos
        try {
            if (pstm != null) pstm.close();
            if (conn != null) conn.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao fechar conexão: " + e.getMessage());
        }
    }
}
    public ArrayList<ProdutosDTO> listarProdutosVendidos() {
    ArrayList<ProdutosDTO> produtosVendidos = new ArrayList<>();
    Connection conn = null;
    PreparedStatement pstm = null;
    ResultSet rs = null;

    try {
        // Estabelecendo a conexão com o banco de dados
        conn = new conectaDAO().connectDB();

        // SQL para buscar os produtos com status 'Vendido'
        String sql = "SELECT * FROM produtos WHERE status = ?";
        pstm = conn.prepareStatement(sql);
        pstm.setString(1, "Vendido");

        // Executar a consulta
        rs = pstm.executeQuery();

        // Preencher a lista com os produtos vendidos
        while (rs.next()) {
            ProdutosDTO produto = new ProdutosDTO();
            produto.setId(rs.getInt("id"));
            produto.setNome(rs.getString("nome"));
            produto.setValor(rs.getInt("valor"));
            produto.setStatus(rs.getString("status"));
            produtosVendidos.add(produto);
        }
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Erro ao listar produtos vendidos: " + e.getMessage());
    } finally {
        // Fechar os recursos
        try {
            if (rs != null) rs.close();
            if (pstm != null) pstm.close();
            if (conn != null) conn.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao fechar conexão: " + e.getMessage());
        }
    }
    return produtosVendidos;
}
        
}


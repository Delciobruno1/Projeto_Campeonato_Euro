package op;

import entidades.Selecao;
import conexao.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SelecaoOP {
    private Connection connection;

    public SelecaoOP(DBConnection DBConnection) {
        this.connection = DBConnection.getConnection();
    }

    public void inserirSelecao(Selecao selecao) {
        String query = "INSERT INTO Selecao (nome, pais_id) VALUES (?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, selecao.getNome());
            stmt.setInt(2, selecao.getPaisId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inserir seleção: " + e.getMessage(), e);
        }
    }

    public Selecao buscarSelecaoPorId(int selecaoId) {
        Selecao selecao = null;
        String query = "SELECT * FROM selecao WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, selecaoId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    selecao = new Selecao();
                    selecao.setId(rs.getInt("id"));
                    selecao.setNome(rs.getString("nome"));
                    selecao.setPaisId(rs.getInt("pais_id"));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar seleção por ID: " + e.getMessage(), e);
        }
        return selecao;
    }

    public Selecao buscarSelecaoPorNome(String nome) {
        Selecao selecao = null;
        String query = "SELECT * FROM Selecao WHERE nome = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, nome);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    selecao = new Selecao();
                    selecao.setId(rs.getInt("id"));
                    selecao.setNome(rs.getString("nome"));
                    selecao.setPaisId(rs.getInt("pais_id"));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar seleção por nome: " + e.getMessage(), e);
        }
        return selecao;
    }

    public List<Selecao> listarSelecoes() {
        List<Selecao> selecoes = new ArrayList<>();
        String query = "SELECT * FROM Selecao";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Selecao selecao = new Selecao();
                    selecao.setId(rs.getInt("id"));
                    selecao.setNome(rs.getString("nome"));
                    selecao.setPaisId(rs.getInt("pais_id"));
                    selecoes.add(selecao);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar seleções: " + e.getMessage(), e);
        }
        return selecoes;
    }

    public List<Integer> listarSelecoesID() {
        List<Integer> selecoesId = new ArrayList<>();
        String query = "SELECT id FROM Selecao";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    selecoesId.add(rs.getInt("id"));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar IDs das seleções: " + e.getMessage(), e);
        }
        return selecoesId;
    }

    public void atualizarSelecao(Selecao selecao) {
        String query = "UPDATE Selecao SET nome = ?, pais_id = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, selecao.getNome());
            stmt.setInt(2, selecao.getPaisId());
            stmt.setInt(3, selecao.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar seleção: " + e.getMessage(), e);
        }
    }

    public void deletarSelecao(int id) {
        String query = "DELETE FROM Selecao WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao deletar seleção: " + e.getMessage(), e);
        }
    }
}

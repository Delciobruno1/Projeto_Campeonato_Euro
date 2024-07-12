package op;

import entidades.Cidade;
import conexao.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CidadeOP {
    private Connection connection;

    public CidadeOP(DBConnection DBConnection) {
        this.connection = DBConnection.getConnection();
    }

    public void inserirCidade(Cidade cidade) throws SQLException {
        String query = "INSERT INTO Cidade (nome, pais_id) VALUES (?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, cidade.getNome());
            stmt.setInt(2, cidade.getPaisId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Erro ao inserir cidade: " + e.getMessage(), e);
        }
    }

    public List<Cidade> listarCidades() throws SQLException {
        List<Cidade> cidades = new ArrayList<>();
        String query = "SELECT * FROM Cidade";
        try (PreparedStatement stmt = connection.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Cidade cidade = new Cidade();
                cidade.setId(rs.getInt("id"));
                cidade.setNome(rs.getString("nome"));
                cidade.setPaisId(rs.getInt("pais_id"));
                cidades.add(cidade);
            }
        } catch (SQLException e) {
            throw new SQLException("Erro ao listar cidades: " + e.getMessage(), e);
        }
        return cidades;
    }

    public void atualizarCidade(Cidade cidade) throws SQLException {
        String query = "UPDATE Cidade SET nome = ?, pais_id = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, cidade.getNome());
            stmt.setInt(2, cidade.getPaisId());
            stmt.setInt(3, cidade.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Erro ao atualizar cidade: " + e.getMessage(), e);
        }
    }

    public Cidade buscarCidadePorId(int cidade_id) throws SQLException {
        Cidade cidade = null;
        String query = "SELECT * FROM Cidade WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, cidade_id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    cidade = new Cidade();
                    cidade.setId(rs.getInt("id"));
                    cidade.setNome(rs.getString("nome"));
                    cidade.setPaisId(rs.getInt("pais_id"));
                }
            }
        } catch (SQLException e) {
            throw new SQLException("Erro ao buscar cidade por ID: " + e.getMessage(), e);
        }
        return cidade;
    }

    public Cidade buscarCidadePorNome(String nome) throws SQLException {
        Cidade cidade = null;
        String query = "SELECT * FROM Cidade WHERE nome = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, nome);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    cidade = new Cidade();
                    cidade.setId(rs.getInt("id"));
                    cidade.setNome(rs.getString("nome"));
                    cidade.setPaisId(rs.getInt("pais_id"));
                }
            }
        } catch (SQLException e) {
            throw new SQLException("Erro ao buscar cidade por nome: " + e.getMessage(), e);
        }
        return cidade;
    }

    public void deletarCidade(int id) throws SQLException {
        String query = "DELETE FROM Cidade WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Erro ao deletar cidade: " + e.getMessage(), e);
        }
    }
}

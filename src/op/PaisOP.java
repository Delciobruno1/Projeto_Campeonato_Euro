package op;

import conexao.DBConnection;
import entidades.Pais;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PaisOP {
    private Connection connection;

    public PaisOP(DBConnection dbConnection) throws SQLException {
        this.connection = dbConnection.getConnection();
    }

    public void inserirPais(Pais pais) throws SQLException {
        String query = "INSERT INTO Pais (nome) VALUES (?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, pais.getNome());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Erro ao inserir país: " + e.getMessage());
        }
    }

    public List<Pais> listarPaises() throws SQLException {
        List<Pais> paises = new ArrayList<>();
        String query = "SELECT * FROM Pais";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Pais pais = new Pais();
                pais.setId(rs.getInt("id"));
                pais.setNome(rs.getString("nome"));
                paises.add(pais);
            }
        } catch (SQLException e) {
            throw new SQLException("Erro ao listar países: " + e.getMessage());
        }
        return paises;
    }

    public void atualizarPais(Pais pais) throws SQLException {
        String query = "UPDATE Pais SET nome = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, pais.getNome());
            stmt.setInt(2, pais.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Erro ao atualizar país: " + e.getMessage());
        }
    }

    public Pais buscarPaisPorId(int pais_id) throws SQLException {
        Pais pais = null;
        String query = "SELECT * FROM Pais WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, pais_id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                pais = new Pais();
                pais.setId(rs.getInt("id"));
                pais.setNome(rs.getString("nome"));
            }
        } catch (SQLException e) {
            throw new SQLException("Erro ao buscar país por ID: " + e.getMessage());
        }
        return pais;
    }

    public Pais buscarPaisPorNome(String nome) throws SQLException {
        Pais pais = null;
        String query = "SELECT * FROM Pais WHERE nome = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, nome);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                pais = new Pais();
                pais.setId(rs.getInt("id"));
                pais.setNome(rs.getString("nome"));
            }
        } catch (SQLException e) {
            throw new SQLException("Erro ao buscar país por nome: " + e.getMessage());
        }
        return pais;
    }

    public void deletarPais(int id) throws SQLException {
        String query = "DELETE FROM Pais WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Erro ao deletar país: " + e.getMessage());
        }
    }
}

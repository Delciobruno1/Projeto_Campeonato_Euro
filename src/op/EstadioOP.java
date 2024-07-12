package op;

import entidades.Estadio;
import conexao.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EstadioOP {
    private Connection connection;

    public EstadioOP(DBConnection DBConnection) {
        this.connection = DBConnection.getConnection();
    }

    public void inserirEstadio(Estadio estadio) throws SQLException {
        String query = "INSERT INTO Estadio (nome, capacidade, cidade_id) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, estadio.getNome());
            stmt.setInt(2, estadio.getCapacidade());
            stmt.setInt(3, estadio.getCidade_id());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Erro ao inserir estádio: " + e.getMessage(), e);
        }
    }

    public List<Estadio> listarEstadios() throws SQLException {
        List<Estadio> estadios = new ArrayList<>();
        String query = "SELECT * FROM Estadio";
        try (PreparedStatement stmt = connection.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Estadio estadio = new Estadio();
                estadio.setId(rs.getInt("id"));
                estadio.setNome(rs.getString("nome"));
                estadio.setCidade_id(rs.getInt("cidade_id"));
                estadios.add(estadio);
            }
        } catch (SQLException e) {
            throw new SQLException("Erro ao listar estádios: " + e.getMessage(), e);
        }
        return estadios;
    }

    public Estadio buscarEstadioPorId(int estadio_id) throws SQLException {
        Estadio estadio = null;
        String query = "SELECT * FROM Estadio WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, estadio_id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    estadio = new Estadio();
                    estadio.setId(rs.getInt("id"));
                    estadio.setNome(rs.getString("nome"));
                    estadio.setCidade_id(rs.getInt("cidade_id"));
                }
            }
        } catch (SQLException e) {
            throw new SQLException("Erro ao buscar estádio por ID: " + e.getMessage(), e);
        }
        return estadio;
    }

    public Estadio buscarEstadioPorNome(String nome) throws SQLException {
        Estadio estadio = null;
        String query = "SELECT * FROM Estadio WHERE nome = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, nome);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    estadio = new Estadio();
                    estadio.setId(rs.getInt("id"));
                    estadio.setNome(rs.getString("nome"));
                    estadio.setCidade_id(rs.getInt("cidade_id"));
                }
            }
        } catch (SQLException e) {
            throw new SQLException("Erro ao buscar estádio por nome: " + e.getMessage(), e);
        }
        return estadio;
    }

    public void atualizarEstadio(Estadio estadio) throws SQLException {
        String query = "UPDATE Estadio SET nome = ?, capacidade = ?, cidade_id = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, estadio.getNome());
            stmt.setInt(2, estadio.getCapacidade());
            stmt.setInt(3, estadio.getCidade_id());
            stmt.setInt(4, estadio.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Erro ao atualizar estádio: " + e.getMessage(), e);
        }
    }

    public void deletarEstadio(int id) throws SQLException {
        String query = "DELETE FROM Estadio WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Erro ao deletar estádio: " + e.getMessage(), e);
        }
    }
}

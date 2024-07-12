package op;

import entidades.Grupo;
import conexao.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GrupoOP {
    private Connection connection;

    public GrupoOP(DBConnection DBConnection) {
        this.connection = DBConnection.getConnection();
    }

    public void inserirGrupo(Grupo grupo) {
        String query = "INSERT INTO Grupo (nome) VALUES (?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, grupo.getNome());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inserir grupo: " + e.getMessage(), e);
        }
    }

    public List<Grupo> listarGrupos() {
        List<Grupo> grupos = new ArrayList<>();
        String query = "SELECT * FROM Grupo";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Grupo grupo = new Grupo();
                    grupo.setId(rs.getInt("id"));
                    grupo.setNome(rs.getString("nome"));
                    grupos.add(grupo);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar grupos: " + e.getMessage(), e);
        }
        return grupos;
    }

    public void atualizarGrupo(Grupo grupo) {
        String query = "UPDATE Grupo SET nome = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, grupo.getNome());
            stmt.setInt(2, grupo.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar grupo: " + e.getMessage(), e);
        }
    }

    public Grupo buscarGrupoPorId(int grupo_id) {
        Grupo grupo = null;
        String query = "SELECT * FROM Grupo WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, grupo_id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    grupo = new Grupo();
                    grupo.setId(rs.getInt("id"));
                    grupo.setNome(rs.getString("nome"));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar grupo por ID: " + e.getMessage(), e);
        }
        return grupo;
    }

    public Grupo buscarGrupoPorNome(String nome) {
        Grupo grupo = null;
        String query = "SELECT * FROM Grupo WHERE nome = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, nome);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    grupo = new Grupo();
                    grupo.setId(rs.getInt("id"));
                    grupo.setNome(rs.getString("nome"));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar grupo por nome: " + e.getMessage(), e);
        }
        return grupo;
    }

    public int contarNumeroGrupos() {
        int numeroGrupos = 0;
        String query = "SELECT COUNT(*) AS total FROM Grupo";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    numeroGrupos = rs.getInt("total");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao contar o número de grupos: " + e.getMessage(), e);
        }
        return numeroGrupos;
    }

    public void deletarGrupo(int id) {
        String query = "DELETE FROM Grupo WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao deletar grupo: " + e.getMessage(), e);
        }
    }
}

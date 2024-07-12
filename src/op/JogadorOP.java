package op;

import conexao.DBConnection;
import entidades.Jogador;
import entidades.Selecao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JogadorOP {
    private Connection connection;

    public JogadorOP(DBConnection dbConnection) throws SQLException {
        this.connection = dbConnection.getConnection();
    }

    public void inserirJogador(Jogador jogador) throws SQLException {
        String query = "INSERT INTO jogador (nome, data_nascimento, posicao, selecao_id) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, jogador.getNome());
            stmt.setString(2, jogador.getDataNascimento());
            stmt.setString(3, jogador.getPosicao());
            stmt.setInt(4, jogador.getSelecao_id());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Erro ao inserir jogador: " + e.getMessage());
        }
    }

    public Jogador buscarJogadorPorId(int id) throws SQLException {
        Jogador jogador = null;
        String query = "SELECT j.*, s.id as selecao_id, s.nome as selecao_nome " +
                "FROM jogador j " +
                "INNER JOIN selecao s ON j.selecao_id = s.id " +
                "WHERE j.id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    jogador = new Jogador();
                    jogador.setId(rs.getInt("id"));
                    jogador.setNome(rs.getString("nome"));
                    jogador.setPosicao(rs.getString("posicao"));
                    jogador.setDataNascimento(rs.getString("data_nascimento"));

                    Selecao selecao = new Selecao();
                    selecao.setId(rs.getInt("selecao_id"));
                    selecao.setNome(rs.getString("selecao_nome"));

                    jogador.setSelecao(selecao);
                }
            }
        } catch (SQLException e) {
            throw new SQLException("Erro ao buscar jogador por ID: " + e.getMessage());
        }
        return jogador;
    }

    public List<Jogador> buscarJogadoresPorSelecao(int selecaoId) throws SQLException {
        List<Jogador> jogadores = new ArrayList<>();
        String query = "SELECT j.*, s.id as selecao_id, s.nome as selecao_nome " +
                "FROM jogador j " +
                "INNER JOIN selecao s ON j.selecao_id = s.id " +
                "WHERE j.selecao_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, selecaoId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Jogador jogador = new Jogador();
                    jogador.setId(rs.getInt("id"));
                    jogador.setNome(rs.getString("nome"));
                    jogador.setDataNascimento(rs.getString("data_nascimento"));
                    jogador.setPosicao(rs.getString("posicao"));

                    Selecao selecao = new Selecao();
                    selecao.setId(rs.getInt("selecao_id"));
                    selecao.setNome(rs.getString("selecao_nome"));
                    jogador.setSelecao(selecao);

                    jogadores.add(jogador);
                }
            }
        } catch (SQLException e) {
            throw new SQLException("Erro ao buscar jogadores por seleção: " + e.getMessage());
        }
        return jogadores;
    }
}

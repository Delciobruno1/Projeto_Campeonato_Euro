package op;

import conexao.DBConnection;
import entidades.EstatisticasIndividuais;
import entidades.Jogador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EstatisticasIndividuaisOP {

    private final Connection connection;
    private final JogadorOP jogadorOP;

    public EstatisticasIndividuaisOP(DBConnection dbConnection) {
        try {
            this.connection = dbConnection.getConnection();
            this.jogadorOP = new JogadorOP(dbConnection);
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao estabelecer conexão com o banco de dados: " + e.getMessage(), e);
        }
    }

    public void inserirEstatisticasIndividuais(EstatisticasIndividuais estatisticas) throws SQLException {
        String sql = "INSERT INTO estatisticas_individuais (jogador_id, golos, remates, fora_jogos, faltas, assistencias, passes, partida_id) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, estatisticas.getJogador_id());
            stmt.setInt(2, estatisticas.getGolos());
            stmt.setInt(3, estatisticas.getRemates());
            stmt.setInt(4, estatisticas.getForaJogos());
            stmt.setInt(5, estatisticas.getFaltas());
            stmt.setInt(6, estatisticas.getAssistencias());
            stmt.setInt(7, estatisticas.getPasses());
            stmt.setInt(8, estatisticas.getPartida_id());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Erro ao inserir estatísticas individuais: " + e.getMessage(), e);
        }
    }

    public EstatisticasIndividuais obterEstatisticasPorId(int jogadorId) throws SQLException {
        EstatisticasIndividuais estatisticas = null;
        String sql = "SELECT * FROM estatisticas_individuais WHERE jogador_id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, jogadorId);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    estatisticas = new EstatisticasIndividuais();
                    estatisticas.setJogador_id(rs.getInt("jogador_id"));
                    estatisticas.setGolos(rs.getInt("golos"));
                    estatisticas.setRemates(rs.getInt("remates"));
                    estatisticas.setForaJogos(rs.getInt("fora_jogos"));
                    estatisticas.setFaltas(rs.getInt("faltas"));
                    estatisticas.setAssistencias(rs.getInt("assistencias"));
                    estatisticas.setPasses(rs.getInt("passes"));
                    estatisticas.setPartida_id(rs.getInt("partida_id"));
                }
            }
        } catch (SQLException e) {
            throw new SQLException("Erro ao obter estatísticas por ID do jogador: " + e.getMessage(), e);
        }

        return estatisticas;
    }

    public EstatisticasIndividuais obterEstatisticasPorNomeJogador(String nomeJogador) throws SQLException {
        EstatisticasIndividuais estatisticas = null;
        String sql = "SELECT ei.* FROM estatisticas_individuais ei " +
                "INNER JOIN jogador j ON ei.jogador_id = j.id " +
                "WHERE j.nome = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, nomeJogador);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    estatisticas = new EstatisticasIndividuais();
                    estatisticas.setJogador_id(rs.getInt("jogador_id"));
                    estatisticas.setGolos(rs.getInt("golos"));
                    estatisticas.setRemates(rs.getInt("remates"));
                    estatisticas.setForaJogos(rs.getInt("fora_jogos"));
                    estatisticas.setFaltas(rs.getInt("faltas"));
                    estatisticas.setAssistencias(rs.getInt("assistencias"));
                    estatisticas.setPasses(rs.getInt("passes"));
                    estatisticas.setPartida_id(rs.getInt("partida_id"));
                }
            }
        } catch (SQLException e) {
            throw new SQLException("Erro ao obter estatísticas por nome do jogador: " + e.getMessage(), e);
        }

        return estatisticas;
    }

    public List<EstatisticasIndividuais> obterMelhoresMarcadores() throws SQLException {
        List<EstatisticasIndividuais> melhoresMarcadores = new ArrayList<>();
        String sql = "SELECT jogador_id, golos FROM estatisticas_individuais ORDER BY golos DESC LIMIT 10";

        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                EstatisticasIndividuais estatisticas = new EstatisticasIndividuais();
                estatisticas.setJogador_id(rs.getInt("jogador_id"));
                estatisticas.setGolos(rs.getInt("golos"));
                Jogador jogador = jogadorOP.buscarJogadorPorId(estatisticas.getJogador_id());
                estatisticas.setJogador(jogador);
                melhoresMarcadores.add(estatisticas);
            }
        } catch (SQLException e) {
            throw new SQLException("Erro ao obter melhores marcadores: " + e.getMessage(), e);
        }

        return melhoresMarcadores;
    }

    public List<EstatisticasIndividuais> obterMelhoresAssistentes() throws SQLException {
        List<EstatisticasIndividuais> melhoresAssistentes = new ArrayList<>();
        String sql = "SELECT jogador_id, assistencias FROM estatisticas_individuais ORDER BY assistencias DESC LIMIT 10";

        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                EstatisticasIndividuais estatisticas = new EstatisticasIndividuais();
                estatisticas.setJogador_id(rs.getInt("jogador_id"));
                estatisticas.setAssistencias(rs.getInt("assistencias"));
                Jogador jogador = jogadorOP.buscarJogadorPorId(estatisticas.getJogador_id());
                estatisticas.setJogador(jogador);
                melhoresAssistentes.add(estatisticas);
            }
        } catch (SQLException e) {
            throw new SQLException("Erro ao obter melhores assistentes: " + e.getMessage(), e);
        }

        return melhoresAssistentes;
    }
}

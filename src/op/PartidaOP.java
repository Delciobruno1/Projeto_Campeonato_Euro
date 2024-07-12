package op;

import conexao.DBConnection;
import entidades.Estadio;
import entidades.Partida;
import entidades.Selecao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PartidaOP {
    private Connection connection;

    public PartidaOP(DBConnection dbConnection) throws SQLException {
        this.connection = dbConnection.getConnection();
    }

    public void adicionarPartida(Partida partida) throws SQLException {
        String query = "INSERT INTO partida (data, estadio_id, selecao_casa_id, selecao_fora_id, gols_casa, gols_fora) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setDate(1, new java.sql.Date(partida.getData().getTime()));
            stmt.setInt(2, partida.getEstadio().getId());
            stmt.setInt(3, partida.getSelecaoCasa_id());
            stmt.setInt(4, partida.getSelecaoFora_id());
            stmt.setInt(5, partida.getGolsCasa());
            stmt.setInt(6, partida.getGolsFora());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Erro ao adicionar partida: " + e.getMessage());
        }
    }

    public List<Partida> listarPartidas() throws SQLException {
        List<Partida> partidas = new ArrayList<>();
        String query = "SELECT * FROM partida";
        try (PreparedStatement stmt = connection.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Partida partida = new Partida();
                partida.setId(rs.getInt("id"));
                partida.setData(new Date(rs.getDate("data").getTime()));

                Estadio estadio = new EstadioOP(new DBConnection()).buscarEstadioPorId(rs.getInt("estadio_id"));
                partida.setEstadio(estadio);

                SelecaoOP selecaoOP = new SelecaoOP(new DBConnection());
                Selecao selecaoCasa = selecaoOP.buscarSelecaoPorId(rs.getInt("selecao_casa_id"));
                Selecao selecaoFora = selecaoOP.buscarSelecaoPorId(rs.getInt("selecao_fora_id"));
                partida.setSelecaoCasa(selecaoCasa);
                partida.setSelecaoFora(selecaoFora);
                partida.setGolsCasa(rs.getInt("gols_casa"));
                partida.setGolsFora(rs.getInt("gols_fora"));
                partidas.add(partida);
            }
        } catch (SQLException e) {
            throw new SQLException("Erro ao listar partidas: " + e.getMessage());
        }
        return partidas;
    }
}
package op;

import conexao.DBConnection;
import entidades.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SimularPartidaOP {
    private Connection connection;
    private Random random;
    private EstatisticasIndividuaisOP estatisticasIndividuaisOP;
    private PartidaOP partidaOP;

    public SimularPartidaOP(DBConnection dbConnection) {
        try {
            this.connection = dbConnection.getConnection();
            this.random = new Random();
            this.estatisticasIndividuaisOP = new EstatisticasIndividuaisOP(dbConnection);
            this.partidaOP = new PartidaOP(dbConnection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void simularPartidasDoGrupo(int grupoId , DBConnection connection) throws SQLException {
        GrupoSelecaoOP selecaoGrupoOP = new GrupoSelecaoOP(connection);
        List<GrupoSelecao> selecoes = selecaoGrupoOP.obterSelecoesPorGrupo(grupoId);

        for (int i = 0; i < selecoes.size(); i++) {
            for (int j = i + 1; j < selecoes.size(); j++) {
                SelecaoOP selecaoOP = new SelecaoOP(connection);
                Selecao selecaoCasa = selecaoOP.buscarSelecaoPorId(selecoes.get(i).getSelecaoId());
                Selecao selecaoFora = selecaoOP.buscarSelecaoPorId(selecoes.get(j).getSelecaoId());
                System.out.println("Equipe: " + selecaoCasa.getNome());
                System.out.println("Equipe: " + selecaoFora.getNome());
                simularPartidaEntreSelecoes(selecaoCasa, selecaoFora, grupoId , connection);
            }
        }
    }

    private void simularPartidaEntreSelecoes(Selecao selecaoCasa, Selecao selecaoFora, int grupoId , DBConnection connection) throws SQLException {
        Partida partida = new Partida();
        partida.setData(new java.util.Date());
        // Assume um estádio aleatório para cada partida
        partida.setEstadio(new EstadioOP(connection).buscarEstadioPorId(random.nextInt(10) + 1)); // Exemplo de estádio com ID entre 1 e 10
        partida.setSelecaoCasa_id(selecaoCasa.getId());
        partida.setSelecaoFora_id(selecaoFora.getId());
        partida.setGolsCasa(random.nextInt(5)); // Gols da casa entre 0 e 4
        partida.setGolsFora(random.nextInt(5)); // Gols fora entre 0 e 4
        partida.setSelecaoCasa(selecaoCasa);
        partida.setSelecaoFora(selecaoFora);

        JogadorOP jogadorOP = new JogadorOP(connection);
        GrupoSelecaoOP grupoSelecaoOP = new GrupoSelecaoOP(connection);
        List<Jogador> jogadoresSelecao1 = jogadorOP.buscarJogadoresPorSelecao(selecaoCasa.getId());
        List<Jogador> jogadoresSelecao2 = jogadorOP.buscarJogadoresPorSelecao(selecaoFora.getId());

        simularEstatisticasIndividuais(partida, jogadoresSelecao1);
        simularEstatisticasIndividuais(partida, jogadoresSelecao2);
        partidaOP.adicionarPartida(partida);
        grupoSelecaoOP.atualizarEstatisticasGrupo(grupoId, partida.getSelecaoCasa_id(), partida.getGolsCasa(), partida.getGolsFora());
        grupoSelecaoOP.atualizarEstatisticasGrupo(grupoId, partida.getSelecaoFora_id(), partida.getGolsFora(), partida.getGolsCasa());
        System.out.print("Jogo a decorrer entre a Selecao " +
                partida.getSelecaoCasa().getNome() + " x " + partida.getSelecaoFora().getNome());
    }

    private void simularEstatisticasIndividuais(Partida partida, List<Jogador> jogadores) throws SQLException {
        for (Jogador jogador : jogadores) {
            EstatisticasIndividuais estatisticas = new EstatisticasIndividuais();
            estatisticas.setJogador_id(jogador.getId());
            estatisticas.setPartida_id(partida.getId());
            estatisticas.setGolos(random.nextInt(2)); // Simula até 2 gols por jogador
            estatisticas.setRemates(random.nextInt(5)); // Simula até 5 remates por jogador
            estatisticas.setForaJogos(random.nextInt(2)); // Simula até 2 fora de jogo por jogador
            estatisticas.setFaltas(random.nextInt(3)); // Simula até 3 faltas cometidas por jogador
            estatisticas.setAssistencias(random.nextInt(2)); // Simula até 2 assistências por jogador
            estatisticas.setPasses(random.nextInt(50)); // Simula até 50 passes por jogador
            estatisticasIndividuaisOP.inserirEstatisticasIndividuais(estatisticas);
        }
    }
}

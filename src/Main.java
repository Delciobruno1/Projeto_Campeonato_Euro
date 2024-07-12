import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import conexao.DBConnection;
import entidades.*;
import op.*;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    static DBConnection connection = new DBConnection();
    static PaisOP paisOP;

    static {
        try {
            paisOP = new PaisOP(connection);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    static CidadeOP cidadeOP = new CidadeOP(connection);
    static EstadioOP estadioOP = new EstadioOP(connection);
    static SelecaoOP selecaoOP = new SelecaoOP(connection);
    static JogadorOP jogadorOP;

    static {
        try {
            jogadorOP = new JogadorOP(connection);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    static GrupoOP grupoOP = new GrupoOP(connection);
    static EstatisticasIndividuaisOP estatisticasIndividuaisOP = new EstatisticasIndividuaisOP(connection);
    static GrupoSelecaoOP grupoSelecaoOP = new GrupoSelecaoOP(connection);
    static PartidaOP partidaOP;

    static {
        try {
            partidaOP = new PartidaOP(connection);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    static SimularPartidaOP simularPartidaOP = new SimularPartidaOP(connection);

    public static void main(String[] args) {
        int opcao = 0;

        do {
            System.out.println("\n=== Menu ===");
            System.out.println("1. Criar País");
            System.out.println("2. Criar Cidade");
            System.out.println("3. Criar Estádio");
            System.out.println("4. Criar Seleção");
            System.out.println("5. Criar Grupo");
            System.out.println("6. Criar Jogador");
            System.out.println("7. Ver Classificação dos Grupos");
            System.out.println("8. Ver Partidas");
            System.out.println("9. Realizar Partida");
            System.out.println("10. Listar Entidades");
            System.out.println("11. Listar Melhores Marcadores");
            System.out.println("12. Listar Melhores Assistentes");
            System.out.println("13. Sair");
            System.out.print("\nEscolha uma opção: ");

            try {
                opcao = scanner.nextInt();
                scanner.nextLine(); // Limpar o buffer do scanner
                switch (opcao) {
                    case 1:
                        criarPais();
                        break;
                    case 2:
                        criarCidade();
                        break;
                    case 3:
                        criarEstadio();
                        break;
                    case 4:
                        criarSelecao();
                        break;
                    case 5:
                        criarGrupo();
                        break;
                    case 6:
                        criarJogador();
                        break;
                    case 7:
                        verClassificacaoGrupos();
                        break;
                    case 8:
                        verPartidas();
                        break;
                    case 9:
                        realizarPartida();
                        break;
                    case 10:
                        listarEntidades(scanner);
                        break;
                    case 11:
                        listarMelhoresMarcadores();
                        break;
                    case 12:
                        listarMelhoresAssistentes();
                        break;
                    case 13:
                        System.out.println("Saindo...");
                        break;
                    default:
                        System.out.println("Opção inválida. Tente novamente.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida. Por favor, insira um número correspondente à opção desejada.");
                scanner.nextLine(); // Limpar o buffer do scanner
            } catch (Exception e) {
                System.out.println("Erro inesperado: " + e.getMessage());
            }
        } while (opcao != 13);

        scanner.close();
    }

    private static void criarPais() {
        try {
            System.out.println("Digite o nome do país:");
            String nome = scanner.nextLine().trim();
            if (nome.isEmpty()) {
                System.out.println("Nome do país não pode estar vazio. Tente novamente.");
                return;
            }

            Pais pais = new Pais();
            pais.setNome(nome);
            paisOP.inserirPais(pais);
            System.out.println("País inserido com sucesso!");
        } catch (SQLException e) {
            System.out.println("Erro ao inserir país: " + e.getMessage());
        }
    }

    private static void criarCidade() {
        try {
            System.out.println("Digite o nome da cidade:");
            String nome = scanner.nextLine().trim();
            if (nome.isEmpty()) {
                System.out.println("Nome da cidade não pode estar vazio. Tente novamente.");
                return;
            }

            System.out.println("Digite o ID do país:");
            int pais_id = scanner.nextInt();
            scanner.nextLine(); // Limpar o buffer do scanner

            Cidade cidade = new Cidade();
            cidade.setNome(nome);
            cidade.setPaisId(pais_id);
            cidadeOP.inserirCidade(cidade);
            System.out.println("Cidade inserida com sucesso!");
        } catch (InputMismatchException e) {
            System.out.println("ID do país deve ser um número inteiro. Tente novamente.");
            scanner.nextLine(); // Limpar o buffer do scanner
        } catch (SQLException e) {
            System.out.println("Erro ao inserir cidade: " + e.getMessage());
        }
    }

    private static void criarEstadio() {
        try {
            System.out.println("Digite o nome do estádio:");
            String nome = scanner.nextLine().trim();
            if (nome.isEmpty()) {
                System.out.println("Nome do estádio não pode estar vazio. Tente novamente.");
                return;
            }

            System.out.println("Digite a capacidade do estádio:");
            int capacidade = scanner.nextInt();
            scanner.nextLine(); // Limpar o buffer do scanner

            System.out.println("Digite o ID da cidade:");
            int cidade_id = scanner.nextInt();
            scanner.nextLine(); // Limpar o buffer do scanner

            Estadio estadio = new Estadio();
            estadio.setNome(nome);
            estadio.setCidade_id(cidade_id);
            estadio.setCapacidade(capacidade);
            estadioOP.inserirEstadio(estadio);
            System.out.println("Estádio inserido com sucesso!");
        } catch (InputMismatchException e) {
            System.out.println("Capacidade do estádio ou ID da cidade deve ser um número inteiro. Tente novamente.");
            scanner.nextLine(); // Limpar o buffer do scanner
        } catch (SQLException e) {
            System.out.println("Erro ao inserir estádio: " + e.getMessage());
        }
    }

    private static void criarSelecao() {
        try {
            System.out.println("Digite o nome da seleção:");
            String nome = scanner.nextLine().trim();
            if (nome.isEmpty()) {
                System.out.println("Nome da seleção não pode estar vazio. Tente novamente.");
                return;
            }

            System.out.println("Digite o ID do país:");
            int pais_id = scanner.nextInt();
            scanner.nextLine(); // Limpar o buffer do scanner

            Selecao selecao = new Selecao(nome, pais_id);
            selecaoOP.inserirSelecao(selecao);
            System.out.println("Seleção inserida com sucesso!");
        } catch (InputMismatchException e) {
            System.out.println("ID do país deve ser um número inteiro. Tente novamente.");
            scanner.nextLine(); // Limpar o buffer do scanner
        } catch (Exception e) {
            System.out.println("Erro ao inserir seleção: " + e.getMessage());
        }
    }

    private static void criarGrupo() {
        try {
            System.out.println("Digite o nome do grupo:");
            String nome = scanner.nextLine().trim();
            if (nome.isEmpty()) {
                System.out.println("Nome do grupo não pode estar vazio. Tente novamente.");
                return;
            }

            Grupo grupo = new Grupo();
            grupo.setNome(nome);
            grupoOP.inserirGrupo(grupo);
            System.out.println("Grupo inserido com sucesso!");
        } catch (Exception e) {
            System.out.println("Erro ao inserir grupo: " + e.getMessage());
        }
    }

    private static void criarJogador() {
        try {
            System.out.println("Digite o nome do jogador:");
            String nome = scanner.nextLine().trim();
            if (nome.isEmpty()) {
                System.out.println("Nome do jogador não pode estar vazio. Tente novamente.");
                return;
            }

            System.out.println("Digite a data de nascimento do jogador (YYYY-MM-DD):");
            String dataNascimento = scanner.nextLine().trim();
            if (dataNascimento.isEmpty()) {
                System.out.println("Data de nascimento não pode estar vazia. Tente novamente.");
                return;
            }

            System.out.println("Digite a posição do jogador:");
            String posicao = scanner.nextLine().trim();
            if (posicao.isEmpty()) {
                System.out.println("Posição do jogador não pode estar vazia. Tente novamente.");
                return;
            }

            System.out.println("Digite o ID da seleção do jogador:");
            int selecaoId = scanner.nextInt();
            scanner.nextLine(); // Limpar o buffer do scanner

            Jogador jogador = new Jogador();
            jogador.setNome(nome);
            jogador.setDataNascimento(dataNascimento);
            jogador.setPosicao(posicao);
            jogador.setSelecao_id(selecaoId);

            jogadorOP.inserirJogador(jogador);
            System.out.println("Jogador inserido com sucesso!");
        } catch (InputMismatchException e) {
            System.out.println("ID da seleção deve ser um número inteiro. Tente novamente.");
            scanner.nextLine(); // Limpar o buffer do scanner
        } catch (SQLException e) {
            System.out.println("Erro ao inserir jogador: " + e.getMessage());
        }
    }

    private static void verClassificacaoGrupos() {
        try {
            grupoSelecaoOP.listarClassificacaoGrupos();
        } catch (Exception e) {
            System.out.println("Erro ao obter classificação dos grupos: " + e.getMessage());
        }
    }

    private static void verPartidas() {
        try {
            List<Partida> partidas = partidaOP.listarPartidas();
            for (Partida partida : partidas) {
                if (partida.getSelecaoCasa() != null && partida.getSelecaoFora() != null) {
                    System.out.println("Partida " + partida.getId() +
                            " Resultado: " +
                            " Seleção Casa: " + partida.getSelecaoCasa().getNome() + " " + partida.getGolsCasa() + " X " +
                            partida.getGolsFora() + " Seleção Fora: " + partida.getSelecaoFora().getNome() +
                            " Estádio: " + partida.getEstadio());
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar partidas: " + e.getMessage());
        }
    }

    private static void realizarPartida() {
        try {
            listarGrupos();
            System.out.println("Digite o ID do Grupo: ");
            int grupo_id = scanner.nextInt();
            scanner.nextLine(); // Limpar o buffer do scanner

            simularPartidaOP.simularPartidasDoGrupo(grupo_id, connection);
        } catch (InputMismatchException e) {
            System.out.println("ID do grupo deve ser um número inteiro. Tente novamente.");
            scanner.nextLine(); // Limpar o buffer do scanner
        } catch (SQLException e) {
            System.out.println("Erro ao realizar partida: " + e.getMessage());
        }
    }

    private static void listarEntidades(Scanner scanner) {
        int opcaoListar = 0;
        do {
            System.out.println("\n=== Listar Entidades ===");
            System.out.println("1. Listar Países");
            System.out.println("2. Listar Cidades");
            System.out.println("3. Listar Estádios");
            System.out.println("4. Listar Seleções");
            System.out.println("0. Voltar ao Menu Principal");
            System.out.print("\nEscolha uma opção: ");

            try {
                opcaoListar = scanner.nextInt();
                scanner.nextLine(); // Limpar o buffer do scanner

                switch (opcaoListar) {
                    case 1:
                        listarPaises();
                        break;
                    case 2:
                        listarCidades();
                        break;
                    case 3:
                        listarEstadios();
                        break;
                    case 4:
                        listarSelecoes();
                        break;
                    case 0:
                        System.out.println("Voltando ao Menu Principal...");
                        break;
                    default:
                        System.out.println("Opção inválida. Tente novamente.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Opção inválida. Insira um número correspondente à opção desejada.");
                scanner.nextLine(); // Limpar o buffer do scanner
            } catch (Exception e) {
                System.out.println("Erro ao listar entidades: " + e.getMessage());
            }

        } while (opcaoListar != 0);
    }

    private static void listarPaises() {
        try {
            List<Pais> paises = paisOP.listarPaises();
            for (Pais pais : paises) {
                System.out.println("Id: " + pais.getId());
                System.out.println("Nome do País: " + pais.getNome());
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar países: " + e.getMessage());
        }
    }

    private static void listarGrupos() {
        try {
            List<Grupo> grupos = grupoOP.listarGrupos();
            for (Grupo grupo : grupos) {
                System.out.println("Id: " + grupo.getId());
                System.out.println("Nome do Grupo: " + grupo.getNome());
            }
        } catch (Exception e) {
            System.out.println("Erro ao listar grupos: " + e.getMessage());
        }
    }

    private static void listarCidades() {
        try {
            List<Cidade> cidades = cidadeOP.listarCidades();
            for (Cidade cidade : cidades) {
                System.out.println("Id: " + cidade.getId());
                System.out.println("Nome da Cidade: " + cidade.getNome());
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar cidades: " + e.getMessage());
        }
    }

    private static void listarEstadios() {
        try {
            List<Estadio> estadios = estadioOP.listarEstadios();
            for (Estadio estadio : estadios) {
                System.out.println("Id: " + estadio.getId());
                System.out.println("Nome do Estádio: " + estadio.getNome());
                System.out.println("Capacidade: " + estadio.getCapacidade());
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar estádios: " + e.getMessage());
        }
    }

    private static void listarSelecoes() {
        try {
            List<Selecao> selecoes = selecaoOP.listarSelecoes();
            for (Selecao selecao : selecoes) {
                System.out.println("Id: " + selecao.getId());
                System.out.println("Nome da Seleção: " + selecao.getNome());
            }
        } catch (Exception e) {
            System.out.println("Erro ao listar seleções: " + e.getMessage());
        }
    }

    private static void listarMelhoresMarcadores() {
        try {
            List<EstatisticasIndividuais> estatisticasIndividuais = estatisticasIndividuaisOP.obterMelhoresMarcadores();
            for (EstatisticasIndividuais estatisticas : estatisticasIndividuais) {
                System.out.println("Jogador: " + estatisticas.getJogador().getNome() + " - Gols: " + estatisticas.getGolos());
            }
        } catch (Exception e) {
            System.out.println("Erro ao listar melhores marcadores: " + e.getMessage());
        }
    }

    private static void listarMelhoresAssistentes() {
        try {
            List<EstatisticasIndividuais> estatisticasIndividuais = estatisticasIndividuaisOP.obterMelhoresAssistentes();
            for (EstatisticasIndividuais estatisticas : estatisticasIndividuais) {
                System.out.println("Jogador: " + estatisticas.getJogador().getNome() + " - Assistências: " + estatisticas.getAssistencias());
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar melhores assistentes: " + e.getMessage());
        }
    }
}

/* Projeto de Software - Sistema de Produtividade Acadêmica (Refatorado)
   Nome do aluno desenvolvedor: Élisson Souza
   Padrões utilizados no refatoramento: * State:
                                          - Status.java, EmElaboracao.java, EmAndamento.java, Concluido.java;
                                        * Extract Method:
                                          - AppSistema.java: tentarNovamente();
                                          - Laboratorio.java: pesquisarProjeto(), pesquisarPublicacao(), pesquisarColaborador().
                                        * Move Accumulation to Collecting Parameter:
                                          - Projeto.java: imprimirParticipantes(), imprimirPublicacoes(), imprimirOrientacoes(), imprimir(), toString();
                                          - Publicacao.java: imprimirAutores(), imprimirProjetoAssociado(), imprimir(), toString();
                                          - Colaborador.java: imprimirPublicaoes(), imprimirProjeto(), imprimir(), toString();
                                          - Professor.java: imprimirOrientacoes(), toString().
 */

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;
import java.util.Scanner;

public class AppSistema {
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);
        Scanner entradaString = new Scanner(System.in);

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        int menu = 0, menu2 = 0;
        boolean concluido;
        String nomeColab, nomeColab2;

        Projeto proj;
        String tituloProj;
        Date dataInicio = null;
        Date dataFim = null;
        String agenciaFinanciadora;
        float valorFinanciado = 0;
        String objetivo;
        String descricao;

        Orientacao ori;

        Publicacao pub;
        String tituloPub;
        String conferencia;
        int anoPub = 0;

        do {
            concluido = false;
            do {
                try {
                    exibirMenu();
                    String aux = entrada.nextLine();
                    menu = Integer.parseInt(aux);
                    concluido = true;
                } catch (NumberFormatException e) {
                    System.out.println("\nDigite o valor numérico correspondente à opção desejada.");
                }
            } while (!concluido);

            switch (menu) {
                case 1:     //1 - Criar projeto
                    System.out.println("\n====== Criar Projeto ======");

                    concluido = false;
                    do {
                        System.out.print("Digite o título: ");
                        tituloProj = entradaString.nextLine();
                        if (!tituloProj.equals("")) {
                            if (Laboratorio.pesquisarProjeto(tituloProj) == null) {     //Verifica se já existe um projeto com o título digitado
                                concluido = true;
                            } else {
                                System.out.println("Já existe um projeto cadastrado com esse título.");
                            }
                        } else {
                            System.out.println("O título do projeto precisa ser inserido.");
                        }
                    } while (!concluido);

                    concluido = false;
                    do {
                        try {
                            System.out.print("Digite a data de inicio (DD/MM/AAAA): ");
                            dataInicio = sdf.parse(entradaString.nextLine());
                            concluido = true;
                        } catch (ParseException e) {
                            System.out.println("A data precisa ser inserida no formato DD/MM/AAAA.");
                        }
                    } while (!concluido);

                    concluido = false;
                    do {
                        try {
                            System.out.print("Digite a data de término (DD/MM/AAAA): ");
                            dataFim = sdf.parse(entradaString.nextLine());
                            concluido = true;
                        } catch (ParseException e) {
                            System.out.println("A data precisa ser inserida no formato DD/MM/AAAA.");
                        }
                    } while (!concluido);

                    concluido = false;
                    do {
                        System.out.print("Digite o nome da agência financiadora: ");
                        agenciaFinanciadora = entradaString.nextLine();
                        if (!agenciaFinanciadora.equals("")) {
                            concluido = true;
                        } else {
                            System.out.println("O nome da agência financiadora precisa ser inserido. Especifique se não houver uma.");
                        }
                    } while (!concluido);

                    concluido = false;
                    do {
                        try {
                            System.out.print("Digite o valor financiado: ");
                            String aux = entradaString.nextLine();
                            valorFinanciado = Float.parseFloat(aux);
                            concluido = true;
                        } catch (NumberFormatException e) {
                            System.out.println("Valor inserido num formato incorreto.");
                        }
                    } while (!concluido);

                    concluido = false;
                    do {
                        System.out.print("Digite o objetivo do projeto: ");
                        objetivo = entradaString.nextLine();
                        if (!objetivo.equals("")) {
                            concluido = true;
                        } else {
                            System.out.println("O objetivo do projeto precisa ser inserido.");
                        }
                    } while (!concluido);

                    concluido = false;
                    do {
                        System.out.print("Digite a descrição do projeto: ");
                        descricao = entradaString.nextLine();
                        if (!descricao.equals("")) {
                            concluido = true;
                        } else {
                            System.out.println("A descrição do projeto precisa ser inserida.");
                        }
                    } while (!concluido);

                    proj = new Projeto(tituloProj, dataInicio, dataFim, agenciaFinanciadora,
                            valorFinanciado, objetivo, descricao);

                    Laboratorio.adicionarProjeto(proj);

                    System.out.println("\nProjeto adicionado com sucesso.");
                    System.out.println(proj);
                    System.out.println("\nConsulte o projeto através do menu principal para acessar opções adicionais.");
                    break;

                case 2:     //2 - Consultar projeto
                    if (!Laboratorio.getListaProjetos().isEmpty()){
                        System.out.println("\nDigite o título do projeto a ser consultado:");
                        tituloProj = entradaString.nextLine();

                        if (Laboratorio.pesquisarProjeto(tituloProj) != null) {     //Verifica se o projeto existe
                            System.out.println("\nProjeto encontrado.\n" + Laboratorio.pesquisarProjeto(tituloProj));      //Imprime os dados do projeto
                        } else {
                            System.out.println("\nProjeto não encontrado.");
                            break;
                        }

                        do {
                            concluido = false;
                            do {
                                try {
                                    exibirMenu2();
                                    String aux = entrada.nextLine();
                                    menu = Integer.parseInt(aux);
                                    concluido = true;
                                } catch (NumberFormatException e) {
                                    System.out.println("\nDigite o valor numérico correspondente à opção desejada.");
                                }
                            } while (!concluido);

                            switch (menu) {
                                case 1:     //1 - Alocar participantes
                                    if (Laboratorio.verificarStatusProj(tituloProj, "Em elaboração")) {
                                        do {
                                            System.out.println("\nDigite o nome do colaborador:");
                                            nomeColab = entradaString.nextLine();

                                            if (Laboratorio.pesquisarColaborador(nomeColab) != null) {
                                                Laboratorio.associarColabProj(tituloProj, nomeColab);
                                                System.out.println("\nColaborador alocado com sucesso.");
                                                break;
                                            } else {
                                                System.out.println("\nColaborador não encontrado.\nCadastre o colaborador no sistema antes de alocá-lo a um projeto.\n");
                                                menu = tentarNovamente();
                                            }
                                        } while (menu != 2);
                                    } else {
                                        System.out.println("\nColaboradores só podem ser alocados em projetos em elaboração.");
                                    }
                                    break;

                                case 2:     //2 - Iniciar projeto
                                    Laboratorio.iniciarProjeto(tituloProj);
                                    break;

                                case 3:     //3 - Concluir projeto
                                    Laboratorio.concluirProjeto(tituloProj);
                                    break;

                                case 4: //4 - Exibir dados do projeto
                                    System.out.println("\n" + Laboratorio.pesquisarProjeto(tituloProj));
                                    break;

                                case 5:     //Voltar
                                    break;

                                default:
                                    System.out.println("\nOpção inválida.");
                            }
                        } while (menu != 5);
                    } else {
                        System.out.println("\nNão existem projetos cadastrados.");
                    }
                    break;

                case 3:     //3 - Cadastrar colaborador
                    do {
                        concluido = false;
                        do {
                            try {
                                exibirMenu3();
                                String aux = entrada.nextLine();
                                menu = Integer.parseInt(aux);
                                concluido = true;
                            } catch (NumberFormatException e) {
                                System.out.println("\nDigite o valor numérico correspondente à opção desejada.");
                            }
                        } while (!concluido);

                        switch (menu) {
                            case 1:
                                do {
                                    concluido = false;
                                    do {
                                        try {
                                            System.out.println("\nQue tipo de aluno?");
                                            System.out.println("1 - Aluno de graduação");
                                            System.out.println("2 - Aluno de mestrado");
                                            System.out.println("3 - Aluno de doutorado");
                                            System.out.println("4 - Cancelar");
                                            System.out.print("=====> Escolha uma opção: ");
                                            String aux = entrada.nextLine();
                                            menu = Integer.parseInt(aux);
                                            concluido = true;
                                        } catch (NumberFormatException e) {
                                            System.out.println("\nDigite o valor numérico correspondente à opção desejada.");
                                        }
                                    } while (!concluido);

                                    switch (menu) {
                                        case 1:
                                            cadastrarColaborador("Aluno de graduação");
                                            menu = 4;
                                            break;
                                        case 2:
                                            cadastrarColaborador("Aluno de mestrado");
                                            menu = 4;
                                            break;
                                        case 3:
                                            cadastrarColaborador("Aluno de doutorado");
                                            menu = 4;
                                            break;
                                        case 4:
                                            break;
                                        default:
                                            System.out.println("\nOpção inválida.\n");
                                    }
                                } while (menu != 4);
                                break;
                            case 2:
                                cadastrarColaborador("Professor");
                                menu = 4;
                                break;
                            case 3:
                                cadastrarColaborador("Pesquisador");
                                menu = 4;
                                break;
                            case 4:
                                break;
                            default:
                                System.out.println("\nOpção inválida.\n");
                        }
                    } while (menu != 4);
                    break;

                case 4:     //4 - Consultar colaborador
                    if (!Laboratorio.getListaColaboradores().isEmpty()){
                        do {
                            System.out.println("\nDigite o nome do colaborador a ser consultado:");
                            nomeColab = entradaString.nextLine();

                            if (Laboratorio.pesquisarColaborador(nomeColab) != null) {     //Verifica se o colaborador existe
                                System.out.println("\nColaborador encontrado.\n" + Laboratorio.pesquisarColaborador(nomeColab));     //Imprime os dados do colaborador
                                break;
                            } else {
                                System.out.println("\nColaborador não encontrado.");
                                menu = tentarNovamente();
                            }
                        } while (menu == 1);
                    } else {
                        System.out.println("\nNão existem colaboradores cadastrados.");
                    }
                    break;

                case 5:     //5 - Cadastrar produção acadêmica
                    do {
                        concluido = false;
                        do {
                            try {
                                exibirMenu5();
                                String aux = entrada.nextLine();
                                menu = Integer.parseInt(aux);
                                concluido = true;
                            } catch (NumberFormatException e) {
                                System.out.println("\nDigite o valor numérico correspondente à opção desejada.");
                            }
                        } while (!concluido);

                        switch (menu) {
                            case 1:     //Cadastrar publicação
                                System.out.println("\n====== Cadastrar Publicação ======");

                                concluido = false;
                                do {
                                    System.out.print("Digite o título da publicação: ");
                                    tituloPub = entradaString.nextLine();
                                    if (!tituloPub.equals("")) {
                                        if (Laboratorio.pesquisarPublicacao(tituloPub) == null) {     //Verifica se já existe uma publicação com o título digitado
                                            concluido = true;
                                        } else {
                                            System.out.println("Já existe uma publicação cadastrada com esse título.");
                                        }
                                    } else {
                                        System.out.println("O título da publicação precisa ser inserido.");
                                    }
                                } while (!concluido);

                                concluido = false;
                                do {
                                    System.out.print("Digite o nome da conferência onde foi publicada: ");
                                    conferencia = entradaString.nextLine();
                                    if (!conferencia.equals("")) {
                                        concluido = true;
                                    } else {
                                        System.out.println("O nome da conferência precisa ser inserido.");
                                    }
                                } while (!concluido);

                                concluido = false;
                                do {
                                    try {
                                        System.out.print("Digite o ano de publicação: ");
                                        String aux = entrada.nextLine();
                                        anoPub = Integer.parseInt(aux);
                                        concluido = true;
                                    } catch (NumberFormatException e) {
                                        System.out.println("Valor inserido num formato incorreto.");
                                    }
                                } while (!concluido);

                                pub = new Publicacao(tituloPub, conferencia, anoPub);

                                Laboratorio.adicionarPublicacao(pub);
                                System.out.println("\nPublicação adicionada com sucesso.");
                                System.out.println(pub);
                                System.out.println("\nConsulte a publicação através do menu principal para acessar opções adicionais,\ncomo adicionar autores e associar a um projeto.");

                                menu = 3;
                                break;

                            case 2:     // Cadastrar orientação
                                System.out.println("\n====== Cadastrar Orientação ======");
                                do {
                                    System.out.println("Digite o nome do colaborador orientador:");
                                    nomeColab = entradaString.nextLine();

                                    if (Laboratorio.pesquisarColaborador(nomeColab) != null) {
                                        if (Objects.requireNonNull(Laboratorio.pesquisarColaborador(nomeColab)).getOcupacao().equals("Professor")) {
                                            do {
                                                System.out.println("Digite o nome do colaborador a ser orientado:");
                                                nomeColab2 = entradaString.nextLine();

                                                if (Laboratorio.pesquisarColaborador(nomeColab2) != null) {
                                                    if (Objects.requireNonNull(Laboratorio.pesquisarColaborador(nomeColab2)).getOcupacao().equals("Aluno de graduação")
                                                    || Objects.requireNonNull(Laboratorio.pesquisarColaborador(nomeColab2)).getOcupacao().equals("Aluno de mestrado")
                                                    || Objects.requireNonNull(Laboratorio.pesquisarColaborador(nomeColab2)).getOcupacao().equals("Aluno de doutorado")) {
                                                        do {
                                                            concluido = false;
                                                            do {
                                                                try {
                                                                    System.out.println("\nDeseja associar esta orientação a um projeto?");
                                                                    System.out.println("1 - Sim");
                                                                    System.out.println("2 - Não");
                                                                    System.out.print("=====> Escolha uma opção: ");
                                                                    String aux = entrada.nextLine();
                                                                    menu = Integer.parseInt(aux);
                                                                    concluido = true;
                                                                } catch (NumberFormatException e) {
                                                                    System.out.println("\nDigite o valor numérico correspondente à opção desejada.");
                                                                }
                                                            } while (!concluido);

                                                            if (menu != 1 & menu != 2) {
                                                                System.out.println("\nOpção inválida.");
                                                            }
                                                        } while (menu != 1 & menu != 2);
                                                        if (menu == 1) {
                                                            do {
                                                                System.out.println("\nDigite o título do projeto:");
                                                                tituloProj = entradaString.nextLine();

                                                                if (Laboratorio.pesquisarProjeto(tituloProj) != null) {
                                                                    if (Laboratorio.verificarStatusProj(tituloProj, "Em andamento")) {
                                                                        ori = new Orientacao(nomeColab, nomeColab2, tituloProj);
                                                                        Laboratorio.adicionarOrientacao(ori, nomeColab, tituloProj);
                                                                        System.out.println("\nOrientação cadastrada com sucesso.");
                                                                        break;
                                                                    } else {
                                                                        System.out.println("\nOrientações só podem ser associadas a projetos em andamento.");
                                                                        menu = tentarNovamente();
                                                                    }
                                                                } else {
                                                                    System.out.println("\nProjeto não encontrado.");
                                                                    menu = tentarNovamente();
                                                                }
                                                            } while (menu != 2);
                                                        } else {
                                                            ori = new Orientacao(nomeColab, nomeColab2, null);
                                                            Laboratorio.adicionarOrientacao(ori, nomeColab, null);
                                                            System.out.println("\nOrientação cadastrada com sucesso.");
                                                        }
                                                    } else {
                                                        System.out.println("\nO colaborador não é um aluno. Operação cancelada.");
                                                    }
                                                    break;
                                                } else {
                                                    System.out.println("\nColaborador não encontrado.");
                                                    menu = tentarNovamente();
                                                }
                                            } while (menu != 2);
                                        } else {
                                            System.out.println("\nO colaborador não é um professor. Operação cancelada.");
                                        }
                                        break;
                                    } else {
                                        System.out.println("\nColaborador não encontrado.");
                                        menu = tentarNovamente();
                                    }
                                } while (menu != 2);
                                menu = 3;
                                break;

                            case 3:     //Cancelar
                                break;

                            default:
                                System.out.println("\nOpção inválida.");
                        }
                    } while (menu != 3);
                    break;

                case 6:     //6 - Consultar publicação
                    if (!Laboratorio.getListaPublicacoes().isEmpty()){
                        System.out.println("\nDigite o título da publicação a ser consultada:");
                        tituloPub = entradaString.nextLine();

                        if (Laboratorio.pesquisarPublicacao(tituloPub) != null) {     //Verifica se a publicação existe
                            System.out.println("\nPublicação encontrada.\n" + Laboratorio.pesquisarPublicacao(tituloPub));     //Imprime os dados da publicação
                        } else {
                            System.out.println("\nPublicação não encontrada.");
                            break;
                        }

                        do {
                            concluido = false;
                            do {
                                try {
                                    exibirMenu6();
                                    String aux = entrada.nextLine();
                                    menu = Integer.parseInt(aux);
                                    concluido = true;
                                } catch (NumberFormatException e) {
                                    System.out.println("\nDigite o valor numérico correspondente à opção desejada.");
                                }
                            } while (!concluido);

                            switch (menu) {
                                case 1:     //1 - Incluir autores
                                    do {
                                        System.out.println("\nDigite o nome do colaborador:");
                                        nomeColab = entradaString.nextLine();

                                        if (Laboratorio.pesquisarColaborador(nomeColab) != null) {
                                            Laboratorio.associarColabPub(tituloPub, nomeColab);
                                            System.out.println("\nO colaborador agora é um autor da publicação.");
                                            do {
                                                concluido = false;
                                                do {
                                                    try {
                                                        System.out.println("1 - Adicionar outro autor");
                                                        System.out.println("2 - Finalizar");
                                                        System.out.print("=====> Escolha uma opção: ");
                                                        String aux = entrada.nextLine();
                                                        menu2 = Integer.parseInt(aux);
                                                        concluido = true;
                                                    } catch (NumberFormatException e) {
                                                        System.out.println("\nDigite o valor numérico correspondente à opção desejada.\n");
                                                    }
                                                } while (!concluido);

                                                if (menu2 != 1 & menu2 != 2) {
                                                    System.out.println("\nOpção inválida.\n");
                                                }
                                            } while (menu2 != 1 & menu2 != 2);
                                        } else {
                                            System.out.println("\nColaborador não encontrado.\nCadastre o colaborador no sistema antes de associá-lo a uma publicação.\n");
                                            menu2 = tentarNovamente();
                                        }
                                    } while (menu2 != 2);
                                    break;

                                case 2:     //2 - Associar a um projeto
                                    do {
                                        System.out.println("\nDigite o título do projeto:");
                                        tituloProj = entradaString.nextLine();

                                        if (Laboratorio.pesquisarProjeto(tituloProj) != null) {
                                            if (Laboratorio.verificarStatusProj(tituloProj, "Em andamento")) {
                                                Laboratorio.associarPubProj(tituloProj, tituloPub);
                                                System.out.println("\nProjeto associado com sucesso.");
                                                break;
                                            } else {
                                                System.out.println("\nPublicações só podem ser associadas a projetos em andamento.");
                                            }
                                        } else {
                                            System.out.println("\nProjeto não encontrado.");
                                            menu = tentarNovamente();
                                        }
                                    } while (menu != 2);
                                    break;

                                case 3:
                                    break;

                                default:
                                    System.out.println("\nOpção inválida.\n");
                            }
                        } while (menu != 3);
                    } else {
                        System.out.println("\nNão existem publicações cadastradas.");
                    }
                    break;

                case 7:     //7 - Relatório do laboratório
                    System.out.println("\n====== Dados Gerais do Laboratório ======");
                    System.out.println("Colaboradores: " + Laboratorio.getListaColaboradores().size());
                    System.out.println("Projetos em elaboração: " + Laboratorio.qtdProjetos("Em elaboração"));
                    System.out.println("Projetos em andamento: " + Laboratorio.qtdProjetos("Em andamento"));
                    System.out.println("Projetos concluídos: " + Laboratorio.qtdProjetos("Concluído"));
                    System.out.println("Total de projetos: " + Laboratorio.getListaProjetos().size());
                    System.out.println("Publicações: " + Laboratorio.getListaPublicacoes().size());
                    System.out.println("Orientações: " + Laboratorio.getListaOrientacoes().size());
                    break;

                case 8:     //8 - Encerrar
                    System.out.println("\nSistema encerrado.");
                    break;

                default:
                    System.out.println("\nOpção inválida.");
            }
        } while (menu != 8);

    }

    static void cadastrarColaborador(String ocupacao) {
        Scanner entradaString = new Scanner(System.in);
        boolean concluido;
        Colaborador colab;
        Professor prof;
        String nomeColab;
        String email;

        System.out.println("\n====== Cadastrar Colaborador ======");

        concluido = false;
        do {
            System.out.print("Digite o nome: ");
            nomeColab = entradaString.nextLine();
            if (!nomeColab.equals("")) {
                if (Laboratorio.pesquisarColaborador(nomeColab) == null) {     //Verifica se já existe um colaborador com o nome digitado
                    concluido = true;
                } else {
                    System.out.println("Já existe um colaborador cadastrado com esse nome.");
                }
            } else {
                System.out.println("O nome do colaborador precisa ser inserido.");
            }
        } while (!concluido);

        concluido = false;
        do {
            System.out.print("Digite o email: ");
            email = entradaString.nextLine();
            if (!email.equals("")) {
                concluido = true;
            } else {
                System.out.println("O email do colaborador precisa ser inserido.");
            }
        } while (!concluido);

        System.out.println("\nColaborador cadastrado com sucesso.");

        if (ocupacao.equals("Professor")) {
            prof = new Professor(nomeColab, email, ocupacao);
            Laboratorio.adicionarColaborador(prof);
            System.out.println(prof);
        } else {
            colab = new Colaborador(nomeColab, email, ocupacao);
            Laboratorio.adicionarColaborador(colab);
            System.out.println(colab);
        }
    }

    static int tentarNovamente() {
        Scanner entrada = new Scanner(System.in);
        boolean concluido;
        int menu = 0;
        do {
            concluido = false;
            do {
                try {
                    System.out.println("1 - Tentar novamente");
                    System.out.println("2 - Voltar");
                    System.out.print("=====> Escolha uma opção: ");
                    String aux = entrada.nextLine();
                    menu = Integer.parseInt(aux);
                    concluido = true;
                } catch (NumberFormatException e) {
                    System.out.println("\nDigite o valor numérico correspondente à opção desejada.\n");
                }
            } while (!concluido);

            if (menu != 1 & menu != 2) {
                System.out.println("\nOpção inválida.\n");
            }
        } while (menu != 1 & menu != 2);
        return menu;
    }

    static void exibirMenu() {     //Menu principal
        System.out.println("\n====== LABORATÓRIO ======");
        System.out.println("1 - Criar projeto");
        System.out.println("2 - Consultar projeto");
        System.out.println("3 - Cadastrar colaborador");
        System.out.println("4 - Consultar colaborador");
        System.out.println("5 - Cadastrar produção acadêmica");
        System.out.println("6 - Consultar publicação");
        System.out.println("7 - Relatório do laboratório");
        System.out.println("8 - Encerrar");
        System.out.print("=====> Escolha uma opção: ");
    }

    static void exibirMenu2() {     //Menu de "Consultar projeto"
        System.out.println("\n====== OPÇÕES DO PROJETO ======");
        System.out.println("1 - Alocar participante");
        System.out.println("2 - Iniciar projeto");
        System.out.println("3 - Concluir projeto");
        System.out.println("4 - Exibir dados do projeto");
        System.out.println("5 - Voltar");
        System.out.print("=====> Escolha uma opção: ");
    }

    static void exibirMenu3() {     //Menu de "Cadastrar colaborador"
        System.out.println("\nQuem você deseja cadastrar?");
        System.out.println("1 - Cadastrar um aluno");
        System.out.println("2 - Cadastrar um professor");
        System.out.println("3 - Cadastrar um pesquisador");
        System.out.println("4 - Cancelar");
        System.out.print("=====> Escolha uma opção: ");
    }

    static void exibirMenu5() {     //Menu de "Cadastrar produção acadêmica"
        System.out.println("\nQue tipo de produção acadêmica você deseja cadastrar?");
        System.out.println("1 - Publicação");
        System.out.println("2 - Orientação");
        System.out.println("3 - Cancelar");
        System.out.print("=====> Escolha uma opção: ");
    }

    static void exibirMenu6() {     //Menu de "Consultar produção acadêmica"
        System.out.println("\n====== OPÇÕES DA PUBLICAÇÃO ======");
        System.out.println("1 - Incluir autores");
        System.out.println("2 - Associar a um projeto");
        System.out.println("3 - Voltar");
        System.out.print("=====> Escolha uma opção: ");
    }
}

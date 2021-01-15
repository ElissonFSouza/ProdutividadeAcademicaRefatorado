import java.util.ArrayList;
import java.util.Collections;

public class Laboratorio {
    private static final ArrayList<Projeto> listaProjetos = new ArrayList<>();
    private static final ArrayList<Publicacao> listaPublicacoes = new ArrayList<>();
    private static final ArrayList<Orientacao> listaOrientacoes = new ArrayList<>();
    private static final ArrayList<Colaborador> listaColaboradores = new ArrayList<>();

    public static ArrayList<Projeto> getListaProjetos() {
        return listaProjetos;
    }

    public static ArrayList<Publicacao> getListaPublicacoes() {
        return listaPublicacoes;
    }

    public static ArrayList<Orientacao> getListaOrientacoes() {
        return listaOrientacoes;
    }

    public static ArrayList<Colaborador> getListaColaboradores() {
        return listaColaboradores;
    }

    public static void adicionarProjeto(Projeto proj) {
        listaProjetos.add(proj);
    }

    public static void adicionarPublicacao(Publicacao pub) {
        listaPublicacoes.add(pub);
        Collections.sort(listaPublicacoes);
    }

    public static void adicionarOrientacao(Orientacao ori, String nomeProf, String tituloProj) {
        listaOrientacoes.add(ori);
        for(Colaborador colab : listaColaboradores) {
            if (colab.getNome().equalsIgnoreCase(nomeProf)) {
                ((Professor) colab).adicionarOrientacao(ori);
                break;
            }
        }
        for (Projeto proj : listaProjetos) {
            if (proj.getTitulo().equalsIgnoreCase(tituloProj)) {
                proj.adicionarOrientacao(ori);
                break;
            }
        }

    }

    public static void adicionarColaborador(Colaborador colab) {
        listaColaboradores.add(colab);
    }

    public static int qtdProjetos(String status) {
        int qtd = 0;
        for(Projeto proj : listaProjetos) {
            if (proj.status.toString().equals(status)) {
                qtd++;
            }
        }
        return qtd;
    }

    public static boolean verificarSituacaoAlunos(String titulo) {     //Verifica se cada aluno participante do projeto já participa de 2 ou mais projetos em andamento
        for (Projeto proj : listaProjetos) {
            if (proj.getTitulo().equalsIgnoreCase(titulo)) {
                for(Colaborador colab : proj.getListaParticipantes()){
                    if (colab.getOcupacao().contains("Aluno")) {
                        int qtd = 0;
                        for(Projeto proj2 : colab.getListaMeusProjetos()){
                            if (proj2.getStatus().equals(proj2.EmAndamento)) {
                                qtd += 1;
                            }
                        }
                        if (qtd >= 2) {
                            return true;
                        }
                    }
                }
                break;
            }
        }
        return false;
    }

    public static boolean verificarProfessorProj(String titulo) {     //Verifica se existe pelo menos 1 professor alocado ao projeto
        for (Projeto proj : listaProjetos) {
            if (proj.getTitulo().equalsIgnoreCase(titulo)) {
                for(Colaborador colab : proj.getListaParticipantes()){
                    if(colab.getOcupacao().equals("Professor")){
                        return true;
                    }
                }
                break;
            }
        }
        return false;
    }

    public static boolean verificarStatusProj(String titulo, String status) {
        for(Projeto proj : listaProjetos) {
            if (proj.getTitulo().equalsIgnoreCase(titulo)) {
                return proj.status.toString().equals(status);
            }
        }
        return false;
    }

    public static boolean verificarPubProjeto(String titulo) {
        for(Projeto proj : listaProjetos) {
            if (proj.getTitulo().equalsIgnoreCase(titulo)) {
                if (proj.getListaPublicacoesAssociadas().isEmpty()) {
                    return true;
                }
                break;
            }
        }
        return false;
    }

    public static Projeto pesquisarProjeto(String titulo) {
        for(Projeto proj : listaProjetos) {
            if (proj.getTitulo().equalsIgnoreCase(titulo)) {
                return proj;
            }
        }
        return null;
    }

    public static Publicacao pesquisarPublicacao(String titulo) {
        for(Publicacao pub : listaPublicacoes) {
            if (pub.getTitulo().equalsIgnoreCase(titulo)) {
                return pub;
            }
        }
        return null;
    }

    public static Colaborador pesquisarColaborador(String nome) {
        for(Colaborador colab : listaColaboradores) {
            if (colab.getNome().equalsIgnoreCase(nome)) {
                return colab;
            }
        }
        return null;
    }

    public static void associarColabProj(String titulo, String nome) {      //Cadastra o colaborador na lista de participantes do projeto
        Colaborador colab = pesquisarColaborador(nome);                      //e inclui o projeto na lista de projetos em que o colaborador participa
        Projeto proj = pesquisarProjeto(titulo);

        if (colab != null && proj != null) {
            colab.adicionarProjeto(proj);
            proj.adicionarParticipante(colab);
        }
    }

    public static void associarColabPub(String titulo, String nome) { //Cadastra o colaborador na lista de autores da publicação
        Colaborador colab = pesquisarColaborador(nome);
        Publicacao pub = pesquisarPublicacao(titulo);

        if (colab != null && pub != null) {
            colab.adicionarPublicacao(pub);
            pub.adicionarAutor(colab);
        }
    }

    public static void associarPubProj(String tituloProj, String tituloPub) { //Inclui a publicação na lista de publicações do projeto
        Publicacao pub = pesquisarPublicacao(tituloPub);
        Projeto proj = pesquisarProjeto(tituloProj);

        if (pub != null && proj != null) {
            pub.setProjetoAssociado(proj);
            proj.adicionarPublicacao(pub);
        }
    }

    public static void iniciarProjeto(String titulo) {
        for(Projeto proj : listaProjetos) {
            if (proj.getTitulo().equalsIgnoreCase(titulo)) {
                proj.iniciarProjeto();
                break;
            }
        }
    }

    public static void concluirProjeto(String titulo) {
        for(Projeto proj : listaProjetos) {
            if (proj.getTitulo().equalsIgnoreCase(titulo)) {
                proj.concluirProjeto();
                break;
            }
        }
    }
}

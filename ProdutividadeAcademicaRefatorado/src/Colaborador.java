import java.util.ArrayList;
import java.util.Collections;

public class Colaborador {
    private final String nome;
    private final String ocupacao;
    private final String email;
    private final ArrayList<Projeto> listaMeusProjetos = new ArrayList<>();
    private final ArrayList<Publicacao> listaMinhasPublicacoes = new ArrayList<>();

    public Colaborador(String nome, String email, String ocupacao) {
        this.nome = nome;
        this.ocupacao = ocupacao;
        this.email = email;
    }

    public ArrayList<Projeto> getListaMeusProjetos() {
        return listaMeusProjetos;
    }

    public ArrayList<Publicacao> getListaMinhasPublicacoes() {
        return listaMinhasPublicacoes;
    }

    public void adicionarProjeto(Projeto proj){
        listaMeusProjetos.add(proj);
        listaMeusProjetos.sort((p1, p2) -> p2.getDataFim().compareTo(p1.getDataFim()));
    }

    public void adicionarPublicacao(Publicacao pub){
        listaMinhasPublicacoes.add(pub);
        Collections.sort(listaMinhasPublicacoes);
    }

    public String getNome() {
        return nome;
    }

    public String getOcupacao() {
        return ocupacao;
    }

    public String toString() {
        StringBuilder publicacoes = new StringBuilder();
        if (getListaMinhasPublicacoes().isEmpty()) {
            publicacoes = new StringBuilder(" Este colaborador não é autor de nenhuma publicação.");
        } else {
            for(Publicacao pub : listaMinhasPublicacoes){
                publicacoes.append("\n  ").append(pub.getTitulo()).append(" (").append(pub.getAnoPublic()).append(")");
            }
        }

        StringBuilder projetos = new StringBuilder();
        if (getListaMeusProjetos().isEmpty()) {
            projetos = new StringBuilder(" Este colaborador não participa de nenhum projeto.");
        } else {
            for(Projeto proj : listaMeusProjetos){
                projetos.append("\n  ").append(proj.getTitulo()).append(" (").append(proj.getStatus()).append(")");
            }
        }

        return "- Nome: " + nome + "\n- Email: " + email + "\n- Ocupação: " + ocupacao
                + "\n- Projetos em que participa:" + projetos + "\n- Publicações:" + publicacoes;
    }
}

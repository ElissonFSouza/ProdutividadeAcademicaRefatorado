import java.util.ArrayList;

public class Publicacao implements Comparable<Publicacao> {
    private final String titulo;
    private final String conferencia;
    private final int anoPublic;
    private final ArrayList<Colaborador> listaAutores = new ArrayList<>();
    private Projeto projetoAssociado = null;

    public Publicacao(String titulo, String conferencia, int anoPublic) {
        this.titulo = titulo;
        this.conferencia = conferencia;
        this.anoPublic = anoPublic;
    }

    public ArrayList<Colaborador> getListaAutores() {
        return listaAutores;
    }

    public void adicionarAutor(Colaborador colab){
        this.listaAutores.add(colab);
    }

    public String getTitulo() {
        return titulo;
    }

    public int getAnoPublic() {
        return anoPublic;
    }

    public void setProjetoAssociado(Projeto projetoAssociado) {
        this.projetoAssociado = projetoAssociado;
    }

    @Override
    public int compareTo(Publicacao pub) {
        return (pub.anoPublic - this.anoPublic);
    }

    public StringBuilder imprimirAutores() {
        StringBuilder autores = new StringBuilder();
        if (getListaAutores().isEmpty()) {
            autores = new StringBuilder(" Não há autores associados à publicação.");
        } else {
            for(Colaborador colab : listaAutores){
                autores.append("\n  ").append(colab.getNome()).append(" (").append(colab.getOcupacao()).append(")");
            }
        }
        return autores;
    }

    public String imprimirProjetoAssociado() {
        String projeto;
        if (projetoAssociado == null) {
            projeto = "Não há projeto associado à publicação.";
        } else {
            projeto = projetoAssociado.getTitulo();
        }
        return projeto;
    }

    public String imprimir() {
        return "- Título da publicação: " + titulo + "\n- Conferência onde foi publicada: "
                + conferencia + "\n- Ano de publicação: " + anoPublic + "\n- Autores:" + imprimirAutores()
                + "\n- Projeto associado: " + imprimirProjetoAssociado();
    }

    public String toString() {
        return imprimir();
    }
}

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

public class Projeto {
    private final String titulo;
    private final Date dataInicio;
    private final Date dataFim;
    private final String agenciaFinanciadora;
    private final float valorFinanciado;
    private final String objetivo;
    private final String descricao;
    private final ArrayList<Colaborador> listaParticipantes = new ArrayList<>();
    private final ArrayList<Publicacao> listaPublicacoesAssociadas = new ArrayList<>();
    private final ArrayList<Orientacao> listaOrientacoesAssociadas = new ArrayList<>();

    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    Status EmElaboracao;
    Status EmAndamento;
    Status Concluido;
    Status status;

    public Projeto(String titulo, Date dataInicio, Date dataFim,
                   String agenciaFinanciadora, float valorFinanciado,
                   String objetivo, String descricao) {
        this.titulo = titulo;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.agenciaFinanciadora = agenciaFinanciadora;
        this.valorFinanciado = valorFinanciado;
        this.objetivo = objetivo;
        this.descricao = descricao;

        EmElaboracao = new EmElaboracao(this);
        EmAndamento = new EmAndamento(this);
        Concluido = new Concluido(this);
        status = EmElaboracao;
    }

    public ArrayList<Colaborador> getListaParticipantes() {
        return listaParticipantes;
    }

    public ArrayList<Publicacao> getListaPublicacoesAssociadas() {
        return listaPublicacoesAssociadas;
    }

    public ArrayList<Orientacao> getListaOrientacoesAssociadas() {
        return listaOrientacoesAssociadas;
    }

    public void adicionarParticipante(Colaborador colab){
        listaParticipantes.add(colab);
    }

    public void adicionarPublicacao(Publicacao pub) {
        listaPublicacoesAssociadas.add(pub);
        Collections.sort(listaPublicacoesAssociadas);
    }

    public void adicionarOrientacao(Orientacao ori) {
        listaOrientacoesAssociadas.add(ori);
    }

    public String getTitulo() {
        return titulo;
    }

    public Date getDataFim() {
        return dataFim;
    }

    public Status getStatus() {
        return status;
    }
    public void setStatus(Status status) {
        this.status = status;
    }

    public Status getEmAndamento() {
        return EmAndamento;
    }

    public Status getConcluido() {
        return Concluido;
    }

    public void iniciarProjeto() {
        status.iniciarProjeto();
    }

    public void concluirProjeto() {
        status.concluirProjeto();
    }

    public String toString() {
        StringBuilder participantes = new StringBuilder();
        if (getListaParticipantes().isEmpty()) {
            participantes = new StringBuilder(" Não há colaboradores alocados ao projeto.");
        } else {
            for(Colaborador colab : listaParticipantes) {
                participantes.append("\n  ").append(colab.getNome()).append(" (").append(colab.getOcupacao()).append(")");
            }
        }

        StringBuilder publicacoes = new StringBuilder();
        if (getListaPublicacoesAssociadas().isEmpty()) {
            publicacoes = new StringBuilder(" Não há publicações associadas ao projeto.");
        } else {
            for(Publicacao pub : listaPublicacoesAssociadas) {
                publicacoes.append("\n  ").append(pub.getTitulo()).append(" (").append(pub.getAnoPublic()).append(")");
            }
        }

        StringBuilder orientacoes = new StringBuilder();
        if (getListaOrientacoesAssociadas().isEmpty()) {
            orientacoes = new StringBuilder(" Não há orientações associadas ao projeto.");
        } else {
                for(Orientacao ori : listaOrientacoesAssociadas) {
                if (ori.getTituloProj() != null) {
                    orientacoes.append("\n  ").append(ori.getNomeProf()).append("/").append(ori.getNomeColab()).append(" (").append(ori.getTituloProj()).append(")");
                } else {
                    orientacoes.append("\n  ").append(ori.getNomeProf()).append("/").append(ori.getNomeColab());
                }

            }
        }

        return "- Título do projeto: " + titulo + "\n- Data de início: " + sdf.format(dataInicio)
                + "\n- Data de término: " + sdf.format(dataFim) + "\n- Agência financiadora: " + agenciaFinanciadora + "\n- Valor financiado: "
                + String.format("R$ %.2f", valorFinanciado) + "\n- Objetivo: " + objetivo + "\n- Descrição: " + descricao
                + "\n- Participantes:" + participantes + "\n- Publicações:" + publicacoes + "\n- Orientações:" + orientacoes
                + "\n- Status: " + status;
    }
}


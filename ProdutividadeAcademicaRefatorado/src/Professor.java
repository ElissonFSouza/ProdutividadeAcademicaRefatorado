import java.util.ArrayList;

public class Professor extends Colaborador{
    private final ArrayList<Orientacao> listaMinhasOrientacoes = new ArrayList<>();

    public Professor(String nome, String email, String ocupacao) {
        super(nome, email, ocupacao);
    }

    public ArrayList<Orientacao> getListaMinhasOrientacoes() {
        return listaMinhasOrientacoes;
    }

    public void adicionarOrientacao(Orientacao ori) {
        listaMinhasOrientacoes.add(ori);
    }

    @Override
    public String toString() {
        StringBuilder orientacoes = new StringBuilder();
        if (getListaMinhasOrientacoes().isEmpty()) {
            orientacoes = new StringBuilder(" Este colaborador não possui orientações.");
        } else {
            for(Orientacao ori : listaMinhasOrientacoes) {
                if (ori.getTituloProj() != null) {
                    orientacoes.append("\n  ").append(ori.getNomeColab()).append(" (").append(ori.getTituloProj()).append(")");
                } else {
                    orientacoes.append("\n  ").append(ori.getNomeColab());
                }

            }
        }
        return super.toString() + "\n- Orientações:" + orientacoes;
    }
}

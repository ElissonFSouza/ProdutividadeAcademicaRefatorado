public class Orientacao {
    private final String nomeProf;
    private final String nomeColab;
    private final String tituloProj;

    public Orientacao(String nomeProf, String nomeColab, String tituloProj) {
        this.nomeProf = nomeProf;
        this.nomeColab = nomeColab;
        this.tituloProj = tituloProj;
    }

    public String getNomeProf() {
        return nomeProf;
    }

    public String getNomeColab() {
        return nomeColab;
    }

    public String getTituloProj() {
        return tituloProj;
    }
}

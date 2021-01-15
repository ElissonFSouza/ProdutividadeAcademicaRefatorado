public class Concluido implements Status {
    Projeto proj;

    public Concluido(Projeto proj) {
        this.proj = proj;
    }

    public void iniciarProjeto() {
        System.out.println("\nO projeto já foi iniciado anteriormente.");
    }

    public void concluirProjeto() {
        System.out.println("\nO projeto já foi concluído anteriormente.");
    }

    public String toString() {
        return "Concluído";
    }
}

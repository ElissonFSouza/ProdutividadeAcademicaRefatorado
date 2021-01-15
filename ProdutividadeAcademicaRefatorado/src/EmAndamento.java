public class EmAndamento implements Status {
    Projeto proj;

    public EmAndamento(Projeto proj) {
        this.proj = proj;
    }

    public void iniciarProjeto() {
        System.out.println("\nO projeto já foi iniciado anteriormente.");
    }

    public void concluirProjeto() {
        if (Laboratorio.verificarPubProjeto(proj.getTitulo())) {
            System.out.println("\nO projeto não pode ser concluído.\nDeve existir ao menos 1 publicação associada ao projeto.");
        } else {
            proj.setStatus(proj.getConcluido());
            System.out.println("\nProjeto concluído com sucesso.\nStatus atual: Concluído");
        }
    }

    public String toString() {
        return "Em andamento";
    }
}

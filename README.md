## Padrões utilizados no refatoramento do projeto:
### State
- Status.java, EmElaboracao.java, EmAndamento.java, Concluido.java
### Extract Method
- **AppSistema.java**: tentarNovamente();
- **Laboratorio.java**: pesquisarProjeto(), pesquisarPublicacao(), pesquisarColaborador().
### Move Accumulation to Collecting Parameter
- **Projeto.java**: imprimirParticipantes(), imprimirPublicacoes(), imprimirOrientacoes(), imprimir(), toString();
- **Publicacao.java**: imprimirAutores(), imprimirProjetoAssociado(), imprimir(), toString();
- **Colaborador.java**: imprimirPublicaoes(), imprimirProjeto(), imprimir(), toString();
- **Professor.java**: imprimirOrientacoes(), toString().

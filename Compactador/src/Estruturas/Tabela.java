package Estruturas;

public class Tabela {

    private ListaEstatica<String> simbolos = new ListaEstatica<>();
    private ListaEstatica<String> codigos = new ListaEstatica<>();

    public void adicionar(String simbolo, String codigo) {
        this.simbolos.adicionar(simbolo);
        this.codigos.adicionar(codigo);
    }

    public String encontrarCodigo(String simbolo) {
        if (this.simbolos.contains(simbolo)) {
            return this.codigos.get(this.simbolos.indexOf(simbolo));
        }
        else return null;
    }

}

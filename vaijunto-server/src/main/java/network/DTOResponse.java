package network;

public class DTOResponse {
    
    private boolean sucesso;
    private String mensagem;
    private Object dados; // Pode guardar uma lista de itinerários, uma reserva, etc.

    // Construtor para respostas simples (sucesso/erro + texto)
    public DTOResponse(boolean sucesso, String mensagem) {
        this.sucesso = sucesso;
        this.mensagem = mensagem;
        this.dados = null;
    }

    // Construtor para respostas com dados complexos
    public DTOResponse(boolean sucesso, String mensagem, Object dados) {
        this.sucesso = sucesso;
        this.mensagem = mensagem;
        this.dados = dados;
    }

    // Getters e Setters
    public boolean isSucesso() {
        return sucesso;
    }

    public void setSucesso(boolean sucesso) {
        this.sucesso = sucesso;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public Object getDados() {
        return dados;
    }

    public void setDados(Object dados) {
        this.dados = dados;
    }
}
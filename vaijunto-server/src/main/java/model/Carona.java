package model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;//gera ID randomico

public class Carona {
    private String id;
    private String motorista;
    private List<Cidades> rota;
    private List<Trecho> trechos;
    private String data;
    private String hora;
    private int vagasTotais;
    private boolean ativaOuNao;
    private List<Double> precoPorTrecho;

    /** 
     * Classe carona é o objeto que o motorista vai gerar e disponibilizar para
     * os clientes
    */
    public Carona(List<Cidades> rota, String data, String hora, 
                  int vagasTotais, boolean ativaOuNao, List<Double> precos, String motorista) {
        
        if (rota == null || rota.size() < 2) {
            throw new IllegalArgumentException("A rota tem que ter pelo menos 2 cidades");
        }
        if (precos == null || precos.size() != (rota.size() - 1)) {
            throw new IllegalArgumentException("Quantidade de preços incompatível com a rota");
        }

        this.id = UUID.randomUUID().toString();
        this.rota = new ArrayList<>(rota);
        this.precoPorTrecho = new ArrayList<>(precos); // Inicializa ANTES de gerar os trechos
        this.trechos = gerarTrechos(this.rota, this.precoPorTrecho, vagasTotais, data);
        this.data = data;
        this.hora = hora;
        this.motorista = motorista;
        this.vagasTotais = vagasTotais;
        this.ativaOuNao = ativaOuNao;
    }

    //gera os trechos individuais com base na lista de cidades passada pelo motorista
    private List<Trecho> gerarTrechos(List<Cidades> rotaCidades, List<Double> precos, int vagas, String data) {
        List<Trecho> lista = new ArrayList<>();//novo array vazio
        for (int i = 0; i < rotaCidades.size() - 1; i++) {//itera o num de cidades
            Cidades origem = rotaCidades.get(i);//pega a origem
            Cidades destino = rotaCidades.get(i + 1);//pega o destino
            double precoDoTrecho = precos.get(i);//associa o preço
            
            lista.add(new Trecho(this.id, data, origem, destino, vagas, precoDoTrecho, this.motorista));
        }
        return lista;
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public List<Cidades> getRota() {
        return rota;
    }
    public void setRota(List<Cidades> rota) {
        this.rota = rota;
    }
    public List<Trecho> getTrechos() {
        return trechos;
    }
    public void setTrechos(List<Trecho> trechos) {
        this.trechos = trechos;
    }
    public String getData() {
        return data;
    }
    public void setData(String data) {
        this.data = data;
    }
    public String getHora() {
        return hora;
    }
    public void setHora(String hora) {
        this.hora = hora;
    }
    public int getVagasTotais() {
        return vagasTotais;
    }
    public void setVagasTotais(int vagasTotais) {
        this.vagasTotais = vagasTotais;
    }
    public String getNomeMotorista() {
        return motorista;
    }
    public void setNomeMotorista(String nomeMotorista) {
        this.motorista = nomeMotorista;
    }
    public boolean isAtivaOuNao() {
        return ativaOuNao;
    }
    public void setAtivaOuNao(boolean ativaOuNao) {
        this.ativaOuNao = ativaOuNao;
    }

    public List<Double> getPrecoPorTrecho() {
        return precoPorTrecho;
    }

    public void setPrecoPorTrecho(List<Double> precoPorTrecho) {
        this.precoPorTrecho = precoPorTrecho;
    }
}

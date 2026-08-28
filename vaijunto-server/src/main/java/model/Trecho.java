package model;

import java.util.Objects;

public class Trecho {
    private String caronaId;
    private String data;
    private Cidades cidadeOrigem;
    private Cidades cidadeDestino;
    private final int totalLugares;
    private int lugaresDisponiveis;
    private double preco;

    // Construtor compatível com a chamada em Carona.java
    public Trecho(String caronaId, String data, Cidades cidadeOrigem, Cidades cidadeDestino, int totalLugares, double preco) {
        this.caronaId = caronaId;
        this.data = data;
        this.cidadeOrigem = cidadeOrigem;
        this.cidadeDestino = cidadeDestino;
        this.totalLugares = totalLugares;
        this.lugaresDisponiveis = totalLugares;
        this.preco = preco;
    }

    // Controle atômico com exclusão mútua
    public synchronized boolean reservarLugar() {
        if (this.lugaresDisponiveis > 0) {
            this.lugaresDisponiveis--;
            return true;
        }
        return false;
    }

    public synchronized void liberarLugar() {
        if (this.lugaresDisponiveis < this.totalLugares) {
            this.lugaresDisponiveis++;
        }
    }

    // Getters e Setters
    public String getCaronaId() {
        return caronaId;
    }

    public void setCaronaId(String caronaId) {
        this.caronaId = caronaId;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Cidades getCidadeOrigem() {
        return cidadeOrigem;
    }

    public void setCidadeOrigem(Cidades cidadeOrigem) {
        this.cidadeOrigem = cidadeOrigem;
    }

    public Cidades getCidadeDestino() {
        return cidadeDestino;
    }

    public void setCidadeDestino(Cidades cidadeDestino) {
        this.cidadeDestino = cidadeDestino;
    }

    public int getTotalLugares() {
        return totalLugares;
    }

    public synchronized int getLugaresDisponiveis() {
        return lugaresDisponiveis;
    }

    public synchronized void setLugaresDisponiveis(int lugaresDisponiveis) {
        this.lugaresDisponiveis = lugaresDisponiveis;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Trecho trecho = (Trecho) o;
        return Objects.equals(caronaId, trecho.caronaId) &&
               cidadeOrigem == trecho.cidadeOrigem &&
               cidadeDestino == trecho.cidadeDestino &&
               Objects.equals(data, trecho.data);
    }

    @Override
    public int hashCode() {
        return Objects.hash(caronaId, data, cidadeOrigem, cidadeDestino);
    }
}
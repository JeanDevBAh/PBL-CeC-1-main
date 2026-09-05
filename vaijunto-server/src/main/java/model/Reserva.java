package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class Reserva {
    private final String id;
    private final String passageiroId;
    private final String loginPassageiro;
    private final List<Trecho> trechos;
    private final double precoTotal;
    private boolean ativa;

    public Reserva(String passageiroId, String loginPassageiro, List<Trecho> trechos) {
        this.id = UUID.randomUUID().toString();
        this.passageiroId = passageiroId;
        this.loginPassageiro = loginPassageiro;
        this.trechos = new ArrayList<>(trechos);
        this.precoTotal = calcularPrecoTotal(trechos);
        this.ativa = true;
    }

    private double calcularPrecoTotal(List<Trecho> trechos) {
        double soma = 0.0;
        for (Trecho t : trechos) {
            soma += t.getPreco();
        }
        return soma;
    }

    // Libera os assentos ocupados caso o passageiro cancele a reserva
  public synchronized void cancelar() {
        if (this.ativa) {
            this.ativa = false;
            for (Trecho t : this.trechos) {
                t.liberarLugar(loginPassageiro);
            }
        }
    }

    // Getters
    public String getId() { return id; }
    public String getPassageiroId() { return passageiroId; }
    public String getLoginPassageiro() { return loginPassageiro; }
    public List<Trecho> getTrechos() { return Collections.unmodifiableList(trechos); }
    public double getPrecoTotal() { return precoTotal; }
    public boolean isAtiva() { return ativa; }
}
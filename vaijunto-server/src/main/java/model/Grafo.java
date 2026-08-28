package model;

import java.util.*;

public class Grafo {

    private final Map<Cidades, List<Trecho>> arestas;

    public Grafo() {
        this.arestas = new HashMap<>();
        for (Cidades cidade : Cidades.values()) {
            arestas.put(cidade, new ArrayList<>());
        }
    }

    public synchronized void addTrechos(List<Trecho> trechos) {
        for (Trecho t : trechos) {
            arestas.get(t.getCidadeOrigem()).add(t);
        }
    }

    public synchronized void removeTrechosDaCarona(String caronaId) {
        for (List<Trecho> lista : arestas.values()) {
            lista.removeIf(t -> t.getCaronaId().equals(caronaId));
        }
    }

    public synchronized List<Itinerario> buscarItinerarios(Cidades origem, Cidades destino, String dataDesejada) {
        List<Itinerario> resultado = new ArrayList<>();

        // Validações de entrada: nulos, origem == destino e cidades bloqueadas (ex: Tahiti)
        if (origem == null || destino == null || origem == destino || dataDesejada == null) {
            return resultado;
        }
        if (!origem.isDisponivel() || !destino.isDisponivel()) {
            return resultado;
        }
        
        List<Trecho> caminhoAtual = new ArrayList<>();
        Set<Cidades> cidadesVisitadas = new HashSet<>();

        cidadesVisitadas.add(origem);
        buscaCaminhoDFS(origem, destino, dataDesejada, cidadesVisitadas, caminhoAtual, resultado);

        return resultado;
    }

    private void buscaCaminhoDFS(Cidades atual, Cidades destino, String dataDesejada,
                                 Set<Cidades> visitadas, List<Trecho> caminhoAtual, 
                                 List<Itinerario> resultado) {
        if (atual == destino) {
            resultado.add(new Itinerario(new ArrayList<>(caminhoAtual)));
            return;
        }      
        
        for (Trecho trecho : arestas.get(atual)) {
            Cidades proxima = trecho.getCidadeDestino();
            
            boolean mesmaData = trecho.getData() != null && trecho.getData().equals(dataDesejada);
            boolean temVagas = trecho.getLugaresDisponiveis() > 0;
            boolean naoVisitada = !visitadas.contains(proxima);
            boolean cidadeAtiva = proxima.isDisponivel();

            if (mesmaData && temVagas && naoVisitada && cidadeAtiva) {
                visitadas.add(proxima);
                caminhoAtual.add(trecho);

                buscaCaminhoDFS(proxima, destino, dataDesejada, visitadas, caminhoAtual, resultado);

                // Backtracking
                caminhoAtual.remove(caminhoAtual.size() - 1);
                visitadas.remove(proxima);
            }
        }
    }
}
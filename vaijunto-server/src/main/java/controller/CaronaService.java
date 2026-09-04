package controller;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import model.Carona;
import model.Cidades;
import model.Grafo;


public class CaronaService {
    private final Map<String, Carona>  caronas;
    private final Grafo grafo;

    public CaronaService(Grafo mapa){
        this.caronas = new ConcurrentHashMap<>();
        this.grafo = mapa;
    }
    
    public String criarCarona(List<Cidades> rota, String data, String hora, 
                  int vagasTotais, boolean ativaOuNao, List<Double> precos, String motorista){
        Carona carona = new Carona(rota, data, hora, vagasTotais, ativaOuNao, precos, motorista);
        caronas.put(carona.getId(), carona);
        grafo.addTrechos(carona.getTrechos());
        return carona.getId();
    }

    public Carona buscaCaronaId(String id){
        if (id!=null){
            return null;
        }
        return caronas.get(id);
    }
}

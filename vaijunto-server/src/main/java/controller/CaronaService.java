package controller;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import model.Carona;
import model.Cidades;
import model.Grafo;
import model.Trecho;
import model.Usuario;
 


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

    public Carona buscaCaronaPorMotorista(String id, String motorista){
        if (id==null){
            return null;
        }
        Carona carona = caronas.get(id);
        if(carona!=null && carona.getNomeMotorista().equals(motorista)){
            return caronas.get(id);
        }
        return null;
    }

    public Map<String, List<String>> consultarPassageirosDaCarona(String idCarona, String loginMotorista) {
        Carona carona = caronas.get(idCarona);
        
        // Valida se a carona existe e pertence ao motorista que fez a requisição
        if (carona == null || !carona.getNomeMotorista().equals(loginMotorista)) {
            return null;
        }

        // Usa LinkedHashMap para manter a ordem cronológica dos trechos da rota
        Map<String, List<String>> mapaPassageiros = new LinkedHashMap<>();

        for (Trecho trecho : carona.getTrechos()) {
            String nomeTrecho = trecho.getCidadeOrigem().getNome() + " -> " + trecho.getCidadeDestino().getNome();
            
            List<String> listaLogins = new ArrayList<>();
            for (Usuario passageiro : trecho.getPassageiros()) {
                listaLogins.add(passageiro.getLogin());
            }
            
            mapaPassageiros.put(nomeTrecho, listaLogins);
        }

        return mapaPassageiros;
    }

    public boolean cancelarCarona(String token, String id){
        if (token == null||id==null) {
            return false;
        }
        Carona carona = caronas.get(id);
        if (carona!=null) {
            carona.setAtivaOuNao(false);
            grafo.removeTrechosDaCarona(id);
            caronas.remove(id);
            return true;
        }
        return false;
    }


}

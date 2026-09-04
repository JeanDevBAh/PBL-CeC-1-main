package controller;

import java.util.List;

import model.Cidades;
import model.Grafo;
import model.Itinerario;
import model.TipoUser;
import model.Usuario;

public class GegenciarGeral {
    private final CaronaService caronaService;
    private final ReservaService reservaService;
    private final UserService userService;
    private final Grafo grafo;
    
    public GegenciarGeral(){
        this.grafo = new Grafo();
        this.caronaService = new CaronaService(this.grafo);
        this.reservaService = new ReservaService(this.grafo);
        this.userService = new UserService();
    }
    // ==========================================
    // FLUXOS DE USUÁRIO (Encaminha para o UserService)
    // ==========================================
    public boolean cadastrarUsuario(String login, String senha, TipoUser tipo) {
        return userService.cadastrarUsuario(login, senha, tipo);
    }

    public String fazerLogin(String login, String senha) {
        return userService.fazerLogin(login, senha);
    }

    public boolean fazerLogout(String token) {
        return userService.logout(token);
    }

    // ==========================================
    // FLUXOS DE MOTORISTA (Encaminha para o CaronaService)
    // ==========================================
    public String oferecerCarona(String token, List<Cidades> rota, String data, String hora, 
                                int vagasTotais, boolean ativaOuNao, List<Double> precos) {
        // Opcional: Validar se o token pertence a um usuário válido antes de criar
        Usuario user = userService.validarSessao(token);
        if (user == null) {
            throw new SecurityException("Usuário não autenticado.");
        }
        
        return caronaService.criarCarona(rota, data, hora, vagasTotais, ativaOuNao, precos,  user.getLogin());
    }

    // ==========================================
    // FLUXOS DE PASSAGEIRO (Encaminha para o ReservaService)
    // ==========================================
    public List<Itinerario> buscarViagens(Cidades origem, Cidades destino, String data) {
        return reservaService.buscarViagens(origem, destino, data);
    }

    public String reservarViagem(String token, Itinerario itinerario) {
        Usuario passageiro = userService.validarSessao(token);
        if (passageiro == null) {
            throw new SecurityException("Usuário não autenticado.");
        }
        return reservaService.efetuarReserva(passageiro, itinerario);
    }

    public boolean cancelarReserva(String token, String idReserva) {
        Usuario passageiro = userService.validarSessao(token);
        if (passageiro == null) {
            return false;
        }
        return reservaService.cancelarReserva(idReserva, passageiro.getLogin());
    }

}

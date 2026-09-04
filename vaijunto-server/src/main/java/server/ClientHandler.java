package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import network.DTORequest;
import network.DTOResponse;
import network.JsonUtil;

public class ClientHandler implements Runnable {
    
    private Socket socketCliente;

    public ClientHandler(Socket socketCliente) {
        this.socketCliente = socketCliente;
    }

    @Override
    public void run() {
        try (
            BufferedReader reader = new BufferedReader(new InputStreamReader(socketCliente.getInputStream()));
            PrintWriter writer = new PrintWriter(socketCliente.getOutputStream(), true);
        ) {
            String mensagemRecebida;

            // Fica escutando enquanto o cliente estiver mandando mensagens na mesma conexão
            while ((mensagemRecebida = reader.readLine()) != null) {
                
                // 1. Transforma o JSON recebido da rede em um DTORequest Java
                DTORequest request = JsonUtil.paraObject(mensagemRecebida, DTORequest.class);
                
                // 2. Processa a requisição (aqui depois chamaremos seus services/controllers)
                DTOResponse response = processarRequisicao(request);

                // 3. Converte a resposta em JSON e envia de volta pelo Socket
                String jsonResposta = JsonUtil.paraJson(response);
                writer.println(jsonResposta);
            }

        } catch (IOException e) {
            System.out.println("Erro na conexão com o cliente: " + e.getMessage());
        } finally {
            try {
                socketCliente.close();
                System.out.println("Conexão encerrada com o cliente.");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Centraliza a lógica de decidir o que fazer com base na 'acao' enviada no DTO.
     */
    private DTOResponse processarRequisicao(DTORequest request) {
        if (request == null || request.getAcao() == null) {
            return new DTOResponse(false, "Requisição inválida ou sem ação definida.");
        }

        // Exemplo básico de roteamento de ações
        switch (request.getAcao().toUpperCase()) {
            case "PING":
                return new DTOResponse(true, "Pong! Servidor VaiJunto online.");
                
            case "BUSCAR_ROTAS":
                // Aqui você integrará com o seu Grafo / Service de Itinerários futuramente!
                return new DTOResponse(true, "Busca de rotas recebida para: " + request.getOrigem() + " -> " + request.getDestino());

            default:
                return new DTOResponse(false, "Ação desconhecida: " + request.getAcao());
        }
    }
}
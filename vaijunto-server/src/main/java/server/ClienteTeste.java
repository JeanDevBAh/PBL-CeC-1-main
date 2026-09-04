package server;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import network.DTORequest;
import network.JsonUtil;

public class ClienteTeste {
    public static void main(String[] args) {
        String enderecoServidor = "127.0.0.1"; // ou "localhost"
        int porta = 8080;

        System.out.println("[CLIENTE] Tentando conectar ao servidor em " + enderecoServidor + ":" + porta + "...");

        try (Socket socket = new Socket(enderecoServidor, porta);
             PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
            
            System.out.println("[CLIENTE] Conectado com sucesso!");

            // 1. Criando um DTORequest de teste com a ação PING
            DTORequest request = new DTORequest();
            request.setAcao("PING");

            // 2. Transformando o objeto em JSON usando o JsonUtil
            String jsonRequisicao = JsonUtil.paraJson(request);

            // 3. Enviando para o servidor
            System.out.println("[CLIENTE] Enviando requisição: " + jsonRequisicao);
            writer.println(jsonRequisicao);

            // 4. Aguardando a resposta do servidor
            String respostaJson = reader.readLine();
            System.out.println("[CLIENTE] Resposta recebida do servidor: " + respostaJson);

        } catch (Exception e) {
            System.out.println("[CLIENTE] Erro ao conectar com o servidor: " + e.getMessage());
            System.out.println("Dica: Certifique-se de que a classe ServidorTCP está rodando em outra aba do terminal!");
        }
    }
}
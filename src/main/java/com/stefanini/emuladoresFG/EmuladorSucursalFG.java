package com.stefanini.emuladoresFG;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class EmuladorSucursalFG {
	
    public static void main(String[] args) {
        int port = 12345; // Puerto en el que el servidor escuchará las conexiones
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Servidor escuchando en el puerto " + port);

            while (true) {
                try (Socket clientSocket = serverSocket.accept()) {
                    System.out.println("Cliente conectado: " + clientSocket.getInetAddress().getHostAddress());

                    // Crear un lector para recibir mensajes del cliente
                    BufferedReader input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                    // Crear un escritor para enviar mensajes al cliente
                    PrintWriter output = new PrintWriter(clientSocket.getOutputStream(), true);

                    String message;
                    while ((message = input.readLine()) != null) {
                        System.out.println("Mensaje recibido del cliente: " + message);
                        // Aquí puedes agregar lógica para procesar el mensaje

                        // Responder al cliente
                        output.println("Mensaje recibido: " + message);
                    }
                } catch (Exception e) {
                    System.err.println("Error en la comunicación con el cliente: " + e.getMessage());
                }
            }
        } catch (Exception e) {
            System.err.println("Error al iniciar el servidor: " + e.getMessage());
        }
    }
}
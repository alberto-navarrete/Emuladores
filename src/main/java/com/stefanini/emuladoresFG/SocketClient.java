package com.stefanini.emuladoresFG;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class SocketClient {
    public static void main(String[] args) {
        String host = "localhost"; // Dirección del servidor
        int port = 12345; // Puerto del servidor
        Scanner scanner = null;
        
        try (Socket socket = new Socket(host, port)) {
            System.out.println("Conectado al servidor");

            // Crear un lector para recibir mensajes del servidor
            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            // Crear un escritor para enviar mensajes al servidor
            PrintWriter output = new PrintWriter(socket.getOutputStream(), true);

            // Usar Scanner para leer mensajes desde la consola
            scanner = new Scanner(System.in);
            String message;
            System.out.println("Introduce un mensaje para enviar al servidor (escribe 'exit' para salir):");

            while (true) {
                message = scanner.nextLine();
                if ("exit".equalsIgnoreCase(message)) {
                    break;
                }

                // Enviar mensaje al servidor
                output.println(message);

                // Leer respuesta del servidor
                String response = input.readLine();
                System.out.println("Respuesta del servidor: " + response);
            }
        } catch (Exception e) {
            System.err.println("Error al conectar con el servidor: " + e.getMessage());
        } finally {
        	if(null!=scanner)
        		scanner.close();
		}
    }
}
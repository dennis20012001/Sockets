package socketString;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

	private static ServerSocket server;
	private static int port = 9876;

	public static void main(String args[]) throws IOException, ClassNotFoundException {
		
		server = new ServerSocket(port);
		
		while (true) {
			
			System.out.println("Esperando cliente");
			Socket socket = server.accept();
			ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
			
			if (socket.isConnected())
				System.out.println("Recibido");

			String message = (String) ois.readObject();
			System.out.println("Saludos desde el servidor al cliente no: " + message);
			ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
			oos.writeObject("Hola cliente " + message);
			
			ois.close();
			oos.close();
			socket.close();
			if (message.equalsIgnoreCase("exit"))
				break;
		}
		
		System.out.println("Demasiados clientes por hoy");
		server.close();
	}
}

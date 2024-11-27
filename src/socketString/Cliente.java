package socketString;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class Cliente {

	public static void main(String[] args)
			throws UnknownHostException, IOException, ClassNotFoundException, InterruptedException {

		InetAddress host = InetAddress.getLocalHost();
		Socket socket = null;
		ObjectOutputStream oos = null;
		ObjectInputStream ois = null;

		for (int i = 1; i < 4; i++) {

			socket = new Socket(host.getHostName(), 9876);

			if (socket.isConnected()) {
				System.out.println("Conexión realizada con el servidor");
			}
			oos = new ObjectOutputStream(socket.getOutputStream());

			System.out.println("Hola servidor, soy un cliente");

			if (i == 4) {
				oos.writeObject("exit");}
			else
				oos.writeObject("" + i);

			ois = new ObjectInputStream(socket.getInputStream());

			String message = (String) ois.readObject();
			System.out.println("Cliente: " + message);

			ois.close();
			oos.close();
			socket.close();
			Thread.sleep(100);
		}
	}
}
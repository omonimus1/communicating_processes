import java.io.*;
import java.net.*;

public class Client 
{
	private static Socket sock;

	public static void main(String[] args)
	{
		try
		{
			// First create the input from keyboard
			BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
			System.out.println("Client Program");
			
			sock = new Socket("146.176.165.159", 5000);
			// Create the incoming stream to read messages from
			DataInputStream network = new DataInputStream(sock.getInputStream());

			// Create the outcomign stream to write message to
			DataOutputStream onetwork = new DataOutputStream(sock.getOutputStream());

			// Display our address
			System.out.println("Address: " + sock.getInetAddress());
			String line;
			
			
			// ask here for file name to search withi server
			System.out.println("file name to search: ");
			String file_name = input.readLine();
			onetwork.writeUTF(file_name);
			// send filename to server 
			
			// Loop until the connection closes, reading from the network
			while ((line = network.readUTF()) != null)
			{
				// Display the received message
				System.out.println(line);
			}
		}
		catch (IOException ioe)
		{
			// This is expected when the server closes the network connection
			System.err.println("Error in I/O");
			System.err.println(ioe.getMessage());
			System.exit(-1);
		}
	}
}

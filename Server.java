import java.io.*;
import java.net.*;
import java.util.*;

public class Server 
{
	public static void main(String[] args)
	{
		try
		{
			// First create the input from the keyboard
			BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
			System.out.println("Server Program");
			
			ServerSocket server = new ServerSocket(5000);
			
			// Accept an incoming client connection on the server socket
			Socket sock = server.accept();
			
			// Create the output stream to the client
			DataOutputStream network = new DataOutputStream(sock.getOutputStream());

			// Create the input stream from the client
			DataInputStream inetowork = new DataInputStream(sock.getInputStream());
			
			// Send message
			network.writeUTF("Welcome " + sock.getInetAddress().getHostName() + ". We are " + new Date() + "\n");

			// Default directory where the file will be searched
			String directory = "H:/communicating_processes/";
			String file_name = inetowork.readUTF(); 

			File f = new File(directory+file_name);
			// Check if file exists and eventually print its contnent
			if(f.exists() && !f.isDirectory())
			{
				Scanner file_content = new Scanner(new File(file_name));
				while(file_content.hasNextLine()){
					network.writeUTF(file_content.nextLine());
				}
			}
			else{
				network.writeUTF("file not found");
			}


			// Close sockets.  This will cause the client to exit
			sock.close();
			server.close();
		}
		catch (IOException ioe)
		{
			System.err.println("Error in I/O");
			System.err.println(ioe.getMessage());
			System.exit(-1);
		}
	}
}

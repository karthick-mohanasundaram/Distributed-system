package clientpgm;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * A simple class that opens a server socket, prints each message received
 * from console and also send the received message back to client
 */
public class TCPSocketServer
{

	/**
	 * Accept maximum of 5 connection request from clients.
	 */
	private int my_backlog = 5;    
   
   
	/**
	   * Creation of server object.
	   */   
	private ServerSocket my_serverSocket;
   
   

   public TCPSocketServer(int a_port) 
   {
      try 
      {
    	 
    	 /**
    	  * creates server socket on a_port with a maximum of queue length of my_backlog 
    	  */
         my_serverSocket = new ServerSocket(a_port, my_backlog);
         System.out.println("TCP socket listening on port " + a_port);
      } 
      catch (IOException ioe) 
      {
         ioe.printStackTrace();
      } 
      catch (SecurityException se) 
      {
         se.printStackTrace();
      }
   }

   /**
    * Method that listens on the server socket forever and prints each incoming
    * message to the console.
    */
   public void listen() 
   {
      while (true) 
      {
         try 
         {
            // Listens for a connection to be made to this socket.
            Socket socket = my_serverSocket.accept();

            // get input and output streams for read/write and wrap them 
            BufferedReader in = new BufferedReader(new InputStreamReader(socket
                  .getInputStream()));
              
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);


            // Read in the message
            String msg = in.readLine();
            

            // Print the message to the console
            System.out.println("Recceived message: " + msg);
            
            //sending the received message back to client 
            out.println("your message was:" + msg);


            in.close();
            socket.close();
         }//end of try block
         
         catch (IOException ioe) 
         {
            ioe.printStackTrace();
         } 
         catch (SecurityException se) 
         {
            se.printStackTrace();
         }
      }//end of while block
   }//end of listen method

   /**
    * Parse the arguments to the program and create the server socket.
    * @param args the program arguments. Should take the form &lt;port&gt;
    */
   public static void main(String[] args) 
   {
      int port = 0;

      // Checking the right number of arguments and parse
      if (args.length == 1) 
      {
         try 
         {
            port = Integer.valueOf(args[0]);
         } 
         catch (NumberFormatException nfe) 
         {
            System.err.println("The value provided for the port is not an integer");
            nfe.printStackTrace();
         }
         // Create the server
      TCPSocketServer server = new TCPSocketServer(port);
         // calling listen method
      server.listen();
      } 
      else 
      {
         System.out.println("Usage: java TCPSocketServer <port>");
      }

   }
}


package clientserverp1_5;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * A simple class that opens a server socket, that receives 
 * 2 strings from client, converts string type into float type
 * and divides one another
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
    * Method that listens on the server socket
    */
   public void listen() 
   {
      while (true) 
      {
         try 
         {
            // Listens for a connection to be made to this socket.
            Socket socket = my_serverSocket.accept();

            //Get input and output streams for read/write and wrap them
            BufferedReader in = new BufferedReader(new InputStreamReader(socket
                  .getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
              
            // Read in the message and converts string to float type
            Float num1 = Float.parseFloat(in.readLine());
            Float num2 = Float.parseFloat(in.readLine());
            Float value=num1/num2;
            
            //Server sending result to client
            out.println("The result of " + num1 + "/ " + num2 + "is " + value);         		

                                   
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
}//end of class



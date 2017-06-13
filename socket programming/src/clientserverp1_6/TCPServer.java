package clientserverp1_6;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * A simple class that opens a server socket, that receives 
 * maximum of 100 connection request to divide two random numbers 
 * and sends the result to client
 */
public class TCPServer
{

	/**
	 * Accept maximum of 100 connection request from clients.
	 */
   private int my_backlog = 100;    
   
   /**
    * Creation of server object.
    */
   private ServerSocket my_serverSocket;
   
   
   public TCPServer(int a_port) 
   {
      try 
      {
    	 
    	 /**
    	  * creates server socket on a_port with a maximum of queue length of my_backlog 
    	  */
    	 my_serverSocket = new ServerSocket(a_port, my_backlog);
         System.out.println("TCP server socket listening on port " + a_port);
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

                         
            //Receiving binary data
            DataInputStream is = 
                    new DataInputStream(socket.getInputStream());
            Float n1 = is.readFloat();
            Float n2 = is.readFloat();
            Float value=n1/n2;
         		

            //sending result as binary data to client
            DataOutputStream os =
        		    new DataOutputStream(socket.getOutputStream()); 
        		os.writeFloat(value);
                                   
                        
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
         TCPServer server = new TCPServer(port);
         // calling listen method
         server.listen();
      } 
      else 
      {
         System.out.println("Usage: java TCPSocketServer <port>");
      }

   }
}



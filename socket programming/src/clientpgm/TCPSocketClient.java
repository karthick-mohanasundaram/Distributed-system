package clientpgm;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * A simple class that opens a socket, sends a message to the server,
 * print the reply message sent by server on terminates.
 */
public class TCPSocketClient 
{

   /**
    * The server host name.
    */
   public String my_serverHost;

   /**
    * The server port.
    */
   public int my_serverPort;

   /**
    * Sets the client up with the server details.
    * @param the_serverHost, the server host name.
    * @param the_serverPort, the server port.
    */
   public TCPSocketClient(String the_serverHost, int the_serverPort) 
   {
      my_serverHost = the_serverHost;
      my_serverPort = the_serverPort;
   }

   /**
    * Creates a connection to the server and sends a message.
    * @param a_message, the message to send to the server.
    */
   public void sendMessage(String a_message) 
   {
      try 
      {
         // Create a socket connecting to the my_serverHost and my_serverPort.
         Socket toServer = new Socket(my_serverHost, my_serverPort);

         // Get input and output streams for read/write.
         InputStream is = toServer.getInputStream();
         BufferedReader in = 
        	        new BufferedReader(new InputStreamReader(is));

         PrintWriter out = new PrintWriter(toServer.getOutputStream(), true);

         // Write the message to the socket.
         out.println(a_message);
         
         //print out response from server
         String line = null;
         while ((line = in.readLine()) != null) 
         {
             System.out.println(line);
         }

         out.close();
         toServer.close();
      } 
      catch (IOException ioe) {
         ioe.printStackTrace();
      } catch (SecurityException se) {
         se.printStackTrace();
      }
   }

   /**
    * Parse the arguments to the program, create a socket, and send a message.
    * @param args the program arguments. Should take the form &lt;host&gt;
    *           &lt;port&gt; &lt;message&gt;
    */
   public static void main(String[] args) 
   {

      String host = null;
      int port = 0;
      String message = null;

      // Check we have the right number of arguments and parse
      if (args.length == 3)
      {
         host = args[0];
         try 
         {
            port = Integer.valueOf(args[1]);
         } 
         catch (NumberFormatException nfe) 
         {
            System.err.println("The value provided for the port is not an integer");
            nfe.printStackTrace();
         }
         message = args[2];

         // Create the client
         TCPSocketClient client = new TCPSocketClient(host, port);
         // Send a message using the client
         client.sendMessage(message);
      } 
      else 
      {
         System.out.println("Usage: java TCPSocketClient <host> <port> <message>");
      }

   }
} // end class



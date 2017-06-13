package clientserverp1_5;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * A simple class that opens a socket and sends 2 numbers to server for division
 */
public class TCPSocketClient 
{

   
   // The server host name.
   public String my_serverHost;

   
   // The server port.
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
    * Creates a connection to the server and sends parameter.
    * @param num1, the string1 to send to the server.
    * @param num2, the string2 to send to the server.
    */
   public void sendMessage(String num1, String num2) 
   {
      try 
      {
         // Create a socket connecting to the my_serverHost and my_serverPort.
         Socket toServer = new Socket(my_serverHost, my_serverPort);

         InputStream is = toServer.getInputStream();
         BufferedReader in = 
        	        new BufferedReader(new InputStreamReader(is));
         PrintWriter out = new PrintWriter(toServer.getOutputStream(), true);
         
         //sending numeric strings to server
         out.println(num1);
         out.println(num2);
         
         System.out.println("CLIENT MACHINE");
         
         //print out response from server
         String line = null;
         while ((line = in.readLine()) != null) 
         {
             System.out.println(line);
         }
         
         // close the opened connection
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
      int port=0;
      String s1=null,s2=null;
      
      // Check we have the right number of arguments and parse
      if (args.length == 4)
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
         s1 = args[2];
         s2 = args[3];

         // Create the client
         TCPSocketClient client = new TCPSocketClient(host, port);
         client.sendMessage(s1,s2);
       } 
      else 
      {
         System.out.println("Usage: java TCPSocketClient <host> <port> <message>");
      }

   }
} // end class



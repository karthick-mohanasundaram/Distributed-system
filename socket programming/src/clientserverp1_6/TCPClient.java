package clientserverp1_6;
import java.io.IOException;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.Random;

/**
 * A simple class that opens a socket and sends specified no. of request (up to 100) 
 * to server for dividing two random numbers and server sends result back to client
 */
public class TCPClient 
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
   public TCPClient(String the_serverHost, int the_serverPort) 
   {
      my_serverHost = the_serverHost;
      my_serverPort = the_serverPort;
   }

   public void sendMessage(String num1, String num2) 
   {
      try 
      {
         // Create a socket connecting to the my_serverHost and my_serverPort.
         Socket toServer = new Socket(my_serverHost, my_serverPort);
         Float n1 = Float.parseFloat(num1);
         Float n2 = Float.parseFloat(num2);
         
         
         //sending binary data to server
         DataOutputStream os = new DataOutputStream(toServer.getOutputStream());
         os.writeFloat(n1);
         os.writeFloat(n2);
        		
         //receiving binary data from server
         DataInputStream is = new DataInputStream(toServer.getInputStream());
         Float val = is.readFloat();
         System.out.print("The result of " + n1 + "/ " + n2 + "is " + val);
         System.out.println();
               
      } 
      catch (IOException ioe) //exception handling
      {
         ioe.printStackTrace();
      } 
      catch (SecurityException se) //exception handling
      {
         se.printStackTrace();
      }
   }

   public static void main(String[] args)throws IOException 
   {

      String host = null;
      int port=0,max=0;
      
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
         max = Integer.valueOf(args[2]);
         System.out.println("CLIENT MACHINE");
         
         // Create the client
         TCPClient client = new TCPClient(host, port);
         Random random=new Random(); //random object provides a simple random number generator
         
         /**
          * max has value which is sent by client on command line,
          * indicate no. of connection request to be made from client to server
          */
         for (int i=0; i<max; i++)
         {
        		
        	client.sendMessage(String.valueOf(random.nextInt(1000)),String.valueOf(random.nextInt(1000)));
         }
       } 
      else 
      {
         System.out.println("Usage: java TCPSocketClient <host> <port> <message>");
      }

   }


} // end class




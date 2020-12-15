import java.net.InetAddress;

import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TThreadPoolServer;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TServerTransport;

public class Server {

	public static FileAddressHandler addressHandler;
	public static FileStore.Processor<FileStore.Iface> fileProcessor;
	public static int port;
	public static String ip;

	public static void main(String[] args) {

		try {
			port = Integer.parseInt(args[0]);
			ip = InetAddress.getLocalHost().getHostAddress();
		} catch (Exception e) {
			System.err.println("Error while initiating server" + e.getMessage());
			System.exit(0);
		}

		addressHandler = new FileAddressHandler(ip, port);
		fileProcessor = new FileStore.Processor<FileStore.Iface>(addressHandler);
		
		Thread centralServerInstance = new Thread(new Server().new CentralServer(fileProcessor)); 
		centralServerInstance.start(); 
	}
	
	 private class CentralServer implements Runnable { 
		  
		 FileStore.Processor<FileStore.Iface> processor;
		 
		 	public CentralServer(FileStore.Processor<FileStore.Iface> processor){
		 		this.processor = processor;
		 	}
		 	
	        public void run() 
	        { 
	        	try {
	    			TServerTransport serverTransport = new TServerSocket(port);

	    			TServer server = new TThreadPoolServer(new TThreadPoolServer.Args(serverTransport).processor(processor));
	    			System.out.println("************Server Information****************");
	    			System.out.println("Hash of Server NodeID: " + addressHandler.activeNode.getId());
	    			System.out.println("Server IP: " + addressHandler.ip);
	    			System.out.println("nServer Port: " + addressHandler.port);
	    			System.out.println("**********************************************");
	    			server.serve();

	    		} catch (Exception e) {
	    			System.err.println("Error in opening TTransport socket: " + e.getMessage());
	    			System.exit(0);
	    		}
	        }

	    } 
}
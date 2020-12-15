import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.MessageDigest;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;

public class Client {

	public static void main(String[] args) {
		
		if (args.length != 2) {
		      System.out.println("Incorrect number of arguments. Please enter [ip] [port]");
		      System.exit(0);
		}
		
	    try {
	      TTransport transport;
	   
	      transport = new TSocket(args[0], Integer.valueOf(args[1]));
	      transport.open();	     

	      TProtocol protocol = new  TBinaryProtocol(transport);
	      FileStore.Client client = new FileStore.Client(protocol);
	      
	      //String fileN = "example.txt";
	      String fileN = "example.txt";
	      String fileId = getHashWithSHA256(fileN);
	      
	      NodeID serverOwner = client.findSucc(fileId);
	      System.out.println("Server ID which owns given file is -> " + serverOwner.port);
	      System.out.println("");
	      
	      /*
	      System.out.println("Teting writing to server..");
		  writeToServer(client, fileN);
		  
		  System.out.println("Teting reading from server..");
		  readFromServer(client, fileN);
		  */
	    }
	    catch (TException e) {
			System.err.println("Error: " + e.getMessage());
			System.exit(0);
		}
	       
	}
	
	public static String getHashWithSHA256(String key) {
		StringBuilder result = new StringBuilder();
		try {
			
			MessageDigest hashVal = MessageDigest.getInstance("SHA-256");
			hashVal.update(key.getBytes());
			byte[] data = hashVal.digest();
			
			for(byte d : data){
				result.append(String.format("%02x", d));
			}
		
			return result.toString();
		} catch (Exception e) {
			System.err.println("Error while calculating SHA-256:" + e.getMessage());
			System.exit(0);
		}
		
		return result.toString();
	}

	/*
	private static void writeToServer(FileStore.Client client, String filename) throws TException {

		System.out.println("Writing the File: "+ filename);
		RFile rFile = new RFile();
		RFileMetadata metadata = new RFileMetadata();
		metadata.setFilename(filename);
		metadata.setFilenameIsSet(true);

		String key = getHashWithSHA256(metadata.getFilename());
		
		rFile.setMeta(metadata);
		String content = null;
		try {
			byte[] byteContent = Files.readAllBytes(Paths.get(filename));
			content = new String(byteContent);
			rFile.setContent(content);
			rFile.setContentIsSet(true);

			NodeID destNode = client.findSucc(key);
			TTransport transport = new TSocket(destNode.getIp(), destNode.getPort());
			transport.open();
			TProtocol protocol = new TBinaryProtocol(transport);
			FileStore.Client writerClient = new FileStore.Client(protocol);

			writerClient.writeFile(rFile);

		} catch (Exception e) {
			System.err.println("Exception occured: " + e.getMessage());
			System.exit(0);
		} 
		System.out.println("TestCase : Passed\n");

	}
	
	private static void readFromServer(FileStore.Client client, String filename) {
		System.out.println("Reading the File: "+ filename);
		String key =getHashWithSHA256(filename);
		
		try {
			NodeID destNode = client.findSucc(key);
			TTransport transport = new TSocket(destNode.getIp(), destNode.getPort());
			transport.open();
			TProtocol protocol = new TBinaryProtocol(transport);
			FileStore.Client readerClient = new FileStore.Client(protocol);

			RFile rfile = readerClient.readFile(filename);
			System.out.println("File read successfully. Content:");
			System.out.println(rfile.getContent());
			System.out.println("Filename: " + rfile.getMeta().getFilename());
			System.out.println("Version: " + rfile.getMeta().getVersion());

		}catch (Exception e) {
			System.err.println("SystemException occured: " + e.getMessage());
		}
	}
	*/

}
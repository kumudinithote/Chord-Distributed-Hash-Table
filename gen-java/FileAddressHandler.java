import java.io.File;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.util.HashMap;
import java.util.List;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;

public class FileAddressHandler implements FileStore.Iface {
	
	private static final String VERSION = "Version";
	private static final String FILE_NAME = "Filename";

	public int port;
	public String ip;
	public NodeID activeNode;
	private HashMap<String, HashMap<String, String>> fileMataData;
	private List<NodeID> fingerTable;

	public FileAddressHandler(String ip, int port) {
		this.port = port;
		this.ip = ip;
		activeNode = new NodeID(getHashWithSHA256(ip + ":" + port), ip, port);
		fileMataData = new HashMap<String, HashMap<String, String>>();
	}

	public String getHashWithSHA256(String key) {
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
	
	@Override
	public void writeFile(RFile rFile) throws SystemException, TException {
		NodeID node = findSucc(getHashWithSHA256(rFile.getMeta().filename));
		
		if(node.equals(activeNode)){
			try {
				String fName = rFile.getMeta().getFilename();
				
				if (fileMataData.containsKey(fName)) {
					HashMap<String, String> value = fileMataData.get(fName);
					Integer version = Integer.parseInt(value.get(VERSION)) + 1;
					value.put(VERSION, version.toString());
				} else {
					HashMap<String, String> metadata = new HashMap<String, String>();
					metadata.put(VERSION, "0");
					metadata.put(FILE_NAME, fName);
				
					fileMataData.put(fName, metadata);
				}

				File file = new File(fName);
			    file.createNewFile();
			    FileWriter writer = new FileWriter(file); 
			      
			    writer.write(rFile.getContent()); 
			    writer.flush();
			    writer.close();
			      
				System.out.println("Done writing file on this server.");
			} catch (Exception e) {
				System.err.println("Error occured while writing file to server");
			}
		}else{
			SystemException exc = new SystemException();
			exc.setMessage("WriteFile Error: Server does not own the File.");
			throw exc;
		}
		
	}

	@Override
	public RFile readFile(String filename) throws SystemException, TException {
		NodeID node = findSucc(getHashWithSHA256(filename));
		
		if(node.equals(activeNode)){
			RFile rfile = null;
			try {
				File file = new File(filename);
				if (fileMataData.containsKey(file.getName())) {
					
					HashMap<String, String> metadata = fileMataData.get(file.getName());
					RFileMetadata rfileMetadata = new RFileMetadata();

					rfileMetadata.setFilename(file.getName());
					rfileMetadata.setFilenameIsSet(true);
					
					rfileMetadata.setVersion(Integer.parseInt(metadata.get(VERSION)));
					rfileMetadata.setVersionIsSet(true);

					rfile = new RFile();
					rfile.setMeta(rfileMetadata);
					rfile.setMetaIsSet(true);

					byte[] byteContent = Files.readAllBytes(Paths.get(filename));
					String content = new String(byteContent);
					System.out.println("Content of the file is : "+content);
					rfile.setContent(content);
					rfile.setContentIsSet(true);
				}else{
					SystemException exc = new SystemException();
					exc.setMessage("Read Error: File is not present on the server!");
					throw exc;
				}
			} catch (Exception e) {
				System.err.println("Exception occured:" + e.getMessage());
			}
			return rfile;

		}else{
			SystemException exc = new SystemException();
			exc.setMessage("ReadFile Error: Server does not own the File.");
			throw exc;
		}
	}

	@Override
	public void setFingertable(List<NodeID> nodeList) throws TException {
		this.fingerTable = nodeList;
	}

	@Override
	public NodeID findSucc(String key) throws SystemException, TException {
		
		String value = getHashWithSHA256(activeNode.port+ ":" + activeNode.port);
		String nodeId = getHashWithSHA256(value);
		
		if(nodeId.compareTo(key) == 0) {
			return activeNode;
		}
		else{
			NodeID tempNode = findPred(key);
			FileStore.Client client = null;
			try {
				TTransport ttransport = new TSocket(tempNode.getIp(), tempNode.getPort());
				ttransport.open();
				TProtocol protocol = new TBinaryProtocol(ttransport);
				client = new FileStore.Client(protocol);
			} catch (Exception e) {
				SystemException exception = new SystemException();
				exception.setMessage("Exception occured:" + e.getMessage());
				System.exit(0);
			}
			return client.getNodeSucc();
		}	
	}

	private boolean isInBetween(String key, String id1, String id2) {
		if (((key.compareTo(id1) > 0) && (key.compareTo(id2) < 0)) ||
				(id1.compareTo(id2) > 0) && ((key.compareTo(id1) < 0) && (key.compareTo(id2) < 0)))
			return true;
		
		return false;
	}
	
	@Override
	public NodeID findPred(String key) throws SystemException, TException {
		if(fingerTable != null){
			boolean inBet = isInBetween(key, activeNode.getId(), fingerTable.get(0).getId());
			while (!inBet) {
				for (int i = fingerTable.size() - 1; i > 0; i--) {
					NodeID tempNode = fingerTable.get(i);
					if (isInBetween(tempNode.getId(), activeNode.getId(), key)) {
						
						try {
							TTransport ttransport = new TSocket(tempNode.getIp(), tempNode.getPort());
							ttransport.open();

							TProtocol protocol = new TBinaryProtocol(ttransport);
							FileStore.Client client = new FileStore.Client(protocol);
							return client.findPred(key);

						} catch (Exception e) {
							System.err.println("Exception occured:" + e.getMessage());
							System.exit(0);
						}
						return tempNode;
					}
				}
				break;
			}
			return activeNode;
		}
		else{
			SystemException exception = new SystemException();
			exception.setMessage("Error: FingerTable is not initialized.");
			throw exception;
		}
	}

	@Override
	public NodeID getNodeSucc()  throws TException{
		if (fingerTable != null) {
			return fingerTable.get(0);
		}
		else{
			SystemException exception = new SystemException();
			exception.setMessage("Error: FingerTable is not initialized.");
			throw exception;
		}
	}

}
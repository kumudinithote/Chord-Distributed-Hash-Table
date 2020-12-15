# Chord-Distributed-Hash-Table
Implemented Chord Distributed Hash Table

Programming is done in Java.

How to run the code:

1. git clone <git_path>
2. cd go to the src folder/
3. Compile code : make
4. start server instances : ./server.sh 9000 and other ports (note IP address and port)
5. update node.txt with IP address and ports captured in above step
6. Initialize fingertable ./init node.txt
7. Start client : ./client.sh <IP address> <port #>


Brief:

Server.java is Server class which will start the server on the given port, where each request is serve on the different thread.
FileAddressHandler.java implements the interface which is given in the thrift library. This provides the implementation of writeFile, readFile, setFingertable, findSucc, findPred, getNodeSucc server methods.
Throws the SystemException in case the unauthorized file access request comes.
Client.java is implemented for testing the Distributed Hash Table. Test case to check for Write and Read File on the correct port. Given a file name it will tell on which server the file should rely.


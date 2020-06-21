/**********************************************/

Instructions to run the Program.

/**********************************************/

There are two ways of running the program. Using the ChatRoom and Using Clients

Using ChatRoom:

1. We are considering 20 users in the client_name.txt. One name for each User
2. We are creating a Server that accepts 20 users
3. ChatRoom program selects a random message from the text.txt and sends it as a message in the server window

Steps to run the Program

i. open 2 command prompts in the directory of the zip folder extract
ii. In the first terminal, run command javac Server.java to compile the java file and create the class file for the server.
iii. run command java Server to run the server. 
iv. In the second terminal, run command javac ChatRoom.java to compile the java file and create the class file for the Chat Room.
v. run command java ChatRoom to run the ChatRoom

we can see the 20 users getting connected to the server. After all the users are connected, we can see the messages being sent by the users.
Every message is being taken randomly from the txt.txt

Using Client:

change NUM_USERS to 1 (if we are creating only 1 client)
We can run the program with multiple Clients also. Just change the value of NUM_USERS 

i. open 2 command prompts in the directory of the zip folder extract
ii. In the first terminal, run command javac Server.java to compile the java file and create the class file for the server.
iii. run command java Server to run the server. 
iv. In the second terminal, run command javac Client.java to compile the java file and create the class file for the Client.
v. run command java Client to run the Client

We can type the message at the Client and the message will be sent to the server. The message will be taken from System.in



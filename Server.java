import java.io.BufferedInputStream;
import java.io.DataOutputStream;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

class Pair{
	public int priority;
	public Message msg;
	Pair(int p, Message msg){
		this.priority = p;
		this.msg = msg;
	}
}

class PairComparator implements Comparator<Pair>{ 
    
    public int compare(Pair p1, Pair p2) { 
        if (p1.priority < p2.priority) 
            return 1; 
        else  
           return 0; 
    } 
}

public class Server {
	
	private static String privateKey = "MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBAKAUZV+tjiNBKhlBZbKBnzeugpdYPhh5PbHanjV0aQ+LF7vetPYhbTiCVqA3a+Chmge44+prlqd3qQCYra6OYIe7oPVq4mETa1c/7IuSlKJgxC5wMqYKxYydb1eULkrs5IvvtNddx+9O/JlyM5sTPosgFHOzr4WqkVtQ71IkR+HrAgMBAAECgYAkQLo8kteP0GAyXAcmCAkA2Tql/8wASuTX9ITD4lsws/VqDKO64hMUKyBnJGX/91kkypCDNF5oCsdxZSJgV8owViYWZPnbvEcNqLtqgs7nj1UHuX9S5yYIPGN/mHL6OJJ7sosOd6rqdpg6JRRkAKUV+tmN/7Gh0+GFXM+ug6mgwQJBAO9/+CWpCAVoGxCA+YsTMb82fTOmGYMkZOAfQsvIV2v6DC8eJrSa+c0yCOTa3tirlCkhBfB08f8U2iEPS+Gu3bECQQCrG7O0gYmFL2RX1O+37ovyyHTbst4s4xbLW4jLzbSoimL235lCdIC+fllEEP96wPAiqo6dzmdH8KsGmVozsVRbAkB0ME8AZjp/9Pt8TDXD5LHzo8mlruUdnCBcIo5TMoRG2+3hRe1dHPonNCjgbdZCoyqjsWOiPfnQ2Brigvs7J4xhAkBGRiZUKC92x7QKbqXVgN9xYuq7oIanIM0nz/wq190uq0dh5Qtow7hshC/dSK3kmIEHe8z++tpoLWvQVgM538apAkBoSNfaTkDZhFavuiVl6L8cWCoDcJBItip8wKQhXwHp0O3HLg10OEd14M58ooNfpgt+8D8/8/2OOFaR0HzA+2Dm";
	
	static int NUM_USERS = 20;
	public static void main(String[] args) throws Exception{
		System.out.println("Server is waiting for the connections to join!");
		ServerSocket ss = new ServerSocket(8011);
		ArrayList<Socket> clients = new ArrayList<>();
		int num = 0;
		while(num < NUM_USERS) {
			try {
				Socket s = ss.accept();
				System.out.println("User " + (num+1) + " Connected!");
				clients.add(s);
				num++;
			}catch(Exception e) {
				System.out.println(e);
			}
		}
		DataOutputStream dos = null;
		ObjectInputStream in = null;
		int counter = 0;
		while(true) {
			int id = counter % NUM_USERS;
			counter++;
			try{
				dos = new DataOutputStream(clients.get(id).getOutputStream());
				in = new ObjectInputStream(new BufferedInputStream(clients.get(id).getInputStream()));
				
				Message msg = (Message) in.readObject();
				String decryptedMessage = RSAUtil.decrypt(msg.message, privateKey);
				System.out.println(msg.name + " says : " + decryptedMessage);
				dos.writeUTF("ACK");
				
			} catch(Exception e) {
				dos.writeUTF("NOACK");
			}
			
		}
		
	}
}

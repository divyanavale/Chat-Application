import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Scanner;

public class ChatRoom {
	
	private static String publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCgFGVfrY4jQSoZQWWygZ83roKXWD4YeT2x2p41dGkPixe73rT2IW04glagN2vgoZoHuOPqa5and6kAmK2ujmCHu6D1auJhE2tXP+yLkpSiYMQucDKmCsWMnW9XlC5K7OSL77TXXcfvTvyZcjObEz6LIBRzs6+FqpFbUO9SJEfh6wIDAQAB"; 
	
	public static void main(String[] args) throws Exception {
		ArrayList<User> users = new ArrayList<>();
		FileInputStream fis=new FileInputStream("client_name.txt");
		Scanner nameScanner = new Scanner(fis);
		while(nameScanner.hasNextLine()) {
			User user = new User();
			user.setName(nameScanner.nextLine());
			user.setSocket(new Socket("127.0.0.1", 8189));
			users.add(user);
			Thread.sleep(500);
		}
		nameScanner.close();
		
		
		while(true) {
			for(User user : users) {
				int num = 1 + (int)(Math.random() * ((25 - 1) + 1));
				String msg = getRandomMessage(num);
				ObjectOutputStream out = new ObjectOutputStream(user.socket.getOutputStream());
				String encryptedMessage = Base64.getEncoder().encodeToString(RSAUtil.encrypt(msg, publicKey));
				out.writeObject(new Message(user.name, encryptedMessage, 1 + (int)(Math.random() * ((20 - 1) + 1))));
				DataInputStream in = new DataInputStream(new BufferedInputStream(user.socket.getInputStream()));
				String data2 = in.readUTF();
				System.out.println(data2);
				Thread.sleep(500);
			}
		}
		
	}

	public static String getRandomMessage(int line) throws FileNotFoundException{
		String res = "";
		try {
		      FileReader readfile = new FileReader("text.txt");
		      @SuppressWarnings("resource")
			BufferedReader readbuffer = new BufferedReader(readfile);
		      for (int lineNumber = 1; lineNumber < 25; lineNumber++) {
		        if (lineNumber == line) {
		          res = readbuffer.readLine();
		        } else
		          readbuffer.readLine();
		      }
		    } catch (Exception e) {
		      e.printStackTrace();
		    }
			
			return res;
    }
}

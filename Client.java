import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Base64;
import java.util.Scanner;

public class Client {

	private static String publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCgFGVfrY4jQSoZQWWygZ83roKXWD4YeT2x2p41dGkPixe73rT2IW04glagN2vgoZoHuOPqa5and6kAmK2ujmCHu6D1auJhE2tXP+yLkpSiYMQucDKmCsWMnW9XlC5K7OSL77TXXcfvTvyZcjObEz6LIBRzs6+FqpFbUO9SJEfh6wIDAQAB"; 
	
	public static void main(String[] args) throws Exception{
		String name;
		System.out.println("Please Enter your name : ");
		Scanner kb = new Scanner(System.in);
		name = kb.nextLine();
		Socket s = new Socket("127.0.0.1", 8011);
		System.out.println("Connected!");
		DataInputStream dis = new DataInputStream(new BufferedInputStream(s.getInputStream()));
		int count = 0;
		while(true) {
			String data = dis.readUTF();
			System.out.println(data);
			ObjectOutputStream out = new ObjectOutputStream(s.getOutputStream());
			String encryptedMessage = Base64.getEncoder().encodeToString(RSAUtil.encrypt(kb.nextLine(), publicKey));
			System.out.println(encryptedMessage);
			out.writeObject(new Message(name, encryptedMessage));
			System.out.println("Data sent Successfully!");
			DataInputStream in = new DataInputsStream(new BufferedInputStream(s.getInputStream()));
			String data2 = in.readUTF();
			System.out.println(data2);
			count++;
			if(count == 20) break;
		}
		kb.close();
		s.close();
	}
}
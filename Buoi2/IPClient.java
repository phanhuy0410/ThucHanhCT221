import java.io.*;
import java.net.*;
import java.util.Scanner;

public class IPClient {

	public static void main(String[] args) {
		try {
			Scanner kb = new Scanner(System.in);
			System.out.println("Nhap dia chi server: ");
			String dcServer = kb.nextLine();
			Socket s = new Socket(dcServer,5000);
			InputStream is = s.getInputStream();
			OutputStream os = s.getOutputStream();
			Scanner sc = new Scanner(is);
			PrintStream ps = new PrintStream(os);
			while(true) {
				System.out.println("Nhap dia chi IP: ");
				String ip = kb.nextLine();
				if(ip.equals("exit")) break;
				ps.println(ip);
				String kq = sc.nextLine();
				System.out.println("Ket qua: " + kq);
			}
			s.close();
		}
		catch(Exception e) {
			System.out.println(e);
		}
	}

}

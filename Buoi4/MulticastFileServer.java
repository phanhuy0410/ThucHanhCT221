import java.io.*;
import java.net.*;
import java.util.*;

public class MulticastFileServer {

	public static void main(String[] args) {
		try {
			Scanner kb = new Scanner(System.in);
			System.out.println("Nhap ten file can gui: ");
			String filename = kb.nextLine();
			File f = new File(filename);
			if (!f.exists() || !f.isFile()) {
				System.out.println("File khong ton tai hoac khong hop le!");
				return;
			}
			MulticastSocket ms = new MulticastSocket();
			InetAddress dc = InetAddress.getByName("231.2.3.4");
			while (true) {
				try {
					FileInputStream fis = new FileInputStream(f);
					byte b[] = new byte[1000];
					int byteRead;
					System.out.println("Dang gui file: " + filename);
					while ((byteRead = fis.read(b)) != -1) {
						byte b1[] = new byte[byteRead];
						System.arraycopy(b, 0, b1, 0, byteRead);
						DatagramPacket goigui = new DatagramPacket(b1, b1.length, dc, 23456);
						ms.send(goigui);
					}
					DatagramPacket goikt = new DatagramPacket(new byte[0], 0, dc, 23456);
					ms.send(goikt);
				} catch (Exception e) {
					System.out.println(e);
				}
				System.out.println("Da gui xong file, sau 30s se gui lai!");
				Thread.sleep(30000);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}

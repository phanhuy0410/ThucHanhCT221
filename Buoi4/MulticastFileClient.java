import java.io.*;
import java.net.*;

public class MulticastFileClient {

	public static void main(String[] args) {
		try {
			MulticastSocket ms = new MulticastSocket(23456);
			InetAddress dc = InetAddress.getByName("231.2.3.4");
			ms.joinGroup(dc);
			int fileCount = 0;
			while (true) {
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				byte b[] = new byte[1000];
				System.out.println("Dang nhan file...");
				while (true) {
					DatagramPacket goinhan = new DatagramPacket(b, b.length);
					ms.receive(goinhan);
					if (goinhan.getLength() == 0)
						break;
					baos.write(goinhan.getData(), 0, goinhan.getLength());
					System.out.println("Da nhan goi: " + goinhan.getLength() + " bytes");
				}
				String filename = "Ketqua"+(++fileCount)+".txt";
				try {
					FileOutputStream f = new FileOutputStream(filename);
					baos.writeTo(f);
					System.out.println("Da luu file: " + filename);
				} catch (Exception e) {
					System.out.println(e);
				}
			}
		} catch (Exception e) {
			System.out.println(e);
		}

	}

}

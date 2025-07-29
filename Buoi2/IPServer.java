import java.io.*;
import java.net.*;
import java.util.Scanner;

class PhucVuIP extends Thread {
	Socket s;

	public PhucVuIP(Socket s1) {
		s = s1;
	}

	public void run() {
		try {
			InputStream is = s.getInputStream();
			OutputStream os = s.getOutputStream();
			Scanner sc = new Scanner(is);
			PrintStream ps = new PrintStream(os);
			while (true) {
				String ip = sc.nextLine();
				if (ip.equals("exit")) break;
				String[] strIP = ip.trim().split("\\.");
				String kq = "";
				if (strIP.length == 4) {
					boolean valid = true;
					for (String str : strIP) {
						int octet = Integer.parseInt(str);
						if (octet < 0 || octet > 255) {
							kq = "Dia chi IP khong hop le";
							valid = false;
							break;
						}
					}
					if (valid) {
						try {
							int octet1 = Integer.parseInt(strIP[0]);
							if (octet1 >= 0 && octet1 <= 127)
								kq = "Dia chi IP tren la thuoc lop A";
							else if (octet1 >= 128 && octet1 <= 191)
								kq = "Dia chi IP tren la thuoc lop B";
							else if (octet1 >= 192 && octet1 <= 223)
								kq = "Dia chi IP tren la thuoc lop C";
							else if (octet1 >= 224 && octet1 <= 239)
								kq = "Dia chi IP tren la thuoc lop D";
							else if (octet1 >= 240 && octet1 <= 255)
								kq = "Dia chi IP tren la thuoc lop E";
						} catch (Exception e) {
							kq = "Dia chi IP khong hop le";
						}
					}
				} else {
					kq = "Do dai dia chi IP khong hop le";
				}
				ps.println(kq);
			}
			s.close();
		} catch (Exception e) {
			System.out.println("Client cong " + s.getPort() + " da dong ket noi!");
		}
	}
}

public class IPServer {
	public static void main(String[] args) {
		try {
			ServerSocket ss = new ServerSocket(5000);
			System.out.println("Da ket noi thanh cong den Server!");
			while (true) {
				Socket s = ss.accept();
				System.out.println("Da ket noi den client cong: " + s.getPort());
				PhucVuIP pv = new PhucVuIP(s);
				pv.start();
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}

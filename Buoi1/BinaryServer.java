import java.io.*;
import java.net.*;
import java.util.Scanner;

public class BinaryServer {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		try {
			ServerSocket ss = new ServerSocket(7);
			System.out.println("Da ket noi thanh cong voi Server");
			while (true) {
				try {
					Socket s = ss.accept();
					System.out.println("Chao mung client: " + s.getPort());
					BufferedInputStream bis = new BufferedInputStream(s.getInputStream());
					BufferedOutputStream bos = new BufferedOutputStream(s.getOutputStream());
					while (true) {
						byte[] inputByte = new byte[100];
						int n = bis.read(inputByte);
						String inputStr = new String(inputByte, 0, n);
						if (inputStr.equals("exit"))
							break;
						String binaryStr;
						try {
							int inputInt = Integer.parseInt(inputStr.trim());
							binaryStr = Integer.toBinaryString(inputInt);
						} catch (Exception e) {
							binaryStr = "Khong phai la so nguyen";
						}
						bos.write(binaryStr.getBytes());
						bos.flush();
					}
					s.close();
					System.out.println("Client " + s.getPort() + " da dong ket noi");
				} catch (IOException e) {}
			}
		} catch (Exception e) {
			System.out.println("Loi server");
		}

	}

}

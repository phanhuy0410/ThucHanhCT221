import java.io.*;
import java.net.*;
import java.util.Scanner;
public class BinaryClient {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		try {
			Socket s = new Socket("localhost",7);
			BufferedInputStream bis = new BufferedInputStream(s.getInputStream());
			BufferedOutputStream bos = new BufferedOutputStream(s.getOutputStream());
			while(true) {
				System.out.println("Nhap chuoi so nguyen:");
				String inputStr = sc.nextLine();
				if(inputStr.length()==0) continue;
				byte[] inputByte = inputStr.getBytes();
				bos.write(inputByte);
				bos.flush();
				if(inputStr.equals("exit")) break;
				//Nhan phan hoi tu server
				byte[] resultByte = new byte[100];
				int n = bis.read(resultByte);
				String resultStr = new String(resultByte,0,n);
				System.out.println("Ket qua: "+resultStr);
			}
			System.out.println("Da dong ket noi!");
			s.close();
		} catch(Exception e) {
			System.out.println("Loi: "+e.toString());
		}

	}

}

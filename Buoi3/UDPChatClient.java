import java.net.*;
import java.util.*;
public class UDPChatClient {
	
	public static void main(String[] args) {
		try {
			Scanner kb = new Scanner(System.in);
			System.out.println("Nhap dia chi Server: ");
			String dcServer = kb.nextLine();
			DatagramSocket ds = new DatagramSocket();
			
			Thread gui = new Thread(){
				public void run() {
					try {
						while(true) {
							System.out.println("Nhap tin nhan: ");
							String str = kb.nextLine();
							if(str.equals("exit")) {
								ds.close();
								break;
							}
							byte b[] = str.getBytes();
							InetAddress dc = InetAddress.getByName(dcServer);
							int p = 5000;
							DatagramPacket goigui = new DatagramPacket(b, b.length, dc, p);
							ds.send(goigui);
						}
					}
					catch(Exception e) {
						System.out.println(e);
					}
				}
			};
			
			Thread nhan = new Thread() {
				public void run() {
					try {
						while(true) {
							byte b1[] = new byte[60000];
							DatagramPacket goinhan = new DatagramPacket(b1, 60000);
							ds.receive(goinhan);
							byte b2[] = goinhan.getData();
							int n2 = goinhan.getLength();
							String kq = new String(b2, 0, n2);
							System.out.println("Nhan duoc: "+kq);
						}
					}
					catch(Exception e) {
						System.out.println("Da dong ket noi voi server");
					}
				}
			};
			gui.start();
			nhan.start();
		}
		catch(Exception e) {
			System.out.println(e);
		}
	}
}

import java.net.*;
import java.util.*;
public class UDPChatServer {

	public static void main(String[] args) {
		try {
			DatagramSocket ds = new DatagramSocket(5000);
			System.out.println("Da ket noi toi Server cong 5000");
			Set<SocketAddress> dcClient = new HashSet<SocketAddress>();
			byte b[] = new byte[60000];
			while(true) {
				DatagramPacket goinhan = new DatagramPacket(b, 60000);
				ds.receive(goinhan);
				byte b1[] = goinhan.getData();
				int n1 = goinhan.getLength();
				String yeucau = new String(b1,0,n1);
				SocketAddress clientGui = goinhan.getSocketAddress();
				dcClient.add(clientGui);
				for(SocketAddress addr : dcClient) {
					if(!addr.equals(clientGui)) {
						byte b2[] = yeucau.getBytes();
						int n2 = b2.length;
						InetAddress dc = ((InetSocketAddress) addr).getAddress();
						int p = ((InetSocketAddress) addr).getPort();
						DatagramPacket goigui = new DatagramPacket(b2, n2, dc, p);
						ds.send(goigui);
					}
				}	
			}
		}
		catch(Exception e) {
			System.out.println(e);
		}
	}

}

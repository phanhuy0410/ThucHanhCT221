import java.io.*;
import java.net.*;
import java.util.*;

public class HuyB2204933 {

    public static void main(String[] args) {
        try {
            Scanner kb = new Scanner(System.in);
            System.out.println("Nhap dia chi Server: ");
            String dcServer = kb.nextLine();
            System.out.println("Nhap cong dich vu UDP Server: ");
            int udpPort = kb.nextInt();
            kb.nextLine();

            DatagramSocket ds = new DatagramSocket();
            System.out.println("Nhap ho ten sinh vien: ");
            String hoten = kb.nextLine();
            byte b[] = hoten.getBytes();
            InetAddress dc = InetAddress.getByName(dcServer);
            DatagramPacket goigui = new DatagramPacket(b, b.length, dc, udpPort);
            ds.send(goigui);

            byte b2[] = new byte[1024];
            DatagramPacket goinhan = new DatagramPacket(b2, b2.length);
            ds.receive(goinhan);
            String kq = new String(goinhan.getData(), 0, goinhan.getLength());
            int tcpPort = Integer.parseInt(kq);
            System.out.println("Cong dich vu TCP la: " + tcpPort);

            Socket s = new Socket(dc, tcpPort);
            InputStream is = s.getInputStream();
            OutputStream os = s.getOutputStream();
            Scanner sc = new Scanner(is);
            PrintStream ps = new PrintStream(os);
            System.out.println("Nhap ngay thang nam sinh: ");
            String ngay = kb.nextLine();
            ps.println(ngay);

            String str = sc.nextLine();
            System.out.println("Chuoi nhan tu Server: " + str);

            ds.close();
            s.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}

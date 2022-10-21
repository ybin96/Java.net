package exam03;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
// java UDPSender 192.168.25.4 안녕
public class UDPSender_test {

	public static void main(String[] args) {
		try {
			DatagramSocket socket = new DatagramSocket();
			InetAddress addr = InetAddress.getByName(args[0]);
			byte[]data = args[1].getBytes();
			DatagramPacket packet = new DatagramPacket(data, data.length, addr, 9002);
			socket.send(packet);
			socket.close();
			
		} catch (Exception e) {
			System.out.println("예외발생:"+e.getMessage());
		}
	}

}

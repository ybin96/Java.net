package exam03;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
// java UDPSender 192.168.25.4 안녕
public class UDPSender {

	public static void main(String[] args) {
		try {
			DatagramSocket socket = new DatagramSocket(); // pack안에 port번호가 있어서 계속 보내기때문에 socket에는 port번호는 생략한다.
			InetAddress addr = InetAddress.getByName(args[0]); // 데이터 패킷형식에 따라 InetAddress의 객체를 생성..addrss는 목적지 주소
			byte[]data = args[1].getBytes(); //byte 단위로 변환
			DatagramPacket packet = new DatagramPacket(data, data.length, addr, 9001);
			// DatagramPacket(byte[] buf, int length, InetAddress address, int port)
			socket.send(packet); //data를 보낸다
			socket.close();
			
		} catch (Exception e) {
			System.out.println("예외발생:" + e.getMessage());
		}

	}

}

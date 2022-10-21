package exam01;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.Arrays;

// 화면없이 UDP방식으로 클라이언트로 부터 수신된 메세지를 그대로 메아리하도록한다.
public class UDPEchoServer {

	public static void main(String[] args) {
		try {
			DatagramSocket socket = new DatagramSocket(9002);
			byte[] data = new byte[100]; // byte단위로 데이터를 주고받기위해
			DatagramPacket packet = new DatagramPacket(data, data.length);
			System.out.println("*** 서버가 가동되었습니다 ***");
			while (true) {
				socket.receive(packet);
				socket.send(packet); // 메아리

				// 받은 데이터를 보내기위해 작성하는 코드
				String msg = new String(data);
				System.out.println("수신된 데이터:" + msg);
				Arrays.fill(data, (byte) 0);
			}

		} catch (Exception e) {
			System.out.println("예외발생:" + e.getMessage());
		}
	}

}

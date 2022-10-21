package exam04;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.Arrays;

// 화면없이 UDP방식으로 클라이언트로 부터 수신된 메세지를 그대로 메아리하도록한다.
public class UDPEchoServer {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			DatagramSocket socket = new DatagramSocket(9002); // 포트 번호 9002
			System.out.println("데이터 수신준비가 되었습니다.");
			byte[] data = new byte[100];
			DatagramPacket packet = new DatagramPacket(data, data.length);
			while(true) {
				socket.receive(packet);
				String msg = new String(data);
				System.out.println("수신된 데이터:"+msg+"\n");
				socket.send(packet); // 메아리
				Arrays.fill(data, (byte)0);
			}
			
		} catch (Exception e) {
			System.out.println("예외처리:"+e.getMessage());
		}
		
	}

}

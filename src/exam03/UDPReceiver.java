package exam03;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.Arrays;

public class UDPReceiver {

	public static void main(String[] args) {
		try {
			DatagramSocket socket = new DatagramSocket(9001); // 포트번호 9001번으로 정함
			System.out.println("*** 데이터 수신 준비가 되었어요 ***");
			byte[] data = new byte[100];
			DatagramPacket packet = new DatagramPacket(data, data.length);
			while (true) {
				socket.receive(packet);
				String msg = new String(data);
				System.out.println("수신된 데이터: " + msg);
				Arrays.fill(data, (byte)0); // 쓰레기값 지운다
			}
		} catch (Exception e) {
			System.out.println("예외발생:" + e.getMessage());
		}
	}
}

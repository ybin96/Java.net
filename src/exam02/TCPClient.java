package exam02;

import java.io.InputStream;
import java.net.Socket;

public class TCPClient {

	public static void main(String[] args) {
		try {
			Socket socket = new Socket("192.168.25.4",9000); // 3번..서버의 ip주소와 포트번호
			InputStream is = socket.getInputStream(); //5번.. 읽기위한 stream
			for(int i=1;i<=10;i++) { // 6번..서버로부터 데이터를 받는다
				int n = is.read();
				System.out.println("서버로 부터 수신된 데이터 >>"+n);
			}
			is.close(); // 7번
			socket.close(); // 7번
		} catch (Exception e) {
			System.out.println("예외처리:"+e.getMessage());
		}

	}

}

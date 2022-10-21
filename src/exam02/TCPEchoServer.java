package exam02;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;

public class TCPEchoServer {

	public static void main(String[] args) {
		try {
			ServerSocket server = new ServerSocket(9003); // 서버가동
			System.out.println("*** 서버가 가동되었습니다 ***");
			byte[] data = new byte[100];
			while (true) { // 무한 대기상태 while
				Socket socket = server.accept();
				System.out.println("*** 클라이언트가 접속하였습니다 ***");
				InputStream is = socket.getInputStream(); // 데이터를 읽기위한 stream
				OutputStream os = socket.getOutputStream(); // 데이터를 받기위한 stream

				while (true) { // 계속 통신하기위한 while .. 데이터를 주고받기위한 과정
					is.read(data); // is를 통해 데이터를 읽는다
					os.write(data); // os를 통해 데이터를 내보낸다(메아리)
					String msg = new String(data);
					System.out.println("수신된 데이터:" + msg);
					Arrays.fill(data, (byte) 0);
				}
			}
		} catch (Exception e) {
			System.out.println("예외발생:" + e.getMessage());
		}

	}

}

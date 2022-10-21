package exam02;

import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;

public class TCPServer {

	public static void main(String[] args) {
		try {
			ServerSocket server = new ServerSocket(9000); //1번..포트번호 9000번으로 정함 (ServerSocket이라는 객체를 생성)
			System.out.println("** 서버가 가동되었습니다 **");
			while(true) { // 2번
				Socket socket = server.accept(); // 4번..데이터를 주고받을 socket을 생성
				System.out.println("클라이언트가 접속하였습니다");
				OutputStream os = socket.getOutputStream(); //5번.. 출력하는 stream
				Random r = new Random(); //6번.. 난수 10개를 0.1초간격으로 보낸다
				for(int i =1;i<=10;i++) {
					int n = r.nextInt(100)+1;
					os.write(n);
					Thread.sleep(100);
				}
				os.close(); // 7번
				socket.close(); // 7번
			}
		} catch (Exception e) {
			System.out.println("예외발생:"+e.getMessage());
			
		}

	}

}

package exam03;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;


class ServerThread extends Thread{
	private Socket socket;
	private InputStream is;
	private OutputStream os;
	
	public ServerThread(Socket socket) {   // 생성자를 만들어 클라이언트를 상대할 socket
		this.socket = socket;
		try {
			is = socket.getInputStream(); // 데이터를 읽기위한 스트림
			os = socket.getOutputStream(); // 데이터를 보내기위한 스트림	
		} catch (Exception e) {
			System.out.println("예외처리:"+e.getMessage());
		}
	}
	
	public void run() {
		byte []data = new byte[100];
		while(true) {
			try {
				is.read(data);
				os.write(data);
				Arrays.fill(data, (byte)0);
			} catch (Exception e) {
				System.out.println("예외처리:"+e.getMessage());
			}
			
		}
	}
}

public class TCPEchoServer {

	public static void main(String[] args) {
		try {
			ServerSocket server = new ServerSocket(9003); // 서버가동
			System.out.println("*** 서버가 가동되었습니다 ***");
			byte[] data = new byte[100];
			while (true) { // 무한 대기상태 while
				Socket socket = server.accept();
				System.out.println("*** 클라이언트가 접속하였습니다 ***");
				new ServerThread(socket).start();
				
				
			}
		} catch (Exception e) {
			System.out.println("예외발생:" + e.getMessage());
		}

	}

}

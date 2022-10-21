package exam04;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;


class ServerThread extends Thread{
	private Socket socket;
	private InputStream is;
	private OutputStream os;
	
	public ServerThread(Socket socket) {   // 생성자를 만들어 클라이언트를 상대할 Server Thread를 생성
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
				sendAll(data);
				Arrays.fill(data, (byte)0);
			} catch (Exception e) {
				System.out.println("예외처리:"+e.getMessage());
			}
		}
	}
	
	public void sendAll(byte []data) {
		for( ServerThread c : TCPChatServer.list) { // list에는 각 클라이언트에 ServerThread가 담겨져있다.
			try {
				c.os.write(data);  // list에 담긴 모든 클라이언트들에게 data를 보낸다
			} catch (Exception e) {
				System.out.println("예외처리:"+e.getMessage());
			}
			
		}
	}
}

public class TCPChatServer {
	
	public static ArrayList<ServerThread> list;  // 클라이언트들을 상대할 ServerThread를 담을 list를 생성

	public static void main(String[] args) {
		try {
			list = new ArrayList<ServerThread>();
			ServerSocket server = new ServerSocket(9008); // 서버가동
			System.out.println("*** 서버가 가동되었습니다 ***");
			byte[] data = new byte[100];
			while (true) { // 무한 대기상태 while
				Socket socket = server.accept();
				System.out.println("*** 클라이언트가 접속하였습니다 ***");
				ServerThread st = new ServerThread(socket); // ServerThread를 가동하고
				list.add(st); // st를 list에 담고
				st.start(); // 실행
			}
		} catch (Exception e) {
			System.out.println("예외발생:" + e.getMessage());
		}

	}

}

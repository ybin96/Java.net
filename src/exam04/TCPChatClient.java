package exam04;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class TCPChatClient extends JFrame {
	JTextArea jta;
	JTextField jtf;
	Socket socket;
	InputStream is; // 버튼을 눌렀을때 필요하기때문에 변수맴버가 필요하다.
	OutputStream os;

	public TCPChatClient() {
		jta = new JTextArea(); // 채팅내용(메아리되는 데이터가 보여진다
		jtf = new JTextField(); // 한줄내용

		JScrollPane jsp = new JScrollPane(jta); // 스크롤을 만드는 준비
		JButton btnSend = new JButton("전송"); // 버튼만들기
		JPanel p = new JPanel(); // 버튼과 jtf를 담을 패널 만들기
		p.setLayout(new BorderLayout()); // 패널의 layout설정하기
		p.add(jtf, BorderLayout.CENTER); // jta를 중앙에 배치
		p.add(btnSend, BorderLayout.EAST); // 버튼을 왼쪽에 배치

		add(jsp, BorderLayout.CENTER); // 스크롤을 가운데
		add(p, BorderLayout.SOUTH); // 패널을 아래에 배치

		setSize(600, 400);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		try {
			Socket socket = new Socket("192.168.25.4", 9008);
			is = socket.getInputStream(); // 위에 변수를 만들었기때문에 is만 남긴다
			os = socket.getOutputStream();

		} catch (Exception e) {
			System.out.println("예외발생:" + e.getMessage());
		}
		
		class ClientThread extends Thread{ // inner class
			byte []data = new byte[100]; // 데이터를 받아오기위한 배열을 만든다
			
			public void run() {
				while(true) {
					try {
						is.read(data);
						String msg = new String(data); // byte를 string으로 변환
						System.out.println(msg);
						jta.append(msg+"\n");
						Arrays.fill(data, (byte)0);
					} catch (Exception e) {
						System.out.println("예외발생:" + e.getMessage());
					}
				}
			}
		}
		
		ClientThread ct = new ClientThread();
		ct.start();

		btnSend.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String msg = jtf.getText(); // jtf에 있는 text를 보내기위한 작업(msg에 담는다)
				try {
					os.write(msg.getBytes()); // msg를 byte형태로 보내기위해 byte로 형변환
				} catch (Exception e2) {
					System.out.println("예외발생:" + e2.getMessage());
				}

			}
		});
	}

	public static void main(String[] args) {
		new TCPChatClient();

	}

}

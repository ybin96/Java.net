package exam04;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.nio.ByteOrder;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
// 서버로부터 수신된 데이터를 jta에 추가하도록 한다. => 버튼누르는거랑 상관없이 받도록 thread로 구현한다.
public class UDPEchoClient extends JFrame {
	JTextArea jta;
	JTextField jtf;
	DatagramSocket socket;
	InetAddress addr;
	int port = 9002;
	
	public UDPEchoClient() {
		try {
			socket = new DatagramSocket();			 //소켓의 객체를 생성
			addr = InetAddress.getByName("192.168.25.4"); // ip주소를 정해준다(지금예제는 나의 ip주소)
		} catch (Exception e) {
			System.out.println("예외발생:"+e.getMessage());
		}
		
		jta = new JTextArea(); 						// 채팅내용(메아리되는 데이터가 보여진다
		jtf = new JTextField(); 					// 한줄내용
		
		JScrollPane jsp = new JScrollPane(jta);		// 스크롤을 만드는 준비
		JButton btnSend = new JButton("전송"); 		// 버튼만들기
		JPanel p = new JPanel();					 // 버튼과 jtf를 담을 패널 만들기
		p.setLayout(new BorderLayout()); 			// 패널의 layout설정하기
		p.add(jtf,BorderLayout.CENTER); 			// jta를 중앙에 배치
		p.add(btnSend,BorderLayout.EAST); 			// 버튼을 왼쪽에 배치
		
		add(jsp, BorderLayout.CENTER);				 // 스크롤을 가운데
		add(p,BorderLayout.SOUTH); 					// 패널을 아래에 배치
		
		setSize(600,400);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		btnSend.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String msg = jtf.getText(); 		// 보내고자하는 데이터
				byte[]data = msg.getBytes(); 		// 데이터를 byte로 변환
				DatagramPacket packet = new DatagramPacket(data, data.length, addr, port);
				try {
					socket.send(packet); 			// 데이터가 다담긴 packet을 보낸다
					socket.receive(packet);
					msg = new String(packet.getData());
					jta.append(msg+"\n");
				} catch (Exception e2) {
					System.out.println("예외발생:"+e2.getMessage());
				}
				
				
			}
		});
		
		
		
	}

	public static void main(String[] args) {
		new UDPEchoClient();
		
	}
}

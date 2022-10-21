package exam01;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.InputStream;
import java.net.URL;
import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class URLTestGUI extends JFrame {
	JTextField jtf;
	JTextArea jta;
	JFileChooser jfc;

	public URLTestGUI() {
		jtf = new JTextField(); // 주소입력창
		jta = new JTextArea(); // 나머지 아래창
		jfc = new JFileChooser("c:/data"); // 파일을 어디에 저장할꺼냐고 물어본다
		JButton btnRead = new JButton("읽어오기"); // 열리는 버튼만들기
		JButton btnSave = new JButton("저장하기"); // 저장하는 버튼 만들기

		// 패널을 만들어서 주소입력창을 넣는다
		JPanel p = new JPanel();
		p.setLayout(new BorderLayout());
		p.add(jtf, BorderLayout.CENTER);

		// 버튼패널을 하나 더 만들어서 열리는버튼과 저장하는 버튼을 넣는다
		JPanel p_button = new JPanel();
		p_button.add(btnRead);
		p_button.add(btnSave);
		p.add(p_button, BorderLayout.EAST);

		// 나머지 아래창을 스코를을 넣어서 프레임 아래에 넣는다
		JScrollPane jsp = new JScrollPane(jta);
		add(p, BorderLayout.NORTH);
		add(jsp, BorderLayout.CENTER);

		setSize(800, 600);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// 익명 클래시이기때문에 바로 객체를 생성한다.(ActionListener)
		btnRead.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String addr = jtf.getText();// textfield에 써있는것을 가져온다
				try {
					URL url = new URL(addr); // URL객체를 생성(addr)
					InputStream is = url.openStream(); // byte단위에 입력
					byte[] data = new byte[200];
					String str = "";
					while (is.read(data) != -1) {
						String line = new String(data);
						str += line;
						Arrays.fill(data, (byte) 0); // 실행될떄 배열에쓰레기값이 들어있을수 있으니 초기화해달라는것
					}
					jta.setText(str); // str에 담긴걸 jta에 보여준다
					is.close();

				} catch (Exception e2) {
					System.out.println("예외처리:" + e2.getMessage());
				}

			}
		});
		// JFileChooser를 사용한다.
		btnSave.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int re = jfc.showSaveDialog(null); // 저장버튼은 보여준다.
				if (re == 0) { // 0이면 긍정
					File file = jfc.getSelectedFile(); // 파일을 선택할수있게한다.
					try {
						FileWriter fw = new FileWriter(file);
						fw.write(jta.getText());
						fw.close();
						JOptionPane.showMessageDialog(null, "저장하였습니다.");
					} catch (Exception e2) {
						System.out.println("예외발생:" + e2.getMessage());
					}
				}
			}
		});
	}

	public static void main(String[] args) {
		new URLTestGUI();

	}

}

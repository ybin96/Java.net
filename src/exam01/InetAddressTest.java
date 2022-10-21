package exam01;

import java.net.InetAddress;

public class InetAddressTest {

	public static void main(String[] args) {
		try {
			InetAddress addr = InetAddress.getByName("www.naver.com");
			System.out.println(addr); // 네이버의 ip주소를 확인할수 있다.
		} catch (Exception e) {
			System.out.println("예외발생:"+e.getMessage());
		}
		

	}

}

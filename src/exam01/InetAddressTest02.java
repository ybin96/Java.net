package exam01;

import java.net.InetAddress;

public class InetAddressTest02 {

	public static void main(String[] args) {
		try {
			InetAddress [] addr = InetAddress.getAllByName("www.naver.com");
			for(InetAddress ad:addr) {
				System.out.println(ad);
			}
			System.out.println(addr); // 네이버의 ip주소를 확인할수 있다.
		} catch (Exception e) {
			System.out.println("예외발생:"+e.getMessage());
		}
		

	}

}

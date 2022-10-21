package exam01;

import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.util.Arrays;

import javax.print.DocFlavor.INPUT_STREAM;

public class URLTest {

	public static void main(String[] args) {
		try {
			URL url = new URL("https://www.hanbit.co.kr/index.html");
			InputStream is = url.openStream();
			byte []data = new byte[200];
			String str = "";
			String line = "";
			while(is.read(data) != -1) {
				line = new String(data);// byte형을 문자로 변경
				str += line;
				Arrays.fill(data, (byte)0); // date다 byte형이니깐 형변환
			}
			System.out.println(str);
			is.close();
		} catch (Exception e) {
			System.out.println("예외발생:"+e.getMessage());
		}

	}

}
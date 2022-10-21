package practice;

import java.io.FileReader;
import java.io.FileWriter;
import java.util.HashMap;

public class Cipher {
	public static void main(String[] args) {
		try {
			FileReader fr = new FileReader(args[0]);
			FileWriter fw = new FileWriter(args[1]);
			
			String key = "abcdefghijklmnopqrstuvwxyz";
			String value = "DEFGHIJKLMNOPQRSTUVWXYZABC";			
			HashMap<String, String> map = new HashMap<String, String>();
			for(int i=0; i<key.length(); i++) {
				map.put(key.charAt(i)+"", value.charAt(i)+"");
			}			
			String str = "";
			int ch;
			while((ch = fr.read()) != -1) {
				 if(ch >= 'a' && ch <= 'z') {	
					 String s = ((char)ch)+"";
					 str += map.get(s);
				 }else {
					 str += ((char)ch)+"";
				 }
				 
			}
			
			fw.write(str);
			fr.close();
			fw.close();
			System.out.println("시저 암호로 암호화하였습니다.");
		}catch (Exception e) {
			System.out.println("예외발생:"+e.getMessage());
		}
	}
}
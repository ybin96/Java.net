package practice;
import java.io.FileWriter;
import java.util.Random;
/*
 숙제1) 지정된 개수의 난수를 문자열 형식으로 파일에 저장하는 프로그램을 작성합니다.
한줄에 Line개의 정수를 저장한다. 각 정수는 공백으로 구분한다.
(단, 난수는 중복을 허용합니다.)

난수의 최대값 : 300
한 줄에 출력하는 난수의 개수 : 10
난수의 개수 : 100
파일의 이름 : test.txt 
 */
import java.util.Scanner;
public class RandomFile {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int max, line, n;
		String fname;
		System.out.print("난수의 최대값:");
		max = sc.nextInt();
		System.out.print("한 줄에 출력하는 난수의 개수 :");
		line = sc.nextInt();
		System.out.print("난수의 개수 :");
		n = sc.nextInt();
		System.out.print("파일의 이름:");
		fname = sc.next();		
		Random rand = new Random();
		String str = "";
		for(int i=1; i<= n; i++) {
			str += rand.nextInt(max)+1 + " ";
			if(i % line == 0) {
				str += "\n";
			}
		}		
		try {
			FileWriter fw = new FileWriter(fname);
			fw.write(str);
			fw.close();
			System.out.println("파일을 생성하였습니다.");
		}catch (Exception e) {
			System.out.println("예외발생:"+e.getMessage());
		}
	}
}










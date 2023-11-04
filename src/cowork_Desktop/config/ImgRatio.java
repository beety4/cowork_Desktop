package cowork_Desktop.config;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ImgRatio {
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		//double x = Double.parseDouble(br.readLine());
		//double y = Double.parseDouble(br.readLine());
		double x = 1303;
		double y = 469;
		double divide = Double.parseDouble(br.readLine());
		
		
		double rs1 = x/divide;
		double rs2 = y/divide;
		System.out.println(rs1 + " " + rs2);
		
	}
}

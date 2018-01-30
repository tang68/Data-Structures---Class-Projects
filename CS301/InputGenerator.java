package solveSystemEquation;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Random;

public class InputGenerator {

	public static void main(String[] args) {

		try (Writer writer = new BufferedWriter(
				new OutputStreamWriter(new FileOutputStream("Coeff.txt"), "utf-8"))) {
			Random rand = new Random();
			
			int num = 50;
			
			for (int i = 0; i < num; i++) {
				
				for (int j = 0; j <= num; j++) {
					int coeff = rand.nextInt(300) -150;
					String str = Integer.toString(coeff);
					writer.write(str + " ");
					
				}
				writer.write("\r\n");
			}
			
		} catch (IOException ex) {
			System.out.println("***** Un able to generate file");
		}
	}

}

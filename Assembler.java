//A program for SimMusic Assembler
//It reads SimMusic Languages and converts them into 5-digit color tape.

import java.io.*;
import java.util.*;

import java.awt.image.BufferedImage;
import java.awt.Graphics2D;
import java.awt.Color;
import javax.imageio.ImageIO;

public class Assembler {
	public static void main(String[] args) {
		/* Read SimMusic Language 
		(Read Input Program file, a text file format) */
		String fileName = "For Elise_lang.txt";
		Scanner inputStream = null;
		try {
			inputStream = new Scanner(new File(fileName));
		} catch (FileNotFoundException e) {	//in the case of opening file error
			System.out.println("Error in opening a File.\n");
			System.exit(1);
		}
		/* Array for storing tokens of input program file */
		int number = 0;	//line number
		String line;	//read text file line by line
		StringTokenizer tokenizer; String token;	//use Tokenizer
		List<String> colorCode = new ArrayList<String>();	
		while (inputStream.hasNextLine()) {
			line = inputStream.nextLine();
			number++;
			tokenizer = new StringTokenizer(line, " ,\n");
			while (tokenizer.hasMoreTokens()) {
				token = tokenizer.nextToken();
				colorCode.add(token);
			}
		}
		inputStream.close();
		
		/* Converts SimMusic Languages into 5-digit number */
		int[][] assem = new int[number][5];
		int index = 0;
		for (int i = 0; i < colorCode.size(); i++) {
			switch (colorCode.get(i)) {
			//in the case of PN
			case "pn":
			case "PN":
			case "pN":
			case "Pn":
				assem[index][0] = 0;
				assem[index][1] = 0;
				i++;
				// Note for play note using C2M(Color to Music) Code
				if (colorCode.get(i).equalsIgnoreCase("C4")) {
					assem[index][2] = 0;
					assem[index][3] = 0;
				} else if (colorCode.get(i).equalsIgnoreCase("CS4")) {
					assem[index][2] = 0;
					assem[index][3] = 1;
				} else if (colorCode.get(i).equalsIgnoreCase("D4")) {
					assem[index][2] = 0;
					assem[index][3] = 2;
				} else if (colorCode.get(i).equalsIgnoreCase("DS4")) {
					assem[index][2] = 0;
					assem[index][3] = 3;
				} else if (colorCode.get(i).equalsIgnoreCase("E4")) {
					assem[index][2] = 0;
					assem[index][3] = 4;
				} else if (colorCode.get(i).equalsIgnoreCase("F4")) {
					assem[index][2] = 1;
					assem[index][3] = 0;
				} else if (colorCode.get(i).equalsIgnoreCase("FS4")) {
					assem[index][2] = 1;
					assem[index][3] = 1;
				} else if (colorCode.get(i).equalsIgnoreCase("G4")) {
					assem[index][2] = 1;
					assem[index][3] = 2;
				} else if (colorCode.get(i).equalsIgnoreCase("GS4")) {
					assem[index][2] = 1;
					assem[index][3] = 3;
				} else if (colorCode.get(i).equalsIgnoreCase("A4")) {
					assem[index][2] = 1;
					assem[index][3] = 4;
				} else if (colorCode.get(i).equalsIgnoreCase("AS4")) {
					assem[index][2] = 2;
					assem[index][3] = 0;
				} else if (colorCode.get(i).equalsIgnoreCase("B4")) {
					assem[index][2] = 2;
					assem[index][3] = 1;
				} else if (colorCode.get(i).equalsIgnoreCase("C5")) {
					assem[index][2] = 2;
					assem[index][3] = 2;
				} else if (colorCode.get(i).equalsIgnoreCase("CS5")) {
					assem[index][2] = 2;
					assem[index][3] = 3;
				} else if (colorCode.get(i).equalsIgnoreCase("D5")) {
					assem[index][2] = 2;
					assem[index][3] = 4;
				} else if (colorCode.get(i).equalsIgnoreCase("DS5")) {
					assem[index][2] = 3;
					assem[index][3] = 0;
				} else if (colorCode.get(i).equalsIgnoreCase("E5")) {
					assem[index][2] = 3;
					assem[index][3] = 1;
				} else if (colorCode.get(i).equalsIgnoreCase("F5")) {
					assem[index][2] = 3;
					assem[index][3] = 2;
				} else if (colorCode.get(i).equalsIgnoreCase("FS5")) {
					assem[index][2] = 3;
					assem[index][3] = 3;
				} else if (colorCode.get(i).equalsIgnoreCase("G5")) {
					assem[index][2] = 3;
					assem[index][3] = 4;
				} else if (colorCode.get(i).equalsIgnoreCase("GS5")) {
					assem[index][2] = 4;
					assem[index][3] = 0;
				} else if (colorCode.get(i).equalsIgnoreCase("A5")) {
					assem[index][2] = 4;
					assem[index][3] = 1;
				} else if (colorCode.get(i).equalsIgnoreCase("B5")) {
					assem[index][2] = 4;
					assem[index][3] = 3;
				}
				i++;
				//Duration for play note
				switch (colorCode.get(i)) {
				case "1":
					assem[index][4] = 0;
					break;
				case "2":
					assem[index][4] = 1;
					break;
				case "4":
					assem[index][4] = 2;
					break;
				case "8":
					assem[index][4] = 3;
					break;
				case "16":
					assem[index][4] = 4;
					break;
				}
				break;
			//in the case of JM
			case "jm":
			case "JM":
			case "jM":
			case "Jm":
				assem[index][0] = 1;
				i++;
				//conversion from decimal to 5-ary
				int tmp = Integer.parseInt(colorCode.get(i));
				for (int j=4; tmp>0; j--){
					assem[index][j] = tmp%5;
					tmp = tmp/5;
				}
				break;
			//in the case of RM
			case "rm":
			case "RM":
			case "rM":
			case "Rm":
				assem[index][0] = 2;
				i++;
				assem[index][1] = Integer.parseInt(colorCode.get(i));
				i++;
				//conversion from decimal to 5-ary
				tmp = Integer.parseInt(colorCode.get(i));
				for (int j=4; tmp>0; j--){
					assem[index][j] = tmp%5;
					tmp = tmp/5;
				}
				break;
			//in the case of PM
			case "pm":
			case "PM":
			case "pM":
			case "Pm":
				assem[index][0] = 3;
				i++;
				//conversion from decimal to 5-ary
				tmp = Integer.parseInt(colorCode.get(i));
				for (int j=4; tmp>0; j--){
					assem[index][j] = tmp%5;
					tmp = tmp/5;
				}
				break;
			//in the case of SM
			case "sm":
			case "SM":
			case "sM":
			case "Sm":
				assem[index][0] = 4;
				assem[index][1] = 0;
				assem[index][2] = 0;
				assem[index][3] = 0;
				assem[index][4] = 0;
				break;
			}
			index++;
		}
		//display 5-digit numbers on console
		for (int i = 0; i < number; i++) {
			for (int j = 0; j < 5; j++) {
				System.out.print(assem[i][j]);
			}
			System.out.println();
		}
		
		/* Converts 5-digit numbers into Color Tape corresponding to machine code */
		double d1 = number;
		int d2 = Math.round(number/2)+1;
		
		//Creating a BufferedImage object and save it to image file 
		BufferedImage image = new BufferedImage(1000, 100*d2, BufferedImage.TYPE_INT_RGB); 
		Graphics2D graphics = image.createGraphics(); 
		
		//Setting colors for color tape */
		Color lavender = new Color(216,191,216);
		Color green = new Color(124, 252, 0);
		
		graphics.setColor(lavender);
		graphics.fillRect(0, 0, 500, 100);
		int flag = 0;
		//Conversion 5-ary to color tape
		for (int i = 0; i <number; i++) {
			for (int j = 0; j < 5; j++) {
				if(assem[i][j]==0){
					graphics.setColor(Color.blue);
				} else if(assem[i][j]==1){
					graphics.setColor(green);
				} else if(assem[i][j]==2){
					graphics.setColor(Color.YELLOW);
				} else if(assem[i][j]==3){
					graphics.setColor(Color.RED);
				} else if(assem[i][j]==4){
					graphics.setColor(lavender);
				}
				if(flag%2 ==0){
					graphics.fillRect(100*(j+5), 100*(flag/2), 100, 100);
				} else {
					graphics.fillRect(100*j, 100*(flag/2+1), 100, 100);
				}
			}
			flag++;
		}
		
		//Save it to jpeg file
		try {
			File file = new File("ForElise_tape.jpeg");
			ImageIO.write(image, "jpeg", file);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

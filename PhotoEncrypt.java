import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;


public class PhotoEncrypt {


	public static void main (String[] args){
		//int x =255; //255 in base 10
		//int y = 0xff;	// 255 in base 16

		//System.out.println("x: " +x + " y: " + y);

		/*
		double eps = 1.0;
		while (1.0+eps != 1.0){
			eps = eps/2;
		}
		System.out.println(eps);
		 */


		//take 1 "000000.....000001" and shift left 4 times
		//System.out.println(1<<30);
		//shifting left by 1 is like multiplying by 2
		// shifting left by n is like multiplying the number by 2^n


		//shift right
		//System.out.println(4>>3);
		//shifting right once is like dividing by 2
		//shifting right by n is like dividing by 2^n
		//System.out.println(-1>>>1);






		/*
		Graphics g = canvas.getGraphics();

		g.setColor(new Color(120,0,0));
		g.fillRect(0, 0, 100, 400);

		g.setColor(new Color(124,0,0));
		g.fillRect(100, 0, 100, 400);

		g.setColor(new Color(128,0,0));
		g.fillRect(200, 0, 100, 400);

		g.setColor(new Color(132,0,0));
		g.fillRect(300, 0, 100, 400);
		 */





		//Picture canvas = new Picture("some.png");
		//decodeLowerBits(canvas);
		//canvas.repaint();

		//Picture a = new Picture("pica.png");
		//Picture b = new Picture("picb.png");
		//a.repaint();
		//b.repaint();
		//Picture result = decrypt(a,b);
		//result.repaint();



		Picture original = new Picture("some.png");
		original.repaint();

		Picture key = new Picture(original.getWidth(), original.getHeight());
		makeKey(key);
		key.repaint();
		Picture encrypted = decrypt (original,key);
		Picture decrypted = decrypt (encrypted, key);
		

		encrypted.repaint();
		decrypted.repaint();





	}//end main

	public static void makeKey(Picture key){
		//fill key up with random color
		Random rand = new Random();
		for (int x = 0; x<key.getWidth(); x++){
			for (int y = 0; y < key.getHeight(); y++){

				key.getPixel(x,y).setColor(
						new Color(rand.nextInt(0x1000000)));
				
			}
		}
	}

	public static Picture decrypt(Picture a, Picture b){
		Picture result = new Picture(a.getWidth(), a.getHeight());
		for (int x = 0; x<a.getWidth(); x++){
			for (int y = 0; y < a.getHeight(); y++){
				Pixel pa =a.getPixel(x, y);
				Pixel pb =b.getPixel(x, y);

				int ra = pa.getRed();
				int ga = pa.getGreen();
				int ba = pa.getBlue();

				int rb = pb.getRed();
				int gb = pb.getGreen();
				int bb = pb.getBlue();


				//the color of the pixel at x,y
				//is a bitwise xor (exclusive or) from respect
				//pixel colors in picure a and b
				result.getPixel(x, y).setColor(
						new Color(ra^rb, ga^gb, ba^bb));
			}
		}
		return result;
	}


	public static void decodeLowerBits(Picture canvas){


		for (int x = 0; x<canvas.getWidth(); x++){
			for (int y = 0; y < canvas.getHeight(); y++){
				Pixel p = canvas.getPixel(x,y);

				int r = p.getRed();
				//p.setRed((r& 3)<<6);


				int g = p.getGreen();
				//p.setGreen((g&3)<<6);


				int b = p.getBlue();
				//p.setBlue((b&3)<<6);

				p.setColor(new Color(((r& 7)<<5)| 31,
						((g& 7)<<5) | 31,
						((b& 7)<<5)|31));

			}
		}
	}



}

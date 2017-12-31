import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;
public class WordPuzzle {
	
	private String [][] puzzle;
//	private String [] words;
	private int wordCount;
	private int row;
	private int column;
	
	public void createPuzzle(int r, int c) {
		row = r;
		column = c;		
		puzzle = new String[row][column];
		Random rand = new Random();
		for(int i = 0; i < row; i++) {
			for(int j = 0; j < column; j++) {
				puzzle[i][j] = Character.toString((char) (97 + rand.nextInt(26)));
			}
		}	
	}
	
	public void printPuzzle() {
		for(int i = 0; i < row; i++) {
			for(int j = 0; j < column; j++) {
				System.out.print(puzzle[i][j] + " ");
			}
			System.out.println();
		}
	}
	
	public void findWords(MyHashTable ht, int type) {
		wordCount = 0;
		//check for word along rows
		for(int i=0; i < row; i++) {
			//forward check for each row
			StringBuilder wordBuilder;
			int k = 0;
			while(k < column) {
				wordBuilder = new StringBuilder();
				for(int j = k; j < column; j++) {
//					System.out.print(":" + i + " " + j + ":");
					wordBuilder.append(puzzle[i][j]);
					int temp = ht.contains(wordBuilder.toString());
					if(temp == 0 && type == 1) {
						wordBuilder.setLength(0);
						break;
					}else if(temp == 1) {
						wordCount++;
						System.out.println(wordBuilder); //print the formed word
					}else {}
				}
				k++;
			}
//			System.out.println();
			
			//reverse check for each row
			StringBuilder wordBuilderRev;
			int kr = column - 1;
			while(kr > 0) {
				wordBuilderRev = new StringBuilder();
				wordBuilderRev.append(puzzle[i][kr]);
//				System.out.print(":" + i + " " + kr + ":");
				for(int j = kr-1 ; j >= 0 ; j-- ) {
//					System.out.print(":" + i + " " + j + ":");
					wordBuilderRev.append(puzzle[i][j]);
					int temp = ht.contains(wordBuilderRev.toString());
					if(temp == 0 && type == 1) {
						wordBuilderRev.setLength(0);
						break;
					}else if(temp == 1) {
						wordCount++;
						System.out.println(wordBuilderRev); //print the formed word
					}else {	}
				}
				kr--;
			}		
		}
		
		
		//check for words along column
		for(int j=0; j < column; j++) {
			//forward check for each column
			StringBuilder wordBuilder;
			int k = 0;
			while(k < row-1) {
				wordBuilder = new StringBuilder();
				wordBuilder.append(puzzle[k][j]);
//				System.out.print(":" + k + " " + j + ":");
				for(int i = k+1; i < row; i++) {
//					System.out.print(":" + i + " " + j + ":");
					wordBuilder.append(puzzle[i][j]);
					int temp = ht.contains(wordBuilder.toString());
					if(temp == 0 && type == 1) {
						wordBuilder.setLength(0);
						break;
					}else if(temp == 1) {
						wordCount++;
						System.out.println(wordBuilder); //print the formed word
					}else {	}
				}
				k++;
			}
//			System.out.println();
			
			//reverse check for each column
			StringBuilder wordBuilderRev;
			int kr = row - 1;
			while(kr > 0) {
				wordBuilderRev = new StringBuilder();
				wordBuilderRev.append(puzzle[kr][j]);
//				System.out.print(":" + kr + " " + j + ":");
				for(int i = kr-1 ; i >= 0 ; i-- ) {
//					System.out.print(":" + i + " " + j + ":");
					wordBuilderRev.append(puzzle[i][j]);
					int temp = ht.contains(wordBuilderRev.toString());
					if(temp == 0 && type == 1) {
						wordBuilderRev.setLength(0);
						break;
					}else if(temp == 1) {
						wordCount++;
						System.out.println(wordBuilderRev); //print the formed word
					}else {	}
				}
				kr--;
			}		
		}
		
		
		//check for word along diagnols 
		for(int i=1; i < row; i++) {
			//forward check for each diagnols row0 to 0column
			StringBuilder wordBuilder;
			int k = 0;
			while(k < column-1) {
				wordBuilder = new StringBuilder();
				wordBuilder.append(puzzle[i][k]);
//				System.out.print(":" + i + " " + k + ":");
				int ir = i-1;
				for(int j = k+1; j < column; j++) {
//					System.out.print(":" + ir + " " + j + ":");
					wordBuilder.append(puzzle[ir][j]);
					int temp = ht.contains(wordBuilder.toString());
					if(temp == 0 && type == 1) {
						wordBuilder.setLength(0);
						break;
					}else if(temp == 1) {
						wordCount++;
						System.out.println(wordBuilder); //print the formed word
					}else {	}
					ir--;
					if(ir < 0) {
						break;
					}					
				}
				k++;
			}
//			System.out.println();
			
			//reverse check for each diagnols 0column to row0
			StringBuilder wordBuilderRev;
			int kr = 1;
			while(kr < column) {
				wordBuilderRev = new StringBuilder();
				wordBuilderRev.append(puzzle[i-1][kr]);
//				System.out.print(":" + (i-1) + " " + kr + ":");
				int irr = i;
				for(int j = kr-1; j >= 0; j--) {
//					System.out.print(":" + irr + " " + j + ":");
					wordBuilderRev.append(puzzle[irr][j]);
					int temp = ht.contains(wordBuilderRev.toString());
					if(temp == 0 && type == 1) {
						wordBuilderRev.setLength(0);
						break;
					}else if(temp == 1) {
						wordCount++;
						System.out.println(wordBuilderRev); //print the formed word
					}else {	}
					irr++;
					if(irr > row - 1) {
						break;
					}					
				}
				kr++;
			}
//			System.out.println();
		}
		
		
		//check for word along another diagnols 
		for(int i=1; i < row; i++) {
			//forward check for each diagnols 00 to rowcolumn
			StringBuilder wordBuilder;
			int k = column - 1;
			while(k > 0) {
				wordBuilder = new StringBuilder();
				wordBuilder.append(puzzle[i-1][k-1]);
//				System.out.print(":" + (i-1) + " " + (k-1) + ":");
				int ir = i;
				for(int j = k; j < column; j++) {
//					System.out.print(":" + ir + " " + j + ":");
					wordBuilder.append(puzzle[ir][j]);
					int temp = ht.contains(wordBuilder.toString());
					if(temp == 0 && type == 1) {
						wordBuilder.setLength(0);
						break;
					}else if(temp == 1) {
						wordCount++;
						System.out.println(wordBuilder); //print the formed word
					}else {	}
					ir++;
					if(ir > row -1) {
						break;
					}
//					
				}
				k--;
			}
//			System.out.println();
			
			//reverse check for each diagnols rowcolumn to 00
			StringBuilder wordBuilderRev;
			int kr = column -1;
			while(kr > 0) {
				wordBuilderRev = new StringBuilder();
				wordBuilderRev.append(puzzle[i][kr]);
//				System.out.print(":" + i + " " + kr + ":");
				int irr = i-1;
				for(int j = kr-1; j >= 0; j--) {
//					System.out.print(":" + irr + " " + j + ":");
					wordBuilderRev.append(puzzle[irr][j]);
					int temp = ht.contains(wordBuilderRev.toString());
					if(temp == 0 && type == 1) {
						wordBuilderRev.setLength(0);
						break;
					}else if(temp == 1) {
						wordCount++;
						System.out.println(wordBuilderRev); //print the formed word
					}else {	}
					irr--;
					if(irr < 0) {
						break;
					}					
				}
				kr--;
			}
//			System.out.println();
		}
	}	
	
	
	public static void main(String[] args) {
		WordPuzzle wp = new WordPuzzle();
		MyHashTable<String> ht = new MyHashTable<>();
		Scanner sc = new Scanner(System.in);
		System.out.println("Please enter the no. of rows and column : ");
		int row = sc.nextInt();
		int column = sc.nextInt();
		if(row < 1 || column < 1) {
			System.out.println("The row and column should be grater than 0.");
		}else {
			System.out.println("Please enter the type of hashing. 0 for default and 1 for enhanced : ");
			int type = sc.nextInt();
			wp.createPuzzle(row, column);
			wp.printPuzzle();
			File file = new File("dictionary.txt"); //for direct path
			//System.err.println(file.getAbsolutePath());
			try {
				Scanner fl = new Scanner(file);
				while (fl.hasNextLine()) {
		            String s = fl.next();
		            if(type == 1) {
		            	StringBuilder sb = new StringBuilder();
						for(int i = 0; i < s.length(); i++) {
							sb.append(s.charAt(i));
							if(i == s.length() - 1) {
								ht.insert(sb.toString(), true);
							}else {								
								ht.insert(sb.toString(), false);
							}							
						}
					}else {
						ht.insert(s, true);
					}
		        }
		        fl.close();
			}catch(FileNotFoundException e){
				e.printStackTrace();
			}
			//ht.printMyHash();
			long startTime = System.currentTimeMillis( );
			wp.findWords(ht, type);
			long endTime = System.currentTimeMillis( );
			System.out.println("Total no. of words found : " + wp.wordCount);
			System.out.println( "Elapsed time: " + (endTime - startTime) );
		}
		sc.close();		
	}
}

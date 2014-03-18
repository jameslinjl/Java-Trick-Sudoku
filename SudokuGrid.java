//James Lin
//jl3782@columbia.edu
//3-1-14

import java.util.Arrays;
import java.util.Date;
import java.util.Random;
import java.util.Scanner;

public class SudokuGrid {
	
	private boolean isSudokuValid;
	private int grid[][];
	private int answerGrid[][];
	private int testArray[];
	private int count;
	private int difficulty;
	private Scanner scan;
	
	public SudokuGrid(int dif){
		isSudokuValid = true;
		grid = new int[9][9];
		answerGrid = new int[9][9];
		testArray = new int[9];
		count = 81;
		difficulty = dif;
		scan = new Scanner(System.in);
	}
	
	private void printSudoku(int[][] thisGrid){

		System.out.println("   0   1   2   3   4   5   6   7   8");
		System.out.print("  _____________________________________\n");
		for(int i=0; i<9; i++){
			System.out.print(i + "| ");
			for(int j=0; j<9; j++){
				if(thisGrid[i][j] != 0)
					System.out.print(thisGrid[i][j] + " | ");
				else
					System.out.print("  | ");
			}
			System.out.print("\n  _____________________________________");
			System.out.print("\n");
		}
	
	}
	
	public void fillSudoku(){
		
		Random rm = new Random();
		rm.setSeed(new Date().getTime());
		
		//randomly pick seed board
		int seedBoard = rm.nextInt(3);
		setBoard(seedBoard);
	
		//randomize	rows
		int firstRows1 = rm.nextInt(3);
		int firstRows2 = rm.nextInt(3);
		int[] temp = grid[firstRows1];
		grid[firstRows1] = grid[firstRows2];
		grid[firstRows2] = temp;
		
		int secondRows1 = rm.nextInt(3)+3;
		int secondRows2 = rm.nextInt(3)+3;
		temp = grid[secondRows1];
		grid[secondRows1] = grid[secondRows2];
		grid[secondRows2] = temp;
		
		int thirdRows1 = rm.nextInt(3)+6;
		int thirdRows2 = rm.nextInt(3)+6;
		temp = grid[thirdRows1];
		grid[thirdRows1] = grid[thirdRows2];
		grid[thirdRows2] = temp;

		//randomize columns
		int firstCols1 = rm.nextInt(3);
		int firstCols2 = rm.nextInt(3);
		for(int i=0; i<9; i++){
			int tmp = 0;
			tmp = grid[i][firstCols1];
			grid[i][firstCols1] = grid[i][firstCols2];
			grid[i][firstCols2] = tmp;
		}
		
		int secondCols1 = rm.nextInt(3)+3;
		int secondCols2 = rm.nextInt(3)+3;
		for(int i=0; i<9; i++){
			int tmp = 0;
			tmp = grid[i][secondCols1];
			grid[i][secondCols1] = grid[i][secondCols2];
			grid[i][secondCols2] = tmp;
		}
			
		int thirdCols1 = rm.nextInt(3)+6;
		int thirdCols2 = rm.nextInt(3)+6;
		for(int i=0; i<9; i++){
			int tmp = 0;
			tmp = grid[i][thirdCols1];
			grid[i][thirdCols1] = grid[i][thirdCols2];
			grid[i][thirdCols2] = tmp;
		}
		
		//make sure to set answerGrid
		for(int i=0; i<9; i++){
			int[] subArray = grid[i];
			int subLength = subArray.length;
			answerGrid[i] = new int[subLength];
			System.arraycopy(subArray, 0, answerGrid[i], 0, subLength);
		}
		
		//make sure to start partially clearing
		partiallyClear();
		
	}
	
	private void setBoard(int seed){

		if(seed == 0){
			int[] r1 = {9,5,3,2,1,4,7,6,8};
			grid[0] = r1;
			int[] r2 = {2,7,6,8,5,3,4,1,9};
			grid[1] = r2;
			int[] r3 = {8,1,4,6,7,9,2,3,5};
			grid[2] = r3;
			int[] r4 = {7,4,8,5,3,1,6,9,2};
			grid[3] = r4;
			int[] r5 = {6,9,1,7,4,2,5,8,3};
			grid[4] = r5;
			int[] r6 = {5,3,2,9,6,8,1,7,4};
			grid[5] = r6;
			int[] r7 = {1,6,9,4,8,5,3,2,7};
			grid[6] = r7;
			int[] r8 = {3,2,5,1,9,7,8,4,6};
			grid[7] = r8;
			int[] r9 = {4,8,7,3,2,6,9,5,1};
			grid[8] = r9;
		}
			
		if(seed == 1){
			int[] r1 = {4,6,3,7,2,8,9,5,1};
			grid[0] = r1;
			int[] r2 = {2,5,9,4,6,1,7,3,8};
			grid[1] = r2;
			int[] r3 = {7,8,1,3,5,9,6,4,2};
			grid[2] = r3;
			int[] r4 = {5,3,2,1,9,7,4,8,6};
			grid[3] = r4;
			int[] r5 = {9,1,4,6,8,2,5,7,3};
			grid[4] = r5;
			int[] r6 = {6,7,8,5,4,3,1,2,9};
			grid[5] = r6;
			int[] r7 = {8,2,6,9,7,5,3,1,4};
			grid[6] = r7;
			int[] r8 = {1,4,7,2,3,6,8,9,5};
			grid[7] = r8;
			int[] r9 = {3,9,5,8,1,4,2,6,7};
			grid[8] = r9;
		}
			
		if(seed == 2){
			int[] r1 = {8,2,5,4,7,1,3,9,6};
			grid[0] = r1;
			int[] r2 = {1,9,4,3,2,6,5,7,8};
			grid[1] = r2;
			int[] r3 = {3,7,6,9,8,5,2,4,1};
			grid[2] = r3;
			int[] r4 = {5,1,9,7,4,3,8,6,2};
			grid[3] = r4;
			int[] r5 = {6,3,2,5,9,8,4,1,7};
			grid[4] = r5;
			int[] r6 = {4,8,7,6,1,2,9,3,5};
			grid[5] = r6;
			int[] r7 = {2,6,3,1,5,9,7,8,4};
			grid[6] = r7;
			int[] r8 = {9,4,8,2,6,7,1,5,3};
			grid[7] = r8;
			int[] r9 = {7,5,1,8,3,4,6,2,9};
			grid[8] = r9;
		}

	}

	
	private void partiallyClear(){
		Random myRan = new Random();
		myRan.setSeed(new Date().getTime());
		if(difficulty == 0){
			while(count > 40){
				int rowRem = myRan.nextInt(9);
				int colRem = myRan.nextInt(9);
				if(grid[rowRem][colRem] != 0){
					count--;
					grid[rowRem][colRem] = 0;
				}
			}
		}
		else{
			while(count > 27){
				int rowRem = myRan.nextInt(9);
				int colRem = myRan.nextInt(9);
				if(grid[rowRem][colRem] != 0){
					count--;
					grid[rowRem][colRem] = 0;
				}
			}
		}
		
	}
	
	public void play(){

		boolean gamemenu = true;
		
		while(gamemenu){
			printSudoku(grid);
			System.out.println("\n(1) Insert or Change Number");
			System.out.println("(2) Quit and See Answer");
		
			System.out.print("\nEnter number: ");

			int choice = scan.nextInt();
			
			if(choice == 1){
				System.out.print("\nEnter the number value you want to insert (1-9): ");
				int value = scan.nextInt();
				System.out.print("\nEnter the number row of the cell you want to insert into (0-8): ");
				int rowNum = scan.nextInt();
				System.out.print("\nEnter the number column of the cell you want to insert into (0-8): ");
				int colNum = scan.nextInt();
				
				if(grid[rowNum][colNum] == 0)
					count++;
				grid[rowNum][colNum] = value;
				
				if(count >= 81){
					boolean winner = true;
					for(int i=0; i<9; i++){
						for(int j=0; j<9; j++){
							if(grid[i][j] != answerGrid[i][j])
								winner = false;
						}
					}
					
					if(winner){
						System.out.println("CONGRATS YOU WON! HOORAY!!!!!");
						choice = 2;
					}
				}
			}
			
			if(choice == 2){
				printSudoku(answerGrid);
				gamemenu = false;
			}
			
			if((choice < 1) || (choice > 2)){
				System.out.println("Enter a valid integer");
			}
			
		}
		
		
	}
}
/**
 * James Lin
 * jl3782@columbia.edu
 * 3-1-14
 * 
 * Tester class for a simplified Sudoku system.
 *
 * Code was cleaned up to conform to Google Java style guide.
 */

import java.util.Scanner;

public class SudokuTester {
    
  public static void main(String[] args) {
        
    boolean menu = true;
    Scanner myScanner = new Scanner(System.in);

    while(menu) {
            
      System.out.println("Welcome to Sudoku!");
      System.out.println("\n(1) Play Easy Game");
      System.out.println("(2) Play Hard Game");
      System.out.println("(3) Quit");
      System.out.print("\nEnter number: ");
      
      int menuChoice = myScanner.nextInt();
      
      if(menuChoice == 1) {
        SudokuGrid game = new SudokuGrid(0);
        game.fillSudoku();
        game.play();
      }
      
      if(menuChoice == 2) {
        SudokuGrid game = new SudokuGrid(1);
        game.fillSudoku();
        game.play();
      }
      
      if(menuChoice == 3) {
        System.out.println("Play again sometime!");
        menu = false;
      }
      
      if((menuChoice < 1) || (menuChoice > 3)) {
        System.out.println("Enter a valid integer");
      }  
    }
  }
}
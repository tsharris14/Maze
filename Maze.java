/* 	Find and mark a path out of the maze represented by a 20x20 array of 0s (foot
	paths) and 1s (hedges). E is the only exit from the maze. You may move vertically or
	horizontally in any direction that contains a 0, you may not move diagonally or enter a
	square through an 1. If you are in a square with 1s on three sides, you must go back and
	seek another path. If you move into the square with the E, you will successfully exit the
	maze.
 */

import java.io.*;
import java.util.*;

public class Maze {
    public static String[][] createMatrixFromFile(String filename) throws Exception {
    	//initialize 2d array
    	String[][] matrix = null;
    	
    	//read in file
        FileReader file = new FileReader("C:\\Users\\T.S.H\\Downloads\\maze-input.txt");
		BufferedReader buffer = new BufferedReader(file);

        String line;
        int row = 0;
        int size = 0;

        //populate 2d matrix
        while ((line = buffer.readLine()) != null) {
            String[] vals = line.trim().split("");
            //System.out.println(Arrays.toString(vals));

            if (matrix == null) {
                size = vals.length;
                matrix = new String[size][size];
            }
            for (int col = 0; col < size; col++) {
                matrix[row][col] = (vals[col]);
            }

            row++;
        }
        return matrix;
    }
    
//mark starting point
public static void startingPoint(int r, int c, String[][] m) throws Exception {
	m[r][c] = "S";
	System.out.println("Starting Point: " + r + "," + c);
	printMatrix(m);
	findPath(r, c, m);
	m[r][c] = "S";
	printMatrix(m);
}

public static void findPath(int r, int c, String[][] m) {
	//initialize stack
	Stack<Integer> stack = new Stack<>(); 

	//store current position
	int currentRow = r;
	int currentCol = c;
	
	//push starting position
	stack.push(currentCol);
	stack.push(currentRow);

	//while there are still possible positions to move to
	while(stack.isEmpty() == false){
		currentRow = stack.pop();
		currentCol = stack.pop();
		System.out.println("Current Location: " + currentRow + "," + currentCol);
		if(currentRow == 0 && currentCol == 1) {
			m[currentRow][currentCol] = "x";
			System.out.println("I am free!");
			break;
		}
		
		m[currentRow][currentCol] = "x";
		//check for valid positions (0s)
		
		//left is a 0
		if(currentCol > 0) {
			if(m[currentRow][currentCol-1].equals("0")) {
				//push location to stack
				stack.push(currentCol-1);
				stack.push(currentRow);
			}
		}
		//down is a 0
		if(currentRow < m.length-1) {
			if(m[currentRow+1][currentCol].equals("0")) {
				//push location to stack
				stack.push(currentCol);
				stack.push(currentRow+1);
			}
		}
		//right is a 0
		if(currentCol < m.length-1) {
			if(m[currentRow][currentCol+1].equals("0")) {
				//push location to stack
				stack.push(currentCol+1);
				stack.push(currentRow);
			}
		}
		//up is a 0
		if(currentRow > 0) {
			if(m[currentRow-1][currentCol].equals("0")) {
				//push location to stack
				stack.push(currentCol);
				stack.push(currentRow-1);
			}		
		}
			
	}
	//System.out.println("Current Location: " + currentRow + "," + currentCol);
	
	//No path possible
	if(currentRow != 0 && currentCol != 1) {
		System.out.println("Help, I am trapped!");
	}
}
//print 2d array w/numbered rows & columns
public static void printMatrix(String[][] matrix) {
    System.out.printf("%-4s", "");
    for (int i = 0; i < matrix[0].length; i++) {
        System.out.printf("%-4d", i);
    }
    System.out.println();
    for (int i = 0; i < matrix.length; i++) {
        System.out.printf("%-4d", i);
        for (int j = 0; j < matrix[0].length; j++) {
            System.out.printf("%-4s", matrix[i][j]);
        }
        System.out.println();
    }  
    System.out.println("==================================================================================");
}
public static void main(String[] args) throws Exception {
	//initiate 2d array
    String[][] matrix = null;

    //create maze format
    try {
        matrix = createMatrixFromFile("square.txt");
        } catch (Exception e) {
            e.printStackTrace();
        }
    
    	//print initial maze without sp
    	printMatrix(matrix);
    	
    	//get row
        System.out.println("Enter a row: ");
    	Scanner sc = new Scanner(System.in);
    	int row = sc.nextInt();
    	//get column
    	System.out.println("Enter a column: ");
    	sc = new Scanner(System.in);
    	int col = sc.nextInt();

    	//mark S
		startingPoint(row, col, matrix);
		
	}	
}
	


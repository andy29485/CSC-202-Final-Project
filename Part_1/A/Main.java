import java.io.*;

//Andriy Zasypkin and Jason Tufano
//2015-11-19
//Final Project part 1A

/** Description:
  *   Write a program to read two 3x3 matrices, and compute the sum
  *   and the product of the two matrices. Then, compute the transpose matrix,
  *   cofactor matrix, and the determinant of the resultant matrix. Then find
  *   the inverse of the first matrix then multiply it by the first column of
  *   the second matrix to get a third matrix. Test the program using matrices
  *   given in class.
  *   
  *   In this program write a different method to perform the operations and
  *   use proper notation to pass the arguments and to access the elements.
  *   
  *   Use files for input and output. Make sure to use a class and objects.
  *   Use a constructor and (an) overloaded constructor(s) to initialize the
  *   object.
  *
  * Assumptions:
  *   - The matrices will be multiplied in the order they are read
  *   - Only the product matrix will have its transpose, cofactor, and
  *   	determinant taken
  *   - The "first matrix" will be the first matrix read, and the "second
  *   	matrix" will be the second matrix read, not any computed matrix.
  *   - The input file will be formatted for ease of use for the program
  *   	with the first line being rows, the second being columns, and each
  *   	subsequent line used to fill the array. There will be a blank line
  *   	between matrix data
  *   - The output file will be formatted for ease of viewing
  *   - The files will not be formatted extensively, most of the formatting
  *   	will be done in in Main when printed
  *   
  *   - User input will be the input and output file paths
  *   - The input file will already exist with matrices ready for the program
  *   - The output file will exist as a blank text file before the program runs
  *   - After each action, the result will be printed
  */

public class Main {
  public static void main(String[] args) throws IOException {
    //Creates an BufferedReader object
    BufferedReader input
            = new BufferedReader(new InputStreamReader(System.in));
    
    //Creates the input FileReader and BufferedReader
    System.out.println("Please enter the input file path: ");
    String s1 = input.readLine(); //Try-catch?
    FileReader fr = new FileReader(s1); //Could this be combined with line [x]?
    BufferedReader fileIn = new BufferedReader(fr);
    
    //Creates the output FileWriter and BufferedWriter
    System.out.println("Please enter the output file path: ");
    String s2 = input.readLine(); //Try-catch?
    FileWriter fw = new FileWriter(s2); //Could this be combined with line [x]?
    BufferedWriter fileOut = new BufferedWriter(fw);
    
    //Creates two Matrix objects, reads data from file
    Matrix A = new Matrix();
    A.fileInput(fileIn);
    fileIn.skip(1);
    Matrix B = new Matrix();
    B.fileInput(fileIn);
    
    //Creates Matrix objects to be created during testing of Matrix methods
    Matrix C = new Matrix();
    Matrix D = new Matrix();
    Matrix traD = new Matrix();
    Matrix cofD = new Matrix();
    Matrix invA = new Matrix();
    Matrix E = new Matrix();
    
    //Calculates matrices and values specified in the problem description
    C = A.add(B);
    D = A.multiply(B);
    traD = D.transpose();
    cofD = D.cofactor();
    double detD = D.det();
    invA = A.inverse();
    E = invA.multiply(B.colMatrix(0));
    
    //Prints out all calculations
    fileOut.write(C.toString());
    fileOut.newLine();
    fileOut.write(D.toString());
    fileOut.newLine();
    fileOut.write(traD.toString());
    fileOut.newLine();
    fileOut.write(cofD.toString());
    fileOut.newLine();
    fileOut.write("Determinant: "+detD+"\n");
    fileOut.newLine();
    fileOut.write(invA.toString());
    fileOut.newLine();
    fileOut.write(E.toString());
    fileOut.flush();
    fileOut.close();
    
    System.exit(0);
  }
}

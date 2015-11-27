//Andriy Zasypkin and Jason Tufano
//2015-11-19
//Final Project part 1A

import java.io.*;

public class Matrix {
  private double data[][];
  private int    rows;
  private int    cols;

  public Matrix() {
    this.data = new double[0][0];
    this.rows = 0;
    this.cols = 0;
  }

  public Matrix(int rows, int cols) {
    this.data = new double[rows][cols];
    this.rows = rows;
    this.cols = cols;
  }

  public Matrix(double data[][]) {
    this.data = data;
    this.rows = data.length;
    this.cols = this.rows > 0 ? data[0].length : 0;
  }

  public void setSize(int rows, int cols) {
    this.data = new double[rows][cols];
    this.rows = rows;
    this.cols = cols;
  }

  public int getRows() {
    return this.rows;
  }

  public int getColumns() {
    return this.cols;
  }

  public void set(double data[][]) {
    this.data = data;
    this.rows = data.length;
    this.cols = this.rows > 0 ? data[0].length : 0;
  }

  public double[][] get() {
    return this.data;
  }

  //return this array, but remove row r, and column c
  private Matrix remove(int r, int c) {
    Matrix A = new Matrix(this.rows-1, this.cols-1);
    for(int i=0; i<this.rows-1; i++) {
      for(int j=0; j<this.cols-1; j++) {
        A.set(i, j, this.get(i<r ? i : i+1, j<c ? j : j+1));
        //No need for output
      }
    }
    return A;
  }

  //return deturminant of this matrix
  public double det() {
    System.out.println("\nDet:");
    double d = det(0, "", 1, false, false);
    
    System.out.printf("  Det = %8.2f\n", d);
    return d;
  }
  
  private double det(int indent, String prefix, double mult,
                     boolean threeXthree, boolean bindent) {
    if(this.rows != this.cols)
      throw new RuntimeException("Illegal matrix dimensions");
    if(this.rows == 0) {
      for(int i=0; i<=indent; i++) {
        System.out.print("  ");
      }
      System.out.printf("det( [] ) = 1\n");
      return 1;
    }
    if(this.rows == 1) {
      for(int i=0; i<=indent; i++) {
        System.out.print("  ");
      }
      System.out.printf("det( [%8.2f] ) = %8.2f\n",
        this.get(0, 0), this.get(0, 0));
      return this.get(0, 0);
    }
    if(this.rows == 2) {
      for(int i=0; i<=indent; i++) {
        System.out.print("  ");
      }
      if(threeXthree)
        System.out.printf("%s((%8.2f * %8.2f) - (%8.2f * %8.2f))",
                 prefix,
                 this.get(0, 0), this.get(1, 1),
                 this.get(1, 0), this.get(0, 1),
                 (this.get(0, 0)*this.get(1, 1)-this.get(1, 0)*this.get(0, 1))*mult);
      else
        System.out.printf("det( %c ) = %s((%8.2f * %8.2f) - (%8.2f * %8.2f)) = %8.2f\n",
                 indent+'A' + (bindent ? -1 : 0),
                 prefix,
                 this.get(0, 0), this.get(1, 1),
                 this.get(1, 0), this.get(0, 1),
                 (this.get(0, 0)*this.get(1, 1)-this.get(1, 0)*this.get(0, 1))*mult);
      return this.get(0, 0)*this.get(1, 1) - this.get(1, 0)*this.get(0, 1);
    }

    double sum = 0;
    for(int i=0; i<this.rows; i++) {
      double a = ((i)%2 == 0 ? 1 : -1);
      double b = this.get(i, 0);
      for(int k=0; k<=indent; k++) {
        System.out.print("  ");
      }
      if(this.rows==3)
        System.out.printf("%c[%02d][%02d] = %s(-1^(%d))*(%8.2f *",
                        indent+'A', i+1, 1, prefix,
                        i+2, b, indent+'B');
      else
        System.out.printf("%c[%02d][%02d] = %s(-1^(%d))*(%8.2f*det( %c )) = ?\n",
                        indent+'A', i+1, 1, prefix,
                        i+2, b, indent+'B');
      double c = this.remove(i, 0).det(indent+1, "", 1, this.rows==3, false);
      for(int k=0; k<indent+1; k++) {
        System.out.print("  ");
      }
      if(this.rows==3)
        System.out.printf(") = %8.2f\n",
                          a*b*c*mult);
      else
        System.out.printf("? = %8.2f\n",
                          a*b*c*mult);
      sum += a*b*c;
    }
    return sum;
  }

  //returns the transpose of the cofactor(adjoint)
  public Matrix adj() {
    System.out.println("\nAdj: cofactor(A)^T");
    return this.cofactor().transpose();
  }

  public void input(BufferedReader input) throws IOException {
    System.out.print("Rows in matrix A: ");//get rows
    int rows = Integer.valueOf(input.readLine()).intValue();
    System.out.print("Columns in matrix A: ");//get columns
    int cols = Integer.valueOf(input.readLine()).intValue();
    this.setSize(rows, cols);//and create empty
    //fill
    for(int i=0; i<rows; i++) {
      for(int j=0; j<cols; j++) {
        System.out.printf("A[%02d][%02d] = ", i+1, j+1);
        this.set(i, j, Double.valueOf(input.readLine()).doubleValue());
      }
    }
  }

  //return cofactor of this matrix
  public Matrix cofactor() {
    System.out.println("\nCofactor:");
    if(this.rows != this.cols)
      throw new RuntimeException("Illegal matrix dimensions");
    Matrix A = new Matrix(this.rows, this.cols);
    for (int i=0; i<this.rows; i++) {
      for (int j=0; j<this.cols; j++) {
        
        double d = this.remove(i, j).det(1,
                                         String.format("(-1^(%d))*", i+j+2),
                                         (i+j)%2 == 0 ? 1 : -1, false, true);
        A.set(j, i, ((i+j)%2 == 0 ? 1 : -1) * d);
      }
    }
    return A;
  }

  //return inverse of this matrix
  public Matrix inverse() {
    if(this.rows != this.cols) //A^-1 = A.transpose()*(A*A^T)^-1
      return this.transpose().multiply(this.multiply(this.transpose()).inverse());
    
    Matrix tmp         = this.adj();
    double deturminant = this.det();
    System.out.println("\nInverse: adj(A)/det(A):");
    tmp.print("/"+deturminant);
    
    return tmp.divide(deturminant);
  }

  //return the matrix of minors
  public Matrix minors() {
    System.out.println("\nMinors:");
    if(this.rows != this.cols)
      throw new RuntimeException("Illegal matrix dimensions");
    Matrix A = new Matrix(this.rows, this.cols);
    for(int i=0; i<this.rows; i++) {
      for(int j=0; j<this.cols; j++) {
        A.set(i, j, this.remove(i, j).det(1, "", 1, false, false));
      }
    }
    return A;
  }

  //set specified /thing/ in matrix to 'value' //TODO correct '/thing/'
  public void set(int i, int j, double value) {
    if(i >= this.rows || j >= this.cols)
      throw new RuntimeException("Illegal matrix coordinates");
    this.data[i][j] = value;
  }

  //get value of specified /thing/ from matrix //TODO correct '/thing/'
  public double get(int i, int j) {
    if(i >= this.rows || j >= this.cols)
      throw new RuntimeException("Illegal matrix coordinates");
    return data[i][j];
  }

  //add A to this matrix
  public Matrix add(Matrix A) {
    if(A.getRows() != this.rows || A.getColumns() != this.cols)
      throw new RuntimeException("Illegal matrix dimensions");
    Matrix B = new Matrix(this.rows, this.cols);
    for(int i=0; i<this.rows; i++)
      for(int j=0; j<this.cols; j++)
        B.set(i, j, this.get(i, j) + A.get(i, j));
    return B;
  }

  //transpose this matrix(do not modify, return transposed)
  public Matrix transpose() {
    Matrix A = new Matrix(this.cols, this.rows);
    for(int i=0; i<this.rows; i++)
      for(int j=0; j<this.cols; j++)
        A.set(j, i, this.get(i, j));
    return A;
  }

  //subtract A from this matrix
  public Matrix subtract(Matrix A) {
    if(A.getRows() != this.rows || A.getColumns() != this.cols)
      throw new RuntimeException("Illegal matrix dimensions");
    Matrix B = new Matrix(this.rows, this.cols);
    for(int i=0; i<this.rows; i++)
      for(int j=0; j<this.cols; j++)
        B.set(i, j, this.get(i, j) - A.get(i, j));
    return B;
  }

  //multiply this by A(matrix)
  public Matrix multiply(Matrix A) {
    System.out.println("\nMultiply by matrix:");
    //After reading the problem statement I realized that this was unnecessary
    if(this.cols != A.getRows())
      throw new RuntimeException("Illegal matrix dimensions");
    Matrix B = new Matrix(this.rows, A.getColumns());
    for(int i=0; i<B.getRows(); i++)
      for(int j=0; j<B.getColumns(); j++) {
        for(int k=0; k<this.cols; k++) {
          System.out.printf("  C[%02d][%02d] = %8.2f + %8.2f *%8.2f = %8.2f\n",
            i+1, j+1, B.get(i, j), this.get(i, k), A.get(k, j),
            B.get(i, j) + this.get(k, i)*A.get(k, j));
          B.set(i, j, B.get(i, j) + this.get(k, i)*A.get(k, j));
        }
        System.out.println();
      }
    return B;
  }

  //multiply this by k(scalar)
  public Matrix multiply(double k) {
    Matrix A = new Matrix(this.rows, this.cols);
    for(int i=0; i<this.rows; i++)
      for(int j=0; j<this.cols; j++)
        A.set(i, j, this.get(i, j) * k);
    return A;
  }

  //divide this by k(scalar)
  public Matrix divide(double k) {
    Matrix A = new Matrix(this.rows, this.cols);
    for(int i=0; i<this.rows; i++)
      for(int j=0; j<this.cols; j++)
        A.set(i, j, this.get(i, j) / k);
    return A;
  }

  //OUTPUT
  // print the matrix
  public void print() {
    System.out.print(this.toString());
  }
  
  public void print(String sufix) {
    System.out.print(this.toString(sufix));
  }
  
  public String toString() {
    return toString("");
  }
  
  public String toString(String sufix) {
    String out = "";
    for(int i=0; i<this.rows; i++) {
      out += "|";
      for(int j=0; j<this.cols; j++)
        out += String.format("%7.2f%s ", this.get(i, j),  sufix);
      out += "|\n";
    }
    return out;
  }
}

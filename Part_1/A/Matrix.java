//Andriy Zasypkin and Jason Tufano
//2015-11-19
//Final Project part 1A

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
  
  //return this array, but remove row r, and column c
  private Matrix remove(int r, int c) {
    Matrix A = new Matrix(this.rows-1, this.cols-1);
    for(int i=0; i<this.rows-1; i++) {
      for(int j=0; j<this.cols-1; j++) {
        A.set(i, j, this.get(i<r ? i : i+1, j<c ? j : j+1));
      }
    }
  }
  
  //return deturminant of this matrix
  public double det() {
    if(this.rows != this.cols)
      throw new RuntimeException("Illegal matrix dimensions");
    if(this.rows == 0)
      return 1;
    if(this.rows == 1)
      return this.get(0, 0);
    if(this.rows == 2)
      return this.get(0, 0)*this.get(1, 1) - this.get(1, 0)*this.get(0, 1);
    
    double sum = 0;
    for(int i=0; i<this.rows; i++) {
      for(int j=0; j<this.cols; j++) {
        sum += ((i+j)%2 == 0 ? 1 : -1) * this.get(i, j) * this.remove(i, j).det();
      }
    }  
  }
  
  public void set(int i, int j, double x) {
    if(i >= this.rows || j >= this.cols)
      throw new RuntimeException("Illegal matrix coordinates");
    this.data[i][j] = x;
  }
  
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
        A.set(j, i, this.get(i, j);
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
    //After reading the problem statement I realized that this was unnecessary
    if(this.rows != A.getColumns())
      throw new RuntimeException("Illegal matrix dimensions");
    Matrix B = new Matrix(this.rows, A.getColumns());
    for(int i=0; i<B.getRows(); i++)
      for(int j=0; j<B.getColumns(); j++)
        for(int k=0; k<this.cols; k++)
          B.set(i, j, B.get(i, j) + this.get(i, k)*A.get(k, j));
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

  //OUTPUT
  // print the matrix
  public void print() {
    for(int i=0; i<this.rows; i++) {
      System.out.print("  |");
      for(int j=0; j<this.cols; j++) 
        System.out.printf("%7.2f ", this.get(i, j));
      System.out.println("|");
    }
  }
}

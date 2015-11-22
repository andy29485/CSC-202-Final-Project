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
    return A;
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
      sum += ((i)%2 == 0 ? 1 : -1) * this.get(i, 0) * this.remove(i, 0).det();
    }
  }

  //returns the transpose of the cofactor(adjoint)
  public Matrix adj() {
    return this.cofactor().transpose();
  }

  //return cofactor of this matrix
  public Matrix cofactor() {
    if(this.rows != this.cols)
      throw new RuntimeException("Illegal matrix dimensions");
    Matrix A = new Matrix(this.rows, this.cols);
    for (int i=0; i<this.rows; i++) {
      for (int j=0; j<this.cols; j++) {
        A.set(j, i, ((i+j)%2 == 0 ? 1 : -1) * this.remove(i, j).det());
      }
    }
    return A;
  }

  //return inverse of this matrix
  public Matrix inverse() {
    if(this.rows != this.cols) //A^-1 = A.transpose()*(A*A^T)^-1
      return this.transpose().multiply(this.multiply(this.transpose()).inverse());
    return this.adj()/this.det();;
  }

  //return the matrix of minors
  public Matrix minors() {
    if(this.rows != this.cols)
      throw new RuntimeException("Illegal matrix dimensions");
    Matrix A = new Matrix(this.rows, this.cols);
    for(int i=0; i<this.rows; i++) {
      for(int j=0; j<this.cols; j++) {
        A.set(i, j, this.remove(i, j).det());
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
    //After reading the problem statement I realized that this was unnecessary
    if(this.cols != A.getRows())
      throw new RuntimeException("Illegal matrix dimensions");
    Matrix B = new Matrix(this.rows, A.getColumns());
    for(int i=0; i<B.getRows(); i++)
      for(int j=0; j<B.getColumns(); j++)
        for(int k=0; k<this.cols; k++)
          B.set(i, j, B.get(i, j) + this.get(k, i)*A.get(k, j));
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

  //divide this by A(matrix)
  public Matrix divide(Matrix A) {
    return this.multiply(A.inverse());
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
    for(int i=0; i<this.rows; i++) {
      System.out.print("  |");
      for(int j=0; j<this.cols; j++)
        System.out.printf("%7.2f ", this.get(i, j));
      System.out.println("|");
    }
  }
}

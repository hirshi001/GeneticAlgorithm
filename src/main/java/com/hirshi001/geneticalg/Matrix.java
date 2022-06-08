package com.hirshi001.geneticalg;

public class Matrix{
    double [][] data;
    int rows, cols;

    public Matrix(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        data = new double[rows][cols];
    }

    public Matrix(Matrix m) {
        this.rows = m.rows;
        this.cols = m.cols;
        data = new double[rows][cols];

        for(int i = 0; i < rows; i++) {
            if (cols >= 0) System.arraycopy(m.data[i], 0, data[i], 0, cols);
        }
    }

    public Matrix randomize() {
        return randomize(1);
    }

    public Matrix randomize(double randomChange) {
        double r2 = randomChange * 2;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                data[i][j] += MatrixUtil.clamp(Math.random()*r2-randomChange,-1,1);
            }
        }
        return this;
    }

    public Matrix add(double scalar){
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                data[i][j] += scalar;
            }
        }
        return this;
    }

    public Matrix add(Matrix m){
        if(rows != m.rows || cols != m.cols)
            throw new RuntimeException("Matrix dimensions do not match");
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                data[i][j] += m.data[i][j];
            }
        }
        return this;
    }

    public Matrix subtract(double scalar){
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                data[i][j] -= scalar;
            }
        }
        return this;
    }

    public Matrix subtract(Matrix m){
        if(rows != m.rows || cols != m.cols)
            throw new RuntimeException("Matrix dimensions do not match");
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                data[i][j] -= m.data[i][j];
            }
        }
        return this;
    }

    public void multiply(Matrix a) {
        for(int i=0;i<a.rows;i++)
        {
            for(int j=0;j<a.cols;j++)
            {
                this.data[i][j]*=a.data[i][j];
            }
        }

    }

    public void multiply(double a) {
        for(int i=0;i<rows;i++)
        {
            for(int j=0;j<cols;j++)
            {
                this.data[i][j]*=a;
            }
        }

    }

    public void sigmoid(){
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                data[i][j] = 1/(1+Math.exp(-data[i][j]));
            }
        }
    }

    public Matrix dsigmoid(){
        Matrix temp = new Matrix(rows, cols);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                temp.data[i][j] = data[i][j]*(1-data[i][j]);
            }
        }
        return temp;
    }


    public double[] toArray(){
        double[] a = new double[rows*cols];
        int index = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                a[index] = data[i][j];
                index++;
            }
        }
        return a;
    }

}

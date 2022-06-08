package com.hirshi001.geneticalg;

public class MatrixUtil {


    public static Matrix add(Matrix a, Matrix b){
        return new Matrix(a).add(b);
    }

    public static Matrix subtract(Matrix a, Matrix b){
        return new Matrix(a).subtract(b);
    }

    public static Matrix transpose(Matrix a){
        Matrix m = new Matrix(a.cols, a.rows);
        for (int i = 0; i < a.rows; i++) {
            for (int j = 0; j < a.cols; j++) {
                m.data[j][i] = a.data[i][j];
            }
        }
        return m;
    }


    public static Matrix multiply(Matrix a, Matrix b) {
        Matrix temp=new Matrix(a.rows,b.cols);
        for(int i=0;i<temp.rows;i++)
        {
            for(int j=0;j<temp.cols;j++)
            {
                double sum=0;
                for(int k=0;k<a.cols;k++)
                {
                    sum+=a.data[i][k]*b.data[k][j];
                }
                temp.data[i][j]=sum;
            }
        }
        return temp;
    }


    public static Matrix fromArray(double[] a){
        Matrix m = new Matrix(a.length, 1);
        for (int i = 0; i < a.length; i++) {
            m.data[i][0] = a[i];
        }
        return m;
    }

    public static double clamp(double value, double min, double max){
        return Math.max(min, Math.min(max, value));
    }

    public static <T extends Comparable<T>> T clamp(T val, T min, T max) {
        return val.compareTo(min) < 0 ? min : val.compareTo(max) > 0 ? max : val;
    }
}

package com.hirshi001.geneticalg;

public class NeuralNetwork {
    Matrix weights_ih, weights_ho, bias_h, bias_o;
    final double l_rate;

    public NeuralNetwork(int i, int h, int o, double l_rate) {
        weights_ih = new Matrix(h, i);
        weights_ih.randomize();

        weights_ho = new Matrix(o, h);
        weights_ho.randomize();

        bias_h = new Matrix(h, 1);
        bias_h.randomize();

        bias_o = new Matrix(o, 1);
        bias_o.randomize();

        this.l_rate = l_rate;
    }

    public NeuralNetwork(NeuralNetwork neuralNetwork, double randomChange){
        this.weights_ih = new Matrix(neuralNetwork.weights_ih);
        this.weights_ih.randomize(randomChange);

        this.weights_ho = new Matrix(neuralNetwork.weights_ho);
        this.weights_ho.randomize(randomChange);

        this.bias_h = new Matrix(neuralNetwork.bias_h);
        this.bias_h.randomize(randomChange);

        this.bias_o = new Matrix(neuralNetwork.bias_o);
        this.bias_o.randomize(randomChange);

        this.l_rate = neuralNetwork.l_rate;
    }

    public double[] predict(double[] x){
        Matrix input = MatrixUtil.fromArray(x);
        Matrix hidden = MatrixUtil.multiply(weights_ih, input);
        hidden.add(bias_h);
        hidden.sigmoid();

        Matrix output = MatrixUtil.multiply(weights_ho, hidden);
        output.add(bias_o);
        output.sigmoid();

        return output.toArray();
    }

    public void train(double [] X,double [] Y)
    {
        Matrix input = MatrixUtil.fromArray(X);
        Matrix hidden = MatrixUtil.multiply(weights_ih, input);
        hidden.add(bias_h);
        hidden.sigmoid();

        Matrix output = MatrixUtil.multiply(weights_ho,hidden);
        output.add(bias_o);
        output.sigmoid();

        Matrix target = MatrixUtil.fromArray(Y);

        Matrix error = MatrixUtil.subtract(target, output);
        Matrix gradient = output.dsigmoid();
        gradient.multiply(error);
        gradient.multiply(l_rate);

        Matrix hidden_T = MatrixUtil.transpose(hidden);
        Matrix who_delta =  MatrixUtil.multiply(gradient, hidden_T);

        weights_ho.add(who_delta);
        bias_o.add(gradient);

        Matrix who_T = MatrixUtil.transpose(weights_ho);
        Matrix hidden_errors = MatrixUtil.multiply(who_T, error);

        Matrix h_gradient = hidden.dsigmoid();
        h_gradient.multiply(hidden_errors);
        h_gradient.multiply(l_rate);

        Matrix i_T = MatrixUtil.transpose(input);
        Matrix wih_delta = MatrixUtil.multiply(h_gradient, i_T);

        weights_ih.add(wih_delta);
        bias_h.add(h_gradient);

    }

    public void fit(double[][]X,double[][]Y,int epochs)
    {
        for(int i=0;i<epochs;i++)
        {
            int sampleN =  (int)(Math.random() * X.length );
            this.train(X[sampleN], Y[sampleN]);
        }
    }


}

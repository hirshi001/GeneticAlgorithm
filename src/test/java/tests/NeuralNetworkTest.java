package tests;

import com.hirshi001.geneticalg.Chromosome;
import com.hirshi001.geneticalg.Population;
import org.junit.jupiter.api.Test;
import com.hirshi001.geneticalg.NeuralNetwork;

import java.util.Arrays;
import java.util.function.Function;

public class NeuralNetworkTest {


    @Test
    public void test(){
        double[][] X = {{0,0},{0,1},{1,0},{1,1}};
        double[][] Y = {{0},{1},{1},{0}};

        NeuralNetwork nn = new NeuralNetwork(2,10,1, 0.05);
        nn.fit(X, Y, 100); //No need to fit if using genetic algorithm

        double [][] input = {{0,0}, {0,1}, {1,0}, {1,1}};
        for(double d[] : input){
            System.out.println(Arrays.toString(nn.predict(d)));
        }
    }

    @Test
    public void geneticAlgTest(){

        double[][] X = {{1,0,0}, {0,1,0}, {0,0,1}};
        double[][] Y = {{0,1, 0}, {0,0,1}, {1,0,0}};

        Function<NeuralNetwork, Double> fitnessFunction = (nn) -> {
            double error = 0;
            for(int i = 0; i < X.length; i++){
                double[] output = nn.predict(X[i]);
                for(int j = 0; j < output.length; j++){
                    error += Math.pow(output[j] - Y[i][j], 2);
                }
            }
            return -error;
        };

        Population p = new Population(2);
        p.initializePopulation(3, 5, 3, 0.05, fitnessFunction);
        p.calculateFitness();
        p.sortChromosomesByFitness();

        for(int i=0;i<1000;i++){
            Chromosome starter = p.getChromosomes()[0];
            p.reinitializePopulation(starter, 0.01, fitnessFunction);
            p.calculateFitness();
            p.sortChromosomesByFitness();
            if(i%20==0) {
                System.out.println("1." + Arrays.toString(p.getChromosomes()[0].getNeuralNetwork().predict(X[0])));
                System.out.println("2." + Arrays.toString(p.getChromosomes()[0].getNeuralNetwork().predict(X[1])));
                System.out.println("3." + Arrays.toString(p.getChromosomes()[0].getNeuralNetwork().predict(X[2])));
            }
        }

       // System.out.println(Arrays.toString(p.getChromosomes()));
    }
}

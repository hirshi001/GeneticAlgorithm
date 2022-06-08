package com.hirshi001.geneticalg;

import java.util.function.Function;

public class Chromosome {

    NeuralNetwork neuralNetwork;
    double fitness;
    Function<NeuralNetwork, Double> fitnessFunction;

    public Chromosome(Function<NeuralNetwork, Double> fitnessFunction) {
        this.fitnessFunction = fitnessFunction;
    }

    public void initializeChromosome(int i, int h, int o, double l_rate){
        neuralNetwork = new NeuralNetwork(i, h, o, l_rate);
    }

    public void initializeChromosome(Chromosome chromosome, double weightChange){
        this.neuralNetwork = new NeuralNetwork(chromosome.getNeuralNetwork(), weightChange);
    }

    public NeuralNetwork getNeuralNetwork() {
        return neuralNetwork;
    }

    public void setFitness(double fitness) {
        this.fitness = fitness;
    }

    public double getFitness() {
        return fitness;
    }


    public void calculateFitness(){
       fitness = fitnessFunction.apply(neuralNetwork);
    }

    @Override
    public String toString() {
        return "Fitness="+ fitness;
    }
}

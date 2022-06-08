package com.hirshi001.geneticalg;

import java.util.Arrays;
import java.util.function.Function;

public class Population {

    private Chromosome[] chromosomes;
    private int populationSize;

    public Population(int populationSize) {
        this.populationSize = populationSize;
        chromosomes = new Chromosome[populationSize];

    }

    public void initializePopulation(int input, int hidden, int output, double l_rate, Function<NeuralNetwork, Double> fitnessFunction) {
        for (int i = 0; i < populationSize; i++) {
            chromosomes[i] = new Chromosome(fitnessFunction);
            chromosomes[i].initializeChromosome(input, hidden, output, l_rate);
        }
    }

    public void reinitializePopulation(Chromosome starter, double weightChange, Function<NeuralNetwork, Double> fitnessFunction) {
        for (int i = 0; i < populationSize; i++) {
            chromosomes[i] = new Chromosome(fitnessFunction);
            chromosomes[i].initializeChromosome(starter, weightChange);
        }
    }

    public void calculateFitness() {
        for (int i = 0; i < populationSize; i++) {
            chromosomes[i].calculateFitness();
        }
    }


    public Chromosome[] getChromosomes() {
        return chromosomes;
    }

    public void sortChromosomesByFitness(){
        Arrays.sort(chromosomes, (c1, c2) -> Double.compare(c2.getFitness(), c1.getFitness()));
    }




}

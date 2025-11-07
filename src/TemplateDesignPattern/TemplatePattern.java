package TemplateDesignPattern;
// ----------------
// Base class defining the template method
//-----------------

import java.sql.SQLOutput;

abstract class ModelTrainer{
    // the template method - final so subclasses can not change the sequence
    public final void trainPipline(String dataPath){
        loadData(dataPath);
        preprocessData();
        trainModel();     // subclass-specific
        evaluateModel();  // subclass-specific
        saveModel();   // sbuclass- specific or default
    }
    protected void loadData(String path){
        System.out.println("[Common] Loading dataset from "+path);

    }

    protected  void preprocessData(){
        System.out.println("[Common] Splitting into train/test and noramldata");
    }

    protected abstract void trainModel();
    protected abstract void evaluateModel();

    // provide a default save,but subclasses can override if needed

    protected void saveModel(){
        System.out.println("[Common] saving model to disk a default form");
    }

}

// 2. Concrete subclass: Neural network

class NeuralNetworkTrainer extends ModelTrainer{
    protected  void trainModel(){
        System.out.println("[NeuralNet] training neural network for 100");
    }

    protected void evaluateModel(){
        System.out.println("[NeuralNet] Evaluating accuracy and loss on validation set");
    }

    protected void saveModel(){
        System.out.println("[NeuralNet] Serialzing network weights to .h5 file");
    }

}

// 3. concrete subclass: decision tree

class DecisionTreeTrainer extends ModelTrainer{
    protected void trainModel(){
        System.out.println("[DecisionTree] Building decision tree with max_depth=5 ");
    }

    protected void evaluateModel(){
        System.out.println("[DecisionTree] Computing classification reporting");
    }

}


public class TemplatePattern {
    public static void main(String [] args){
        System.out.println("=====Neural Network Training ===");
        ModelTrainer nnTrainer =new NeuralNetworkTrainer();
        nnTrainer.trainPipline("data/images/");

        System.out.println("\n === Decision Tree Taining ===");
        ModelTrainer dtTrainer= new DecisionTreeTrainer();
        dtTrainer.trainPipline("data/iris.csv");
    }
}

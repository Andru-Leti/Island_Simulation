package service;

import model.animals.TickCounters;

public class PrintStatistic {
    public static void print(){
        System.out.println("Количество съеденных" + TickCounters.eatenAnimals);
    }
}

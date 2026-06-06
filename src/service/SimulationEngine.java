package service;

import model.animals.TickCounters;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class SimulationEngine {
    LifeCycle lifeCycle;

    ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor(r -> {
        Thread thread = new Thread(r, "Поток запуска симуляции");
        return thread;
    });

    public void start() {
        System.out.println("Поток запущен");

        scheduledExecutorService.scheduleAtFixedRate(() -> {
            try {
                TickCounters.reset();
                lifeCycle.runTick();
                PrintStatistic.print();
                // необходимо выводить статистику
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }, 0, 0L, TimeUnit.MILLISECONDS);
    }

    public void stop(){
        // продумать как остановить shutdown
    }
}

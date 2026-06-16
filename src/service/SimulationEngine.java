package service;

import model.Island;
import model.animals.TickCounters;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class SimulationEngine {

    private final Island island;
    private final LifeCycle lifeCycle;
    private final ScheduledExecutorService scheduledExecutorService;
    private final int tickDelayMs = 1000;
    private int currentTick = 0;

    public SimulationEngine() {
        WorldFactory worldFactory = new WorldFactory();
        this.island = worldFactory.createWorld(
                SimulationSettings.WIDTH,
                SimulationSettings.HEIGHT
        );

        EatService eatService = new EatService();
        MoveService moveService = new MoveService(island);
        ReproduceService reproduceService = new ReproduceService();

        this.lifeCycle = new LifeCycle(
                island,
                SimulationSettings.THREAD_POOL_SIZE,
                SimulationSettings.GRASS_GROWTH_PER_TICK,
                eatService,
                moveService,
                reproduceService
        );

        this.scheduledExecutorService = Executors.newSingleThreadScheduledExecutor(r -> {
            Thread thread = new Thread(r, "Симуляция-поток");
            return thread;
        });
    }

    public void start() {
        System.out.println("   СИМУЛЯЦИЯ ЗАПУЩЕНА");
        System.out.println("Остров: " + island.getWidth() + "x" + island.getHeight());
        System.out.println("Задержка между тактами: " + tickDelayMs + " мс");
        System.out.println();

        scheduledExecutorService.scheduleAtFixedRate(() -> {
            try {
                if (currentTick >= SimulationSettings.TICKS_BEFORE_SHUTDOWN) {
                    stop();
                    return;
                }

                currentTick++;
                System.out.println("\n    Такт " + currentTick);

                TickCounters.reset();

                lifeCycle.runTick();

                PrintStatistic.print(island);

                if (island.getTotalAnimals() == 0) {
                    System.out.println(" Все животные умерли. Симуляция остановлена.");
                    stop();
                }

            } catch (Exception e) {
                System.err.println("Ошибка в такте симуляции: " + e.getMessage());
                e.printStackTrace();
                stop();
            }
        }, 0, tickDelayMs, TimeUnit.MILLISECONDS);
    }

    public void stop() {
        System.out.println("\n   ОСТАНОВКА СИМУЛЯЦИИ ");

        scheduledExecutorService.shutdown();
        try {
            if (!scheduledExecutorService.awaitTermination(5, TimeUnit.SECONDS)) {
                scheduledExecutorService.shutdownNow();
            }
        } catch (InterruptedException e) {
            scheduledExecutorService.shutdownNow();
            Thread.currentThread().interrupt();
        }

        lifeCycle.shutdown();
        System.out.println("Симуляция завершена.");
        System.exit(0);
    }

}
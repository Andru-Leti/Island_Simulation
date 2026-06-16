
package service;

public class Main {
    static void main(String[] args) {
        SimulationEngine engine = new SimulationEngine();
        engine.start();

        try {
            Thread.sleep(Long.MAX_VALUE);
        } catch (InterruptedException e) {
            engine.stop();
        }
    }
}
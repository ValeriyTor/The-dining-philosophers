public class Main {
    public static void main(String[] args) {

        Philosopher[] philosophers = new Philosopher[5];
        Boolean[] forks = new Boolean[philosophers.length];

        for (int i = 0; i < forks.length; i++) {
            forks[i] = false;
        }

        for (int i = 0; i < philosophers.length; i++) {
            boolean leftFork = forks[i];
            boolean rightFork = forks[(i + 1) % forks.length];
            philosophers[i] = new Philosopher(leftFork, rightFork);
            Thread t = new Thread(philosophers[i], "Philosopher " + (i + 1));
            t.start();
        }

    }
}


public class Philosopher implements Runnable {


    private boolean leftFork = false;
    private boolean rightFork = false;

    public Philosopher(boolean leftFork, boolean rightFork) {
        this.leftFork = leftFork;
        this.rightFork = rightFork;
    }

    public void think() {
        try {
            System.out.println(Thread.currentThread().getName() + " thinks");
            Thread.sleep((int) (Math.random() * 1000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public synchronized void eat() throws InterruptedException {
        if (!leftFork) {
            if (!rightFork) {
                leftFork = true;
                System.out.println(Thread.currentThread().getName() + " picked up left fork");
                rightFork = true;
                System.out.println( Thread.currentThread().getName() + " picked up right fork");
                System.out.println(Thread.currentThread().getName() + " eats");
                Thread.sleep((int) (Math.random() * 1000));
                System.out.println(Thread.currentThread().getName() + " put left fork");
                System.out.println(Thread.currentThread().getName() + " put right fork");
                leftFork = false;
                rightFork = false;
                notifyAll();
            } else {
                leftFork = false;
                wait((int) (Math.random() * 1000));
            }
        }
    }

    @Override
    public void run() {
        while (true) {
            think();
            try {
                eat();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            think();
        }
    }
}



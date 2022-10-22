public class Philosopher implements Runnable {


    private Object leftFork;
    private Object rightFork;

    public Philosopher(Object leftFork, Object rightFork) {
        this.leftFork = leftFork;
        this.rightFork = rightFork;
    }

    public void think() {
        try {
            System.out.println(Thread.currentThread().getName() + " is thinking");
            Thread.sleep((int) (Math.random() * 100));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public void takeLeftFork() throws InterruptedException {
                System.out.println(Thread.currentThread().getName() + " picked up left fork");
                Thread.sleep(((int) (Math.random() * 100)));
            }
    public void takeRightFork() throws InterruptedException {
        System.out.println(Thread.currentThread().getName() + " picked up right fork");
        System.out.println(Thread.currentThread().getName() + " is eating");
        Thread.sleep(((int) (Math.random() * 100)));
    }
    public void putLeftFork() throws InterruptedException {
        System.out.println(Thread.currentThread().getName() + " put left fork");
        Thread.sleep(((int) (Math.random() * 100)));
    }
    public void putRightFork() throws InterruptedException {
        System.out.println(Thread.currentThread().getName() + " put right fork");
        Thread.sleep(((int) (Math.random() * 100)));
    }

    @Override
    public void run() {
        try {
            while (true) {
                think();
                synchronized (leftFork) {
                    takeLeftFork();
                    synchronized (rightFork) {
                        takeRightFork();
                        putRightFork();
                    }
                    putLeftFork();
                }
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}



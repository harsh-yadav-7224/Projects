class Restaurant {

    boolean orderReady = false;

    synchronized void placeOrder(String order) {
        try {
            while (orderReady) {
                wait(); // wait if kitchen is busy
            }

            System.out.println("Waiter placed order: " + order);
            orderReady = true;

            notify(); // notify chef

        } catch (InterruptedException e) {
            System.out.println(e);
        }
    }

    // Chef prepares order
    synchronized void prepareOrder() {
        try {
            while (!orderReady) {
                wait(); // wait if no order
            }

            System.out.println("Chef is preparing order...");
            Thread.sleep(2000); // simulate cooking

            System.out.println("Chef delivered the order\n");
            orderReady = false;

            notify(); // notify waiter

        } catch (InterruptedException e) {
            System.out.println(e);
        }
    }
}

class Waiter extends Thread {
    Restaurant r;

    Waiter(Restaurant r) {
        this.r = r;
    }

    public void run() {
        String[] orders = { "Pie", "Rice", "Pasta" };

        for (String order : orders) {
            r.placeOrder(order);
        }
    }
}

class Chef extends Thread {
    Restaurant r;

    Chef(Restaurant r) {
        this.r = r;
    }

    public void run() {
        for (int i = 0; i < 3; i++) {
            r.prepareOrder();
        }
    }
}

public class RestaurantDemo {
    public static void main(String[] args) {

        Restaurant r = new Restaurant();

        Waiter w = new Waiter(r);
        Chef c = new Chef(r);

        w.start();
        c.start();
    }
}
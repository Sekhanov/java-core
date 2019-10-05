package blockthreads;

import lombok.Setter;

/**
 * ShowMessageThread
 */
public class ShowMessageThread implements Runnable {

    private ShowMessageMonitor monitor;
    private String message;
    @Setter
    private boolean isBlocked = true;

    public ShowMessageThread(ShowMessageMonitor monitor, String message) {
        this.monitor = monitor;
        this.message = message;
        new Thread(this, "Th-" + message).start();
    }

    @Override
    public void run() {
        while (true) {
            synchronized (monitor) {
                while (isBlocked) {
                    try {
                        monitor.wait(100);                        
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.print(message);
                this.isBlocked = true;
                monitor.releaseNextThread(this);   
                           
            }
        }
    }

    

    
}
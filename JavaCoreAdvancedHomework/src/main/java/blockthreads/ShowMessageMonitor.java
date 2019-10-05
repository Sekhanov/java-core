package blockthreads;

import java.util.List;

/**
 * Monitor
 */
public class ShowMessageMonitor {

    private List<ShowMessageThread> listOfThreads;

    public ShowMessageMonitor(List<ShowMessageThread> mapOfThreads) {
        this.listOfThreads = mapOfThreads;
    }

    public void releaseNextThread(ShowMessageThread showMessageThread) {
        int nextIndex = listOfThreads.indexOf(showMessageThread) + 1;
        if (nextIndex == listOfThreads.size()) {
            nextIndex = 0;
        }
        listOfThreads.get(nextIndex).setBlocked(false);
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        this.notifyAll();
    }
    
    public void releaseFirstThread() {
        listOfThreads.get(0).setBlocked(false);
        // this.notifyAll();
    }
}
package in.obvious.assignments.cosmos.framework.utils;

import android.os.Handler;
import android.os.Looper;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class TaskExecutors {
    private static TaskExecutors taskExecutors;
    private Executor diskOperationThread;
    private Executor voicePlaybackThread;
    private Executor networkOperationThread;
    private Executor mainThread;

    /*
     * methods to dispatch tasks on demanded thread.*/

    private TaskExecutors() {
        this.diskOperationThread = Executors.newSingleThreadExecutor();
        this.voicePlaybackThread = Executors.newSingleThreadExecutor();
        this.networkOperationThread = Executors.newFixedThreadPool(3);
        this.mainThread = new MainThreadExecutor();
    }

    public static TaskExecutors getInstance() {
        if (taskExecutors == null) {
            taskExecutors = new TaskExecutors();
        }
        return taskExecutors;
    }

    public Executor getDiskOperationThread() {
        return diskOperationThread;
    }

    public Executor getNetworkOperationThread() {
        return networkOperationThread;
    }

    public Executor getMainThread() {
        return mainThread;
    }

    public Executor getVoicePlaybackThread() {
        return voicePlaybackThread;
    }

    private class MainThreadExecutor implements Executor {
        private Handler mainThreadHandler = new Handler(Looper.getMainLooper());

        @Override
        public void execute(Runnable command) {
            mainThreadHandler.post(command);
        }
    }

}


package test;

import javafx.application.Platform;

import java.util.concurrent.CountDownLatch;

public class FXR {
    public static void runFX(Runnable runnable) {
        try {
            CountDownLatch countDownLatch = new CountDownLatch(1);
            Platform.startup(runnable);
            countDownLatch.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}

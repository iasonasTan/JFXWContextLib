package test;

import com.jjfx.utils.Disposable;
import com.jjfx.utils.InputWindow;
import javafx.application.Platform;
import org.junit.Test;

import java.util.concurrent.CountDownLatch;

public class InputWindowTest {
    @Test
    public void inputWindow() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);

        Platform.startup(() -> {
                InputWindow inputWindow = new InputWindow(
                "JJFXUtils Test",
                "Enter the value a",
                null,
                "Value A",
                    System.out::println
                );
                inputWindow.addAction("Done", Disposable::close);
                inputWindow.showWindow(true);
        });

        latch.await();
    }
}

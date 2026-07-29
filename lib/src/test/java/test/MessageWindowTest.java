package test;

import com.jjfx.utils.MessageWindow;
import javafx.application.Platform;
import org.junit.Test;

import java.util.concurrent.CountDownLatch;

public class MessageWindowTest {
    @Test
    public void showLight() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);

        Platform.startup(() -> {
            var msgWin = new MessageWindow("JUnit4 Test Window",
                    null,
                    "This is a light themed window.",
                    "This is a junit4 test for the light theme of this window");
            msgWin.addAction("Ok", MessageWindow::close);
            msgWin.showWindow(false);
        });

        latch.await();
    }

    @Test
    public void showDark() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);

        Platform.startup(() -> {
            var msgWin = new MessageWindow("JUnit4 Test Window",
                    null,
                    "This is a dark themed window.",
                    "This is a junit4 test for the dark theme of this window");
            msgWin.addAction("Ok", MessageWindow::close);
            msgWin.showWindow(true);
        });

        latch.await();
    }

    @Test
    public void switchTheme() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);

        Platform.startup(() -> {
            var msgWin = new MessageWindow("JUnit4 Test Window",
                    null,
                    "This is a dark themed window.",
                    "This is a junit4 test for the dark theme of this window");
            msgWin.addAction("Ok", w -> {
                w.close();
                msgWin.showWindow(false);
            });
            msgWin.showWindow(true);
        });

        latch.await();
    }

    @Test
    public void wrongStartMethod() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);

        Platform.startup(() -> {
            var msgWin = new MessageWindow("JUnit4 Test Window",
                    null,
                    "This is a dark themed window.",
                    "This is a junit4 test for the dark theme of this window");
            msgWin.addAction("Ok", MessageWindow::close);
            msgWin.show();
        });

        latch.await();
    }
}
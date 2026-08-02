package test;

import com.jjfx.utils.MessageWindow;
import javafx.application.Platform;
import javafx.geometry.Dimension2D;
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
            msgWin.addAction("Ok", mw -> {
                mw.close();
                Platform.exit();
            });
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
            msgWin.addAction("Ok", mw -> {
                mw.close();
                Platform.exit();
            });
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
            msgWin.addAction("Ok", mw -> {
                mw.close();
                Platform.exit();
            });
            msgWin.show();
        });

        latch.await();
    }

    @Test
    public void newMethods() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);

        Platform.startup(() -> {
            MessageWindow messageWindow = new MessageWindow(
                    "Vocabulary Quiz - Hint",
                    null,
                    "Load words from file",
                    "Loads words from a file.\nFormat of file:\nkey1=value1\nkey2=value2\n...\nYou can choose a file by clicking 'Browse'"
            );
            messageWindow.setDimension(new Dimension2D(350, 300));
            messageWindow.addActionOk();
            messageWindow.showWindow(false);
        });

        latch.await();
    }
}
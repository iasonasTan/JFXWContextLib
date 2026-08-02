package test;

import com.jjfx.utils.Disposable;
import com.jjfx.utils.InputWindow;
import org.junit.Test;

public class InputWindowTest {
    @Test
    public void inputWindow() {
        FXR.runFX(() -> {
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
    }
}

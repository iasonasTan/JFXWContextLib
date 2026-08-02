package test;

import com.jjfx.utils.ExceptionWindow;
import org.junit.Test;

public class ExceptionWindowTest {
    @Test
    public void light() {
        FXR.runFX(() -> {
            Throwable thr = new IllegalStateException("This code is too awesome!");
            ExceptionWindow exceptionWindow = new ExceptionWindow(null, thr);
            exceptionWindow.addActionOk();
            exceptionWindow.showWindow(false);
        });
    }
}

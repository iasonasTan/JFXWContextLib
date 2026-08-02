package com.jjfx.utils;

import javafx.geometry.Dimension2D;
import javafx.stage.Stage;

public non-sealed class ExceptionWindow extends MessageWindow {
    public ExceptionWindow(Stage parent, Throwable exception) {
        super(
                "Error!",
                parent,
                "There was an error: ",
                Utils.stacktraceToString(exception.getStackTrace())
        );
        setDimension(new Dimension2D(800, 500));
    }
}

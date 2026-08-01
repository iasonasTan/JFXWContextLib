package com.jjfx.utils;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public final class InputWindow extends UtilWindow {
    private final List<Runnable> mRunnables = new ArrayList<>();

    public InputWindow(String title, String message, Stage parent, String hint, Consumer<String> result) {
        super(title, parent);
        addInput(hint, result);
        addMessage(message);
    }

    public void addMessage(String message) {
        Label label = new Label(message);
        label.getStyleClass().add("title");
        mMainLayout.getChildren().addFirst(label);
    }

    public void addInput(String hint, Consumer<String> resultConsumer) {
        TextField textField = new TextField();
        textField.setPromptText(hint);
        mRunnables.add(() -> resultConsumer.accept(textField.getText()));
        mMainLayout.getChildren().addFirst(textField);
    }

    @Override
    public void addAction(String text, final MessageWindowListener<Disposable> action) {
        MessageWindowListener<Disposable> action2 = window -> {
            action.onOk(window);
            mRunnables.forEach(Runnable::run);
        };
        super.addAction(text, action2);
    }
}

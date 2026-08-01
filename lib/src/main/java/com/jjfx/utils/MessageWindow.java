package com.jjfx.utils;

import javafx.scene.control.Label;
import javafx.stage.Stage;

public final class MessageWindow extends UtilWindow {
    public MessageWindow(String title, Stage parent, String message, String description) {
        super(title, parent);
        addText(message, description);
    }

    public void addText(String message, String description) {
        Label titleLabel = new Label(message);
        titleLabel.getStyleClass().add("title");
        mMainLayout.getChildren().add(titleLabel);

        Label descriptionLabel = new Label(description);
        mMainLayout.getChildren().add(descriptionLabel);
    }
}

package com.jjfx.utils;

import javafx.geometry.Dimension2D;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Objects;

public class MessageWindow extends Stage {
    private final VBox mMainLayout = new VBox(10);
    private final HBox mButtonsLayout = new HBox(10);
    private final Scene mScene;
    private Dimension2D mDimension = new Dimension2D(300, 200);
    private final Label mWarningLabel = new Label("CALL `showWindow()` instead of `show()`.");

    public MessageWindow(String title, Stage parent, String message, String description) {
        addText(message, description);
        initOwner(parent);
        setAlwaysOnTop(true);
        setTitle(title);

        setWidth(mDimension.getWidth());
        setHeight(mDimension.getHeight());

        mMainLayout.setAlignment(Pos.CENTER);
        mButtonsLayout.setAlignment(Pos.CENTER);
        mMainLayout.getChildren().add(mButtonsLayout);
        mMainLayout.getStylesheets().add(getStylesheet("style.css"));
        mScene = new Scene(mMainLayout, mDimension.getWidth(), mDimension.getHeight());

        setScene(mScene);

        // Gets removed when showWindow is used.
        mWarningLabel.getStyleClass().add("warning_label");
        mMainLayout.getChildren().add(mWarningLabel);
    }

    protected void addText(String message, String description) {
        Label titleLabel = new Label(message);
        titleLabel.getStyleClass().add("title");
        mMainLayout.getChildren().add(titleLabel);

        Label descriptionLabel = new Label(description);
        mMainLayout.getChildren().add(descriptionLabel);
    }

    public MessageWindow addAction(String text, MessageWindowListener action) {
        Button button = new Button(text);
        button.setOnAction(_ -> action.onOk(this));
        mButtonsLayout.getChildren().add(button);
        return this;
    }

    public MessageWindow setDimension(Dimension2D dimension) {
        mDimension = new Dimension2D(dimension.getWidth(), dimension.getHeight());
        return this;
    }

    public void showWindow() {
        mMainLayout.getChildren().remove(mWarningLabel);
        setWidth(mDimension.getWidth());
        setHeight(mDimension.getHeight());
        setScene(mScene);
        //sizeToScene();
        show();
    }

    public void showWindow(boolean darkTheme) {
        if (darkTheme) {
            mMainLayout.getStylesheets().remove(getStylesheet("light_theme_style.css"));
            mMainLayout.getStylesheets().add(getStylesheet("dark_theme_style.css"));
        } else {
            mMainLayout.getStylesheets().remove(getStylesheet("dark_theme_style.css"));
            mMainLayout.getStylesheets().add(getStylesheet("light_theme_style.css"));
        }

        showWindow();
    }

    private String getStylesheet(String name) {
        return Objects.requireNonNull(getClass().getResource("/style/" + name)).toExternalForm();
    }

    @Deprecated
    public void closeWindow() {
        close();
    }

    @Override
    public void close() {
        super.close();
    }

    public interface MessageWindowListener {
        void onOk(MessageWindow window);
    }
}

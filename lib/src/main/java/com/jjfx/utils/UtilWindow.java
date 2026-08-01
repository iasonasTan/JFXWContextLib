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

@SuppressWarnings("unused")
abstract class UtilWindow extends Stage implements Disposable, Window {
    protected final VBox mMainLayout = new VBox(10);
    protected final HBox mButtonsLayout = new HBox(10);
    protected final Scene mScene;
    protected Dimension2D mDimension = new Dimension2D(300, 200);
    protected final Label mWarningLabel = new Label("Call `showWindow()` instead of `show()`.");

    public UtilWindow(String title, Stage parent) {
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

    public void addAction(String text, MessageWindowListener<Window> action) {
        Button button = new Button(text);
        button.setOnAction(_ -> action.onOk(this));
        mButtonsLayout.getChildren().add(button);
    }

    public void setDimension(Dimension2D dimension) {
        mDimension = new Dimension2D(dimension.getWidth(), dimension.getHeight());
    }

    @Deprecated
    public void showWindow() {
        showWindow(false);
    }

    @Override
    public void showWindow(boolean darkTheme) {
        if (darkTheme) {
            mMainLayout.getStylesheets().remove(getStylesheet("light_theme_style.css"));
            mMainLayout.getStylesheets().add(getStylesheet("dark_theme_style.css"));
        } else {
            mMainLayout.getStylesheets().remove(getStylesheet("dark_theme_style.css"));
            mMainLayout.getStylesheets().add(getStylesheet("light_theme_style.css"));
        }

        mMainLayout.getChildren().remove(mWarningLabel);
        setWidth(mDimension.getWidth());
        setHeight(mDimension.getHeight());
        setScene(mScene);
        //sizeToScene();
        show();
    }

    @Deprecated
    public void closeWindow() {
        close();
    }

    @Override
    public void close() {
        super.close();
    }

    protected String getStylesheet(String name) {
        return Objects.requireNonNull(getClass().getResource("/je-jfx-utils/style/" + name)).toExternalForm();
    }

    public void addActionOk() {
        addAction("OK", Disposable::close);
    }
}

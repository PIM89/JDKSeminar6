package org.example;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


public class Main extends Application {

    MontyHall montyHall = new MontyHall(1000_000);

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Pane root = new Pane();
        ObservableList<PieChart.Data> valueList = FXCollections.observableArrayList(
                new PieChart.Data("Игрок не меняет дверь", montyHall.getWinWithoutChose()),
                new PieChart.Data("Игрок меняет дверь", montyHall.getWinByChose()));

        PieChart pieChart = new PieChart(valueList);
        pieChart.setTitle("The Monty Hall paradox");

        final Label caption = new Label("");
        caption.setTextFill(Color.BLACK);
        caption.setStyle("-fx-font: 14 arial;");

        for (final PieChart.Data data : pieChart.getData()) {
            data.getNode().addEventHandler(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent e) {
                    caption.setTranslateX(e.getSceneX());
                    caption.setTranslateY(e.getSceneY());
                    caption.setText(String.valueOf(data.getPieValue() / 10000) + "%");
                }
            });
        }

        root.getChildren().addAll(pieChart);
        root.getChildren().addAll(caption);
        Scene scene = new Scene(root, 500, 450);

        primaryStage.setTitle("Statistics");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}


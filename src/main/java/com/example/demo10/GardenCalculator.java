package com.example.demo10;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * JavaFX-sovellus puutarhatöiden hallintaan.
 */
public class GardenCalculator extends Application {
    private ObservableList<PuutarhaTyo> tyot = FXCollections.observableArrayList();
    private ListView<PuutarhaTyo> tyoListView;

    /**
     * Pääohjelma, joka käynnistää JavaFX-sovelluksen.
     * @param args Komentoriviparametrit
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Alustaa ja asettaa ylätason JavaFX-näkymän.
     * @param primaryStage Sovelluksen pääikkuna
     */
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Puutarhalaskuri");

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10));
        grid.setVgap(5);
        grid.setHgap(5);

        Label nimiLabel = new Label("Työn nimi:");
        GridPane.setConstraints(nimiLabel, 0, 0);
        TextField nimiField = new TextField();
        GridPane.setConstraints(nimiField, 1, 0);

        Label hintaLabel = new Label("Työn hinta (€):");
        GridPane.setConstraints(hintaLabel, 0, 1);
        TextField hintaField = new TextField();
        GridPane.setConstraints(hintaField, 1, 1);

        Label kestoLabel = new Label("Työn kesto (tuntia):");
        GridPane.setConstraints(kestoLabel, 0, 2);
        TextField kestoField = new TextField();
        GridPane.setConstraints(kestoField, 1, 2);

        CheckBox nurmikonLeikkausCheckbox = new CheckBox("Nurmikon leikkaus");
        GridPane.setConstraints(nurmikonLeikkausCheckbox, 0, 3);

        CheckBox kukkienIstutusCheckbox = new CheckBox("Kukkien istutus");
        GridPane.setConstraints(kukkienIstutusCheckbox, 1, 3);

        CheckBox haravointiCheckbox = new CheckBox("Haravointi");
        GridPane.setConstraints(haravointiCheckbox, 2, 3);

        Button lisaaButton = new Button("Lisää työ");
        lisaaButton.setOnAction(e -> {
            String tyonNimi = nimiField.getText();
            double tyonHinta = Double.parseDouble(hintaField.getText());
            int tyonKesto = Integer.parseInt(kestoField.getText());
            boolean nurmikonLeikkaus = nurmikonLeikkausCheckbox.isSelected();
            boolean kukkienIstutus = kukkienIstutusCheckbox.isSelected();
            boolean haravointi = haravointiCheckbox.isSelected();

            PuutarhaTyo tyo = new PuutarhaTyo(tyonNimi, tyonHinta, tyonKesto, nurmikonLeikkaus, kukkienIstutus, haravointi);
            tyot.add(tyo);

            nimiField.clear();
            hintaField.clear();
            kestoField.clear();
            nurmikonLeikkausCheckbox.setSelected(false);
            kukkienIstutusCheckbox.setSelected(false);
            haravointiCheckbox.setSelected(false);
        });
        GridPane.setConstraints(lisaaButton, 1, 4);

        tyoListView = new ListView<>(tyot);
        GridPane.setConstraints(tyoListView, 0, 5, 3, 1);

        Button tallennaButton = new Button("Tallenna työt");
        tallennaButton.setOnAction(e -> tallennaTiedostoon());
        GridPane.setConstraints(tallennaButton, 0, 6);

        Button lataaButton = new Button("Lataa työt");
        lataaButton.setOnAction(e -> lataaTiedostosta());
        GridPane.setConstraints(lataaButton, 1, 6);

        grid.getChildren().addAll(nimiLabel, nimiField, hintaLabel, hintaField, kestoLabel, kestoField,
                nurmikonLeikkausCheckbox, kukkienIstutusCheckbox, haravointiCheckbox,
                lisaaButton, tyoListView, tallennaButton, lataaButton);

        Scene scene = new Scene(grid, 600, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * Tallentaa puutarhatyöt tiedostoon.
     */
    private void tallennaTiedostoon() {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("puutarhatyot.dat"))) {
            outputStream.writeObject(new ArrayList<>(tyot));
            System.out.println("Työt tallennettu tiedostoon.");
        } catch (IOException e) {
            System.out.println("Virhe tallennettaessa tiedostoon: " + e.getMessage());
        }
    }

    /**
     * Lataa puutarhatyöt tiedostosta.
     */
    private void lataaTiedostosta() {
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("puutarhatyot.dat"))) {
            List<PuutarhaTyo> ladatutTyot = (List<PuutarhaTyo>) inputStream.readObject();
            tyot.clear();
            tyot.addAll(ladatutTyot);
            System.out.println("Työt ladattu tiedostosta.");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Virhe ladattaessa tiedostosta: " + e.getMessage());
        }
    }
}

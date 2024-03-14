package com.example.rgpgamejavafx;

import com.example.rgpgamejavafx.Entity.Enemy;
import com.example.rgpgamejavafx.Entity.Player;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;


public class BattleWindow {

    public static void display(Player player, Enemy enemy, Stage primaryStage) {
        Stage window = new Stage();
        window.setTitle("Batalla");

        // Crear controles
        Label titleLabel = new Label("¡Comienza la Batalla!");
        Label statusLabel = new Label();
        Button attackButton = new Button("Atacar");
        Button magicButton = new Button("Magia");
        Button itemsButton = new Button("Objetos");
        Button fleeButton = new Button("Huir");

        // Configurar eventos de los botones
        attackButton.setOnAction(event -> handleAttack(player, enemy, statusLabel));
        magicButton.setOnAction(event -> handleMagic(player, enemy, statusLabel));
        itemsButton.setOnAction(event -> usePotion(player, statusLabel));
        fleeButton.setOnAction(event -> handleFlee(player, statusLabel, window, primaryStage));

        VBox layout = new VBox(10);
        layout.setAlignment(Pos.CENTER);
        layout.getChildren().addAll(titleLabel, statusLabel, attackButton, magicButton, itemsButton, fleeButton);

        Scene scene = new Scene(layout, 300, 250);
        window.setScene(scene);

        // Hacer que la ventana sea modal
        window.initOwner(primaryStage);
        window.initModality(Modality.APPLICATION_MODAL);

        window.showAndWait();
    }

    private static void handleAttack(Player player, Enemy enemy, Label statusLabel) {
        // Implementar la lógica del ataque
        int playerDamage = player.attack();
        enemy.takeDamage(playerDamage);

        if (!enemy.isAlive()) {
            statusLabel.setText("¡Felicidades! Has derrotado a " + enemy.getName() + ".");
            return;
        }

        int enemyDamage = enemy.attack();
        player.takeDamage(enemyDamage);

        if (!player.isAlive()) {
            statusLabel.setText("¡Game Over! " + enemy.getName() + " te ha derrotado.");
            return;
        }

        updateStatusLabel(player, enemy, statusLabel);
    }

    private static void handleMagic(Player player, Enemy enemy, Label statusLabel) {
        // Implementar la lógica de la magia
        int magicDamage = player.castSpell();
        enemy.takeDamage(magicDamage);

        if (!enemy.isAlive()) {
            statusLabel.setText("¡Felicidades! Has derrotado a " + enemy.getName() + ".");
            return;
        }

        int enemyDamage = enemy.attack();
        player.takeDamage(enemyDamage);

        if (!player.isAlive()) {
            statusLabel.setText("¡Game Over! " + enemy.getName() + " te ha derrotado.");
            return;
        }

        updateStatusLabel(player, enemy, statusLabel);
    }

    private static void usePotion(Player player, Label statusLabel) {
        // Implementar la lógica de los objetos (pociones)
        // Aquí puedes implementar la lógica de los objetos según lo que necesites
        statusLabel.setText("¡Objetos aún no implementados!");
    }

    private static void handleFlee(Player player, Label statusLabel, Stage window, Stage primaryStage) {
        statusLabel.setText(player.getName() + " ha huido del combate.");
        window.close();
        HelloApplication.restartApplication();

    }

    private static void updateStatusLabel(Player player, Enemy enemy, Label statusLabel) {
        statusLabel.setText(player.getName() + " VS " + enemy.getName() + "\n" +
                player.getName() + " (HP: " + player.getHealth() + ") VS " +
                enemy.getName() + " (HP: " + enemy.getHealth() + ")");
    }
}

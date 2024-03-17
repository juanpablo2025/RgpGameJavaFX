package com.example.rgpgamejavafx;

import com.example.rgpgamejavafx.Entity.Enemy;
import com.example.rgpgamejavafx.Entity.Player;
import javafx.animation.TranslateTransition;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;


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

        // Crear rectángulos para representar al jugador y al enemigo
        Rectangle playerRect = new Rectangle(50, 50, Color.BLUE);
        Rectangle enemyRect = new Rectangle(50, 50, Color.RED);

        // Configurar eventos de los botones
        attackButton.setOnAction(event -> handleAttack(player, enemy, statusLabel, playerRect, enemyRect));
        magicButton.setOnAction(event -> handleMagic(player, enemy, statusLabel, playerRect, enemyRect));
        itemsButton.setOnAction(event -> usePotion(player, statusLabel));
        fleeButton.setOnAction(event -> handleFlee(player, statusLabel, window, primaryStage));

        // Organizar los controles y los rectángulos en un contenedor
        VBox layout = new VBox(10);
        layout.setAlignment(Pos.CENTER);
        layout.getChildren().addAll(titleLabel, playerRect, enemyRect, statusLabel, attackButton, magicButton, itemsButton, fleeButton);

        Scene scene = new Scene(layout, 300, 300);
        window.setScene(scene);

        // Hacer que la ventana sea modal
        window.initOwner(primaryStage);
        window.initModality(Modality.APPLICATION_MODAL);

        window.showAndWait();
    }

    private static void handleAttack(Player player, Enemy enemy, Label statusLabel, Rectangle playerRect, Rectangle enemyRect) {
        // Implementar la lógica del ataque
        int playerDamage = player.attack();
        enemy.takeDamage(playerDamage);
        statusLabel.setText(player.getName() + " ataca a " + enemy.getName() + " y causa " + playerDamage + " de daño.");

        // Animación de ataque del jugador
        TranslateTransition playerAttack = new TranslateTransition(Duration.seconds(0.5), playerRect);
        playerAttack.setByX(20);
        playerAttack.setCycleCount(2);
        playerAttack.setAutoReverse(true);

        playerAttack.play();

        if (!enemy.isAlive()) {
            statusLabel.setText(statusLabel.getText() + "\n¡Felicidades! Has derrotado a " + enemy.getName() + ".");
            return;
        }

        int enemyDamage = enemy.attack();
        player.takeDamage(enemyDamage);
        statusLabel.setText(statusLabel.getText() + "\n" + enemy.getName() + " contraataca y causa " + enemyDamage + " de daño a " + player.getName() + ".");

        // Animación de contraataque del enemigo
        TranslateTransition enemyAttack = new TranslateTransition(Duration.seconds(0.5), enemyRect);
        enemyAttack.setByX(-20);
        enemyAttack.setCycleCount(2);
        enemyAttack.setAutoReverse(true);

        enemyAttack.play();

        if (!player.isAlive()) {
            statusLabel.setText(statusLabel.getText() + "\n¡Game Over! " + enemy.getName() + " te ha derrotado.");
            return;
        }
    }

    private static void handleMagic(Player player, Enemy enemy, Label statusLabel, Rectangle playerRect, Rectangle enemyRect) {
        // Implementar la lógica de la magia
        int magicDamage = player.castSpell();
        enemy.takeDamage(magicDamage);
        statusLabel.setText(player.getName() + " lanza un hechizo sobre " + enemy.getName() + " y causa " + magicDamage + " de daño.");

        // Animación de magia del jugador
        TranslateTransition playerMagic = new TranslateTransition(Duration.seconds(0.5), playerRect);
        playerMagic.setByX(20);
        playerMagic.setCycleCount(2);
        playerMagic.setAutoReverse(true);

        playerMagic.play();

        if (!enemy.isAlive()) {
            statusLabel.setText(statusLabel.getText() + "\n¡Felicidades! Has derrotado a " + enemy.getName() + ".");
            return;
        }

        int enemyDamage = enemy.attack();
        player.takeDamage(enemyDamage);
        statusLabel.setText(statusLabel.getText() + "\n" + enemy.getName() + " contraataca y causa " + enemyDamage + " de daño a " + player.getName() + ".");

        // Animación de contraataque del enemigo
        TranslateTransition enemyAttack = new TranslateTransition(Duration.seconds(0.5), enemyRect);
        enemyAttack.setByX(-20);
        enemyAttack.setCycleCount(2);
        enemyAttack.setAutoReverse(true);

        enemyAttack.play();

        if (!player.isAlive()) {
            statusLabel.setText(statusLabel.getText() + "\n¡Game Over! " + enemy.getName() + " te ha derrotado.");
            return;
        }
    }

    private static void usePotion(Player player, Label statusLabel) {
        // Implementar la lógica de los objetos (pociones)
        // Aquí puedes implementar la lógica de los objetos según lo que necesites
        statusLabel.setText("¡Objetos aún no implementados!");
    }

    private static void handleFlee(Player player, Label statusLabel, Stage window, Stage primaryStage) {
        statusLabel.setText(player.getName() + " ha huido del combate.");
        window.close();
        RpgJavaFXApplication.restartApplication();
    }
}
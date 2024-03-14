package com.example.rgpgamejavafx;

import com.example.rgpgamejavafx.Entity.Enemy;
import com.example.rgpgamejavafx.Entity.Player;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class HelloApplication extends Application {
    private Player player;
    private Enemy enemy;
    private Label statusLabel;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("RPG por Turnos");

        // Crear jugador y enemigo
        player = new Player("Jugador", 100, 20);
        enemy = new Enemy("Enemigo", 50, 10);

        // Crear controles
        Label titleLabel = new Label("¡Bienvenido al juego RPG por turnos!");
        statusLabel = new Label();
        Button searchBattleButton = new Button("Buscar Batalla");

        // Configurar evento del botón "Buscar Batalla"
        searchBattleButton.setOnAction(event -> {
            BattleWindow.display(player, enemy, primaryStage);
            primaryStage.close(); // Cerrar la ventana principal al abrir la ventana de batalla
        });

        // Organizar los controles en un contenedor
        VBox root = new VBox(10);
        root.setAlignment(Pos.CENTER);
        root.getChildren().addAll(titleLabel, statusLabel, searchBattleButton);

        // Mostrar la escena
        Scene scene = new Scene(root, 300, 200);
        primaryStage.setScene(scene);
        primaryStage.show();

        updateStatusLabel();
    }

    private void updateStatusLabel() {
        statusLabel.setText(player.getName() + " VS " + enemy.getName() + "\n" +
                player.getName() + " (HP: " + player.getHealth() + ") VS " +
                enemy.getName() + " (HP: " + enemy.getHealth() + ")");
    }
    // Función para reiniciar la aplicación
    public static void restartApplication() {
        // Reiniciar la aplicación cerrando y volviendo a lanzar la instancia actual
        Platform.runLater(() -> {
            try {
                new HelloApplication().start(new Stage());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}



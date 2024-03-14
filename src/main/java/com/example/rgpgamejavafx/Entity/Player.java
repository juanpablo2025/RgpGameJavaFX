package com.example.rgpgamejavafx.Entity;

import java.util.Random;

public class Player {
    private String name;
    private int health;
    private int attackPower;
    private int magicPower;
    private int numPotions;
    private Random random;

    public Player(String name, int health, int attackPower) {
        this.name = name;
        this.health = health;
        this.attackPower = attackPower;
        this.magicPower = 30; // Poder mágico inicial
        this.numPotions = 3; // Cantidad inicial de pociones
        this.random = new Random();
    }

    public String getName() {
        return name;
    }

    public int getHealth() {
        return health;
    }

    public boolean isAlive() {
        return health > 0;
    }

    public int attack() {
        return attackPower;
    }

    public int castSpell() {
        // Implementar lógica de magia
        int spellDamage = 0;
        int spellType = random.nextInt(3); // 0: Fuego, 1: Planta, 2: Agua
        switch (spellType) {
            case 0:
                spellDamage = random.nextInt(10) + 10; // Daño de fuego entre 10 y 19
                break;
            case 1:
                spellDamage = random.nextInt(10) + 5; // Daño de planta entre 5 y 14
                break;
            case 2:
                spellDamage = random.nextInt(10) + 15; // Daño de agua entre 15 y 24
                break;
        }
        magicPower -= 10; // Costo de poder mágico
        return spellDamage;
    }

    public void usePotion() {
        // Implementar la lógica para usar una poción
        if (numPotions > 0) {
            int potionValue = random.nextInt(20) + 10; // Valor de curación de la poción entre 10 y 29
            health += potionValue;
            if (health > 100) {
                health = 100; // Limitar la salud máxima a 100
            }
            numPotions--;
        }
    }

    public void takeDamage(int damage) {
        health -= damage;
        if (health < 0) {
            health = 0;
        }
    }
}


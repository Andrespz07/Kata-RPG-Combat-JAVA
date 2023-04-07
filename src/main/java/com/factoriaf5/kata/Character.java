package com.factoriaf5.kata;
import java.util.HashSet;
import java.util.Set;

public class Character {
    private int health;
    private int level;
    private boolean isAlive;
    private int attackMaxRange;
    private Set<Character> allies = new HashSet<>();
    private Set<Character> enemies = new HashSet<>();

    public Character() {
        health = 1000;
        level = 1;
        isAlive = true;
        attackMaxRange = 2;
    }

    public int getHealth() {
        return health;
    }

    public int getLevel() {
        return level;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public int getAttackMaxRange() {
        return attackMaxRange;
    }

    public void joinFaction(Character character) {
        allies.add(character);
        character.getAllies().add(this);
    }

    public void leaveFaction(Character character) {
        allies.remove(character);
        character.getAllies().remove(this);
    }

    public Set<Character> getAllies() {
        return allies;
    }

    public Set<Character> getEnemies() {
        return enemies;
    }

    public void attack(Character target, int damage) {
        if (this == target) {
            return;
        }

        int levelDifference = target.getLevel() - this.getLevel();
        if (levelDifference >= 5) {
            damage = (int) (damage * 0.5);
        } else if (levelDifference <= -5) {
            damage = (int) (damage * 1.5);
        }

        if (target.getHealth() - damage <= 0) {
            target.setHealth(0);
            target.setAlive(false);
        } else {
            target.setHealth(target.getHealth() - damage);
        }
    }

    public void heal(Character target, int amount) {
        if (!target.isAlive()) {
            return;
        }

        if (this != target) {
            return;
        }

        target.setHealth(Math.min(target.getHealth() + amount, 1000));
    }

    public void takeDamage(int damage) {
        if (!isAlive()) {
            return;
        }

        if (health - damage <= 0) {
            health = 0;
            isAlive = false;
        } else {
            health -= damage;
        }
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void setAttackMaxRange(int attackMaxRange) {
        this.attackMaxRange = attackMaxRange;
    }
}


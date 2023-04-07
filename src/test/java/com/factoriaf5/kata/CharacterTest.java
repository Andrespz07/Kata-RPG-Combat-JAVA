package com.factoriaf5.kata;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CharacterTest {
    
    private Character character;
    
    @BeforeEach
    void setUp() throws Exception {
        character = new Character();
    }

    @Test
    void testDefaultValues() {
        assertEquals(1000, character.getHealth());
        assertEquals(1, character.getLevel());
        assertTrue(character.isAlive());
        assertEquals(2, character.getAttackMaxRange());
        assertTrue(character.getAllies().isEmpty());
        assertTrue(character.getEnemies().isEmpty());
    }
    
    @Test
    void testJoinFaction() {
        Character ally = new Character();
        character.joinFaction(ally);
        assertTrue(character.getAllies().contains(ally));
        assertTrue(ally.getAllies().contains(character));
    }
    
    @Test
    void testLeaveFaction() {
        Character ally = new Character();
        character.joinFaction(ally);
        character.leaveFaction(ally);
        assertFalse(character.getAllies().contains(ally));
        assertFalse(ally.getAllies().contains(character));
    }
    
    @Test
    void testAttack() {
        Character target = new Character();
        int initialHealth = target.getHealth();
        int damage = 100;
        character.attack(target, damage);
        assertEquals(initialHealth - damage, target.getHealth());
    }
    
    @Test
    void testHeal() {
        character.takeDamage(500);
        int initialHealth = character.getHealth();
        int amount = 200;
        character.heal(character, amount);
        assertEquals(initialHealth + amount, character.getHealth());
    }
    
    @Test
    void testTakeDamage() {
        int initialHealth = character.getHealth();
        int damage = 1000;
        character.takeDamage(damage);
        assertEquals(initialHealth - damage, character.getHealth());
        assertFalse(character.isAlive());
    }
}


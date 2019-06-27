package eu.kgorecki.rpgame.enemy.domain;

import eu.kgorecki.rpgame.enemy.dto.EnemyAttackPower;
import eu.kgorecki.rpgame.enemy.dto.EnemyId;
import eu.kgorecki.rpgame.enemy.dto.EnemyStatus;
import eu.kgorecki.rpgame.enemy.dto.EnemyTakeDamageCommand;

import java.io.Serializable;
import java.util.Objects;

public class Enemy implements Serializable {
    private final Id id;
    private final String name;
    private final int hitPoints;
    private final int attackPower;
    private final int defenceModifier;

    Enemy(Id id, String name, int hitPoints, int attackPower, int defenceModifier) {
        this.id = id;
        this.name = name;
        this.hitPoints = hitPoints;
        this.attackPower = attackPower;
        this.defenceModifier = defenceModifier;
    }

    public static Enemy of(int id, String name, int hitPoints, int attackPower, int defenceModifier) {
        return new Enemy(new Id(id), name, hitPoints, attackPower, defenceModifier);
    }

    EnemyAttackPower getAttackPower() {
        return new EnemyAttackPower(attackPower);
    }

    Enemy takeDamage(EnemyTakeDamageCommand command) {
        int newHitPoints = this.hitPoints - command.getAttackPower();

        return new Enemy(id, name, newHitPoints, attackPower, defenceModifier);
    }

    EnemyStatus getStatus() {
        return hitPoints <= 0 ? EnemyStatus.DEAD : EnemyStatus.ALIVE;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Enemy enemy = (Enemy) o;
        return Objects.equals(id, enemy.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Enemy name= " + name + '\n' +
                "hitPoints=" + hitPoints + "\n" +
                "attackPower=" + attackPower + "\n" +
                "defenceModifier=" + defenceModifier;
    }

    public Id getId() {
        return id;
    }

    public EnemyId getIdAsEnemyId() {
        return EnemyId.of(id.getId());
    }
}

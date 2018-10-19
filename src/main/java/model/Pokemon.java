package model;

public class Pokemon {
    private int number;
    private String name;
    private String type1;
    private String type2;
    private int powerPoints;
    private int healthPoints;
    private int attackPoints;
    private int defensePoints;
    private int speedAttack;
    private int speedDefense;
    private int speedPoints;
    private int generation;

    public Pokemon() {
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType1() {
        return type1;
    }

    public void setType1(String type1) {
        this.type1 = type1;
    }

    public String getType2() {
        return type2;
    }

    public void setType2(String type2) {
        this.type2 = type2;
    }

    public int getPowerPoints() {
        return powerPoints;
    }

    public void setPowerPoints(int powerPoints) {
        this.powerPoints = powerPoints;
    }

    public int getHealthPoints() {
        return healthPoints;
    }

    public void setHealthPoints(int healthPoints) {
        this.healthPoints = healthPoints;
    }

    public int getAttackPoints() {
        return attackPoints;
    }

    public void setAttackPoints(int attackPoints) {
        this.attackPoints = attackPoints;
    }

    public int getDefensePoints() {
        return defensePoints;
    }

    public void setDefensePoints(int defensePoints) {
        this.defensePoints = defensePoints;
    }

    public int getSpeedAttack() {
        return speedAttack;
    }

    public void setSpeedAttack(int speedAttack) {
        this.speedAttack = speedAttack;
    }

    public int getSpeedDefense() {
        return speedDefense;
    }

    public void setSpeedDefense(int speedDefense) {
        this.speedDefense = speedDefense;
    }

    public int getSpeedPoints() {
        return speedPoints;
    }

    public void setSpeedPoints(int speedPoints) {
        this.speedPoints = speedPoints;
    }

    public int getGeneration() {
        return generation;
    }

    public void setGeneration(int generation) {
        this.generation = generation;
    }



    @Override
    public String toString() {
        return "Pokemon{" +
                "number=" + number +
                ", name='" + name + '\'' +
                ", type1='" + type1 + '\'' +
                ", type2='" + type2 + '\'' +
                ", powerPoints=" + powerPoints +
                ", healthPoints=" + healthPoints +
                ", attackPoints=" + attackPoints +
                ", defensePoints=" + defensePoints +
                ", speedAttack=" + speedAttack +
                ", speedDefense=" + speedDefense +
                ", speedPoints=" + speedPoints +
                ", generation=" + generation +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Pokemon pokemon = (Pokemon) o;

        return name != null ? name.equals(pokemon.name) : pokemon.name == null;
    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }
}

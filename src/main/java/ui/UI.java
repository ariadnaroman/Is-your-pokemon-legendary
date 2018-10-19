package ui;

import decision_tree.DecisionTree;
import model.Pokemon;
import repository.PokemonRepository;

import java.util.Scanner;

public class UI {
    private DecisionTree decisionTree;

    public UI() {
        decisionTree = new DecisionTree(new PokemonRepository("Train.csv", "Test.csv"));
        decisionTree.growTree();
    }

    public void showMenu() {
        while (true) {
            System.out.println("");
            System.out.println();
            System.out.println("Select an option: ");
            System.out.println("1. Test a pokemon.");
            System.out.println("2. Calculate accuracy for test data.");
            Scanner scanner = new Scanner(System.in);
            String amCitit = scanner.next();
            if (amCitit.equals("1")) {
                Pokemon pokemon = new Pokemon();
                System.out.println("Insert points for power: ");
                int powerPoints = scanner.nextInt();
                pokemon.setPowerPoints(powerPoints);
                System.out.println("Insert points for health: ");
                int healthPoints = scanner.nextInt();
                pokemon.setHealthPoints(healthPoints);
                System.out.println("Insert points for attack: ");
                int attackPoints = scanner.nextInt();
                pokemon.setAttackPoints(attackPoints);
                System.out.println("Insert points for defense: ");
                int defensePoints = scanner.nextInt();
                pokemon.setDefensePoints(defensePoints);
                System.out.println("Insert speed for attack: ");
                int speedAttack = scanner.nextInt();
                pokemon.setSpeedAttack(speedAttack);
                System.out.println("Insert speed for defense: ");
                int speedDefense = scanner.nextInt();
                pokemon.setSpeedDefense(speedDefense);
                System.out.println("Insert points for speed: ");
                int speedPoints = scanner.nextInt();
                pokemon.setSpeedPoints(speedPoints);
                System.out.println("Insert generation (between 1 and 6): ");
                int generation = scanner.nextInt();
                pokemon.setGeneration(generation);
                System.out.println("Is pokemon legendary?");
                System.out.println(this.decisionTree.isPokemonLegendary(pokemon));
            } else if (amCitit.equals("2")){
                System.out.println("The accuracy for the test data is:");
                System.out.println(this.decisionTree.testTree());
            } else {
                System.out.println("Please select 1 or 2.");
                showMenu();
            }
        }
    }
}

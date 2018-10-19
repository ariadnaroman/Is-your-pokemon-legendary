package main;

import decision_tree.DecisionTree;
import model.Attribute;
import model.Pokemon;
import repository.PokemonRepository;
import ui.UI;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;

public class Main {
    public static void main(String[] args) {
        UI ui = new UI();
        ui.showMenu();
//        PokemonRepository pokemonRepository = new PokemonRepository("Train.csv", "Test.csv");
//        DecisionTree decisionTree = new DecisionTree(pokemonRepository);
//        decisionTree.growTree();
//        decisionTree.testTree();
//        Pokemon pokemon = new Pokemon();
//        pokemon.setNumber(571);
//        pokemon.setName("Zoroark");
//        pokemon.setType1("Dark");
//        pokemon.setType2("");
//        pokemon.setPowerPoints(510);
//        pokemon.setHealthPoints(60);
//        pokemon.setAttackPoints(105);
//        pokemon.setDefensePoints(60);
//        pokemon.setSpeedAttack(120);
//        pokemon.setSpeedDefense(60);
//        pokemon.setSpeedPoints(105);
//        pokemon.setGeneration(5);
//        System.out.println(decisionTree.isPokemonLegendary(pokemon));
//        System.out.println("Train data:");
//        Map<Pokemon,Boolean> train = pokemonRepository.getTrainData();
//        System.out.println(train.entrySet().size());
//        for (Map.Entry<Pokemon,Boolean> entry : train.entrySet()) {
//            System.out.println(entry);
//        }
//        System.out.println("Test data:");
//        Map<Pokemon,Boolean> test = pokemonRepository.getTestData();
//        System.out.println(test.entrySet().size());
//        for (Map.Entry<Pokemon,Boolean> entry : test.entrySet()) {
//            System.out.println(entry);
//        }
//        DecisionTree decisionTree = new DecisionTree();
//        Map<Pokemon,Boolean> pokemons = new HashMap<>();
//        Pokemon pokemon1 = new Pokemon();
//        pokemon1.setName("1");
//        pokemon1.setType1("sunny");
//        pokemons.put(pokemon1,true);
//
//        Pokemon pokemon2 = new Pokemon();
//        pokemon2.setName("2");
//        pokemon2.setType1("sunny");
//        pokemons.put(pokemon2,true);
//
//        Pokemon pokemon3 = new Pokemon();
//        pokemon3.setName("pokemon3");
//        pokemon3.setType1("sunny");
//        pokemons.put(pokemon3,true);
//
//        Pokemon pokemon4 = new Pokemon();
//        pokemon4.setName("pokemon4");
//        pokemon4.setType1("sunny");
//        pokemons.put(pokemon4,false);
//
//        Pokemon pokemon5 = new Pokemon();
//        pokemon5.setName("pokemon5");
//        pokemon5.setType1("sunny");
//        pokemons.put(pokemon5,false);
//
//        //
//
//        Pokemon pokemon6 = new Pokemon();
//        pokemon6.setName("pokemon6");
//        pokemon6.setType1("overcast");
//        pokemons.put(pokemon6,true);
//
//        Pokemon pokemon7 = new Pokemon();
//        pokemon7.setName("p7");
//        pokemon7.setType1("overcast");
//        pokemons.put(pokemon7,true);
//
//        Pokemon pokemon8 = new Pokemon();
//        pokemon8.setName("pokemon8");
//        pokemon8.setType1("overcast");
//        pokemons.put(pokemon8,true);
//
//        Pokemon pokemon9 = new Pokemon();
//        pokemon9.setName("pokemon9");
//        pokemon9.setType1("overcast");
//        pokemons.put(pokemon9,true);
//
//        //
//
//        Pokemon pokemon10 = new Pokemon();
//        pokemon10.setName("pokemon10");
//        pokemon10.setType1("rainy");
//        pokemons.put(pokemon10,true);
//
//        Pokemon pokemon11 = new Pokemon();
//        pokemon11.setName("pokemon11");
//        pokemon11.setType1("rainy");
//        pokemons.put(pokemon11,true);
//
//        Pokemon pokemon12 = new Pokemon();
//        pokemon12.setName("pokemon12");
//        pokemon12.setType1("rainy");
//        pokemons.put(pokemon12,false);
//
//        Pokemon pokemon13 = new Pokemon();
//        pokemon13.setName("pokemon13");
//        pokemon13.setType1("rainy");
//        pokemons.put(pokemon13,false);
//
//        Pokemon pokemon14 = new Pokemon();
//        pokemon14.setName("pokemon14");
//        pokemon14.setType1("rainy");
//        pokemons.put(pokemon14,false);
//
////
//
//        Attribute attribute = new Attribute();
//        attribute.setName("outlook");
//        Map<Predicate<Pokemon>,String> conditions = new HashMap<>();
//        conditions.put(new Predicate<Pokemon>() {
//            @Override
//            public boolean test(Pokemon pokemon) {
//                return pokemon.getType1().equals("sunny");
//            }
//        }, "sunny");
//        conditions.put(new Predicate<Pokemon>() {
//            @Override
//            public boolean test(Pokemon pokemon) {
//                return pokemon.getType1().equals("overcast");
//            }
//        }, "overcast");
//        conditions.put(new Predicate<Pokemon>() {
//            @Override
//            public boolean test(Pokemon pokemon) {
//                return pokemon.getType1().equals("rainy");
//            }
//        }, "rainy");
//        attribute.setConditions(conditions);
//        System.out.println(decisionTree.calculateGain(pokemons,attribute));
    }
}

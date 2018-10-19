package decision_tree;

import model.Attribute;
import model.Node;
import model.Pokemon;
import repository.PokemonRepository;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class DecisionTree {
    private PokemonRepository pokemonRepository;
    private Node root;


    public DecisionTree(PokemonRepository pokemonRepository) {
        this.pokemonRepository = pokemonRepository;
    }

    private static double Log2(double n) {
        return Math.log(n) / Math.log(2);
    }

    public PokemonRepository getPokemonRepository() {
        return pokemonRepository;
    }

    public void setPokemonRepository(PokemonRepository pokemonRepository) {
        this.pokemonRepository = pokemonRepository;
    }

    public Node getRoot() {
        return root;
    }

    public void setRoot() {
        String rootLabel;
        List<Attribute> rootAttributes = new ArrayList<>();
        Attribute rootAttribute = new Attribute();
        Map<Pokemon, Boolean> rootPokemons = pokemonRepository.getTrainData();
        Double rootEntropy = calculateEntropy(rootPokemons);
        if (rootEntropy == 0.0) {
            Map.Entry<Pokemon, Boolean> entry = (Map.Entry<Pokemon, Boolean>) rootPokemons.entrySet().toArray()[0];
            rootLabel = entry.getValue().toString();
        } else {
            rootAttributes = composeAttributeList();
            rootAttribute = getBestAttribute(rootPokemons, rootAttributes);
            rootLabel = rootAttribute.getName();
        }
        this.root = new Node(rootPokemons,rootAttributes,rootLabel,rootEntropy,null,null,rootAttribute);
    }

    public void growTree() {
        setRoot();
        Stack<Node> toVisit = new Stack<>();
        List<Node> visited = new ArrayList<>();
        toVisit.push(root);
        while (!toVisit.empty()) {
            Node node = toVisit.pop();
            if (visited.contains(node)) continue;
            visited.add(node);
            if (node.getLabel().equals("true") || node.getLabel().equals("false")) continue;
            for (Map.Entry<Predicate<Pokemon>, String> condition : node.getAttribute().getConditions().entrySet()) {
                Node child = setUpChild(node, condition);
                node.getChildren().add(child);
                toVisit.push(child);
            }
        }
    }

    public String isPokemonLegendary(Pokemon pokemon) {
        Stack<Node> toVisit = new Stack<>();
        List<Node> visited = new ArrayList<>();
        toVisit.push(root);
        while (!toVisit.empty()) {
            Node node = toVisit.pop();
            if (visited.contains(node)) continue;
            visited.add(node);
            if (node.getLabel().equals("true") || node.getLabel().equals("false")) {
                return node.getLabel();
            }
            for (Map.Entry<Predicate<Pokemon>, String> condition : node.getAttribute().getConditions().entrySet()) {
                if (condition.getKey().test(pokemon)) {
                    for (Node child : node.getChildren()) {
                        if (child.getParentAttributeRespose().equals(condition.getValue()))
                            toVisit.push(child);
                    }
                }
            }
        }
        return null;
    }

    public Double testTree() {
        double accuracy = 0.0;
        for (Map.Entry<Pokemon,Boolean> pokemon : pokemonRepository.getTestData().entrySet()) {
            String response = isPokemonLegendary(pokemon.getKey());
            if (response.equals(pokemon.getValue().toString())) accuracy+=1;
            System.out.println(response + " " + pokemon.getValue());
        }
        accuracy = accuracy*1.0/pokemonRepository.getTestData().entrySet().size();
        return accuracy;
    }

    private Node setUpChild(Node parentNode, Map.Entry<Predicate<Pokemon>, String> condition) {
        String childLabel;
        String parentAttributeResponse = condition.getValue();
        Attribute childAttribute = new Attribute();
        List<Attribute> childAttributes = new ArrayList<>();
        Map<Pokemon, Boolean> childPokemons = conditionPokemons(parentNode.getPokemons(),condition.getKey());
        Double childEntropy = calculateEntropy(childPokemons);
        if (childEntropy == 0.0) {
            List<Map.Entry<Pokemon, Boolean>> list = new ArrayList<>(childPokemons.entrySet());
            if (list.size() == 0) childLabel = "true";
            else {
                Map.Entry<Pokemon, Boolean> entry1 = list.get(0);
                childLabel = entry1.getValue().toString();
            }
        } else {
            childAttributes = parentNode.getRemainingAttributes().stream().filter(attribute1 -> !attribute1.getName().equals(parentNode.getAttribute().getName())).collect(Collectors.toList());
            if (childAttributes.size() == 0) {
                childLabel = getMajorityLabel(childPokemons).toString();
            } else {
                childAttribute = getBestAttribute(childPokemons, childAttributes);
                childLabel = childAttribute.getName();
            }
        }
        Node child = new Node(childPokemons,childAttributes,childLabel,childEntropy,parentNode,parentAttributeResponse,childAttribute);
        return child;
    }

    private Map<Pokemon,Boolean> conditionPokemons(Map<Pokemon,Boolean> pokemons, Predicate<Pokemon> condition) {
        Map<Pokemon, Boolean> conditionedPokemons = new HashMap<>();
        Set<Map.Entry<Pokemon, Boolean>> pokemonsSet = pokemons.entrySet().stream().filter(pokemon -> condition.test(pokemon.getKey())).collect(Collectors.toSet());
        pokemonsSet.forEach(pokemon -> conditionedPokemons.put(pokemon.getKey(), pokemon.getValue()));
        return conditionedPokemons;
    }

    private Boolean getMajorityLabel(Map<Pokemon, Boolean> pokemons) {
        long legendary = pokemons.entrySet().stream().filter(Map.Entry::getValue).count();
        long nonLegendary = pokemons.entrySet().stream().filter(pokemonBooleanEntry -> !pokemonBooleanEntry.getValue()).count();
        return legendary > nonLegendary;
    }

    private List<Attribute> composeAttributeList() {
        int maxPowerPoints = pokemonRepository.getMaxPowerPoints();
        int maxHealthPoints = pokemonRepository.getMaxHealthPoints();
        int maxAttackPoints = pokemonRepository.getMaxAttackPoints();
        int maxDefensePoints = pokemonRepository.getMaxDefensePoints();
        int maxSpeedAttack = pokemonRepository.getMaxSpeedAttack();
        int maxSpeedDefense = pokemonRepository.getMaxSpeedDefense();
        int maxSpeedPoints = pokemonRepository.getMaxSpeedPoints();
        List<Attribute> attributeList = new ArrayList<>();

        Attribute firstAbility = new Attribute();
        firstAbility.setName("first ability");
        Set<String> abilities = pokemonRepository.getAbilities();
        Map<Predicate<Pokemon>, String> conditionsAbilities = new HashMap<>();
        for (String ability : abilities) {
            conditionsAbilities.put(pokemon -> pokemon.getType1().equals(ability), ability);
        }
        firstAbility.setConditions(conditionsAbilities);
        //attributeList.add(firstAbility);

        Attribute secondAbility = new Attribute();
        secondAbility.setName("second ability");
        Map<Predicate<Pokemon>, String> conditionsAbilities2 = new HashMap<>();
        for (String ability : abilities) {
            conditionsAbilities2.put(pokemon -> pokemon.getType2().equals(ability), ability);
        }
        conditionsAbilities2.put(pokemon -> pokemon.getType2() == null || pokemon.getType2().equals(""), "none");
        secondAbility.setConditions(conditionsAbilities2);
        //attributeList.add(secondAbility);

        Attribute powerPoints = new Attribute();
        powerPoints.setName("powerPoints");
        Map<Predicate<Pokemon>, String> conditionsPP = new HashMap<>();
        conditionsPP.put(pokemon -> pokemon.getPowerPoints() > maxPowerPoints, "> " + maxPowerPoints);
        conditionsPP.put(pokemon -> pokemon.getPowerPoints() <= maxPowerPoints, "<= " + maxPowerPoints);
        powerPoints.setConditions(conditionsPP);
        attributeList.add(powerPoints);

        Attribute healthPoints = new Attribute();
        healthPoints.setName("healthPoints");
        Map<Predicate<Pokemon>, String> conditionsHP = new HashMap<>();
        conditionsHP.put(pokemon -> pokemon.getHealthPoints() > maxHealthPoints, "> " + maxHealthPoints);
        conditionsHP.put(pokemon -> pokemon.getHealthPoints() <= maxHealthPoints, "<= " + maxHealthPoints);
        healthPoints.setConditions(conditionsHP);
        attributeList.add(healthPoints);

        Attribute attackPoints = new Attribute();
        attackPoints.setName("attackPoints");
        Map<Predicate<Pokemon>, String> conditionsAP = new HashMap<>();
        conditionsAP.put(pokemon -> pokemon.getAttackPoints() > maxAttackPoints, "> " + maxAttackPoints);
        conditionsAP.put(pokemon -> pokemon.getAttackPoints() <= maxAttackPoints, "<= " + maxAttackPoints);
        attackPoints.setConditions(conditionsAP);
        attributeList.add(attackPoints);

        Attribute defensePoints = new Attribute();
        defensePoints.setName("defensePoints");
        Map<Predicate<Pokemon>, String> conditionsDP = new HashMap<>();
        conditionsDP.put(pokemon -> pokemon.getDefensePoints() > maxDefensePoints, "> " + maxDefensePoints);
        conditionsDP.put(pokemon -> pokemon.getDefensePoints() <= maxDefensePoints, "<= " + maxDefensePoints);
        defensePoints.setConditions(conditionsDP);
        attributeList.add(defensePoints);

        Attribute speedAttack = new Attribute();
        speedAttack.setName("speedAttack");
        Map<Predicate<Pokemon>, String> conditionsSA = new HashMap<>();
        conditionsSA.put(pokemon -> pokemon.getSpeedAttack() > maxSpeedAttack, "> " + maxSpeedAttack);
        conditionsSA.put(pokemon -> pokemon.getSpeedAttack() <= maxSpeedAttack, "<= " + maxSpeedAttack);
        speedAttack.setConditions(conditionsSA);
        attributeList.add(speedAttack);

        Attribute speedDefense = new Attribute();
        speedDefense.setName("speedDefense");
        Map<Predicate<Pokemon>, String> conditionsSD = new HashMap<>();
        conditionsSD.put(pokemon -> pokemon.getSpeedDefense() > maxSpeedDefense, "> " + maxSpeedDefense);
        conditionsSD.put(pokemon -> pokemon.getSpeedDefense() <= maxSpeedDefense, "<= " + maxSpeedDefense);
        speedDefense.setConditions(conditionsSD);
        attributeList.add(speedDefense);

        Attribute speedPoints = new Attribute();
        speedPoints.setName("speedPoints");
        Map<Predicate<Pokemon>, String> conditionsSP = new HashMap<>();
        conditionsSP.put(pokemon -> pokemon.getSpeedPoints() > maxSpeedPoints, "> " + maxSpeedPoints);
        conditionsSP.put(pokemon -> pokemon.getSpeedPoints() <= maxSpeedPoints, "<= " + maxSpeedPoints);
        speedPoints.setConditions(conditionsSP);
        attributeList.add(speedPoints);

        Attribute generation = new Attribute();
        generation.setName("generation");
        Map<Predicate<Pokemon>, String> conditionsG = new HashMap<>();
        conditionsG.put(pokemon -> pokemon.getGeneration() == 1, "1");
        conditionsG.put(pokemon -> pokemon.getGeneration() == 2, "2");
        conditionsG.put(pokemon -> pokemon.getGeneration() == 3, "3");
        conditionsG.put(pokemon -> pokemon.getGeneration() == 4, "4");
        conditionsG.put(pokemon -> pokemon.getGeneration() == 5, "5");
        conditionsG.put(pokemon -> pokemon.getGeneration() == 6, "6");
        conditionsG.put(pokemon -> pokemon.getGeneration() == 7, "7");
        generation.setConditions(conditionsG);
        attributeList.add(generation);

        return attributeList;
    }

    public Attribute getBestAttribute(Map<Pokemon, Boolean> pokemons, List<Attribute> attributes) {
        Attribute bestAttribute = null;
        Double bestGain = 0.0;
        for (Attribute attribute : attributes) {
            if (bestAttribute == null) {
                bestAttribute = attribute;
                bestGain = calculateGain(pokemons, attribute);
            } else {
                Double gain = calculateGain(pokemons, attribute);
                if (gain > bestGain) {
                    bestAttribute = attribute;
                    bestGain = gain;
                }
            }
        }
        return bestAttribute;
    }

    public Double calculateEntropy(Map<Pokemon, Boolean> pokemons) {
        int size = pokemons.entrySet().size();
        int noOfLeg = 0;
        int noOfNonLeg = 0;
        for (Map.Entry<Pokemon, Boolean> entry : pokemons.entrySet()) {
            if (entry.getValue()) noOfLeg++;
            else noOfNonLeg++;
        }
        if (noOfLeg == size || noOfLeg == 0)
            return 0.0;
        Double legRaport = noOfLeg * 1.0 / size;
        Double nonLegRaport = noOfNonLeg * 1.0 / size;
        Double entropy = 0.0;
        entropy -= legRaport * Log2(legRaport);
        entropy -= nonLegRaport * Log2(nonLegRaport);
        return entropy;
    }

    public Double calculateEntropyAttribute(Map<Pokemon, Boolean> pokemons, Attribute attribute) {
        Double sum = 0.0;
        for (Map.Entry<Predicate<Pokemon>, String> condition : attribute.getConditions().entrySet()) {
            int noPokemons = 0;
            Map<Pokemon, Boolean> conditionedPok = new HashMap<>();
            for (Map.Entry<Pokemon, Boolean> entry : pokemons.entrySet()) {
                if (condition.getKey().test(entry.getKey())) {
                    noPokemons++;
                    conditionedPok.put(entry.getKey(), entry.getValue());
                }
            }
            Double probability = noPokemons * 1.0 / pokemons.entrySet().size();
            Double entropy = calculateEntropy(conditionedPok);
            sum += probability * entropy;
        }
        return sum;
    }

    public Double calculateGain(Map<Pokemon, Boolean> pokemons, Attribute attribute) {
        return Math.abs(calculateEntropy(pokemons) - calculateEntropyAttribute(pokemons, attribute));
    }
}

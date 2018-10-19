package repository;

import model.Pokemon;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class PokemonRepository {
    private String filenameTrain;
    private String filenameTest;
    private Map<Pokemon,Boolean> trainData;
    private Map<Pokemon,Boolean> testData;
    private Set<String> abilities = new LinkedHashSet<>();
    private int maxPowerPoints = 0;
    private int maxHealthPoints = 0;
    private int maxAttackPoints = 0;
    private int maxDefensePoints = 0;
    private int maxSpeedAttack = 0;
    private int maxSpeedDefense = 0;
    private int maxSpeedPoints = 0;

    public PokemonRepository(String filenameTrain, String filenameTest) {
        this.filenameTrain = filenameTrain;
        this.filenameTest = filenameTest;
        readFromFile();
    }

    private void readFromFile() {
        String line;
        try {
            FileReader fileReader = new FileReader(filenameTrain);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            trainData = new HashMap<>();

            while ((line = bufferedReader.readLine()) != null) {
                String elems[] = line.split(",");
                Pokemon pokemon = new Pokemon();
                pokemon.setNumber(Integer.parseInt(elems[0]));
                pokemon.setName(elems[1]);
                pokemon.setType1(elems[2]);
                pokemon.setType2(elems[3]);
                pokemon.setPowerPoints(Integer.parseInt(elems[4]));
                pokemon.setHealthPoints(Integer.parseInt(elems[5]));
                pokemon.setAttackPoints(Integer.parseInt(elems[6]));
                pokemon.setDefensePoints(Integer.parseInt(elems[7]));
                pokemon.setSpeedAttack(Integer.parseInt(elems[8]));
                pokemon.setSpeedDefense(Integer.parseInt(elems[9]));
                pokemon.setSpeedPoints(Integer.parseInt(elems[10]));
                pokemon.setGeneration(Integer.parseInt(elems[11]));
                if (elems[12].equals("TRUE")) trainData.put(pokemon, true);
                else {
                    trainData.put(pokemon, false);
                    if (pokemon.getPowerPoints()>maxPowerPoints) maxPowerPoints = pokemon.getPowerPoints();
                    if (pokemon.getHealthPoints()>maxHealthPoints) maxHealthPoints = pokemon.getHealthPoints();
                    if (pokemon.getAttackPoints()>maxAttackPoints) maxAttackPoints = pokemon.getAttackPoints();
                    if (pokemon.getDefensePoints()>maxDefensePoints) maxDefensePoints = pokemon.getDefensePoints();
                    if (pokemon.getSpeedAttack()>maxSpeedAttack) maxSpeedAttack = pokemon.getSpeedAttack();
                    if (pokemon.getSpeedDefense()>maxSpeedDefense) maxSpeedDefense = pokemon.getSpeedDefense();
                    if (pokemon.getSpeedPoints()>maxSpeedPoints) maxSpeedPoints = pokemon.getSpeedPoints();
                }
                abilities.add(elems[2]);
                if (elems[3]!=null && !elems[3].equals("")) abilities.add(elems[3]);
            }
            bufferedReader.close();
        } catch (FileNotFoundException ex) {
            System.out.println("Unable to open file '" + filenameTrain + "'");
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        try {
            FileReader fileReader = new FileReader(filenameTest);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            testData = new HashMap<>();

            while ((line = bufferedReader.readLine()) != null) {
                String elems[] = line.split(",");
                Pokemon pokemon = new Pokemon();
                pokemon.setNumber(Integer.parseInt(elems[0]));
                pokemon.setName(elems[1]);
                pokemon.setType1(elems[2]);
                pokemon.setType2(elems[3]);
                pokemon.setPowerPoints(Integer.parseInt(elems[4]));
                pokemon.setHealthPoints(Integer.parseInt(elems[5]));
                pokemon.setAttackPoints(Integer.parseInt(elems[6]));
                pokemon.setDefensePoints(Integer.parseInt(elems[7]));
                pokemon.setSpeedAttack(Integer.parseInt(elems[8]));
                pokemon.setSpeedDefense(Integer.parseInt(elems[9]));
                pokemon.setSpeedPoints(Integer.parseInt(elems[10]));
                pokemon.setGeneration(Integer.parseInt(elems[11]));
                if (elems[12].equals("TRUE")) testData.put(pokemon, true);
                else testData.put(pokemon, false);
            }
            bufferedReader.close();
        } catch (FileNotFoundException ex) {
            System.out.println("Unable to open file '" + filenameTest + "'");
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    public Map<Pokemon, Boolean> getTrainData() {
        return trainData;
    }

    public Map<Pokemon, Boolean> getTestData() {
        return testData;
    }

    public Set<String> getAbilities() {
        return abilities;
    }

    public void setAbilities(Set<String> abilities) {
        this.abilities = abilities;
    }

    public int getMaxPowerPoints() {
        return maxPowerPoints;
    }

    public int getMaxHealthPoints() {
        return maxHealthPoints;
    }

    public int getMaxAttackPoints() {
        return maxAttackPoints;
    }

    public int getMaxDefensePoints() {
        return maxDefensePoints;
    }

    public int getMaxSpeedAttack() {
        return maxSpeedAttack;
    }

    public int getMaxSpeedDefense() {
        return maxSpeedDefense;
    }

    public int getMaxSpeedPoints() {
        return maxSpeedPoints;
    }
}

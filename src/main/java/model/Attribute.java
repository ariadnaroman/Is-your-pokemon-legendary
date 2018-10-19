package model;

import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

public class Attribute {
    private String name;
    private Map<Predicate<Pokemon>,String> conditions;

    public Attribute() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<Predicate<Pokemon>, String> getConditions() {
        return conditions;
    }

    public void setConditions(Map<Predicate<Pokemon>, String> conditions) {
        this.conditions = conditions;
    }

    @Override
    public String toString() {
        return "Attribute{" +
                "name='" + name + '\'' +
                '}';
    }
}

package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Node {
    private Map<Pokemon,Boolean> pokemons;
    private List<Attribute> remainingAttributes;
    private String label = null;
    private Double entropy;
    private Node parent;
    private List<Node> children = new ArrayList<>();
    private Attribute attribute;
    private String parentAttributeRespose;

    public Node() {
    }

    public Node(Map<Pokemon, Boolean> pokemons, List<Attribute> remainingAttributes, String label, Double entropy, Node parent, String parentAttributeRespose, Attribute attribute) {
        this.pokemons = pokemons;
        this.remainingAttributes = remainingAttributes;
        this.label = label;
        this.entropy = entropy;
        this.parent = parent;
        this.parentAttributeRespose = parentAttributeRespose;
        this.attribute = attribute;
    }

    public Map<Pokemon, Boolean> getPokemons() {
        return pokemons;
    }

    public void setPokemons(Map<Pokemon, Boolean> pokemons) {
        this.pokemons = pokemons;
    }

    public List<Attribute> getRemainingAttributes() {
        return remainingAttributes;
    }

    public void setRemainingAttributes(List<Attribute> remainingAttributes) {
        this.remainingAttributes = remainingAttributes;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Double getEntropy() {
        return entropy;
    }

    public void setEntropy(Double entropy) {
        this.entropy = entropy;
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public List<Node> getChildren() {
        return children;
    }

    public void setChildren(List<Node> children) {
        this.children = children;
    }

    public Attribute getAttribute() {
        return attribute;
    }

    public void setAttribute(Attribute attribute) {
        this.attribute = attribute;
    }

    public String getParentAttributeRespose() {
        return parentAttributeRespose;
    }

    public void setParentAttributeRespose(String parentAttributeRespose) {
        this.parentAttributeRespose = parentAttributeRespose;
    }

    @Override
    public String toString() {
        return "Node{" +
//                "pokemons=" + pokemons +
//                ", remainingAttributes=" + remainingAttributes +
                ", label='" + label + '\'' +
                ", entropy=" + entropy +
//                ", parent=" + parent +
//                ", children=" + children +
                ", attribute=" + attribute +
                ", parentAttributeRespose='" + parentAttributeRespose + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Node node = (Node) o;

        if (pokemons != null ? !pokemons.equals(node.pokemons) : node.pokemons != null) return false;
        if (remainingAttributes != null ? !remainingAttributes.equals(node.remainingAttributes) : node.remainingAttributes != null)
            return false;
        if (label != null ? !label.equals(node.label) : node.label != null) return false;
        if (entropy != null ? !entropy.equals(node.entropy) : node.entropy != null) return false;
        if (parent != null ? !parent.equals(node.parent) : node.parent != null) return false;
        if (children != null ? !children.equals(node.children) : node.children != null) return false;
        if (attribute != null ? !attribute.equals(node.attribute) : node.attribute != null) return false;
        return parentAttributeRespose != null ? parentAttributeRespose.equals(node.parentAttributeRespose) : node.parentAttributeRespose == null;
    }

    @Override
    public int hashCode() {
        int result = pokemons != null ? pokemons.hashCode() : 0;
        result = 31 * result + (remainingAttributes != null ? remainingAttributes.hashCode() : 0);
        result = 31 * result + (label != null ? label.hashCode() : 0);
        result = 31 * result + (entropy != null ? entropy.hashCode() : 0);
        result = 31 * result + (parent != null ? parent.hashCode() : 0);
        result = 31 * result + (children != null ? children.hashCode() : 0);
        result = 31 * result + (attribute != null ? attribute.hashCode() : 0);
        result = 31 * result + (parentAttributeRespose != null ? parentAttributeRespose.hashCode() : 0);
        return result;
    }
}

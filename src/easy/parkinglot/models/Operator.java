package src.easy.parkinglot.models;

public class Operator {
    private String operatorId;
    private String name;

    public Operator(String operatorId, String name) {
        this.operatorId = operatorId;
        this.name = name;
    }

    public String getOperatorId() {
        return operatorId;
    }

    public String getName() {
        return name;
    }
}

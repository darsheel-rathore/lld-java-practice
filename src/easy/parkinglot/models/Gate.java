package src.easy.parkinglot.models;

import src.easy.parkinglot.enums.GateType;

public class Gate {
    private int gateId;
    private GateType gateType;
    private Operator operator;

    public Gate(int gateId, GateType gateType, Operator operator) {
        this.gateId = gateId;
        this.gateType = gateType;
        this.operator = operator;
    }

    public int getGateId() {
        return gateId;
    }

    public GateType getGateType() {
        return gateType;
    }

    public Operator getOperator() {
        return operator;
    }
}

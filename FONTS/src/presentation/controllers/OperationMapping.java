package presentation.controllers;

import models.operations.Operation;

class OperationMapping {
    boolean include;
    Class<? extends Operation> operation;

    public OperationMapping(boolean include, Class<? extends Operation> operation) {
        this.include = include;
        this.operation = operation;
    }
}
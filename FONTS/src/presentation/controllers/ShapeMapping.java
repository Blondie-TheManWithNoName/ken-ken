package presentation.controllers;

import models.topologies.Shape;

class ShapeMapping {
    boolean include;
    Shape shape;

    public ShapeMapping(boolean include, Shape shape) {
        this.include = include;
        this.shape = shape;
    }
}
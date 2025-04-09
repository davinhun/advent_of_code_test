package org.example;

public class DistanceCalculator {
    private static final String NORTH = "North";
    private static final String EAST = "East";
    private static final String WEST = "West";
    private static final String SOUTH = "South";

    private int x, y;
    private final String[] instructions;

    DistanceCalculator(String[] instructions) {
        this.instructions = instructions;
        this.x = 0;
        this.y = 0;
    }

    public void calculateDistance() {
        String currentDegree = NORTH;  //start degree
        for (var instruction : instructions) {
            char direction = instruction.charAt(0);
            int quantity = Integer.parseInt(instruction.substring(1));


            currentDegree = changeDegree(currentDegree, direction);
            updateCoordinates(currentDegree, quantity);
        }
        printResult();
    }

    private void printResult() {
        int result = Math.abs(x) + Math.abs(y);
        System.out.println("Shortest path from (0,0) to (" + x + "," + y + ") is " + result);
    }

    private void updateCoordinates(String currentDegree, int quantity) {
        switch (currentDegree) {
            case NORTH -> y = y + quantity;
            case SOUTH -> y = y - quantity;
            case EAST -> x = x + quantity;
            case WEST -> x = x - quantity;
        }
    }

    private String changeDegree(String degree, char turn) {
        return switch (degree) {
            case NORTH -> turn == 'L' ? WEST : EAST;
            case EAST -> turn == 'L' ? NORTH : SOUTH;
            case SOUTH -> turn == 'L' ? EAST : WEST;
            case WEST -> turn == 'L' ? SOUTH : NORTH;
            default -> throw new IllegalArgumentException("Invalid degree: " + degree);
        };
    }
}
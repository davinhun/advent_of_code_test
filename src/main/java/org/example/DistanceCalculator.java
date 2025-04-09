package org.example;

import java.util.HashSet;
import java.util.Set;

public class DistanceCalculator {
    private static final String NORTH = "North";
    private static final String EAST = "East";
    private static final String WEST = "West";
    private static final String SOUTH = "South";

    private final String[] instructions;
    private final Set<String> visitedLocations;
    private String visitedTwice;
    private int x, y;

    DistanceCalculator(String[] instructions) {
        this.instructions = instructions;
        this.visitedLocations = new HashSet<>();
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

    private void updateCoordinates(String degree, int quantity) {
        switch (degree) {
            case NORTH -> {
                for (int i = 0; i < quantity; i++) {
                    y++;
                    updateVisitedLocations();
                }
            }
            case SOUTH -> {
                for (int i = 0; i < quantity; i++) {
                    y--;
                    updateVisitedLocations();
                }
            }
            case EAST -> {
                for (int i = 0; i < quantity; i++) {
                    x++;
                    updateVisitedLocations();
                }
            }
            case WEST -> {
                for (int i = 0; i < quantity; i++) {
                    x--;
                    updateVisitedLocations();
                }
            }
            default -> throw new IllegalArgumentException("Invalid degree: " + degree);
        }
    }

    private void updateVisitedLocations() {
        String location = x + "," + y;
        if (visitedLocations.contains(location) && visitedTwice == null) {
            visitedTwice = location;
        }
        visitedLocations.add(location);
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

    private void printResult() {
        int result = Math.abs(x) + Math.abs(y);
        System.out.println("Shortest path to [" + x + "," + y + "] is " + result);

        if (visitedTwice != null) {
            String[] parts = visitedTwice.split(",");
            int visitedX = Integer.parseInt(parts[0]);
            int visitedY = Integer.parseInt(parts[1]);
            int visitedDistance = Math.abs(visitedX) + Math.abs(visitedY);
            System.out.println("First location visited twice is [" + visitedX + "," + visitedY + "], distance is " + visitedDistance);
        }
    }
}
package org.example;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {


    public static void main(String[] args) throws IOException {

        String inputFile = Files.readString(Paths.get("src/main/resources/input.txt"));
        String[] instructions = inputFile.replaceAll("[\\r\\n]", "").split(", ");

        DistanceCalculator distanceCalculator = new DistanceCalculator(instructions);
        System.out.println("Shortest path is " + distanceCalculator.calculateDistance());

    }

}
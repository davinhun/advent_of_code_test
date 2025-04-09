package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class DistanceCalculatorTest {


    @Test
    public void testWithoutVisitedLocation() {
        String[] input = {"R4", "R1", "L2"};

        DistanceCalculator distanceCalculator = new DistanceCalculator(input);

        assertEquals(7, distanceCalculator.calculateDistance());
        assertNull(distanceCalculator.getVisitedTwiceLocation());
    }

    @Test
    public void testWithVisitedLocationTwice() {
        String[] input = {"R8", "R4", "R4", "R8", "L8", "L4", "L4", "L8"};

        DistanceCalculator distanceCalculator = new DistanceCalculator(input);

        assertEquals(8, distanceCalculator.calculateDistance());
        assertEquals(4, distanceCalculator.getVisitedTwiceLocation());
    }

}
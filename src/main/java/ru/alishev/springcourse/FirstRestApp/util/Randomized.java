package ru.alishev.springcourse.FirstRestApp.util;

import ru.alishev.springcourse.FirstRestApp.models.Device;

import java.util.Random;

public class Randomized {
    private final Random random = new Random();

    public Device randomSensor() {
        Device device = new Device();
        var v = Math.random() * 3;
        if (v < 1) {
            device.setName("Test1");
        } else if (v >= 1) {
            device.setName("Test2");
        } else {
            device.setName("Test3");
        }
        return device;
    }

    public boolean randomRaining() {
        return getRandomInt();
    }

    public double randomValue() {
        if (getRandomInt()) {
            return random.nextDouble() * 100;
        } else {
            return -random.nextDouble() * 100;
        }
    }

    private boolean getRandomInt() {
        return random.nextInt() * 4 <= 1;
    }
}

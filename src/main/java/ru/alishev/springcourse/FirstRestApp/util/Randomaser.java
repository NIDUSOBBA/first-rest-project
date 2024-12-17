package ru.alishev.springcourse.FirstRestApp.util;

import ru.alishev.springcourse.FirstRestApp.models.Sensor;

import java.util.Random;

public class Randomaser {
    private final Random random = new Random();

    public Sensor randomSensor() {
        Sensor sensor = new Sensor();
        if(Math.random()*3 < 1){
            sensor.setName("Test1");
        } else if (Math.random()*3 >= 1) {
            sensor.setName("Test2");
        }else {
            sensor.setName("Test3");
        }
        return sensor;
    }

    public boolean randomRaining(){
        return int1();
    }

    public double randomValue(){
        if(int1()){
            return random.nextDouble()*100;
        }else {
            return -random.nextDouble()*100;
        }
    }

    private boolean int1(){
        return random.nextInt()*4 <= 1;
    }
}

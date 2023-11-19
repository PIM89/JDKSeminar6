package org.example;


import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset;

import java.util.Random;

public class MontyHall {
    private int iteration;
    private Random random = new Random();
    private Multiset<String> resWithoutChose = HashMultiset.create();
    private Multiset<String> resByChose = HashMultiset.create();

    public int getWinWithoutChose() {
        return resWithoutChose.count("Win");
    }

    public int getWinByChose() {
        return resByChose.count("Win");
    }

    public MontyHall(int iteration) {
        this.iteration = iteration;
        start();
    }

    public void start() {
        System.out.println("Игрок не меняет дверь.");
        for (int i = 1; i <= iteration; i++) {
            int prizeDoor = random.nextInt(3);
            int userDoor = random.nextInt(3);
            if (userDoor == prizeDoor) {
                resWithoutChose.add("Win");
            } else {
                resWithoutChose.add("Lose");
            }
        }
        int winWithoutChose = resWithoutChose.count("Win");
        System.out.println("Количество побед: " + winWithoutChose + " из " + iteration);
        System.out.println("Процент побед: " + ((double) winWithoutChose / iteration) * 100);
        System.out.println();

        System.out.println("Игрок всегда меняет дверь.");

        for (int i = 1; i <= iteration; i++) {
            int prizeDoor = random.nextInt(3);
            int userDoor = random.nextInt(3);
            int wrongDoor = prizeDoor;
            while (wrongDoor == prizeDoor || wrongDoor == userDoor) {
                wrongDoor = random.nextInt(3);
            }
            int alternateDoor = 3 - (userDoor + wrongDoor);
            if (alternateDoor == prizeDoor) {
                resByChose.add("Win");
            } else {
                resByChose.add("Lose");
            }
        }
        int winByChose = resByChose.count("Win");
        System.out.println("Количество побед: " + winByChose + " из " + iteration);
        System.out.println("Процент побед: " + ((double) winByChose / iteration) * 100);
    }
}

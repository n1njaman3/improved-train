/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package brdlbrdon12418tk1rand23441;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author bradley.blanchard
 */
public class BrdlBrdOn12418tk1Rand23441 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        Random rand = new Random();
        Scanner playerInput = new Scanner(System.in);
        Character PlayerOne = new Player("PC", 1);
        Room enterHall = new Room("Hallway");
        List<Room> roomOrder = new ArrayList();
        List<Character> enemiesGenTab = new ArrayList();
        List<Character> bossFights = new ArrayList();
        int bossChance = 0;
        for (int i = 0; i < 101; i++) {
            Character enemyGen = new Enemy("NPC", 1);
            enemyGen.selectClass();
            enemyGen.calculateArmor();
            enemyGen.getName();
            switch (enemyGen.Class) {
                case "Bandit":
                    enemyGen.equip(new Sword("Wood Club", 4, 1), enemyGen);
                    break;
                case "Zombie":
                    enemyGen.equip(new NaturalWeapon("Rotten fist", 4, 1), enemyGen);
                    break;
                case "Lion":
                    enemyGen.equip(new NaturalWeapon("Claw", 3, 0), enemyGen);
                    break;
                case "Boar":
                    enemyGen.equip(new NaturalWeapon("Tusk", 5, 0), enemyGen);
                    break;
                case "Apprentice":
                    enemyGen.equip(new Wand("Flame Burst", 7, 0), enemyGen);
                    break;
                case "Grunt":
                    enemyGen.equip(new Sword("Well-Used Sword", 5, 0), enemyGen);
                    break;
            }
            enemiesGenTab.add(enemyGen);
        }
        for (int i = 0; i < 5; i++) {
            Boss bossGen = new Boss("NPC", 1);
            bossGen.selectClass();
            bossGen.calculateArmor();
            bossGen.getName();
            switch (bossGen.Name) {
                case "Urk the Dull":
                    bossGen.equip(new Sword("Bulging Club", 7, 1), bossGen);
                    break;
                case "Ralzan Crimsonfire":
                    bossGen.equip(new Wand("Crimson Blast", 10, 0), bossGen);
                    break;
                case "Sargeant Tomt":
                    bossGen.equip(new Sword("Steel Sword", 6, 2), bossGen);
                    break;
            }
            bossGen.powerData();
            bossFights.add(bossGen);
        }

        PlayerOne.getName();
        PlayerOne.getData();
        while (PlayerOne.Class == null) {
            PlayerOne.selectClass();
            PlayerOne.powerData();
        }
        PlayerOne.calculateArmor();
        switch (PlayerOne.Class) {
            case "Soldier":
                PlayerOne.equip(new Sword("Old Sword", 6, 0), PlayerOne);
                break;
            case "Wizard":
                PlayerOne.equip(new Wand("Cheap Wand", 8, 0), PlayerOne);
                break;
            case "Null":
                PlayerOne.equip(new Sword("NullBlade", 12, 4), PlayerOne);
                break;
            default:
                PlayerOne.equip(null, PlayerOne);
                break;
        }

        Actions.move(enterHall, PlayerOne);

        roomOrder.add(enterHall);
        String roomType;
        while (PlayerOne.Armor > 0) {
            int roomExploreType = rand.nextInt(3);
            if (roomExploreType == 0) {
                roomType = "Hallway";
            } else {
                roomType = "Room";
            }
            Room currentRoom = new Room(roomType);
            System.out.println("Type 1 to move, Type 2 to see player info");
            int command = playerInput.nextInt();
            System.out.println("");
            if (command == 1) {
                Actions.move(currentRoom, PlayerOne);
                roomOrder.add(currentRoom);
                if ("Room".equals(roomType)) {
                    int monsterChance = rand.nextInt(2);
                    switch (monsterChance) {
                        case 0:
                            if (!(bossFights.isEmpty()) && bossChance == 4) {
                                bossFights.get(0).getData();
                                Actions.bossFight(PlayerOne, (Boss) bossFights.get(0));
                                bossFights.remove(0);
                                bossChance = 0;
                            } else {
                                enemiesGenTab.get(0).getData();
                                Actions.fight(PlayerOne, enemiesGenTab.get(0));
                                enemiesGenTab.remove(0);
                                bossChance++;
                            }
                            break;

                        case 1:
                            System.out.println("There are no enemies in this room.");
                            break;
                    }
                } else if ("Hallway".equals(roomType)) {
                    int trapChance = rand.nextInt(2);
                    switch (trapChance) {
                        case 0:
                            Trap newTrap = new Trap("Trap");
                            newTrap.getTrapType();
                            newTrap.trapIsSprung(PlayerOne);
                            break;
                        case 1:
                            System.out.println("You are clear to continue on");
                            break;
                    }
                }
            } else if (command == 2) {
                PlayerOne.getData();
            }
        }

    }

}

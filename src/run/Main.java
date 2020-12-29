package run;

import model.ChargingSocket;
import model.ChargingStation;
import model.Robot;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

//        ChargingSocket cs = new ChargingSocket('1');
//
//        Robot r1 = new Robot("robot1", 2, cs);
//        Robot r2 = new Robot("robot2", 1, cs);
//
//        Thread robot1 = new Thread(r1);
//        Thread robot2 = new Thread(r2);
//
//
//        r1.setName("robot");
//        r2.setName("robot2");
//
//        robot1.start();
//        robot2.start();

//        ChargingStation cs = new ChargingStation(10);
//
//        int tab[] = cs.findFreeSockets();
//
//        for (int i = 0; i < tab.length; i++) {
//            System.out.println(i + "      = " + tab[i]);
//        }



        ChargingStation chargingStation = new ChargingStation(3);
        Robot r1 = new Robot("1", 3, chargingStation);
        Robot r2 = new Robot("2", 1, chargingStation);
        Robot r3 = new Robot("3", 1, chargingStation);

        Thread t1 = new Thread(r1);
        Thread t2 = new Thread(r2);
        Thread t3 = new Thread(r3);

        t1.start();
        t2.start();
        t3.start();
    }
}
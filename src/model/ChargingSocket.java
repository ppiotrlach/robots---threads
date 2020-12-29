package model;

public class ChargingSocket {
    private char socketName;
    private boolean isEmpty = true;
    public ChargingSocket(char name) {
        socketName = name;
    }

    public synchronized void chargeRobot(Robot r){
        String rName = r.getRobotName();
        int rBattery = r.getBatterySize();

        System.out.println("connecting " + rName + " to charge socket - " + socketName);
        isEmpty = false;
        try {
            System.out.println(rName + " is charging...");
            r.sleep(rBattery*100*10);
            System.out.println(rName + " successfully charged");
        } catch (InterruptedException e) {
            System.out.println(rName + " INTERRRRRUPTED");
        }

        isEmpty = true;
    }

    public boolean isEmpty(){
        return isEmpty;
    }
}

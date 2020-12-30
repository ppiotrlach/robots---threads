package model;

public class Robot extends Thread {

    private String name;
    private int batterySize;
    private int batteryCondition = 100;
    private Object lock;
    public ChargingStation chargingStation;
    private RobotState robotState;

    public Robot(String name, int batterySize, ChargingStation chargingStation) {
        this.name = name;
        this.batterySize = batterySize;
        this.chargingStation = chargingStation;
        setRobotState(RobotState.WORKING);
    }

    public void setRobotState(RobotState robotState) {
        this.robotState = robotState;
    }

    @Override
    public void run() {
        while (true) {
            work();
        }
    }

    public void work() {
        setRobotState(RobotState.WORKING);
        for (int i = 0; i < 100 * batterySize; i++) {
            if(batteryCondition <= 0) break;
            System.out.println(name + " = " + batteryCondition / batterySize + "%");
            batteryCondition--;
            try {
                sleep(10);
            } catch (InterruptedException e) {
                System.out.println(name + " INTERRRRUPTED");
            }
        }

        charge(chargingStation);
    }

    public void charge(ChargingStation chargingStation) {
        chargingStation.charge(this);
    }

    public String getRobotName() {
        return name;
    }

    public int getBatterySize() {
        return batterySize;
    }

    public Object getLock() {
        return lock;
    }

    public String toString() {
        String description = "               " + name + "      " + batterySize + "               " + batteryCondition / batterySize + "%         " + robotState;

        return description;
    }


    public void addBatteryCondition(int i) {
        this.batteryCondition += i;
    }

    public int getBatteryCondition() {
        return batteryCondition;
    }

    public int getFullBatteryCondition(){
        return 100*batterySize;
    }
}

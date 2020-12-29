package model;

public class Robot extends Thread {

    private String name;
    private int batterySize;
    private int batteryCondition;
    private Object lock;
    public ChargingStation chargingStation;
    private RobotState robotState;

    public Robot(String name, int batterySize, ChargingStation chargingStation) {
        this.name = name;
        this.batterySize = batterySize;
        this.chargingStation = chargingStation;
    }

    public void setRobotState(RobotState robotState){
        this.robotState = robotState;
    }

    @Override
    public void run() {
        while(true){
            setRobotState(RobotState.WORKING);
            work();
            charge(chargingStation);
        }
    }

    public void work(){
        batteryCondition = 100*batterySize;
        for (int i = 0; i < 100*batterySize; i++) {
            System.out.println(name + " = " + batteryCondition/batterySize + "%");
            batteryCondition--;
        }
    }

    public void charge(ChargingStation chargingStation){
        chargingStation.charge(this);
    }

    public String getRobotName(){
        return name;
    }

    public int getBatterySize(){
        return batterySize;
    }

    public Object getLock(){
        return lock;
    }



}

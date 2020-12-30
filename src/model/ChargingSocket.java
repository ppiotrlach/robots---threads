package model;

public class ChargingSocket {
    private char socketName;
    private boolean isEmpty = true;
    private Robot r;


    public ChargingSocket(char name) {
        socketName = name;
    }


    public synchronized void chargeRobot(Robot r){
        this.r = r;
        String rName = r.getRobotName();

        System.out.println("connecting " + rName + " to charge socket - " + socketName);
        isEmpty = false;
        try {
            System.out.println(rName + " is charging...");
            while(r.getBatteryCondition()<=r.getFullBatteryCondition()){
                r.setRobotState(RobotState.CHARGING);
                r.sleep(10);
                r.addBatteryCondition(1);
                System.out.println("&*^$*&#@$^)$*@&#^*&($#@*($#_@$&#@$@#      " + rName +  "    " + r.getBatteryCondition());
            }

            System.out.println(rName + " successfully charged");
        } catch (InterruptedException e) {
            System.out.println(rName + " INTERRRRRUPTED");
        }

        isEmpty = true;
        this.r = null;
    }

    public boolean isEmpty(){
        return isEmpty;
    }

    public String whoIsHere(){
        String temp = "  ";
        if(r!=null)  temp = r.getRobotName();
        return temp;
    }

    public char getSocketName() {
        return socketName;
    }
}

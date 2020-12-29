package model;

import java.util.ArrayList;
import java.util.List;

public class ChargingStation {
    int stationSize = 1;
    private char alphabet[] = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
    private List<ChargingSocket> sockets;
    private List<Robot> robots;


    public ChargingStation(int greatestBatterySize) {
        stationSize = greatestBatterySize;
        createStation();
    }

    private void createStation() {
        sockets = new ArrayList<>();
        for (int i = 0; i < stationSize; i++) {
            sockets.add(new ChargingSocket(alphabet[i]));
        }
    }

    public void charge(Robot r) {
        boolean isCharging = false;
        int size = r.getBatterySize();
        int availableSize = 0;
        int freesockets[] = findFreeSockets();

        for(int i = 0; i < freesockets.length;  i++){
            if(freesockets[i] == 1){
                availableSize++;
                if(availableSize == size){
                    for(int j = i; j > i - size; j--){
                        sockets.get(j).chargeRobot(r);

                    }
                    isCharging = true;
                }
            }else availableSize = 0;
        }

        if(!isCharging){
            if (freesockets[0] == 1){
                sockets.get(0).chargeRobot(r);
                isCharging = true;
            }
            if(!isCharging){
                if (freesockets[freesockets.length-1] == 1){
                    sockets.get(freesockets.length-1).chargeRobot(r);
                    isCharging = true;
                }
            }
        }

    }

    public int[] findFreeSockets() {
        int freeSockets[] = new int[stationSize];

        for (int i = 0; i < sockets.size(); i++) {
            if (sockets.get(i).isEmpty()) {
                freeSockets[i] = 1;
            } else freeSockets[i] = 0;
        }
        return freeSockets;
    }

}


public class World {

    private Antena antena1,antena2,antena3;
    private Robot robot;
    private final int width=400;
    private final  int height= 400;

    final private int scale = 5;

    public World() {
        // trzeba potestować
        /*
        int xR=4;
        int yR=2;
        int xA1=3;
        int yA1=6;
        int xA2=6;
        int yA2=3;
        int xA3=2;
        int yA3=2;
        */

        int xR=30;
        int yR=20;
        int xA1=10;
        int yA1=10;
        int xA2=40;
        int yA2=10;
        int xA3=10;
        int yA3=50;

        robot = new Robot(xR,yR);
        antena1=new Antena(xA1,yA1);
        antena2=new Antena(xA2,yA2);
        antena3=new Antena(xA3,yA3);
    }



    public boolean isRobotInside() {

        double signal0Antena1, signalRightAntena1, signalLeftAntena1, signalForwardAntena1, signalBackAntena1;
        double signal0Antena2, signalRightAntena2, signalLeftAntena2, signalForwardAntena2, signalBackAntena2;
        double signal0Antena3, signalRightAntena3, signalLeftAntena3, signalForwardAntena3, signalBackAntena3;

        signal0Antena1 = robot.readSignal(antena1);
        signal0Antena2 = robot.readSignal(antena2);
        signal0Antena3 = robot.readSignal(antena3);

        robot.moveRight();
        signalRightAntena1 = robot.readSignal(antena1);
        signalRightAntena2 = robot.readSignal(antena2);
        signalRightAntena3 = robot.readSignal(antena3);

        robot.moveLeft();
        robot.moveLeft();
        signalLeftAntena1=robot.readSignal(antena1);
        signalLeftAntena2=robot.readSignal(antena2);
        signalLeftAntena3=robot.readSignal(antena3);

        robot.moveRight();
        robot.moveForward();
        signalForwardAntena1=robot.readSignal(antena1);
        signalForwardAntena2=robot.readSignal(antena2);
        signalForwardAntena3=robot.readSignal(antena3);

        robot.moveBack();
        robot.moveBack();
        signalBackAntena1=robot.readSignal(antena1);
        signalBackAntena2=robot.readSignal(antena2);
        signalBackAntena3=robot.readSignal(antena3);

        robot.moveForward();

        // warunek - 2 odległości wzrosły i jedna zmalała lub 2 odległości zmalały i jedna wzrosła
        int checkRight; // 1 - warunek spełniony, 0 - warunek nie jest spełniony
        int checkLeft;
        int checkForward;
        int checkBack;

        //check right;
        int checkR1;
        int checkR2;
        int checkR3;
        double checkRightAntena1 = signalRightAntena1 - signal0Antena1; // liczba ujemna - odległość zmalała
                                                                        // liczba dodatnia - odległość wzrosła
        if(checkRightAntena1<0) checkR1=-1;
        else checkR1=1;
        double checkRightAntena2 =  signalRightAntena2 - signal0Antena2;
        if(checkRightAntena2<0) checkR2=-1;
        else checkR2=1;
        double checkRightAntena3 =  signalRightAntena3 - signal0Antena3;
        if(checkRightAntena3<0) checkR3=-1;
        else checkR3=1;
        if((checkR1+checkR2+checkR3)==-1 || (checkR1+checkR2+checkR3)==1) checkRight=1;
        else checkRight=0;

        //check left
        int checkL1;
        int checkL2;
        int checkL3;
        double checkLeftAntena1 = signalLeftAntena1 - signal0Antena1;
        if(checkLeftAntena1<0) checkL1=-1;
        else checkL1=1;
        double checkLeftAntena2 =  signalLeftAntena2 - signal0Antena2;
        if(checkLeftAntena2<0) checkL2=-1;
        else checkL2=1;
        double checkLeftAntena3 =  signalLeftAntena3 - signal0Antena3;
        if(checkLeftAntena3<0) checkL3=-1;
        else checkL3=1;
        if((checkL1+checkL2+checkL3)==-1 || (checkL1+checkL2+checkL3)==1) checkLeft=1;
        else checkLeft=0;

        //check forward
        int checkF1;
        int checkF2;
        int checkF3;
        double checkForwardAntena1 = signalForwardAntena1 - signal0Antena1;
        if(checkForwardAntena1<0) checkF1=-1;
        else checkF1=1;
        double checkForwardAntena2 = signalForwardAntena2 - signal0Antena2;
        if(checkForwardAntena2<0) checkF2=-1;
        else checkF2=1;
        double checkForwardAntena3 = signalForwardAntena3 - signal0Antena3;
        if(checkForwardAntena3<0) checkF3=-1;
        else checkF3=1;
        if((checkF1+checkF2+checkF3)==-1 || (checkF1+checkF2+checkF3)==1) checkForward=1;
        else checkForward=0;

        //check back
        int checkB1;
        int checkB2;
        int checkB3;
        double checkBackAntena1 = signalBackAntena1 - signal0Antena1;
        if(checkBackAntena1<0) checkB1=-1;
        else checkB1=1;
        double checkBackAntena2 = signalBackAntena2 - signal0Antena2;
        if(checkBackAntena2<0) checkB2=-1;
        else checkB2=1;
        double checkBackAntena3 = signalBackAntena3 - signal0Antena3;
        if(checkBackAntena3<0) checkB3=-1;
        else checkB3=1;
        if((checkB1+checkB2+checkB3)==-1 || (checkB1+checkB2+checkB3)==1) checkBack=1;
        else checkBack=0;

        // final check
        int sum = checkRight+checkLeft+checkForward+checkBack;
        if(sum==4) return true;
        else return false;
    }

    public static void main(String[] args) {
        World world = new World();
        boolean check = world.isRobotInside(); //działa lepiej dla współrzędnych>10
        if(check) System.out.println("inside");
        else System.out.println("outside");
    }
}

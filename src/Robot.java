public class Robot {
    int x;
    int y;

    double readSignal(Antena antena){
        return antena.getSignal(x,y);
    }

}

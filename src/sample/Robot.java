package sample;

class Robot {
    private int xR;
    private int yR;
    Robot(int xR, int yR) {
        this.xR=xR;
        this.yR=yR;
    }

    int getX() {return xR;}
    int getY() {return yR;}

    private double readSignal(Antena antena){
        return antena.getSignal(xR,yR);
    }

    /**
     *
     * @param antena antena dla której szukamy względnych współrzędnych (wzgledem położenia robota)
     * @return tablica [coordX,coordY] gdzie coordX,coordY - względne współrzędne anteny
     */
    private int[] findSourceCoordinate(Antena antena) {
        int coordX=0,coordY=0;
        double signal1 = antena.getSignal(xR,yR);
        double signal2;
        xR--;
        signal2=antena.getSignal(xR,yR);
        if(signal2>signal1) {
            coordX--;
            while(signal2>signal1) {
                xR--;
                coordX--;
                signal1=signal2;
                signal2=antena.getSignal(xR,yR);
            }
            xR++; //powrót do najsilnieszego sygnału
            coordX++;
        }else {
            xR++; //powrót do poz (0,0)
            xR++;
            coordX++;
            signal2 = antena.getSignal(xR, yR);
            while (signal2 > signal1) {
                xR++;
                coordX++;
                signal1 = signal2;
                signal2 = antena.getSignal(xR, yR);
            }
            xR--;
            coordX--;
        }
        yR--;
        signal2=antena.getSignal(xR,yR);
        if(signal2>signal1) {
            coordY--;
            while(signal2>signal1) {
                yR--;
                coordY--;
                signal1=signal2;
                signal2=antena.getSignal(xR,yR);
            }
            yR++; //powrót do najsilnieszego sygnału
            coordY++;
        }else {
            yR++; //powrót do poz (0,0)
            yR++;
            coordY++;
            signal2 = antena.getSignal(xR, yR);
            while (signal2 > signal1) {
                yR++;
                coordY++;
                signal1 = signal2;
                signal2 = antena.getSignal(xR, yR);
            }
            yR--;
            coordY--;
        }
        int[] coordArray = {coordX,coordY};
        xR=xR-coordX;
        yR=yR-coordY;
        return coordArray;
    }

    /**
     * 
     * @param x1 pierwsza współrzędna pierwszego punktu
     * @param y1 druga współrzędna pierwszego punktu
     * @param x2 pierwsza współrzędna drugiego punktu
     * @param y2 druga współrzędna drugiego punktu
     * @return tablica przedstawiająca prostą przechodzącą przez punkty o współrzędnych (x1,y1) i (x2,y2), dla prostej w postaci cy=ax+b tablica to [a,b,c]
     */
    private double[] findLine(int x1, int y1, int x2, int y2) {
        if(x2==x1) {
            double[] line = {1,-x1,0};
            return line;
        }
        double a = (double)(y2-y1)/(double)(x2-x1);
        double b = (double) y1 - a*(double)x1;
        double[] line = {a,b,1}; //współczynniki a,b i wspł. przy y
        return line;
    }

    /**
     *
     * @param line1 tablica zawierająca informacje o prostej w formacie [a,b,c] dla prostej cy=ax+b
     * @param line2 jw.
     * @return tablica [x,y] gdzie x,y - punkt przecięcia prostych line1 i line2
     * @throws NoCrossLineException wyjątek wyrzucany w przypadku prostych równoległych - brak punktu przecięcia
     * @throws CrossLineException wyjątek wyrzucany w przypadku gdy line1 i line2 są tymi samymi prostymi - nieskończenie wiele punktów przecięcia
     */

    private double[] findCrossPoint(double[] line1, double[] line2) throws NoCrossLineException, CrossLineException {

        double a1 = line1[0];
        double b1 = line1[1];
        double a2 = line2[0];
        double b2 = line2[1];

        if(line1[2]==0 && line2[2]!=0) {
            double x=-b1;
            double y=a2*x+b2;
            double[] point = {x,y};
            return point;
        } else if(line2[2]==0 && line1[2]!=0) {
            double x=-b2;
            double y=a1*x+b1;
            double[] point = {x,y};
            return point;
        } else if(line1[2]==0 && line2[2]==0) {
            if(a1==a2) throw new CrossLineException();
            else throw new NoCrossLineException();
        }
        if(a1==a2&&b1==b2) throw new CrossLineException();
        if(a1==a2) throw new NoCrossLineException();
        if(b1==b2) {
            double[] point = {0,b1};
            return point;
        }
        double x = (b2-b1)/(a1-a2);
        double y = a2*x+b2;
        double[] point = {x,y};
        return point;
    }

    /**
     *
     * @param antena1 antena dla której przeprowadzane jest początkowe sprawdzenie (sprawdzenie czy przy ruchu robota w prawo, lewo, górę i dół sygnały maleją czy rosną)
     * @param antena2 jw.
     * @param antena3 jw.
     * @return wynik logiczny początkowego sprawdzenia
     */

    private boolean firstInCheck(Antena antena1, Antena antena2, Antena antena3) {
        double signal0Antena1, signalRightAntena1, signalLeftAntena1, signalForwardAntena1, signalBackAntena1;
        double signal0Antena2, signalRightAntena2, signalLeftAntena2, signalForwardAntena2, signalBackAntena2;
        double signal0Antena3, signalRightAntena3, signalLeftAntena3, signalForwardAntena3, signalBackAntena3;

        signal0Antena1 = readSignal(antena1);
        signal0Antena2 = readSignal(antena2);
        signal0Antena3 = readSignal(antena3);

        xR++;
        signalRightAntena1 = readSignal(antena1);
        signalRightAntena2 = readSignal(antena2);
        signalRightAntena3 = readSignal(antena3);

        xR--;
        xR--;
        signalLeftAntena1=readSignal(antena1);
        signalLeftAntena2=readSignal(antena2);
        signalLeftAntena3=readSignal(antena3);

        xR++;
        yR++;
        signalForwardAntena1=readSignal(antena1);
        signalForwardAntena2=readSignal(antena2);
        signalForwardAntena3=readSignal(antena3);

        yR--;
        yR--;
        signalBackAntena1=readSignal(antena1);
        signalBackAntena2=readSignal(antena2);
        signalBackAntena3=readSignal(antena3);

        yR++;

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
        if(sum==4 || sum==3) return true;
        else return false;
    }

    /**
     *
     * @param A1 dwuelementowa tablica ze współrzędnymi [x,y]
     * @param A2 jw.
     * @param A3 jw.
     * @return wynik logiczny sprawdzenia czy współrzędne robota znajdują się wewnątrz punktów wyznaczonych przez A1,A2 i A3, sprawdzenie polega na
     * utworzeniu prostych A1A2 oraz A3R i zbadaniu czy punkt ich przecięcia istnieje oraz czy znajduje się na boku trójkąta, badanie warunku jest powtórzone
     * dla prostych A1A3 i A2R oraz A2A3 i A1R, jeśli wszystkie trzy warunki są spełnione oznacza to, że robot znajduje się w trójkącie wyznaczonym przez anteny i funkcja zwraca true.
     */

    private boolean finalInCheck(int[] A1, int[] A2, int[] A3) {//crosslineexc-te same proste, nocrosslineexc-rownolegle
        int xA1 = A1[0];
        int yA1 = A1[1];
        int xA2 = A2[0];
        int yA2 = A2[1];
        int xA3 = A3[0];
        int yA3 = A3[1];
        int xR_=0;
        int yR_=0;

        // A1 A2
        double[] A1A2 = findLine(xA1,yA1,xA2,yA2);
        double[] A3R = findLine(xA3,yA3,xR_,yR_);
        double[] crossPointA1A2R;
        try {crossPointA1A2R = findCrossPoint(A1A2, A3R);
        }catch(NoCrossLineException e) {return false;}
        catch (CrossLineException e){return true;}
        double xC = crossPointA1A2R[0];
        double yC = crossPointA1A2R[1];
        boolean checkX1=false,checkY1=false;
        if(xA1<=xA2) {
            if(xC>=xA1&&xC<=xA2) checkX1=true;
        }else {
            if(xC>=xA2&&xC<=xA1) checkX1=true;
        }
        if(yA1<=yA2) {
            if(yC>=yA1&&yC<=yA2) checkY1=true;
        }else {
            if(yC>=yA2&&yC<=yA1) checkY1=true;
        }
        if(!(checkX1&&checkY1)) return false;

        // A1 A3
        double[] A1A3 = findLine(xA1,yA1,xA3,yA3);
        double[] A2R = findLine(xA2,yA2,xR_,yR_);
        double[] crossPointA1A3R;
        try {crossPointA1A3R = findCrossPoint(A1A3,A2R);
        }catch(NoCrossLineException e) {return false;}
        catch (CrossLineException e){return true;}
        xC = crossPointA1A3R[0];
        yC = crossPointA1A3R[1];
        boolean checkX2=false,checkY2=false;
        if(xA1<=xA3) {
            if(xC>=xA1&&xC<=xA3) checkX2=true;
        }else {
            if(xC>=xA3&&xC<=xA1) checkX2=true;
        }
        if(yA1<=yA3) {
            if(yC>=yA1&&yC<=yA3) checkY2=true;
        }else {
            if(yC>=yA3&&yC<=yA1) checkY2=true;
        }
        if(!(checkX2&&checkY2)) return false;

        // A2 A3
        double[] A2A3 = findLine(xA2,yA2,xA3,yA3);
        double[] A1R = findLine(xA1,yA1,xR_,yR_);
        double[] crossPointA2A3R;
        try {crossPointA2A3R = findCrossPoint(A2A3,A1R);
        }catch(NoCrossLineException e) {return false;}
        catch (CrossLineException e){return true;}
        xC = crossPointA2A3R[0];
        yC = crossPointA2A3R[1];
        boolean checkX3=false,checkY3=false;
        if(xA3<=xA2) {
            if(xC>=xA3&&xC<=xA2) checkX3=true; //kiedy rowne-> robot jest na linii, traktujemy jako w srodku?
        }else {
            if(xC>=xA2&&xC<=xA3) checkX3=true;
        }
        if(yA3<=yA2) {
            if(yC>=yA3&&yC<=yA2) checkY3=true;
        }else {
            if(yC>=yA2&&yC<=yA3) checkY3=true;
        }
        return (checkX3&&checkY3);
    }


    /**
     *
     * @param antena1 antena dla której będzie przeprowadzone finalne sprawdzenie
     * @param antena2 jw.
     * @param antena3 jw.
     * @return wynik logiczny finalnego sprawdzenia, w pierwszym kroku wykonywane jest sprawdzenie początkowe, jeśli ono nie określi jednoznacznie czy robot jest w środku
     * przeprowadzone jest sprawdzenie funkcją finalInCheck()
     */
    boolean inside(Antena antena1, Antena antena2, Antena antena3) {

        if(firstInCheck(antena1,antena2,antena3)) {
            int[] A1 = findSourceCoordinate(antena1);
            int[] A2 = findSourceCoordinate(antena2);
            int[] A3 = findSourceCoordinate(antena3);
            return finalInCheck(A1,A2,A3);
        }
        else return false;
    }
}

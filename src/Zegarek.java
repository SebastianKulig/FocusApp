import java.util.concurrent.TimeUnit;

public class Zegarek {
    int minuty = 25;
    int przerwa = 5;

    void setMinuty (int n) {
        minuty = n;
    }

    void setPrzerwa (int n) {
        przerwa = n;
    }

    void czasSkupienia () throws InterruptedException {
        int czas = minuty * 60;
        for (int i = czas; czas > 0; czas--) {
            TimeUnit.SECONDS.sleep(1);
            System.out.println(czas/60 + ":"+ czas%60); //TODO dostosowanie wyswietlania do aplikacji

        }
    }

    void czasPrzerwy () throws InterruptedException {
        int czas = przerwa * 60;
        for (int i = czas; czas > 0; czas--) {
            TimeUnit.SECONDS.sleep(1);
            System.out.println(czas/60 + ":"+ czas%60); //TODO dostosowanie wyswietlania do aplikacji

        }
    }

    public static void main (String[] args) throws InterruptedException {//TODO menu wyboru, dostosowanie dlugosci przerwy i skupienia, mozliwosc przerwania i resetu pracy
        Zegarek miernik = new Zegarek();
        miernik.setMinuty(1);
        miernik.czasSkupienia();

    }

}


package de.dieckie.raetsel;

public class Lueking {

    int sum;

    public Lueking(int sum) {
        this.sum = sum;
    }

    public boolean check1() {
        for(int i = 1; i <= sum / 2; i++) {
            if(Prim.isPrim(i) && Prim.isPrim(sum - i)) {
                return false;
            }
        }
        return true;
    }

}

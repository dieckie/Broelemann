package de.dieckie.raetsel;

public class Lueking {

    int sum;

    public Lueking(int sum) {
        this.sum = sum;
    }

    public boolean check1() {
        for(int i = 1; i <= sum / 2; i++) {
            if((Prim.isPrim(i) && Prim.isPrim(sum - i))) {
                if(i * (sum - i) > 1000) {
                    return false;
                }
            } else if((Prim.isPrim(i) && (sum - i == 1)) || (Prim.isPrim(sum - i) && (i == 1))) {
                return false;
            }
        }
        return true;
    }
//177382
}

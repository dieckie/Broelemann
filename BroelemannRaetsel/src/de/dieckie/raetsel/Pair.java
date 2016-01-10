package de.dieckie.raetsel;

public class Pair {

    int n1, n2, sum, dif, pro;

    public Pair(int n1, int n2) {
        this.n1 = n1;
        this.n2 = n2;
        sum = n1 + n2;
        dif = Math.abs(n1 - n2);
        pro = n1 * n2;
    }

    @Override
    public String toString() {
        return "{" + n1 + ", " + n2 + "}";
    }
}

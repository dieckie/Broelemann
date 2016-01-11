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

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Pair) {
            Pair p = (Pair) obj;
            if((p.n1 == n1 && p.n2 == n2) || (p.n1 == n2 && p.n2 == n1)) {
                return true;
            }
        }
        return false;
    }

    // @Override
    // public int compareTo(Pair o) {
    // if(this.equals(o)){
    // return 0;
    // } else {
    // return -1;
    // }
    // }

}

package de.dieckie.raetsel;

public class Broele {

    int product;

    public Broele(int product) {
        this.product = product;
    }

    public boolean check1() {
        return !Prim.isPrim(product);
    }

}

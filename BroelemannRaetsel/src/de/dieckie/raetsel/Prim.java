package de.dieckie.raetsel;

import java.util.ArrayList;
import java.util.List;

public class Prim {

    static int[] prim = new int[10000];

    public static void init() {
        int PrimBestimmung = 2;
        int PrimBestimmungHoch = 2;
        int prims = 0;

        while(prims < prim.length - 1) {

            if(PrimBestimmung == PrimBestimmungHoch) {
                prim[prims] = PrimBestimmung;
                prims++;
                PrimBestimmung++;
                PrimBestimmungHoch = 2;

            } else {
                if(PrimBestimmung % PrimBestimmungHoch == 0) {
                    PrimBestimmung++;
                    PrimBestimmungHoch = 2;
                } else {
                    PrimBestimmungHoch++;
                }
            }
        }
    }

    public static List<Integer> primfaktorzerlegung(int n) {
        List<Integer> primf = new ArrayList<Integer>();
        primf.add(n);
        boolean retry = true;
        while(retry) {
            retry = false;
            b1: for(int i = 0; i < primf.size(); i++) {
                int f = primf.get(i);
                int sqrt = (int) (Math.sqrt(f) + 1);
                for(int k = 0; k < prim.length; k++) {
                    int p = prim[k];
                    if(p > sqrt) {
                        continue b1;
                    }
                    if(p != f && f % p == 0) {
                        primf.set(i, f / p);
                        primf.add(p);
                        retry = true;
                        break b1;
                    }
                }
            }
        }
        return primf;
    }

    public static boolean isPrim(int n) {
        int sqrt = (int) Math.sqrt(n) + 1;
        for(int k = 0; k < prim.length; k++) {
            int p = prim[k];
            if(p > sqrt) {
                break;
            }
            if(p != n && n % p == 0) {
                return false;
            }
        }
        return true;
    }

}

package com.company;

import java.io.*;
import java.util.Scanner;
import java.io.Serializable;

public class Ympyra implements Serializable {
    private double sade;

    public Ympyra(double r) {
        sade = r;
    }

    public double getAla() {
        return Math.PI * sade * sade;
    }

    public double getSade() {
        return sade;
    }

    public String toString() {
        return "Ympyrän säde on " + sade + " ja pinta-ala on " + getAla();
    }

    public boolean equals(Ympyra y) {
        if (this.sade == y.getSade())
            return true;
        else
            return false;
    }

    public boolean suurempiKuin(Ympyra y) {
        if (this.getAla() > y.getAla())
            return true;
        else
            return false;
    }

    public static void main(String[] args) throws Exception {
        Ympyra[] kymmenenOliota = {
                new Ympyra(1),
                new Ympyra(2),
                new Ympyra(3),
                new Ympyra(4),
                new Ympyra(5),
                new Ympyra(6),
                new Ympyra(7),
                new Ympyra(8),
                new Ympyra(9),
                new Ympyra(10),
        };

        String tiedostonNimi = "Ympyrat.dat";
        ObjectOutputStream tiedosto = null;
        try {
            tiedosto = new ObjectOutputStream(new FileOutputStream(tiedostonNimi));
            tiedosto.writeObject(kymmenenOliota);
        } catch (Exception e) {
            e.printStackTrace();
        }
        ObjectInputStream tulostus = null;
        Ympyra[] oliot = new Ympyra[kymmenenOliota.length];
        try {
            tulostus = new ObjectInputStream(new FileInputStream(tiedostonNimi));
            oliot = (Ympyra[]) tulostus.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                if (tiedosto != null) tiedosto.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (tulostus != null) tulostus.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                System.out.println("Tiedosto on olemassa");
                for (Ympyra olio : oliot) {
                    System.out.println(olio.getSade());
                    tiedosto.close();
                }
            } catch (FileNotFoundException e) {
                System.out.println("Tiedostoa ei ole olemassa");
                System.exit(0);
            }
            Scanner input = new Scanner(System.in);
            System.out.println("anna ekan ympyrän säde: ");
            double s1 = input.nextDouble();
            System.out.println("anna tokan ympyrän säde: ");
            double s2 = input.nextDouble();
            Ympyra y1 = new Ympyra(s1);
            Ympyra y2 = new Ympyra(s2);
            System.out.println(y1);
            System.out.println(y2);
            if (y1.equals(y2))
                System.out.println("Ympyrät samat");
            else
                System.out.println("Ympyrät ei ole samat");
            if (y1.suurempiKuin(y2))
                System.out.println("Eka suurempi");
            else
                System.out.println("Eka ei ole suurempi");
        }
    }
}

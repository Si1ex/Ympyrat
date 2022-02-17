package com.company;
import java.io.*;


public class Pankkitili implements Serializable {
    private double vuosiKorko;
    private double saldo;

    /**
     * Pankkiili alkusaldolla.
     * @param saldo
     */
    public Pankkitili(double saldo) {
        this.saldo = saldo;
    }
    public Pankkitili(Pankkitili tili) {
        this.saldo = tili.getSaldo();
    }
    /**
     * Vuosikoron asetus
     * @param vuosiKorko
     */
    public void setVuosiKorko(double vuosiKorko) {
        this.vuosiKorko = vuosiKorko;
    }

    /**
     * Summan talletus tilille.
     * @param summa
     */
    public void talleta(double summa) {
        if (summa > 0){
            this.saldo += summa;
            System.out.println("Talletettiin " + summa);
        }
        else
            System.out.println("Rahamaaran pitaa olla positiivinen arvo!");
    }
    /**
     * Summan nosto tililta.
     * @param summa
     */
    public void nosta(double summa) {
        if (summa < 0)
            System.out.println("Rahamaaran pitaa olla positiivinen arvo!");
        else if (summa <= this.saldo) {
            this.saldo -= summa;
            System.out.println("Nostettiin " + summa);
        }
        else {
            System.out.println("Tililla ei ole riittavasti katetta.");
            System.out.println("Nostettiin " + this.saldo);
            this.saldo = 0;

        }
    }
    /**
     * Kuukausikoron lisays.
     */
    public void lisaaKorko() {
        this.saldo += (this.saldo * this.vuosiKorko / 100.0) / 12;
    }
    /**
     * Saldon tarkistus.
     * @return
     */
    public double getSaldo() {
        return saldo;
    }

    public static void main(String[] args) {
        Pankkitili[] kolmeOliota = {
                new Pankkitili(500),
                new Pankkitili(600),
                new Pankkitili(700),
        };
        String tiedostonNimi = "Tilit.dat";
        ObjectOutputStream tiedosto = null;
        try{
            tiedosto = new ObjectOutputStream(new FileOutputStream (tiedostonNimi));
            tiedosto.writeObject(kolmeOliota);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        ObjectInputStream tulostus = null;
        Pankkitili[] oliot = new Pankkitili[kolmeOliota.length];
        try{
            tulostus = new ObjectInputStream(new FileInputStream(tiedostonNimi));
            oliot = (Pankkitili[]) tulostus.readObject();}
        catch (IOException e){
            e.printStackTrace();
        }
        catch (ClassNotFoundException e){
            e.printStackTrace();
        }
        finally{
            try {
                if (tiedosto != null) tiedosto.close();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (tulostus != null) tulostus.close();
            }
            catch (IOException e){
                e.printStackTrace();
            }
            for (Pankkitili olio : oliot){
                System.out.println(olio.getSaldo());
            }
        }
    }
}

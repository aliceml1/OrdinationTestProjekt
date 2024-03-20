package ordination;

import java.time.LocalDate;

public class PN extends Ordination{

    private double antalEnheder;
    private int antalGangeGivet;
    public PN(LocalDate startDen, LocalDate slutDen, double antalEnheder) {
        super(startDen, slutDen);
        this.antalEnheder = antalEnheder;
    }

    /**
     * Registrerer at der er givet en dosis paa dagen givesDen
     * Returnerer true hvis givesDen er inden for ordinationens gyldighedsperiode og datoen huskes
     * Retrurner false ellers og datoen givesDen ignoreres
     * @param givesDen
     * @return
     */
    public boolean givDosis(LocalDate givesDen) {
        boolean kanDosisGives = false;
        if (givesDen.isAfter(getStartDen()) && givesDen.isBefore(getSlutDen()) ||
                givesDen.equals(getStartDen()) || givesDen.equals(getSlutDen())) {
            kanDosisGives = true;
            antalGangeGivet++;
        }
        return kanDosisGives;
    }

    public double doegnDosis() {
        return (getAntalGangeGivet() * antalEnheder) / (double) antalDage();
    }

    @Override
    public String getType() {
        return "PN";
    }

    public double samletDosis() {
        return getAntalEnheder() * (double) getAntalGangeGivet();
    }

    /**
     * Returnerer antal gange ordinationen er anvendt
     * @return
     */
    public int getAntalGangeGivet() {
        return antalGangeGivet;
    }

    public double getAntalEnheder() {
        return antalEnheder;
    }

    public void setAntalEnheder(double antalEnheder) {
        this.antalEnheder = antalEnheder;
    }

    public void setAntalGangeGivet(int antalGangeGivet) {
        this.antalGangeGivet = antalGangeGivet;
    }
}

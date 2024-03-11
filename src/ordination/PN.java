package ordination;

import java.time.LocalDate;

public class PN extends Ordination{

    private double antalEnheder;

    public PN(LocalDate startDen, LocalDate slutDen) {
        super(startDen, slutDen);
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
                (givesDen.equals(getStartDen()) || (givesDen.equals(getSlutDen())))) {
            kanDosisGives = true;
        }
        // TODO
        return kanDosisGives;
    }

    public double doegnDosis() {
        // TODO
        return 0.0;
    }

    @Override
    public String getType() {
        return null;
    }


    public double samletDosis() {
        // TODO
        return 0.0;
    }

    /**
     * Returnerer antal gange ordinationen er anvendt
     * @return
     */
    public int getAntalGangeGivet() {
        // TODO
        return-1;
    }

    public double getAntalEnheder() {
        return antalEnheder;
    }

}

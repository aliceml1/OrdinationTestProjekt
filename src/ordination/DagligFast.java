package ordination;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class DagligFast extends Ordination {
    private Dosis[] doser = new Dosis[4];
    private int antalDoser;

    public DagligFast(LocalDate startDen, LocalDate slutDen) {
        super(startDen, slutDen);
        antalDoser = 0;
    }

    public void opretDosis(LocalTime tid, double antal) {
        if (antalDoser < 4) {
            Dosis dosis = new Dosis(tid, antal);
            doser[antalDoser] = dosis;
            antalDoser++;
        }
    }
    @Override
    public double samletDosis() {
        double dosisTotal = 0.0;
        for (int i = 0; i < doser.length; i++) {
            dosisTotal += doser[i].getAntal();
        }
        return dosisTotal * doser.length;
    }

    @Override
    public double doegnDosis() {
        return (double) samletDosis() / (double) super.antalDage();
    }

    @Override
    public String getType() {
        return "Daglig fast";
    }

    public Dosis[] getDoser() {
        return doser;
    }
}

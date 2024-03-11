package ordination;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class DagligSkaev extends Ordination{
    private List<Dosis> dosisList = new ArrayList<>();

    public DagligSkaev(LocalDate startDen, LocalDate slutDen) {
        super(startDen, slutDen);
    }

    public void opretDosis(LocalTime tid, double antal) {
        Dosis dosis = new Dosis(tid, antal);
        dosisList.add(dosis);
    }

    @Override
    public double samletDosis() {
        double samletDosis = 0;
        for (Dosis dosis : dosisList) {
            samletDosis += dosis.getAntal();
        }
        return samletDosis;
    }

    @Override
    public double doegnDosis() {
        return samletDosis() / (double) super.antalDage();
    }

    @Override
    public String getType() {
        return "Daglig skæv";
    }
}

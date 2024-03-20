import controller.Controller;
import ordination.DagligSkaev;
import ordination.Enhed;
import ordination.Laegemiddel;
import ordination.Patient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

class DagligSkaevTest {
    private Controller controller;
    private Laegemiddel paracetamol;
    private Patient alice;

    @BeforeEach
    void setup() {
        alice = new Patient("1010102020", "Alice", 20.0);
        paracetamol = new Laegemiddel("Paracetamol", 1, 1.5, 2, Enhed.STYK);
        controller = Controller.getTestController();
    }

    @Test
    void testConstructorTC1() {
        LocalDate startdato = LocalDate.of(2024, 03, 18);
        LocalDate slutDato = LocalDate.of(2024, 03, 18);
        DagligSkaev skaev = new DagligSkaev(startdato, slutDato);
        assertNotNull(skaev);
    }
    @Test
    void testConstructorTC2() {
        LocalDate startdato = LocalDate.of(2024, 03, 20);
        LocalDate slutDato = LocalDate.of(2024, 03, 23);
        DagligSkaev skaev = new DagligSkaev(startdato, slutDato);
        assertNotNull(skaev);
    }

    @Test
    void opretDosisTC1() {
        LocalDate startDato = LocalDate.of(2024, 03, 18);
        LocalDate slutDato = LocalDate.of(2024, 03, 21);
        LocalTime[] klokkeslet = {LocalTime.of(10, 00)};
        double[] enheder = {2.0};
        DagligSkaev dagligSkaev = controller.opretDagligSkaevOrdination(startDato, slutDato, alice, paracetamol, klokkeslet, enheder);
        assertEquals(dagligSkaev.getDoser().get(0).getAntal(), 2.0);
        assertEquals(dagligSkaev.getDoser().get(0).getTid(), LocalTime.of(10, 00));
    }

    @Test
    void opretDosisTC2() {
        LocalDate startDato = LocalDate.of(2024, 03, 18);
        LocalDate slutDato = LocalDate.of(2024, 03, 21);
        LocalTime[] klokkeslet = {LocalTime.of(8, 00), LocalTime.of(12, 00)};
        double[] enheder = {2.0, 3.0};
        DagligSkaev dagligSkaev = controller.opretDagligSkaevOrdination(startDato, slutDato, alice, paracetamol, klokkeslet, enheder);
        assertEquals(dagligSkaev.getDoser().get(0).getAntal(), 2.0);
        assertEquals(dagligSkaev.getDoser().get(1).getAntal(), 3.0);
        assertEquals(dagligSkaev.getDoser().get(0).getTid(), LocalTime.of(8, 00));
        assertEquals(dagligSkaev.getDoser().get(1).getTid(), LocalTime.of(12, 00));
    }

    @Test
    void samletDosisTC1() {
        LocalDate startDato = LocalDate.of(2024, 03, 18);
        LocalDate slutDato = LocalDate.of(2024, 03, 21);
        LocalTime[] klokkeslet = {LocalTime.of(8, 00), LocalTime.of(12, 00)};
        double[] enheder = {2.0, 2.0};
        DagligSkaev dagligSkaev = controller.opretDagligSkaevOrdination(startDato, slutDato, alice, paracetamol, klokkeslet, enheder);
        double result = dagligSkaev.samletDosis();
        assertEquals(4.0, result);
    }

    @Test
    void doegnDosisTC1() {
        LocalDate startDato = LocalDate.of(2024, 03, 18);
        LocalDate slutDato = LocalDate.of(2024, 03, 21);
        LocalTime[] klokkeslet = {LocalTime.of(8, 00), LocalTime.of(12, 00)};
        double[] enheder = {2.0, 2.0};
        DagligSkaev dagligSkaev = controller.opretDagligSkaevOrdination(startDato, slutDato, alice, paracetamol, klokkeslet, enheder);
        double result = dagligSkaev.doegnDosis();
        assertEquals(1.0, result);
    }

    @Test
    void doegnDosisTC2() {
        LocalDate startDato = LocalDate.of(2024, 03, 18);
        LocalDate slutDato = LocalDate.of(2024, 03, 21);
        LocalTime[] klokkeslet = {LocalTime.of(8, 00), LocalTime.of(12, 00)};
        double[] enheder = {0.0, 0.0};
        DagligSkaev dagligSkaev = controller.opretDagligSkaevOrdination(startDato, slutDato, alice, paracetamol, klokkeslet, enheder);
        double result = dagligSkaev.doegnDosis();
        assertEquals(0.0, result);
    }
}
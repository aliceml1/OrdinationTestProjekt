package ordination;

import controller.Controller;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

class DagligFastTest {
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
        DagligFast dagligFast = new DagligFast(startdato, slutDato);
        assertNotNull(dagligFast);
    }

    @Test
    void testConstructorTC2() {
        LocalDate startdato = LocalDate.of(2024, 03, 20);
        LocalDate slutDato = LocalDate.of(2024, 03, 23);
        DagligFast dagligFast = new DagligFast(startdato, slutDato);
        assertNotNull(dagligFast);
    }

    @Test
    void testConstructorTC3() {
        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> controller.opretDagligFastOrdination(LocalDate.of(2024, 03, 17), LocalDate.of(2024, 03, 16), alice, paracetamol, 2.0, 2.0, 2.0, 2.0));
        assertEquals(exception.getMessage(), "startdato skal være før slutdato");
    }

    @Test
    void opretDosisTC1() {
        DagligFast dagligFast = controller.opretDagligFastOrdination(LocalDate.of(2024, 03, 18), LocalDate.of(2024, 03, 21), alice, paracetamol, 2.0, 2.0, 2.0, 2.0);
        dagligFast.opretDosis(LocalTime.of(8, 00), 2.0);
        dagligFast.opretDosis(LocalTime.of(12, 00), 2.0);
        dagligFast.opretDosis(LocalTime.of(18, 00), 2.0);
        dagligFast.opretDosis(LocalTime.of(22, 00), 2.0);
        Dosis[] result = dagligFast.getDoser();
        assertEquals(2.0, result[0].getAntal());
        assertEquals(LocalTime.of(8, 00), result[0].getTid());
        assertEquals(2.0, result[1].getAntal());
        assertEquals(LocalTime.of(12, 00), result[1].getTid());
        assertEquals(2.0, result[2].getAntal());
        assertEquals(LocalTime.of(18, 00), result[2].getTid());
        assertEquals(2.0, result[3].getAntal());
        assertEquals(LocalTime.of(22, 00), result[3].getTid());
        assertEquals(4, result.length);
    }

    @Test
    void samletDosisTC1() {
        DagligFast dagligFast = controller.opretDagligFastOrdination(LocalDate.of(2024, 03, 18), LocalDate.of(2024, 03, 21), alice, paracetamol, 0.0, 0.0, 0.0, 0.0);
        double result = dagligFast.samletDosis();
        assertEquals(0, result);
    }

    @Test
    void samletDosisTC2() {
        DagligFast dagligFast = controller.opretDagligFastOrdination(LocalDate.of(2024, 03, 18), LocalDate.of(2024, 03, 21), alice, paracetamol, 2.0, 2.0, 2.0, 2.0);
        double result = dagligFast.samletDosis();
        assertEquals(32.0, result);
    }

    @Test
    void doegnDosisTC1() {
        DagligFast dagligFast = controller.opretDagligFastOrdination(LocalDate.of(2024, 03, 18), LocalDate.of(2024, 03, 21), alice, paracetamol, 2.0, 2.0, 2.0, 2.0);
        double result = dagligFast.doegnDosis();
        assertEquals(8.0, result);
    }

    @Test
    void doegnDosisTC2() {
        DagligFast dagligFast = controller.opretDagligFastOrdination(LocalDate.of(2024, 03, 18), LocalDate.of(2024, 03, 21), alice, paracetamol, 0.0, 0.0, 0.0, 0.0);
        double result = dagligFast.doegnDosis();
        assertEquals(0.0, result);
    }
}
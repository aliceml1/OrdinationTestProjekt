package ordination;

import controller.Controller;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

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
    void opretDosis() {
    }

    @Test
    void samletDosis() {
    }

    @Test
    void doegnDosis() {
    }
}
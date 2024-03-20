package ordination;

import controller.Controller;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
    void opretDosis() {
    }

    @Test
    void samletDosis() {
    }

    @Test
    void doegnDosis() {
    }
}
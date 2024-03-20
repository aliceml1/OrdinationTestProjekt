package ordination;

import controller.Controller;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.naming.ldap.Control;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class PNTest {
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
    void givDosisTC1() {
        PN pn = controller.opretPNOrdination(LocalDate.of(2024, 03, 18), LocalDate.of(2024, 03, 21), alice, paracetamol, 2.0);
        boolean result = pn.givDosis(LocalDate.of(2024, 03, 18));
        assertEquals(true, result);
    }

    @Test
    void givDosisTC2() {
        PN pn = controller.opretPNOrdination(LocalDate.of(2024, 03, 18), LocalDate.of(2024, 03, 21), alice, paracetamol, 2.0);
        boolean result = pn.givDosis(LocalDate.of(2024, 03, 17));
        assertEquals(false, result);
    }

    @Test
    void doegnDosisTC1() {
        PN pn = controller.opretPNOrdination(LocalDate.of(2024, 03, 18), LocalDate.of(2024, 03, 21), alice, paracetamol, 2.0);
        pn.setAntalGangeGivet(4);
        pn.setAntalEnheder(4.0);
        double result = pn.doegnDosis();
        assertEquals(4, result);
    }

    @Test
    void samletDosis() {
        PN pn = controller.opretPNOrdination(LocalDate.of(2024, 03, 18), LocalDate.of(2024, 03, 21), alice, paracetamol, 2.0);
        pn.setAntalGangeGivet(4);
        pn.setAntalEnheder(4.0);
        double result = pn.samletDosis();
        assertEquals(16, result);
    }

    @Test
    void getAntalGangeGivetTC1() {
        PN pn = controller.opretPNOrdination(LocalDate.of(2024, 03, 18), LocalDate.of(2024, 03, 21), alice, paracetamol, 2.0);
        pn.setAntalGangeGivet(1);
        double result = pn.getAntalGangeGivet();
        assertEquals(1, result);
    }

    @Test
    void getAntalGangeGivetTC2() {
        PN pn = controller.opretPNOrdination(LocalDate.of(2024, 03, 18), LocalDate.of(2024, 03, 21), alice, paracetamol, 2.0);
        pn.setAntalGangeGivet(3);
        double result = pn.getAntalGangeGivet();
        assertEquals(3, result);
    }
}
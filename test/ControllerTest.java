import controller.Controller;
import ordination.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Array;
import java.rmi.server.ExportException;
import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

class ControllerTest {
    private Controller controller;
    private Laegemiddel paracetamol;
    private Patient alice;
    private LocalDate startdato;

    @BeforeEach
    void setup() {
        paracetamol = new Laegemiddel("Paracetamol", 1, 1.5, 2, Enhed.STYK);
        controller = Controller.getTestController();
        alice = new Patient("1010102020", "Alice", 40);
        startdato = LocalDate.of(2024, 03, 18);
    }

    @Test
    void testOpretDagligFastOrdinationTC1() {
        LocalDate slutDato = LocalDate.of(2024, 03, 21);
        int morgen = 2;
        int middag = 2;
        int aften = 2;
        int nat = 2;
        DagligFast ordination = controller.opretDagligFastOrdination(startdato, slutDato, alice, paracetamol, morgen, middag, aften, nat);
        assertNotNull(ordination);
        assertEquals(ordination.getDoser().length, 4);
        assertEquals(ordination.getLaegemiddel(), paracetamol);
        assert (alice.getOrdinationer().contains(ordination));
    }

    @Test
    void testOpretDagligFastOrdinationTC2() {
        int morgen = 2;
        int middag = 2;
        int aften = 2;
        int nat = 2;
        LocalDate ugyldigSlutdato = LocalDate.of(2024, 03, 17);
        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> controller.opretDagligFastOrdination(startdato, ugyldigSlutdato, alice, paracetamol, morgen, middag, aften, nat));
        assertEquals(exception.getMessage(), "startdato skal være før slutdato");
    }

    @org.junit.jupiter.api.Test
    void opretDagligSkaevOrdinationTC1() {
        LocalDate slutDato = LocalDate.of(2024, 03, 21);
        LocalTime[] klokkeslet = {LocalTime.of(10, 00), LocalTime.of(14, 00), LocalTime.of(20, 00)};
        double[] enheder = {1.5, 1.5, 1.5};
        DagligSkaev skaev = controller.opretDagligSkaevOrdination(startdato, slutDato, alice, paracetamol, klokkeslet, enheder);
        assertNotNull(skaev);
        assertEquals(skaev.getLaegemiddel(), paracetamol);
        assert (alice.getOrdinationer().contains(skaev));
    }

    @Test
    void opretDagligSkaevOrdinationTC2() {
        LocalDate startDato = LocalDate.of(2024, 03, 18);
        LocalDate slutDato = LocalDate.of(2024, 03, 17);
        LocalTime[] klokkeslet = {LocalTime.of(10, 00), LocalTime.of(14, 00), LocalTime.of(20, 00)};
        double[] enheder = {1.5, 1.5, 1.5};
        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> controller.opretDagligSkaevOrdination(startdato, slutDato, alice, paracetamol, klokkeslet, enheder));
        assertEquals(exception.getMessage(), "startdato skal være før slutdato");
    }

    @Test
    void opretDagligSkaevOrdinationTC4() {
        LocalDate startDato = LocalDate.of(2024, 03, 18);
        LocalDate slutDato = LocalDate.of(2024, 03, 21);
        LocalTime[] klokkeslet = {LocalTime.of(10, 00)};
        double[] enheder = {1.5};
        DagligSkaev skaev = controller.opretDagligSkaevOrdination(startdato, slutDato, alice, paracetamol, klokkeslet, enheder);
        assertEquals(skaev.getDoser().get(0).getAntal(), 1.5);
        assertEquals(skaev.getDoser().get(0).getTid(), LocalTime.of(10, 00));
    }

    @Test
    void opretDagligPNOrdinationTC1() {
        double antal = 2;
        LocalDate slutDato = LocalDate.of(2024, 03, 21);
        PN pn = controller.opretPNOrdination(startdato, slutDato, alice, paracetamol, antal);
        assertNotNull(pn);
        assertEquals(pn.getLaegemiddel(), paracetamol);
        assert (alice.getOrdinationer().contains(pn));
    }

    @Test
    void opretDagligPNOrdinationTC2() {
        double antal = 2;
        LocalDate slutDato = LocalDate.of(2024, 03, 17);
        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> controller.opretPNOrdination(startdato, slutDato, alice, paracetamol, antal));
        assertEquals(exception.getMessage(), "startdato skal være før slutdato");
    }
    @Test
    void ordinationPNAnvdentTC1() {
        double antal = 2;
        LocalDate dato = LocalDate.of(2024,03,20);
        LocalDate slutDato = LocalDate.of(2024, 03, 21);
        PN pn = controller.opretPNOrdination(startdato, slutDato, alice, paracetamol, antal);
        assertDoesNotThrow(() -> controller.ordinationPNAnvendt(pn,dato));
    }
    @org.junit.jupiter.api.Test
    void ordinationPNAnvendtTC2() {
        double antal = 2;
        LocalDate dato = LocalDate.of(2024,03,17);
        LocalDate slutDato = LocalDate.of(2024, 03, 21);
        PN pn = controller.opretPNOrdination(startdato, slutDato, alice, paracetamol, antal);
        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> controller.ordinationPNAnvendt(pn,dato));
        assertEquals(exception.getMessage(), "dato ligger udenfor interval");
    }

    @org.junit.jupiter.api.Test
    void anbefaletDosisPrDoegnTC1() {
        alice = new Patient("1010102020", "Alice", 22);
        double result = controller.anbefaletDosisPrDoegn(alice,paracetamol);
        assertEquals(22,result);
    }
    @Test
    void anbefaletDosisPrDoegnTC2() {
        alice = new Patient("1010102020", "Alice", 25);
        double result = controller.anbefaletDosisPrDoegn(alice,paracetamol);
        assertEquals(37.5,result);
    }
    @Test
    void anbefaletDosisPrDoegnTC3() {
        alice = new Patient("1010102020", "Alice", 70);
        double result = controller.anbefaletDosisPrDoegn(alice,paracetamol);
        assertEquals(105,result);
    }
    @Test
    void anbefaletDosisPrDoegnTC4() {
        alice = new Patient("1010102020", "Alice", 120);
        double result = controller.anbefaletDosisPrDoegn(alice,paracetamol);
        assertEquals(180,result);
    }
    @Test
    void anbefaletDosisPrDoegnTC5() {
        alice = new Patient("1010102020", "Alice", 122);
        double result = controller.anbefaletDosisPrDoegn(alice,paracetamol);
        assertEquals(244,result);
    }
}
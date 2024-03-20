package controller;

import ordination.Enhed;
import ordination.Laegemiddel;
import org.junit.jupiter.api.BeforeEach;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class ControllerTest {

    @BeforeEach
    void setup() {
        Laegemiddel paracetamol = new Laegemiddel("Paracetamol",1,1.5,2, Enhed.STYK);
    }
    @org.junit.jupiter.api.Test
    void opretPNOrdination() {

    }

    @org.junit.jupiter.api.Test
    void opretDagligFastOrdination() {
        LocalDate startDato = LocalDate.of(2024,03,18);
        LocalDate slutDato = LocalDate.of(2024,03,21);
        LocalDate ugyldigSlutdato = LocalDate.of(2024,03,17);
        int morgen, middag, aften, nat = 2;
        //assert()
    }

    @org.junit.jupiter.api.Test
    void opretDagligSkaevOrdination() {
    }

    @org.junit.jupiter.api.Test
    void ordinationPNAnvendt() {
    }

    @org.junit.jupiter.api.Test
    void anbefaletDosisPrDoegn() {
    }
}
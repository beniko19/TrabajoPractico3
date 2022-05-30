package algoritmoGoloso;

import app.ManejoJSON;
import com.sun.source.tree.AssertTree;
import grafos.GrafoVecinos;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class RadioCensalTest {

    private ArrayList<Cencista> cencistas;
    RadioCensal radioCensal;

    private void inicilizarObjetos(){
        GrafoVecinos grafoVecinos = ManejoJSON.leerJSON(new File("pruebaManejoJSON.json"));
        cencistas = new ArrayList<>();
        for (int i = 0; i < grafoVecinos.tamano(); i++) {
            cencistas.add(new Cencista());
        }
        radioCensal = new RadioCensal(grafoVecinos, cencistas);

    }

    @Test
    void asignarCensistasAManzanas() {
        inicilizarObjetos();
        radioCensal.asignarCensistasAManzanas();
        assertFalse(isCenistaAvailable());
        assertFalse(isSameSizeGraphWithSquares());
    }

    @Test
    boolean isCenistaAvailable(){
        return radioCensal.isCenistaAvailable();
    }

    @Test
    boolean isSameSizeGraphWithSquares(){
        return radioCensal.isSameSizeGraphWithSquares();
    }

    @Test
    void asignarManzanaACensista() {
        inicilizarObjetos();
        int censitaActual = 2;
        radioCensal._cencistaActual = censitaActual;
        radioCensal.asignarManzanaACensista(4); // al censita numero X le agrego Y manzana
        assertTrue(cencistas.get(2).tieneManzanaAsignada());
    }

    @Test
    void manzanaAsignadaComparteCalle() {
        inicilizarObjetos();
        asignarManzanaACensitaAux(0,3);
        assertFalse(radioCensal.manzanaAsignadaComparteCalle(4));
        assertTrue(radioCensal.manzanaAsignadaComparteCalle(7));
    }

    @Test
    void censistaTieneLugarParaManzana() {
        inicilizarObjetos();
        asignarManzanaACensitaAux(5, 3);
        assertFalse(radioCensal.censistaTieneLugarParaManzana(5));
        asignarManzanaACensitaAux(2, 2);
        assertTrue(radioCensal.censistaTieneLugarParaManzana(2));
    }

    @Test
    void censistaTieneManzanaAsignada() {
        inicilizarObjetos();
        asignarManzanaACensitaAux(6, 1);
        assertTrue(radioCensal.censistaTieneManzanaAsignada(6));
        assertFalse(radioCensal.censistaTieneManzanaAsignada(1));
    }

    private void asignarManzanaACensitaAux(int censita, int cantManzanas) {
        radioCensal._cencistaActual = censita;
        for (int i = 0; i < cantManzanas; i++) {
            cencistas.get(censita).asignarManzana(i);
        }
    }
}
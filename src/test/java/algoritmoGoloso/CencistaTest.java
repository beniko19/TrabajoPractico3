package algoritmoGoloso;

import app.ManejoJSON;
import grafos.GrafoVecinos;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.lang.reflect.Array;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class CencistaTest {
    private ArrayList<Cencista> cencistas;
    private GrafoVecinos grafoVecinos;

    private void inicializar(){
        Cencista.resetearID();
        GrafoVecinos grafoVecinos = ManejoJSON.leerJSON(new File("pruebaManejoJSON.json"));
        cencistas = new ArrayList<>();
        for (int i = 0; i < grafoVecinos.tamano(); i++) {
            cencistas.add(new Cencista());
        }
    }

    @Test
    void conocerID() {
        inicializar();
        assertEquals(1, cencistas.get(0).conocerID());
    }

    @Test
    void tieneManzanaAsignada() {
        inicializar();
        Assert.assertFalse(cencistas.get(0).tieneManzanaAsignada());
    }

    @Test
    void conocerManzanasAsignadas() {
        inicializar();
        ArrayList<Integer> manzanasExpected = new ArrayList<>();
        manzanasExpected.add(0);
        manzanasExpected.add(1);
        manzanasExpected.add(2);
        cencistas.get(0).asignarManzana(0);
        cencistas.get(0).asignarManzana(1);
        cencistas.get(0).asignarManzana(2);
        assertEquals(manzanasExpected, cencistas.get(0).conocerManzanasAsignadas());
    }

    @Test
    void asignarManzanas() {
        inicializar();
        cencistas.get(0).asignarManzana(2);
        assertTrue(cencistas.get(0).tieneManzanaAsignada());
    }

    @Test
    void cantDeManzanasAsignadas() {
        inicializar();
        cencistas.get(0).asignarManzana(10);
        cencistas.get(0).asignarManzana(2);
        cencistas.get(0).asignarManzana(1);
        assertEquals(3, cencistas.get(0).cantDeManzanasAsignadas());
    }
}
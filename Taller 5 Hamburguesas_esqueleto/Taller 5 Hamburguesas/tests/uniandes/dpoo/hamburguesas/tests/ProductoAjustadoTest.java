package uniandes.dpoo.hamburguesas.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import uniandes.dpoo.hamburguesas.mundo.*;

public class ProductoAjustadoTest {

    private ProductoMenu elBase;
    private ProductoAjustado elAjustado;
    private Ingrediente queso;
    private Ingrediente aceituna;

    @BeforeEach
    public void setUp() {
    	elBase = new ProductoMenu("Hamburguesa", 10000);
    	elAjustado = new ProductoAjustado(elBase);
        queso = new Ingrediente("Queso", 1750);
        aceituna = new Ingrediente("Aceitunas", 500);
    }

    @Test
    public void testGetNombre() {
        assertEquals("Hamburguesa", elAjustado.getNombre());
    }

    @Test
    public void testPrecio() {
        assertEquals(10000, elAjustado.getPrecio());
    }

    @Test
    public void testRepetidos() {
    	elAjustado.agregarIngrediente(queso);
    	elAjustado.agregarIngrediente(queso);
        
        System.out.println(elAjustado.getPrecio());

        assertEquals(13500, elAjustado.getPrecio()); 
    }
    @Test
    public void testPrecioConIngredientesAgregados() {
    	elAjustado.agregarIngrediente(queso);
        elAjustado.agregarIngrediente(aceituna);
        assertEquals(12250, elAjustado.getPrecio());
    }

    @Test
    public void testGenerarTextoFactura() {
        String factura = elAjustado.generarTextoFactura();
        System.out.println(factura);
        assertEquals("Hamburguesa", elAjustado.getNombre());
        assertTrue(factura.contains("10000"));
    }

    @Test
    public void testGenerarTextoFacturaConAgregadosYEliminados() {
    	elAjustado.agregarIngrediente(queso);
    	elAjustado.eliminarIngrediente(aceituna);
        String factura = elAjustado.generarTextoFactura();
        System.out.println(factura);
        assertEquals("Hamburguesa", elAjustado.getNombre());
        assertTrue(factura.contains("Queso"));
        assertTrue(factura.contains("1750"));
        assertTrue(factura.contains("Aceitunas"));
        assertTrue(factura.contains("11750"));  
    }


}

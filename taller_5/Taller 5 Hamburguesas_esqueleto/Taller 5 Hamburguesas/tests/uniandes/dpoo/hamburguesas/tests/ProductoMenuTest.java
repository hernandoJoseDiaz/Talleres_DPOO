package uniandes.dpoo.hamburguesas.tests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import uniandes.dpoo.hamburguesas.mundo.ProductoMenu;

public class ProductoMenuTest {

    @Test
    public void testGetNombre() {
        ProductoMenu producto = new ProductoMenu("diario", 12000);
        assertEquals("diario", producto.getNombre());
    }

    @Test
    public void testGetPrecio() {
        ProductoMenu producto = new ProductoMenu("diariomini", 5000);
        assertEquals(5000, producto.getPrecio());
    }

    @Test
    public void testGenerarTextoFactura() {
        ProductoMenu producto = new ProductoMenu("cocacola", 4500);
        String esperado = "cocacola\n            4500\n";
        assertEquals(esperado, producto.generarTextoFactura());
    }

    @Test
    public void testNombreVacioPrecioCero() {
        ProductoMenu producto = new ProductoMenu("", 0);
        assertEquals("", producto.getNombre());
        assertEquals(0, producto.getPrecio());
    }

}

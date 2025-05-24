package uniandes.dpoo.hamburguesas.tests;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import uniandes.dpoo.hamburguesas.mundo.Combo;
import uniandes.dpoo.hamburguesas.mundo.ProductoMenu;

public class ComboTest {

    @Test
    public void testGetNombre() {
        ArrayList<ProductoMenu> items = new ArrayList<>();
        Combo combo = new Combo("Combo1", 0.5, items);
        assertEquals("Combo1", combo.getNombre());
    }

    @Test
    public void testGetPrecio() {
        ArrayList<ProductoMenu> items = new ArrayList<>();
        ProductoMenu producto1 = new ProductoMenu("Hamburguesa", 10000);
        ProductoMenu producto2 = new ProductoMenu("Papas", 5000);
        
        items.add(producto1);
        items.add(producto2);
        Combo combo = new Combo("Combo1", 0.0, items);
        assertEquals(15000, combo.getPrecio());
    }

    @Test
    public void testGetPrecioMitadDePrecio() {
        ArrayList<ProductoMenu> items = new ArrayList<>();
        ProductoMenu producto1 = new ProductoMenu("Hamburguesa", 10000);
        ProductoMenu producto2 = new ProductoMenu("Papas", 5000);
        
        items.add(producto1);
        items.add(producto2);
        Combo combo = new Combo("Combo a mitad de precio", 0.5, items);
        assertEquals(7500, combo.getPrecio());
    }

    @Test
    public void testGetPrecioDescuento100() {
        ArrayList<ProductoMenu> items = new ArrayList<>();
        ProductoMenu producto1 = new ProductoMenu("Hamburguesa", 10000);
        ProductoMenu producto2 = new ProductoMenu("Papas", 5000);
        
        items.add(producto1);
        items.add(producto2);
        Combo combo = new Combo("Combo Gratis", 1.0, items);
        assertEquals(0, combo.getPrecio());
    }

    @Test
    public void testFacturaContenido() {
        ArrayList<ProductoMenu> items = new ArrayList<>();
        items.add(new ProductoMenu("Hamburguesa", 10000));
        
        Combo combo = new Combo("Combo", 0.1, items);
        String factura = combo.generarTextoFactura();
        
        assertTrue(factura.contains("Combo Combo"));
        assertTrue(factura.contains("Descuento: 0.1"));
        assertTrue(factura.contains(String.valueOf(combo.getPrecio())));
    }
}

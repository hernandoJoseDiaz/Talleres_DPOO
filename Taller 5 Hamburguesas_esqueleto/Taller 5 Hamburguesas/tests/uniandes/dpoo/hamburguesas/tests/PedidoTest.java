package uniandes.dpoo.hamburguesas.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.*;

import uniandes.dpoo.hamburguesas.mundo.*;

public class PedidoTest {

    private Pedido pedido;
    private ProductoMenu hamburguesa;
    private ProductoMenu bebida;

    @BeforeEach
    public void setUp() {
        pedido = new Pedido("cliente1", "111");
        hamburguesa = new ProductoMenu("Hamburguesa", 10000);
        bebida = new ProductoMenu("Bebida", 5000);
    }

    @Test
    public void testAgregarProductoYCalculos() {
        pedido.agregarProducto(hamburguesa);
        pedido.agregarProducto(bebida);

        int precioNetoEsperado = 10000 + 5000;
        int ivaEsperado = (int)(precioNetoEsperado * 0.19);
        int precioTotalEsperado = precioNetoEsperado + ivaEsperado;

        assertEquals(precioTotalEsperado, pedido.getPrecioTotalPedido());
    }

    @Test
    public void testGenerarTextoFactura() {
        pedido.agregarProducto(hamburguesa);
        String factura = pedido.generarTextoFactura();

        assertTrue(factura.contains("cliente1"));
        assertTrue(factura.contains("111"));
        assertTrue(factura.contains("Hamburguesa"));
    }

    @Test
    public void testGuardarFactura() throws IOException {
        pedido.agregarProducto(hamburguesa);
        pedido.agregarProducto(bebida);

        
        File file = File.createTempFile("factura_test", ".txt");

        pedido.guardarFactura(file);

    }
    
    

    @Test
    public void testId() {
        int idEsperado = pedido.getIdPedido();
        
        assertTrue(idEsperado >= 0);

        Pedido nuevoPedido = new Pedido("Cliente2", "222");
        int nuevoId = nuevoPedido.getIdPedido();

        List<Pedido> pedidosSimulados = new ArrayList<>();
        pedidosSimulados.add(pedido);
        pedidosSimulados.add(nuevoPedido);

        boolean encontrado = false;
        for (Pedido cadaPedido : pedidosSimulados) {
            if (cadaPedido.getIdPedido() == idEsperado) {
                encontrado = true;
            }
        }
        assertTrue(encontrado);
    }


    
}

package uniandes.dpoo.hamburguesas.tests;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import uniandes.dpoo.hamburguesas.excepciones.*;
import uniandes.dpoo.hamburguesas.mundo.*;

import java.io.*;

public class RestauranteTest {

    private Restaurante restaurante;

    @BeforeEach
    public void setUp() {
        restaurante = new Restaurante();
    }

    @Test
    public void testCargarDatosExitosamente() throws Exception {
        File ingredientes = new File("data/ingredientes.txt");
        File menu = new File("data/menu.txt");
        File combos = new File("data/combos.txt");

        restaurante.cargarInformacionRestaurante(ingredientes, menu, combos);

        assertFalse(restaurante.getIngredientes().isEmpty());
        assertFalse(restaurante.getMenuBase().isEmpty());
        assertFalse(restaurante.getMenuCombos().isEmpty());
    }

    @Test
    public void testIniciarPedido() throws Exception {
        restaurante.iniciarPedido("cliente1", "111");
        Pedido pedido = restaurante.getPedidoEnCurso();
        assertNotNull(pedido);
        assertEquals("cliente1", pedido.getNombreCliente());
    }

    @Test
    public void testIniciarPedidoYaExistente() throws Exception {
        restaurante.iniciarPedido("cliente1", "111");
        assertThrows(YaHayUnPedidoEnCursoException.class, () -> {restaurante.iniciarPedido("cliente2", "222");
        });
    }

    @Test
    public void testCerrarYGuardarPedidoYActualizarLista() throws Exception {
        restaurante.iniciarPedido("cliente1", "111");
        ProductoMenu producto = new ProductoMenu("TestBurger", 10000);
        
        restaurante.getMenuBase().add(producto);
        restaurante.getPedidoEnCurso().agregarProducto(producto);


        restaurante.cerrarYGuardarPedido();

        assertNull(restaurante.getPedidoEnCurso());
        assertEquals(1, restaurante.getPedidos().size());
    }

    @Test
    public void testCerrarPedidoSinPedidoEnCursoLanzaExcepcion() {
        assertThrows(NoHayPedidoEnCursoException.class, () -> {restaurante.cerrarYGuardarPedido();
        });
    }

    @Test
    public void testGettersListasVaciasInicialmente() {
        assertTrue(restaurante.getIngredientes().isEmpty());
        assertTrue(restaurante.getMenuBase().isEmpty());
        assertTrue(restaurante.getMenuCombos().isEmpty());
        assertTrue(restaurante.getPedidos().isEmpty());
    }

    @Test
    public void testIngredienteRepetidoLanzaExcepcion() throws Exception {
        File archivo = File.createTempFile("ingredientes_repetidos", ".txt");
        PrintWriter escribir = new PrintWriter(archivo);
        escribir.println("pizza;1");
        escribir.println("pizza;1");
        escribir.close();

        assertThrows(IngredienteRepetidoException.class, () -> {
            restaurante.cargarInformacionRestaurante(archivo, new File("data/menu.txt"), new File("data/combos.txt"));
        });

        archivo.delete();
    }

    @Test
    public void testProductoRepetidoEnMenu() throws Exception {
        File archivo = File.createTempFile("menu_repetido", ".txt");
        PrintWriter escribir = new PrintWriter(archivo);
        escribir.println("pizza;1");
        escribir.println("pizza;1");
        escribir.close();

        assertThrows(ProductoRepetidoException.class, () -> {
            restaurante.cargarInformacionRestaurante(new File("data/ingredientes.txt"), archivo, new File("data/combos.txt"));
        });

        archivo.delete();
    }

    @Test
    public void testProductoFaltanEnCombo() throws Exception {
        File archivo = File.createTempFile("combo", ".txt");
        PrintWriter pw = new PrintWriter(archivo);
        pw.println("combo1;0.1;nada");
        pw.close();

        assertThrows(ProductoFaltanteException.class, () -> {
            restaurante.cargarInformacionRestaurante(new File("data/ingredientes.txt"), new File("data/menu.txt"), archivo);
        });

        archivo.delete();
    }
}

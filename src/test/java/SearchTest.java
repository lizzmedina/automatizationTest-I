import Reportes.ExtentFactory;
import com.aventstack.extentreports.*;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SearchTest {
    public WebDriver driver;
    private WebDriverWait wait;
    static ExtentSparkReporter info = new ExtentSparkReporter("target/REPORTES/RegisterTest.html");
    static ExtentReports extent;

    @BeforeAll
    public static void crearReporte() {
        extent = ExtentFactory.getInstance();
        extent.attachReporter(info);
    }

    @BeforeEach
    public void inicio() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        SearchPage searchPage = new SearchPage(driver, wait);
        searchPage.setUp();
        searchPage.getUrl("https://opencart.abstracta.us/index.php?route=common/home");
    }

    @Test
    @Tag("Busqueda")
    @Tag("ALL")
    public void testBusqueda_iphone() throws InterruptedException {
        ExtentTest testSearch = extent.createTest("Prueba de Busqueda iphone Exitosa");
        testSearch.log(Status.INFO, "Comienza el Test");
        SearchPage searchPage = new SearchPage(driver, wait);

        try {
            searchPage.escribirProducto("iphone");
            searchPage.clickBuscar();
            testSearch.log(Status.PASS, "Completar Busqueda de iphone");
            Assertions.assertEquals(searchPage.obtenerPorducto(), "iPhone");
            testSearch.log(Status.PASS, "Validación de producto 'iphone' Exitosa");

            searchPage.clickAnadirAlCarrito();
            testSearch.log(Status.PASS, "se añadió iphone al carrito");
            Assertions.assertTrue(searchPage.verificarMensajeExitoPorAnadirAlCarrito());


        } catch (AssertionError error) {
            testSearch.log(Status.FAIL, "Falló la validación: " + error.getLocalizedMessage());
            throw error;
        }
    }

    @AfterEach
    public void quit() {
        SearchPage searchPage = new SearchPage(driver, wait);
        searchPage.close();
    }

    @AfterAll
    public static void reporte() {
        extent.flush();
    }
}
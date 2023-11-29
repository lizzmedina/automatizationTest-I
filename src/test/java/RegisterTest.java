import Reportes.ExtentFactory;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class RegisterTest {
    private WebDriver driver;
    private WebDriverWait wait;
    static ExtentSparkReporter info = new ExtentSparkReporter("target/REPORTES/RegisterTest.html");
    static ExtentReports extent;

    @BeforeAll
    public static void crearReporte() {
        extent = ExtentFactory.getInstance();
        extent.attachReporter(info);
    }

    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        RegisterPage registerPage = new RegisterPage(driver, wait);
        registerPage.setUp();
        registerPage.getUrl("https://opencart.abstracta.us/index.php?route=common/home");
    }

    @Test
    @Tag("Registro")
    @Tag("ALL")
    public void RegistroExitosoTest() throws InterruptedException {
        ExtentTest test = extent.createTest("Prueba de Crear Cuenta Exitosa");
        test.log(Status.INFO, "Comienza el Test");
        RegisterPage registerPage = new RegisterPage(driver, wait);

        try {
            registerPage.clickCuenta();
            Assertions.assertEquals(registerPage.verificarDesplegableCuenta(), "Register\nLogin");
            registerPage.clickRegistrar();
            Assertions.assertEquals(registerPage.obtenerTituloRegistro(), "Account");
            test.log(Status.PASS, "Ingresó a la página de Registro");

            registerPage.escribirNombre("Liza");
            registerPage.escribirApellido("Medina");
            registerPage.escribirMail("lizmedina@gmail.com");
            registerPage.escribirTelefono("31564877");
            registerPage.escribirContraseña("123456");
            registerPage.escribirConfirmarContrasenia("123456");
            registerPage.marcarSubscribe();
            Assertions.assertEquals(registerPage.verificarSubcribeNo(), "No");

            registerPage.marcarPrivacyPoliticy();
            Assertions.assertTrue(registerPage.verificarPolicy());
            test.log(Status.PASS, "Completó los datos de registro correctamente");

            registerPage.clickRegistrarse();
            test.log(Status.PASS, "Completó el registro");

            Assertions.assertEquals(registerPage.obtenerMensajeExito(),
                    "Account\nCongratulations! Your new account has been successfully created!\nYou can now take advantage of member privileges to enhance your online shopping experience with us.\nIf you have ANY questions about the operation of this online shop, please e-mail the store owner.\nA confirmation has been sent to the provided e-mail address. If you have not received it within the hour, please contact us.\nContinue");

            test.log(Status.PASS, "Validó que el registro se haya hecho de forma exitosa");

            registerPage.clickVolverAInicio();
            test.log(Status.PASS, "Regresó a la página inicial");

        } catch (AssertionError error) {
            test.log(Status.FAIL, "Falló la validación: " + error.getLocalizedMessage());
            throw error;
        }
    }


    @AfterEach
    public void cerrar() {
        RegisterPage registerPage = new RegisterPage(driver, wait);
        registerPage.close();
    }

    @AfterAll
    public static void reporte() {
        extent.flush();
    }
}

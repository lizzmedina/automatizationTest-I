import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RegisterPage extends BasePage{
    private By title = By.xpath("//h1[normalize-space()='Account']");
    private By firstName = By.id("input-firstname");
    private By lastName = By.id("input-lastname");
    private By email = By.id("input-email");
    private By telepohone = By.id("input-telephone");
    private By password = By.id("input-password");
    private By passwordConfirm = By.id("input-confirm");
    private By suscribe = By.xpath("//label[normalize-space()='No']");

    private By privacyPolicy = By.xpath("//input[@name='agree']");
    private By buttonContinue = By.xpath("//input[@value='Continue']");
    private By textToConfirm = By.xpath("//div[@id='content']"); // assert "Account/nCongratulations! Your new account has been successfully created!"
    private By goToHomeBtn = By.xpath("//a[normalize-space()='Continue']");

    public RegisterPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }


    public String obtenerTituloRegistro() throws InterruptedException {
        System.out.println("Hay un formulario de solicitud: " + getText(title));
        return this.getText(title);
    }

    public void escribirNombre(String nombre) throws InterruptedException {
        sendText(nombre, firstName);
    }

    public void escribirApellido(String apellido) throws InterruptedException {
        sendText(apellido, lastName);
    }

    public void escribirMail(String mail) throws InterruptedException {
        sendText(mail, email);
    }
    public void escribirTelefono(String telefono) throws InterruptedException{
        sendText(telefono, telepohone);
    }

    public void escribirContraseña(String clave) throws InterruptedException {
        sendText(clave, password);
    }

    public void escribirConfirmarContrasenia(String clave) throws InterruptedException {
        sendText(clave, passwordConfirm);
    }
    public void marcarSubscribe() throws InterruptedException {
        click(suscribe);
    }
    public void marcarPrivacyPoliticy() throws InterruptedException {
        Thread.sleep(1000);
        click(privacyPolicy);
    }
    public String getNoSuscribe() throws InterruptedException {
        Thread.sleep(1000);
        return  getText(privacyPolicy);
    }

    public void clickRegistrarse() throws InterruptedException {
        Thread.sleep(1000);
        click(buttonContinue);
    }

    public String obtenerMensajeExito() throws InterruptedException {
        Thread.sleep(1000);
        System.out.println("Se creo la cuenta: " + getText(textToConfirm));
        return this.getText(textToConfirm);
    }

    public void clickVolverAInicio() throws InterruptedException{
        Thread.sleep(1000);
        this.click(goToHomeBtn);
    }

    public String verificarSubcribeNo() throws InterruptedException {
        Thread.sleep(1000);
        return getText(suscribe);
    }
    public boolean verificarPolicy() throws InterruptedException {
        Thread.sleep(1000);
        return isElementPresent(privacyPolicy);
    }

}

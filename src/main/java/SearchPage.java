import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SearchPage extends BasePage {

    private final By searchResult = By.xpath("//div[@class='caption']//h4");
    private final By anadirCarritoBrn = By.xpath("//div[@class='product-layout product-grid col-lg-3 col-md-3 col-sm-6 col-xs-12']//button[1]");
    private final By messageProdcutoAnadidoAlCarrito = By.xpath("//div[@class='alert alert-success alert-dismissible']");
    public SearchPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }


    public String obtenerPorducto() throws InterruptedException {
        Thread.sleep(1000);
        System.out.println("RESULTADO DE LA BUSQUEDA: " + this.getText(searchResult));
        return this.getText(searchResult);
    }

    public void clickAnadirAlCarrito() throws InterruptedException {
        Thread.sleep(1000);
        this.click(anadirCarritoBrn);
    }

    public boolean verificarMensajeExitoPorAnadirAlCarrito() throws InterruptedException {
        Thread.sleep(1000);
        return isElementPresent(messageProdcutoAnadidoAlCarrito);
    }
}

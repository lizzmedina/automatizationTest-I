import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class BasePage {
    protected By miCuenta = By.xpath("//a[@title='My Account']");
    protected By dropDownCuenta = By.xpath("//ul[@class='dropdown-menu dropdown-menu-right']");
    protected By registerButtom = By.xpath("//a[normalize-space()='Register']");

    private final By searchProduct = By.name("search");
    private final By searchButtom = By.xpath("//button[@class='btn btn-default btn-lg']");
    public static WebDriver driver;
    public static WebDriverWait wait;

    public BasePage(WebDriver driver, WebDriverWait wait) {
        BasePage.driver = driver;
        BasePage.wait = wait;
    }

    public void setUp() {
        driver.manage().window().maximize();
    }

    public void getUrl(String url) {
        driver.get(url);
    }

    public void close() {
        driver.quit();
    }

    protected WebElement elementFind(By locator) throws InterruptedException {
        wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        return driver.findElement(locator);
    }

    protected void sendText(String imputText, By locator) throws InterruptedException {
        this.elementFind(locator).clear();
        this.elementFind(locator).sendKeys(imputText);
    }

    protected void sendKey(CharSequence key, By locator) throws InterruptedException {
        this.elementFind(locator).sendKeys(key);
    }

    protected void click(By locator) throws InterruptedException {
        wait.until(ExpectedConditions.elementToBeClickable(locator));
        this.elementFind(locator).click();
    }

    protected String getText(By locator) throws InterruptedException {
        return this.elementFind(locator).getText();
    }

    public void clickRegistrar() throws InterruptedException {
        this.click(registerButtom);
    }

    public void clickCuenta() throws InterruptedException {
        Thread.sleep(1000);
        this.click(miCuenta);
    }
    public void escribirProducto(String producto) throws InterruptedException {
        Thread.sleep(1000);
        this.sendText(producto, searchProduct);
        this.sendKey(Keys.ENTER, searchProduct);
    }
    public void clickBuscar() throws InterruptedException {
        Thread.sleep(1000);
        this.click(searchButtom);
    }

    public String verificarDesplegableCuenta() throws InterruptedException {
        return  this.elementFind(dropDownCuenta).getText();
    }

    public boolean isElementPresent(By locator) {
        try {
            driver.findElement(locator);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }
}
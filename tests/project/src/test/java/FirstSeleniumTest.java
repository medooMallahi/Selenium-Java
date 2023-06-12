import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.openqa.selenium.interactions.Actions;
import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.support.ui.*;
import org.openqa.selenium.remote.RemoteWebDriver;
import java.net.URL;
import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;

public class FirstSeleniumTest {

    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void setup() throws MalformedURLException {
        ChromeOptions options = new ChromeOptions();
        driver = new RemoteWebDriver(new URL("http://selenium:4444/wd/hub"), options);
        driver.manage().window().maximize();

        wait = new WebDriverWait(driver, 10);
    }

    @Test
    public void testFormSubmissionAndLogout() {
        driver.get("https://demoqa.com/text-box");

        WebElement nameInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("userName")));
        nameInput.sendKeys("Mohammed Almallahi");

        WebElement emailInput = driver.findElement(By.id("userEmail"));
        emailInput.sendKeys("Mohammed@gmail.com");

        WebElement currentAddressInput = driver.findElement(By.id("currentAddress"));
        currentAddressInput.sendKeys("1223 Nagytetenyi ut, Budapest, Hungary");

        WebElement permanentAddressInput = driver.findElement(By.id("permanentAddress"));
        permanentAddressInput.sendKeys("1223 Nagytetenyi ut, Budapest, Hungary");

        WebElement submitButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("submit")));
        System.out.print(submitButton.getText());
        submitButton.click();


        WebElement nameOutput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("name")));
        assertEquals("Name:Mohammed Almallahi", nameOutput.getText());

        Perform hover action
        WebElement hoverElement = driver.findElement(By.xpath("//span[text()='Interactions']"));
        Actions actions = new Actions(driver);
        actions.moveToElement(hoverElement).perform();

        WebElement subMenuElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Sortable']")));
        assertTrue(subMenuElement.isDisplayed());

        // Perform file upload
        driver.get("https://demoqa.com/upload-download");
        WebElement chooseFileButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("uploadFile")));
        chooseFileButton.sendKeys("./A");

        WebElement uploadedFileName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("uploadedFilePath")));
        assertTrue(uploadedFileName.isDisplayed());

        // Logout -> I will do it in login form
        driver.get("https://demoqa.com/login");
        WebElement nameInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("userName")));
        nameInput.sendKeys("Mohammed Almallahi");

        WebElement nameInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("password")));
        nameInput.sendKeys("12340214");

        WebElement logoutButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("login")));
        logoutButton.click();

        WebElement logoutMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h5[text()='Login']")));
        assertTrue(logoutMessage.isDisplayed());
    }


    @After
    public void close() {
        if (driver != null) {
            driver.quit();
        }
    }
}

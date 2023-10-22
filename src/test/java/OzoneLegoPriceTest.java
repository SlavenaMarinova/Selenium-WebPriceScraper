import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.time.Duration;

public class OzoneLegoPriceTest {
    WebDriver driver;

        @BeforeMethod
       public void setUp(){
            WebDriverManager.chromedriver().driverVersion("118").setup();
            System.out.println("ChromeDriver path: " + System.getProperty("webdriver.chrome.driver"));
            driver = new ChromeDriver();
            driver.get("https://www.ozone.bg");
            driver.manage().window().maximize();
        }

    @org.testng.annotations.Test
    public void searchingLegoPrice(){
        WebElement homePageButton = driver.findElement(By.xpath("/html/body/header/div[1]/span/span"));
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        homePageButton.click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement searchBoxElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("search")));
        searchBoxElement.sendKeys("Lego Harry Potter Hogwarts Castle");
        searchBoxElement.sendKeys(Keys.ENTER);
        WebElement firstLegoItem = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[2]/div/div[2]/div/div/div[3]/div[5]/ul/li[1]/div[2]/a")));
        firstLegoItem.click();
        WebElement legoPrice = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[2]/div/div[6]/div/div/form/div/div[3]/div[2]/div[2]/p[2]/span[2]")));

        String price = legoPrice.getText();
        System.out.println("Cenata na produkta e: " + price);

    }

    @AfterMethod
    public void quitDriver(){
        driver.quit();
    }
}

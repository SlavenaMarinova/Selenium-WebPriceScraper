
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class AmazongLegoPriceTest {
    WebDriver driver;

  @BeforeMethod
    public void setup() {
    WebDriverManager.chromedriver().driverVersion("118").setup();
    System.out.println("ChromeDriver path: " + System.getProperty("webdriver.chrome.driver"));
        driver = new ChromeDriver();
        driver.get("https://www.amazon.com");
        driver.manage().window().maximize();
    }

  @Test
    public void searchingLegoPrice() {
      WebElement searchBox = driver.findElement(By.id("twotabsearchtextbox"));
      searchBox.sendKeys("LEGO Harry Potter Hogwarts Castle and Grounds 76419");
      WebElement searchButton = driver.findElement(By.id("nav-search-submit-button"));
      searchButton.click();
      WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
      WebElement legoHarryPotter = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[1]/div[1]/div[1]/div[1]/div/span[1]/div[1]/div[3]/div/div/div/div/div[3]/div[1]/h2/a/span")));
      legoHarryPotter.click();
      WebElement legoPrice =  wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[1]/div/div[8]/div[3]/div[1]/div[6]/div/div[1]/div/div/div/form/div/div/div/div/div[3]/div/div[1]/div/div/span[1]/span[2]")));
      String price = legoPrice.getText();
      System.out.println("Cenata na produkta e: " + price);

    }

    @AfterMethod
    public void quitDriver(){
      driver.quit();
    }
}

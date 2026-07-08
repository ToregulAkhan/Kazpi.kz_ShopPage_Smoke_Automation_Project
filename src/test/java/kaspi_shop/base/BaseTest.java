package kaspi_shop.base;

import kaspi_shop.driver.DriverManager;
import kaspi_shop.pages.HomePage;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest {

    protected WebDriver driver;
    protected HomePage homePage;
    protected BasePage basePage;

    @BeforeMethod
    public void setUp(){
        driver = DriverManager.getDriver();
        homePage =new HomePage(driver);
        homePage.open();
    }

    @AfterMethod
    public void tearDown(){
        DriverManager.quiteDriver();
    }
}

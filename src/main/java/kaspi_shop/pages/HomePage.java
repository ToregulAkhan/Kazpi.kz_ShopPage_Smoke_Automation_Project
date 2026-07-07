package kaspi_shop.pages;

import kaspi_shop.base.BasePage;
import kaspi_shop.constants.Urls;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage extends BasePage {


    public HomePage(WebDriver driver){
        super(driver);
    }

    public HomePage open(){
        driver.get(Urls.BASE_URL);
        return this;
    }

    public

}

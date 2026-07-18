package kaspi_shop.pages;

import kaspi_shop.base.BasePage;
import kaspi_shop.constants.Locator;
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


    public HomePage openAllCategoriesMenu() {
        click(Locator.ALL_CATEGORIES_BUTTON);
        return this;
    }

    public CategoryPage categoryPageOpen(){
        driver.get(Urls.ALL_CATEGORIES_URL);
        return new CategoryPage(driver);
    }


    public String getTextToCategory(By locator){
        return getText(locator);
    }

    public String getPresentText(By locator) {
        return waitUtils.waitPresent(locator).getText();
    }

    public SearchPage searchPage(){
        return new SearchPage(driver);
    }

    public ProductPage productPage(){
        return new ProductPage(driver);
    }
}

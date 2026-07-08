package kaspi_shop.base;

import kaspi_shop.driver.DriverManager;
import kaspi_shop.pages.HomePage;
import kaspi_shop.utils.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.List;
import java.util.NoSuchElementException;

public class BasePage {
    protected final WebDriver driver;
    protected final WaitUtils waitUtils;

    protected BasePage(WebDriver driver){
        this.driver = driver;
        this.waitUtils = new WaitUtils(driver);
    }

    public void click(By locator){

        waitUtils.waitClickable(locator).click();
    }

    public String getText(By locator){

        return waitUtils.waitVisible(locator).getText();
    }

    public WebElement getElement(By locator){
        return waitUtils.waitVisible(locator);
    }

    public List<WebElement> getAll(By locator){

        return waitUtils.waitVisibleAll(locator);
    }

    public boolean isDisplayed(By locator){
        try {
            return waitUtils.waitVisible(locator).isDisplayed();
        }catch (NoSuchElementException e){
            return false;
        }
    }

    public WebElement refreshed(By locator){
        return waitUtils.waitRefreshed(locator);
    }

    public void ClearAndType(By locator, String text){
        WebElement element = waitUtils.waitVisible(locator);
        element.clear();
        element.sendKeys(text);
    }

    public String getCurrentUrl(){

        return driver.getCurrentUrl();
    }

    public String getTitle(){

        return driver.getTitle();
    }

    public String getValue(By locate){
        WebElement element = getElement(locate);
        return element.getAttribute("value");
    }

    public boolean isPageOpened(By locator){
        return isDisplayed(locator);
    }


}

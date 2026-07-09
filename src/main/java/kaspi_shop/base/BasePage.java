package kaspi_shop.base;

import kaspi_shop.driver.DriverManager;
import kaspi_shop.pages.HomePage;
import kaspi_shop.utils.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.security.Key;
import java.util.List;
import java.util.NoSuchElementException;

public class BasePage {
    protected final WebDriver driver;
    protected final WaitUtils waitUtils;

    protected BasePage(WebDriver driver){
        this.driver = driver;
        this.waitUtils = new WaitUtils(driver);
    }
//__________________________________________________________________________________________________

    public void click(By locator){

        waitUtils.waitClickable(locator).click();
    }

//_______________________________________________________________________________________________________

    public String getText(By locator){

        return waitUtils.waitVisible(locator).getText();
    }

    //_______________________________________________________________________________________________________
    public WebElement getElement(By locator){
        return waitUtils.waitVisible(locator);
    }

    //_______________________________________________________________________________________________________
    public List<WebElement> getVisibleAll(By locator){

        return waitUtils.waitVisibleAll(locator);
    }
    //_______________________________________________________________________________________________________
    public List<WebElement> getPresentAll(By locator){
        return waitUtils.waitPresentAll(locator);
    }
    //_______________________________________________________________________________________________________
    public boolean isDisplayed(By locator){
        try {
            return waitUtils.waitVisible(locator).isDisplayed();
        }catch (NoSuchElementException e){
            return false;
        }
    }
    //_______________________________________________________________________________________________________

    public boolean isMoreDisplayed(By locator){
        try {
            return waitUtils.waitVisibleAll(locator).get(1).isDisplayed();
        }catch (NoSuchElementException e){
            return false;
        }
    }
    //_______________________________________________________________________________________________________
    public WebElement refreshed(By locator){
        return waitUtils.waitRefreshed(locator);
    }
    //_______________________________________________________________________________________________________
    public void clearAndType(By locator, String text){
        WebElement element = waitUtils.waitVisible(locator);
        element.clear();
        element.sendKeys(text);
    }
    //_______________________________________________________________________________________________________

    public void clearAndTypeAndEntry(By locator, String text){
        WebElement element = waitUtils.waitVisible(locator);
        element.clear();
        element.sendKeys(text);
        element.sendKeys(Keys.ENTER);
    }
    //_______________________________________________________________________________________________________
    public void invisible(By locator){
        waitUtils.waitInvisible(locator);
    }
    //_______________________________________________________________________________________________________
    public String getCurrentUrl(){

        return driver.getCurrentUrl();
    }
    //_______________________________________________________________________________________________________
    public String getTitle(){

        return driver.getTitle();
    }
    //_______________________________________________________________________________________________________
    public String getValue(By locate){
        WebElement element = getElement(locate);
        return element.getAttribute("value");
    }
    //_______________________________________________________________________________________________________

    public boolean isPageOpened(By locator){
        return isDisplayed(locator);
    }

    public WebElement getPresentElement(){
        return getPresentElement();
    }
//_______________________________________________________________________________________________________
}

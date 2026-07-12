package kaspi_shop.pages;

import kaspi_shop.base.BasePage;
import kaspi_shop.constants.Locator;
import kaspi_shop.constants.Urls;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class CategoryPage extends BasePage {

    protected CategoryPage(WebDriver driver) {
        super(driver);
    }

    public void clickFirstItemCardWithRetry(By locator) throws InterruptedException {
        for (int attempt = 1; attempt <= 3; attempt++) {
            try {
                getVisibleAll(locator).get(0).click();
                return; // получилось - выходим
            } catch (StaleElementReferenceException e) {
                if (attempt == 3) {
                    throw e;
                }
                Thread.sleep(300);
            }
        }
    }

    public void entryToListItem(By locator) throws InterruptedException {
        click(Locator.ACTUAL_SELECT_LIST);
        getElement(locator).click();
        clickFirstItemCardWithRetry(Locator.ITEM_CARD);
    }

    public void clickPrevious(){
        click(Locator.PREVIOUS);
    }

    public void clickNext(){
        click(Locator.NEXT);
    }

    public void checkItems(){
        int count = 0;
        while (true){
            try {
                getVisibleAll(Locator.ITEM_CARD);
                break;
            }catch (NoSuchElementException | StaleElementReferenceException e){
                if (count == 3){
                    throw e;
                }count++;
            }
        }
        Assert.assertFalse(getVisibleAll(Locator.ITEM_CARD).isEmpty());
    }
    public void checkActiveRow(String s){
        int count = 0;
        while (true){
            try {
                String expected = s.split("\\(")[0].trim();
                String actual = getText(Locator.ACTIVE_FILTER_ROW).split("\\(")[0].trim();
                Assert.assertEquals(actual, expected);
                break;
            }catch (AssertionError e){
                if (count == 4){
                    throw e;
                }count++;
            }
        }
    }

}

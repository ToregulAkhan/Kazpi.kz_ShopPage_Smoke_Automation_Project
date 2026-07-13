package kaspi_shop.pages;

import kaspi_shop.base.BasePage;
import kaspi_shop.constants.Locator;
import kaspi_shop.constants.Urls;
import org.openqa.selenium.*;
import org.testng.Assert;

import java.util.List;

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

    public void choseAndCheckOneItemInFilter(int itemIndex, By filterLocator) throws InterruptedException {

        String name = getVisibleAll(filterLocator).get(itemIndex).getText();
        System.out.println(name);

        // запоминаем ссылку на элемент СПИСКА ТОВАРОВ до клика
        WebElement oldItemCard = getElement(Locator.ITEM_CARD);
        getVisibleAll(filterLocator).get(itemIndex).click();
        waitUpdatePage(oldItemCard);   // ждём, пока старый список реально исчезнет

        checkActiveRow(name);
        checkItems();

        System.out.println("Активных фильтров сейчас: " + getVisibleAll(Locator.ACTIVE_FILTER_ROW).size());

        // СНЯТИЕ ФИЛЬТРА — тоже ждём обновления товаров, а не staleness чипа
        WebElement oldItemCard2 = getElement(Locator.ITEM_CARD);
        click(Locator.ACTIVE_FILTER_ROW);
        waitUpdatePage(oldItemCard2);   // <-- товары, не активная строка

    }

    public int allFilterSize(By locator){
        int i = 0;
        while (true){
            try {
                return getVisibleAll(locator).size();
            }catch (StaleElementReferenceException e){
                if(i == 3){
                    throw e;
                }i++;
            }
        }
    }

}

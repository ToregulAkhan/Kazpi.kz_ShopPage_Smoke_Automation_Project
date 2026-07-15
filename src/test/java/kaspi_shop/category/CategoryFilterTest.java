package kaspi_shop.category;

import kaspi_shop.base.BaseTest;
import kaspi_shop.constants.Locator;
import kaspi_shop.listeners.TestListener;
import kaspi_shop.pages.CategoryPage;
import kaspi_shop.pages.HomePage;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.util.List;


public class CategoryFilterTest extends BaseTest {
    protected CategoryPage categoryPage;

    @Test
    public void checkCategoryOpened(){
        categoryPage = homePage.categoryPageOpen();
        By allCategory = By.xpath("//a[@href=\"/shop/c/categories/\" and @class=\"tree__link\"]");
        Assert.assertEquals(categoryPage.getElement(allCategory).getText(), "Все категории");
    }

    @Test
    public void checkFilters() throws InterruptedException {
        categoryPage = homePage.categoryPageOpen();

        List<By> filterLink = List.of(Locator.PRICE_100000_149999, Locator.SAMSUNG, Locator.SULPAK);

        int expectedCount = 0;

        for (By filter : filterLink) {
            categoryPage.click(filter);
            expectedCount++;   // после каждого клика ожидаем на 1 активный фильтр больше

            int attempts = 0;
            while (true) {
                try {
                    List<WebElement> activeFilters = categoryPage.getVisibleAll(Locator.ACTIVE_FILTER_ROW);
                    Assert.assertEquals(activeFilters.size(), expectedCount);

                    System.out.println(activeFilters.get(activeFilters.size() - 1).getText());
                    break;   // проверка прошла - выходим из retry-цикла, переходим к следующему фильтру

                } catch (AssertionError e) {
                    attempts++;
                    if (attempts == 4) {
                        throw e;   // 4 попытки исчерпаны - тест реально падает
                    }
                }
            }
        }

        List<WebElement> size_item_card = categoryPage.getVisibleAll(Locator.ITEM_CARD);
        Assert.assertFalse(size_item_card.isEmpty());
    }

    @Test
    public void checkSelectList () throws InterruptedException {
        categoryPage = homePage.categoryPageOpen();

        List<WebElement> all_select_list = categoryPage.getPresentAll(Locator.SELECT_LIST);
        for(int i =0; i < all_select_list.size(); i++){
            categoryPage.click(Locator.ACTUAL_SELECT_LIST);
            categoryPage.getVisibleAll(Locator.ACTUAL_SELECT_LIST);

            Thread.sleep(50);

            String textName = all_select_list.get(i).getText();
            all_select_list.get(i).click();
            int count = 0;

            while (true){
                try {
                    Assert.assertEquals(textName, categoryPage.getText(Locator.ACTUAL_SELECT_LIST));
                    System.out.println(textName);
                    List<WebElement> size_item_card = categoryPage.getVisibleAll(Locator.ITEM_CARD);
                    Assert.assertFalse(size_item_card.isEmpty());
                    break;
                }catch (AssertionError e){
                    if (count == 4){
                        throw e;
                    }
                    count++;
                }
            }

        }
    }
}

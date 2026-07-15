package kaspi_shop.filters;

import kaspi_shop.base.BaseTest;
import kaspi_shop.constants.Locator;
import kaspi_shop.listeners.TestListener;
import kaspi_shop.pages.CategoryPage;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

public class SellerFilterTest extends BaseTest {
    public CategoryPage categoryPage;

    @Test
    public void checkSellerFilter() throws InterruptedException {
        setCategoryPage();

        for (int i = 0; i < 3; i++) {
            categoryPage.getVisibleAll(Locator.BUTTON_SHOW_ELSE).get(1).click();
            Assert.assertTrue(categoryPage.isDisplayed(Locator.BUTTON_HIDE_ELSE));

            int all_size = categoryPage.allFilterSize(Locator.FILTER_SELLERS);

            if (i == 0) {
                categoryPage.choseAndCheckOneItemInFilter(0, Locator.FILTER_SELLERS);
                checkContainSellerInItem();
                categoryPage.clickToActiveRow();
            } else if (i == 1) {
                categoryPage.choseAndCheckOneItemInFilter(all_size/2, Locator.FILTER_SELLERS);
                checkContainSellerInItem();
                categoryPage.clickToActiveRow();
            } else{
                categoryPage.choseAndCheckOneItemInFilter(all_size-1, Locator.FILTER_SELLERS);
                checkContainSellerInItem();
                categoryPage.clickToActiveRow();
            }
        }
    }

    public void checkContainSellerInItem(){
        String SellerName = categoryPage.getText(Locator.ACTIVE_FILTER_ROW).split("\\(")[0].trim();
        categoryPage.click(Locator.ITEM_CARD);
        categoryPage.isDisplayed(By.xpath("//td[@class=\"sellers-table__cell\"]//a"));
        String SellerInItem = categoryPage.getText(By.xpath("//td[@class=\"sellers-table__cell\"]//a"));
        Assert.assertEquals(SellerName, SellerInItem);
        categoryPage.back();
    }

    public void setCategoryPage(){
        categoryPage = homePage.categoryPageOpen();
    }
}

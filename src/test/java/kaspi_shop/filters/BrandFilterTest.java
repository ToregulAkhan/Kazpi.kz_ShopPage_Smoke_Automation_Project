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

public class BrandFilterTest extends BaseTest {
    public CategoryPage categoryPage;

    @Test
    public void checkBrandFilter() throws InterruptedException {
        setCategoryPage();

        for (int i = 0; i < 3; i++) {
            categoryPage.getVisibleAll(Locator.BUTTON_SHOW_ELSE).get(0).click();
            Assert.assertTrue(categoryPage.isDisplayed(Locator.BUTTON_HIDE_ELSE));

            int all_size = categoryPage.allFilterSize(Locator.FILTER_BRAND);

            if (i == 0) {
                categoryPage.choseAndCheckOneItemInFilter(0, Locator.FILTER_BRAND);
                checkContainBrandInItem();
                categoryPage.clickToActiveRow();
            } else if (i == 1) {
                categoryPage.choseAndCheckOneItemInFilter(all_size/2,Locator.FILTER_BRAND);
                checkContainBrandInItem();
                categoryPage.clickToActiveRow();
            } else{
                categoryPage.choseAndCheckOneItemInFilter(all_size-1,Locator.FILTER_BRAND);
                checkContainBrandInItem();
                categoryPage.clickToActiveRow();
            }


        }
    }


    public void checkContainBrandInItem(){
        String brandName = categoryPage.getText(Locator.ACTIVE_FILTER_ROW).split("\\(")[0].trim();
        List<String> brandNameList = Arrays.asList(brandName.split(" "));

        String itemD = categoryPage.getText(By.cssSelector("[class=\"item-card__name-link\"]"));
        List<String> itemList = Arrays.asList(itemD.split(" "));

        for (String s : brandNameList){
            Assert.assertTrue(itemList.contains(s));
        }
    }

    public void setCategoryPage(){
        categoryPage = homePage.categoryPageOpen();
    }
}

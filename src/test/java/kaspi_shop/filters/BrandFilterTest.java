package kaspi_shop.filters;

import kaspi_shop.base.BaseTest;
import kaspi_shop.constants.Locator;
import kaspi_shop.pages.CategoryPage;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

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
            } else if (i == 1) {
                categoryPage.choseAndCheckOneItemInFilter(all_size/2,Locator.FILTER_BRAND);
            } else{
                categoryPage.choseAndCheckOneItemInFilter(all_size-1,Locator.FILTER_BRAND);
            }


        }
    }


    public void setCategoryPage(){
        categoryPage = homePage.categoryPageOpen();
    }
}

package kaspi_shop.filters;

import kaspi_shop.base.BaseTest;
import kaspi_shop.constants.Locator;
import kaspi_shop.pages.CategoryPage;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class BrandFilterTest extends BaseTest {
    public CategoryPage categoryPage;

    @Test
    public void checkBrandFilter() throws InterruptedException {
        setCategoryPage();
        int size = categoryPage.getVisibleAll(Locator.FILTER_BRAND).size();
        categoryPage.getElement(Locator.BUTTON_SHOW_ELSE).click();
        int all_size = categoryPage.getVisibleAll(Locator.FILTER_BRAND).size();

        for (int i = 0; i < all_size; i++) {
            Assert.assertTrue(size < all_size);
            List<WebElement> brandList = categoryPage.getVisibleAll(Locator.FILTER_BRAND);

            String brand = brandList.get(i).getText();
            System.out.println(brand);

            brandList.get(i).click();

            categoryPage.checkActiveRow(brand);

            Thread.sleep(3000);
            categoryPage.checkItems();

            categoryPage.getElement(Locator.ACTIVE_FILTER_ROW).click();
            Thread.sleep(2000);
            categoryPage.getElement(Locator.BUTTON_SHOW_ELSE).click();
            Thread.sleep(3000);
            //нужна решить подаждать загрузку патом толька двигать тоесть патомтолька кликать или что в каждом клике бываеть загрузка вот егонужно подождать

        }

    }

    public void setCategoryPage(){
        categoryPage = homePage.categoryPageOpen();
    }
}

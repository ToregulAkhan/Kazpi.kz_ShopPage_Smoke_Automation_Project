package kaspi_shop.filters;

import kaspi_shop.base.BaseTest;
import kaspi_shop.constants.Locator;
import kaspi_shop.pages.CategoryPage;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class PriceFilterTest extends BaseTest {
    public CategoryPage categoryPage;

    @Test
    public void checkPriceFilter() throws InterruptedException {
        setCategoryPage();
        int size = categoryPage.getVisibleAll(Locator.FILTER_PRICE).size();

        for (int i = 0; i < size; i++) {
            List<WebElement> priceList = categoryPage.getVisibleAll(Locator.FILTER_PRICE);

            String price = priceList.get(i).getText();
            System.out.println(price);

            priceList.get(i).click();

            categoryPage.checkActiveRow(price);

            Thread.sleep(2000);
            categoryPage.checkItems();

            categoryPage.getElement(Locator.ACTIVE_FILTER_ROW).click();
        }

    }

    public void setCategoryPage(){
        categoryPage = homePage.categoryPageOpen();
    }

}

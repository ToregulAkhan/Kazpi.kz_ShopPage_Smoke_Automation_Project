package kaspi_shop.search;

import kaspi_shop.base.BaseTest;
import kaspi_shop.listeners.TestListener;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

public class SearchTest extends BaseTest {

    @Test
    public void checkSearchWorks() throws InterruptedException {
        String elementForSearch = "книга";
        By item_card = By.cssSelector("[class=\"item-card ddl_product ddl_product_link undefined \"]");

        homePage.searchPage().search();
        homePage.searchPage().inputToSearch(elementForSearch);
        Assert.assertTrue(homePage.searchPage().isMoreDisplayed(item_card));

    }
}

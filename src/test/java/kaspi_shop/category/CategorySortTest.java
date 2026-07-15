package kaspi_shop.category;

import kaspi_shop.base.BaseTest;
import kaspi_shop.constants.Locator;
import kaspi_shop.listeners.TestListener;
import kaspi_shop.pages.CategoryPage;
import org.openqa.selenium.*;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;


public class CategorySortTest extends BaseTest {
    protected CategoryPage categoryPage;

    @Test
    public void checkSortingPopular() throws InterruptedException {
        setCategoryPage(Locator.POPULAR_LIST);
        By feedback = By.xpath("//a[@class='item__rating-link']//span");
        Assert.assertFalse(categoryPage.getElement(feedback).getText().isEmpty());
    }

    @Test
    public void checkSortingNew() throws InterruptedException {
        setCategoryPage(Locator.NEW_LIST);

        By feedback = By.xpath("//a[@class='item__rating-link']");

        Assert.assertFalse(categoryPage.isDisplayed(feedback));

    }

    @Test
    public void checkSortingCheep() throws InterruptedException {
        setCategoryPage(Locator.CHEEP_PRICE_LIST);

        int number = categoryPage.getNumber(Locator.PRICE_LINK);
        Assert.assertTrue(number < 10000);

    }

    @Test
    public void checkSortingExpansive() throws InterruptedException {
        setCategoryPage(Locator.EXPENSIVE_PRICE_LIST);

        int number = categoryPage.getNumber(Locator.PRICE_LINK);
        Assert.assertTrue(number > 500000);
    }

    @Test
    public void checkSortingTopRating() throws InterruptedException {
        setCategoryPage(Locator.TOP_RATING_LIST);

        By feedback = By.xpath("//a[@class='item__rating-link']//span");

        String text = categoryPage.getText(feedback).replaceAll("[^0-9]", "");
        int digit = Integer.parseInt(text);

        Assert.assertTrue(digit > 10000);

    }

    public void setCategoryPage(By locator) throws InterruptedException {
        categoryPage = homePage.categoryPageOpen();
        categoryPage.entryToListItem(locator);
    }








}

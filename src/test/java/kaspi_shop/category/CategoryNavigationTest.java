package kaspi_shop.category;

import kaspi_shop.base.BaseTest;
import kaspi_shop.constants.Locator;
import kaspi_shop.listeners.TestListener;
import kaspi_shop.pages.CategoryPage;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;


public class CategoryNavigationTest extends BaseTest {
    public CategoryPage categoryPage;

    @Test
    public void checkNextSlide(){
        setCategoryPage();
        for (int i = 1; i <= 4; i++){
            String pagination = categoryPage.getText(Locator.ACTIVE);
            Assert.assertEquals(Integer.parseInt(pagination), i);

            categoryPage.checkItems();

            categoryPage.clickNext();
            wait.until(ExpectedConditions.textToBe(Locator.ACTIVE, String.valueOf(i+1)));
        }

    }

    @Test
    public void checkPrevious(){
        setCategoryPage();
        Assert.assertEquals(categoryPage.getText(Locator.ACTIVE),"1");
        categoryPage.getVisibleAll(Locator.PAGINATION_EL).get(1).click();
        wait.until(ExpectedConditions.textToBe(Locator.ACTIVE, "3"));

        categoryPage.checkItems();

        wait.until(ExpectedConditions.textToBe(Locator.PREVIOUS, "← Предыдущая"));
        categoryPage.getVisibleAll(Locator.PAGINATION_EL).get(1).click();
        wait.until(ExpectedConditions.textToBe(Locator.ACTIVE, "1"));
        wait.until(ExpectedConditions.textToBe(Locator.DISABLED, "← Предыдущая"));
        Assert.assertEquals(categoryPage.getText(Locator.DISABLED), "← Предыдущая");


    }

    public void setCategoryPage(){
        categoryPage = homePage.categoryPageOpen();
    }


}

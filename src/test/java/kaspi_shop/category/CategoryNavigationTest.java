package kaspi_shop.category;

import kaspi_shop.base.BaseTest;
import kaspi_shop.constants.Locator;
import kaspi_shop.pages.CategoryPage;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CategoryNavigationTest extends BaseTest {
    public CategoryPage categoryPage;

    @Test
    public void checkNextSlide(){
        setCategoryPage();
        for (int i = 1; i <= 4; i++){
            String pagination = categoryPage.getText(Locator.ACTIVE);
            Assert.assertEquals(Integer.parseInt(pagination), i);

            int count = 0;
            while (true){
                try {
                    categoryPage.getVisibleAll(Locator.ITEM_CARD);
                    break;
                }catch (NoSuchElementException e){
                    if (count == 3){
                        throw e;
                    }count++;
                }
            }
            Assert.assertFalse(categoryPage.getVisibleAll(Locator.ITEM_CARD).isEmpty());
            categoryPage.clickNext();
            wait.until(ExpectedConditions.textToBe(Locator.ACTIVE, String.valueOf(i+1)));
        }

    }

    @Test
    public void checkPrevious(){

    }

    public void setCategoryPage(){
        categoryPage = homePage.categoryPageOpen();
    }
}

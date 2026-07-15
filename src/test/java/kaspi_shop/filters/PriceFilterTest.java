package kaspi_shop.filters;

import kaspi_shop.base.BaseTest;
import kaspi_shop.constants.Locator;
import kaspi_shop.listeners.TestListener;
import kaspi_shop.pages.CategoryPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Listeners;
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

            if(i==0){
                String price = priceList.get(i).getText();
                System.out.println(price);

                priceList.get(i).click();
                categoryPage.checkActiveRow(price);
                categoryPage.checkItems();
                checkPriceItem(price);

                categoryPage.getElement(Locator.ACTIVE_FILTER_ROW).click();
            }else {
                String price = priceList.get(i).getText();
                System.out.println(price);

                WebElement oldItemCard = categoryPage.getElement(Locator.ITEM_CARD);
                priceList.get(i).click();
                categoryPage.waitUpdatePage(oldItemCard);
                categoryPage.checkActiveRow(price);
                categoryPage.checkItems();
                checkPriceItem(price);

                WebElement oldItemCard2 = categoryPage.getElement(Locator.ITEM_CARD);
                categoryPage.getElement(Locator.ACTIVE_FILTER_ROW).click();
                categoryPage.waitUpdatePage(oldItemCard2);
            }

        }

    }

    public void checkPriceItem(String s){
        By price = By.cssSelector("[class=\"item-card__prices-price\"]");
        String priceS = categoryPage.getVisibleAll(price).get(0).getText();
        int itemPrice = Integer.parseInt(priceS.replaceAll("\\D", ""));
        String filterPrice = s.split("\\(")[0].trim();
        if (filterPrice.equals("до 10 000 т")){

            Assert.assertTrue(itemPrice < 10000);

        } else if (filterPrice.equals("10 000 - 49 999 т")) {

            Assert.assertTrue(itemPrice >= 10000 && itemPrice <= 49999);

        }else if (filterPrice.equals("50 000 - 99 999 т")) {

            Assert.assertTrue(itemPrice >= 50000 && itemPrice <= 99999);

        }else if (filterPrice.equals("100 000 - 149 999 т")) {

            Assert.assertTrue(itemPrice >= 100000 && itemPrice <= 149999);

        }else if (filterPrice.equals("150 000 - 199 999 т")) {

            Assert.assertTrue(itemPrice >= 150000 && itemPrice <= 199999);

        }else if (filterPrice.equals("200 000 - 499 999 т")) {

            Assert.assertTrue(itemPrice >= 200000 && itemPrice <= 499999);

        }else if (filterPrice.equals("более 500 000 т")) {

            Assert.assertTrue(itemPrice >= 500000);

        }

    }
    public void setCategoryPage(){
        categoryPage = homePage.categoryPageOpen();
    }

}

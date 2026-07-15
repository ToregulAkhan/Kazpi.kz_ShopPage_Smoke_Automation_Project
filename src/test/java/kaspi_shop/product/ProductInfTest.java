package kaspi_shop.product;

import kaspi_shop.base.BaseTest;
import kaspi_shop.constants.Locator;
import kaspi_shop.constants.Urls;
import kaspi_shop.listeners.TestListener;
import kaspi_shop.pages.ProductPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.util.List;

public class ProductInfTest extends BaseTest {
    public ProductPage productPage;

    @Test
    public void checkTvAudioProduct(){
        setProductPage();
        productPage.tvAudioProduct();

        checkAboutProduct();
    }

    @Test
    public void checkFashionProduct(){
        setProductPage();
        productPage.fashionProduct();

        checkAboutProduct();
    }

    @Test
    public void checkFurnitureProduct(){
        setProductPage();
        productPage.furnitureProduct();

        checkAboutProduct();
    }

    @Test
    public void checkPharmacyProduct(){
        setProductPage();
        productPage.pharmacyProduct();

        checkAboutProduct();
    }



    public void checkAboutProduct(){
        productPage.click(Locator.ITEM_CARD);

        By item_heading = By.cssSelector("[class=\"item__heading\"]");
        By item_rating_link = By.xpath("//a[@class=\"item__rating-link\"]//span");
        By price = By.cssSelector("[class=\"item__price-once\"]");
        By specifications = By.cssSelector("[data-tab=\"specifications\"]");
        By description = By.cssSelector("[data-tab=\"description\"]");

        System.out.println("названия: " + productPage.getText(item_heading));
        System.out.println("цена: " + productPage.getText(price));
        System.out.println("отзыв: " + productPage.getText(item_rating_link));

        productPage.click(specifications);
        List<WebElement> spec_term_text = productPage.getVisibleAll(By.cssSelector("[class=\"specifications-list__spec-term-text\"]"));
        List<WebElement> spec_definition = productPage.getVisibleAll(By.cssSelector("[class=\"specifications-list__spec-definition\"]"));

        for(int i =0; i < spec_definition.size();i++){
            String s1 = spec_term_text.get(i).getText();
            String s2 = spec_definition.get(i).getText();
            System.out.println(s1 + ": " + s2);
        }

        productPage.click(description);
        String d = productPage.getText(By.cssSelector("[class=\"description \"]"));
        System.out.println("дескрипция: " + d);

    }


    public void setProductPage(){
        productPage = homePage.productPage();
    }
}

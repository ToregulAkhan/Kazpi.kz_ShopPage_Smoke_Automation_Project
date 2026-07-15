package kaspi_shop.pages;

import kaspi_shop.base.BasePage;
import kaspi_shop.constants.Urls;
import org.bouncycastle.oer.its.etsi102941.Url;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductPage extends BasePage {

    protected ProductPage(WebDriver driver) {
        super(driver);
    }

    public void fashionProduct(){
        driver.get(Urls.FASHION_CATEGORIES_URL);
    }

    public void tvAudioProduct(){
        driver.get(Urls.TV_AUDIO_CATEGORIES_URL);
    }

    public void furnitureProduct(){
        driver.get(Urls.FURNITURE_CATEGORIES_URL);
    }

    public void pharmacyProduct(){
        driver.get(Urls.PHARMACY_CATEGORIES_URL);
    }
}

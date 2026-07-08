package kaspi_shop.home;

import kaspi_shop.base.BasePage;
import kaspi_shop.base.BaseTest;
import kaspi_shop.pages.HomePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class HomePageTest extends BaseTest {

    @Test
    public void checkAllCategoriesText(){
        List<String> category = List.of("телефоны и гаджеты", "бытовая техника", "тв, аудио, видео",
                "компьютеры", "мебель", "красота, здоровье", "детские товары", "аптека");

        List<WebElement> elements = homePage.getAll(By.xpath("//li//a[@class=\"navigation__link\"]"));
        for (WebElement i : elements){
            Assert.assertTrue(category.contains(i.getText().toLowerCase()));
        }
    }

    @Test
    public void chooseCity(){
        By almaty = By.cssSelector("[data-city-id=\"750000000\"]");
        By astana = By.cssSelector("[data-city-id=\"710000000\"]");
        By semey = By.cssSelector("[data-city-id=\"632810000\"]");
        By city = By.cssSelector("[data-test-id=\"current-city\"]");

        List<By> cities = List.of(semey, astana, almaty);

        for (By c : cities){
            homePage.click(city);
            homePage.click(c);
            homePage.refreshed(city);
        }

    }
}

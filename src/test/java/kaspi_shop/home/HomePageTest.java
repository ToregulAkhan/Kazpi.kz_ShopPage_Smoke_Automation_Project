package kaspi_shop.home;

import kaspi_shop.base.BasePage;
import kaspi_shop.base.BaseTest;
import kaspi_shop.constants.Locator;
import kaspi_shop.constants.Urls;
import kaspi_shop.listeners.TestListener;
import kaspi_shop.pages.HomePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.util.*;


public class HomePageTest extends BaseTest {

    @Test
    public void checkNavigationItem(){
        List<String> category = List.of("телефоны и гаджеты", "бытовая техника", "тв, аудио, видео",
                "компьютеры", "мебель", "красота, здоровье", "детские товары", "аптека");

        List<WebElement> elements = homePage.getVisibleAll(By.xpath("//li//a[@class=\"navigation__link\"]"));
        for (WebElement i : elements){
            Assert.assertTrue(category.contains(i.getText().toLowerCase()));
        }
    }

    @Test
    public void checkCategoryItem() throws InterruptedException {
        List<String> category = List.of("телефоны и гаджеты", "бытовая техника", "тв, аудио, видео",
                "компьютеры", "товары для дома", "мебель", "одежда", "спорт, туризм", "автотовары", "аксессуары",
                "красота и здоровье", "детские товары", "аптека", "строительство и ремонт", "украшения", "обувь",
                "подарки, товары для праздников", "досуг, книги", "канцелярские товары", "товары для животных");

        Set<String> collected = new LinkedHashSet<>();
        int maxClicks = 3;

        for (int i = 0; i < maxClicks; i++) {
            List<WebElement> categoryItems = homePage.getPresentAll(By.cssSelector(".category-item__title"));

            for (WebElement c : categoryItems) {
                collected.add(c.getText().toLowerCase());
            }

            By nextArrow = By.cssSelector("[data-test-id='categories-next-slide']");

            homePage.click(nextArrow);
            Thread.sleep(2000);
        }

        System.out.println(collected);
        for (String title : category) {
            System.out.println(title);
            Assert.assertTrue(collected.contains(title));
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
            Assert.assertTrue(homePage.isDisplayed(city));
            homePage.click(city);
            Assert.assertTrue(homePage.isDisplayed(c));
            homePage.click(c);
            homePage.refreshed(city);
        }
    }


    @Test
    public void checkLogoDisplayed(){
        By logo = By.cssSelector("[class=\"ds-image__img ds-image__img--contain\"]");
        Assert.assertTrue(homePage.isDisplayed(logo));
    }



}

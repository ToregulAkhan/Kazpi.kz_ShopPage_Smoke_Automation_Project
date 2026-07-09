package kaspi_shop.constants;

import org.openqa.selenium.By;

public class Locator {

    public static final By ALL_CATEGORIES_BUTTON = By.xpath("//a[@class=\"navigation__link navigation__link_all-categories\"]");
    public static final By SEARCH_INPUT = By.cssSelector("[id=\"search-bar-input\"]");
    public static final By SEARCH_BUTTON = By.cssSelector("[class=\"search-bar__submit-button\"]");
    public static final By OVERLAY = By.cssSelector(".search-bar-overlay");
}

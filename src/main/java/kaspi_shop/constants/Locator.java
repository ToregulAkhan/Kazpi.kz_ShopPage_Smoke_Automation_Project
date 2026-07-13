package kaspi_shop.constants;

import org.openqa.selenium.By;

public class Locator {

    public static final By ALL_CATEGORIES_BUTTON = By.xpath("//a[@class=\"navigation__link navigation__link_all-categories\"]");
    public static final By SEARCH_INPUT = By.cssSelector("[id=\"search-bar-input\"]");
    public static final By SEARCH_BUTTON = By.cssSelector("[class=\"search-bar__submit-button\"]");
    public static final By OVERLAY = By.cssSelector(".search-bar-overlay");

    public static final By ITEM_CARD = By.cssSelector("[class=\"item-card ddl_product ddl_product_link undefined \"]");

    //filter_actual
    public static final By SELECT_LIST = By.cssSelector(".select__list-item");
    public static final By ACTUAL_SELECT_LIST = By.xpath("//div[@class=\"select__title\"]//span");
    public static final By ACTIVE_FILTER_ROW = By.cssSelector("[class=\"filters__filter-row _active \"]");

    //filters_filter
    public static final By PRICE_10000 = By.xpath("//span[text()='до 10 000 т']");
    public static final By PRICE_100000_149999 = By.xpath("//span[text()='100 000 - 149 999 т']");
    public static final By PRICE_500000 = By.xpath("//span[text()='более 500 000 т']");
    public static final By SAMSUNG = By.xpath("//span[text()='Samsung']");
    public static final By NIKE = By.xpath("//span[text()='Nike']");
    public static final By LEGO = By.xpath("//span[text()='LEGO']");
    public static final By SULPAK = By.xpath("//span[text()='Sulpаk']");
    public static final By FILTER_SELLERS = By.xpath("//span[text()='Продавцы']/following-sibling::div//div[contains(@class,'filters__filter-row')]");
    public static final By FILTER_PRICE = By.xpath("//span[text()='Цена']/following-sibling::div//div[contains(@class,'filters__filter-row')]");
    public static final By FILTER_BRAND = By.xpath("//span[text()='Бренд']/following-sibling::div//div[contains(@class,'filters__filter-row')]");
    public static final By BUTTON_SHOW_ELSE = By.cssSelector("[class=\"filters__spoiler \"]");
    public static final By BUTTON_HIDE_ELSE = By.cssSelector("[class=\"filters__spoiler _active\"]");

    //select_list_item
    public static final By POPULAR_LIST = By.cssSelector("[data-id=\"relevance\"]");
    public static final By NEW_LIST = By.cssSelector("[data-id=\"created-desc\"]");
    public static final By CHEEP_PRICE_LIST = By.cssSelector("[data-id=\"price-asc\"]");
    public static final By EXPENSIVE_PRICE_LIST = By.cssSelector("[data-id=\"price-desc\"]");
    public static final By TOP_RATING_LIST = By.cssSelector("[data-id=\"rating\"]");

    public static final By PRICE_LINK = By.cssSelector("[class=\"item__price-once\"]");

    //pagination
    public static final By PREVIOUS = By.xpath("//li[text()=\"← Предыдущая\"]");
    public static final By NEXT = By.xpath("//li[text()=\"Следующая →\"]");
    public static final By DISABLED = By.cssSelector("[class=\"pagination__el _disabled\"]");
    public static final By ACTIVE = By.cssSelector("[class=\"pagination__el _active\"]");
    public static final By PAGINATION_EL = By.cssSelector("[class=\"pagination__el\"]");



}

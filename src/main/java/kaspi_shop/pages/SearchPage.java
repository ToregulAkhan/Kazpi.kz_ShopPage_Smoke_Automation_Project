package kaspi_shop.pages;

import kaspi_shop.base.BasePage;
import kaspi_shop.constants.Locator;
import kaspi_shop.constants.Urls;
import org.openqa.selenium.WebDriver;

public class SearchPage extends BasePage {
    public SearchPage(WebDriver driver) {
        super(driver);
    }

    public void inputToSearch(String text){
        clearAndTypeAndEntry(Locator.SEARCH_INPUT,text);
    }

    public void search(){
        click(Locator.SEARCH_BUTTON);
    }
}

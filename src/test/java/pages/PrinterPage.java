package pages;

import io.cucumber.java.mn.Харин;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import stepdefs.SortBy;

import java.util.List;

public class PrinterPage {
    private WebDriver driver;
    private WebDriverWait wait;

    public PrinterPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver,5);
    }
    @FindBy (xpath = "//input[@name='withImagesOnly']/..//span")
    private WebElement deliveryCheckbox;

    @FindBy (xpath = "//button[@data-marker='search-filters/submit-button']")
    private WebElement searchButton;

    @FindBy (xpath = "//div[@class='index-topPanel-1F0TP']//select[@class='select-select-3CHiM']")
    private WebElement searchOptions;

    @FindBy (xpath = "//h1[@data-marker='page-title/text']")
    private WebElement pageTitle;

    public PrinterPage checkboxAndSearch() {
            if (!deliveryCheckbox.isSelected()) {
                deliveryCheckbox.click();
            }
            searchButton.click();
        return this;
    }

    public void sortBy(SortBy by) {
        Select select = new Select(searchOptions);
        select.selectByValue(by.value + "");
    }

    public PrinterPage takeExpensivePrinter(int number) {

        List<WebElement> printers = driver.findElements(By.xpath("//a[@data-marker='item-title']"));
        for (int i = 0; i < number; i++) {
            System.out.println(printers.get(i).getAttribute("outerText"));
        }
        return this;
    }

    public void checkPageTitle(String text) {
//        String actual = pageTitle.getAttribute("outerText");
        String actual = pageTitle.getText();
        String expected = "Объявления по запросу «" + text + "» в Владивостоке";
        if (!(actual.equalsIgnoreCase(expected))) {
            System.out.println(actual);
            System.out.println(expected);
            Assert.fail("Название страницы не соответствует ожидаемому");
        }
    }

}

package hooks;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import pages.MainPage;
import pages.PrinterPage;
import java.util.concurrent.TimeUnit;

public class Hooks {
    WebDriver driver;
    public MainPage main;
    public PrinterPage printer;

    @Before
    public void start(){
        System.setProperty("webdriver.chrome.driver","src\\test\\java\\resources\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        main = PageFactory.initElements(driver,MainPage.class);
        printer = PageFactory.initElements(driver, PrinterPage.class);
    }

    @After
    public void end() {
        driver.quit();
    }

    @Test
    public void test() {
        driver.get("https://www.avito.ru/");
        main.selectCategory()
            .sendText("Принтер")
            .chooseCity("Владивосток");
        printer.checkboxAndSearch()
                .takeExpensivePrinter();

    }

}

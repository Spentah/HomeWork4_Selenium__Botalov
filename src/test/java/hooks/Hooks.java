package hooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.chrome.ChromeDriver;
import stepdefs.MyStepdefs;

import java.util.concurrent.TimeUnit;

public class Hooks {

    @Before
    public void start(){
        System.setProperty("webdriver.chrome.driver","src\\test\\java\\resources\\chromedriver.exe");
        MyStepdefs.driver = new ChromeDriver();
        MyStepdefs.driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        MyStepdefs.driver.manage().window().maximize();
    }

    @After
    public void end() {
        MyStepdefs.driver.quit();
    }



}

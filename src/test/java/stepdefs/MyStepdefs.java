package stepdefs;

import com.sun.jmx.snmp.SnmpUnknownModelException;
import hooks.Hooks;
import io.cucumber.java.ParameterType;
import io.cucumber.java.ru.И;
import io.cucumber.java.ru.Пусть;
import io.cucumber.java.ru.Тогда;
import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import net.bytebuddy.TypeCache;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import pages.MainPage;
import pages.PrinterPage;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import stepdefs.Category;
import stepdefs.SortBy;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MyStepdefs{
    public static WebDriver driver;
    public MainPage main = PageFactory.initElements(driver, MainPage.class);
    public PrinterPage printer = PageFactory.initElements(driver, PrinterPage.class);

    @Attachment(value = "Скриншот", type = "image/png")
    public byte[] screenshot() {

            BufferedImage bufferedImage = new AShot().takeScreenshot(driver).getImage();
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            ImageIO.write(bufferedImage, "png", baos);
            baos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

            byte[] image = baos.toByteArray();
            return image;

    }
    @Step("Открываем авито")
    @Пусть("открыт ресурс авито")
    public void открытРесурсАвито() {
        main.goTo();
        screenshot();
    }
    @Step("Выбираем категорию оргтехника")
    @И("в выпадающем списке категорий выбрана {category}")
    public void вВыпадающемСпискеКатегорийВыбранаОргтехника(Category category) {
        main.selectCategory(category);
        screenshot();
    }
    @Step("В после поиска вводим {text}")
    @И("в поле поиска введено значение {string}")
    public void вПолеПоискаВведеноЗначение(String text) {
        main.sendText(text);
        screenshot();
    }
    @Step("Кликаем по списку регионов")
    @Тогда("кликнуть по выпадающему списку регионов")
    public void кликнутьПоВыпадающемуСпискуРегионов() {
        main.cityField();
        screenshot();
    }
    @Step("Вводим в поле регион значение {city}")
    @Тогда("в поле регион введено значение {string}")
    public void вПолеРегионВведеноЗначение(String city) {
        main.chooseCity(city);
        screenshot();
    }
    @Step("Нажимаем кнопку показать объявления")
    @И("нажата кнопка показать объявления")
    public void нажатаКнопкаПоказатьОбъявления() {
        main.buttonClick();
        screenshot();
    }
    @Step("Открылась страница результаты по запросу {text}")
    @Тогда("открылась страница результаты по запросу {string}")
    public void открыласьСтраницаРезультатыПоЗапросуПринтер(String text) {
        printer.checkPageTitle(text);
        screenshot();
    }
    @Step("Активирован чекбокс только с фотографией")
    @И("активирован чекбокс только с фотографией")
    public void активированЧекбоксТолькоСФотографией() {
        printer.checkboxAndSearch();
        screenshot();
    }
    @Step("В выпадающем списке сортировка выбрано значение дороже")
    @И("в выпадающем списке сортировка выбрано значение {sort}")
    public void вВыпадающемСпискеСортировкаВыбраноЗначениеДороже(SortBy by) {
        printer.sortBy(by);
        screenshot();
    }
    @Step("В консоль выведено значение названия и цены {number} первых товаров")
    @И("в консоль выведено значение названия и цены {int} первых товаров")
    public void вКонсольВыведеноЗначениеНазванияИЦеныПервыхТоваров(int number) {
        printer.takeExpensivePrinter(number);
        screenshot();
    }

    @ParameterType(".*")
    public SortBy sort(String text) {
        SortBy by;
        switch (text) {
            case "дороже" :
                by = SortBy.EXPENSIVE;
                break;
            case "дешевле" :
                by = SortBy.CHEAPER;
                break;
            case "по дате" :
                by = SortBy.BY_DATE;
                break;
            case "по умолчанию" :
                by = SortBy.DEFAULT;
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + text);
        } return by;
    }

    @ParameterType(".*")
    public Category category(String text) {
        Category result = null;
        Category[] categories = {Category.AUTO, Category.BIKE, Category.APARTMENTS,
                Category.PLANTS, Category.OFFICE_EQUIPMENT, Category.FURNITURE, Category.FOOD_PRODUCTS};
        for (Category category : categories) {
            if (category.text.equalsIgnoreCase(text)) {
                result = category;
            }
        } return result;
    }

}

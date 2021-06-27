package stepdefs;

import java.util.List;

public enum Category {
    AUTO("Автомобили", 9),
    BIKE("Мотоциклы", 14),
    APARTMENTS("Квартиры", 24),
    OFFICE_EQUIPMENT("Оргтехника", 99),
    FURNITURE("Мебель", 20),
    PLANTS("Растения", 106),
    FOOD_PRODUCTS("Продукты питания", 82);

    public String text;
    public int value;

    Category(String text, int value) {
        this.text = text;
        this.value = value;
    }
}

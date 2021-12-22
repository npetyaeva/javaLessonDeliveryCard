package ru.netology;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;
import ru.netology.utils.DataGenerator;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Random;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class DeliveryCardTest2 {
    private final DataGenerator dataGenerator = new DataGenerator();
    private final Random random = new Random();

    @BeforeAll
    static void setUpAll() {
        Configuration.browser = "chrome";
    }

    @BeforeEach
    void setUp() {
        open("http://localhost:9999/");
    }

    @Test
    void shouldCheckDropdownCities() {
        String planningDate = dataGenerator.generateDate(3);

        $("[placeholder = 'Город']").setValue("Ка");
        List<SelenideElement> cities = $$(".menu-item__control");
        int index = random.nextInt(cities.size());
        $$(".menu-item__control").get(index).click();
        $("[placeholder = 'Дата встречи']").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[placeholder = 'Дата встречи']").setValue(planningDate);
        $("[name = 'name']").setValue(dataGenerator.generateName());
        $("[name = 'phone']").setValue(dataGenerator.generatePhone());
        $(".checkbox__text").click();
        $(".button").click();
        $(".notification__content").shouldBe(visible, Duration.ofSeconds(15))
                .shouldHave(exactText("Встреча успешно забронирована на " + planningDate));
    }

    @Test
    void shouldCheckDataDeliveryInFiveDays() {
        int dates = 5;

        $("[placeholder = 'Город']").setValue("Ка");
        List<SelenideElement> cities = $$(".menu-item__control");
        int index = random.nextInt(cities.size());
        $$(".menu-item__control").get(index).click();
        String planningDate = chooseDate(dates);
        $("[name = 'name']").setValue(dataGenerator.generateName());
        $("[name = 'phone']").setValue(dataGenerator.generatePhone());
        $(".checkbox__text").click();
        $(".button").click();
        $(".notification__content").shouldBe(visible, Duration.ofSeconds(15))
                .shouldHave(exactText("Встреча успешно забронирована на " + planningDate));
    }

    @Test
    void shouldCheckDataDeliveryInFifteenDays() {
        int dates = 15;

        $("[placeholder = 'Город']").setValue("Ка");
        List<SelenideElement> cities = $$(".menu-item__control");
        int index = random.nextInt(cities.size());
        $$(".menu-item__control").get(index).click();
        String planningDate = chooseDate(dates);
        $("[name = 'name']").setValue(dataGenerator.generateName());
        $("[name = 'phone']").setValue(dataGenerator.generatePhone());
        $(".checkbox__text").click();
        $(".button").click();
        $(".notification__content").shouldBe(visible, Duration.ofSeconds(15))
                .shouldHave(exactText("Встреча успешно забронирована на " + planningDate));
    }

    private String chooseDate(int dates) {
        $("span button.icon-button").click();
        LocalDate planningDate = LocalDate.now().plusDays(dates);
        if (planningDate.getMonth() != LocalDate.now().plusDays(3).getMonth()) {
            $("[data-step='1']").click();
        }
        String dayOfMonth = String.valueOf(planningDate.getDayOfMonth());
        $$(".calendar__day").find(exactText(dayOfMonth)).click();
        return String.valueOf(planningDate.format(DateTimeFormatter.ofPattern("dd.MM.yyyy")));
    }
}


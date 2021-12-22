package ru.netology;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;
import ru.netology.utils.DataGenerator;

import java.time.Duration;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class DeliveryCardTest1 {
    private final DataGenerator dataGenerator = new DataGenerator();

    @BeforeAll
    static void setUpAll() {
        Configuration.browser = "chrome";
    }

    @BeforeEach
    void setUp() {
        open("http://localhost:9999/");
    }

    @Test
    void shouldSendFormWithValidData() {
        String dates = dataGenerator.generateDate(5);

        $("[placeholder = 'Город']").setValue(dataGenerator.generateCity());
        $("[placeholder = 'Дата встречи']").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[placeholder = 'Дата встречи']").setValue(dates);
        $("[name = 'name']").setValue(dataGenerator.generateName());
        $("[name = 'phone']").setValue(dataGenerator.generatePhone());
        $(".checkbox__text").click();
        $(".button").click();
        $(".notification__content").shouldBe(visible, Duration.ofSeconds(15))
                .shouldHave(exactText("Встреча успешно забронирована на " + dates));
    }
}

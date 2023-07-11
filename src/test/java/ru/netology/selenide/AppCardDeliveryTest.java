package ru.netology.selenide;
import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;

public class AppCardDeliveryTest {
    private String generateDate(int daysAdd, String pattern) {
        return LocalDate.now().plusDays(daysAdd).format(DateTimeFormatter.ofPattern(pattern));
    }

    @Test
    void shouldOrderCard() {
        open("http://localhost:7777");

        $("[data-test-id='city'] input").setValue("Иркутск");
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $("[data-test-id='date'] input").setValue(generateDate(3, "dd.MM.yyyy"));
        $("[data-test-id='name'] input").setValue("Евдокимов Николай");
        $("[data-test-id='phone'] input").setValue("+79247689098");
        $("[data-test-id='agreement']").click();
        $(withText("Забронировать")).click();
        $("[data-test-id=notification]").shouldBe(visible, Duration.ofSeconds(15));
    }

}

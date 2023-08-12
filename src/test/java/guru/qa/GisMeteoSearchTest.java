package guru.qa;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class GisMeteoSearchTest {

    static {
        Configuration.pageLoadStrategy = "eager";
    }

    @BeforeEach
    void setUp() {
        open("https://www.gismeteo.ru/");
    }

    @CsvFileSource(resources = "/successfulSearchGismeteoTest.csv")
    @Tags({
            @Tag("smoke"), // HIGH
            @Tag("web")
    })
    @DisplayName("Проверка поиска в gismeteo")
    @ParameterizedTest(name = "При вводе города {0} в поисковой выдаче на сайте gismeteo результатом поиска является {1}")
    void successfulSearch(String nameCity, String resultCity) {

        $("input[type='search']").setValue(nameCity);
        $(".found-section .city").shouldHave(text(nameCity)).click();
        $(".page-title").shouldHave(text(resultCity));
    }
}

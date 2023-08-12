package guru.qa;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class GisMeteoSearchTest extends BaseTest {

    @BeforeEach
    void setUp() {
        open("https://www.gismeteo.ru/");
    }

    @CsvFileSource(resources = "/successfulSearchGismeteoTest.csv")

    @Tags({
            @Tag("web"),
            @Tag("search")
    })

    @DisplayName("Проверка поиска в gismeteo.ru")
    @ParameterizedTest(name = "При вводе в поиске города {0} открылась страница {1}")
    void successfulSearch(String nameCity, String resultCity) {

        $("input[type='search']").setValue(nameCity);
        $(".found-section .city").shouldHave(text(nameCity)).click();
        $(".page-title").shouldHave(text(resultCity));
    }
}

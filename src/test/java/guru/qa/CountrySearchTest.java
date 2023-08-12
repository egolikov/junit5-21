package guru.qa;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class CountrySearchTest extends BaseTest {

    @BeforeEach
    void setUp() {
        open("https://www.unipage.net/ru/countries");
    }

    static Stream<String> searchString() {
        return Stream.of("Россия", "Португалия", "Болгария", "Казахстан", "Швеция", "Италия", "Узбекистан");
    }

    @Tags({
            @Tag("web"),
            @Tag("search")
    })

    @DisplayName("Проверка поиска в unipage.net")
    @ParameterizedTest(name = "После поиска {0} открылась страница с информацией о стране {0}")
    @MethodSource("searchString")
    public void successfulSearch(String searchString) {
        $("[type='search']").setValue(searchString).pressEnter();
        $(".list-container").shouldHave(text(searchString));
    }
}


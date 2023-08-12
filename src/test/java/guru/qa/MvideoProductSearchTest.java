package guru.qa;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static com.codeborne.selenide.Selenide.*;

public class MvideoProductSearchTest {

    static {
        Configuration.pageLoadStrategy = "eager";
    }

    @BeforeEach
    void setUp() {
        open("https://mvideo.ru/");
    }


    @ValueSource(
            strings = {"Samsung", "LG", "Apple", "Sony"}
    )

    @Tags({
            @Tag("smoke"), // BLOCKER
            @Tag("web")
    })
    @DisplayName("Проверка поиска в mvideo")
    @ParameterizedTest(name = "В поисковой выдаче присутствуют товары {0}")
    void searchResultsShouldNotBeEmpty(String searchQuery) {
        $(".input__container .input__field").setValue(searchQuery).pressEnter();
        $$(".main-container").shouldHave(CollectionCondition.sizeGreaterThan(0));
    }
}

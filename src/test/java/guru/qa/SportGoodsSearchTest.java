package guru.qa;

import com.codeborne.selenide.CollectionCondition;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static com.codeborne.selenide.Selenide.*;

public class SportGoodsSearchTest extends BaseTest {

    @BeforeEach
    void setUp() {
        open("https://sport-tovari.com/");
    }

    @ValueSource(
            strings = {"Велоспорт", "Игры", "Плавание", "Туризм", "Рыбалка", "Хоккей", "Лёгкая атлетика", "Футбол"}
    )

    @Tags({
            @Tag("web"),
            @Tag("search")
    })

    @DisplayName("Проверка поиска в sport-tovari.com")
    @ParameterizedTest(name = "Результат поиска товаров категории {0} не пустой")
    void searchResultsShouldNotBeEmpty(String searchQuery) {
        $("[placeholder='Поиск по товарам']").setValue(searchQuery).pressEnter();
        $$(".container .catalog .catalog").shouldHave(CollectionCondition.sizeGreaterThan(0));
    }
}

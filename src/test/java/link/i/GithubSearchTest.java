package link.i;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import io.qameta.allure.selenide.AllureSelenide;
import com.codeborne.selenide.logevents.SelenideLogger;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;

public class GithubSearchTest {

    @Feature("������� �� �������� Wiki")
    @Story("������� SoftAssertions")
    @Severity(SeverityLevel.CRITICAL)
    @Test
    void searchExample() {
        SelenideLogger.addListener("allure", new AllureSelenide());
        Configuration.browserSize = "1800x1200";
        Configuration.holdBrowserOpen = true;

        String url = "https://github.com/selenide/selenide",
                // content
                desiredPageName = "SoftAssertions",
                hiddenPagesButtonText = "Show 2 more pages",
                exampleTitle = "3. Using JUnit5 extend test class:",
                // locators
                hiddenPagesButton = "li.Box-row.wiki-more-pages-link",
                wikiTab = "#wiki-tab",
                pageContent = "#wiki-wrapper";

        step("������� �������� Selenide �� ����� Github", ()-> open(url));
        step("������� ������� Wiki",() ->$(wikiTab).click());
        step("������� �������� " + desiredPageName, () -> {
            $(hiddenPagesButton)
                    .findElement(withText(hiddenPagesButtonText))
                    .click();
            $(By.linkText(desiredPageName))
                    .should(Condition.exist)
                    .click();
            $(pageContent).$(withText(desiredPageName)).shouldBe(Condition.visible);
        });
        step("����������� ������� ������� ��� JUnit5", () ->
                $(pageContent).$(withText(exampleTitle)).shouldBe(Condition.exist)
        );
    }
}

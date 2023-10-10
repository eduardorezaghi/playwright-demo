package org.example;

import java.util.regex.Pattern;
import com.microsoft.playwright.*;
import com.microsoft.playwright.options.AriaRole;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class FullTests {
    public static void main(String[] args) {
        try (Playwright playwright = Playwright.create()) {
            // Arrange - Create a new browser instance
            Browser browser = playwright.chromium().launch();
            Page page = browser.newPage();
            page.navigate("https://playwright.dev/");

            // Expect that the page title is "Fast and reliable end-to-end testing for modern web apps | Playwright"
            System.out.println(page.title());
            assertThat(page).hasTitle(Pattern.compile("Playwright"));

            Locator getStarted  = page.locator("text=Get Started");

            // Expect an attribute "to be strictly equal" to the value.
            assertThat(getStarted).hasAttribute("href", "/docs/intro");

            // Performs a click action on the element.
            getStarted.click();

            // Expects page to have a heading with the name of Installation.
            assertThat(page.getByRole(AriaRole.HEADING,
                    new Page.GetByRoleOptions().setName("Installation"))).isVisible();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

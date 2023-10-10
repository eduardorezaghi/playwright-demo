package org.example;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.*;
import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class TodoTests {
    public static void main(String[] args) {
        try (Playwright playwright = Playwright.create()) {
            // Arrange - Create a new browser instance
            Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions()
                    .setHeadless(false));
            BrowserContext context = browser.newContext();
            Page page = context.newPage();
            page.navigate("https://demo.playwright.dev/todomvc/");

            // Act - Add a few items to the todo list
            Locator todoList = page.locator(".new-todo");
            todoList.click();

            todoList.fill("Prepare coffee");
            assertThat(todoList).hasValue("Prepare coffee");
            todoList.press("Enter");

            todoList.fill("Buy more coffee");
            assertThat(todoList).hasValue("Buy more coffee");
            todoList.press("Enter");

            todoList.fill("Walk the dog");
            assertThat(todoList).hasValue("Walk the dog");
            todoList.press("Enter");

            // Assert - Verify that the items are added to the list
            assertThat(page.locator("text=Prepare coffee")).isVisible();
            assertThat(page.locator("text=Buy more coffee")).isVisible();
            assertThat(page.locator("text=Walk the dog")).isVisible();

            // Clean up - Delete the items from the list
            page.getByText("Walk the dog").click();
            page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Delete")).click();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
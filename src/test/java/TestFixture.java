import com.microsoft.playwright.*;
import org.junit.jupiter.api.*;

import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

// Subclasses will inherit PER_CLASS behavior.
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class TestFixture {
    Playwright playwright;
    Browser browser;

    @BeforeAll
    void launchBrowser() {
        try {
            playwright = Playwright.create();
            browser = playwright.firefox().launch(
                    new BrowserType.LaunchOptions()
            );
        } catch (Exception e) {
            Logger.getGlobal().severe(e.getMessage());
        }
    }

    @AfterAll
    void closeBrowser() {
        playwright.close();
    }

    // New instance for each test method.
    BrowserContext context;
    Page page;

    // Context is created before each test method.
    // Page is created before each test method.
        // Context represents a single browser tab.
        // Page represents a single web page.
    @BeforeEach
    void createContextAndPage() {
        context = browser.newContext();
        page = context.newPage();
    }

    @AfterEach
    void closeContext() {
        context.close();
    }
}

class Test1 extends TestFixture {
    @Test
    void shouldClickButton() {
        page.navigate("data:text/html,<script>var result;</script><button onclick='result=\"Clicked\"'>Go</button>");
        page.locator("button").click();
        assertEquals("Clicked", page.evaluate("result"));
    }

    @Test
    void shouldCheckTheBox() {
        page.setContent("<input id='checkbox' type='checkbox'></input>");
        page.locator("input").check();
        assertTrue((Boolean) page.evaluate("() => window['checkbox'].checked"));
    }

    @Test
    void shouldSearchWiki() {
        page.navigate("https://www.wikipedia.org/");
        page.locator("input[name=\"search\"]").click();
        page.locator("input[name=\"search\"]").fill("playwright");
        page.locator("input[name=\"search\"]").press("Enter");
        assertEquals("https://en.wikipedia.org/wiki/Playwright", page.url());
    }
}

class Test2 extends TestFixture {
    @Test
    void shouldClickButton() {
        page.navigate("data:text/html,<script>var result;</script><button onclick='result=\"Clicked\"'>Go</button>");
        page.locator("button").click();
        assertEquals("Clicked", page.evaluate("result"));
    }

    @Test
    void shouldCheckTheBox() {
        page.setContent("<input id='checkbox' type='checkbox'></input>");
        page.locator("input").check();
        assertTrue((Boolean) page.evaluate("() => window['checkbox'].checked"));
    }

    @Test
    void shouldVisitBlankPage() {
        page.navigate("about:blank");
        assertEquals("about:blank", page.url());
    }
}
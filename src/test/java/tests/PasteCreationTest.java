package tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pages.HomePage;
import pages.PastePage;
import utils.WebDriverManager;

import static org.junit.jupiter.api.Assertions.*;

public class PasteCreationTest {
    private WebDriver driver;
    private HomePage homePage;
    private PastePage pastePage;

    @BeforeEach
    public void setUp() {
        driver = WebDriverManager.getDriver();
        driver.get("https://pastebin.com/");
        homePage = new HomePage(driver);
        pastePage = new PastePage(driver);
    }

    @Test
    public void testCreateNewPaste() {
        homePage.goToNewPastePage();
        pastePage.enterCode("git config --global user.name  \"New Sheriff in Town\"\n" +
                "            git reset $(git commit-tree HEAD^{tree} -m \"Legacy code\")\n" +
                "            git push origin master --force\n");
        pastePage.selectHighlighting("Bash");
        pastePage.selectExpiration("10 Minutes");
        pastePage.enterPasteName("how to gain dominance among developers");
        pastePage.createPaste();

        assertEquals(
                "how to gain dominance among developers",
                driver.findElement(By.cssSelector(".info-top h1")).getText()
        );
        assertEquals(
                "Bash",
                driver.findElement(By.cssSelector(".info-top h1")).getText()
        );
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
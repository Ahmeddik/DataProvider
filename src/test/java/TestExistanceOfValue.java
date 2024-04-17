import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.time.Duration;

public class TestExistanceOfValue {
    String levelSet_HomePage = "https://www.levelset.com/";
    String getPaid = "//a[contains(.,\"Get paid \")]";
    By xpathOfGetPaid = By.xpath(getPaid);

    String file_a_lien = "//div[@class=\"left\" and contains(.,\"File a Lien\")]";
    By xpathOfFileALien = By.xpath(file_a_lien);
    WebDriver driver;
    WebDriverWait wait;


    @BeforeClass
    public void setup() {
        driver = new ChromeDriver();
    }

    @Test(dataProvider = "GetValues")

    void testValueName (String name){
        String actualData;

        driver.get(levelSet_HomePage);

        wait = new WebDriverWait(driver, Duration.ofSeconds(30));

        wait.until(ExpectedConditions.presenceOfElementLocated(xpathOfGetPaid));

        WebElement getPaidButton = driver.findElement(xpathOfGetPaid);
        getPaidButton.sendKeys(Keys.ENTER);

        wait.until(ExpectedConditions.presenceOfElementLocated(xpathOfFileALien));
        WebElement fileALien = driver.findElement(xpathOfFileALien);

        actualData = fileALien.getText();

        Assert.assertEquals(actualData,name);
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }

    @DataProvider (name = "GetValues")
    public Object[] getValues(){
        return new Object[]{
                "20-Day Preliminary Notice",
                "Notice of Intent to Lien",
                "File a Lien",
                "Lien / Bond Claim",
                "Lien Waiver"
        };
    }
}

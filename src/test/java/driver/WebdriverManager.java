package driver;

import org.openqa.selenium.chrome.ChromeDriver;

public class WebdriverManager {

    public static ChromeDriver createChromeDriver() {

        System.setProperty("webdriver.chrome.driver", "src/test/Resources/chromedriver.exe");
        return new ChromeDriver();

    }
}

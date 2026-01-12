package Baseclass;

import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.time.Duration;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class BaseClass {

    private static ThreadLocal<WebDriver> tdriver = new ThreadLocal<>();
    public Logger logger;
    public Properties p;

    public WebDriver getDriver() {
        return tdriver.get();
    }

    @BeforeClass(groups = { "Reggression", "Master", "Sanity", "DataDriven" })
    @Parameters({ "browser", "os" })
    public void setup(String browser, String os) throws IOException {

        // ---------- Load config.properties ----------
        String path = System.getProperty("user.dir")
                + "\\src\\test\\resources\\config.properties";
        FileReader file = new FileReader(path);
        p = new Properties();
        p.load(file);

        logger = LogManager.getLogger(this.getClass());

        WebDriver driver;
        String executionEnv = p.getProperty("execution_env").toLowerCase();

        // ---------- REMOTE EXECUTION ----------
        if (executionEnv.equals("remote")) {

            DesiredCapabilities cap = new DesiredCapabilities();

            // OS
            if (os.equalsIgnoreCase("windows")) {
                cap.setPlatform(Platform.WIN10);
            }  else if (os.equalsIgnoreCase("linux")) {
                cap.setPlatform(Platform.LINUX);
            }else {
            	System.out.println("invalid os");
            	return;
            }

            // Browser
            switch (browser.toLowerCase()) {
            case "chrome":
                cap.setBrowserName("chrome");
                break;
            case "edge":
                cap.setBrowserName("MicrosoftEdge");
                break;
            case "firefox":
                cap.setBrowserName("firefox");
                break;
            default:
                throw new RuntimeException("Invalid browser for remote: " + browser);
            }

            driver = new RemoteWebDriver(
                    new URL("http://172.29.144.1:4444"), cap);
            logger.info("Running tests on REMOTE machine");

        }
        // ---------- LOCAL EXECUTION ----------
        else {

            switch (browser.toLowerCase()) {
            case "chrome":
                driver = new ChromeDriver();
                break;
            case "edge":
                driver = new EdgeDriver();
                break;
            case "firefox":
                driver = new FirefoxDriver();
                break;
            default:
                throw new RuntimeException("Invalid browser: " + browser);
            }
            logger.info("Running tests on LOCAL machine");
        }

        tdriver.set(driver);

        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        getDriver().get(p.getProperty("url"));
        getDriver().manage().window().maximize();
    }

    @AfterClass(groups = { "Reggression",  "Sanity", "DataDriven" })
    public void teardown() {
        if (getDriver() != null) {
            getDriver().quit();
            tdriver.remove();
        }
    }

    // ---------- Utilities ----------
    public String Randomemail() {
        return RandomStringUtils.randomAlphabetic(6) + "@gmail.com";
    }

    public String Randomnum() {
        return RandomStringUtils.randomNumeric(10);
    }

    public String Randompass() {
        return RandomStringUtils.randomAlphabetic(6) + "@1A";
    }
}

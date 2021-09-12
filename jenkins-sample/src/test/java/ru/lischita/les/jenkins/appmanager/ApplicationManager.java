package ru.lischita.les.jenkins.appmanager;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;


import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;

public class ApplicationManager {
  protected WebDriver wd;
  private SessionHelper sessionHelper;
  private NavigationHelper navigationHelper;
  private GroupHelper groupHelper;
  private ContactHelper contactHelper;
  private String browser;
  public Properties properties;
  private DBHelper dbHelper;


  public ApplicationManager(String browser) {
    this.browser = browser;
    properties = new Properties();
  }

  public void init() throws IOException {
    String target=System.getProperty("target","local");  //local имя файла  .properties по умолчанию передаваемое в командной строке если будет отсуствовать атрибут target gradlew -Pbrowser=firefox,target=local testContacts
    properties.load(new FileReader(new File(String.format("src/test/resources/%s.properties",target))));
    dbHelper=new DBHelper();

    if ("".equals(properties.getProperty("selenium.server"))){
    if(browser.equals(BrowserType.CHROME)) {
      System.setProperty("webdriver.chrome.driver", properties.getProperty("webdriver.chrome"));//System.setProperty("webdriver.chrome.driver", "C:\\Users\\Елизавета Криворучка\\Desktop\\world\\chromedriver.exe");
      wd = new ChromeDriver();
    }
    else if(browser.equals(BrowserType.IE)) {
      System.setProperty("webdriver.ie.driver",properties.getProperty("webdriver.ie"));//System.setProperty("webdriver.ie.driver", "C:\\Users\\Елизавета Криворучка\\Desktop\\world\\IEDriverServer.exe");
      wd = new InternetExplorerDriver();
      // wd.manage().window().maximize();
    } else if (browser.equals(BrowserType.FIREFOX)) {
     System.setProperty("webdriver.gecko.driver", properties.getProperty("webdriver.firefox"));//System.setProperty("webdriver.gecko.driver", "C:\\Users\\Елизавета Криворучка\\Desktop\\world\\geckodriver.exe");
      wd = new FirefoxDriver();
    }
    }else {
      DesiredCapabilities capabilites=new DesiredCapabilities();
      capabilites.setBrowserName(browser);
      //capabilites.setPlatform(Platform.fromString(System.getProperty("platform","win7")));
      wd=new RemoteWebDriver(new URL(properties.getProperty("selenium.server")),capabilites);

    }
   //wd.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    wd.get(properties.getProperty("web.baseURL")); //wd.get("http://localhost/addressbook/");
    groupHelper = new GroupHelper(wd);
    navigationHelper = new NavigationHelper(wd);
    sessionHelper = new SessionHelper(wd);
    contactHelper = new ContactHelper(wd);
    sessionHelper.login(properties.getProperty("web.adminLogin"), properties.getProperty("web.adminPassword"));//sessionHelper.login("admin", "secret");
  }

  public void stop() {
    wd.quit();
  }

  public boolean isElementPresent(By by) {
    try {
      wd.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  public GroupHelper group() {
    return groupHelper;
  }

  public NavigationHelper goTo() {
    return navigationHelper;
  }

  public ContactHelper contact() {
    return contactHelper;
  }

  public DBHelper db(){
    return dbHelper;
  }
}

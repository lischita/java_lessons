package ru.lischita.les.mantis.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.BrowserType;
import ru.lischita.les.mantis.tests.RegistrationHelper;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ApplicationManager {
 private WebDriver wd;
   private String browser;
  public Properties properties;
  private RegistrationHelper registrationHelper;
  private FtpHelper ftp;
  private MailHelper mailHelper;


  public ApplicationManager(String browser) {
    this.browser = browser;
    properties = new Properties();
  }

  public void init() throws IOException {
    String target=System.getProperty("target","local");  //local имя файла  .properties по умолчанию передаваемое в командной строке если будет отсуствовать атрибут target gradlew -Pbrowser=firefox,target=local testContacts
    properties.load(new FileReader(new File(String.format("src/test/resources/%s.properties",target))));

   /* if(browser.equals(BrowserType.CHROME)) {
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

    wd.get(properties.getProperty("web.baseURL")); //wd.get("http://localhost/addressbook/");*/  //Перенесли в getDriver() ниже выполняя лекцию 8.4
 //   sessionHelper.login(properties.getProperty("web.adminLogin"), properties.getProperty("web.adminPassword"));//sessionHelper.login("admin", "secret");
  }

  public void stop() {
    if (wd!=null) {   wd.quit();}

  }

  public boolean isElementPresent(By by) {
    try {
      wd.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

public HttpSession newSession(){
    return new HttpSession(this);
}

public String getPropetry(String key)
{
    return properties.getProperty(key);
}

  public RegistrationHelper registration() {
    if (registrationHelper==null){ registrationHelper= new RegistrationHelper(this);}
    return registrationHelper;
  }

  public FtpHelper ftp(){
    if (ftp==null){ ftp = new FtpHelper(this);}
    return ftp;
  }

  public MailHelper mail(){
    if (mailHelper==null){ mailHelper = new MailHelper(this);}
    return mailHelper;
  }

  public WebDriver getDriver() {
    if(wd==null){
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

      wd.get(properties.getProperty("web.baseURL")); //wd.get("http://localhost/addressbook/");

    }
    return wd;
  }
}

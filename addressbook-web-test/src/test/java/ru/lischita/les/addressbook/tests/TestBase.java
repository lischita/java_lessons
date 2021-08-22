package ru.lischita.les.addressbook.tests;

import org.openqa.selenium.remote.BrowserType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import ru.lischita.les.addressbook.appmanager.ApplicationManager;
import ru.lischita.les.addressbook.model.ContactData;
import ru.lischita.les.addressbook.model.Contacts;
import ru.lischita.les.addressbook.model.GroupData;
import ru.lischita.les.addressbook.model.Groups;

import java.lang.reflect.Array;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class TestBase {
  Logger logger = LoggerFactory.getLogger(TestBase.class);

  protected static ApplicationManager app = new ApplicationManager(System.getProperty("browser", BrowserType.CHROME));

  @BeforeSuite
  public void setUp() throws Exception
  {
    app.init();
  }

  @AfterSuite (alwaysRun = true)
  public void tearDown() throws Exception
  {
    app.stop();
  }

  @BeforeMethod
  public void logTestStart(Method m, Object [] p)
  {logger.info("Start method "+m.getName()+" with params "+ Arrays.asList(p));}


  @AfterMethod (alwaysRun = true)
  public void logTestStop(Method m){
    logger.info("Stop method "+m.getName());}

  public void verifyInGroupListInUI() {
    if (Boolean.getBoolean("verifyUI")) {    // проверяем если сисетммное свойство true, то выполняем иначе нет
      Groups dbGroups = app.db().groups();
      Groups uiGroups = app.group().all();
      assertThat(uiGroups, equalTo(dbGroups.stream().map((g) -> new GroupData().withId(g.getId()).withName(g.getName())).collect(Collectors.toSet())));
      // сравниваем то что получили из БД с тем что есть в UI ghb этом данные из БД упрощаем, создаем новое множество где присуствуют только Id и name группы
    }
  }
  public void verifyInContactListInUI() {
    if (Boolean.getBoolean("verifyUI")) {    // проверяем если сисетммное свойство true, то выполняем иначе нет
      Contacts dbContacts = app.db().contacts();
      Contacts uiContacts = app.contact().all();
      assertThat(uiContacts, equalTo(dbContacts.stream().map((c) -> new ContactData().withId(c.getId()).withName(c.getName())).collect(Collectors.toSet())));
      // сравниваем то что получили из БД с тем что есть в UI ghb этом данные из БД упрощаем, создаем новое множество где присуствуют только Id и name группы
    }
  }
}

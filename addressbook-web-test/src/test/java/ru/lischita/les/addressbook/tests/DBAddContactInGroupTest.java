package ru.lischita.les.addressbook.tests;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.lischita.les.addressbook.model.ContactData;
import ru.lischita.les.addressbook.model.Contacts;
import ru.lischita.les.addressbook.model.GroupData;
import ru.lischita.les.addressbook.model.Groups;
import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DBAddContactInGroupTest extends TestBase {
  @BeforeMethod
  public void ensurePreconditions() {
    DateFormat dateFormat = new SimpleDateFormat("yyyy_MM_dd_HH:mm:ss");
    Date date = new Date();
    if (app.db().groups().size() == 0) {
      app.goTo().groupPage();
      app.group().create(new GroupData().withName("test_"+ dateFormat.format(date)).withFooter("test2").withHeader("test3"));
    }
    if (app.db().contacts().size() == 0) {
      Groups dbGroups = app.db().groups();
      GroupData group = dbGroups.iterator().next();
      app.goTo().HomePage();
      app.contact().crate(new ContactData().withName("Дмитрий").withMiddlename("Тестович").withLastname("Петрович")
              .withNickname("Тестер").withTitle("Тестировщик").withCompany("Тест-Комплект").withAddress("г.Москва Тестовая дом 13")
              .withHomephone("8-495-123-56-78").withMobilephone("8-976-456-67-87").withEmail("test@test_complect.ru")
              .withBday("11").withBmonth("May").withByear("1980")
              .withHomeaddress("г. Москва, ул. Тестиррования  дом 13")
              .withWorkphone("495-123-45-67").withPhoto(new File("src/test/resources/photo_0.jpg")).inGroup(group));
    }
  }


  @Test
  public void testAddContactInGroup() throws Exception {
    DateFormat dateFormat = new SimpleDateFormat("yyyy_MM_dd_HH:mm:ss");
    Date date = new Date();
    Groups dbGroups = app.db().groups();
    Contacts dbContacts = app.db().contacts();
    app.goTo().HomePage();
    ContactData selectContact = dbContacts.iterator().next();
    if (selectContact.getGroups().equals(dbGroups)) {
      app.goTo().groupPage();
      app.group().create(new GroupData().withName("test_"+ dateFormat.format(date)).withFooter("test2").withHeader("test3"));
      dbGroups = app.db().groups();
    }
    for (GroupData group : dbGroups) {
    Contacts before = group.getContacts();
    app.goTo().HomePage();
    app.contact().addInGroup(selectContact, group);
    Contacts after=app.db().contacts();
    Assert.assertEquals(after,before.withAdded(selectContact));
    }
  }
 /* @Test
  public void testDeleteContactFromGroup() throws Exception {
    Groups dbGroups = app.db().groups();
    Contacts dbContacts = app.db().contacts();
    app.goTo().HomePage();
    ContactData selectContact = dbContacts.iterator().next();
    if (selectContact.getGroups().equals(dbGroups)) {
      app.goTo().groupPage();
      app.group().create(new GroupData().withName("test10").withFooter("test2").withHeader("test3"));
      dbGroups = app.db().groups();
    }
    for (GroupData group : dbGroups) {
      Contacts before = group.getContacts();
      app.goTo().HomePage();
      app.contact().addInGroup(selectContact, group);
      System.out.println(group.getContacts());
      Contacts after=app.db().contacts();
      Assert.assertEquals(after,before.withAdded(selectContact));
    }
  }*/
 }
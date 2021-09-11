package ru.lischita.les.jenkins.tests;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.lischita.les.jenkins.model.ContactData;
import ru.lischita.les.jenkins.model.Contacts;
import ru.lischita.les.jenkins.model.GroupData;
import ru.lischita.les.jenkins.model.Groups;
import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DBAddContactInGroupTest extends TestBase {
  DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
  Date date = new Date();

  @BeforeMethod
  public void ensurePreconditions() {
      if (app.db().groups().size() == 0) {
      app.goTo().groupPage();
      app.group().create(new GroupData().withName("test_"+ dateFormat.format(date)).withFooter("test2").withHeader("test3"));
    }
    if (app.db().contacts().size() == 0) {
      Groups dbGroups = app.db().groups();
      GroupData group = dbGroups.iterator().next();
      app.goTo().HomePage();
      app.contact().crate(new ContactData().withName("Дмитрий").withMiddlename("Тестович").withLastname("Иванов")
              .withNickname("Тестер").withTitle("Тестировщик").withCompany("Тест-Комплект").withAddress("г.Москва Тестовая дом 13")
              .withHomephone("8-495-123-56-78").withMobilephone("8-976-456-67-87").withEmail("test@test_complect.ru")
              .withBday("11").withBmonth("May").withByear("1980")
              .withHomeaddress("г. Москва, ул. Тестиррования  дом 13")
              .withWorkphone("495-123-45-67").withPhoto(new File("src/test/resources/photo_0.jpg")).inGroup(group));
    }
  }


  @Test
  public void testAddContactInGroup() throws Exception {
    Groups dbGroups = app.db().groups();
    Contacts dbContacts = app.db().contacts();
    app.goTo().HomePage();
    ContactData selectContact = dbContacts.iterator().next();
    if (selectContact.getGroups().equals(dbGroups))
    {
      app.goTo().groupPage();
      app.group().create(new GroupData().withName("test_"+ dateFormat.format(date)).withFooter("test2").withHeader("test3"));
      dbGroups = app.db().groups();
     }
    for (GroupData group : dbGroups) {
      if(selectContact.getGroups().contains(group))
      {
        System.out.println(group+" Группа в контакт"+selectContact.getId()+ " не бобавлена, так как там уже присутствует ");
      }
        else
        {Groups before=selectContact.getGroups();
        app.goTo().HomePage();
        app.contact().addInGroup(selectContact, group);
        Groups after=app.db().contactfromDB(selectContact).getGroups();
        Assert.assertEquals(after,before.withAdded(group));
        }
    }
  }

 }
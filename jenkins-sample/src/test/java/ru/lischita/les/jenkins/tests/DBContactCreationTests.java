package ru.lischita.les.jenkins.tests;
import org.testng.Assert;
import org.testng.annotations.Test;
import ru.lischita.les.jenkins.model.ContactData;
import ru.lischita.les.jenkins.model.Contacts;
import ru.lischita.les.jenkins.model.GroupData;
import ru.lischita.les.jenkins.model.Groups;


import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class DBContactCreationTests extends TestBase
{
  @Test
  public void testContactCreationTests () throws Exception
  {
    Contacts before = app.db().contacts();
    Groups dbGroups = app.db().groups();
    GroupData group = dbGroups.iterator().next();
    app.goTo().HomePage();
    ContactData contacts = new ContactData().withName("Дмитрий").withMiddlename("Тестович").withLastname("Иванов")
            .withNickname("Тестер").withTitle("Тестировщик").withCompany("Тест-Комплект").withAddress("г.Москва Тестовая дом 13")
            .withHomephone("8-495-123-56-78").withMobilephone("8-976-456-67-87").withEmail("test@test_complect.ru")
            .withBday("11").withBmonth("May").withByear("1980")
            .withHomeaddress("г. Москва, ул. Тестиррования  дом 13")
            .withWorkphone("495-123-45-67").withPhoto(new File("src/test/resources/photo_0.jpg")).inGroup(group);

    app.contact().crate(contacts);
    app.goTo().HomePage();
    Assert.assertEquals(app.contact().all().size(), before.size() + 1);
    Contacts after = app.db().contacts();
    assertThat(after, equalTo(before.withAdded(contacts.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));
    verifyInContactListInUI();
  }


}

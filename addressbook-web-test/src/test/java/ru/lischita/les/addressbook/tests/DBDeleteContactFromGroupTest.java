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
import java.util.Iterator;

public class DBDeleteContactFromGroupTest extends TestBase  {
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
  public void testDeleteContactFromGroup() throws Exception {
   Groups dbGroups = app.db().groups();
   Contacts dbContacts=app.db().contacts();
   Iterator <GroupData> group = dbGroups.iterator();
    while(group.hasNext()){
      Contacts before=group.next().getContacts(); // список всех контактов в группе до удаления
      if(before.isEmpty())
     { System.out.println("yes "+ before);
       /*ContactData selectContact = dbContacts.iterator().next();
       Groups before = selectContact.getGroups();
       app.goTo().HomePage();
       app.contact().delfromGroup(selectContact, group.next());
       Groups after = app.db().contactfromDB(selectContact).getGroups();
       Assert.assertEquals(after, before.withOut(group.next()));*/
     }
     else
       {System.out.println("no "+ before.stream().mapToInt((c) -> c.getId()).max().getAsInt());
       ContactData deleteContact=dbContacts.iterator().next().withId(before.stream().mapToInt((c) -> c.getId()).max().getAsInt()); // из всех контактов принадлежащих группе выбираю с максимальным id
       System.out.println("no " +deleteContact);
       //app.goTo().HomePage();
       //app.contact().delfromGroup(deleteContact, group.next()); ?? как определить группу
     }
    }

  //  System.out.println("Буаа  "+ before);

  // for(ContactData contact:dbContacts) {
  //   if (before.size() > 0) {
 //      GroupData group = dbGroups.iterator().next();
 //      app.goTo().HomePage();
 //      app.contact().delfromGroup(selectContact, group);
   //    Groups after = app.db().contactfromDB(selectContact).getGroups();
 //      Assert.assertEquals(after, before.withOut(group));
   //  } else {
//       selectContact = dbContacts.iterator().next();
     }
   }

  // System.out.println("Контакты  "+group.getContacts());

   //ContactData selectContact = dbContacts.iterator().next();



   // GroupData group= dbGroups.iterator().next().withId(310);
   // Contacts contacts =group.getContacts();
   // int df= contacts.size();

    //while(group.getContacts().size()== 0)
    //{
     // int i = dbGroups.iterator().next().getId();
      //group= dbGroups.iterator().next().withId(i=i+1);
      //group= dbGroups.iterator().next();
    //}
    //ContactData selectContact = group.getContacts().iterator().next();
      //ContactData selectContact = dbContacts.iterator().next();
      //Groups before = selectContact.getGroups();
    //  app.goTo().HomePage();
    //  app.contact().delfromGroup(selectContact, group);
   //   Groups after = app.db().contactfromDB(selectContact).getGroups();
    //  Assert.assertEquals(after, before.withOut(group));
//  }


//}

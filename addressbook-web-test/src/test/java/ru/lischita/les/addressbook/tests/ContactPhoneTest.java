package ru.lischita.les.addressbook.tests;
import org.testng.annotations.BeforeMethod;
import ru.lischita.les.addressbook.model.Contacts;
import ru.lischita.les.addressbook.model.GroupData;
import ru.lischita.les.addressbook.model.ContactData;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.equalTo;

public class ContactPhoneTest extends TestBase
{
  @BeforeMethod
  public void ensurePreconditions(){
    app.goTo().HomePage();
    if(app.contact().all().size()==0)
    {
      app.contact().crate(new ContactData().withName("Дмитрий").withMiddlename("Тестович").withLastname("Петрович").withNickname("Тестер").withTitle("Тестировщик").withCompany("Тест-Комплект").withAddress("г.Москва Тестовая дом 13").withHomephone("8-495-123-56-78").withMobilephone("8-976-456-67-87").withEmail("test@test_complect.ru").withBday("11").withBmonth("May").withByear("1980").withGroup("test1").withHomeaddress("г. Москва, ул. Тестиррования  дом 13").withWorkphone("495-123-45-67"));
    }
  }


  @Test
  public void testContactPhone()

  {
    app.goTo().HomePage();
    ContactData contact = app.contact().all().iterator().next();
    ContactData contactInfoFromEditForm = app.contact().infoEditForm(contact);
    /*Версия теста когда строку с телефонами резали в all()
    assertThat(contact.getHomephone(),equalTo(cleaned(contactInfoFromEditForm.getHomephone())));
    assertThat(contact.getMobilephone(),equalTo(cleaned(contactInfoFromEditForm.getMobilephone())));
    assertThat(contact.getWorkphone(),equalTo(cleaned(contactInfoFromEditForm.getWorkphone())));*/

    assertThat(contact.getAllphones(),equalTo(mergePhones(contactInfoFromEditForm))); // Вариант когда склеиваем строки

  }

  private String mergePhones(ContactData contact)
  {
    return  Arrays.asList(contact.getHomephone(),contact.getMobilephone(),contact.getWorkphone(),contact.getHomeaddress())
            .stream().filter(s->!s.equals(""))
            .map(ContactPhoneTest::cleaned)
            .collect(Collectors.joining("\n"));
  }

  static String cleaned (String phone)
  {
    return phone.replaceAll("\\s","").replaceAll("[-().]","");
  }


}

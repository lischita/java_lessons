package ru.lischita.les.jenkins.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.lischita.les.jenkins.model.ContactData;
import ru.lischita.les.jenkins.model.Contacts;

import java.io.File;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;
import static ru.lischita.les.jenkins.tests.TestBase.app;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class ContactCreationTests {
    @Test(enabled = false)
    public void testContactCreationTests () throws Exception
    {
        File photo = new File("src/test/resources/photo_0.jpg");
        app.goTo().HomePage();
        Contacts before = app.contact().all();
        ContactData contacts = new ContactData().withName("Дмитрий").withMiddlename("Тестович").withLastname("Петрович").withNickname("Тестер")
                .withTitle("Тестировщик").withCompany("Тест-Комплект").withAddress("г.Москва Тестовая дом 13").withHomephone("8-495-123-56-78")
                .withMobilephone("8-976-456-67-87").withEmail("test@test_complect.ru").withBday("11").withBmonth("May").withByear("1980")
                //.withGroup("test1") убрал перед выподнением ДЗ 16
                .withHomeaddress("г. Москва, ул. Тестиррования  дом 13").withWorkphone("495-123-45-67").withPhoto(photo);
        app.contact().crate(contacts);
        app.goTo().HomePage();
        Contacts after = app.contact().all();
        assertEquals(after.size(), before.size() + 1);
        assertEquals(before.withAdded(contacts.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt())).hashCode(), after.hashCode());
        assertThat(String.valueOf(after), equalTo(before.withAdded(contacts.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));
    }
}

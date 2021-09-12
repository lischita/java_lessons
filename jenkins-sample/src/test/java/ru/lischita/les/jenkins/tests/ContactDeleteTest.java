package ru.lischita.les.jenkins.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.lischita.les.jenkins.model.ContactData;
import ru.lischita.les.jenkins.model.Contacts;


import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class ContactDeleteTest extends TestBase{

  @Test
  public void testContactDelete()
  {
    app.goTo().HomePage();
    Contacts before=app.contact().all();
    ContactData contactGroup=before.iterator().next();
    app.contact().delete(contactGroup);
    app.goTo().HomePage();
    Contacts after=app.contact().all();
    Assert.assertEquals(after.size(), before.size() - 1);
    Assert.assertEquals(before.withOut(contactGroup).hashCode(),after.hashCode());
    assertThat(after, equalTo(before.withOut(contactGroup)));
   }

}



package ru.lischita.les.addressbook.tests;

import org.testng.annotations.Test;

public class ContactDeleteTest extends TestBase{

  @Test
  public void testContactDelete()
  {
    app.getNavigationHelper().gotoHomePage();
    app.getContactHelper().selectContact();
    app.getContactHelper().deleteSelectionContact();
    app.getContactHelper().confirmContactDeletion();
    app.getNavigationHelper().gotoHomePage();
  }
}

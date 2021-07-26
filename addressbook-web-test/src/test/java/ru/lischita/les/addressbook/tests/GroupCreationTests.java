package ru.lischita.les.addressbook.tests;
import org.testng.annotations.*;
import ru.lischita.les.addressbook.model.GroupData;

public class GroupCreationTests extends TestBase {

  @Test
  public void testGroupCreation() throws Exception {
    app.getNavigationHelper().goToGroupPage();
    app.getGroupHelper().crateGroup(new GroupData("test1", "test2", "test3"));
  }

}
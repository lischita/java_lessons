package ru.lischita.les.addressbook.tests;
import org.testng.Assert;
import org.testng.annotations.*;
import ru.lischita.les.addressbook.model.GroupData;

public class GroupCreationTests extends TestBase {

  @Test
  public void testGroupCreation() throws Exception {
    //int before=app.getGroupHelper().getGroupCount();
    app.getNavigationHelper().goToGroupPage();
    int before=app.getGroupHelper().getGroupCount();
    app.getGroupHelper().crateGroup(new GroupData("test1", "test2", "test3"));
    int after=app.getGroupHelper().getGroupCount();
    Assert.assertEquals(after,before+1);
  }

}

package ru.lischita.les.addressbook.tests;
import org.testng.Assert;
import org.testng.annotations.*;
import ru.lischita.les.addressbook.model.GroupData;

import java.util.List;

public class GroupCreationTests extends TestBase {

  @Test
  public void testGroupCreation() throws Exception {

    app.getNavigationHelper().goToGroupPage();
    List<GroupData> before=app.getGroupHelper().getGroupList();
    //int before=app.getGroupHelper().getGroupCount();
    app.getGroupHelper().crateGroup(new GroupData("test1", "test2", "test3"));
    List<GroupData> after=app.getGroupHelper().getGroupList();
    Assert.assertEquals(after.size(),before.size()+1);
  }

}

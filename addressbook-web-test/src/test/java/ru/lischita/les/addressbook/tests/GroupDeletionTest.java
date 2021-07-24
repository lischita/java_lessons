package ru.lischita.les.addressbook.tests;

import org.testng.annotations.Test;
import ru.lischita.les.addressbook.model.GroupData;

public class GroupDeletionTest extends TestBase {

  @Test
  public void testGroupDeletion() throws Exception {
    app.getNavigationHelper().goToGroupPage();
    if(!app.getGroupHelper().isAThereGroup())
    {
      app.getGroupHelper().crateGroup(new GroupData("test1", "test2", "test3"));
    }
    app.getGroupHelper().selectGroup();
    app.getGroupHelper().deletedSelectedGroups();
    app.getGroupHelper().returntoGroupPage();
  }

}

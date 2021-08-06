package ru.lischita.les.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.lischita.les.addressbook.model.GroupData;

import java.util.List;

public class GroupDeletionTest extends TestBase {

  @BeforeMethod
  public void ensurePreconditions(){
    app.getNavigationHelper().goToGroupPage();
    if(!app.getGroupHelper().isAThereGroup())
    {
      app.getGroupHelper().crateGroup(new GroupData("test1", "test2", "test3"));
    }
  }


  @Test
  public void testGroupDeletion() throws Exception {
   /* был до занятия 5
      app.getNavigationHelper().goToGroupPage();
       if(!app.getGroupHelper().isAThereGroup())
    {
      app.getGroupHelper().crateGroup(new GroupData("test1", "test2", "test3"));
    }*/
    List<GroupData> before=app.getGroupHelper().getGroupList();
    app.getGroupHelper().selectGroup(before.size()-1);
    app.getGroupHelper().deletedSelectedGroups();
    app.getGroupHelper().returntoGroupPage();
    List<GroupData> after=app.getGroupHelper().getGroupList();
    Assert.assertEquals(after.size(),before.size()-1);
    before.remove(before.size()-1);
    Assert.assertEquals(after,before);

  }

}

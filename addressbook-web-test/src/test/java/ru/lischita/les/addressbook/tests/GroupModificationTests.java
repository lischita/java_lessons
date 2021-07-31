package ru.lischita.les.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.lischita.les.addressbook.model.GroupData;

import java.util.List;

public class GroupModificationTests extends TestBase{

  @Test
  public void testGroupModification ()
  {
    app.getNavigationHelper().goToGroupPage();
     if(!app.getGroupHelper().isAThereGroup())
    {
      app.getGroupHelper().crateGroup(new GroupData("test1", "test2", "test3"));
    }
    List<GroupData> before=app.getGroupHelper().getGroupList();
    app.getGroupHelper().selectGroup(before.size()-1);
    app.getGroupHelper().initGroupModification();
    app.getGroupHelper().fillGroupForm(new GroupData("test1","test2","test3"));
    app.getGroupHelper().submitGroupModification();
    app.getGroupHelper().returntoGroupPage();
    List<GroupData> after=app.getGroupHelper().getGroupList();
    Assert.assertEquals(after.size(),before.size());

  }

}

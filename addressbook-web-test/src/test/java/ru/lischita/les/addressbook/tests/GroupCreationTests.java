package ru.lischita.les.addressbook.tests;
import jdk.jfr.EventType;
import org.testng.Assert;
import org.testng.annotations.*;
import ru.lischita.les.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

public class GroupCreationTests extends TestBase {

  @Test
  public void testGroupCreation() throws Exception {

    app.getNavigationHelper().goToGroupPage();
    List<GroupData> before=app.getGroupHelper().getGroupList();
    //int before=app.getGroupHelper().getGroupCount();
   GroupData group=new GroupData("test1", "test2", "test3");
    app.getGroupHelper().crateGroup(group);
    List<GroupData> after=app.getGroupHelper().getGroupList();
    Assert.assertEquals(after.size(),before.size()+1);
    /*int max=0;
    for (GroupData g:after){
      if(g.getId()>max){max=g.getId();}
    }
    group.setId(max);*/
    group.setId(after.stream().max((o1,o2)->Integer.compare(o1.getId(),o2.getId())).get().getId());
    before.add(group);
    Assert.assertEquals(new HashSet<Object>(after),new HashSet<Object>(before));
  }

}

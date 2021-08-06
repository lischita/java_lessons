package ru.lischita.les.addressbook.tests;
import org.testng.Assert;
import org.testng.annotations.*;
import ru.lischita.les.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;

public class GroupCreationTests extends TestBase {

  @Test
  public void testGroupCreation() throws Exception {

    app.goTo().groupPage();
    List<GroupData> before=app.group().list();
    //int before=app.getGroupHelper().getGroupCount();
    // GroupData group=new GroupData("test1", "test2", "test3");
    GroupData group=new GroupData().withName("test1").withFooter("test2").withHeader("test3");
    app.group().create(group);
    List<GroupData> after=app.group().list();
    Assert.assertEquals(after.size(),before.size()+1);
    /*int max=0;
    for (GroupData g:after){
      if(g.getId()>max){max=g.getId();}
    }
    group.setId(max);
    group.setId(after.stream().max((o1,o2)->Integer.compare(o1.getId(),o2.getId())).get().getId());
    before.add(group);
        Assert.assertEquals(new HashSet<Object>(after),new HashSet<Object>(before));
    */
    before.add(group);
    Comparator<? super GroupData> byId=(g1,g2)->Integer.compare(g1.getId(), g2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(after,before);
  }

}

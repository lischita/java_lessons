package ru.lischita.les.addressbook.appmanager;

import org.openqa.selenium.*;
import org.testng.Assert;
import ru.lischita.les.addressbook.model.ContactData;
import ru.lischita.les.addressbook.model.GroupData;

import java.util.ArrayList;
import java.util.List;


public class ContactHelper extends HelperBase {

  public ContactHelper(WebDriver wd) {
    super(wd);
  }

  public void submitContacform() {
    click(By.xpath("//div[@id='content']/form/input[21]"));
  }

  public void fillContactForm(ContactData contactData, boolean creation) {
    type(By.name("firstname"), contactData.getName());
    type(By.name("middlename"), contactData.getMiddlename());
    type(By.name("lastname"), contactData.getLastname());
    type(By.name("nickname"), contactData.getNickname());
    type(By.name("title"), contactData.getTitle());
    type(By.name("company"), contactData.getCompany());
    type(By.name("address"), contactData.getAddress());
    type(By.name("home"), contactData.getHomephone());
    type(By.name("mobile"), contactData.getMobilephone());
    type(By.name("email"), contactData.getEmail());

    select(By.name("bday"), contactData.getBday());
    select(By.name("bmonth"), contactData.getBmonth());

    type(By.name("byear"), contactData.getByear());

    if (creation){select(By.name("new_group"), contactData.getGroup());}
    else { Assert.assertFalse(IsElementPresent(By.name("new_group")));}

    type(By.name("phone2"), contactData.getHomeaddress());

  }

  public void initContactCreation() {
    click(By.linkText("add new"));
  }

  public void selectContact(int index) {
    wd.findElements(By.name("selected[]")).get(index).click();
//    click(By.name("selected[]"));
  }

  public void deleteSelectionContact() {
    click(By.xpath("//input[@value='Delete']"));
  }

  public void confirmContactDeletion() {
    wd.switchTo().alert().accept();
  }

  public void initEditContact(int index) {
   // int count=index+2;
   // click(By.xpath("//*[@id=\"maintable\"]/tbody/tr["+count+"]/td[8]/a/img"));
    wd.findElements(By.xpath("//img[@alt='Edit']")).get(index).click();
 //   click(By.xpath("//img[@alt='Edit']"));

  }

  public void submitEditForm() {
    click(By.xpath("//div[@id='content']/form/input[22]"));
  }

  public void crateContact(ContactData group) {
    initContactCreation();
    fillContactForm(group,true);
    submitContacform();

  }

  public boolean isAThereContact() {
    return IsElementPresent(By.name("selected[]"));
  }

  /*public List<ContactData> getContactList()
  { String name=null;
    String lastname=null;
    int i=2;
    List<ContactData> groups=new ArrayList<ContactData>();
    List<WebElement> elements = wd.findElements(By.name("selected[]"));
    for(WebElement element:elements){
        name= element.findElement(By.xpath("//*[@id=\"maintable\"]/tbody/tr["+i+"]/td[3]")).getText();
        lastname = element.findElement(By.xpath("//*[@id=\"maintable\"]/tbody/tr["+i+"]/td[2]")).getText();
        int id=Integer.parseInt(element.findElement(By.xpath("//*[@id=\"maintable\"]/tbody/tr["+i+"]/td[1]/input")).getAttribute("value"));
        ContactData group = new ContactData(id, name, null, lastname, null, null, null, null, null, null, null, null, null, null, null, null);
        groups.add(group);
         i++;
      }
    return groups;
  }*/

  public List<ContactData> getContactList()
  { List<ContactData> groups=new ArrayList<ContactData>();
    List<WebElement> elements = wd.findElements(By.name("entry"));
    for(WebElement element:elements) {
        List <WebElement> cells=element.findElements(By.tagName("td"));
        String name = cells.get(2).getText();
        String lastname = cells.get(1).getText();
        int id = Integer.parseInt(element.findElement(By.cssSelector("input")).getAttribute("value"));
        ContactData group = new ContactData(id, name, null, lastname, null, null, null, null, null, null, null, null, null, null, null, null);
        groups.add(group);
    }
    return groups;
  }
}


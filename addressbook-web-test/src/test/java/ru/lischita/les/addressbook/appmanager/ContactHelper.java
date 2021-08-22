package ru.lischita.les.addressbook.appmanager;

import org.openqa.selenium.*;
import org.testng.Assert;
import ru.lischita.les.addressbook.model.ContactData;
import ru.lischita.les.addressbook.model.Contacts;

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
    type(By.name("email2"), contactData.getEmail2());
    type(By.name("email3"), contactData.getEmail3());
    select(By.name("bday"), contactData.getBday());
    select(By.name("bmonth"), contactData.getBmonth());
    attach(By.name("photo"), contactData.getPhoto());

    type(By.name("byear"), contactData.getByear());

    if (creation){select(By.name("new_group"), contactData.getGroup());}
    else { Assert.assertFalse(IsElementPresent(By.name("new_group")));}

    type(By.name("phone2"), contactData.getHomeaddress());
    type(By.name("work"), contactData.getWorkphone());

  }

  public void initContactCreation() {
    click(By.linkText("add new"));
  }

  public void selectContact(int index) {
    wd.findElements(By.name("selected[]")).get(index).click();
//    click(By.name("selected[]"));
  }

  public void selectContactById(int id) {
    wd.findElement(By.cssSelector("input[value='"+id+"']")).click();
  }

  public void deleteContact() {
    click(By.xpath("//input[@value='Delete']"));
  }

  public void confirmContactDeletion() {
    wd.switchTo().alert().accept();
  }

  public void initEditContact(int id) {
   // int count=index+2;
   // click(By.xpath("//*[@id=\"maintable\"]/tbody/tr["+count+"]/td[8]/a/img"));
    //wd.findElements(By.xpath("//img[@alt='Edit']")).get(id).click();
    wd.findElement(By.cssSelector(String.format("a[href='edit.php?id=%s']",id))).click();
 //   click(By.xpath("//img[@alt='Edit']"));

  }

  public void submitEditForm() {
    click(By.xpath("//div[@id='content']/form/input[22]"));
  }

  public void crate(ContactData contacts) {
    initContactCreation();
    fillContactForm(contacts,true);
    submitContacform();
    contactsCache=null;
  }
  public void modify(ContactData contacts) {
    initEditContact(contacts.getId());
    fillContactForm(contacts,false);
    submitEditForm();
    contactsCache=null;
  }

  public void delete(ContactData contact) {
    selectContactById(contact.getId());
    deleteContact();
    confirmContactDeletion();
    contactsCache=null;
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

 /* public List<ContactData> getContactList()
  { List<ContactData> groups=new ArrayList<ContactData>();
    List<WebElement> elements = wd.findElements(By.name("entry"));
    for(WebElement element:elements) {
        List <WebElement> cells=element.findElements(By.tagName("td"));
        String name = cells.get(2).getText();
        String lastname = cells.get(1).getText();
        int id = Integer.parseInt(element.findElement(By.cssSelector("input")).getAttribute("value"));
        ContactData group = new ContactData(id, name, null, lastname, null, null, null, null, null, null, null, null, null, null, null, null);
        //groups.add(group);
    }
    return groups;
  }*/

  private Contacts contactsCache=null;

  public Contacts all()
  {
    if(contactsCache!=null) {return new Contacts(contactsCache);}
    contactsCache=new Contacts();
    List<WebElement> elements = wd.findElements(By.name("entry"));
    for(WebElement element:elements) {
      List <WebElement> cells=element.findElements(By.tagName("td"));
      String name = cells.get(2).getText();
      String lastname = cells.get(1).getText();
      //String[] phones = cells.get(5).getText().split("\n");  Получаем данные из 6 столбца и разрезаем их
      int id = Integer.parseInt(element.findElement(By.cssSelector("input")).getAttribute("value"));
     // ContactData contact = new ContactData().withId(id).withName(name).withLastname(lastname).withHomephone(phones[0]).withMobilephone(phones[1]).withWorkphone(phones[2]);
      // загружаем полученные части по телефонам
      String allphones=cells.get(5).getText();
      String allemails=cells.get(4).getText();
      String address=cells.get(3).getText();
      ContactData contact = new ContactData().withId(id).withName(name).withLastname(lastname).withAllphones(allphones).withAllemails(allemails).withAddress(address);
      contactsCache.add(contact);
    }
    return new Contacts(contactsCache);

  }

  public ContactData infoEditForm(ContactData contact)
  {
    initEditContact(contact.getId());
    String name= wd.findElement(By.name("firstname")).getAttribute("value");
    String lastname= wd.findElement(By.name("lastname")).getAttribute("value");
    String home= wd.findElement(By.name("home")).getAttribute("value");
    String mobile= wd.findElement(By.name("mobile")).getAttribute("value");
    String work= wd.findElement(By.name("work")).getAttribute("value");
    String homeaddress= wd.findElement(By.name("phone2")).getAttribute("value");
    String email= wd.findElement(By.name("email")).getAttribute("value");
    String email2= wd.findElement(By.name("email2")).getAttribute("value");
    String email3= wd.findElement(By.name("email3")).getAttribute("value");
    String address= wd.findElement(By.name("address")).getAttribute("value");
    wd.navigate().back();
    return new ContactData().withId(contact.getId()).withName(name).withLastname(lastname).withHomephone(home).withMobilephone(mobile).withWorkphone(work)
            .withHomeaddress(homeaddress).withEmail(email).withEmail2(email2).withEmail3(email3).withAddress(address);
  }
  public int count() {
    return wd.findElements(By.name("entry")).size();
  }

}


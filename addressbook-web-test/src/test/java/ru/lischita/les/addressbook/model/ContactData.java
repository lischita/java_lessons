package ru.lischita.les.addressbook.model;

import com.google.gson.annotations.Expose;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;

import java.io.File;
import java.util.Objects;

@XStreamAlias("contact") // указываем что в xml использовать тег contact
public class ContactData {
  @XStreamOmitField   // указываем что следующее поле не записывать в xml
  private int id=Integer.MAX_VALUE;
  @Expose // указываем что это поле обязательно жолжно быть в json файле
  private  String name;
  @Expose // указываем что это поле обязательно жолжно быть в json файле
  private  String middlename;
  @Expose // указываем что это поле обязательно жолжно быть в json файле
  private  String lastname;
  @Expose // указываем что это поле обязательно жолжно быть в json файле
  private  String nickname;
  @Expose // указываем что это поле обязательно жолжно быть в json файле
  private  String title;
  @Expose // указываем что это поле обязательно жолжно быть в json файле
  private  String company;
  @Expose // указываем что это поле обязательно жолжно быть в json файле
  private  String address;
  @Expose // указываем что это поле обязательно жолжно быть в json файле
  private  String homephone;
  @Expose // указываем что это поле обязательно жолжно быть в json файле
  private  String mobilephone;
  @Expose // указываем что это поле обязательно жолжно быть в json файле
  private  String workphone;
  @Expose // указываем что это поле обязательно жолжно быть в json файле
  private  String email;
  @Expose // указываем что это поле обязательно жолжно быть в json файле
  private  String email2;
  @Expose // указываем что это поле обязательно жолжно быть в json файле
  private  String email3;
  @Expose // указываем что это поле обязательно жолжно быть в json файле
  private  String bday;
  @Expose // указываем что это поле обязательно жолжно быть в json файле
  private  String bmonth;
  @Expose // указываем что это поле обязательно жолжно быть в json файле
  private  String byear;
  @Expose // указываем что это поле обязательно жолжно быть в json файле
  private  String group;
  @Expose // указываем что это поле обязательно жолжно быть в json файле
  private  String homeaddress;
  @Expose // указываем что это поле обязательно жолжно быть в json файле
  private  String allphones;
  @Expose // указываем что это поле обязательно жолжно быть в json файле
  private  String allemails;
  @Expose// указываем что это поле обязательно жолжно быть в json файле
  private   File photo;


  /*public ContactData(int id,String name, String middlename, String lastname, String nickname, String title, String company, String address, String homephone, String mobilephone, String email, String bday, String bmonth, String byear, String group, String homeaddress) {
    this.id=id;
    this.name = name;
    this.middlename = middlename;
    this.lastname = lastname;
    this.nickname = nickname;
    this.title = title;
    this.company = company;
    this.address = address;
    this.homephone = homephone;
    this.mobilephone = mobilephone;
    this.email = email;
    this.bday = bday;
    this.bmonth = bmonth;
    this.byear = byear;
    this.group = group ;
    this.homeaddress = homeaddress;
  }


  public ContactData(String name, String middlename, String lastname, String nickname, String title, String company, String address, String homephone, String mobilephone, String email, String bday, String bmonth, String byear, String group, String homeaddress) {
    this.id=Integer.MAX_VALUE;
    this.name = name;
    this.middlename = middlename;
    this.lastname = lastname;
    this.nickname = nickname;
    this.title = title;
    this.company = company;
    this.address = address;
    this.homephone = homephone;
    this.mobilephone = mobilephone;
    this.email = email;
    this.bday = bday;
    this.bmonth = bmonth;
    this.byear = byear;
    this.group = group ;
    this.homeaddress = homeaddress;
  }*/

  public int getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public String getMiddlename() {
    return middlename;
  }

  public String getLastname() {
    return lastname;
  }

  public String getNickname() {
    return nickname;
  }

  public String getTitle() {
    return title;
  }

  public String getCompany() {
    return company;
  }

  public String getAddress() {
    return address;
  }

  public String getHomephone() {
    return homephone;
  }

  public String getMobilephone() {
    return mobilephone;
  }

  public String getEmail() {
    return email;
  }

  public String getEmail2() { return email2;  }

  public String getEmail3() {  return email3;  }

  public String getBday() {
    return bday;
  }

  public String getBmonth() {
    return bmonth;
  }

  public String getByear() {
    return byear;
  }

  public String getGroup() {
    return group;
  }

  public String getHomeaddress() {
    return homeaddress;
  }

  public String getWorkphone() {
    return workphone;
  }

  public String getAllphones() {
    return allphones;
  }
  public String getAllemails() {
    return allemails;
  }

  public File getPhoto() {
    return photo;
  }

  public ContactData withPhoto(File photo) {
    this.photo = photo;
    return this;
  }

  public ContactData withId(int id) {
    this.id = id;
    return this;
  }

  public ContactData withName(String name) {
    this.name = name;
    return this;
  }

  public ContactData withMiddlename(String middlename) {
    this.middlename = middlename;
    return this;
  }

  public ContactData withLastname(String lastname) {
    this.lastname = lastname;
    return this;
  }

  public ContactData withNickname(String nickname) {
    this.nickname = nickname;
    return this;
  }

  public ContactData withTitle(String title) {
    this.title = title;
    return this;
  }

  public ContactData withCompany(String company) {
    this.company = company;
    return this;
  }

  public ContactData withAddress(String address) {
    this.address = address;
    return this;
  }

  public ContactData withHomephone(String homephone) {
    this.homephone = homephone;
    return this;
  }

  public ContactData withMobilephone(String mobilephone) {
    this.mobilephone = mobilephone;
    return this;
  }

  public ContactData withEmail(String email) {
    this.email = email;
    return this;
  }
  public ContactData withEmail2(String email2) {
    this.email2 = email2;
    return this;
  }
  public ContactData withEmail3(String email3) {
    this.email3 = email3;
    return this;
  }

  public ContactData withBday(String bday) {
    this.bday = bday;
    return this;
  }

  public ContactData withBmonth(String bmonth) {
    this.bmonth = bmonth;
    return this;
  }

  public ContactData withByear(String byear) {
    this.byear = byear;
    return this;
  }

  public ContactData withGroup(String group) {
    this.group = group;
    return this;
  }
  public ContactData withHomeaddress(String homeaddress) {
    this.homeaddress = homeaddress;
    return this;
  }

  public ContactData withWorkphone(String workphone) {
    this.workphone = workphone;
    return this;
  }
  public ContactData withAllphones(String allphones) {
    this.allphones = allphones;
    return this;
  }
  public ContactData withAllemails(String allemails) {
    this.allemails = allemails;
    return this;
  }

  @Override
  public String toString() {
    return "ContactData{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", middlename='" + middlename + '\'' +
            ", lastname='" + lastname + '\'' +
            ", nickname='" + nickname + '\'' +
            ", title='" + title + '\'' +
            ", company='" + company + '\'' +
            ", address='" + address + '\'' +
            ", homephone='" + homephone + '\'' +
            ", mobilephone='" + mobilephone + '\'' +
            ", email='" + email + '\'' +
            ", email2='" + email2 + '\'' +
            ", email3='" + email3 + '\'' +
            ", bday='" + bday + '\'' +
            ", bmonth='" + bmonth + '\'' +
            ", byear='" + byear + '\'' +
            ", group='" + group + '\'' +
            ", homeaddress='" + homeaddress + '\'' +
            ", workphone='" + workphone + '\'' +
            ", allphones='" + allphones+ '\'' +
            ", allemails='" + allemails+ '\'' +
            '}';
  }



  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ContactData that = (ContactData) o;
    return id == that.id && Objects.equals(name, that.name) && Objects.equals(lastname, that.lastname);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, lastname);
  }




}

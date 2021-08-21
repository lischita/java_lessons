package ru.lischita.les.addressbook.appmanager;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import ru.lischita.les.addressbook.model.ContactData;
import ru.lischita.les.addressbook.model.Contacts;
import ru.lischita.les.addressbook.model.GroupData;
import ru.lischita.les.addressbook.model.Groups;

import java.util.List;

public class DBHelper {

  private final SessionFactory sessionFactory;

  public DBHelper ()  // создаем соединение с БД
  {
   final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
            .configure() // configures settings from hibernate.cfg.xml
            .build();
    sessionFactory = new MetadataSources( registry ).buildMetadata().buildSessionFactory();

  }

  public Groups groups (){
    Session session = sessionFactory.openSession();
    session.beginTransaction();
    List<GroupData> result = session.createQuery( "from GroupData" ).list(); // извелкаем объект OQL ( object query language )
    session.getTransaction().commit();
    session.close();
    return new Groups(result);
  }

  public Contacts contacts (){
    Session session = sessionFactory.openSession();
    session.beginTransaction();
    List<ContactData> result = session.createQuery( "from ContactData where deprecated='0000-00-00'" ).list(); // извелкаем объект OQL ( object query language )
    session.getTransaction().commit();
    session.close();
    return new Contacts(result);
  }

}

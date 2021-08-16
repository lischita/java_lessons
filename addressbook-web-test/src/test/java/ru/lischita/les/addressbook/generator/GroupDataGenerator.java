package ru.lischita.les.addressbook.generator;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.thoughtworks.xstream.XStream;
import ru.lischita.les.addressbook.model.GroupData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class GroupDataGenerator {

  @Parameter(names = "-c",description = ("Group Count"))
  public int count;
  @Parameter(names = "-f",description = ("Target File"))
  public String file;
  @Parameter(names = "-d",description = ("Data Format"))
  public String format;  // пример команды -c 2 -d csv -f src/test/resurces/groups.csv

  public static void main (String[] args) throws IOException {
    GroupDataGenerator generator=new GroupDataGenerator();
    JCommander jCommander=new JCommander(generator);
    try{
    jCommander.parse(args);}
    catch (ParameterException ex){
      jCommander.usage();
      return;
    }
    generator.run();
    //int count =Integer.parseInt(args[0]);
   // File file = new File(args[1]);
   }

  private void run() throws IOException {
    List<GroupData> groups=generateGroups(count);
    if (format.equals("csv")) {saveAsCSV(groups,new File(file));}
    else if (format.equals("xml")){saveAsXML(groups,new File(file));}
    else if (format.equals("json")){saveAsJSON(groups,new File(file));}
    else System.out.println(("Unrecognized format data"+format));
  }

    private List<GroupData> generateGroups(int count ){
    List<GroupData> groups=new ArrayList<>();
    for (int i=0;i<count;i++){
    groups.add(new GroupData().withName(String.format("test %s",i))
                              .withHeader(String.format("header %s",i))
                              .withFooter(String.format("footer %s",i)));
    }

    return groups;
  }

  private  void saveAsCSV(List<GroupData> groups, File file) throws IOException {
    try (Writer writer= new FileWriter(file))
    {
    for (GroupData group:groups) {
      writer.write(String.format("%s;%s;%s\n", group.getName(), group.getHeader(), group.getFooter()));
    }
    }
   }

  private void saveAsXML(List<GroupData> groups, File file) throws IOException {
    XStream xStream=new XStream();
    xStream.processAnnotations(GroupData.class);
    String xml=xStream.toXML(groups);
    try (Writer writer= new FileWriter(file)) {writer.write(xml);}
  }

  private void saveAsJSON(List<GroupData> groups, File file) throws IOException {
   // Gson gson= new GsonBuilder().setPrettyPrinting().create(); // сохранили все поля  в файл json
    Gson gson= new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create(); // Сохранили только те поля, что помечены аннотаций Expose
    String json=gson.toJson(groups);
    try (Writer writer= new FileWriter(file)) { writer.write(json);}
  }


}

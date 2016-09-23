import java.util.List;
import java.util.ArrayList;
import org.sql2o.*;

public class Stylist {
  private int id;
  private String name;
  private String otherSkills;
  private String hairStyle;
  // private List<Client> cilentList;

  public Stylist (String name, String hairStyle, String otherSkills) {
    this.name = name;
    this.otherSkills = otherSkills;
    this.hairStyle = hairStyle;
  }

  public String getName() {
    return name;
  }

  public String getSkills() {
    return otherSkills;
  }

  public String getStyle() {
    return hairStyle;
  }

  public int getId() {
    return id;
  }

  @Override
  public boolean equals(Object otherStylist) {
    if (!(otherStylist instanceof Stylist)) {
      return false;
    } else {
      Stylist newStylist = (Stylist) otherStylist;
      return this.getName().equals(newStylist.getName()) &&
             this.getStyle().equals(newStylist.getStyle()) &&
             this.getSkills().equals(newStylist.getSkills());
    }
  }

  public void save() {
    try (Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO stylists (name, hairStyle, otherSkills) VALUES (:name, :hairStyle, :otherSkills)";
      this.id = (int) con.createQuery(sql, true)
      .addParameter("name", this.name)
      .addParameter("hairStyle", this.hairStyle)
      .addParameter("otherSkills", this.otherSkills)
      .executeUpdate()
      .getKey();
    }
  }

  public static List<Stylist> all() {
    try (Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM stylists";
      return con.createQuery(sql).executeAndFetch(Stylist.class);
    }
  }

  public static Stylist find(int id) {
    try (Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM stylists WHERE id = :id";
      return con.createQuery(sql)
             .addParameter("id", id)
             .executeAndFetchFirst(Stylist.class);
    }
  }

  public List<Client> getClient() {
    try (Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM clients WHERE stylist_id = :id";
      return con.createQuery(sql)
             .addParameter("id", id)
             .executeAndFetch(Client.class);
    }
  }

  public void delete() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "DELETE FROM stylists WHERE id = :id;";
      con.createQuery(sql)
        .addParameter("id", id)
        .executeUpdate();

        int newStylist_id = -1;

        sql = "UPDATE clients SET stylist_id = :newStylist_id WHERE stylist_id = :stylist_id";
        con.createQuery(sql)
        .addParameter("stylist_id", this.id)
        .addParameter("newStylist_id", newStylist_id)
        .executeUpdate();
      }
  }
}
import org.sql2o.*;
import java.util.List;
import java.util.ArrayList;

public class Client {
  private int id;
  private String name;
  private String likes;
  private int stylist_id;

  public Client(String name, String likes, int stylist_id) {
    this.name = name;
    this.likes = likes;
    this.stylist_id = stylist_id;
  }

  public String getName() {
    return name;
  }

  public String getLike() {
    return likes;
  }

  public int getStylistId() {
    return stylist_id;
  }

  public int getId() {
    return id;
  }

  @Override
  public boolean equals(Object otherClient) {
    if (!(otherClient instanceof Client)) {
      return false;
    } else {
      Client review = (Client) otherClient;
      return this.getName().equals(review.getName()) &&
             this.getLike().equals(review.getLike()) &&
             this.getStylistId() == review.getStylistId();
    }
  }

  public void save() {
    try (Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO clients (name, likes, stylist_id) VALUES (:name, :likes, :stylist_id)";
      this.id = (int) con.createQuery(sql, true)
      .addParameter("name", this.name)
      .addParameter("likes", this.likes)
      .addParameter("stylist_id", this.stylist_id)
      .executeUpdate()
      .getKey();
    }
  }

  public static List<Client> all() {
    try (Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM clients";
      return con.createQuery(sql)
             .executeAndFetch(Client.class);
    }
  }

  public static Client find(int id) {
    try (Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM clients WHERE id = :id";
      return con.createQuery(sql)
      .addParameter("id", id)
      .executeAndFetchFirst(Client.class);
    }
  }

  public void update(String name, String likes, int stylist_id) {
    try (Connection con = DB.sql2o.open()) {
      this.likes = likes;
      this.name = name;
      this.stylist_id = stylist_id;
      String sql = "UPDATE clients SET likes = :likes, name = :name, stylist_id = :stylist_id WHERE id = :id";
      con.createQuery(sql)
      .addParameter("id", this.id)
      .addParameter("name", this.name)
      .addParameter("stylist_id", this.stylist_id)
      .addParameter("likes", this.likes)
      .executeUpdate();
    }
  }

  public void delete() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "DELETE FROM clients WHERE id = :id;";
      con.createQuery(sql)
        .addParameter("id", id)
        .executeUpdate();
      }
  }
}
import org.junit.*;
import static org.junit.Assert.*;
import java.util.Arrays;

public class StylistTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void Stylist_correctlyInstantiates_true() {
    Stylist testStylist = new Stylist("bob", "short cut", "great with kids");
    assertTrue(testStylist instanceof Stylist);
  }

  @Test
  public void getName_returnStylistName_true() {
    Stylist testStylist = new Stylist("bob", "short cut", "great with kids");
    assertEquals("bob", testStylist.getName());
  }

  @Test
  public void getStyle_returnStylistStyle_true() {
    Stylist testStylist = new Stylist("bob", "short cut", "great with kids");
    assertEquals("short cut", testStylist.getStyle());
  }

  @Test
  public void getSkills_returnStylistSkills_true() {
    Stylist testStylist = new Stylist("bob", "short cut", "great with kids");
    assertEquals("great with kids", testStylist.getSkills());
  }

  @Test
  public void equals_FirstStylistAndSecondStylist_true() {
    Stylist firstStylist = new Stylist("bob", "short cut", "great with kids");
    Stylist secondStylist = new Stylist("bob", "short cut", "great with kids");
    assertTrue(firstStylist.equals(secondStylist));
  }

  @Test
  public void save_platformSavesToDatabase_true() {
    Stylist testStylist = new Stylist("bob", "short cut", "great with kids");
    testStylist.save();
    assertTrue(testStylist.getId() > 0);
  }

  @Test
  public void all_returnsAllInstancesOfStylist_true() {
    Stylist testStylist = new Stylist("bob", "short cut", "great with kids");
    testStylist.save();
    assertTrue(testStylist.equals(Stylist.all().get(0)));
  }

  @Test
  public void find_returnsStylistWithSameId_true() {
    Stylist testStylist = new Stylist("bob", "short cut", "great with kids");
    testStylist.save();
    Stylist test2Stylist = new Stylist("adam", "long cut", "ok with kids");
    test2Stylist.save();
    Stylist foundStylist = Stylist.find(test2Stylist.getId());
    assertTrue(test2Stylist.equals(foundStylist));
  }

  @Test
  public void getClient_returnInstantancesOfClientforStylist_true() {
    Stylist testStylist = new Stylist("bob", "short cut", "great with kids");
    testStylist.save();
    Client firstClient = new Client("Adam", "like short cut and talking with person", testStylist.getId());
    firstClient.save();
    Client secondClient = new Client("Adam", "like short cut and talking with person", testStylist.getId());
    secondClient.save();
    Client[] client = new Client[] { firstClient, secondClient };
    assertTrue(testStylist.getClient().containsAll(Arrays.asList(client)));
  }

  @Test
  public void delete_deletesStylist_true() {
    Stylist testStylist = new Stylist("bob", "short cut", "great with kids");
    testStylist.save();
    int id = testStylist.getId();
    testStylist.delete();
    assertEquals(null, Stylist.find(id));
  }

  @Test
  public void updateTheClient_deletesStylist_true() {
    Stylist testStylist = new Stylist("bob", "short cut", "great with kids");
    testStylist.save();
    Client firstClient = new Client("Adam", "like short cut and talking with person", testStylist.getId());
    firstClient.save();
    Client secondClient = new Client("Adam", "like short cut and talking with person", testStylist.getId());
    secondClient.save();
    testStylist.delete();
    Client updateClient = Client.find(firstClient.getId());
    Client updateClient2 = Client.find(secondClient.getId());
    assertEquals(-1, updateClient.getStylistId());
    assertEquals(-1, updateClient2.getStylistId());
  }

  @Test
  public void update_updatesStyleWithSameContent_true() {
    String style = "like short cut";
    String name = "Adam";
    String skills = "bad with kids";
    Stylist testStylist = new Stylist("bob", "short cut", "great with kids");
    testStylist.save();
    testStylist.update(name, style, skills);
    assertEquals(name, testStylist.getName());
  }

}
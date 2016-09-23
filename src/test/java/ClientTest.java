import java.util.Arrays;
import java.time.LocalDateTime;
import org.junit.*;
import static org.junit.Assert.*;
import java.util.Date;
import java.sql.Timestamp;

public class ClientTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void Client_correctlyInstantiates_true() {
    Client testClient = new Client("Adam", "like short cut and talking with person", 1);
    assertTrue(testClient instanceof Client);
  }

  @Test
  public void getName_ReturnClientName_True() {
    Client testClient = new Client("Adam", "like short cut and talking with person", 1);
    assertEquals("Adam", testClient.getName());
  }

  @Test
  public void getLike_ReturnClientLike_True() {
    Client testClient = new Client("Adam", "like short cut and talking with person", 1);
    assertEquals("like short cut and talking with person", testClient.getLike());
  }

  @Test
  public void equals_FirstClientIsEqualToSecondClient_true() {
    Client firstClient = new Client("Adam", "like short cut and talking with person", 1);
    Client secondClient = new Client("Adam", "like short cut and talking with person", 1);
    assertTrue(firstClient.equals(secondClient));
  }

  @Test
  public void save_savesTestClientToDatabase_true() {
    Client testClient = new Client("Adam", "like short cut and talking with person", 1);
    testClient.save();
    assertTrue(testClient.getId() > 0);
  }

  @Test
  public void all_saveTestClientToDatabase_true() {
    Client firstClient = new Client("Adam", "like short cut and talking with person", 1);
    firstClient.save();
    assertTrue(firstClient.equals(Client.all().get(0)));
  }

  @Test
  public void find_returnsSameClientId_true() {
    Client firstClient = new Client("Adam", "like short cut and talking with person", 1);
    firstClient.save();
    Client secondClient = new Client("Adam", "like short cut and talking with person", 1);
    secondClient.save();
    Client savedClient = Client.find(secondClient.getId());
    assertTrue(secondClient.equals(savedClient));
  }

  @Test
  public void update_updatesClientWithSameContent_true() {
    String like = "like short cut";
    String name = "bob";
    int id = -10;
    Client firstClient = new Client("Adam", "like short cut and talking with person", 1);
    firstClient.save();
    firstClient.update(name, like, id);
    assertEquals(like, firstClient.getLike());
  }

  @Test
  public void delete_deletesClient_true() {
    Client testClient = new Client("Adam", "like short cut and talking with person", 1);
    testClient.save();
    int id = testClient.getId();
    testClient.delete();
    assertEquals(null, Client.find(id));
  }
}
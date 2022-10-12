import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectClasses({GameUnitTest.class, AcceptanceTestPart1.class})
public class TestSuite {

}

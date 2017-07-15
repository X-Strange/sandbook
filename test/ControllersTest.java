// import org.junit.*;

// import java.util.*;

// import static play.test.Helpers.*;
// import static org.fest.assertions.Assertions.*;
// import play.mvc.*;
// import play.mvc.Results.Status;

// import controllers.*;


// public class ControllersTest {
    
//     @Test
//     public void IndexTest() {
//     	Application app = new Application();
//         Status result = (Status) app.index();
//         int expectedResult = Results.ok().getWrappedSimpleResult().header().status();
//     	assertThat(result.getWrappedSimpleResult().header().status() == expectedResult); 
//     }


//     @Test
//     public void DashboardTest() {
//         Application app = new Application();
//         Status result = (Status) app.dashboard();
//         int expectedResult = Results.ok().getWrappedSimpleResult().header().status();
//         assertThat(result.getWrappedSimpleResult().header().status() == expectedResult); 
//     }


//     @Test
//     public void GetIndexTest() {
//         Application app = new Application();
//         Status result = (Status) app.getIndex();
//         int expectedResult = Results.ok().getWrappedSimpleResult().header().status();
//         assertThat(result.getWrappedSimpleResult().header().status() == expectedResult); 
//     }
// }
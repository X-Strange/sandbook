import org.junit.*;
import java.util.*;

import play.test.*;
import play.libs.F.*;

import static play.test.Helpers.*;
import static org.fest.assertions.Assertions.*;

import models.User;
import static org.fluentlenium.core.filter.FilterConstructor.*;


public class UserTest {
    @Test
    public void SetUserFirstNameTest() {
        running(testServer(3333, fakeApplication(inMemoryDatabase())), FIREFOX, new Callback<TestBrowser>() {
            public void invoke(TestBrowser browser) {
                User user = new User();
                user.setFirstName("firstName");
                assertThat("firstName" == user.getFirstName()); 
            }
        });
    }


    @Test
    public void SetUserLastNameTest() {
        running(testServer(3333, fakeApplication(inMemoryDatabase())), FIREFOX, new Callback<TestBrowser>() {
            public void invoke(TestBrowser browser) {
                User user = new User();
                user.setLastName("lastName");
                assertThat("lastName" == user.getLastName()); 
            }
        });
    }


    @Test
    public void SetUserNicknameTest() {
        running(testServer(3333, fakeApplication(inMemoryDatabase())), FIREFOX, new Callback<TestBrowser>() {
            public void invoke(TestBrowser browser) {
                User user = new User();
                user.setNickname("nickName");
                assertThat("nickName" == user.getNickname()); 
            }
        });
    }


    @Test
    public void SetUserAvatarUrlTest() {
        running(testServer(3333, fakeApplication(inMemoryDatabase())), FIREFOX, new Callback<TestBrowser>() {
            public void invoke(TestBrowser browser) {
                User user = new User();
                user.setAvatarUrl("avatarUrl");
                assertThat("avatarUrl" == user.getAvatarUrl()); 
            }
        });
    }
}
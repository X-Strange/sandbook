package models;

import com.github.cleverage.elasticsearch.Index;
import com.github.cleverage.elasticsearch.Indexable;
import com.github.cleverage.elasticsearch.annotations.IndexType;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by tkmaec0 on 6/4/14.
 */
@IndexType(name = "user")
public class User extends Index{

    private String firstName;
    private String lastName;
    private String nickname;
    private String avatarUrl;

    public static Finder<User> find = new Finder<User>(User.class);

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }


    @Override
    public Map toIndex() {
        HashMap map = new HashMap();
        map.put("firstName", firstName);
        map.put("lastName", lastName);
        map.put("nickname", nickname);
        map.put("avatarUrl", avatarUrl);
        return map;
    }

    @Override
    public Indexable fromIndex(Map map) {
        if (map == null) {
            return this;
        }
        this.firstName = (String) map.get("firstName");
        this.lastName = (String) map.get("lastName");
        this.nickname = (String) map.get("nickname");
        this.avatarUrl = (String) map.get("avatarUrl");
        return this;
    }
}

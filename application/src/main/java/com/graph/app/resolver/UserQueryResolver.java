package com.graph.app.resolver;

import com.graphql.generated.types.User;
import graphql.kickstart.tools.GraphQLQueryResolver;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class UserQueryResolver implements GraphQLQueryResolver {
    protected static final Map<String, User> userDB;

    static {
        userDB = new HashMap<>();
        for (int i = 1; i < 6; i++) {
            User user = createUser(i);
            userDB.put(user.getUserId(), user);
        }
    }

    private static User createUser(int id) {
        return User.newBuilder()
            .userId(String.valueOf(id))
            .age(id)
            .name("user" + id)
            .email("email" + id)
            .build();
    }

    public User user (String userId) {
        return userDB.get(userId);
    }
}

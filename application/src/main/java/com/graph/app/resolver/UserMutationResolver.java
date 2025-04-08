package com.graph.app.resolver;

import com.graphql.generated.types.UpdateEmailInput;
import com.graphql.generated.types.User;
import com.graphql.generated.types.CreateUserInput;
import graphql.kickstart.tools.GraphQLMutationResolver;
import org.springframework.stereotype.Component;

import static com.graph.app.resolver.UserQueryResolver.userDB;

@Component
public class UserMutationResolver implements GraphQLMutationResolver {
    public User createUser (CreateUserInput createUserInput) {
        User newUser = User.newBuilder()
            .userId(String.valueOf(userDB.size() + 1))
            .name(createUserInput.getName())
            .age(createUserInput.getAge())
            .email(createUserInput.getEmail())
            .nickname(createUserInput.getNickname())
            .build();
        userDB.put(newUser.getUserId(), newUser);
        return newUser;
    }

    public User updateEmail (UpdateEmailInput updateEmailInput) {
        User user = userDB.get(updateEmailInput.getUserId());
        user.setEmail(updateEmailInput.getEmail());
        userDB.put(updateEmailInput.getUserId(), user);
        return user;
    }
}

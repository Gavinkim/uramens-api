package com.uramens.user.infrastructure;

import com.uramens.user.domain.User;
import com.uramens.user.domain.UserRepository;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import org.apache.commons.lang3.StringUtils;

public class MemoryUserRepository implements UserRepository {

    private static ConcurrentHashMap<String, User> userMap = new ConcurrentHashMap<>();

    @Override
    public User save(User user) {
        userMap.put(user.getEmail(), user);
        return user;
    }

    @Override
    public User update(User user) {
        userMap.put(user.getEmail(), user);
        return user;
    }

    @Override
    public Optional<User> findById(Long userId) {
        return Optional.ofNullable(userMap.get(userId));
    }

    @Override
    public Optional<User> getByEmail(String email) {
        return userMap.values().stream().filter(user -> StringUtils.equals(user.getEmail(), email)).findAny();
    }

    @Override
    public boolean existsByEmail(String email) {
        return userMap.values().stream()
            .anyMatch(user -> StringUtils.equals(user.getEmail(), email));
    }

    @Override
    public void deleteAll() {
        userMap.clear();
    }
}

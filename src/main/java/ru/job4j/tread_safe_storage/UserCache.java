package ru.job4j.tread_safe_storage;

import java.util.HashMap;
import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

@ThreadSafe
public class UserCache {
    @GuardedBy("this")
    private final HashMap<Integer, User> users = new HashMap<>();

    public synchronized void add(User user) {
        users.putIfAbsent(user.getId(), user);
    }

    public synchronized boolean delete(User user) {
        return users.remove(user.getId(), user);
    }

    public synchronized boolean update(User user) {
        return users.replace(user.getId(), user.clone()) != null;
    }

    public synchronized boolean transfer(int fromId, int toId, int amount) {
        User from = users.get(fromId);
        User to = users.get(toId);
        if (to == null || from == null || from.getAmount() < amount) {
            return false;
        }
        from.setAmount(from.getAmount() - amount);
        to.setAmount(to.getAmount() + amount);
        return true;
    }
}

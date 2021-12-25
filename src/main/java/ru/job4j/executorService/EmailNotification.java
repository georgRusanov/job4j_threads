package ru.job4j.executorService;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class EmailNotification {

    static ExecutorService pool = Executors.newFixedThreadPool(
            Runtime.getRuntime().availableProcessors()
    );

    public static void emailTo(User user) {
        String subject = String.format("Notification %s to email %s", user.username, user.email);
        String body = String.format("Add a new event to %s", user.username);
        send(subject, body, user.email);
    }

    public static void close() {
        pool.shutdown();
    }

    public static void send(String subject, String body, String email) {}

    public static void main(String[] args) {
        pool.submit(() -> {
            User user = new User("user", "email");
            emailTo(user);
        });
    }
}

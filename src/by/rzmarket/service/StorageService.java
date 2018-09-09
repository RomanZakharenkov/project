package by.rzmarket.service;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class StorageService {

    private static final StorageService INSTANCE = new StorageService();

    public static StorageService getInstance() {
        return INSTANCE;
    }
}

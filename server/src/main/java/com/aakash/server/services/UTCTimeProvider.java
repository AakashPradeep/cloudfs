package com.aakash.server.services;

import java.time.Instant;

public class UTCTimeProvider {
    public long currentEpochTime() {
        return Instant.now().toEpochMilli();
    }
}

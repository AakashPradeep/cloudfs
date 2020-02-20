package com.aakash.server.impl;

/**
 * State of the metadata server.
 */
public enum State {
    READ_ONLY, STAND_BY, ALIVE, SHUTTING_DOWN;
}

package com.github.rosklyar.client.transaction.domain.importance;

public enum Action {

    ACTIVATE(1), DEACTIVATE(2);

    public final int mode;

    Action(int mode) {
        this.mode = mode;
    }
}

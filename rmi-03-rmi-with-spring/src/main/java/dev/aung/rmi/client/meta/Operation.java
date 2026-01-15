package dev.aung.rmi.client.meta;

public abstract class Operation {

    private final String title;

    public Operation(String title) {
        this.title = title;
    }

    public abstract void doWork();

    public String getTitle() {
        return title;
    }
}

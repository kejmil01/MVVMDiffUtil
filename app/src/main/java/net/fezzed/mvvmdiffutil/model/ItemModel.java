package net.fezzed.mvvmdiffutil.model;

public class ItemModel {
    private final String first;
    private final String second;

    public ItemModel(String first, String second) {
        this.first = first;
        this.second = second;
    }

    public String getFirst() {
        return first;
    }

    public String getSecond() {
        return second;
    }
}

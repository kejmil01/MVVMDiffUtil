package net.fezzed.mvvmdiffutil.model;

public class ItemModel {
    private int id;
    private final String first;
    private final String second;

    public ItemModel(
            int id,
            String first,
            String second) {
        this.id = id;
        this.first = first;
        this.second = second;
    }

    public String getFirst() {
        return first;
    }

    public String getSecond() {
        return second;
    }

    public int getId() {
        return id;
    }
}

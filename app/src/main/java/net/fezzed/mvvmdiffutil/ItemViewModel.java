package net.fezzed.mvvmdiffutil;


import net.fezzed.mvvmdiffutil.databinding.ObservableString;
import net.fezzed.mvvmdiffutil.model.ItemModel;

public class ItemViewModel {

    private ObservableString first = new ObservableString();
    private ObservableString second = new ObservableString();

    public void setModel(ItemModel model) {
        first.set(model.getFirst());
        second.set(model.getSecond());
    }

    public ObservableString getFirst() {
        return first;
    }

    public ObservableString getSecond() {
        return second;
    }
}

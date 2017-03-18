package net.fezzed.mvvmdiffutil;


import android.support.v7.util.DiffUtil;

import net.fezzed.mvvmdiffutil.model.ItemModel;
import net.fezzed.mvvmdiffutil.view.CompoundRecyclerView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainViewModel {
    private List<ItemModel> models = new ArrayList<>();

    private CompoundRecyclerView compoundRecyclerView;
    private int firsItemId = 0;

    public MainViewModel(CompoundRecyclerView compoundRecyclerView) {
        this.compoundRecyclerView = compoundRecyclerView;
    }

    public void onSwitchEventClick() {
        ArrayList<ItemModel> copy = new ArrayList<>(models);
        Collections.copy(copy, models);
        for (int i = 0; i < models.size(); i++) {
            if (i % 2 == 0) {
                ItemModel itemModel = copy.get(i);
                String newFirst = itemModel.getFirst() + " Even";
                String newSecond = itemModel.getSecond() + " Even";
                ItemModel newItemModel = new ItemModel(itemModel.getId(), newFirst, newSecond);
                copy.set(i, newItemModel);
            }
        }
        DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(new ItemListDiffCallback(models, copy));
        models = copy;
        compoundRecyclerView.updateList(models, diffResult);

    }

    public void onSwitchOddClick() {
        ArrayList<ItemModel> copy = new ArrayList<>(models);
        Collections.copy(copy, models);
        for (int i = 0; i < models.size(); i++) {
            if (i % 2 == 1) {
                ItemModel itemModel = copy.get(i);
                String newFirst = itemModel.getFirst() + " Odd";
                String newSecond = itemModel.getSecond() + " Odd";
                ItemModel newItemModel = new ItemModel(itemModel.getId(), newFirst, newSecond);
                copy.set(i, newItemModel);
            }
        }
        DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(new ItemListDiffCallback(models, copy));
        models = copy;
        compoundRecyclerView.updateList(models, diffResult);
    }

    public void onAddMoreItemsClick() {
        ItemModel itemModel = new ItemModel(firsItemId, "Item " + firsItemId + " top.", "Item " + (firsItemId + 1) + " bottom.");
        ItemModel itemModel1 = new ItemModel(firsItemId, "Item " + firsItemId + " top.", "Item " + (firsItemId + 1) + " bottom.");
        firsItemId += 2;

        ArrayList<ItemModel> copy = new ArrayList<>(models);
        Collections.copy(copy, models);
        copy.add(itemModel);
        copy.add(itemModel1);

        DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(new ItemListDiffCallback(models, copy));
        models = copy;
        compoundRecyclerView.updateList(models, diffResult);
    }

    public void onClearClick() {
        List<ItemModel> emptyList = new ArrayList<>();
        DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(new ItemListDiffCallback(models, emptyList));
        models = emptyList;
        compoundRecyclerView.updateList(models, diffResult);
    }

}

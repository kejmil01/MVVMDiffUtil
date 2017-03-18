package net.fezzed.mvvmdiffutil;


import android.support.v7.util.DiffUtil;

import net.fezzed.mvvmdiffutil.model.ItemModel;

import java.util.List;

public class ItemListDiffCallback extends DiffUtil.Callback {

    private List<ItemModel> oldList;
    private List<ItemModel> newList;

    public ItemListDiffCallback(List<ItemModel> oldList, List<ItemModel> newList) {
        this.oldList = oldList;
        this.newList = newList;
    }

    @Override
    public int getOldListSize() {
        return oldList != null ? oldList.size() : 0;
    }

    @Override
    public int getNewListSize() {
        return newList != null ? newList.size() : 0;
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        ItemModel oldItemModel = oldList.get(oldItemPosition);
        ItemModel newItemModel = newList.get(newItemPosition);
        return oldItemModel.getId() == newItemModel.getId();
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        ItemModel oldItemModel = oldList.get(oldItemPosition);
        ItemModel newItemModel = newList.get(newItemPosition);
        if (!oldItemModel.getFirst().equals(newItemModel.getFirst())) {
            return false;
        }
        return oldItemModel.getSecond().equals(newItemModel.getSecond());
    }

    //No need for using getChangePayload cause of binding ViewHolder which updates only changed parts of view

}

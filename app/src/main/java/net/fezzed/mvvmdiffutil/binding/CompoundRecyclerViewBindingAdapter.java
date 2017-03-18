package net.fezzed.mvvmdiffutil.binding;


import android.databinding.BindingAdapter;

import net.fezzed.mvvmdiffutil.model.ItemModel;
import net.fezzed.mvvmdiffutil.view.CompoundRecyclerView;

public class CompoundRecyclerViewBindingAdapter {
    @BindingAdapter("modelList")
    public static void bindList(CompoundRecyclerView compoundRecyclerView, ObservableListResult<ItemModel> resultModel) {
        compoundRecyclerView.updateList(resultModel.get());
    }
}

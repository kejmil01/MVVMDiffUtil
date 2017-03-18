package net.fezzed.mvvmdiffutil;


import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.fezzed.mvvmdiffutil.databinding.ItemLayoutBinding;
import net.fezzed.mvvmdiffutil.model.ItemModel;

import java.util.ArrayList;
import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemViewHolder> {

    private List<ItemModel> itemModelList;

    public ItemAdapter() {
        itemModelList = new ArrayList<>();
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        holder.setModel(itemModelList.get(position));
    }

    @Override
    public int getItemCount() {
        return itemModelList.size();
    }

    public void updateItems(List<ItemModel> itemModels) {
        itemModelList = itemModels;
    }

    class ItemViewHolder extends RecyclerView.ViewHolder {

        private ItemViewModel viewModel;

        ItemViewHolder(View itemView) {
            super(itemView);

            ItemLayoutBinding binding = DataBindingUtil.bind(itemView);
            viewModel = new ItemViewModel();
            binding.setViewModel(viewModel);
        }

        void setModel(ItemModel model) {
            viewModel.setModel(model);
        }

    }
}

package net.fezzed.mvvmdiffutil;


import android.support.v7.util.DiffUtil;

import net.fezzed.mvvmdiffutil.microservice.NewItemsMicroService;
import net.fezzed.mvvmdiffutil.microservice.SwitchEvenItemsMicroService;
import net.fezzed.mvvmdiffutil.microservice.SwitchOddItemsMicroService;
import net.fezzed.mvvmdiffutil.model.ItemModel;
import net.fezzed.mvvmdiffutil.model.ResultModel;
import net.fezzed.mvvmdiffutil.view.CompoundRecyclerView;

import java.util.ArrayList;
import java.util.List;

import rx.functions.Action1;

public class MainViewModel {
    private List<ItemModel> models = new ArrayList<>();

    private CompoundRecyclerView compoundRecyclerView;
    private int firsItemId = 0;

    public MainViewModel(CompoundRecyclerView compoundRecyclerView) {
        this.compoundRecyclerView = compoundRecyclerView;
    }

    public void onSwitchEventClick() {
        new SwitchEvenItemsMicroService()
                .buildObservable(models)
                .subscribe(new Action1<ResultModel<ItemModel>>() {
                    @Override
                    public void call(ResultModel<ItemModel> resultModel) {
                        models = resultModel.getModelList();
                        compoundRecyclerView.updateList(resultModel);
                    }
                });

    }

    public void onSwitchOddClick() {
        new SwitchOddItemsMicroService()
                .buildObservable(models)
                .subscribe(new Action1<ResultModel<ItemModel>>() {
                    @Override
                    public void call(ResultModel<ItemModel> resultModel) {
                        models = resultModel.getModelList();
                        compoundRecyclerView.updateList(resultModel);
                    }
                });
    }

    public void onAddMoreItemsClick() {
        new NewItemsMicroService()
                .buildObservable(firsItemId, models)
                .subscribe(new Action1<ResultModel<ItemModel>>() {
                    @Override
                    public void call(ResultModel<ItemModel> resultModel) {
                        firsItemId += 2;
                        models = resultModel.getModelList();
                        compoundRecyclerView.updateList(resultModel);
                    }
                });
    }

    public void onClearClick() {
        List<ItemModel> emptyList = new ArrayList<>();
        DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(new ItemListDiffCallback(models, emptyList));
        models = emptyList;
        compoundRecyclerView.updateList(new ResultModel<>(diffResult, models));
    }

}

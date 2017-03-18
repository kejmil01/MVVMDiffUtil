package net.fezzed.mvvmdiffutil;


import android.support.v7.util.DiffUtil;

import net.fezzed.mvvmdiffutil.binding.ObservableListResult;
import net.fezzed.mvvmdiffutil.microservice.NewItemsMicroService;
import net.fezzed.mvvmdiffutil.microservice.SwitchEvenItemsMicroService;
import net.fezzed.mvvmdiffutil.microservice.SwitchOddItemsMicroService;
import net.fezzed.mvvmdiffutil.model.ItemModel;
import net.fezzed.mvvmdiffutil.model.ResultModel;

import java.util.ArrayList;
import java.util.List;

import rx.functions.Action1;

public class MainViewModel {
    private final ObservableListResult<ItemModel> modelList = new ObservableListResult<>();
    private List<ItemModel> models = new ArrayList<>();
    private int firstItemId = 0;

    public ObservableListResult<ItemModel> getModelList() {
        return modelList;
    }

    public void onSwitchEvenClick() {
        new SwitchEvenItemsMicroService()
                .buildObservable(models)
                .subscribe(new Action1<ResultModel<ItemModel>>() {
                    @Override
                    public void call(ResultModel<ItemModel> resultModel) {
                        models = resultModel.getModelList();
                        modelList.set(resultModel);
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
                        modelList.set(resultModel);
                    }
                });
    }

    public void onAddMoreItemsClick() {
        new NewItemsMicroService()
                .buildObservable(firstItemId, models)
                .subscribe(new Action1<ResultModel<ItemModel>>() {
                    @Override
                    public void call(ResultModel<ItemModel> resultModel) {
                        firstItemId += 2;
                        models = resultModel.getModelList();
                        modelList.set(resultModel);
                    }
                });
    }

    public void onClearClick() {
        List<ItemModel> emptyList = new ArrayList<>();
        DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(new ItemListDiffCallback(models, emptyList));
        models = emptyList;
        modelList.set(new ResultModel<>(diffResult, models));
    }

}

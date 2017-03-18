package net.fezzed.mvvmdiffutil.microservice;


import android.support.v7.util.DiffUtil;

import net.fezzed.mvvmdiffutil.ItemListDiffCallback;
import net.fezzed.mvvmdiffutil.model.ItemModel;
import net.fezzed.mvvmdiffutil.model.ResultModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class NewItemsMicroService {
    public Observable<ResultModel<ItemModel>> buildObservable(final int firstItemId, List<ItemModel> oldItemList) {
        return Observable.just(oldItemList)
                .subscribeOn(Schedulers.io())
                .map(new Func1<List<ItemModel>, ResultModel<ItemModel>>() {
                    @Override
                    public ResultModel<ItemModel> call(List<ItemModel> itemModels) {
                        ItemModel itemModel = new ItemModel(firstItemId, "Item " + firstItemId + " top.", "Item " + (firstItemId + 1) + "" +
                                " bottom.");
                        ItemModel itemModel1 = new ItemModel(firstItemId, "Item " + firstItemId + " top.", "Item " + (firstItemId + 1) +
                                " bottom.");

                        ArrayList<ItemModel> copy = new ArrayList<>(itemModels);
                        Collections.copy(copy, itemModels);
                        copy.add(itemModel);
                        copy.add(itemModel1);

                        DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(new ItemListDiffCallback(itemModels, copy));
                        return new ResultModel<>(diffResult, copy);
                    }
                })
                .observeOn(AndroidSchedulers.mainThread());
    }
}

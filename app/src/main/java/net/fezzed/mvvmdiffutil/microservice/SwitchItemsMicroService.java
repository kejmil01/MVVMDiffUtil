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

public abstract class SwitchItemsMicroService {
    public Observable<ResultModel<ItemModel>> buildObservable(List<ItemModel> oldItemList) {
        return Observable.just(oldItemList)
                .subscribeOn(Schedulers.io())
                .map(new Func1<List<ItemModel>, ResultModel<ItemModel>>() {
                    @Override
                    public ResultModel<ItemModel> call(List<ItemModel> itemModels) {
                        ArrayList<ItemModel> copy = new ArrayList<>(itemModels);
                        Collections.copy(copy, itemModels);
                        for (int i = 0; i < itemModels.size(); i++) {
                            if (i % 2 == isEven()) {
                                ItemModel itemModel = copy.get(i);
                                String newFirst = itemModel.getFirst() + getText();
                                String newSecond = itemModel.getSecond() + getText();
                                ItemModel newItemModel = new ItemModel(itemModel.getId(), newFirst, newSecond);
                                copy.set(i, newItemModel);
                            }
                        }
                        DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(new ItemListDiffCallback(itemModels, copy));
                        return new ResultModel<>(diffResult, copy);
                    }
                })
                .observeOn(AndroidSchedulers.mainThread());
    }

    protected abstract String getText();

    /**
     * 0 - even
     * 1 - odd
     *
     * @return
     */
    protected abstract int isEven();
}

package net.fezzed.mvvmdiffutil.microservice;


import android.support.v7.util.DiffUtil;

import net.fezzed.mvvmdiffutil.ItemListDiffCallback;
import net.fezzed.mvvmdiffutil.model.ItemModel;
import net.fezzed.mvvmdiffutil.model.ResultModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class NewItemsMicroService {
    public Single<ResultModel<ItemModel>> buildObservable(final int firstItemId, List<ItemModel> oldItemList) {
        return Single.just(oldItemList)
                .subscribeOn(Schedulers.io())
                .map(new Function<List<ItemModel>, ResultModel<ItemModel>>() {
                    @Override
                    public ResultModel<ItemModel> apply(@NonNull List<ItemModel> itemModels) throws Exception {
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

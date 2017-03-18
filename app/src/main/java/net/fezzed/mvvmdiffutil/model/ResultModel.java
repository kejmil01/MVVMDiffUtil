package net.fezzed.mvvmdiffutil.model;


import android.support.v7.util.DiffUtil;

import java.util.List;

public class ResultModel<T> {
    private final DiffUtil.DiffResult diffResult;
    private final List<T> modelList;

    public ResultModel(DiffUtil.DiffResult diffResult, List<T> modelList) {
        this.diffResult = diffResult;
        this.modelList = modelList;
    }

    public DiffUtil.DiffResult getDiffResult() {
        return diffResult;
    }

    public List<T> getModelList() {
        return modelList;
    }
}

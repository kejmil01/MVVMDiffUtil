package net.fezzed.mvvmdiffutil.binding;


import android.databinding.BaseObservable;

import net.fezzed.mvvmdiffutil.model.ResultModel;

public class ObservableListResult<T> extends BaseObservable {
    private ResultModel<T> value;

    public ObservableListResult(ResultModel<T> value) {
        this.value = value;
    }

    public ObservableListResult() {
    }

    public ResultModel<T> get() {
        return value;
    }

    public void set(ResultModel<T> value) {
        if (!value.equals(this.value)) {
            this.value = value;
            notifyChange();
        }
    }
}

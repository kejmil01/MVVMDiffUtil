package net.fezzed.mvvmdiffutil.util;

import android.databinding.BindingAdapter;
import android.widget.TextView;

public class TextViewBindingAdapter {
    @BindingAdapter({"android:text"})
    public static void bindText(TextView view, ObservableString observableString) {
        String newValue = observableString.get();
        if (!view.getText().toString().equals(newValue)) {
            view.setText(newValue);
        }
    }
}

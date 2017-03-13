package net.fezzed.mvvmdiffutil.view;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;

import net.fezzed.mvvmdiffutil.ItemAdapter;
import net.fezzed.mvvmdiffutil.R;

public class CompoundRecyclerView extends FrameLayout {

    private ItemAdapter adapter;

    public CompoundRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    protected void init(Context context) {
        LayoutInflater
                .from(getContext())
                .inflate(R.layout.list_layout, this, true);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        adapter = new ItemAdapter();
        recyclerView.setAdapter(adapter);
    }
}

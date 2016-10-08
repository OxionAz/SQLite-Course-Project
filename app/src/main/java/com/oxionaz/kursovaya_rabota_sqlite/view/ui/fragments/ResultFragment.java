package com.oxionaz.kursovaya_rabota_sqlite.view.ui.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.Loader;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.oxionaz.kursovaya_rabota_sqlite.R;
import com.oxionaz.kursovaya_rabota_sqlite.model.DataManager;
import com.oxionaz.kursovaya_rabota_sqlite.util.AlertDialogs;
import com.oxionaz.kursovaya_rabota_sqlite.util.UpdateCallback;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import java.util.List;

@EFragment(R.layout.fragment_unuversal_tables)
public class ResultFragment extends Fragment {

    private List<String> data;

    @ViewById
    TextView label;

    @ViewById
    ListView list;

    @ViewById
    FloatingActionButton fab;

    public void setData(List<String> data){
        this.data = data;
    }

    @AfterViews
    void ready(){
        getActivity().setTitle("Результат");
        fab.setVisibility(View.GONE);
        label.setText("Найдено элементов: " + data.size());
        list.setAdapter(new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, data));
    }
}
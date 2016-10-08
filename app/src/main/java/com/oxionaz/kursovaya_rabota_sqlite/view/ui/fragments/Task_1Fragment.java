package com.oxionaz.kursovaya_rabota_sqlite.view.ui.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.Loader;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.oxionaz.kursovaya_rabota_sqlite.R;
import com.oxionaz.kursovaya_rabota_sqlite.model.DataManager;
import com.oxionaz.kursovaya_rabota_sqlite.util.AlertDialogs;
import com.oxionaz.kursovaya_rabota_sqlite.util.UpdateCallback;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;

import java.util.List;

@EFragment(R.layout.task_1)
public class Task_1Fragment extends Fragment {

    private static final String EMPTY = "Не выбрано";
    private List<String> list_1, list_2, list_3;

    @ViewById
    Spinner input_1, input_2, input_3;

    @AfterViews
    void ready(){
        getActivity().setTitle(R.string.task_1);
        getSpinnerData();
    }

    @Click
    void execute_button(){
        List<String> data = getData();
        if (data != null) {
            ResultFragment resultFragment = new ResultFragment_();
            resultFragment.setData(data);
            getActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.main_frame, resultFragment).addToBackStack("T")
                    .commit();
        }
    }

    @Background
    void getSpinnerData(){
        list_1 = DataManager.getListMD(getContext());
        list_2 = DataManager.getListArmy(getContext());
        list_2.add(0, EMPTY);
        list_3 = DataManager.getListComm(getContext());
        list_3.add(0, EMPTY);
        setSpinnerData();
    }

    @UiThread
    void setSpinnerData(){
        ArrayAdapter<String> list_1 = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, this.list_1);
        list_1.setDropDownViewResource(android.R.layout.simple_list_item_1);
        input_1.setAdapter(list_1);
        ArrayAdapter<String> list_2 = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, this.list_2);
        list_2.setDropDownViewResource(android.R.layout.simple_list_item_1);
        input_2.setAdapter(list_2);
        ArrayAdapter<String> list_3 = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, this.list_3);
        list_3.setDropDownViewResource(android.R.layout.simple_list_item_1);
        input_3.setAdapter(list_3);
    }

    private List<String> getData(){
        String  data_1, data_2, data_3;
        data_1 = input_1.getSelectedItem().toString();
        if (!input_2.getSelectedItem().toString().equalsIgnoreCase(EMPTY)) {
            String[] data = DataManager.getRowListArmy(getContext(), input_2.getSelectedItemPosition() - 1);
            data_2 = data[1];
        } else data_2 = "";
        if (!input_3.getSelectedItem().toString().equalsIgnoreCase(EMPTY))
            data_3 = DataManager.getRowListComm(getContext(), input_3.getSelectedItemPosition()-1);
        else data_3 = "";
        if (!data_1.isEmpty()) return DataManager.getDataT1(getContext(), data_1, data_2, data_3);
        return null;
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.e("onResume: ", "Hello from "+getClass().getSimpleName()+" fragment");
    }
}
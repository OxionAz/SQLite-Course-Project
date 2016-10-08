package com.oxionaz.kursovaya_rabota_sqlite.view.ui.fragments;

import android.support.v4.app.Fragment;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.oxionaz.kursovaya_rabota_sqlite.R;
import com.oxionaz.kursovaya_rabota_sqlite.model.DataManager;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.List;

@EFragment(R.layout.task_13)
public class Task_13Fragment extends Fragment {

    private static final String EMPTY = "Не выбрано";
    private List<String> list_1, list_2;

    @ViewById
    Spinner input_1, input_2;

    @AfterViews
    void ready(){
        getActivity().setTitle(R.string.task_13);
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
        list_1 = new ArrayList<>(3);
        list_1.add("Армия");
        list_1.add("Объединение");
        list_2 = new ArrayList<>(3);
        list_2.add(EMPTY);
        list_2.add("Больше всего военных частей");
        list_2.add("Меньше всего военных частей");
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
    }

    private List<String> getData(){
        String  data_1 = null, data_2 = null;
        if (!input_1.getSelectedItem().toString().equalsIgnoreCase(EMPTY)) {
            if (input_1.getSelectedItem().toString().equals("Армия"))
                data_1 = "Армия";
            if (input_1.getSelectedItem().toString().equals("Объединение"))
                data_1 = "Объединение";
        }
        else data_1 = "";
        if (!input_2.getSelectedItem().toString().equalsIgnoreCase(EMPTY)) {
            if (input_2.getSelectedItem().toString().equals("Больше всего военных частей"))
                data_2 = "DESC LIMIT 1";
            if (input_2.getSelectedItem().toString().equals("Меньше всего военных частей"))
                data_2 = "LIMIT 1";
        }
        else data_2 = "";
        if (!data_1.isEmpty()) return DataManager.getDataT13(getContext(), data_1, data_2);
        return null;
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.e("onResume: ", "Hello from "+getClass().getSimpleName()+" fragment");
    }
}
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

@EFragment(R.layout.task_8)
public class Task_8Fragment extends Fragment {

    private static final String EMPTY = "Не выбрано";
    private List<String> list_1;

    @ViewById
    Spinner input_1;

    @AfterViews
    void ready(){
        getActivity().setTitle(R.string.task_8);
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
        list_1 = DataManager.getListEquC(getContext());
        list_1.add(0, EMPTY);
        setSpinnerData();
    }

    @UiThread
    void setSpinnerData(){
        ArrayAdapter<String> list_1 = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, this.list_1);
        list_1.setDropDownViewResource(android.R.layout.simple_list_item_1);
        input_1.setAdapter(list_1);
    }

    private List<String> getData(){
        String  data_1;
        if (!input_1.getSelectedItem().toString().equalsIgnoreCase(EMPTY)) {
            data_1 = DataManager.getRowListEquC(getContext(), input_1.getSelectedItemPosition() - 1);
        } else data_1 = "";
        return DataManager.getDataT8(getContext(), data_1);
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.e("onResume: ", "Hello from "+getClass().getSimpleName()+" fragment");
    }
}
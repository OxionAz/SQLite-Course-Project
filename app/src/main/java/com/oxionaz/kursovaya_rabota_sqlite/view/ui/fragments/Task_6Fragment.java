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

@EFragment(R.layout.task_6)
public class Task_6Fragment extends Fragment {

    private static final String EMPTY = "Не выбрано";
    private List<String> list_1, list_2, list_3, list_4, list_5, list_6;

    @ViewById
    Spinner input_1, input_2, input_3, input_4, input_5, input_6;

    @AfterViews
    void ready(){
        getActivity().setTitle(R.string.task_6);
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
        list_4 = DataManager.getListMU(getContext());
        list_4.add(0, EMPTY);
        list_5 = DataManager.getListEquC(getContext());
        list_5.add(0, EMPTY);
        list_6 = new ArrayList<>(3);
        list_6.add(EMPTY);
        list_6.add("Транспортная техника");
        list_6.add("Военная техника");
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
        ArrayAdapter<String> list_4 = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, this.list_4);
        list_4.setDropDownViewResource(android.R.layout.simple_list_item_1);
        input_4.setAdapter(list_4);
        ArrayAdapter<String> list_5 = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, this.list_5);
        list_5.setDropDownViewResource(android.R.layout.simple_list_item_1);
        input_5.setAdapter(list_5);
        ArrayAdapter<String> list_6 = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, this.list_6);
        list_6.setDropDownViewResource(android.R.layout.simple_list_item_1);
        input_6.setAdapter(list_6);
    }

    private List<String> getData(){
        String  data_1, data_2, data_3, data_4, data_5, data_6;
        data_1 = input_1.getSelectedItem().toString();
        if (!input_2.getSelectedItem().toString().equalsIgnoreCase(EMPTY)) {
            String[] data = DataManager.getRowListArmy(getContext(), input_2.getSelectedItemPosition() - 1);
            data_2 = data[1];
        } else data_2 = "";
        if (!input_3.getSelectedItem().toString().equalsIgnoreCase(EMPTY))
            data_3 = DataManager.getRowListComm(getContext(), input_3.getSelectedItemPosition()-1);
        else data_3 = "";
        if (!input_4.getSelectedItem().toString().equalsIgnoreCase(EMPTY)) {
            String[] data = DataManager.getRowListMU(getContext(), input_4.getSelectedItemPosition() - 1);
            data_4 = data[2];
        } else data_4 = "";
        if (!input_5.getSelectedItem().toString().equalsIgnoreCase(EMPTY)) {
            data_5 = DataManager.getRowListEquC(getContext(), input_5.getSelectedItemPosition() - 1);
        } else data_5 = "";
        if (!input_6.getSelectedItem().toString().equalsIgnoreCase(EMPTY)) {
            data_6 = input_6.getSelectedItem().toString();
        } else data_6 = "";
        if (!data_1.isEmpty()) return DataManager.getDataT6(getContext(), data_1, data_2, data_3, data_4, data_5, data_6);
        return null;
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.e("onResume: ", "Hello from "+getClass().getSimpleName()+" fragment");
    }
}
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

@EFragment(R.layout.task_7)
public class Task_7Fragment extends Fragment {

    private static final String EMPTY = "Не выбрано";
    private List<String> list_1, list_2, list_3;

    @ViewById
    Spinner input_1, input_2, input_3;

    @AfterViews
    void ready(){
        getActivity().setTitle(R.string.task_7);
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
        list_2 = DataManager.getListMU(getContext());
        list_2.add(0, EMPTY);
        list_3 = new ArrayList<>(3);
        list_3.add(EMPTY);
        list_3.add("Более одного подразделения");
        list_3.add("Ни одного подразделения");
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
        String  data_1, data_2, data_3 = null;
        data_1 = input_1.getSelectedItem().toString();
        if (!input_2.getSelectedItem().toString().equalsIgnoreCase(EMPTY)) {
            String[] data = DataManager.getRowListMU(getContext(), input_2.getSelectedItemPosition() - 1);
            data_2 = data[2];
        } else data_2 = "";
        if (!input_3.getSelectedItem().toString().equalsIgnoreCase(EMPTY)) {
            if (input_3.getSelectedItem().toString().equals("Более одного подразделения"))
                data_3 = "HAVING COUNT(subdivision_dislocation._id_subdivision) > 1";
            if (input_3.getSelectedItem().toString().equals("Ни одного подразделения"))
                data_3 = "HAVING COUNT(subdivision_dislocation._id_subdivision) = 0";
        }
        else data_3 = "";
        if (!data_1.isEmpty()) return DataManager.getDataT7(getContext(), data_1, data_2, data_3);
        return null;
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.e("onResume: ", "Hello from "+getClass().getSimpleName()+" fragment");
    }
}
package com.oxionaz.kursovaya_rabota_sqlite.view.ui.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.ActionBar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import com.oxionaz.kursovaya_rabota_sqlite.R;
import com.oxionaz.kursovaya_rabota_sqlite.model.DataManager;
import com.oxionaz.kursovaya_rabota_sqlite.util.AlertDialogs;
import com.oxionaz.kursovaya_rabota_sqlite.util.UpdateCallback;
import com.oxionaz.kursovaya_rabota_sqlite.view.ui.activities.MainActivity_;
import com.oxionaz.kursovaya_rabota_sqlite.view.ui.adapters.ToolbarSpinnerAdapter;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.res.LayoutRes;

import java.util.ArrayList;
import java.util.List;

@EFragment(R.layout.fragment_unuversal_tables)
public class SpecialityAccountingFragment extends Fragment implements UpdateCallback, AdapterView.OnItemSelectedListener {

    private View spinnerContainer;
    private Spinner toolbar_spinner;
    private TextView title;
    private final ActionBar.LayoutParams lp = new ActionBar.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
    private String[] mlt = null;

    @ViewById
    TextView label;

    @ViewById
    ListView list;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        spinnerContainer = getLayoutInflater(savedInstanceState).inflate(R.layout.toolbar_spinner, MainActivity_.toolbar, false);
        toolbar_spinner = (Spinner) spinnerContainer.findViewById(R.id.toolbar_spinner);
        title = (TextView) spinnerContainer.findViewById(R.id.toolbar_spinner_title);
    }

    @AfterViews
    void ready(){
        setToolbarSpinner();
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String[] row_data = DataManager.getRowSpcA(getContext(), position, mlt[1]);
                AlertDialogs.alertDialogSpcA(getContext(), row_data, mlt);
            }
        });
    }

    private void setToolbarSpinner(){
        getActivity().setTitle(null);
        title.setText(R.string.speciality_accounting);
        List<String> military = DataManager.getListMilitary(getContext());
        if(military.isEmpty()) military.add("Нет военнообязанных");
        ToolbarSpinnerAdapter adapter = new ToolbarSpinnerAdapter(getContext(), military);
        toolbar_spinner.setAdapter(adapter);
        toolbar_spinner.setOnItemSelectedListener(this);
        if (!toolbar_spinner.getSelectedItem().toString().equalsIgnoreCase("Нет военнообязанных"))
            mlt = DataManager.getListMilitaryRow(getContext(), toolbar_spinner.getSelectedItemPosition());
    }

    @Click
    void fab(){
        if (!toolbar_spinner.getSelectedItem().toString().equalsIgnoreCase("Нет военнообязанных"))
            AlertDialogs.alertDialogSpcA(getContext(), null, mlt);
    }

    @Override
    public void onPause() {
        super.onPause();
        MainActivity_.toolbar.removeView(spinnerContainer);
        Log.e("onPause: ", "Hello from "+getClass().getSimpleName()+" fragment");
    }

    @Override
    public void onResume() {
        super.onResume();
        AlertDialogs.registerCallback(this);
        MainActivity_.toolbar.addView(spinnerContainer, lp);
        updateData(mlt[1]);
        Log.e("onResume: ", "Hello from "+getClass().getSimpleName()+" fragment");
    }

    @SuppressLint("SetTextI18n")
    void updateData(final String idM){
        getLoaderManager().restartLoader(0, null, new LoaderManager.LoaderCallbacks<List<String>>() {
            @Override
            public Loader<List<String>> onCreateLoader(int id, Bundle args) {
                final AsyncTaskLoader<List<String>> loader = new AsyncTaskLoader<List<String>>(getContext()) {
                    @Override
                    public List<String> loadInBackground() {
                        return DataManager.getDataSpcA(getContext(), idM);
                    }
                };
                loader.forceLoad();
                return loader;
            }

            @Override
            public void onLoadFinished(Loader<List<String>> loader, List<String> data) {
                label.setText("Найдено элементов: " + data.size());
                list.setAdapter(new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, data));
            }

            @Override
            public void onLoaderReset(Loader<List<String>> loader) {

            }
        });
    }

    @Override
    public void reload() {
        updateData(mlt[1]);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        mlt = DataManager.getListMilitaryRow(getContext(), position);
        updateData(mlt[1]);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
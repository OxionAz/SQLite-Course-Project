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
public class MilitaryDistrictFragment extends Fragment implements UpdateCallback {

    @ViewById
    TextView label;

    @ViewById
    ListView list;

    @AfterViews
    void ready(){
        getActivity().setTitle(R.string.military_district);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String[] row_data = DataManager.getRowMD(position);
                AlertDialogs.alertDialogMilitaryDistrict(getContext(), row_data);
            }
        });
    }

    @Click
    void fab(){
        AlertDialogs.alertDialogMilitaryDistrict(getContext(), null);
    }

    @Override
    public void onResume() {
        super.onResume();
        AlertDialogs.registerCallback(this);
        updateData();
        Log.e("onResume: ", "Hello from "+getClass().getSimpleName()+" fragment");
    }

    @SuppressLint("SetTextI18n")
    void updateData(){
        getLoaderManager().restartLoader(0, null, new LoaderManager.LoaderCallbacks<List<String>>() {
            @Override
            public Loader<List<String>> onCreateLoader(int id, Bundle args) {
                final AsyncTaskLoader<List<String>> loader = new AsyncTaskLoader<List<String>>(getContext()) {
                    @Override
                    public List<String> loadInBackground() {
                        return DataManager.getDataMD(getContext());
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
        updateData();
    }
}

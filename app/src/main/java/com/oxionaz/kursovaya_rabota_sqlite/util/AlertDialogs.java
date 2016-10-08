package com.oxionaz.kursovaya_rabota_sqlite.util;

import android.app.Dialog;
import android.content.ContentValues;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.oxionaz.kursovaya_rabota_sqlite.R;
import com.oxionaz.kursovaya_rabota_sqlite.model.DataManager;
import com.oxionaz.kursovaya_rabota_sqlite.model.db.DatabaseHelper;

import java.util.ArrayList;
import java.util.List;

public class AlertDialogs {

    private static UpdateCallback updateCallback;

    public static void registerCallback(UpdateCallback uc){
        updateCallback = uc;
    }

    public static void alertDialogMilitaryDistrict(final Context context, final String[] row_data){
        final Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.input_alert_md);
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        dialog.getWindow().setLayout(1000, 1200);
        dialog.show();

        final TextView title = (TextView) dialog.findViewById(R.id.dialog_title);
        final EditText input_1 = (EditText) dialog.findViewById(R.id.input_1);
        final EditText input_2 = (EditText) dialog.findViewById(R.id.input_2);
        final EditText input_3 = (EditText) dialog.findViewById(R.id.input_3);
        final Button cancelButton = (Button) dialog.findViewById(R.id.dialog_cancelButton);
        final Button okButton = (Button) dialog.findViewById(R.id.dialog_okButton);
        final Button changeButton = (Button) dialog.findViewById(R.id.dialog_changeButton);
        final Button deleteButton = (Button) dialog.findViewById(R.id.dialog_deleteButton);

        if (row_data != null){
            title.setText(R.string.change_row);
            okButton.setVisibility(View.GONE);
            changeButton.setVisibility(View.VISIBLE);
            deleteButton.setVisibility(View.VISIBLE);
            input_1.setText(row_data[0]);
            input_2.setText(row_data[1]);
            input_3.setText(row_data[2]);
        }
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (input_1.getText().toString().isEmpty()){
                    input_1.setError("Заполните поле");
                } else
                if (input_2.getText().toString().isEmpty()){
                    input_2.setError("Заполните поле");
                } else
                if (input_3.getText().toString().isEmpty()){
                    input_3.setError("Заполните поле");
                } else {
                    ContentValues cv = new ContentValues();
                    cv.put(DatabaseHelper.COLUMN_MILITARY_DISTRICT_ID, input_1.getText().toString());
                    cv.put(DatabaseHelper.COLUMN_NAME, input_2.getText().toString());
                    cv.put(DatabaseHelper.COLUMN_COUNTRY, input_3.getText().toString());
                    DataManager.addDataMD(context, cv);
                    updateCallback.reload();
                    dialog.dismiss();
                }
            }
        });
        changeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (input_1.getText().toString().isEmpty()){
                    input_1.setError("Заполните поле");
                } else
                if (input_2.getText().toString().isEmpty()){
                    input_2.setError("Заполните поле");
                } else
                if (input_3.getText().toString().isEmpty()){
                    input_3.setError("Заполните поле");
                } else {
                    ContentValues cv = new ContentValues();
                    cv.put(DatabaseHelper.COLUMN_MILITARY_DISTRICT_ID, input_1.getText().toString());
                    cv.put(DatabaseHelper.COLUMN_NAME, input_2.getText().toString());
                    cv.put(DatabaseHelper.COLUMN_COUNTRY, input_3.getText().toString());
                    DataManager.changeDataMD(context, cv, row_data[0]);
                    updateCallback.reload();
                    dialog.dismiss();
                }
            }
        });
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DataManager.deleteDataMD(context, row_data[0]);
                updateCallback.reload();
                dialog.dismiss();
            }
        });
    }

    public static void alertDialogDislocationPlace(final Context context, final String[] row_data){
        final Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.input_alert_dp);
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        dialog.getWindow().setLayout(1000, 1200);
        dialog.show();

        final TextView title = (TextView) dialog.findViewById(R.id.dialog_title);
        final EditText input_1 = (EditText) dialog.findViewById(R.id.input_1);
        final Spinner input_2 = (Spinner) dialog.findViewById(R.id.input_2);
        final EditText input_3 = (EditText) dialog.findViewById(R.id.input_3);
        final Button cancelButton = (Button) dialog.findViewById(R.id.dialog_cancelButton);
        final Button okButton = (Button) dialog.findViewById(R.id.dialog_okButton);
        final Button changeButton = (Button) dialog.findViewById(R.id.dialog_changeButton);
        final Button deleteButton = (Button) dialog.findViewById(R.id.dialog_deleteButton);

        List<String> data = DataManager.getListMD(context);
        data.add("ID Округа");
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_dropdown_item, data) {

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {

                View v = super.getView(position, convertView, parent);
                if (position == getCount()) {
                    ((TextView)v.findViewById(android.R.id.text1)).setText("");
                    ((TextView)v.findViewById(android.R.id.text1)).setHint(getItem(getCount())); //"Hint to be displayed"
                }

                return v;
            }

            @Override
            public int getCount() {
                return super.getCount()-1; // you dont display last item. It is used as hint.
            }

        };
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        input_2.setAdapter(adapter);
        input_2.setSelection(adapter.getCount()); //display hint

        if (row_data != null){
            title.setText(R.string.change_row);
            okButton.setVisibility(View.GONE);
            changeButton.setVisibility(View.VISIBLE);
            deleteButton.setVisibility(View.VISIBLE);
            input_1.setText(row_data[0]);
            input_2.setSelection(data.indexOf(row_data[1]));
            input_3.setText(row_data[2]);
        }
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (input_1.getText().toString().isEmpty()){
                    input_1.setError("Заполните поле");
                } else
                if (input_2.getSelectedItem().toString().equalsIgnoreCase("ID Округа")){
                    Toast.makeText(context, "Вы не выбрали округ!", Toast.LENGTH_SHORT).show();
                } else
                if (input_3.getText().toString().isEmpty()){
                    input_3.setError("Заполните поле");
                } else {
                    ContentValues cv = new ContentValues();
                    cv.put(DatabaseHelper.COLUMN_DISLOCATION_PLACE_ID, input_1.getText().toString());
                    cv.put(DatabaseHelper.COLUMN_MILITARY_DISTRICT_ID, input_2.getSelectedItem().toString());
                    cv.put(DatabaseHelper.COLUMN_NAME, input_3.getText().toString());
                    DataManager.addDataDP(context, cv);
                    updateCallback.reload();
                    dialog.dismiss();
                }
            }
        });
        changeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (input_1.getText().toString().isEmpty()){
                    input_1.setError("Заполните поле");
                } else
                if (input_2.getSelectedItem().toString().isEmpty()){
                    Toast.makeText(context, "Вы не выбрали округ!", Toast.LENGTH_SHORT).show();
                } else
                if (input_3.getText().toString().isEmpty()){
                    input_3.setError("Заполните поле");
                } else {
                    ContentValues cv = new ContentValues();
                    cv.put(DatabaseHelper.COLUMN_DISLOCATION_PLACE_ID, input_1.getText().toString());
                    cv.put(DatabaseHelper.COLUMN_MILITARY_DISTRICT_ID, input_2.getSelectedItem().toString());
                    cv.put(DatabaseHelper.COLUMN_NAME, input_3.getText().toString());
                    DataManager.changeDataDP(context, cv, row_data[0]);
                    updateCallback.reload();
                    dialog.dismiss();
                }
            }
        });
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DataManager.deleteDataDP(context, row_data[0]);
                updateCallback.reload();
                dialog.dismiss();
            }
        });
    }

    public static void alertDialogRank(final Context context, final String[] row_data){
        final Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.input_alert_rank);
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        dialog.getWindow().setLayout(1000, 1200);
        dialog.show();

        final TextView title = (TextView) dialog.findViewById(R.id.dialog_title);
        final Spinner input_1 = (Spinner) dialog.findViewById(R.id.input_1);
        final EditText input_2 = (EditText) dialog.findViewById(R.id.input_2);
        final EditText input_3 = (EditText) dialog.findViewById(R.id.input_3);
        final Button cancelButton = (Button) dialog.findViewById(R.id.dialog_cancelButton);
        final Button okButton = (Button) dialog.findViewById(R.id.dialog_okButton);
        final Button changeButton = (Button) dialog.findViewById(R.id.dialog_changeButton);
        final Button deleteButton = (Button) dialog.findViewById(R.id.dialog_deleteButton);

        final List<String> type = new ArrayList<>(4);
        type.add("Офицерский состав");
        type.add("Сержантский состав");
        type.add("Рядовой состав");
        type.add("Выберите тип состава");
        ArrayAdapter<String> adapterType = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_dropdown_item, type) {

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {

                View v = super.getView(position, convertView, parent);
                if (position == getCount()) {
                    ((TextView)v.findViewById(android.R.id.text1)).setText(null);
                    ((TextView)v.findViewById(android.R.id.text1)).setHint(getItem(getCount())); //"Hint to be displayed"
                }

                return v;
            }

            @Override
            public int getCount() {
                return super.getCount()-1; // you don`t display last item. It is used as hint.
            }

        };
        adapterType.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        input_1.setAdapter(adapterType);
        input_1.setSelection(adapterType.getCount()); //display hint

        if (row_data != null){
            title.setText(R.string.change_row);
            okButton.setVisibility(View.GONE);
            changeButton.setVisibility(View.VISIBLE);
            deleteButton.setVisibility(View.VISIBLE);
            input_1.setSelection(type.indexOf(row_data[0]));
            input_2.setText(row_data[1]);
            input_3.setText(row_data[2]);
        }
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (input_1.getSelectedItem().toString().equalsIgnoreCase("Выберите тип состава")){
                    Toast.makeText(context, "Вы не выбрали тип состава!", Toast.LENGTH_SHORT).show();
                } else
                if (input_2.getText().toString().isEmpty()){
                    input_2.setError("Заполните поле");
                } else
                if (input_3.getText().toString().isEmpty()){
                    input_3.setError("Заполните поле");
                } else {
                    ContentValues cv = new ContentValues();
                    cv.put(DatabaseHelper.COLUMN_TYPE, input_1.getSelectedItem().toString());
                    cv.put(DatabaseHelper.COLUMN_RANK_ID, input_2.getText().toString());
                    cv.put(DatabaseHelper.COLUMN_NAME, input_3.getText().toString());
                    DataManager.addDataRank(context, cv);
                    updateCallback.reload();
                    dialog.dismiss();
                }
            }
        });
        changeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (input_1.getSelectedItem().toString().equalsIgnoreCase("Выберите тип состава")){
                    Toast.makeText(context, "Вы не выбрали тип состава!", Toast.LENGTH_SHORT).show();
                } else
                if (input_2.getText().toString().isEmpty()){
                    input_2.setError("Заполните поле");
                } else
                if (input_3.getText().toString().isEmpty()){
                    input_3.setError("Заполните поле");
                } else {
                    ContentValues cv = new ContentValues();
                    cv.put(DatabaseHelper.COLUMN_TYPE, input_1.getSelectedItem().toString());
                    cv.put(DatabaseHelper.COLUMN_RANK_ID, input_2.getText().toString());
                    cv.put(DatabaseHelper.COLUMN_NAME, input_3.getText().toString());
                    DataManager.changeDataRank(context, cv, row_data[1]);
                    updateCallback.reload();
                    dialog.dismiss();
                }
            }
        });
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DataManager.deleteDataRank(context, row_data[1]);
                updateCallback.reload();
                dialog.dismiss();
            }
        });
    }

    public static void alertDialogMilitary(final Context context, final String[] row_data){
        final Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.input_alert_mlt);
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        dialog.getWindow().setLayout(1000, 1300);
        dialog.show();

        final TextView title = (TextView) dialog.findViewById(R.id.dialog_title);
        final Spinner input_1 = (Spinner) dialog.findViewById(R.id.input_1);
        final Spinner input_2 = (Spinner) dialog.findViewById(R.id.input_2);
        final EditText input_3 = (EditText) dialog.findViewById(R.id.input_3);
        final EditText input_4 = (EditText) dialog.findViewById(R.id.input_4);
        final EditText input_5 = (EditText) dialog.findViewById(R.id.input_5);
        final Button cancelButton = (Button) dialog.findViewById(R.id.dialog_cancelButton);
        final Button okButton = (Button) dialog.findViewById(R.id.dialog_okButton);
        final Button changeButton = (Button) dialog.findViewById(R.id.dialog_changeButton);
        final Button deleteButton = (Button) dialog.findViewById(R.id.dialog_deleteButton);

        List<String> dataMD = DataManager.getListMD(context);
        dataMD.add("ID Округа");
        ArrayAdapter<String> adapterMD = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_dropdown_item, dataMD) {

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {

                View v = super.getView(position, convertView, parent);
                if (position == getCount()) {
                    ((TextView)v.findViewById(android.R.id.text1)).setText("");
                    ((TextView)v.findViewById(android.R.id.text1)).setHint(getItem(getCount())); //"Hint to be displayed"
                }

                return v;
            }

            @Override
            public int getCount() {
                return super.getCount()-1; // you dont display last item. It is used as hint.
            }

        };
        adapterMD.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        input_1.setAdapter(adapterMD);
        input_1.setSelection(adapterMD.getCount()); //display hint

        List<String> dataRank = DataManager.getListRank(context);
        dataRank.add("ID Звания");
        ArrayAdapter<String> adapterRank = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_dropdown_item, dataRank) {

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {

                View v = super.getView(position, convertView, parent);
                if (position == getCount()) {
                    ((TextView)v.findViewById(android.R.id.text1)).setText("");
                    ((TextView)v.findViewById(android.R.id.text1)).setHint(getItem(getCount())); //"Hint to be displayed"
                }

                return v;
            }

            @Override
            public int getCount() {
                return super.getCount()-1; // you dont display last item. It is used as hint.
            }

        };
        adapterRank.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        input_2.setAdapter(adapterRank);
        input_2.setSelection(adapterRank.getCount()); //display hint

        if (row_data != null){
            title.setText(R.string.change_row);
            okButton.setVisibility(View.GONE);
            changeButton.setVisibility(View.VISIBLE);
            deleteButton.setVisibility(View.VISIBLE);
            input_1.setSelection(dataMD.indexOf(row_data[0]));
            input_2.setSelection(dataRank.indexOf(row_data[1]));
            input_3.setText(row_data[2]);
            input_4.setText(row_data[3]);
            input_5.setText(row_data[4]);
        }
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (input_1.getSelectedItem().toString().equalsIgnoreCase("ID Округа")){
                    Toast.makeText(context, "Вы не выбрали округ!", Toast.LENGTH_SHORT).show();
                } else
                if (input_2.getSelectedItem().toString().equalsIgnoreCase("ID Звания")){
                    Toast.makeText(context, "Вы не присвоили звание!", Toast.LENGTH_SHORT).show();
                } else
                if (input_3.getText().toString().isEmpty()){
                    input_3.setError("Заполните поле");
                } else
                if (input_4.getText().toString().isEmpty()){
                    input_4.setError("Заполните поле");
                } else
                if (input_5.getText().toString().isEmpty()){
                    input_5.setError("Заполните поле");
                } else {
                    ContentValues cv = new ContentValues();
                    cv.put(DatabaseHelper.COLUMN_MILITARY_DISTRICT_ID, input_1.getSelectedItem().toString());
                    cv.put(DatabaseHelper.COLUMN_RANK_ID, input_2.getSelectedItem().toString());
                    cv.put(DatabaseHelper.COLUMN_MILITARY_ID, input_3.getText().toString());
                    cv.put(DatabaseHelper.COLUMN_MILITARY_FIO, input_4.getText().toString());
                    cv.put(DatabaseHelper.COLUMN_ATT, input_5.getText().toString());
                    DataManager.addDataMilitary(context, cv);
                    updateCallback.reload();
                    dialog.dismiss();
                }
            }
        });
        changeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (input_1.getSelectedItem().toString().equalsIgnoreCase("ID Округа")){
                    Toast.makeText(context, "Вы не выбрали округ!", Toast.LENGTH_SHORT).show();
                } else
                if (input_2.getSelectedItem().toString().equalsIgnoreCase("ID Звания")){
                    Toast.makeText(context, "Вы не присвоили звание!", Toast.LENGTH_SHORT).show();
                } else
                if (input_3.getText().toString().isEmpty()){
                    input_3.setError("Заполните поле");
                } else
                if (input_4.getText().toString().isEmpty()){
                    input_4.setError("Заполните поле");
                } else
                if (input_5.getText().toString().isEmpty()){
                    input_5.setError("Заполните поле");
                } else {
                    ContentValues cv = new ContentValues();
                    cv.put(DatabaseHelper.COLUMN_MILITARY_DISTRICT_ID, input_1.getSelectedItem().toString());
                    cv.put(DatabaseHelper.COLUMN_RANK_ID, input_2.getSelectedItem().toString());
                    cv.put(DatabaseHelper.COLUMN_MILITARY_ID, input_3.getText().toString());
                    cv.put(DatabaseHelper.COLUMN_MILITARY_FIO, input_4.getText().toString());
                    cv.put(DatabaseHelper.COLUMN_ATT, input_5.getText().toString());
                    DataManager.changeDataMilitary(context, cv, row_data[2]);
                    updateCallback.reload();
                    dialog.dismiss();
                }
            }
        });
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DataManager.deleteDataMilitary(context, row_data[2]);
                updateCallback.reload();
                dialog.dismiss();
            }
        });
    }

    public static void alertDialogArmy(final Context context, final String[] row_data){
        final Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.input_alert_army);
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        dialog.getWindow().setLayout(1000, 1200);
        dialog.show();

        final TextView title = (TextView) dialog.findViewById(R.id.dialog_title);
        final Spinner input_1 = (Spinner) dialog.findViewById(R.id.input_1);
        final Spinner input_2 = (Spinner) dialog.findViewById(R.id.input_2);
        final EditText input_3 = (EditText) dialog.findViewById(R.id.input_3);
        final EditText input_4 = (EditText) dialog.findViewById(R.id.input_4);
        final Button cancelButton = (Button) dialog.findViewById(R.id.dialog_cancelButton);
        final Button okButton = (Button) dialog.findViewById(R.id.dialog_okButton);
        final Button changeButton = (Button) dialog.findViewById(R.id.dialog_changeButton);
        final Button deleteButton = (Button) dialog.findViewById(R.id.dialog_deleteButton);

        List<String> dataMD = DataManager.getListMD(context);
        dataMD.add("ID Округа");
        ArrayAdapter<String> adapterMD = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_dropdown_item, dataMD) {

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {

                View v = super.getView(position, convertView, parent);
                if (position == getCount()) {
                    ((TextView)v.findViewById(android.R.id.text1)).setText("");
                    ((TextView)v.findViewById(android.R.id.text1)).setHint(getItem(getCount())); //"Hint to be displayed"
                }
                return v;
            }

            @Override
            public int getCount() {
                return super.getCount()-1; // you dont display last item. It is used as hint.
            }

        };
        adapterMD.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        input_1.setAdapter(adapterMD);
        input_1.setSelection(adapterMD.getCount()); //display hint

        List<String> dataCommander = DataManager.getListFreeMilitaryOF(context);
        dataCommander.add(0, "Нет командующего");
        dataCommander.add("Командующий (либо тот же)");
        ArrayAdapter<String> adapterCommands = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_dropdown_item, dataCommander) {

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {

                View v = super.getView(position, convertView, parent);
                if (position == getCount()) {
                    ((TextView)v.findViewById(android.R.id.text1)).setText("");
                    ((TextView)v.findViewById(android.R.id.text1)).setHint(getItem(getCount())); //"Hint to be displayed"
                }

                return v;
            }

            @Override
            public int getCount() {
                return super.getCount()-1; // you dont display last item. It is used as hint.
            }

        };
        adapterCommands.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        input_2.setAdapter(adapterCommands);
        input_2.setSelection(adapterCommands.getCount()); //display hint

        if (row_data != null){
            title.setText(R.string.change_row);
            okButton.setVisibility(View.GONE);
            changeButton.setVisibility(View.VISIBLE);
            deleteButton.setVisibility(View.VISIBLE);
            input_1.setSelection(dataMD.indexOf(row_data[0]));
            input_2.setSelection(adapterCommands.getCount());
            input_3.setText(row_data[1]);
            input_4.setText(row_data[2]);
        }
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (input_1.getSelectedItem().toString().equalsIgnoreCase("ID Округа")){
                    Toast.makeText(context, "Вы не выбрали округ!", Toast.LENGTH_SHORT).show();
                } else
                if (input_3.getText().toString().isEmpty()){
                    input_3.setError("Заполните поле");
                } else
                if (input_4.getText().toString().isEmpty()){
                    input_4.setError("Заполните поле");
                } else {
                    ContentValues cv = new ContentValues();
                    cv.put(DatabaseHelper.COLUMN_MILITARY_DISTRICT_ID, input_1.getSelectedItem().toString());
                    if (!input_2.getSelectedItem().toString().equalsIgnoreCase("Командующий (либо тот же)")
                            && !input_2.getSelectedItem().toString().equalsIgnoreCase("Нет командующего")) {
                        String[] commander = DataManager.getFreeMilitaryRowOF(context, input_2.getSelectedItemPosition()-1);
                        cv.put(DatabaseHelper.COLUMN_MILITARY_ID, commander[0]);
                        cv.put(DatabaseHelper.COLUMN_MILITARY_FIO, commander[1]);
                    }
                    cv.put(DatabaseHelper.COLUMN_ARMY_ID, input_3.getText().toString());
                    cv.put(DatabaseHelper.COLUMN_NAME, input_4.getText().toString());
                    DataManager.addDataArmy(context, cv);
                    updateCallback.reload();
                    dialog.dismiss();
                }
            }
        });
        changeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (input_1.getSelectedItem().toString().equalsIgnoreCase("ID Округа")){
                    Toast.makeText(context, "Вы не выбрали округ!", Toast.LENGTH_SHORT).show();
                } else
                if (input_3.getText().toString().isEmpty()){
                    input_3.setError("Заполните поле");
                } else
                if (input_4.getText().toString().isEmpty()){
                    input_4.setError("Заполните поле");
                } else {
                    ContentValues cv = new ContentValues();
                    cv.put(DatabaseHelper.COLUMN_MILITARY_DISTRICT_ID, input_1.getSelectedItem().toString());
                    if (!input_2.getSelectedItem().toString().equalsIgnoreCase("Командующий (либо тот же)")
                            && !input_2.getSelectedItem().toString().equalsIgnoreCase("Нет командующего")) {
                        String[] commander = DataManager.getFreeMilitaryRowOF(context, input_2.getSelectedItemPosition()-1);
                        cv.put(DatabaseHelper.COLUMN_MILITARY_ID, commander[0]);
                        cv.put(DatabaseHelper.COLUMN_MILITARY_FIO, commander[1]);
                    } else if (input_2.getSelectedItem().toString().equalsIgnoreCase("Нет командующего")){
                        cv.putNull(DatabaseHelper.COLUMN_MILITARY_ID);
                        cv.putNull(DatabaseHelper.COLUMN_MILITARY_FIO);
                    }
                    cv.put(DatabaseHelper.COLUMN_ARMY_ID, input_3.getText().toString());
                    cv.put(DatabaseHelper.COLUMN_NAME, input_4.getText().toString());
                    DataManager.changeDataArmy(context, cv, row_data[1]);
                    updateCallback.reload();
                    dialog.dismiss();
                }
            }
        });
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DataManager.deleteDataArmy(context, row_data[1]);
                updateCallback.reload();
                dialog.dismiss();
            }
        });
    }

    public static void alertDialogCommunity(final Context context, final String[] row_data){
        final Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.input_alert_comm);
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        dialog.getWindow().setLayout(1000, 1300);
        dialog.show();

        final TextView title = (TextView) dialog.findViewById(R.id.dialog_title);
        final Spinner input_1 = (Spinner) dialog.findViewById(R.id.input_1);
        final Spinner input_2 = (Spinner) dialog.findViewById(R.id.input_2);
        final Spinner input_3 = (Spinner) dialog.findViewById(R.id.input_3);
        final EditText input_4 = (EditText) dialog.findViewById(R.id.input_4);
        final EditText input_5 = (EditText) dialog.findViewById(R.id.input_5);
        final Button cancelButton = (Button) dialog.findViewById(R.id.dialog_cancelButton);
        final Button okButton = (Button) dialog.findViewById(R.id.dialog_okButton);
        final Button changeButton = (Button) dialog.findViewById(R.id.dialog_changeButton);
        final Button deleteButton = (Button) dialog.findViewById(R.id.dialog_deleteButton);

        List<String> dataArmy = DataManager.getListArmy(context);
        dataArmy.add(0, "Не назначена");
        dataArmy.add("Армия");
        ArrayAdapter<String> adapterArmy = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_dropdown_item, dataArmy) {

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {

                View v = super.getView(position, convertView, parent);
                if (position == getCount()) {
                    ((TextView)v.findViewById(android.R.id.text1)).setText("");
                    ((TextView)v.findViewById(android.R.id.text1)).setHint(getItem(getCount())); //"Hint to be displayed"
                }
                return v;
            }

            @Override
            public int getCount() {
                return super.getCount()-1; // you dont display last item. It is used as hint.
            }

        };
        adapterArmy.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        input_1.setAdapter(adapterArmy);
        input_1.setSelection(adapterArmy.getCount()); //display hint

        List<String> dataCommander = DataManager.getListFreeMilitaryOF(context);
        dataCommander.add(0, "Нет командующего");
        dataCommander.add("Командующий (либо тот же)");
        ArrayAdapter<String> adapterCommands = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_dropdown_item, dataCommander) {

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {

                View v = super.getView(position, convertView, parent);
                if (position == getCount()) {
                    ((TextView)v.findViewById(android.R.id.text1)).setText("");
                    ((TextView)v.findViewById(android.R.id.text1)).setHint(getItem(getCount())); //"Hint to be displayed"
                }

                return v;
            }

            @Override
            public int getCount() {
                return super.getCount()-1; // you dont display last item. It is used as hint.
            }

        };
        adapterCommands.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        input_2.setAdapter(adapterCommands);
        input_2.setSelection(adapterCommands.getCount()); //display hint

        final List<String> type = new ArrayList<>(4);
        type.add("Дивизия");
        type.add("Корпус");
        type.add("Бригада");
        type.add("Выберите тип объединения");
        ArrayAdapter<String> adapterType = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_dropdown_item, type) {

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {

                View v = super.getView(position, convertView, parent);
                if (position == getCount()) {
                    ((TextView)v.findViewById(android.R.id.text1)).setText(null);
                    ((TextView)v.findViewById(android.R.id.text1)).setHint(getItem(getCount())); //"Hint to be displayed"
                }

                return v;
            }

            @Override
            public int getCount() {
                return super.getCount()-1; // you don`t display last item. It is used as hint.
            }

        };
        adapterType.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        input_3.setAdapter(adapterType);
        input_3.setSelection(adapterType.getCount()); //display hint

        if (row_data != null){
            title.setText(R.string.change_row);
            okButton.setVisibility(View.GONE);
            changeButton.setVisibility(View.VISIBLE);
            deleteButton.setVisibility(View.VISIBLE);
            if (row_data[1] != null) input_1.setSelection(dataArmy.indexOf(row_data[0]+" - "+row_data[1]+" - "+row_data[2]));
            else input_1.setSelection(adapterArmy.getCount());
            input_2.setSelection(adapterCommands.getCount());
            input_3.setSelection(type.indexOf(row_data[3]));
            input_4.setText(row_data[4]);
            input_5.setText(row_data[5]);
        }
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (input_3.getSelectedItem().toString().equalsIgnoreCase("Выберите тип объединения")){
                    Toast.makeText(context, "Вы не выбрали тип объединения!", Toast.LENGTH_SHORT).show();
                } else
                if (input_4.getText().toString().isEmpty()){
                    input_4.setError("Заполните поле");
                } else
                if (input_5.getText().toString().isEmpty()){
                    input_5.setError("Заполните поле");
                } else {
                    ContentValues cv = new ContentValues();
                    if (!input_1.getSelectedItem().toString().equalsIgnoreCase("Армия")
                            && !input_1.getSelectedItem().toString().equalsIgnoreCase("Не назначена")) {
                        String[] army = DataManager.getRowListArmy(context, input_1.getSelectedItemPosition()-1);
                        cv.put(DatabaseHelper.COLUMN_MILITARY_DISTRICT_ID, army[0]);
                        cv.put(DatabaseHelper.COLUMN_ARMY_ID, army[1]);
                    }
                    if (!input_2.getSelectedItem().toString().equalsIgnoreCase("Командующий (либо тот же)")
                            && !input_2.getSelectedItem().toString().equalsIgnoreCase("Нет командующего")) {
                        String[] commander = DataManager.getFreeMilitaryRowOF(context, input_2.getSelectedItemPosition()-1);
                        cv.put(DatabaseHelper.COLUMN_MILITARY_ID, commander[0]);
                        cv.put(DatabaseHelper.COLUMN_MILITARY_FIO, commander[1]);
                    }
                    cv.put(DatabaseHelper.COLUMN_TYPE, input_3.getSelectedItem().toString());
                    cv.put(DatabaseHelper.COLUMN_COMMUNITY_ID, input_4.getText().toString());
                    cv.put(DatabaseHelper.COLUMN_NAME, input_5.getText().toString());
                    DataManager.addDataComm(context, cv);
                    updateCallback.reload();
                    dialog.dismiss();
                }
            }
        });
        changeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (input_3.getSelectedItem().toString().equalsIgnoreCase("Выберите тип объединения")){
                    Toast.makeText(context, "Вы не выбрали тип объединения!", Toast.LENGTH_SHORT).show();
                } else
                if (input_4.getText().toString().isEmpty()){
                    input_4.setError("Заполните поле");
                } else
                if (input_5.getText().toString().isEmpty()){
                    input_5.setError("Заполните поле");
                } else {
                    ContentValues cv = new ContentValues();
                    if (!input_1.getSelectedItem().toString().equalsIgnoreCase("Армия")
                            && !input_1.getSelectedItem().toString().equalsIgnoreCase("Не назначена")) {
                        String[] army = DataManager.getRowListArmy(context, input_1.getSelectedItemPosition()-1);
                        cv.put(DatabaseHelper.COLUMN_MILITARY_DISTRICT_ID, army[0]);
                        cv.put(DatabaseHelper.COLUMN_ARMY_ID, army[1]);
                    } else if (input_1.getSelectedItem().toString().equalsIgnoreCase("Не назначена")){
                        cv.putNull(DatabaseHelper.COLUMN_MILITARY_DISTRICT_ID);
                        cv.putNull(DatabaseHelper.COLUMN_ARMY_ID);
                    }
                    if (!input_2.getSelectedItem().toString().equalsIgnoreCase("Командующий (либо тот же)")
                            && !input_2.getSelectedItem().toString().equalsIgnoreCase("Нет командующего")) {
                        String[] commander = DataManager.getFreeMilitaryRowOF(context, input_2.getSelectedItemPosition()-1);
                        cv.put(DatabaseHelper.COLUMN_MILITARY_ID, commander[0]);
                        cv.put(DatabaseHelper.COLUMN_MILITARY_FIO, commander[1]);
                    } else if (input_2.getSelectedItem().toString().equalsIgnoreCase("Нет командующего")){
                        cv.putNull(DatabaseHelper.COLUMN_MILITARY_ID);
                        cv.putNull(DatabaseHelper.COLUMN_MILITARY_FIO);
                    }
                    cv.put(DatabaseHelper.COLUMN_TYPE, input_3.getSelectedItem().toString());
                    cv.put(DatabaseHelper.COLUMN_COMMUNITY_ID, input_4.getText().toString());
                    cv.put(DatabaseHelper.COLUMN_NAME, input_5.getText().toString());
                    DataManager.changeDataComm(context, cv, row_data[4]);
                    updateCallback.reload();
                    dialog.dismiss();
                }
            }
        });
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DataManager.deleteDataComm(context, row_data[4]);
                updateCallback.reload();
                dialog.dismiss();
            }
        });
    }

    public static void alertDialogMilitaryUnit(final Context context, final String[] row_data){
        final Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.input_alert_mu);
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        dialog.getWindow().setLayout(1000, 1300);
        dialog.show();

        final TextView title = (TextView) dialog.findViewById(R.id.dialog_title);
        final Spinner input_1 = (Spinner) dialog.findViewById(R.id.input_1);
        final Spinner input_2 = (Spinner) dialog.findViewById(R.id.input_2);
        final Spinner input_3 = (Spinner) dialog.findViewById(R.id.input_3);
        final EditText input_4 = (EditText) dialog.findViewById(R.id.input_4);
        final EditText input_5 = (EditText) dialog.findViewById(R.id.input_5);
        final Button cancelButton = (Button) dialog.findViewById(R.id.dialog_cancelButton);
        final Button okButton = (Button) dialog.findViewById(R.id.dialog_okButton);
        final Button changeButton = (Button) dialog.findViewById(R.id.dialog_changeButton);
        final Button deleteButton = (Button) dialog.findViewById(R.id.dialog_deleteButton);

        List<String> dataDP = DataManager.getListDP(context);
        if (dataDP.isEmpty()) dataDP.add("Места дислокации отсутствуют");
        dataDP.add("Место дислокации");
        ArrayAdapter<String> adapterDP = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_dropdown_item, dataDP) {

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {

                View v = super.getView(position, convertView, parent);
                if (position == getCount()) {
                    ((TextView)v.findViewById(android.R.id.text1)).setText("");
                    ((TextView)v.findViewById(android.R.id.text1)).setHint(getItem(getCount())); //"Hint to be displayed"
                }
                return v;
            }

            @Override
            public int getCount() {
                return super.getCount()-1; // you dont display last item. It is used as hint.
            }

        };
        adapterDP.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        input_1.setAdapter(adapterDP);
        input_1.setSelection(adapterDP.getCount()); //display hint

        List<String> dataComm = DataManager.getListComm(context);
        dataComm.add(0, "Не назначено");
        dataComm.add("Объединение");
        ArrayAdapter<String> adapterComm = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_dropdown_item, dataComm) {

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {

                View v = super.getView(position, convertView, parent);
                if (position == getCount()) {
                    ((TextView)v.findViewById(android.R.id.text1)).setText("");
                    ((TextView)v.findViewById(android.R.id.text1)).setHint(getItem(getCount())); //"Hint to be displayed"
                }
                return v;
            }

            @Override
            public int getCount() {
                return super.getCount()-1; // you dont display last item. It is used as hint.
            }

        };
        adapterComm.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        input_2.setAdapter(adapterComm);
        input_2.setSelection(adapterComm.getCount()); //display hint

        List<String> dataCommander = DataManager.getListFreeMilitaryOF(context);
        dataCommander.add(0, "Нет командующего");
        dataCommander.add("Командующий (либо тот же)");
        ArrayAdapter<String> adapterCommands = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_dropdown_item, dataCommander) {

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {

                View v = super.getView(position, convertView, parent);
                if (position == getCount()) {
                    ((TextView)v.findViewById(android.R.id.text1)).setText("");
                    ((TextView)v.findViewById(android.R.id.text1)).setHint(getItem(getCount())); //"Hint to be displayed"
                }

                return v;
            }

            @Override
            public int getCount() {
                return super.getCount()-1; // you dont display last item. It is used as hint.
            }

        };
        adapterCommands.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        input_3.setAdapter(adapterCommands);
        input_3.setSelection(adapterCommands.getCount()); //display hint

        if (row_data != null){
            title.setText(R.string.change_row);
            okButton.setVisibility(View.GONE);
            changeButton.setVisibility(View.VISIBLE);
            deleteButton.setVisibility(View.VISIBLE);
            input_1.setSelection(dataDP.indexOf(row_data[0]+" - "+row_data[1]+" - "+row_data[2]));
            if (row_data[3] != null) input_2.setSelection(dataComm.indexOf(row_data[0]+" - "+row_data[3]+" - "+row_data[4]));
            else input_2.setSelection(adapterComm.getCount());
            input_3.setSelection(adapterCommands.getCount());
            input_4.setText(row_data[5]);
            input_5.setText(row_data[6]);
        }
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (input_1.getSelectedItem().toString().equalsIgnoreCase("Место дислокации")
                        || input_1.getSelectedItem().toString().equalsIgnoreCase("Места дислокации отсутствуют")){
                    Toast.makeText(context, "Вы не выбрали место дислокации!", Toast.LENGTH_SHORT).show();
                } else
                if (input_4.getText().toString().isEmpty()){
                    input_4.setError("Заполните поле");
                } else
                if (input_5.getText().toString().isEmpty()){
                    input_5.setError("Заполните поле");
                } else {
                    ContentValues cv = new ContentValues();
                    String[] dp = DataManager.getRowListDP(context, input_1.getSelectedItemPosition());
                    cv.put(DatabaseHelper.COLUMN_MILITARY_DISTRICT_ID, dp[0]);
                    cv.put(DatabaseHelper.COLUMN_DISLOCATION_PLACE_ID, dp[1]);
                    if (!input_2.getSelectedItem().toString().equalsIgnoreCase("Объединение")
                            && !input_2.getSelectedItem().toString().equalsIgnoreCase("Не назначено")) {
                        String comm = DataManager.getRowListComm(context, input_2.getSelectedItemPosition()-1);
                        cv.put(DatabaseHelper.COLUMN_COMMUNITY_ID, comm);
                    }
                    if (!input_3.getSelectedItem().toString().equalsIgnoreCase("Командующий (либо тот же)")
                            && !input_3.getSelectedItem().toString().equalsIgnoreCase("Нет командующего")) {
                        String[] commander = DataManager.getFreeMilitaryRowOF(context, input_3.getSelectedItemPosition()-1);
                        cv.put(DatabaseHelper.COLUMN_MILITARY_ID, commander[0]);
                        cv.put(DatabaseHelper.COLUMN_MILITARY_FIO, commander[1]);
                    }
                    cv.put(DatabaseHelper.COLUMN_MILITARY_UNIT_ID, input_4.getText().toString());
                    cv.put(DatabaseHelper.COLUMN_NAME, input_5.getText().toString());
                    DataManager.addDataMU(context, cv);
                    updateCallback.reload();
                    dialog.dismiss();
                }
            }
        });
        changeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (input_1.getSelectedItem().toString().equalsIgnoreCase("Место дислокации")
                        || input_1.getSelectedItem().toString().equalsIgnoreCase("Места дислокации отсутствуют")){
                    Toast.makeText(context, "Вы не выбрали место дислокации!", Toast.LENGTH_SHORT).show();
                } else
                if (input_4.getText().toString().isEmpty()){
                    input_4.setError("Заполните поле");
                } else
                if (input_5.getText().toString().isEmpty()){
                    input_5.setError("Заполните поле");
                } else {
                    ContentValues cv = new ContentValues();
                    String[] dp = DataManager.getRowListDP(context, input_1.getSelectedItemPosition());
                    cv.put(DatabaseHelper.COLUMN_MILITARY_DISTRICT_ID, dp[0]);
                    cv.put(DatabaseHelper.COLUMN_DISLOCATION_PLACE_ID, dp[1]);
                    if (!input_2.getSelectedItem().toString().equalsIgnoreCase("Объединение")
                            && !input_2.getSelectedItem().toString().equalsIgnoreCase("Не назначено")) {
                        String comm = DataManager.getRowListComm(context, input_2.getSelectedItemPosition()-1);
                        cv.put(DatabaseHelper.COLUMN_COMMUNITY_ID, comm);
                    } else if (input_2.getSelectedItem().toString().equalsIgnoreCase("Не назначено")){
                        cv.putNull(DatabaseHelper.COLUMN_COMMUNITY_ID);
                    }
                    if (!input_3.getSelectedItem().toString().equalsIgnoreCase("Командующий (либо тот же)")
                            && !input_3.getSelectedItem().toString().equalsIgnoreCase("Нет командующего")) {
                        String[] commander = DataManager.getFreeMilitaryRowOF(context, input_3.getSelectedItemPosition()-1);
                        cv.put(DatabaseHelper.COLUMN_MILITARY_ID, commander[0]);
                        cv.put(DatabaseHelper.COLUMN_MILITARY_FIO, commander[1]);
                    } else if (input_3.getSelectedItem().toString().equalsIgnoreCase("Нет командующего")){
                        cv.putNull(DatabaseHelper.COLUMN_MILITARY_ID);
                        cv.putNull(DatabaseHelper.COLUMN_MILITARY_FIO);
                    }
                    cv.put(DatabaseHelper.COLUMN_MILITARY_UNIT_ID, input_4.getText().toString());
                    cv.put(DatabaseHelper.COLUMN_NAME, input_5.getText().toString());
                    DataManager.changeDataMU(context, cv, row_data[5]);
                    updateCallback.reload();
                    dialog.dismiss();
                }
            }
        });
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DataManager.deleteDataMU(context, row_data[5]);
                updateCallback.reload();
                dialog.dismiss();
            }
        });
    }

    public static void alertDialogCompany(final Context context, final String[] row_data){
        final Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.input_alert_cmp);
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        dialog.getWindow().setLayout(1000, 1200);
        dialog.show();

        final TextView title = (TextView) dialog.findViewById(R.id.dialog_title);
        final Spinner input_1 = (Spinner) dialog.findViewById(R.id.input_1);
        final Spinner input_2 = (Spinner) dialog.findViewById(R.id.input_2);
        final EditText input_3 = (EditText) dialog.findViewById(R.id.input_3);
        final EditText input_4 = (EditText) dialog.findViewById(R.id.input_4);
        final Button cancelButton = (Button) dialog.findViewById(R.id.dialog_cancelButton);
        final Button okButton = (Button) dialog.findViewById(R.id.dialog_okButton);
        final Button changeButton = (Button) dialog.findViewById(R.id.dialog_changeButton);
        final Button deleteButton = (Button) dialog.findViewById(R.id.dialog_deleteButton);

        List<String> dataMU = DataManager.getListMU(context);
        if (dataMU.isEmpty()) dataMU.add("Военные части отсутствуют");
        dataMU.add("Военная часть");
        ArrayAdapter<String> adapterMU = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_dropdown_item, dataMU) {

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {

                View v = super.getView(position, convertView, parent);
                if (position == getCount()) {
                    ((TextView)v.findViewById(android.R.id.text1)).setText("");
                    ((TextView)v.findViewById(android.R.id.text1)).setHint(getItem(getCount())); //"Hint to be displayed"
                }
                return v;
            }

            @Override
            public int getCount() {
                return super.getCount()-1; // you dont display last item. It is used as hint.
            }

        };
        adapterMU.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        input_1.setAdapter(adapterMU);
        input_1.setSelection(adapterMU.getCount()); //display hint

        List<String> dataCommander = DataManager.getListFreeMilitaryOF(context);
        dataCommander.add(0, "Нет командующего");
        dataCommander.add("Командующий (либо тот же)");
        ArrayAdapter<String> adapterCommands = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_dropdown_item, dataCommander) {

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {

                View v = super.getView(position, convertView, parent);
                if (position == getCount()) {
                    ((TextView)v.findViewById(android.R.id.text1)).setText("");
                    ((TextView)v.findViewById(android.R.id.text1)).setHint(getItem(getCount())); //"Hint to be displayed"
                }

                return v;
            }

            @Override
            public int getCount() {
                return super.getCount()-1; // you dont display last item. It is used as hint.
            }

        };
        adapterCommands.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        input_2.setAdapter(adapterCommands);
        input_2.setSelection(adapterCommands.getCount()); //display hint

        if (row_data != null){
            title.setText(R.string.change_row);
            okButton.setVisibility(View.GONE);
            changeButton.setVisibility(View.VISIBLE);
            deleteButton.setVisibility(View.VISIBLE);
            input_1.setSelection(dataMU.indexOf(row_data[0]+" - "+row_data[1]+" - "+row_data[2]));
            input_2.setSelection(adapterCommands.getCount());
            input_3.setText(row_data[3]);
            input_4.setText(row_data[4]);
        }
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (input_1.getSelectedItem().toString().equalsIgnoreCase("Военная часть")
                        || input_1.getSelectedItem().toString().equalsIgnoreCase("Военные части отсутствуют")){
                    Toast.makeText(context, "Вы не выбрали военную часть!", Toast.LENGTH_SHORT).show();
                } else
                if (input_3.getText().toString().isEmpty()){
                    input_3.setError("Заполните поле");
                } else
                if (input_4.getText().toString().isEmpty()){
                    input_4.setError("Заполните поле");
                } else {
                    ContentValues cv = new ContentValues();
                    String[] mu = DataManager.getRowListMU(context, input_1.getSelectedItemPosition());
                    cv.put(DatabaseHelper.COLUMN_MILITARY_DISTRICT_ID, mu[0]);
                    cv.put(DatabaseHelper.COLUMN_DISLOCATION_PLACE_ID, mu[1]);
                    cv.put(DatabaseHelper.COLUMN_MILITARY_UNIT_ID, mu[2]);
                    if (!input_2.getSelectedItem().toString().equalsIgnoreCase("Командующий (либо тот же)")
                            && !input_2.getSelectedItem().toString().equalsIgnoreCase("Нет командующего")) {
                        String[] commander = DataManager.getFreeMilitaryRowOF(context, input_2.getSelectedItemPosition()-1);
                        cv.put(DatabaseHelper.COLUMN_MILITARY_ID, commander[0]);
                        cv.put(DatabaseHelper.COLUMN_MILITARY_FIO, commander[1]);
                    }
                    cv.put(DatabaseHelper.COLUMN_COMPANY_ID, input_3.getText().toString());
                    cv.put(DatabaseHelper.COLUMN_NAME, input_4.getText().toString());
                    DataManager.addDataCmp(context, cv);
                    updateCallback.reload();
                    dialog.dismiss();
                }
            }
        });
        changeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (input_1.getSelectedItem().toString().equalsIgnoreCase("Военная часть")
                        || input_1.getSelectedItem().toString().equalsIgnoreCase("Военные части отсутствуют")){
                    Toast.makeText(context, "Вы не выбрали военную часть!", Toast.LENGTH_SHORT).show();
                } else
                if (input_3.getText().toString().isEmpty()){
                    input_3.setError("Заполните поле");
                } else
                if (input_4.getText().toString().isEmpty()){
                    input_4.setError("Заполните поле");
                } else {
                    ContentValues cv = new ContentValues();
                    String[] mu = DataManager.getRowListMU(context, input_1.getSelectedItemPosition());
                    cv.put(DatabaseHelper.COLUMN_MILITARY_DISTRICT_ID, mu[0]);
                    cv.put(DatabaseHelper.COLUMN_DISLOCATION_PLACE_ID, mu[1]);
                    cv.put(DatabaseHelper.COLUMN_MILITARY_UNIT_ID, mu[2]);
                    if (!input_2.getSelectedItem().toString().equalsIgnoreCase("Командующий (либо тот же)")
                            && !input_2.getSelectedItem().toString().equalsIgnoreCase("Нет командующего")) {
                        String[] commander = DataManager.getFreeMilitaryRowOF(context, input_2.getSelectedItemPosition()-1);
                        cv.put(DatabaseHelper.COLUMN_MILITARY_ID, commander[0]);
                        cv.put(DatabaseHelper.COLUMN_MILITARY_FIO, commander[1]);
                    } else if (input_2.getSelectedItem().toString().equalsIgnoreCase("Нет командующего")){
                        cv.putNull(DatabaseHelper.COLUMN_MILITARY_ID);
                        cv.putNull(DatabaseHelper.COLUMN_MILITARY_FIO);
                    }
                    cv.put(DatabaseHelper.COLUMN_COMPANY_ID, input_3.getText().toString());
                    cv.put(DatabaseHelper.COLUMN_NAME, input_4.getText().toString());
                    DataManager.changeDataCmp(context, cv, row_data[3]);
                    updateCallback.reload();
                    dialog.dismiss();
                }
            }
        });
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DataManager.deleteDataCmp(context, row_data[3]);
                updateCallback.reload();
                dialog.dismiss();
            }
        });
    }

    public static void alertDialogPlatoon(final Context context, final String[] row_data){
        final Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.input_alert_pln);
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        dialog.getWindow().setLayout(1000, 1200);
        dialog.show();

        final TextView title = (TextView) dialog.findViewById(R.id.dialog_title);
        final Spinner input_1 = (Spinner) dialog.findViewById(R.id.input_1);
        final Spinner input_2 = (Spinner) dialog.findViewById(R.id.input_2);
        final EditText input_3 = (EditText) dialog.findViewById(R.id.input_3);
        final EditText input_4 = (EditText) dialog.findViewById(R.id.input_4);
        final Button cancelButton = (Button) dialog.findViewById(R.id.dialog_cancelButton);
        final Button okButton = (Button) dialog.findViewById(R.id.dialog_okButton);
        final Button changeButton = (Button) dialog.findViewById(R.id.dialog_changeButton);
        final Button deleteButton = (Button) dialog.findViewById(R.id.dialog_deleteButton);

        List<String> dataCmp = DataManager.getListCmp(context);
        if (dataCmp.isEmpty()) dataCmp.add("Роты отсутствуют");
        dataCmp.add("Рота");
        ArrayAdapter<String> adapterCmp = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_dropdown_item, dataCmp) {

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {

                View v = super.getView(position, convertView, parent);
                if (position == getCount()) {
                    ((TextView)v.findViewById(android.R.id.text1)).setText("");
                    ((TextView)v.findViewById(android.R.id.text1)).setHint(getItem(getCount())); //"Hint to be displayed"
                }
                return v;
            }

            @Override
            public int getCount() {
                return super.getCount()-1; // you dont display last item. It is used as hint.
            }

        };
        adapterCmp.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        input_1.setAdapter(adapterCmp);
        input_1.setSelection(adapterCmp.getCount()); //display hint

        List<String> dataCommander = DataManager.getListFreeMilitary(context);
        dataCommander.add(0, "Нет командующего");
        dataCommander.add("Командующий (либо тот же)");
        ArrayAdapter<String> adapterCommands = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_dropdown_item, dataCommander) {

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {

                View v = super.getView(position, convertView, parent);
                if (position == getCount()) {
                    ((TextView)v.findViewById(android.R.id.text1)).setText("");
                    ((TextView)v.findViewById(android.R.id.text1)).setHint(getItem(getCount())); //"Hint to be displayed"
                }

                return v;
            }

            @Override
            public int getCount() {
                return super.getCount()-1; // you dont display last item. It is used as hint.
            }

        };
        adapterCommands.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        input_2.setAdapter(adapterCommands);
        input_2.setSelection(adapterCommands.getCount()); //display hint

        if (row_data != null){
            title.setText(R.string.change_row);
            okButton.setVisibility(View.GONE);
            changeButton.setVisibility(View.VISIBLE);
            deleteButton.setVisibility(View.VISIBLE);
            input_1.setSelection(dataCmp.indexOf(row_data[0]+" - "+row_data[1]+" - "+row_data[2]));
            input_2.setSelection(adapterCommands.getCount());
            input_3.setText(row_data[3]);
            input_4.setText(row_data[4]);
        }
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (input_1.getSelectedItem().toString().equalsIgnoreCase("Рота")
                        || input_1.getSelectedItem().toString().equalsIgnoreCase("Роты отсутствуют")){
                    Toast.makeText(context, "Вы не выбрали роту!", Toast.LENGTH_SHORT).show();
                } else
                if (input_3.getText().toString().isEmpty()){
                    input_3.setError("Заполните поле");
                } else
                if (input_4.getText().toString().isEmpty()){
                    input_4.setError("Заполните поле");
                } else {
                    ContentValues cv = new ContentValues();
                    String[] cmp = DataManager.getRowListCmp(context, input_1.getSelectedItemPosition());
                    cv.put(DatabaseHelper.COLUMN_MILITARY_DISTRICT_ID, cmp[0]);
                    cv.put(DatabaseHelper.COLUMN_DISLOCATION_PLACE_ID, cmp[1]);
                    cv.put(DatabaseHelper.COLUMN_MILITARY_UNIT_ID, cmp[2]);
                    cv.put(DatabaseHelper.COLUMN_COMPANY_ID, cmp[3]);
                    if (!input_2.getSelectedItem().toString().equalsIgnoreCase("Командующий (либо тот же)")
                            && !input_2.getSelectedItem().toString().equalsIgnoreCase("Нет командующего")) {
                        String[] commander = DataManager.getFreeMilitaryRow(context, input_2.getSelectedItemPosition()-1);
                        cv.put(DatabaseHelper.COLUMN_MILITARY_ID, commander[0]);
                        cv.put(DatabaseHelper.COLUMN_MILITARY_FIO, commander[1]);
                    }
                    cv.put(DatabaseHelper.COLUMN_PLATOON_ID, input_3.getText().toString());
                    cv.put(DatabaseHelper.COLUMN_NAME, input_4.getText().toString());
                    DataManager.addDataPln(context, cv);
                    updateCallback.reload();
                    dialog.dismiss();
                }
            }
        });
        changeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (input_1.getSelectedItem().toString().equalsIgnoreCase("Рота")
                        || input_1.getSelectedItem().toString().equalsIgnoreCase("Роты отсутствуют")){
                    Toast.makeText(context, "Вы не выбрали роту!", Toast.LENGTH_SHORT).show();
                } else
                if (input_3.getText().toString().isEmpty()){
                    input_3.setError("Заполните поле");
                } else
                if (input_4.getText().toString().isEmpty()){
                    input_4.setError("Заполните поле");
                } else {
                    ContentValues cv = new ContentValues();
                    String[] cmp = DataManager.getRowListCmp(context, input_1.getSelectedItemPosition());
                    cv.put(DatabaseHelper.COLUMN_MILITARY_DISTRICT_ID, cmp[0]);
                    cv.put(DatabaseHelper.COLUMN_DISLOCATION_PLACE_ID, cmp[1]);
                    cv.put(DatabaseHelper.COLUMN_MILITARY_UNIT_ID, cmp[2]);
                    cv.put(DatabaseHelper.COLUMN_COMPANY_ID, cmp[3]);
                    if (!input_2.getSelectedItem().toString().equalsIgnoreCase("Командующий (либо тот же)")
                            && !input_2.getSelectedItem().toString().equalsIgnoreCase("Нет командующего")) {
                        String[] commander = DataManager.getFreeMilitaryRow(context, input_2.getSelectedItemPosition()-1);
                        cv.put(DatabaseHelper.COLUMN_MILITARY_ID, commander[0]);
                        cv.put(DatabaseHelper.COLUMN_MILITARY_FIO, commander[1]);
                    } else if (input_2.getSelectedItem().toString().equalsIgnoreCase("Нет командующего")){
                        cv.putNull(DatabaseHelper.COLUMN_MILITARY_ID);
                        cv.putNull(DatabaseHelper.COLUMN_MILITARY_FIO);
                    }
                    cv.put(DatabaseHelper.COLUMN_PLATOON_ID, input_3.getText().toString());
                    cv.put(DatabaseHelper.COLUMN_NAME, input_4.getText().toString());
                    DataManager.changeDataPln(context, cv, row_data[3]);
                    updateCallback.reload();
                    dialog.dismiss();
                }
            }
        });
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DataManager.deleteDataPln(context, row_data[3]);
                updateCallback.reload();
                dialog.dismiss();
            }
        });
    }

    public static void alertDialogDepartment(final Context context, final String[] row_data){
        final Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.input_alert_dpm);
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        dialog.getWindow().setLayout(1000, 1200);
        dialog.show();

        final TextView title = (TextView) dialog.findViewById(R.id.dialog_title);
        final Spinner input_1 = (Spinner) dialog.findViewById(R.id.input_1);
        final Spinner input_2 = (Spinner) dialog.findViewById(R.id.input_2);
        final EditText input_3 = (EditText) dialog.findViewById(R.id.input_3);
        final EditText input_4 = (EditText) dialog.findViewById(R.id.input_4);
        final Button cancelButton = (Button) dialog.findViewById(R.id.dialog_cancelButton);
        final Button okButton = (Button) dialog.findViewById(R.id.dialog_okButton);
        final Button changeButton = (Button) dialog.findViewById(R.id.dialog_changeButton);
        final Button deleteButton = (Button) dialog.findViewById(R.id.dialog_deleteButton);

        List<String> dataPln = DataManager.getListPln(context);
        if (dataPln.isEmpty()) dataPln.add("Взводы отсутствуют");
        dataPln.add("Взвод");
        ArrayAdapter<String> adapterPln = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_dropdown_item, dataPln) {

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {

                View v = super.getView(position, convertView, parent);
                if (position == getCount()) {
                    ((TextView)v.findViewById(android.R.id.text1)).setText("");
                    ((TextView)v.findViewById(android.R.id.text1)).setHint(getItem(getCount())); //"Hint to be displayed"
                }
                return v;
            }

            @Override
            public int getCount() {
                return super.getCount()-1; // you dont display last item. It is used as hint.
            }

        };
        adapterPln.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        input_1.setAdapter(adapterPln);
        input_1.setSelection(adapterPln.getCount()); //display hint

        List<String> dataCommander = DataManager.getListFreeMilitary(context);
        dataCommander.add(0, "Нет командующего");
        dataCommander.add("Командующий (либо тот же)");
        ArrayAdapter<String> adapterCommands = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_dropdown_item, dataCommander) {

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {

                View v = super.getView(position, convertView, parent);
                if (position == getCount()) {
                    ((TextView)v.findViewById(android.R.id.text1)).setText("");
                    ((TextView)v.findViewById(android.R.id.text1)).setHint(getItem(getCount())); //"Hint to be displayed"
                }

                return v;
            }

            @Override
            public int getCount() {
                return super.getCount()-1; // you dont display last item. It is used as hint.
            }

        };
        adapterCommands.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        input_2.setAdapter(adapterCommands);
        input_2.setSelection(adapterCommands.getCount()); //display hint

        if (row_data != null){
            title.setText(R.string.change_row);
            okButton.setVisibility(View.GONE);
            changeButton.setVisibility(View.VISIBLE);
            deleteButton.setVisibility(View.VISIBLE);
            input_1.setSelection(dataPln.indexOf(row_data[0]+" - "+row_data[1]+" - "+row_data[2]));
            input_2.setSelection(adapterCommands.getCount());
            input_3.setText(row_data[3]);
            input_4.setText(row_data[4]);
        }
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (input_1.getSelectedItem().toString().equalsIgnoreCase("Взвод")
                        || input_1.getSelectedItem().toString().equalsIgnoreCase("Взводы отсутствуют")){
                    Toast.makeText(context, "Вы не выбрали взвод!", Toast.LENGTH_SHORT).show();
                } else
                if (input_3.getText().toString().isEmpty()){
                    input_3.setError("Заполните поле");
                } else
                if (input_4.getText().toString().isEmpty()){
                    input_4.setError("Заполните поле");
                } else {
                    ContentValues cv = new ContentValues();
                    String[] pln = DataManager.getRowListPln(context, input_1.getSelectedItemPosition());
                    cv.put(DatabaseHelper.COLUMN_MILITARY_DISTRICT_ID, pln[0]);
                    cv.put(DatabaseHelper.COLUMN_DISLOCATION_PLACE_ID, pln[1]);
                    cv.put(DatabaseHelper.COLUMN_MILITARY_UNIT_ID, pln[2]);
                    cv.put(DatabaseHelper.COLUMN_COMPANY_ID, pln[3]);
                    cv.put(DatabaseHelper.COLUMN_PLATOON_ID, pln[4]);
                    if (!input_2.getSelectedItem().toString().equalsIgnoreCase("Командующий (либо тот же)")
                            && !input_2.getSelectedItem().toString().equalsIgnoreCase("Нет командующего")) {
                        String[] commander = DataManager.getFreeMilitaryRow(context, input_2.getSelectedItemPosition()-1);
                        cv.put(DatabaseHelper.COLUMN_MILITARY_ID, commander[0]);
                        cv.put(DatabaseHelper.COLUMN_MILITARY_FIO, commander[1]);
                    }
                    cv.put(DatabaseHelper.COLUMN_DEPARTMENT_ID, input_3.getText().toString());
                    cv.put(DatabaseHelper.COLUMN_NAME, input_4.getText().toString());
                    DataManager.addDataDpm(context, cv);
                    updateCallback.reload();
                    dialog.dismiss();
                }
            }
        });
        changeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (input_1.getSelectedItem().toString().equalsIgnoreCase("Взвод")
                        || input_1.getSelectedItem().toString().equalsIgnoreCase("Взводы отсутствуют")){
                    Toast.makeText(context, "Вы не выбрали взвод!", Toast.LENGTH_SHORT).show();
                } else
                if (input_3.getText().toString().isEmpty()){
                    input_3.setError("Заполните поле");
                } else
                if (input_4.getText().toString().isEmpty()){
                    input_4.setError("Заполните поле");
                } else {
                    ContentValues cv = new ContentValues();
                    String[] pln = DataManager.getRowListPln(context, input_1.getSelectedItemPosition());
                    cv.put(DatabaseHelper.COLUMN_MILITARY_DISTRICT_ID, pln[0]);
                    cv.put(DatabaseHelper.COLUMN_DISLOCATION_PLACE_ID, pln[1]);
                    cv.put(DatabaseHelper.COLUMN_MILITARY_UNIT_ID, pln[2]);
                    cv.put(DatabaseHelper.COLUMN_COMPANY_ID, pln[3]);
                    cv.put(DatabaseHelper.COLUMN_PLATOON_ID, pln[4]);
                    if (!input_2.getSelectedItem().toString().equalsIgnoreCase("Командующий (либо тот же)")
                            && !input_2.getSelectedItem().toString().equalsIgnoreCase("Нет командующего")) {
                        String[] commander = DataManager.getFreeMilitaryRow(context, input_2.getSelectedItemPosition()-1);
                        cv.put(DatabaseHelper.COLUMN_MILITARY_ID, commander[0]);
                        cv.put(DatabaseHelper.COLUMN_MILITARY_FIO, commander[1]);
                    } else if (input_2.getSelectedItem().toString().equalsIgnoreCase("Нет командующего")){
                        cv.putNull(DatabaseHelper.COLUMN_MILITARY_ID);
                        cv.putNull(DatabaseHelper.COLUMN_MILITARY_FIO);
                    }
                    cv.put(DatabaseHelper.COLUMN_DEPARTMENT_ID, input_3.getText().toString());
                    cv.put(DatabaseHelper.COLUMN_NAME, input_4.getText().toString());
                    DataManager.changeDataDpm(context, cv, row_data[3]);
                    updateCallback.reload();
                    dialog.dismiss();
                }
            }
        });
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DataManager.deleteDataDpm(context, row_data[3]);
                updateCallback.reload();
                dialog.dismiss();
            }
        });
    }

    public static void alertDialogSpeciality(final Context context, final String[] row_data){
        final Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.input_alert_spc);
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        dialog.getWindow().setLayout(1000, 1200);
        dialog.show();

        final TextView title = (TextView) dialog.findViewById(R.id.dialog_title);
        final EditText input_1 = (EditText) dialog.findViewById(R.id.input_1);
        final EditText input_2 = (EditText) dialog.findViewById(R.id.input_2);
        final EditText input_3 = (EditText) dialog.findViewById(R.id.input_3);
        final Button cancelButton = (Button) dialog.findViewById(R.id.dialog_cancelButton);
        final Button okButton = (Button) dialog.findViewById(R.id.dialog_okButton);
        final Button changeButton = (Button) dialog.findViewById(R.id.dialog_changeButton);
        final Button deleteButton = (Button) dialog.findViewById(R.id.dialog_deleteButton);

        if (row_data != null){
            title.setText(R.string.change_row);
            okButton.setVisibility(View.GONE);
            changeButton.setVisibility(View.VISIBLE);
            deleteButton.setVisibility(View.VISIBLE);
            input_1.setText(row_data[0]);
            input_2.setText(row_data[1]);
            input_3.setText(row_data[2]);
        }
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (input_1.getText().toString().isEmpty()){
                    input_1.setError("Заполните поле");
                } else
                if (input_2.getText().toString().isEmpty()){
                    input_2.setError("Заполните поле");
                } else
                if (input_3.getText().toString().isEmpty()){
                    input_3.setError("Заполните поле");
                } else {
                    ContentValues cv = new ContentValues();
                    cv.put(DatabaseHelper.COLUMN_SPECIALITY_ID, input_1.getText().toString());
                    cv.put(DatabaseHelper.COLUMN_NAME, input_2.getText().toString());
                    cv.put(DatabaseHelper.COLUMN_QUALIFICATION, input_3.getText().toString());
                    DataManager.addDataSpc(context, cv);
                    updateCallback.reload();
                    dialog.dismiss();
                }
            }
        });
        changeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (input_1.getText().toString().isEmpty()){
                    input_1.setError("Заполните поле");
                } else
                if (input_2.getText().toString().isEmpty()){
                    input_2.setError("Заполните поле");
                } else
                if (input_3.getText().toString().isEmpty()){
                    input_3.setError("Заполните поле");
                } else {
                    ContentValues cv = new ContentValues();
                    cv.put(DatabaseHelper.COLUMN_SPECIALITY_ID, input_1.getText().toString());
                    cv.put(DatabaseHelper.COLUMN_NAME, input_2.getText().toString());
                    cv.put(DatabaseHelper.COLUMN_QUALIFICATION, input_3.getText().toString());
                    DataManager.changeDataSpc(context, cv, row_data[0]);
                    updateCallback.reload();
                    dialog.dismiss();
                }
            }
        });
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DataManager.deleteDataSpc(context, row_data[0]);
                updateCallback.reload();
                dialog.dismiss();
            }
        });
    }

    public static void alertDialogEquipmentCategory(final Context context, final String[] row_data){
        final Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.input_alert_equ_c);
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        dialog.getWindow().setLayout(1000, 1200);
        dialog.show();

        final TextView title = (TextView) dialog.findViewById(R.id.dialog_title);
        final EditText input_1 = (EditText) dialog.findViewById(R.id.input_1);
        final EditText input_2 = (EditText) dialog.findViewById(R.id.input_2);
        final Button cancelButton = (Button) dialog.findViewById(R.id.dialog_cancelButton);
        final Button okButton = (Button) dialog.findViewById(R.id.dialog_okButton);
        final Button changeButton = (Button) dialog.findViewById(R.id.dialog_changeButton);
        final Button deleteButton = (Button) dialog.findViewById(R.id.dialog_deleteButton);

        if (row_data != null){
            title.setText(R.string.change_row);
            okButton.setVisibility(View.GONE);
            changeButton.setVisibility(View.VISIBLE);
            deleteButton.setVisibility(View.VISIBLE);
            input_1.setText(row_data[0]);
            input_2.setText(row_data[1]);
        }
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (input_1.getText().toString().isEmpty()){
                    input_1.setError("Заполните поле");
                } else
                if (input_2.getText().toString().isEmpty()){
                    input_2.setError("Заполните поле");
                } else {
                    ContentValues cv = new ContentValues();
                    cv.put(DatabaseHelper.COLUMN_EQUIPMENT_CATEGORY_ID, input_1.getText().toString());
                    cv.put(DatabaseHelper.COLUMN_NAME, input_2.getText().toString());
                    DataManager.addDataEquC(context, cv);
                    updateCallback.reload();
                    dialog.dismiss();
                }
            }
        });
        changeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (input_1.getText().toString().isEmpty()){
                    input_1.setError("Заполните поле");
                } else
                if (input_2.getText().toString().isEmpty()){
                    input_2.setError("Заполните поле");
                } else {
                    ContentValues cv = new ContentValues();
                    cv.put(DatabaseHelper.COLUMN_EQUIPMENT_CATEGORY_ID, input_1.getText().toString());
                    cv.put(DatabaseHelper.COLUMN_NAME, input_2.getText().toString());
                    DataManager.changeDataEquC(context, cv, row_data[0]);
                    updateCallback.reload();
                    dialog.dismiss();
                }
            }
        });
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DataManager.deleteDataEquC(context, row_data[0]);
                updateCallback.reload();
                dialog.dismiss();
            }
        });
    }

    public static void alertDialogWeaponryCategory(final Context context, final String[] row_data){
        final Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.input_alert_wpn_c);
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        dialog.getWindow().setLayout(1000, 1200);
        dialog.show();

        final TextView title = (TextView) dialog.findViewById(R.id.dialog_title);
        final EditText input_1 = (EditText) dialog.findViewById(R.id.input_1);
        final EditText input_2 = (EditText) dialog.findViewById(R.id.input_2);
        final Button cancelButton = (Button) dialog.findViewById(R.id.dialog_cancelButton);
        final Button okButton = (Button) dialog.findViewById(R.id.dialog_okButton);
        final Button changeButton = (Button) dialog.findViewById(R.id.dialog_changeButton);
        final Button deleteButton = (Button) dialog.findViewById(R.id.dialog_deleteButton);

        if (row_data != null){
            title.setText(R.string.change_row);
            okButton.setVisibility(View.GONE);
            changeButton.setVisibility(View.VISIBLE);
            deleteButton.setVisibility(View.VISIBLE);
            input_1.setText(row_data[0]);
            input_2.setText(row_data[1]);
        }
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (input_1.getText().toString().isEmpty()){
                    input_1.setError("Заполните поле");
                } else
                if (input_2.getText().toString().isEmpty()){
                    input_2.setError("Заполните поле");
                } else {
                    ContentValues cv = new ContentValues();
                    cv.put(DatabaseHelper.COLUMN_WEAPONRY_CATEGORY_ID, input_1.getText().toString());
                    cv.put(DatabaseHelper.COLUMN_NAME, input_2.getText().toString());
                    DataManager.addDataWpnC(context, cv);
                    updateCallback.reload();
                    dialog.dismiss();
                }
            }
        });
        changeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (input_1.getText().toString().isEmpty()){
                    input_1.setError("Заполните поле");
                } else
                if (input_2.getText().toString().isEmpty()){
                    input_2.setError("Заполните поле");
                } else {
                    ContentValues cv = new ContentValues();
                    cv.put(DatabaseHelper.COLUMN_WEAPONRY_CATEGORY_ID, input_1.getText().toString());
                    cv.put(DatabaseHelper.COLUMN_NAME, input_2.getText().toString());
                    DataManager.changeDataWpnC(context, cv, row_data[0]);
                    updateCallback.reload();
                    dialog.dismiss();
                }
            }
        });
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DataManager.deleteDataWpnC(context, row_data[0]);
                updateCallback.reload();
                dialog.dismiss();
            }
        });
    }

    public static void alertDialogEquipment(final Context context, final String[] row_data){
        final Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.input_alert_equ);
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        dialog.getWindow().setLayout(1000, 1300);
        dialog.show();

        final TextView title = (TextView) dialog.findViewById(R.id.dialog_title);
        final Spinner input_1 = (Spinner) dialog.findViewById(R.id.input_1);
        final Spinner input_2 = (Spinner) dialog.findViewById(R.id.input_2);
        final EditText input_3 = (EditText) dialog.findViewById(R.id.input_3);
        final EditText input_4 = (EditText) dialog.findViewById(R.id.input_4);
        final EditText input_5 = (EditText) dialog.findViewById(R.id.input_5);
        final Button cancelButton = (Button) dialog.findViewById(R.id.dialog_cancelButton);
        final Button okButton = (Button) dialog.findViewById(R.id.dialog_okButton);
        final Button changeButton = (Button) dialog.findViewById(R.id.dialog_changeButton);
        final Button deleteButton = (Button) dialog.findViewById(R.id.dialog_deleteButton);

        List<String> dataEquC = DataManager.getListEquC(context);
        if (dataEquC.isEmpty()) dataEquC.add("Категории отсутствуют");
        dataEquC.add("Категория техники");
        ArrayAdapter<String> adapterEquC = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_dropdown_item, dataEquC) {

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {

                View v = super.getView(position, convertView, parent);
                if (position == getCount()) {
                    ((TextView)v.findViewById(android.R.id.text1)).setText("");
                    ((TextView)v.findViewById(android.R.id.text1)).setHint(getItem(getCount())); //"Hint to be displayed"
                }
                return v;
            }

            @Override
            public int getCount() {
                return super.getCount()-1; // you dont display last item. It is used as hint.
            }

        };
        adapterEquC.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        input_1.setAdapter(adapterEquC);
        input_1.setSelection(adapterEquC.getCount()); //display hint

        final List<String> type = new ArrayList<>(3);
        type.add("Боевая техника");
        type.add("Транспортная техника");
        type.add("Выберите тип техники");
        ArrayAdapter<String> adapterType = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_dropdown_item, type) {

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {

                View v = super.getView(position, convertView, parent);
                if (position == getCount()) {
                    ((TextView)v.findViewById(android.R.id.text1)).setText(null);
                    ((TextView)v.findViewById(android.R.id.text1)).setHint(getItem(getCount())); //"Hint to be displayed"
                }

                return v;
            }

            @Override
            public int getCount() {
                return super.getCount()-1; // you don`t display last item. It is used as hint.
            }

        };
        adapterType.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        input_2.setAdapter(adapterType);
        input_2.setSelection(adapterType.getCount()); //display hint

        if (row_data != null){
            title.setText(R.string.change_row);
            okButton.setVisibility(View.GONE);
            changeButton.setVisibility(View.VISIBLE);
            deleteButton.setVisibility(View.VISIBLE);
            if (row_data[0] != null) input_1.setSelection(dataEquC.indexOf(row_data[0]+" - "+row_data[1]));
            else input_1.setSelection(adapterEquC.getCount());
            input_2.setSelection(type.indexOf(row_data[2]));
            input_3.setText(row_data[3]);
            input_4.setText(row_data[4]);
            input_5.setText(row_data[5]);
        }
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (input_1.getSelectedItem().toString().equalsIgnoreCase("Категория техники")
                        || input_1.getSelectedItem().toString().equalsIgnoreCase("Категории отсутствуют")){
                    Toast.makeText(context, "Вы не выбрали категорию техники!", Toast.LENGTH_SHORT).show();
                } else
                if (input_2.getSelectedItem().toString().equalsIgnoreCase("Выберите тип техники")){
                    Toast.makeText(context, "Вы не выбрали тип техники!", Toast.LENGTH_SHORT).show();
                } else
                if (input_3.getText().toString().isEmpty()){
                    input_3.setError("Заполните поле");
                } else
                if (input_4.getText().toString().isEmpty()){
                    input_4.setError("Заполните поле");
                } else
                if (input_5.getText().toString().isEmpty()){
                    input_5.setError("Заполните поле");
                } else {
                    ContentValues cv = new ContentValues();
                    String equC = DataManager.getRowListEquC(context, input_1.getSelectedItemPosition());
                    cv.put(DatabaseHelper.COLUMN_EQUIPMENT_CATEGORY_ID, equC);
                    cv.put(DatabaseHelper.COLUMN_TYPE, input_2.getSelectedItem().toString());
                    cv.put(DatabaseHelper.COLUMN_EQUIPMENT_ID, input_3.getText().toString());
                    cv.put(DatabaseHelper.COLUMN_NAME, input_4.getText().toString());
                    cv.put(DatabaseHelper.COLUMN_ATT, input_5.getText().toString());
                    DataManager.addDataEqu(context, cv);
                    updateCallback.reload();
                    dialog.dismiss();
                }
            }
        });
        changeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (input_1.getSelectedItem().toString().equalsIgnoreCase("Категория техники")
                        || input_1.getSelectedItem().toString().equalsIgnoreCase("Категории отсутствуют")){
                    Toast.makeText(context, "Вы не выбрали категорию техники!", Toast.LENGTH_SHORT).show();
                } else
                if (input_2.getSelectedItem().toString().equalsIgnoreCase("Выберите тип техники")){
                    Toast.makeText(context, "Вы не выбрали тип техники!", Toast.LENGTH_SHORT).show();
                } else
                if (input_3.getText().toString().isEmpty()){
                    input_3.setError("Заполните поле");
                } else
                if (input_4.getText().toString().isEmpty()){
                    input_4.setError("Заполните поле");
                } else
                if (input_5.getText().toString().isEmpty()){
                    input_5.setError("Заполните поле");
                } else {
                    ContentValues cv = new ContentValues();
                    String equC = DataManager.getRowListEquC(context, input_1.getSelectedItemPosition());
                    cv.put(DatabaseHelper.COLUMN_EQUIPMENT_CATEGORY_ID, equC);
                    cv.put(DatabaseHelper.COLUMN_TYPE, input_2.getSelectedItem().toString());
                    cv.put(DatabaseHelper.COLUMN_EQUIPMENT_ID, input_3.getText().toString());
                    cv.put(DatabaseHelper.COLUMN_NAME, input_4.getText().toString());
                    cv.put(DatabaseHelper.COLUMN_ATT, input_5.getText().toString());
                    DataManager.changeDataEqu(context, cv, row_data[3]);
                    updateCallback.reload();
                    dialog.dismiss();
                }
            }
        });
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DataManager.deleteDataEqu(context, row_data[3]);
                updateCallback.reload();
                dialog.dismiss();
            }
        });
    }

    public static void alertDialogWeaponry(final Context context, final String[] row_data){
        final Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.input_alert_wpn);
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        dialog.getWindow().setLayout(1000, 1200);
        dialog.show();

        final TextView title = (TextView) dialog.findViewById(R.id.dialog_title);
        final Spinner input_1 = (Spinner) dialog.findViewById(R.id.input_1);
        final EditText input_2 = (EditText) dialog.findViewById(R.id.input_2);
        final EditText input_3 = (EditText) dialog.findViewById(R.id.input_3);
        final EditText input_4 = (EditText) dialog.findViewById(R.id.input_4);
        final Button cancelButton = (Button) dialog.findViewById(R.id.dialog_cancelButton);
        final Button okButton = (Button) dialog.findViewById(R.id.dialog_okButton);
        final Button changeButton = (Button) dialog.findViewById(R.id.dialog_changeButton);
        final Button deleteButton = (Button) dialog.findViewById(R.id.dialog_deleteButton);

        List<String> dataWpnC = DataManager.getListWpnC(context);
        if (dataWpnC.isEmpty()) dataWpnC.add("Категории отсутствуют");
        dataWpnC.add("Категория вооружения");
        ArrayAdapter<String> adapterWpnC = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_dropdown_item, dataWpnC) {

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {

                View v = super.getView(position, convertView, parent);
                if (position == getCount()) {
                    ((TextView)v.findViewById(android.R.id.text1)).setText("");
                    ((TextView)v.findViewById(android.R.id.text1)).setHint(getItem(getCount())); //"Hint to be displayed"
                }
                return v;
            }

            @Override
            public int getCount() {
                return super.getCount()-1; // you dont display last item. It is used as hint.
            }

        };
        adapterWpnC.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        input_1.setAdapter(adapterWpnC);
        input_1.setSelection(adapterWpnC.getCount()); //display hint

        if (row_data != null){
            title.setText(R.string.change_row);
            okButton.setVisibility(View.GONE);
            changeButton.setVisibility(View.VISIBLE);
            deleteButton.setVisibility(View.VISIBLE);
            if (row_data[0] != null) input_1.setSelection(dataWpnC.indexOf(row_data[0]+" - "+row_data[1]));
            else input_1.setSelection(adapterWpnC.getCount());
            input_2.setText(row_data[2]);
            input_3.setText(row_data[3]);
            input_4.setText(row_data[4]);
        }
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (input_1.getSelectedItem().toString().equalsIgnoreCase("Категория вооружения")
                        || input_1.getSelectedItem().toString().equalsIgnoreCase("Категории отсутствуют")){
                    Toast.makeText(context, "Вы не выбрали категорию вооружения!", Toast.LENGTH_SHORT).show();
                } else
                if (input_2.getText().toString().isEmpty()){
                    input_2.setError("Заполните поле");
                } else
                if (input_3.getText().toString().isEmpty()){
                    input_3.setError("Заполните поле");
                } else
                if (input_4.getText().toString().isEmpty()){
                    input_4.setError("Заполните поле");
                } else {
                    ContentValues cv = new ContentValues();
                    String wpnC = DataManager.getRowListWpnC(context, input_1.getSelectedItemPosition());
                    cv.put(DatabaseHelper.COLUMN_WEAPONRY_CATEGORY_ID, wpnC);
                    cv.put(DatabaseHelper.COLUMN_WEAPONRY_ID, input_2.getText().toString());
                    cv.put(DatabaseHelper.COLUMN_NAME, input_3.getText().toString());
                    cv.put(DatabaseHelper.COLUMN_ATT, input_4.getText().toString());
                    DataManager.addDataWpn(context, cv);
                    updateCallback.reload();
                    dialog.dismiss();
                }
            }
        });
        changeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (input_1.getSelectedItem().toString().equalsIgnoreCase("Категория вооружения")
                        || input_1.getSelectedItem().toString().equalsIgnoreCase("Категории отсутствуют")){
                    Toast.makeText(context, "Вы не выбрали категорию вооружения!", Toast.LENGTH_SHORT).show();
                } else
                if (input_2.getText().toString().isEmpty()){
                    input_2.setError("Заполните поле");
                } else
                if (input_3.getText().toString().isEmpty()){
                    input_3.setError("Заполните поле");
                } else
                if (input_4.getText().toString().isEmpty()){
                    input_4.setError("Заполните поле");
                } else {
                    ContentValues cv = new ContentValues();
                    String wpnC = DataManager.getRowListWpnC(context, input_1.getSelectedItemPosition());
                    cv.put(DatabaseHelper.COLUMN_WEAPONRY_CATEGORY_ID, wpnC);
                    cv.put(DatabaseHelper.COLUMN_WEAPONRY_ID, input_2.getText().toString());
                    cv.put(DatabaseHelper.COLUMN_NAME, input_3.getText().toString());
                    cv.put(DatabaseHelper.COLUMN_ATT, input_4.getText().toString());
                    DataManager.changeDataWpn(context, cv, row_data[2]);
                    updateCallback.reload();
                    dialog.dismiss();
                }
            }
        });
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DataManager.deleteDataWpn(context, row_data[2]);
                updateCallback.reload();
                dialog.dismiss();
            }
        });
    }

    public static void alertDialogBuilding(final Context context, final String[] row_data){
        final Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.input_alert_blg);
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        dialog.getWindow().setLayout(1000, 1200);
        dialog.show();

        final TextView title = (TextView) dialog.findViewById(R.id.dialog_title);
        final Spinner input_1 = (Spinner) dialog.findViewById(R.id.input_1);
        final EditText input_2 = (EditText) dialog.findViewById(R.id.input_2);
        final EditText input_3 = (EditText) dialog.findViewById(R.id.input_3);
        final EditText input_4 = (EditText) dialog.findViewById(R.id.input_4);
        final Button cancelButton = (Button) dialog.findViewById(R.id.dialog_cancelButton);
        final Button okButton = (Button) dialog.findViewById(R.id.dialog_okButton);
        final Button changeButton = (Button) dialog.findViewById(R.id.dialog_changeButton);
        final Button deleteButton = (Button) dialog.findViewById(R.id.dialog_deleteButton);

        List<String> dataMU = DataManager.getListMU(context);
        if (dataMU.isEmpty()) dataMU.add("Военные части отсутствуют");
        dataMU.add("Военная часть");
        ArrayAdapter<String> adapterMU = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_dropdown_item, dataMU) {

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {

                View v = super.getView(position, convertView, parent);
                if (position == getCount()) {
                    ((TextView)v.findViewById(android.R.id.text1)).setText("");
                    ((TextView)v.findViewById(android.R.id.text1)).setHint(getItem(getCount())); //"Hint to be displayed"
                }
                return v;
            }

            @Override
            public int getCount() {
                return super.getCount()-1; // you dont display last item. It is used as hint.
            }

        };
        adapterMU.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        input_1.setAdapter(adapterMU);
        input_1.setSelection(adapterMU.getCount()); //display hint

        if (row_data != null){
            title.setText(R.string.change_row);
            okButton.setVisibility(View.GONE);
            changeButton.setVisibility(View.VISIBLE);
            deleteButton.setVisibility(View.VISIBLE);
            input_1.setSelection(dataMU.indexOf(row_data[0]+" - "+row_data[1]+" - "+row_data[2]));
            input_2.setText(row_data[3]);
            input_3.setText(row_data[4]);
            input_4.setText(row_data[5]);
        }
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (input_1.getSelectedItem().toString().equalsIgnoreCase("Военная часть")
                        || input_1.getSelectedItem().toString().equalsIgnoreCase("Военные части отсутствуют")){
                    Toast.makeText(context, "Вы не выбрали военную часть!", Toast.LENGTH_SHORT).show();
                } else
                if (input_2.getText().toString().isEmpty()){
                    input_2.setError("Заполните поле");
                } else
                if (input_3.getText().toString().isEmpty()){
                    input_3.setError("Заполните поле");
                } else
                if (input_4.getText().toString().isEmpty()){
                    input_4.setError("Заполните поле");
                } else {
                    ContentValues cv = new ContentValues();
                    String[] mu = DataManager.getRowListMU(context, input_1.getSelectedItemPosition());
                    cv.put(DatabaseHelper.COLUMN_MILITARY_DISTRICT_ID, mu[0]);
                    cv.put(DatabaseHelper.COLUMN_DISLOCATION_PLACE_ID, mu[1]);
                    cv.put(DatabaseHelper.COLUMN_MILITARY_UNIT_ID, mu[2]);
                    cv.put(DatabaseHelper.COLUMN_BUILDING_ID, input_2.getText().toString());
                    cv.put(DatabaseHelper.COLUMN_NAME, input_3.getText().toString());
                    cv.put(DatabaseHelper.COLUMN_ATT, input_4.getText().toString());
                    DataManager.addDataBlg(context, cv);
                    updateCallback.reload();
                    dialog.dismiss();
                }
            }
        });
        changeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (input_1.getSelectedItem().toString().equalsIgnoreCase("Военная часть")
                        || input_1.getSelectedItem().toString().equalsIgnoreCase("Военные части отсутствуют")){
                    Toast.makeText(context, "Вы не выбрали военную часть!", Toast.LENGTH_SHORT).show();
                } else
                if (input_2.getText().toString().isEmpty()){
                    input_2.setError("Заполните поле");
                } else
                if (input_3.getText().toString().isEmpty()){
                    input_3.setError("Заполните поле");
                } else
                if (input_4.getText().toString().isEmpty()){
                    input_4.setError("Заполните поле");
                } else {
                    ContentValues cv = new ContentValues();
                    String[] mu = DataManager.getRowListMU(context, input_1.getSelectedItemPosition());
                    cv.put(DatabaseHelper.COLUMN_MILITARY_DISTRICT_ID, mu[0]);
                    cv.put(DatabaseHelper.COLUMN_DISLOCATION_PLACE_ID, mu[1]);
                    cv.put(DatabaseHelper.COLUMN_MILITARY_UNIT_ID, mu[2]);
                    cv.put(DatabaseHelper.COLUMN_BUILDING_ID, input_2.getText().toString());
                    cv.put(DatabaseHelper.COLUMN_NAME, input_3.getText().toString());
                    cv.put(DatabaseHelper.COLUMN_ATT, input_4.getText().toString());
                    DataManager.changeDataBlg(context, cv, row_data[3]);
                    updateCallback.reload();
                    dialog.dismiss();
                }
            }
        });
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DataManager.deleteDataBlg(context, row_data[3]);
                updateCallback.reload();
                dialog.dismiss();
            }
        });
    }

    public static void alertDialogSubDis(final Context context, final String[] row_data){
        final Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.input_alert_sub_dis);
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        dialog.getWindow().setLayout(1000, 1000);
        dialog.show();

        final TextView title = (TextView) dialog.findViewById(R.id.dialog_title);
        final Spinner input_1 = (Spinner) dialog.findViewById(R.id.input_1);
        final EditText input_2 = (EditText) dialog.findViewById(R.id.input_2);
        final Button cancelButton = (Button) dialog.findViewById(R.id.dialog_cancelButton);
        final Button okButton = (Button) dialog.findViewById(R.id.dialog_okButton);
        final Button changeButton = (Button) dialog.findViewById(R.id.dialog_changeButton);
        final Button deleteButton = (Button) dialog.findViewById(R.id.dialog_deleteButton);

        List<String> dataBlg = DataManager.getListBlg(context);
        if (dataBlg.isEmpty()) dataBlg.add("Сооружения отсутствуют");
        dataBlg.add("ID Сооружения");
        ArrayAdapter<String> adapterBlg = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_dropdown_item, dataBlg) {

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {

                View v = super.getView(position, convertView, parent);
                if (position == getCount()) {
                    ((TextView)v.findViewById(android.R.id.text1)).setText("");
                    ((TextView)v.findViewById(android.R.id.text1)).setHint(getItem(getCount())); //"Hint to be displayed"
                }
                return v;
            }

            @Override
            public int getCount() {
                return super.getCount()-1; // you dont display last item. It is used as hint.
            }

        };
        adapterBlg.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        input_1.setAdapter(adapterBlg);
        input_1.setSelection(adapterBlg.getCount()); //display hint

        if (row_data != null){
            title.setText(R.string.change_row);
            okButton.setVisibility(View.GONE);
            changeButton.setVisibility(View.VISIBLE);
            deleteButton.setVisibility(View.VISIBLE);
            input_1.setSelection(dataBlg.indexOf(row_data[0]));
            input_2.setText(row_data[1]);
        }
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (input_1.getSelectedItem().toString().equalsIgnoreCase("ID Сооружения")
                        || input_1.getSelectedItem().toString().equalsIgnoreCase("Сооружения отсутствуют")){
                    Toast.makeText(context, "Вы не выбрали соружение!", Toast.LENGTH_SHORT).show();
                } else
                if (input_2.getText().toString().isEmpty()){
                    input_2.setError("Заполните поле");
                } else {
                    ContentValues cv = new ContentValues();
                    cv.put(DatabaseHelper.COLUMN_BUILDING_ID, input_1.getSelectedItem().toString());
                    cv.put(DatabaseHelper.COLUMN_SUBDIVISION_ID, input_2.getText().toString());
                    DataManager.addDataSubDis(context, cv);
                    updateCallback.reload();
                    dialog.dismiss();
                }
            }
        });
        changeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (input_1.getSelectedItem().toString().equalsIgnoreCase("ID Сооружения")
                        || input_1.getSelectedItem().toString().equalsIgnoreCase("Сооружения отсутствуют")){
                    Toast.makeText(context, "Вы не выбрали соружение!", Toast.LENGTH_SHORT).show();
                } else
                if (input_2.getText().toString().isEmpty()){
                    input_2.setError("Заполните поле");
                } else {
                    ContentValues cv = new ContentValues();
                    cv.put(DatabaseHelper.COLUMN_BUILDING_ID, input_1.getSelectedItem().toString());
                    cv.put(DatabaseHelper.COLUMN_SUBDIVISION_ID, input_2.getText().toString());
                    DataManager.changeDataSubDis(context, cv, row_data[1]);
                    updateCallback.reload();
                    dialog.dismiss();
                }
            }
        });
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DataManager.deleteDataSubDis(context, row_data[1]);
                updateCallback.reload();
                dialog.dismiss();
            }
        });
    }

    public static void alertDialogSpcA(final Context context, final String[] row_data, final String[] mlt){
        final Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.input_alert_spc_a);
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        dialog.getWindow().setLayout(1000, 1000);
        dialog.show();

        final TextView title = (TextView) dialog.findViewById(R.id.dialog_title);
        final Spinner input_1 = (Spinner) dialog.findViewById(R.id.input_1);
        final Button cancelButton = (Button) dialog.findViewById(R.id.dialog_cancelButton);
        final Button okButton = (Button) dialog.findViewById(R.id.dialog_okButton);
        final Button changeButton = (Button) dialog.findViewById(R.id.dialog_changeButton);
        final Button deleteButton = (Button) dialog.findViewById(R.id.dialog_deleteButton);

        List<String> dataSpc = DataManager.getListSpc(context);
        if (dataSpc.isEmpty()) dataSpc.add("Специальности отсутствуют");
        dataSpc.add("ID Специальности");
        ArrayAdapter<String> adapterSpc = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_dropdown_item, dataSpc) {

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {

                View v = super.getView(position, convertView, parent);
                if (position == getCount()) {
                    ((TextView)v.findViewById(android.R.id.text1)).setText("");
                    ((TextView)v.findViewById(android.R.id.text1)).setHint(getItem(getCount())); //"Hint to be displayed"
                }
                return v;
            }

            @Override
            public int getCount() {
                return super.getCount()-1; // you dont display last item. It is used as hint.
            }

        };
        adapterSpc.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        input_1.setAdapter(adapterSpc);
        input_1.setSelection(adapterSpc.getCount()); //display hint

        if (row_data != null){
            title.setText(R.string.change_row);
            okButton.setVisibility(View.GONE);
            changeButton.setVisibility(View.VISIBLE);
            deleteButton.setVisibility(View.VISIBLE);
            input_1.setSelection(dataSpc.indexOf(row_data[1]));
        }
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (input_1.getSelectedItem().toString().equalsIgnoreCase("ID Специальности")
                        || input_1.getSelectedItem().toString().equalsIgnoreCase("Специальности отсутствуют")){
                    Toast.makeText(context, "Вы не выбрали специальность!", Toast.LENGTH_SHORT).show();
                } else {
                    ContentValues cv = new ContentValues();
                    cv.put(DatabaseHelper.COLUMN_MILITARY_DISTRICT_ID, mlt[0]);
                    cv.put(DatabaseHelper.COLUMN_MILITARY_ID, mlt[1]);
                    cv.put(DatabaseHelper.COLUMN_MILITARY_FIO, mlt[2]);
                    cv.put(DatabaseHelper.COLUMN_SPECIALITY_ID, input_1.getSelectedItem().toString());
                    DataManager.addDataSpcA(context, cv);
                    updateCallback.reload();
                    dialog.dismiss();
                }
            }
        });
        changeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (input_1.getSelectedItem().toString().equalsIgnoreCase("ID Специальности")
                        || input_1.getSelectedItem().toString().equalsIgnoreCase("Специальности отсутствуют")){
                    Toast.makeText(context, "Вы не выбрали специальность!", Toast.LENGTH_SHORT).show();
                } else {
                    ContentValues cv = new ContentValues();
                    cv.put(DatabaseHelper.COLUMN_MILITARY_DISTRICT_ID, mlt[0]);
                    cv.put(DatabaseHelper.COLUMN_MILITARY_ID, mlt[1]);
                    cv.put(DatabaseHelper.COLUMN_MILITARY_FIO, mlt[2]);
                    cv.put(DatabaseHelper.COLUMN_SPECIALITY_ID, input_1.getSelectedItem().toString());
                    DataManager.changeDataSpcA(context, cv, row_data[0]);
                    updateCallback.reload();
                    dialog.dismiss();
                }
            }
        });
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DataManager.deleteDataSpcA(context, row_data[0]);
                updateCallback.reload();
                dialog.dismiss();
            }
        });
    }

    public static void alertDialogEquA(final Context context, final String[] row_data, final String[] mu){
        final Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.input_alert_equ_a);
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        dialog.getWindow().setLayout(1000, 1000);
        dialog.show();

        final TextView title = (TextView) dialog.findViewById(R.id.dialog_title);
        final Spinner input_1 = (Spinner) dialog.findViewById(R.id.input_1);
        final EditText input_2 = (EditText) dialog.findViewById(R.id.input_2);
        final Button cancelButton = (Button) dialog.findViewById(R.id.dialog_cancelButton);
        final Button okButton = (Button) dialog.findViewById(R.id.dialog_okButton);
        final Button changeButton = (Button) dialog.findViewById(R.id.dialog_changeButton);
        final Button deleteButton = (Button) dialog.findViewById(R.id.dialog_deleteButton);

        List<String> dataEqu = DataManager.getListEqu(context);
        if (dataEqu.isEmpty()) dataEqu.add("Техника отсутствует");
        dataEqu.add("Техника");
        ArrayAdapter<String> adapterEqu = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_dropdown_item, dataEqu) {

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {

                View v = super.getView(position, convertView, parent);
                if (position == getCount()) {
                    ((TextView)v.findViewById(android.R.id.text1)).setText("");
                    ((TextView)v.findViewById(android.R.id.text1)).setHint(getItem(getCount())); //"Hint to be displayed"
                }
                return v;
            }

            @Override
            public int getCount() {
                return super.getCount()-1; // you dont display last item. It is used as hint.
            }

        };
        adapterEqu.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        input_1.setAdapter(adapterEqu);
        input_1.setSelection(adapterEqu.getCount()); //display hint

        if (row_data != null){
            title.setText(R.string.change_row);
            okButton.setVisibility(View.GONE);
            changeButton.setVisibility(View.VISIBLE);
            deleteButton.setVisibility(View.VISIBLE);
            input_1.setSelection(dataEqu.indexOf(row_data[1]+" - "+row_data[2]));
            input_2.setText(row_data[3]);
        }
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (input_1.getSelectedItem().toString().equalsIgnoreCase("Техника")
                        || input_1.getSelectedItem().toString().equalsIgnoreCase("Техника отсутствует")){
                    Toast.makeText(context, "Вы не выбрали технику!", Toast.LENGTH_SHORT).show();
                } else
                if (input_2.getText().toString().isEmpty()){
                    input_2.setError("Заполните поле");
                } else {
                    ContentValues cv = new ContentValues();
                    cv.put(DatabaseHelper.COLUMN_MILITARY_DISTRICT_ID, mu[0]);
                    cv.put(DatabaseHelper.COLUMN_DISLOCATION_PLACE_ID, mu[1]);
                    cv.put(DatabaseHelper.COLUMN_MILITARY_UNIT_ID, mu[2]);
                    String equ = DataManager.getRowListEqu(context, input_1.getSelectedItemPosition());
                    cv.put(DatabaseHelper.COLUMN_EQUIPMENT_ID, equ);
                    cv.put(DatabaseHelper.COLUMN_QUANTITY, input_2.getText().toString());
                    DataManager.addDataEquA(context, cv);
                    updateCallback.reload();
                    dialog.dismiss();
                }
            }
        });
        changeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (input_1.getSelectedItem().toString().equalsIgnoreCase("Техника")
                        || input_1.getSelectedItem().toString().equalsIgnoreCase("Техника отсутствует")){
                    Toast.makeText(context, "Вы не выбрали технику!", Toast.LENGTH_SHORT).show();
                } else
                if (input_2.getText().toString().isEmpty()){
                    input_2.setError("Заполните поле");
                } else {
                    ContentValues cv = new ContentValues();
                    cv.put(DatabaseHelper.COLUMN_MILITARY_DISTRICT_ID, mu[0]);
                    cv.put(DatabaseHelper.COLUMN_DISLOCATION_PLACE_ID, mu[1]);
                    cv.put(DatabaseHelper.COLUMN_MILITARY_UNIT_ID, mu[2]);
                    String equ = DataManager.getRowListEqu(context, input_1.getSelectedItemPosition());
                    cv.put(DatabaseHelper.COLUMN_EQUIPMENT_ID, equ);
                    cv.put(DatabaseHelper.COLUMN_QUANTITY, input_2.getText().toString());
                    DataManager.changeDataEquA(context, cv, row_data[0]);
                    updateCallback.reload();
                    dialog.dismiss();
                }
            }
        });
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DataManager.deleteDataEquA(context, row_data[0]);
                updateCallback.reload();
                dialog.dismiss();
            }
        });
    }

    public static void alertDialogWpnA(final Context context, final String[] row_data, final String[] mu){
        final Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.input_alert_wpn_a);
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        dialog.getWindow().setLayout(1000, 1000);
        dialog.show();

        final TextView title = (TextView) dialog.findViewById(R.id.dialog_title);
        final Spinner input_1 = (Spinner) dialog.findViewById(R.id.input_1);
        final EditText input_2 = (EditText) dialog.findViewById(R.id.input_2);
        final Button cancelButton = (Button) dialog.findViewById(R.id.dialog_cancelButton);
        final Button okButton = (Button) dialog.findViewById(R.id.dialog_okButton);
        final Button changeButton = (Button) dialog.findViewById(R.id.dialog_changeButton);
        final Button deleteButton = (Button) dialog.findViewById(R.id.dialog_deleteButton);

        List<String> dataWpn = DataManager.getListWpn(context);
        if (dataWpn.isEmpty()) dataWpn.add("Вооружение отсутствует");
        dataWpn.add("Вооружение");
        ArrayAdapter<String> adapterWpn = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_dropdown_item, dataWpn) {

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {

                View v = super.getView(position, convertView, parent);
                if (position == getCount()) {
                    ((TextView)v.findViewById(android.R.id.text1)).setText("");
                    ((TextView)v.findViewById(android.R.id.text1)).setHint(getItem(getCount())); //"Hint to be displayed"
                }
                return v;
            }

            @Override
            public int getCount() {
                return super.getCount()-1; // you dont display last item. It is used as hint.
            }

        };
        adapterWpn.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        input_1.setAdapter(adapterWpn);
        input_1.setSelection(adapterWpn.getCount()); //display hint

        if (row_data != null){
            title.setText(R.string.change_row);
            okButton.setVisibility(View.GONE);
            changeButton.setVisibility(View.VISIBLE);
            deleteButton.setVisibility(View.VISIBLE);
            input_1.setSelection(dataWpn.indexOf(row_data[1]+" - "+row_data[2]));
            input_2.setText(row_data[3]);
        }
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (input_1.getSelectedItem().toString().equalsIgnoreCase("Вооружение")
                        || input_1.getSelectedItem().toString().equalsIgnoreCase("Вооружение отсутствует")){
                    Toast.makeText(context, "Вы не выбрали вооружение!", Toast.LENGTH_SHORT).show();
                } else
                if (input_2.getText().toString().isEmpty()){
                    input_2.setError("Заполните поле");
                } else {
                    ContentValues cv = new ContentValues();
                    cv.put(DatabaseHelper.COLUMN_MILITARY_DISTRICT_ID, mu[0]);
                    cv.put(DatabaseHelper.COLUMN_DISLOCATION_PLACE_ID, mu[1]);
                    cv.put(DatabaseHelper.COLUMN_MILITARY_UNIT_ID, mu[2]);
                    String wpn = DataManager.getRowListWpn(context, input_1.getSelectedItemPosition());
                    cv.put(DatabaseHelper.COLUMN_WEAPONRY_ID, wpn);
                    cv.put(DatabaseHelper.COLUMN_QUANTITY, input_2.getText().toString());
                    DataManager.addDataWpnA(context, cv);
                    updateCallback.reload();
                    dialog.dismiss();
                }
            }
        });
        changeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (input_1.getSelectedItem().toString().equalsIgnoreCase("Вооружение")
                        || input_1.getSelectedItem().toString().equalsIgnoreCase("Вооружение отсутствует")){
                    Toast.makeText(context, "Вы не выбрали вооружение!", Toast.LENGTH_SHORT).show();
                } else
                if (input_2.getText().toString().isEmpty()){
                    input_2.setError("Заполните поле");
                } else {
                    ContentValues cv = new ContentValues();
                    cv.put(DatabaseHelper.COLUMN_MILITARY_DISTRICT_ID, mu[0]);
                    cv.put(DatabaseHelper.COLUMN_DISLOCATION_PLACE_ID, mu[1]);
                    cv.put(DatabaseHelper.COLUMN_MILITARY_UNIT_ID, mu[2]);
                    String wpn = DataManager.getRowListWpn(context, input_1.getSelectedItemPosition());
                    cv.put(DatabaseHelper.COLUMN_WEAPONRY_ID, wpn);
                    cv.put(DatabaseHelper.COLUMN_QUANTITY, input_2.getText().toString());
                    DataManager.changeDataWpnA(context, cv, row_data[0]);
                    updateCallback.reload();
                    dialog.dismiss();
                }
            }
        });
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DataManager.deleteDataWpnA(context, row_data[0]);
                updateCallback.reload();
                dialog.dismiss();
            }
        });
    }
}
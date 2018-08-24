package com.msp.mspshoubraapp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;

import com.msp.mspshoubraapp.R;

import java.util.ArrayList;

/**
 * Created by Laila Al Ashkar on 8/14/2018.
 */

public class TablesFragment extends Fragment implements AdapterView.OnItemClickListener {

    ImageView lectures, midterm, Final;
    Spinner spinnerGroup, spinnerSection;
    ArrayList<Integer> groups;
    ArrayList<Integer> sections;

    @Nullable
    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_tables, container, false);
        /*lectures = view.findViewById(R.id.lectures);
        midterm = view.findViewById(R.id.midterm);
        Final = view.findViewById(R.id.Final);

        lectures.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder alertDialog = new AlertDialog.Builder(getActivity());
                View alertDialogView = getLayoutInflater().inflate(R.layout.alertdialog, null);

                spinnerGroup = alertDialogView.findViewById(R.id.spinner_group);
                spinnerSection = alertDialogView.findViewById(R.id.spinner_section);

                groups = new ArrayList<>();
                sections = new ArrayList<>();

                for (int i = 1; i <= 50; i++) {
                    if (i <= 6) {
                        groups.add(i);
                    }
                    sections.add(i);
                }

                ArrayAdapter<Integer> groupAdapter = new ArrayAdapter<Integer>(TablesFragment.this.getActivity(), android.R.layout.simple_spinner_item, groups);
                groupAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinnerGroup.setAdapter(groupAdapter);

                ArrayAdapter<Integer> sectionAdapter = new ArrayAdapter<Integer>(TablesFragment.this.getActivity(), android.R.layout.simple_spinner_item, sections);
                sectionAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinnerSection.setAdapter(sectionAdapter);

                alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        Intent intent = new Intent(TablesFragment.this.getContext(), LecturesFragment.class);
                        intent.putExtra("group", spinnerGroup.getSelectedItem().toString());
                        intent.putExtra("section", spinnerSection.getSelectedItem().toString());
                        startActivity(intent);

                    }
                });

                alertDialog.setView(alertDialogView);
                alertDialog.create().show();


            }
        });*/
        return view;
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }
}

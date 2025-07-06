package com.example.workzydb;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

public class FragmentOne extends Fragment {

    private SharedViewModel viewModel;

    public FragmentOne() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_one, container, false);

        EditText editText = view.findViewById(R.id.input_text);
        Switch switchMode = view.findViewById(R.id.switch_mode);
        Spinner spinner = view.findViewById(R.id.spinner_options);
        Button buttonSend = view.findViewById(R.id.button_send);

        viewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);

        buttonSend.setOnClickListener(v -> {
            String text = editText.getText().toString();
            boolean isSwitchOn = switchMode.isChecked();
            String spinnerSelected = spinner.getSelectedItem().toString();

            String finalText = text + " | Mode: " + (isSwitchOn ? "ON" : "OFF") + " | Option: " + spinnerSelected;

            viewModel.setText(finalText);
            Toast.makeText(getContext(), "Data sent!", Toast.LENGTH_SHORT).show();
        });

        return view;
    }
}

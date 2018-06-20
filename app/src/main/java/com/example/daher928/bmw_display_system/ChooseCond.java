package com.example.daher928.bmw_display_system;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ChooseCond extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    final int SPINNERS_COUNT = 4;
    List<String> spinner_items_list;
    String[] spinner_chosen_items = {"nil","nil","nil","nil"};
    ArrayAdapter<String> adapter;
    Spinner[] spinners = new Spinner[SPINNERS_COUNT];
    int last_active_spinner = 0;
    Button[] rmv_buttons = new Button[SPINNERS_COUNT];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_cond);

        final ImageButton back_button = findViewById(R.id.back_button);
        back_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View button) {
                back_button.animate();
                startActivity(new Intent(ChooseCond.this, MainMenu.class));
            }

        });

        final ImageButton next_button = findViewById(R.id.next_button);
        next_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View button) {
                next_button.animate();
                startActivity(new Intent(ChooseCond.this, ChooseGrid.class));
            }

        });

        spinners[0] = findViewById(R.id.spinner0);
        spinners[1] = findViewById(R.id.spinner1);
        spinners[2] = findViewById(R.id.spinner2);
        spinners[3] = findViewById(R.id.spinner3);


        rmv_buttons[0] = null;
        rmv_buttons[1] = findViewById(R.id.remove_button1);
        rmv_buttons[2] = findViewById(R.id.remove_button2);
        rmv_buttons[3] = findViewById(R.id.remove_button3);

        rmv_buttons[1].setVisibility(View.INVISIBLE);
        rmv_buttons[2].setVisibility(View.INVISIBLE);
        rmv_buttons[3].setVisibility(View.INVISIBLE);

//        for(int i=1; i<SPINNERS_COUNT; i++){
//            final int finalI = i;
//            rmv_buttons[i].setOnClickListener(new View.OnClickListener() {
//                public void onClick(View button) {
//                    if (button.isPressed()) {
//                        spinners[finalI].setVisibility(View.INVISIBLE);
//                        last_active_spinner--;
//                        if(last_active_spinner!=0)
//                            rmv_buttons[last_active_spinner].setVisibility(View.VISIBLE);
//                        for(int j=1; j<SPINNERS_COUNT; j++){
//                            if(j!=last_active_spinner)
//                                rmv_buttons[j].setVisibility(View.INVISIBLE);
//                        }
//                        recover_selected(finalI);
//                    }
//                }
//            });
//        }


        String[] spinner_items = {"<Select>", "Condition1", "Condition2", "Condition3", "Condition4"};


        spinner_items_list = new ArrayList<>(Arrays.asList(spinner_items));
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, spinner_items_list);

        for (Spinner spinner : spinners) {
            spinner.setAdapter(adapter);
            spinner.setOnItemSelectedListener(this);
            if (spinner.getId() != R.id.spinner0)
                spinner.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
        Spinner curr_spinner = (Spinner) parent;
        final int s_idx = curr_spinner.getId() - spinners[0].getId();

        if (parent.getItemAtPosition(pos).toString() != "<Select>") {
            if(spinner_chosen_items[s_idx].equals("nil")) { //New Selection
                if (s_idx != SPINNERS_COUNT - 1) {
                    spinners[s_idx+1].setVisibility(View.VISIBLE);
                    last_active_spinner++;
                }
                spinner_chosen_items[s_idx] = parent.getItemAtPosition(pos).toString();
                spinner_items_list.remove(parent.getItemAtPosition(pos).toString());
            }else{  //Changed selection
                String recovered = spinner_chosen_items[s_idx];
                spinner_items_list.add(recovered);
                spinner_chosen_items[s_idx] = parent.getItemAtPosition(pos).toString();
                spinner_items_list.remove(parent.getItemAtPosition(pos).toString());
            }

            if(last_active_spinner < s_idx)
                last_active_spinner = s_idx;
            if(last_active_spinner >= 1) {
                rmv_buttons[last_active_spinner].setVisibility(View.VISIBLE);
                for(int i=1; i<SPINNERS_COUNT; i++){
                    if(i!=last_active_spinner)
                        rmv_buttons[i].setVisibility(View.INVISIBLE);
                }

            }
        }

//TODO:remove_button action
//        if(s_idx!=0){
//            for(int i=1; i<SPINNERS_COUNT; i++){
//                final int finalI = i;
//                Log.i("finalI=", Integer.toString(finalI));
//                rmv_buttons[finalI].setOnClickListener(new View.OnClickListener() {
//                    public void onClick(View button) {
//                        if (button.isPressed()) {
//                            spinners[finalI].setVisibility(View.INVISIBLE);
//                            last_active_spinner--;
//                            if(finalI-1!=0)
//                                rmv_buttons[finalI-1].setVisibility(View.VISIBLE);
//                            for(int j=1; j<SPINNERS_COUNT; j++){
//                                if(j!=finalI-1)
//                                    rmv_buttons[j].setVisibility(View.INVISIBLE);
//                            }
//                            recover_selected(s_idx);
//                        }
//                    }
//                });
//            }

//        }




    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    void recover_selected(int spinner_idx){
        String recovered = spinner_chosen_items[spinner_idx];
        spinner_items_list.add(recovered);
        //spinner_chosen_items[spinner_idx] = "nil";
    }
}

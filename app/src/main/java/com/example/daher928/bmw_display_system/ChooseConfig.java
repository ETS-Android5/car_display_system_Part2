package com.example.daher928.bmw_display_system;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.Map;

import static android.content.ContentValues.TAG;

public class ChooseConfig extends AppCompatActivity {

    String[] colorsArraySpinner = new String[] {
            "Blue", "Red", "Green", "Black", "Yellow"
    };

    int[] colors = new int[] {
            Color.BLUE, Color.RED, Color.GREEN, Color.BLACK, Color.YELLOW
    };

    public final String USER_EMAIL_PROPERTY = "userId";
    public final String USER_SENSORS_CONFIG = "sensorsConfigs";
    public final String LOG_COLLECTION_NAME = "bmwLog";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_configuration);

        View v = getWindow().getDecorView();
        ImageButton back_button = findViewById(R.id.back_button);
        ImageButton next_button = findViewById(R.id.next_button);

        if(AppTheme.theme == ThemeColor.BLUE) {
            v.setBackground(getResources().getDrawable(R.drawable.background_image, null));
            back_button.setImageDrawable(getResources().getDrawable(R.drawable.back_button, null));
            next_button.setImageDrawable(getResources().getDrawable(R.drawable.next_button, null));
        }else{
            v.setBackground(getResources().getDrawable(R.drawable.red_backround_image, null));
            back_button.setImageDrawable(getResources().getDrawable(R.drawable.red_back_button, null));
            next_button.setImageDrawable(getResources().getDrawable(R.drawable.red_next_button, null));
        }


        back_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View button) {
                startActivity(new Intent(ChooseConfig.this, ChooseCond.class));
            }

        });

        final int numSelectedSensors = AppState.selectedIds.size();

        final Sensor sensor1;
        final Sensor sensor2;
        final Sensor sensor3;
        final Sensor sensor4;

        //Config components
        sensor1 = AppState.getSensorFromId(AppState.selectedIds.get(0));
        sensor1.resetConfig();
        TextView sensor1txtView = findViewById(R.id.sensorName1);
        sensor1txtView.setText(sensor1.getName() + " [0x" + sensor1.getId() + "]");
        Spinner spinner1 =  findViewById(R.id.colorSpinner1);
        ArrayAdapter<String> adapter1 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, colorsArraySpinner);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(adapter1);
        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                sensor1.getConfig().setColor(colors[position]);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }
        });

        if(numSelectedSensors > 1){
            TableRow row2 = findViewById(R.id.row2);
            row2.setVisibility(View.VISIBLE);
            sensor2 = AppState.getSensorFromId(AppState.selectedIds.get(1));
            sensor2.resetConfig();
            TextView sensor2txtView = findViewById(R.id.sensorName2);
            sensor2txtView.setText(sensor2.getName() + " [0x" + sensor2.getId() + "]");
            Spinner spinner2 =  findViewById(R.id.colorSpinner2);
            ArrayAdapter<String> adapter2 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, colorsArraySpinner);
            adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner2.setAdapter(adapter2);
            spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                    sensor2.getConfig().setColor(colors[position]);
                }
                @Override
                public void onNothingSelected(AdapterView<?> parentView) {
                }
            });
        }

        if(numSelectedSensors > 2){
            TableRow row3 = findViewById(R.id.row3);
            row3.setVisibility(View.VISIBLE);
            sensor3 = AppState.getSensorFromId(AppState.selectedIds.get(2));
            sensor3.resetConfig();
            TextView sensor3txtView = findViewById(R.id.sensorName3);
            sensor3txtView.setText(sensor3.getName() + " [0x" + sensor3.getId() + "]");
            Spinner spinner3 =  findViewById(R.id.colorSpinner3);
            ArrayAdapter<String> adapter3 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, colorsArraySpinner);
            adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner3.setAdapter(adapter3);
            spinner3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                    sensor3.getConfig().setColor(colors[position]);
                }
                @Override
                public void onNothingSelected(AdapterView<?> parentView) {
                }
            });
        }

        if(numSelectedSensors > 3){
            TableRow row4 = findViewById(R.id.row4);
            row4.setVisibility(View.VISIBLE);
            sensor4 = AppState.getSensorFromId(AppState.selectedIds.get(3));
            sensor4.resetConfig();
            TextView sensor4txtView = findViewById(R.id.sensorName4);
            sensor4txtView.setText(sensor4.getName() + " [0x" + sensor4.getId() + "]");
            Spinner spinner4 =  findViewById(R.id.colorSpinner4);
            ArrayAdapter<String> adapter4 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, colorsArraySpinner);
            adapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner4.setAdapter(adapter4);
            spinner4.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                    sensor4.getConfig().setColor(colors[position]);
                }
                @Override
                public void onNothingSelected(AdapterView<?> parentView) {
                }
            });
        }

        //Configutarions (except color) will be set on next button click)
        next_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View button) {
                EditText res1text = findViewById(R.id.resolutionInputText1);
                String res1input = res1text.getText().toString();

                Map<String, Object> sensorsConfigNestedMap = new HashMap<>();

                if(!res1input.isEmpty())
                    sensor1.getConfig().setResolution(Double.parseDouble(res1input));

                EditText max1text = findViewById(R.id.maxValInputText1);
                String max1input = max1text.getText().toString();
                if(!max1input.isEmpty())
                    sensor1.getConfig().setMaxY(Double.parseDouble(max1input));

                Map<String, Object> map1 = sensor1.getConfig().sensorConfigMap();
                map1.put("order",1);
                sensorsConfigNestedMap.put(sensor1.getId(), map1);

                if (numSelectedSensors>1){
                    Sensor sensor2 = AppState.getSensorFromId(AppState.selectedIds.get(1));
                    EditText res2text = findViewById(R.id.resolutionInputText2);
                    String res2input = res2text.getText().toString();
                    if(!res2input.isEmpty())
                        sensor2.getConfig().setResolution(Double.parseDouble(res2input));

                    EditText max2text = findViewById(R.id.maxValInputText2);
                    String max2input = max2text.getText().toString();
                    if(!max2input.isEmpty())
                        sensor2.getConfig().setMaxY(Double.parseDouble(max2input));

                    Map<String, Object> map2 = sensor2.getConfig().sensorConfigMap();
                    map2.put("order",2);
                    sensorsConfigNestedMap.put(sensor2.getId(), map2);

                }

                if (numSelectedSensors>2){
                    Sensor sensor3 = AppState.getSensorFromId(AppState.selectedIds.get(2));
                    EditText res3text = findViewById(R.id.resolutionInputText3);
                    String res3input = res3text.getText().toString();
                    if(!res3input.isEmpty())
                        sensor3.getConfig().setResolution(Double.parseDouble(res3input));

                    EditText max3text = findViewById(R.id.maxValInputText3);
                    String max3input = max3text.getText().toString();
                    if(!max3input.isEmpty())
                        sensor3.getConfig().setMaxY(Double.parseDouble(max3input));

                    Map<String, Object> map3 = sensor3.getConfig().sensorConfigMap();
                    map3.put("order",3);
                    sensorsConfigNestedMap.put(sensor3.getId(), map3);

                }

                if (numSelectedSensors>3){
                    Sensor sensor4 = AppState.getSensorFromId(AppState.selectedIds.get(3));
                    EditText res4text = findViewById(R.id.resolutionInputText4);
                    String res4input = res4text.getText().toString();
                    if(!res4input.isEmpty())
                        sensor4.getConfig().setResolution(Double.parseDouble(res4input));

                    EditText max4text = findViewById(R.id.maxValInputText4);
                    String max4input = max4text.getText().toString();
                    if(!max4input.isEmpty())
                        sensor4.getConfig().setMaxY(Double.parseDouble(max4input));

                    Map<String, Object> map4 = sensor4.getConfig().sensorConfigMap();
                    map4.put("order",4);
                    sensorsConfigNestedMap.put(sensor4.getId(), map4);

                }

                addConfigToFirebase(sensorsConfigNestedMap);

                Intent mIntent = new Intent(ChooseConfig.this, LiveConditions.class);
                mIntent.putExtra("FROM_ACTIVITY", "ChooseConfig");
                startActivity(mIntent);
            }
        });


    }

    private void addConfigToFirebase(Map<String, Object> sensorsConfigNestedMap) {

        String userId = FirebaseAuth.getInstance().getCurrentUser().getEmail();

        FirebaseFirestore.getInstance()
                .collection("Users")
                .document(userId)
                .collection("sensorsConfigs")
                .add(sensorsConfigNestedMap)
                .addOnCompleteListener(task -> {
                    if (!task.isSuccessful()) {
                        Log.d(TAG, "get failed with ", task.getException());
                    }
                });
    }


}

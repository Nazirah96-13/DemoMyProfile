package sg.edu.rp.c346.id17032457.demomyprofile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText etName;
    EditText etGPA;
    RadioGroup rgGender;
    Button btnSave;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etName = findViewById(R.id.editTextName);
        etGPA = findViewById(R.id.editTextGPA);
        rgGender = findViewById(R.id.radioGroupGender);
        btnSave = findViewById(R.id.buttonSave);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strName = etName.getText().toString();
                float gpa = Float.parseFloat(etGPA.getText().toString());
                int intGenderId = rgGender.getCheckedRadioButtonId();

                SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
                SharedPreferences.Editor prefEdit = prefs.edit();

                prefEdit.putString("name", strName);
                prefEdit.putFloat("gpa", gpa);
                prefEdit.putInt("genderId", intGenderId);
            }
        });


    }

    @Override
    protected void onPause() {
        super.onPause();

        String strName = etName.getText().toString();
        float gpa = Float.parseFloat(etGPA.getText().toString());
        int intGenderId = rgGender.getCheckedRadioButtonId();
        //Step 1a: Obtain an instance of the SharedPreferences
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        //Step 1b: Obtain an instance of the SharedPreference Editor for update

        SharedPreferences.Editor prefEdit = prefs.edit();

        //Step 1c: Add the key-value pair
        prefEdit.putString("name", strName);
        prefEdit.putFloat("gpa", gpa);
        prefEdit.putInt("genderId", intGenderId);

        prefEdit.commit();


    }


    @Override
    protected void onResume() {
        super.onResume();
        //Step 2a: Obtain an instance of the SharedPreferences
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        //Step 2b: Obtain an instance of the SharedPreference Editor for update
        String strName = prefs.getString("name","John");
        float gpa = prefs.getFloat("gpa", 0);

        int intGenderId = prefs.getInt("genderId",R.id.radioButtonGenderMale);
        //Step 2c: Add the key-value pair
        etName.setText(strName);
        etGPA.setText(gpa + "");
        rgGender.check(intGenderId);
    }
}

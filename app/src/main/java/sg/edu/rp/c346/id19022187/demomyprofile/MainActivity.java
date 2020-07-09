package sg.edu.rp.c346.id19022187.demomyprofile;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText etName, etGPA;
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
                save();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        // Step 2a: Obtain an instance of the SharedPreferences
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);

        // Step 2b: Retrieve the saved data with the key "greeting" from the SharedPreferences object
        String name = prefs.getString("name", "No name");
        float gpa = prefs.getFloat("gpa", 0);
        int intGenderId = prefs.getInt("genderId",R.id.radioButtonGenderMale);

        // Step 2c: Update the UI element with the view
        etName.setText(name);
        etGPA.setText(gpa+"");
        rgGender.check(intGenderId);

    }

    @Override
    protected void onPause() {
        super.onPause();
        save();
    }

    protected void save(){
        Toast.makeText(MainActivity.this, "Saving", Toast.LENGTH_LONG).show();
        // Step 1a: Obtain an instance of the SharedPreferences
        String strName = etName.getText().toString();
        float floatGPA = Float.parseFloat(etGPA.getText().toString());
        int intGenderId = rgGender.getCheckedRadioButtonId();

        // Step 1c: Obtain an instance of the SHaredPreference Editor for update later
        SharedPreferences prefs = androidx.preference.PreferenceManager.getDefaultSharedPreferences(this);

        // Step 1c: Obtain an instance of the SHaredPreference Editor for update later
        SharedPreferences.Editor prefEdit = prefs.edit();

        // Step 1d: Add the key-value pair
        prefEdit.putString("name", strName);
        prefEdit.putFloat("gpa", floatGPA);
        prefEdit.putInt("genderId", intGenderId);

        // Step 1e: Call commit() to save the changes into SHaredPreferences
        prefEdit.commit();
    }


}
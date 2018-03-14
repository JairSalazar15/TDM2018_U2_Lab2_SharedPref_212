package tdm2018.ittepic.edu.tdm2018_u2_lab2_sharedpref_212;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Toast;

import static android.content.Context.MODE_PRIVATE;

public class MainActivity extends Activity implements RadioGroup.OnCheckedChangeListener, AdapterView.OnItemSelectedListener {
    private String email;
    private String celular;
    private String direccion;
    private String fecha;
    private String hora;
    private String platillos;
    private String postres;

    private String gender;
    private String hobbies;
    private String zodiac;
    private SeekBar simpleSeekBar;
    private Integer numeroseek=0;
    public static final String STORAGE_NAME = "MySharedPreferences";;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        email = ((EditText)findViewById(R.id.editTextNombre)).getText().toString();
        celular = ((EditText)findViewById(R.id.editTextNum)).getText().toString();
        direccion = ((EditText)findViewById(R.id.editTextDir)).getText().toString();
        fecha = ((EditText)findViewById(R.id.editTextFecha)).getText().toString();
        hora = ((EditText)findViewById(R.id.editTextInicio)).getText().toString();
        platillos = ((EditText)findViewById(R.id.editTextPlatillos)).getText().toString();
        postres = ((EditText)findViewById(R.id.editTextPostres)).getText().toString();
        simpleSeekBar=((SeekBar)findViewById(R.id.seekBar));

        simpleSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int progressChangedValue = numeroseek;

            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                progressChangedValue = progress;
            }

            public void onStartTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }

            public void onStopTrackingTouch(SeekBar seekBar) {
                Toast.makeText(MainActivity.this, "Numero de Meseros:" + progressChangedValue,Toast.LENGTH_SHORT).show();

                numeroseek=progressChangedValue;
            }
        });

        gender = "";
        hobbies = "";
        zodiac = "";
    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        int radioButtonId = radioGroup.getCheckedRadioButtonId();
        RadioButton radioButton = (RadioButton)radioGroup.findViewById(radioButtonId);
        gender = radioButton.getText().toString();
    }
    // Add other methods

    public void onCheckboxClicked(View view) {

        CheckBox chkJogging = (CheckBox) findViewById(R.id.checkBoxBasic);
        CheckBox chkCoding = (CheckBox) findViewById(R.id.checkBoxLuj);

        StringBuilder sb = new StringBuilder();

        if (chkJogging.isChecked()) {
            sb.append(", " + chkJogging.getText());
        }

        if (chkCoding.isChecked()) {
            sb.append(", " + chkCoding.getText());
        }


        if (sb.length() > 0) { // No toast if the string is empty
            // Remove the first comma
            hobbies = sb.deleteCharAt(sb.indexOf(",")).toString();
        } else {
            hobbies = "";
        }
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        zodiac = parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    public void save(View view) {
        // Capture email input
        email = ((EditText)findViewById(R.id.editTextNombre)).getText().toString();
        celular = ((EditText)findViewById(R.id.editTextNum)).getText().toString();
        direccion = ((EditText)findViewById(R.id.editTextDir)).getText().toString();
        fecha = ((EditText)findViewById(R.id.editTextFecha)).getText().toString();
        hora = ((EditText)findViewById(R.id.editTextInicio)).getText().toString();
        platillos = ((EditText)findViewById(R.id.editTextPlatillos)).getText().toString();
        postres = ((EditText)findViewById(R.id.editTextPostres)).getText().toString();


        SharedPreferences sharedPreferences = getSharedPreferences(STORAGE_NAME, Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("email", email);
        editor.putString("celular", celular);
        editor.putString("direccion", direccion);
        editor.putString("fecha", fecha);
        editor.putString("hora", hora);
        editor.putString("platillos", platillos);
        editor.putString("postres", postres);

        editor.putString("gender", gender);
        editor.putString("hobbies", hobbies);
        editor.putString("zodiac", zodiac);
        editor.putInt("numeseek", numeroseek);

        editor.apply();

        Toast.makeText(getApplicationContext(), "Datos Guardados", Toast.LENGTH_SHORT).show();

        // To add code to save data to storage later on
    }

    public void retrieve(View view) {
        SharedPreferences sharedPreferences = getSharedPreferences(STORAGE_NAME, Context.MODE_PRIVATE);

        email = sharedPreferences.getString("email", "");
        celular = sharedPreferences.getString("celular", "");
        direccion = sharedPreferences.getString("direccion", "");
        fecha = sharedPreferences.getString("fecha", "");
        hora = sharedPreferences.getString("hora", "");
        platillos = sharedPreferences.getString("platillos", "");
        postres = sharedPreferences.getString("postres", "");

        gender = sharedPreferences.getString("gender", "");
        hobbies = sharedPreferences.getString("hobbies", "");
        zodiac = sharedPreferences.getString("zodiac", "");
        numeroseek = sharedPreferences.getInt("numeseek",numeroseek);

        setupUI();
    }

    protected void setupUI(){
        ((EditText)findViewById(R.id.editTextNombre)).setText(email);
        ((EditText)findViewById(R.id.editTextNum)).setText(celular);
        ((EditText)findViewById(R.id.editTextDir)).setText(direccion);
        ((EditText)findViewById(R.id.editTextFecha)).setText(fecha);
        ((EditText)findViewById(R.id.editTextInicio)).setText(hora);
        ((EditText)findViewById(R.id.editTextPlatillos)).setText(platillos);
        ((EditText)findViewById(R.id.editTextPostres)).setText(postres);// Add code here

        ((SeekBar)findViewById(R.id.seekBar)).setProgress(numeroseek);

        //RadioButton radMale = (RadioButton)findViewById(R.id.radMale);
        //RadioButton radFemale = (RadioButton)findViewById(R.id.radFemale);

        CheckBox chkCoding = (CheckBox)findViewById(R.id.checkBoxBasic);
        CheckBox chkWriting = (CheckBox)findViewById(R.id.checkBoxLuj);


        chkCoding.setChecked(false);
        chkWriting.setChecked(false);


        if (hobbies.contains("Basica")) {
            chkCoding.setChecked(true);
        }

        if (hobbies.contains("Lujo")) {
            chkWriting.setChecked(true);
        }

    }
}

// IT19170176
// FERNANDO W.N.D
// CarMart Notices
package com.example.vehicleappnotices;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.regex.Pattern;


public class EmailValidator extends Activity {
    Button saveInfoBt;
    EditText pemailEt;
    TextView textviewMessage;


    private boolean mIsValid = false;

    public boolean isValid()
    {

        return mIsValid;
    }

    public static boolean isValidEmail(CharSequence email) {

        return email != null;
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_record);
        saveInfoBt = findViewById(R.id.add_notice);
        pemailEt = findViewById(R.id.email);

        saveInfoBt.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                String  getText = pemailEt.getText().toString();
                String Expn =
                        "^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]{1}|[\\w-]{2,}))@"
                                + "((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                                + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\."
                                + "([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                                + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])){1}|"
                                + "([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$";



                if (getText.matches(Expn) && getText.length() > 0) {
                    textviewMessage.setText("valid email");


                } else {
                    textviewMessage.setText("invalid email");
                }

            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
        });
    }
}

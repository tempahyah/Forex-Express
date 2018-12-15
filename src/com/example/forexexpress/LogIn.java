package com.example.forexexpress;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.content.Intent;
import android.widget.Button;
import android.view.View;


public class LogIn extends Activity {

	 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_log_in);
		Button Login_button = (Button) findViewById(R.id.Login_Button);
        Login_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent myIntent = new Intent(view.getContext(), MainActivity.class);
                startActivityForResult(myIntent, 0);
            }
        });     
        
	}
	public void reg(View v)
    {
        Intent i=new Intent();
        i.setClass(this,SignUp.class);
        startActivity(i);
    }

    public void forgot_password(View v)
    {
        Intent in=new Intent();
        in.setClass(this,ForgotPassword.class);
        startActivity(in);
    }

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.log_in, menu);
		return true;
	}
	

}

package com.example.book;

import java.util.HashMap;
import java.util.Map;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.service.LoginService;

public class login extends Activity {
	private Button button;
	private EditText editText1;
	private EditText editText2;
	private CheckBox checkBox1;
	private CheckBox checkBox2;
	private LoginService service;
	Map<String,?> map=null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);
		button =(Button)this.findViewById(R.id.login);
		editText1=(EditText)this.findViewById(R.id.editText1);
		editText2=(EditText)this.findViewById(R.id.editText2);
		checkBox1=(CheckBox)this.findViewById(R.id.c1);
		checkBox2=(CheckBox)this.findViewById(R.id.c2);
		service=new LoginService(this);
		map=service.getMessage("userinfo");		
		
		if(!map.isEmpty()&&map!=null){
			
			String username=map.get("username").toString().trim();
			String password=map.get("password").toString().trim();			
			editText1.setText(username);
			editText2.setText(password);
			checkBox1.setChecked((Boolean)map.get("RememberPswd"));
		}
		
		button.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String e1=editText1.getText().toString().trim();
				String e2=editText2.getText().toString().trim();
								
					if(e1.isEmpty()){
						Toast.makeText(login.this, "请输入用户名", 2).show();
					}else if(e2.isEmpty()){
						Toast.makeText(login.this, "请输入密码", 2).show();
					}else if(e1.equals("admin")&&e2.equals("123")){	
						Map<String,Object> map=new HashMap<String,Object>();
						map.put("username", e1);
						if(checkBox1.isChecked()){
							map.put("password", e2);
						}else{
							map.put("password", "");
						}						
						map.put("RememberPswd", checkBox1.isChecked());
						service.saveMessage("userinfo", map);
						Toast.makeText(login.this, "登陆成功", 2).show();
						Intent intent=new Intent(login.this,MainTabActivity.class);
						startActivity(intent);
					}else{
						Toast.makeText(login.this, "用户名或密码错误！", 2).show();
					}
				}
				
			
		});
		}

}

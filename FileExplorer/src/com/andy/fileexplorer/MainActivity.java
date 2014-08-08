package com.andy.fileexplorer;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import com.example.afilemanger.R;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends Activity {
	
	private List<String> items = null;
	private List<String> pathlist = null;
	private ListView listview;
	private final String rootpath = "/";
	private String originalpath;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		listview = (ListView) findViewById(R.id.frmfiles_listview);
		getFileDir(rootpath);
		
		listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			public void onItemClick(AdapterView<?> arg0, View arg1,
					int position, long arg3) {
				File file = new File(pathlist.get(position));
				if (file.isDirectory()) {
					try {
						getFileDir(file.getPath());
					} catch (Exception e) {
						getFileDir(file.getParent());
					}
				} else {
					originalpath = file.getPath().toLowerCase(Locale.getDefault());
					//onExit();
					Toast.makeText(MainActivity.this, originalpath, Toast.LENGTH_SHORT).show();
				}
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	/*private void onExit() {
		Intent intent = new Intent();
		intent.putExtra("filepath", originalpath);
		setResult(2, intent);
		finish();
	}*/

	private void getFileDir(String filepath) {
		items = new ArrayList<String>();
		pathlist = new ArrayList<String>();
		File sfile = new File(filepath);

		if (!rootpath.equals(filepath)) {
			items.add("back");
			pathlist.add(sfile.getParent());
		}

		File[] files = sfile.listFiles();
		for (File file : files) {
			items.add(file.getName());
			pathlist.add(file.getPath());
		}
		listview.setAdapter(new FileAdapter(this, items, pathlist));
	}

}

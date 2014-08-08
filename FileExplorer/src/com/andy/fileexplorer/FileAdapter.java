package com.andy.fileexplorer;

import java.io.File;
import java.util.List;
import java.util.Locale;

import com.example.afilemanger.R;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * FileAdapter
 * @author Administrator
 *
 */
public class FileAdapter extends BaseAdapter {

	private Bitmap otherfile, folder, back, txt, audio, img;
	private List<String> filenames;
	private List<String> filepaths;
	private Context mContext;
	//private LayoutInflater inflater;

	/**
	 * @param context
	 * @param items
	 * @param pathlist
	 */
	public FileAdapter(Context context, List<String> items,
			List<String> pathlist) {
		this.mContext = context;
		this.folder = BitmapFactory.decodeResource(context.getResources(), R.drawable.folder);
		this.back = BitmapFactory.decodeResource(context.getResources(), R.drawable.fileback);
		this.otherfile = BitmapFactory.decodeResource(context.getResources(), R.drawable.type_file);
		this.txt = BitmapFactory.decodeResource(context.getResources(), R.drawable.type_txt);
		this.audio = BitmapFactory.decodeResource(context.getResources(), R.drawable.type_audio);
		this.img = BitmapFactory.decodeResource(context.getResources(), R.drawable.type_img);
		this.filenames = items;
		this.filepaths = pathlist;
	}

	public int getCount() {
		return filenames.size();
	}

	public Object getItem(int position) {
		return filenames.get(position);
	}

	public long getItemId(int position) {
		return position;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		/**
		 * 传统写法
		 */
		/*ViewHolder holder;
		if (null == convertView) {
			inflater = (LayoutInflater) this.wappcontext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = inflater.inflate(R.layout.frmfiles_row, null);
			holder = new ViewHolder();
			holder.iconView = (ImageView) convertView.findViewById(R.id.frmfiles_icon);
			holder.nameView = (TextView) convertView.findViewById(R.id.frmfiles_name);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		File file = new File(filepaths.get(position).toString());
		if (filenames.get(position).toString().equals("back")) {
			holder.iconView.setImageBitmap(back);
			holder.nameView.setText("Go back");
		} else {
			String filename = file.getName().toLowerCase(Locale.getDefault());
			if (file.isDirectory()) {
				holder.iconView.setImageBitmap(folder);
			} else {
				holder.iconView.setImageBitmap(filetype);
			}
			holder.nameView.setText(file.getName());
		}
		return convertView;*/
		
		/**
		 * 优化写法
		 */
		if (convertView == null) {
	        convertView = LayoutInflater.from(mContext)
	          .inflate(R.layout.frmfiles_row, parent, false);
	    }

	    ImageView iconView = ViewHolder.get(convertView, R.id.frmfiles_icon);
	    TextView nameView = ViewHolder.get(convertView, R.id.frmfiles_name);

	    File file = new File(filepaths.get(position).toString());
		if (filenames.get(position).toString().equals("back")) {
			iconView.setImageBitmap(back);
			nameView.setText("Back");
		} else {
			String filename = file.getName().toLowerCase(Locale.getDefault());
			if (file.isDirectory()) {
				iconView.setImageBitmap(folder);
			} else if(filename.contains(".jpg") || filename.contains(".png")){
				iconView.setImageBitmap(img);
			} else if(filename.contains(".txt")){
				iconView.setImageBitmap(txt);
			} else if(filename.contains(".mp3")){
				iconView.setImageBitmap(audio);
			} else {
				iconView.setImageBitmap(otherfile);
			}
			// …… and all other file types
			nameView.setText(file.getName());
		}

	    return convertView;
	}

	/**
	 * 传统写法的ViewHolder
	 */
	/*static class ViewHolder {
		ImageView iconView;
		TextView nameView;
	}*/
}

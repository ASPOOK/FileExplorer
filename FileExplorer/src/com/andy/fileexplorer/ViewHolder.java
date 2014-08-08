package com.andy.fileexplorer;

import android.util.SparseArray;
import android.view.View;

public class ViewHolder {
	
	private ViewHolder() {
		
	}

	@SuppressWarnings("unchecked")
	public static <T extends View> T get(View view, int id) {
		SparseArray<View> viewHolder = (SparseArray<View>) view.getTag();
		if (viewHolder == null) {
			viewHolder = new SparseArray<View>();
			view.setTag(viewHolder);
		}
		View childView = viewHolder.get(id);
		if (childView == null) {
			childView = view.findViewById(id);
			viewHolder.put(id, childView);
		}
		
		return (T) childView;
		
	}
	
	/**
	 * 该类在Adapter的getView中的调用方法，示例如下：
	 */
	/*@Override
	public View getView(int position, View convertView, ViewGroup parent) {

	    if (convertView == null) {
	        convertView = LayoutInflater.from(context)
	          .inflate(R.layout.banana_phone, parent, false);
	    }

	    ImageView imgView = ViewHolder.get(convertView, R.id.img);
	    TextView txtView = ViewHolder.get(convertView, R.id.text);
	    
	    txtView.setText("some text");
	    imgView.setImageResource(imgresource);

	    return convertView;
	}*/
}

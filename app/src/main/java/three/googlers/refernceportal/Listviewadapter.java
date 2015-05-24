package three.googlers.refernceportal;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class Listviewadapter extends BaseAdapter{
	Activity context;
	String name[];
	String number[];
	String profile[];
	String xyz[];
	public Listviewadapter(Activity context, String[] name, String[] number, String[] profile,String[] xyz) {
		super();
		this.context = context;
		this.name = name;
		this.number = number;
		this.profile = profile;
		this.xyz=xyz;
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return name.length	;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}
	private class ViewHolder {
        TextView textView1;
        TextView textView2;
        TextView textView3;
        TextView textView4;

	}
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
				ViewHolder holder;
				LayoutInflater inflater =  context.getLayoutInflater();

				if (convertView == null)
				{
					convertView = inflater.inflate(R.layout.singleview2, null);
					holder = new ViewHolder();
					
					holder.textView1 = (TextView) convertView.findViewById(R.id.textView1);
					holder.textView2 = (TextView) convertView.findViewById(R.id.textView2);
					holder.textView3 = (TextView) convertView.findViewById(R.id.textView3);
					holder.textView4 = (TextView) convertView.findViewById(R.id.textView4);
					convertView.setTag(holder);
				}
				else
				{
					holder = (ViewHolder) convertView.getTag();
				}

				holder.textView1.setText(name[position]);
				holder.textView2.setText(number[position]);
				
				holder.textView3.setText(xyz[position]);
				holder.textView4.setText(profile[position]);
			return convertView;
	}

}

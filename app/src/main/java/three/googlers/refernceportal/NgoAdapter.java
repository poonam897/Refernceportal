package three.googlers.refernceportal;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.widget.CursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import three.googlers.refernceportal.data.NgoContract;

public class NgoAdapter extends CursorAdapter {
    public NgoAdapter(Context context, Cursor c, int flags) {
        super(context, c, flags);
    }
    public static class ViewHolder {
        TextView txtTitle;
        TextView txtTitle2;
        TextView txtTitle3;
        TextView txtTitle4;

        public ViewHolder(View row) {
            txtTitle = (TextView)row.findViewById(R.id.textView1);
            txtTitle2 = (TextView)row.findViewById(R.id.textView2);
            txtTitle3 = (TextView)row.findViewById(R.id.textView3);
            txtTitle4 = (TextView)row.findViewById(R.id.textView4);

        }
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        int layoutId = R.layout.singleview1;
        View view = LayoutInflater.from(context).inflate(layoutId, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        view.setTag(viewHolder);
        return view;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        ViewHolder holder = (ViewHolder) view.getTag();
        holder.txtTitle.setText(cursor.getString(cursor.getColumnIndex(NgoContract.NgoEntry.COLUMN_NAME)));
        holder.txtTitle2.setText(cursor.getString(cursor.getColumnIndex(NgoContract.NgoEntry.COLUMN_ADDRESS)));
        holder.txtTitle3.setText(cursor.getString(cursor.getColumnIndex(NgoContract.NgoEntry.COLUMN_NGO)));
        holder.txtTitle4.setText(cursor.getString(cursor.getColumnIndex(NgoContract.NgoEntry.COLUMN_PHONE)));
    }
}

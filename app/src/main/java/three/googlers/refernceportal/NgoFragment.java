package three.googlers.refernceportal;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import three.googlers.refernceportal.data.NgoContract;
import three.googlers.refernceportal.data.PortalDbHelper;

public class NgoFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor> {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private PortalDbHelper mOpenHelper;
    private static final int NGO_LOADER = 0;
    private NgoAdapter mNgoAdapter;
    private ListView mListView;
    private int mPosition = ListView.INVALID_POSITION;
    private static final String SELECTED_KEY = "selected_position";
    private static final String[] NGO_COLUMNS = {
            NgoContract.NgoEntry._ID,
            NgoContract.NgoEntry.COLUMN_NAME,
            NgoContract.NgoEntry.COLUMN_ADDRESS,
            NgoContract.NgoEntry.COLUMN_NGO,
            NgoContract.NgoEntry.COLUMN_PHONE
    };
    @Override
    public void onSaveInstanceState(Bundle outState) {
        if (mPosition != ListView.INVALID_POSITION) {
            outState.putInt(SELECTED_KEY, mPosition);
        }
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        getLoaderManager().initLoader(NGO_LOADER, null, this);
        super.onActivityCreated(savedInstanceState);
    }
    public static NgoFragment newInstance(String param1, String param2) {
        NgoFragment fragment = new NgoFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }
    public NgoFragment() {
        // Required empty public constructor
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.ngodetails, container, false);
        mNgoAdapter = new NgoAdapter(getActivity(),null,0);
        mListView=(ListView) rootView.findViewById(R.id.listView1);
        mListView.setAdapter(mNgoAdapter);
        if (savedInstanceState != null && savedInstanceState.containsKey(SELECTED_KEY)) {
            // The listview probably hasn't even been populated yet.  Actually perform the
            // swapout in onLoadFinished.
            mPosition = savedInstanceState.getInt(SELECTED_KEY);
            mListView.smoothScrollToPosition(mPosition);
        }
        return rootView;
    }


 @Override
 public Loader<Cursor> onCreateLoader(int id, Bundle args) {
     String sortOrder = NgoContract.NgoEntry._ID + " ASC";
     return new CursorLoader(getActivity(),
             NgoContract.NgoEntry.CONTENT_URI,
             NGO_COLUMNS,
             null,
             null,
             sortOrder);
 }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        mNgoAdapter.swapCursor(null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        mNgoAdapter.swapCursor(data);
        if (mPosition != ListView.INVALID_POSITION) {
            mListView.smoothScrollToPosition(mPosition);
        }
    }
}
	



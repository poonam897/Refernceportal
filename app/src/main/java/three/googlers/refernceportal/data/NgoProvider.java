package three.googlers.refernceportal.data;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;

/**
 * Created by Agarwal on 16-05-2015.
 */
public class NgoProvider extends ContentProvider{
    static final int NGO = 100;
    static final int NGO_WITH_ID = 101;
    private static final String sNgoIdSelection = NgoContract.NgoEntry._ID + " = ? ";

    private static final UriMatcher sUriMatcher = buildUriMatcher();
    private PortalDbHelper mOpenHelper;
    private static final SQLiteQueryBuilder sponsorQueryBuilder;

    static{
        sponsorQueryBuilder = new SQLiteQueryBuilder();
        sponsorQueryBuilder.setTables(NgoContract.NgoEntry.TABLE_NAME);
    }

    public boolean onCreate() {
        mOpenHelper = new PortalDbHelper(getContext());
        return true;
    }
    public Cursor getNgoById(Uri uri, String[] projection, String sortOrder){
        long id = NgoContract.NgoEntry.getIdFromUri(uri);
        String[] selectionArgs = new String[]{Long.toString(id)};
        String selection=sNgoIdSelection;
        return sponsorQueryBuilder.query(mOpenHelper.getReadableDatabase(),
                projection,
                selection,
                selectionArgs,
                null,
                null,
                sortOrder
        );
    }
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        Cursor retCursor;
        switch (sUriMatcher.match(uri)) {
            case NGO: {
                retCursor = mOpenHelper.getReadableDatabase().query(
                        NgoContract.NgoEntry.TABLE_NAME,
                        new String[]{NgoContract.NgoEntry._ID, NgoContract.NgoEntry.COLUMN_NAME,
                                NgoContract.NgoEntry.COLUMN_ADDRESS, NgoContract.NgoEntry.COLUMN_NGO,
                                NgoContract.NgoEntry.COLUMN_PHONE},
                        selection,
                        selectionArgs,
                        null,
                        null,
                        sortOrder
                );
                break;
            }
            case NGO_WITH_ID:
                retCursor = getNgoById(uri, projection, sortOrder);
                break;
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }
        retCursor.setNotificationUri(getContext().getContentResolver(), uri);
        return retCursor;
    }

    @Override
    public String getType(Uri uri) {
        final int match = sUriMatcher.match(uri);

        switch (match) {
            case NGO:
                return NgoContract.NgoEntry.CONTENT_TYPE;
            case NGO_WITH_ID:
                return NgoContract.NgoEntry.CONTENT_ITEM_TYPE;
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        final SQLiteDatabase db = mOpenHelper.getWritableDatabase();
        final int match = sUriMatcher.match(uri);
        Uri returnUri;
        switch (match) {
            case NGO: {
                long _id = db.insert(NgoContract.NgoEntry.TABLE_NAME, null, values);
                if ( _id > 0 )
                    returnUri = NgoContract.NgoEntry.buildSponsorUri(_id);
                else
                    throw new android.database.SQLException("Failed to insert row into " + uri);
                break;
            }
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);
        db.close();
        return returnUri;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        final SQLiteDatabase db = mOpenHelper.getWritableDatabase();
        final int match = sUriMatcher.match(uri);
        int rowsDeleted;
        if(null==selection) selection="1";
        switch(match){
            case NGO:
                rowsDeleted = db.delete(NgoContract.NgoEntry.TABLE_NAME,selection,selectionArgs);
                break;
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }
        if(rowsDeleted!=0)
            getContext().getContentResolver().notifyChange(uri, null);
        return rowsDeleted;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        final SQLiteDatabase db = mOpenHelper.getWritableDatabase();
        final int match = sUriMatcher.match(uri);
        int rowsUpdated;
        switch(match){
            case NGO:
                rowsUpdated = db.update(NgoContract.NgoEntry.TABLE_NAME,values,selection,selectionArgs);
                break;
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }
        if(rowsUpdated!=0)
            getContext().getContentResolver().notifyChange(uri, null);
        return rowsUpdated;
    }

    static UriMatcher buildUriMatcher() {
        final UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);
        final String authority = NgoContract.CONTENT_AUTHORITY;
        matcher.addURI(authority, NgoContract.PATH_NGO,NGO);
        matcher.addURI(authority, NgoContract.PATH_NGO+"/#",NGO_WITH_ID);
        return matcher;
    }
}

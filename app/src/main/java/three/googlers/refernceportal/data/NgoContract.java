package three.googlers.refernceportal.data;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by Agarwal on 16-05-2015.
 */
public class NgoContract {
    public static final String CONTENT_AUTHORITY = "three.googlers.refernceportal";
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);
    public static final String PATH_NGO = NgoEntry.TABLE_NAME;

    public static final class NgoEntry implements BaseColumns {
        public static final String TABLE_NAME = "ngo";
        public static final String COLUMN_NAME="name";
        public static final String COLUMN_ADDRESS="address";
        public static final String COLUMN_NGO="ngo";
        public static final String COLUMN_PHONE="phone";

        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_NGO).build();
        public static final String CONTENT_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_NGO;
        public static final String CONTENT_ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE+ "/" + CONTENT_AUTHORITY + "/" + PATH_NGO;
        public static Uri buildSponsorUri(long id) {
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }
        public static long getIdFromUri(Uri uri) {
            return Long.parseLong(uri.getPathSegments().get(1));
        }
    }

}

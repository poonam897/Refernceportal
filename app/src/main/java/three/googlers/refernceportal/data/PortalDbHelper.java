package three.googlers.refernceportal.data;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Agarwal on 25-03-2015.
 */
public class PortalDbHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "ReferencePortal.db";
    public static final String TABLE_ELECTRICIAN = "electrician";
    public static final String TABLE_MAID = "maid";
    public static final String TABLE_GARDENER = "gardener";
    public static final String TABLE_NGO = NgoContract.NgoEntry.TABLE_NAME;
    public static final String TABLE_PLUMBER = "plumber";
    public static final String TABLE_WATCHMAN = "watchman";
    public static final String COLUMN_ID=NgoContract.NgoEntry._ID;
    public static final String COLUMN_NAME=NgoContract.NgoEntry.COLUMN_NAME;
    public static final String COLUMN_ADDRESS=NgoContract.NgoEntry.COLUMN_ADDRESS;
    public static final String COLUMN_NGO=NgoContract.NgoEntry.COLUMN_NGO;
    public static final String COLUMN_PHONE=NgoContract.NgoEntry.COLUMN_PHONE;
    public static final String TABLE_LOGIN="Login";
    public static final String COLUMN_PASSWORD="pass";
    public static final String COLUMN_USERNAME="user";
    public static Context mContext;
    private SQLiteDatabase database;
    public PortalDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        mContext = context;
        database = getWritableDatabase();
    }
    public Cursor getAllRecords(String table){
        return database.rawQuery("Select*from "+table,null);
    }
    public Cursor getRecordsById(String table,int id){
        return database.rawQuery("Select*from "+table+" where "+COLUMN_ID+" = "+id,null);
    }
    public int getNGOId(String user,String pass){
        Cursor c = database.rawQuery("Select*from "+TABLE_LOGIN+" where "+COLUMN_USERNAME
                +" = \'"+user+"\' and "+COLUMN_PASSWORD+" = \'"+pass+"\'",null);
        if(c.moveToFirst())
                return c.getInt(c.getColumnIndex(COLUMN_NGO));
        return -1;
    }
    public String getNGOById(int id){
        Cursor c =database.rawQuery("select*from "+TABLE_NGO+" where "+COLUMN_ID+"="+id,null);
        c.moveToFirst();
        return c.getString(c.getColumnIndex(COLUMN_NAME));
    }
    public Cursor getRecordsByNgo(String s){

        return database.rawQuery("select * from "+TABLE_PLUMBER+" where "+COLUMN_NGO+" = \'"+s
                +"\' union all select * from "+TABLE_GARDENER+" where "+COLUMN_NGO+" = \'"+s
                +"\' union all select * from "+TABLE_ELECTRICIAN+" where "+COLUMN_NGO+" = \'"+s
                +"\' union all select * from "+TABLE_MAID+" where "+COLUMN_NGO+" = \'"+s
                +"\' union all select * from "+TABLE_WATCHMAN+" where "+COLUMN_NGO+" = \'"+s+"\'"
                ,null);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NGO + "( " + COLUMN_ID
                + " NUMBER PRIMARY KEY, "+ COLUMN_NAME + " TEXT ," + COLUMN_ADDRESS + " TEXT, "
                + COLUMN_NGO + " TEXT, " + COLUMN_PHONE + " TEXT)");
        db.execSQL("INSERT INTO "+ TABLE_NGO +" values(0,\'Goonj Foundation\'," +
                "\'  W.E. Highway Road, Kashimira, Mira Road (E), Thane, Maharashtra- 401104  \'," +
                "\'Pratham Foundation\',\'022- 28453034, 8767434352\')");
        db.execSQL("INSERT INTO "+ TABLE_NGO +" values(1,\'Helpage India\',\'  Bailey Road, Patna-800014 \',null,\'0612 3260433\')");
        db.execSQL("INSERT INTO "+ TABLE_NGO +" values(2,\'Uday Foundation\',\'  Sheerra Road , Ajmer, Rajasthan- 100104  \',null,\'083- 28453034, 737384352\')");
        db.execSQL("INSERT INTO "+ TABLE_NGO +" values(3,\'Deepalaya Foundation\',\' Bailey Road, Kanpur-600014 \',null,\'012 3260433\')");
        db.execSQL("INSERT INTO "+ TABLE_NGO +" values(4,\'Lepra Foundation\',\'  Khar Road (E), Andheri, Maharashtra- 701104  \',null,\'033- 28453034, 8862824352\')");
        db.execSQL("INSERT INTO "+ TABLE_NGO +" values(5,\'Akshaaya Trust\',\'  W.E. Highway Road, Kashimira, chinchpokhali (w), borivali, Maharashtra- 401104  \',null,\'022- 28453034, 8767434352\')");
        db.execSQL("INSERT INTO "+ TABLE_NGO +" values(6,\'A smile Foundation\',\'  Bailey Road, Satna-800014 \',null,\'0612 3260433\')");
        db.execSQL("INSERT INTO "+ TABLE_NGO +" values(7,\'Udaan Welfare\',\'  Sheerra Road , Amer, Rajasthan- 100104  \',null,\'083- 28453034, 737384352\')");
        db.execSQL("INSERT INTO "+ TABLE_NGO +" values(8,\'Pratham\',\' Bailey Road, Ramnpur-600014 \',null,\'012 3260433\')");
        db.execSQL("INSERT INTO "+ TABLE_NGO +" values(9,\'Nanhi Kali\',\'  Khar Road (E), Churchgate Maharashtra- 701104  \',\'Pratham Foundation\',\'033- 28453034, 8862824352\')");

        db.execSQL("create table " + TABLE_WATCHMAN + "( " + COLUMN_ID
                + " NUMBER PRIMARY KEY, "+ COLUMN_NAME + " TEXT, " + COLUMN_ADDRESS + " TEXT, "
                + COLUMN_NGO + " TEXT, " + COLUMN_PHONE + " TEXT)");
        db.execSQL("INSERT INTO "+ TABLE_WATCHMAN +" values(0,\'Shithiz kumar\',\'  W.E. Highway Road, Kashimira, Mira Road (E), Thane, Maharashtra- 401104  " +
                "\',\' Goonj Foundation\',\'022- 28453034, 8767434352\')");
        db.execSQL("INSERT INTO "+ TABLE_WATCHMAN +" values(1,\'Rajkumar\',\'  Bailey Road, Patna-800014 \',\'Helpage India\',\'0612 3260433\')");
        db.execSQL("INSERT INTO "+ TABLE_WATCHMAN +" values(2,\'Shiahir\',\'  Sheerra Road , Ajmer, Rajasthan- 100104  \',\'Akshaaya Trust" +
                "\',\'083- 28453034, 737384352\')");
        db.execSQL("INSERT INTO "+ TABLE_WATCHMAN +" values(3,\'Nishit\',\' Bailey Road, Kanpur-600014 \'," +
                "\'Uday Foundation\',\'012 3260433\')");
        db.execSQL("INSERT INTO "+ TABLE_WATCHMAN +" values(5,\'Fazir mohamad\',\'  Bailey Road, Patna-800014 " +
                "\',\'Pratham Foundation\',\'0612 3260433\')");
        db.execSQL("INSERT INTO "+ TABLE_WATCHMAN +" values(6,\'Zakir khan\',\'  Sheerra Road , Ajmer, Rajasthan- 100104  " +
                "\',\'Akshaaya Trust\',\'083- 28453034, 737384352\')");
        db.execSQL("INSERT INTO "+ TABLE_WATCHMAN +" values(7,\'Dipanshu\',\' Bailey Road, Kanpur-600014 " +
                "\',\'Uday Foundatiation\',\'012 3260433\')");
        db.execSQL("INSERT INTO "+ TABLE_WATCHMAN +" values(8,\'Ronak\',\'  Khar Road (E), Andheri, Maharashtra- 701104  " +
                "\',\'Goonj Foundation\',\'033- 28453034, 8862824352\')");


        db.execSQL("create table " + TABLE_ELECTRICIAN + "( " + COLUMN_ID
                + " NUMBER PRIMARY KEY, "+ COLUMN_NAME + " TEXT, " + COLUMN_ADDRESS + " TEXT, "
                + COLUMN_NGO + " TEXT, " + COLUMN_PHONE + " TEXT)");
        db.execSQL("INSERT INTO "+ TABLE_ELECTRICIAN +" values(0,\'Shithiz kumar\',\'  W.E. Highway Road, Kashimira, Mira Road (E), Thane, Maharashtra- 401104  " +
                "\',\' Goonj Foundation\',\'022- 28453034, 8767434352\')");
        db.execSQL("INSERT INTO "+ TABLE_ELECTRICIAN +" values(1,\'Rajkumar\',\'  Bailey Road, Patna-800014 \',\'Helpage India\',\'0612 3260433\')");
        db.execSQL("INSERT INTO "+ TABLE_ELECTRICIAN +" values(2,\'Shiahir\',\'  Sheerra Road , Ajmer, Rajasthan- 100104  \',\'Akshaaya Trust" +
                "\',\'083- 28453034, 737384352\')");
        db.execSQL("INSERT INTO "+ TABLE_ELECTRICIAN +" values(3,\'Nishit\',\' Bailey Road, Kanpur-600014 \'," +
                "\'Uday Foundation\',\'012 3260433\')");
        db.execSQL("INSERT INTO "+ TABLE_ELECTRICIAN +" values(5,\'Fazir mohamad\',\'  Bailey Road, Patna-800014 " +
                "\',\'Pratham Foundation\',\'0612 3260433\')");
        db.execSQL("INSERT INTO "+ TABLE_ELECTRICIAN +" values(6,\'Zakir khan\',\'  Sheerra Road , Ajmer, Rajasthan- 100104  " +
                "\',\'Akshaaya Trust\',\'083- 28453034, 737384352\')");
        db.execSQL("INSERT INTO "+ TABLE_ELECTRICIAN +" values(7,\'Dipanshu\',\' Bailey Road, Kanpur-600014 " +
                "\',\'Uday Foundatiation\',\'012 3260433\')");
        db.execSQL("INSERT INTO "+ TABLE_ELECTRICIAN +" values(8,\'Ronak\',\'  Khar Road (E), Andheri, Maharashtra- 701104  " +
                "\',\'Goonj Foundation\',\'033- 28453034, 8862824352\')");

        db.execSQL("create table " + TABLE_GARDENER + "( " + COLUMN_ID
                + " NUMBER PRIMARY KEY, "+ COLUMN_NAME + " TEXT, " + COLUMN_ADDRESS + " TEXT, "
                + COLUMN_NGO + " TEXT, " + COLUMN_PHONE + " TEXT)");
        db.execSQL("INSERT INTO "+ TABLE_GARDENER +" values(0,\'Shithiz kumar\',\'  W.E. Highway Road, Kashimira, Mira Road (E), Thane, Maharashtra- 401104  " +
                "\',\' Goonj Foundation\',\'022- 28453034, 8767434352\')");
        db.execSQL("INSERT INTO "+ TABLE_GARDENER +" values(1,\'Rajkumar\',\'  Bailey Road, Patna-800014 \',\'Helpage India\',\'0612 3260433\')");
        db.execSQL("INSERT INTO "+ TABLE_GARDENER +" values(2,\'Shiahir\',\'  Sheerra Road , Ajmer, Rajasthan- 100104  \',\'Akshaaya Trust" +
                "\',\'083- 28453034, 737384352\')");
        db.execSQL("INSERT INTO "+ TABLE_GARDENER +" values(3,\'Nishit\',\' Bailey Road, Kanpur-600014 \'," +
                "\'Uday Foundation\',\'012 3260433\')");
        db.execSQL("INSERT INTO "+ TABLE_GARDENER +" values(5,\'Fazir mohamad\',\'  Bailey Road, Patna-800014 " +
                "\',\'Pratham Foundation\',\'0612 3260433\')");
        db.execSQL("INSERT INTO "+ TABLE_GARDENER+" values(6,\'Zakir khan\',\'  Sheerra Road , Ajmer, Rajasthan- 100104  " +
                "\',\'Akshaaya Trust\',\'083- 28453034, 737384352\')");
        db.execSQL("INSERT INTO "+ TABLE_GARDENER +" values(7,\'Dipanshu\',\' Bailey Road, Kanpur-600014 " +
                "\',\'Uday Foundatiation\',\'012 3260433\')");
        db.execSQL("INSERT INTO "+ TABLE_GARDENER +" values(8,\'Ronak\',\'  Khar Road (E), Andheri, Maharashtra- 701104  " +
                "\',\'Goonj Foundation\',\'033- 28453034, 8862824352\')");


        db.execSQL("create table " + TABLE_PLUMBER + "( " + COLUMN_ID
                + " NUMBER PRIMARY KEY, "+ COLUMN_NAME + " TEXT, " + COLUMN_ADDRESS + " TEXT, "
                + COLUMN_NGO + " TEXT, " + COLUMN_PHONE + " TEXT)");
        db.execSQL("INSERT INTO "+ TABLE_PLUMBER +" values(0,\'Shithiz kumar\',\'  W.E. Highway Road, Kashimira, Mira Road (E), Thane, Maharashtra- 401104  " +
                "\',\' Goonj Foundation\',\'022- 28453034, 8767434352\')");
        db.execSQL("INSERT INTO "+ TABLE_PLUMBER +" values(1,\'Rajkumar\',\'  Bailey Road, Patna-800014 \',\'Helpage India\',\'0612 3260433\')");
        db.execSQL("INSERT INTO "+ TABLE_PLUMBER +" values(2,\'Shiahir\',\'  Sheerra Road , Ajmer, Rajasthan- 100104  \',\'Akshaaya Trust" +
                "\',\'083- 28453034, 737384352\')");
        db.execSQL("INSERT INTO "+ TABLE_PLUMBER +" values(3,\'Nishit\',\' Bailey Road, Kanpur-600014 \'," +
                "\'Uday Foundation\',\'012 3260433\')");
        db.execSQL("INSERT INTO "+ TABLE_PLUMBER +" values(5,\'Fazir mohamad\',\'  Bailey Road, Patna-800014 " +
                "\',\'Pratham Foundation\',\'0612 3260433\')");
        db.execSQL("INSERT INTO "+ TABLE_PLUMBER +" values(6,\'Zakir khan\',\'  Sheerra Road , Ajmer, Rajasthan- 100104  " +
                "\',\'Akshaaya Trust\',\'083- 28453034, 737384352\')");
        db.execSQL("INSERT INTO "+ TABLE_PLUMBER +" values(7,\'Dipanshu\',\' Bailey Road, Kanpur-600014 " +
                "\',\'Uday Foundatiation\',\'012 3260433\')");
        db.execSQL("INSERT INTO "+ TABLE_PLUMBER +" values(8,\'Ronak\',\'  Khar Road (E), Andheri, Maharashtra- 701104  " +
                "\',\'Goonj Foundation\',\'033- 28453034, 8862824352\')");


        db.execSQL("create table " + TABLE_MAID + "( " + COLUMN_ID
                + " NUMBER PRIMARY KEY, "+ COLUMN_NAME + " TEXT, " + COLUMN_ADDRESS + " TEXT, "
                + COLUMN_NGO + " TEXT, " + COLUMN_PHONE + " TEXT)");
        db.execSQL("INSERT INTO "+ TABLE_MAID +" values(0,\'Rakhi\',\' Khar Road (E), Andheri, Maharashtra- 701104  \'," +
                "\'Helpage India\',\'033- 28453034, 8862824352\')");
        db.execSQL("INSERT INTO "+ TABLE_MAID +" values(1,\'Gopi\',\'  W.E. Highway Road, Kashimira, Mira Road (E), Thane, Maharashtra- 401104  " +
                "\',\'Uday Foundation\',\'022- 28453034, 8767434352\')");
        db.execSQL("INSERT INTO "+ TABLE_MAID +" values(2,\'Sita\',\'  Bailey Road, Patna-800014 \",\"Akshaaya Trust" +
                "\',null,\'0612 3260433\')");
        db.execSQL("INSERT INTO "+ TABLE_MAID +" values(3,\'Shishir\',\'Bailey Road, Kanpur-600014 " +
                "\',\'Helpage India\',\'083- 28453034, 737384352\')");
        db.execSQL("INSERT INTO "+ TABLE_MAID +" values(4,\'Savi\',\' Bailey Road, Kanpur-600014 \'," +
                "\'Pratham Foundation\',\'012 3260433\')");
        db.execSQL("INSERT INTO "+ TABLE_MAID +" values(5,\'Fazir mohamad\',\'  Bailey Road, Patna-800014 " +
                "\',\'Pratham Foundation\',\'0612 3260433\')");
        db.execSQL("INSERT INTO "+ TABLE_MAID +" values(6,\'Zakir khan\',\'  Sheerra Road , Ajmer, Rajasthan- 100104  " +
                "\',\'Akshaaya Trust\',\'083- 28453034, 737384352\')");
        db.execSQL("INSERT INTO "+ TABLE_MAID +" values(7,\'Dipanshu\',\' Bailey Road, Kanpur-600014 " +
                "\',\'Uday Foundatiation\',\'012 3260433\')");
        db.execSQL("INSERT INTO "+ TABLE_MAID +" values(8,\'Ronak\',\'  Khar Road (E), Andheri, Maharashtra- 701104  " +
                "\',\'Goonj Foundation\',\'033- 28453034, 8862824352\')");


        db.execSQL("create table " + TABLE_LOGIN + "( " + COLUMN_ID
                + " NUMBER PRIMARY KEY, "+ COLUMN_NGO + " NUMBER , " + COLUMN_USERNAME + " TEXT, " + COLUMN_PASSWORD + " TEXT)");
            db.execSQL("INSERT INTO "+ TABLE_LOGIN +" values(0,0," +
                    "\'fgh\'," +
                    "\'fgh\')");
            db.execSQL("INSERT INTO "+ TABLE_LOGIN +" values(1,1,\'abc\',\'abc\')");
            db.execSQL("INSERT INTO "+ TABLE_LOGIN +" values(2,2,\'zxc\',\'zxc\')");
            db.execSQL("INSERT INTO "+ TABLE_LOGIN +" values(3,3,\'plm\',\'plm\')");
            db.execSQL("INSERT INTO "+ TABLE_LOGIN +" values(4,4,\'123\',\'123\')");
            db.execSQL("INSERT INTO "+ TABLE_LOGIN +" values(5,7,\'qwerty\',\'qwerty\')");
            db.execSQL("INSERT INTO "+ TABLE_LOGIN +" values(6,5,\'asd\',\'asd\')");
            db.execSQL("INSERT INTO "+ TABLE_LOGIN +" values(7,6,\'iop\',\'iop\')");
            db.execSQL("INSERT INTO "+ TABLE_LOGIN +" values(8,8,\'789\',\'789\')");
            db.execSQL("INSERT INTO "+ TABLE_LOGIN +" values(9,9,\'456\',\'456\')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PLUMBER);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MAID);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ELECTRICIAN);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NGO);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_GARDENER);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_WATCHMAN);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_LOGIN);
        onCreate(db);
    }
}

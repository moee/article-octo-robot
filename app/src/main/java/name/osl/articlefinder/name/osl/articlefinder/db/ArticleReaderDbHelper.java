package name.osl.articlefinder.name.osl.articlefinder.db;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by michaelosl on 15/03/15.
 */
public class ArticleReaderDbHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_PATH = "/data/data/name.osl.articlefinder/databases/";
    public static final String DATABASE_NAME = "ArticleReader.db";
    public static final String LOG_TAG = ArticleReaderDbHelper.class.getCanonicalName();
    private Context context;
    private SQLiteDatabase database;

    public ArticleReaderDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

        this.context = context;

        if (false && checkDataBase()) {
            openDataBase();
        } else {
            this.getReadableDatabase();
            try {
                copyDataBase();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            this.close();
            openDataBase();
        }
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }


    private void copyDataBase() throws IOException {
        Log.d(LOG_TAG, "copyDataBase()");
        InputStream myInput = context.getAssets().open(DATABASE_NAME);
        String outFileName = DATABASE_PATH + DATABASE_NAME;
        OutputStream myOutput = new FileOutputStream(outFileName);

        byte[] buffer = new byte[1024];
        int length;
        while ((length = myInput.read(buffer)) > 0) {
            myOutput.write(buffer, 0, length);
        }

        myOutput.flush();
        myOutput.close();
        myInput.close();
        Log.d(LOG_TAG, "done with copy");
    }

    public void openDataBase() throws SQLException {
        String dbPath = DATABASE_PATH + DATABASE_NAME;
        database = SQLiteDatabase.openDatabase(dbPath, null, SQLiteDatabase.OPEN_READWRITE);
    }

    private boolean checkDataBase() {
        Log.d(this.LOG_TAG, "checkDataBase()");
        SQLiteDatabase checkDB = null;
        boolean exist = false;
        String dbPath = DATABASE_PATH + DATABASE_NAME;
        checkDB = SQLiteDatabase.openDatabase(dbPath, null,
                SQLiteDatabase.OPEN_READONLY);

        if (checkDB != null) {
            exist = true;
            checkDB.close();
        }
        Log.d(this.LOG_TAG, "exist = " + exist);
        return exist;
    }

}

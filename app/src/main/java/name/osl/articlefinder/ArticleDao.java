package name.osl.articlefinder;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import name.osl.articlefinder.name.osl.articlefinder.db.ArticleReaderContract;
import name.osl.articlefinder.name.osl.articlefinder.db.ArticleReaderDbHelper;


/**
 * Created by michaelosl on 15/03/15.
 */
public class ArticleDao {

    public static final String LOG_TAG = ArticleDao.class.getSimpleName();

    public Article getArticle(String word, Context context) {
        ArticleReaderDbHelper dbHelper = new ArticleReaderDbHelper(context);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String[] projection = {
                ArticleReaderContract.WordEntry._ID,
                ArticleReaderContract.WordEntry.COLUMN_NAME_ARTICLE,
                ArticleReaderContract.WordEntry.COLUMN_NAME_WORD
        };
        String sortOrder = ArticleReaderContract.WordEntry.COLUMN_NAME_WORD + " DESC";

        Log.d(LOG_TAG, "word = " + word);

        String[] selectionArgs = {word};

        Cursor c = db.query(
                ArticleReaderContract.WordEntry.TABLE_NAME,
                projection,
                ArticleReaderContract.WordEntry.COLUMN_NAME_WORD + " LIKE ?",
                selectionArgs,
                null,
                null,
                sortOrder
        );

        try {
            Log.d(LOG_TAG, "moveToFirst()");
            Log.d(LOG_TAG, "count is " + c.getCount());
            if (c.getColumnCount() != 1) {
                return null;
            }
            c.moveToFirst();
            int colIndex = c.getColumnIndex(ArticleReaderContract.WordEntry.COLUMN_NAME_ARTICLE);
            Log.d(LOG_TAG, "colIndex = " + colIndex);
            String value = c.getString(colIndex);
            Log.d(LOG_TAG, "value = " + value);
            return Article.valueOf(value);
        } catch (Exception e) {
            Log.e(LOG_TAG, "got exception", e);
            return null;
        }
    }

    enum Article {
        der, die, das
    }

}

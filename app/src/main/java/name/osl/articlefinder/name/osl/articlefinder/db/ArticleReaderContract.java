package name.osl.articlefinder.name.osl.articlefinder.db;

import android.provider.BaseColumns;

/**
 * Created by michaelosl on 15/03/15.
 */
public final class ArticleReaderContract {
    public ArticleReaderContract() {
    }

    public static abstract class WordEntry implements BaseColumns {
        public static final String TABLE_NAME = "words";
        public static final String COLUMN_NAME_WORD = "word";
        public static final String COLUMN_NAME_ARTICLE = "article";
    }
}

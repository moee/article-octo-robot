package name.osl.articlefinder;

/**
 * Created by michaelosl on 15/03/15.
 */
public class ArticleDao {
    public Article getArticle(String word) {
        return Article.das;
    }

    enum Article {
        der, die, das
    }

}

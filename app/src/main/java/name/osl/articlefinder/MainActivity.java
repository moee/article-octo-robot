package name.osl.articlefinder;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        private ArticleDao articleDao = new ArticleDao();

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            final View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            final Button searchButton = (Button) rootView.findViewById(R.id.searchbutton);

            searchButton.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    EditText editText = (EditText) rootView.findViewById(R.id.searchTerm);
                    String searchTerm = editText.getText().toString();
                    TextView textViewArticle = (TextView) rootView.findViewById(R.id.article);
                    TextView textViewWord = (TextView) rootView.findViewById(R.id.word);
                    textViewWord.setText(searchTerm);
                    ArticleDao.Article result = articleDao.getArticle(searchTerm, rootView.getContext());
                    if (result == null) {
                        textViewArticle.setText("?");
                    } else {
                        textViewArticle.setText(result.toString());
                    }
                }
            });

            return rootView;
        }


    }
}

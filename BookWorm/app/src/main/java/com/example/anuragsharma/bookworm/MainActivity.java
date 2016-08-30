package com.example.anuragsharma.bookworm;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class MainActivity extends AppCompatActivity {

    public static final String LOG_TAG = MainActivity.class.getSimpleName();
    private static String BOOKS_API_URL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView listView = (ListView) findViewById(R.id.list);
        listView.setEmptyView(findViewById(R.id.empty));
        final EditText query = (EditText) findViewById(R.id.search);
        Button button = (Button) findViewById(R.id.search_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(query.getText().toString().isEmpty()==false && isNetworkAvailable()==true) {
                    StringTokenizer st = new StringTokenizer(query.getText().toString());
                    String searchQuery = st.nextToken();
                    while(st.hasMoreTokens())
                    {
                        searchQuery+="+"+st.nextToken();
                    }
                    BOOKS_API_URL = "https://www.googleapis.com/books/v1/volumes?q=" + searchQuery + "&maxResults=20";
                    BookAsyncTask bookAsyncTask = new BookAsyncTask();
                    bookAsyncTask.execute();
                }
                else if(query.getText().toString().isEmpty()==true)
                    Toast.makeText(MainActivity.this,"Please fill the search field",Toast.LENGTH_LONG).show();
                else if(isNetworkAvailable()==false)
                    Toast.makeText(MainActivity.this,"No Internet Connection",Toast.LENGTH_LONG).show();
            }
        });
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnectedOrConnecting();
    }

    private void updateUi(ArrayList<Book> bookList){
        BookAdapter bookAdapter = new BookAdapter(this,bookList);
        ListView listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(bookAdapter);
    }

    private class BookAsyncTask extends AsyncTask<URL,Void,ArrayList<Book>>{

        ArrayList<Book> list = new ArrayList<>();
        @Override
        protected ArrayList<Book> doInBackground(URL... urls) {
            // Create URL object
            URL url = createUrl(BOOKS_API_URL);
            // Perform HTTP request to the URL and receive a JSON response back
            String jsonResponse = "";
            try {
                jsonResponse = makeHttpRequest(url);
            } catch (IOException e) {
                e.printStackTrace();
            }
            // Extract relevant fields from the JSON response and create an {@link Book} object
            list = extractFeatureFromJson(jsonResponse);
            return list;
        }

        @Override
        protected void onPostExecute(ArrayList<Book> book) {
            if (book == null) {
                Toast.makeText(MainActivity.this,"No Results Found",Toast.LENGTH_LONG).show();
                return;
            }
            updateUi(book);
        }

        private URL createUrl(String stringUrl) {
            URL url = null;
            try {
                url = new URL(stringUrl);
            } catch (MalformedURLException exception) {
                Log.e(LOG_TAG, "Error with creating URL", exception);
                return null;
            }
            return url;
        }

        private String makeHttpRequest(URL url) throws IOException {
            String jsonResponse = "";
            HttpURLConnection urlConnection = null;
            InputStream inputStream = null;
            try {
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.setReadTimeout(10000 /* milliseconds */);
                urlConnection.setConnectTimeout(15000 /* milliseconds */);
                urlConnection.connect();
                inputStream = urlConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (urlConnection != null) {
                    urlConnection.disconnect();
                }
                if (inputStream != null) {
                    // function must handle java.io.IOException here
                    inputStream.close();
                }
            }
            return jsonResponse;
        }

        private String readFromStream(InputStream inputStream) throws IOException {
            StringBuilder output = new StringBuilder();
            if (inputStream != null) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
                BufferedReader reader = new BufferedReader(inputStreamReader);
                String line = reader.readLine();
                while (line != null) {
                    output.append(line);
                    line = reader.readLine();
                }
            }
            return output.toString();
        }

        private ArrayList<Book> extractFeatureFromJson(String bookJSON) {
            try {
                JSONObject baseJsonResponse = new JSONObject(bookJSON);
                int noOfItems = baseJsonResponse.getInt("totalItems");
                if(noOfItems>0) {
                    ArrayList<Book> list = new ArrayList<>();
                    JSONArray itemArray = baseJsonResponse.getJSONArray("items");
                    // If there are results in the item array
                    if (itemArray.length() > 0) {
                        for(int i=0;i<itemArray.length();i++) {
                            // Extract out the first book
                            JSONObject firstBook = itemArray.getJSONObject(i);
                            JSONObject volumeInfo = firstBook.getJSONObject("volumeInfo");
                            // Extract out the title, author values
                            String title = volumeInfo.getString("title");
                            String author="";
                            if(volumeInfo.has("authors")) {
                                JSONArray authorArray = volumeInfo.getJSONArray("authors");
                                if (authorArray.length() > 0) {
                                    for (int j = 0; j < authorArray.length(); j++) {
                                        author += authorArray.getString(j) + "\n";
                                    }
                                }
                            }
                            else
                                author="UNKNOWN";
                            // Create a new {@link Book} object
                            Book book = new Book(title, author);
                            list.add(book);
                        }
                    }
                    return list;
                }
            } catch (JSONException e) {
                Log.e(LOG_TAG, "Problem parsing the book JSON results", e);
                Toast.makeText(MainActivity.this,"No Results Found",Toast.LENGTH_LONG).show();
            }
            return null;
        }
    }
}

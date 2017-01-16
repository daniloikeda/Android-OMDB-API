package com.example.daniloikeda.zuptestemobile.Fragment;

import android.app.AlertDialog;
import android.app.FragmentManager;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.daniloikeda.zuptestemobile.DatabaseORMLite.DatabaseHelper;
import com.example.daniloikeda.zuptestemobile.DatabaseORMLite.Movies;
import com.example.daniloikeda.zuptestemobile.R;
import com.example.daniloikeda.zuptestemobile.Retrofit.api.gitapi;
import com.example.daniloikeda.zuptestemobile.Retrofit.model.gitmodel;
import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.squareup.picasso.Picasso;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by DaniloIkeda on 09/01/2017.
 */
public class CadastroFragment extends Fragment {
    LinearLayout informationLayout;
    ScrollView scrollView;
    Button search;
    Button download;
    EditText et_name;
    ProgressBar pb;
    TextView title;
    TextView year;
    TextView genre;
    TextView rating;
    TextView plot;
    TextView director;
    TextView writer;
    TextView actors;
    TextView release;
    TextView language;
    TextView country;

    FragmentManager fragmanager;
    Movies movieConstructor;
    String API = "http://www.omdbapi.com";
    DatabaseHelper dbHelper;

    public static CadastroFragment newInstance(int page) {

        CadastroFragment fragment = new CadastroFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.cadastrar_layout, container, false);

        informationLayout = (LinearLayout)view.findViewById(R.id.information_layout);
        scrollView = (ScrollView)view.findViewById(R.id.scrollView_layout);
        pb = (ProgressBar)view.findViewById(R.id.progress_bar);

        informationLayout.setVisibility(View.INVISIBLE);


        search = (Button)view.findViewById(R.id.search_button);
        et_name = (EditText)view.findViewById(R.id.input_movie_name);
        title = (TextView)view.findViewById(R.id.title);
        year = (TextView)view.findViewById(R.id.year);
        genre = (TextView)view.findViewById(R.id.genre);
        rating = (TextView)view.findViewById(R.id.rating);
        plot = (TextView)view.findViewById(R.id.plot);
        director = (TextView)view.findViewById(R.id.director);
        writer = (TextView)view.findViewById(R.id.writer);
        actors = (TextView)view.findViewById(R.id.actors);
        release = (TextView)view.findViewById(R.id.release);
        language = (TextView)view.findViewById(R.id.language);
        country = (TextView)view.findViewById(R.id.country);
        download = (Button)view.findViewById(R.id.download);
        final ImageView background = (ImageView)view.findViewById(R.id.background_image);


        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String movie = et_name.getText().toString();
                pb.setVisibility(View.VISIBLE);

                //Retrofit section start from here...
                RestAdapter restAdapter = new RestAdapter.Builder()
                        .setEndpoint(API).build();                                        //create an adapter for retrofit with base url

                final gitapi git = restAdapter.create(gitapi.class);                            //creating a service for adapter with our GET class

                //Now ,we need to call for response
                //Retrofit using gson for JSON-POJO conversion

                git.getFeed(movie, new Callback<gitmodel>() {
                    @Override
                    public void success(gitmodel gitmodel, Response response) {
                        //we get json object from github server to our POJO or model class
                        if(gitmodel.getTitle() == null)
                        {
                            AlertDialog alertDialog = new AlertDialog.Builder(getActivity()).create();
                            alertDialog.setTitle("Erro");
                            alertDialog.setMessage("Filme não encontrado");
                            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int which) {
                                            dialog.dismiss();
                                        }
                                    });
                            alertDialog.show();
                        }
                        else {
                            movieConstructor = new Movies(gitmodel.getTitle(), gitmodel.getYear(), gitmodel.getRated(), gitmodel.getReleased(), gitmodel.getRuntime(), gitmodel.getGenre(),
                                    gitmodel.getDirector(), gitmodel.getWriter(), gitmodel.getActors(), gitmodel.getPlot(), gitmodel.getLanguage(), gitmodel.getCountry(), gitmodel.getAwards() ,gitmodel.getPoster(),
                                    gitmodel.getMetascore(), gitmodel.getImdbRating(), gitmodel.getImdbVotes(), gitmodel.getImdbID(), gitmodel.getType());

                            informationLayout.setVisibility(View.VISIBLE);
                            title.setText(gitmodel.getTitle());
                            year.setText("(" + gitmodel.getYear() + ")");
                            genre.setText(gitmodel.getGenre());
                            rating.setText(gitmodel.getImdbRating()+"/10");
                            plot.setText(gitmodel.getPlot());
                            director.setText(gitmodel.getDirector());
                            writer.setText(gitmodel.getWriter());
                            actors.setText(gitmodel.getActors());
                            release.setText(gitmodel.getReleased());
                            language.setText(gitmodel.getLanguage());
                            country.setText(gitmodel.getCountry());
                            Picasso.with(getActivity())
                                    .load(gitmodel.getPoster())
                                    .into(background);
                        }
                        pb.setVisibility(View.INVISIBLE);                               //disable progressbar
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        informationLayout.setVisibility(View.INVISIBLE);
                        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                        builder.setMessage(error.getMessage())
                                .setTitle("Erro");
                        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // User clicked OK button
                            }
                        });
                        AlertDialog dialog = builder.create();
                        pb.setVisibility(View.INVISIBLE);                               //disable progressbar
                         }
                });
            }
        });

        download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbHelper = OpenHelperManager.getHelper(getActivity(), DatabaseHelper.class);
                RuntimeExceptionDao<Movies, Integer> moviesDao = dbHelper.getMovieRuntimeExceptionDao();

                moviesDao.create(new Movies(movieConstructor.getTitle(), movieConstructor.getYear(), movieConstructor.getRated(), movieConstructor.getReleased(),
                        movieConstructor.getRuntime(), movieConstructor.getGenre(), movieConstructor.getDirector(), movieConstructor.getWriter(), movieConstructor.getActors(),
                        movieConstructor.getPlot(),movieConstructor.getLanguage(), movieConstructor.getCountry() ,movieConstructor.getAwards(), movieConstructor.getPoster(),
                        movieConstructor.getMetascore(), movieConstructor.getImdbRating(), movieConstructor.getImdbVotes(), movieConstructor.getImdbID(), movieConstructor.getType()));
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setMessage("Filme Cadastrado")
                        .setTitle("Sucesso");
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User clicked OK button
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });

        return view;
    }

}



package com.example.daniloikeda.zuptestemobile.Fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ListView;

import com.example.daniloikeda.zuptestemobile.DatabaseORMLite.DatabaseHelper;
import com.example.daniloikeda.zuptestemobile.DatabaseORMLite.Movies;
import com.example.daniloikeda.zuptestemobile.FilmesAdapter.ExpandableListAdapter;
import com.example.daniloikeda.zuptestemobile.FilmesAdapter.ItemListView;
import com.example.daniloikeda.zuptestemobile.R;
import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.RuntimeExceptionDao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


/**
 * Created by DaniloIkeda on 09/01/2017.
 */
public class FilmesFragment extends Fragment {
    View view;
    Button clear;
    DatabaseHelper dbHelper;
    ArrayList<ItemListView> itens;
    ListView listView;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;
    ExpandableListView expadableListView;

    ExpandableListAdapter listAdapter;
    boolean firstTime = true;

    public static FilmesFragment newInstance(int page) {

        FilmesFragment fragment = new FilmesFragment();
        return fragment;

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.movies_fragment, container, false);
        expadableListView = (ExpandableListView)view.findViewById(R.id.listView);
        //listView = (ListView)view.findViewById(R.id.listView);
        clear = (Button)view.findViewById(R.id.clear);
        createListView();
        firstTime = false;
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setMessage("Tem certeza que deseja excluir os filmes cadastrados ?")
                        .setTitle("Confirmação");
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dbHelper = OpenHelperManager.getHelper(getActivity(), DatabaseHelper.class);
                        RuntimeExceptionDao<Movies, Integer> movieDao = dbHelper.getMovieRuntimeExceptionDao();
                        List<Movies> moviesList = movieDao.queryForAll();
                        for(Movies movie : moviesList){
                            movieDao.delete(movie);
                        }
                        createListView();
                    }
                });
                builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
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

    private void createListView()
    {
        listDataHeader = new ArrayList<String>();
        String header;
        listDataChild = new HashMap<String, List<String>>();


        itens = new ArrayList<ItemListView>();

        dbHelper = OpenHelperManager.getHelper(getActivity(), DatabaseHelper.class);
        RuntimeExceptionDao<Movies, Integer> movieDao = dbHelper.getMovieRuntimeExceptionDao();
        List<Movies> moviesList = movieDao.queryForAll();
        for(Movies movie : moviesList){
            listDataHeader.add(movie.getTitle());
            List<String> listContent = new ArrayList<String>();
            listContent.add("Ano:     " + movie.getYear());
            listContent.add("Lançamento:     " + movie.getReleased());
            listContent.add("Duração:     " + movie.getRuntime());
            listContent.add("Gêneno:     " + movie.getGenre());
            listContent.add("Diretor:     " + movie.getDirector());
            listContent.add("Escritor:     " + movie.getWriter());
            listContent.add("Atores:     " + movie.getActors());
            listContent.add("Sinopse:     " + movie.getPlot());
            listContent.add("Idioma:     " + movie.getLanguage());
            listContent.add("País:     " + movie.getCountry());
            listDataChild.put(movie.getTitle(), listContent);
            //ItemListView item1 = new ItemListView(movie.getTitle(), movie.getYear());
            //itens.add(item1);
        }
        listAdapter = new ExpandableListAdapter(getActivity(), listDataHeader, listDataChild);

        // setting list adapter
        expadableListView.setAdapter(listAdapter);
        //adapterListView = new AdapterListView(getActivity(), itens);
        //listView.setAdapter(adapterListView);
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        if(isVisibleToUser && !firstTime)
        {
           createListView();
        }
        super.setUserVisibleHint(isVisibleToUser);
    }
}
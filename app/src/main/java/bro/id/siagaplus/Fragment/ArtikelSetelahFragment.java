package bro.id.siagaplus.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import bro.id.siagaplus.Adapter.ArtikelSetelahAdapter;
import bro.id.siagaplus.R;

public class ArtikelSetelahFragment extends Fragment {

    private RecyclerView recyclerView;
    private ArtikelSetelahAdapter artikelSetelahAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_artikel_setelah, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.list_menu);
        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llm);
        artikelSetelahAdapter = new ArtikelSetelahAdapter(getContext());
        recyclerView.setAdapter(artikelSetelahAdapter);

        return view;
    }
}
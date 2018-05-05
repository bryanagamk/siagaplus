package bro.id.siagaplus.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import bro.id.siagaplus.Adapter.ArtikelTrimester2Adapter;
import bro.id.siagaplus.R;

public class ArtikelTrimester2Fragment extends Fragment {

    private RecyclerView recyclerView;
    private ArtikelTrimester2Adapter artikelTrimester2Adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_artikel_trimester2, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.list_menu);
        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llm);
        artikelTrimester2Adapter = new ArtikelTrimester2Adapter(getContext());
        recyclerView.setAdapter(artikelTrimester2Adapter);

        return view;
    }
}
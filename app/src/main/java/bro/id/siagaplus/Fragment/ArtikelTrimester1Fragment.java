package bro.id.siagaplus.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import bro.id.siagaplus.Adapter.ArtikelTrimester1Adapter;
import bro.id.siagaplus.R;

public class ArtikelTrimester1Fragment extends Fragment {

    private RecyclerView recyclerView;
    private ArtikelTrimester1Adapter artikelTrimester1Adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_artikel_trimester1, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.list_menu);
        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llm);
        artikelTrimester1Adapter = new ArtikelTrimester1Adapter(getContext());
        recyclerView.setAdapter(artikelTrimester1Adapter);

        return view;
    }
}
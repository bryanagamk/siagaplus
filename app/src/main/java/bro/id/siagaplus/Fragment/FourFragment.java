package bro.id.siagaplus.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import bro.id.siagaplus.Activity.ArtikelActivity;
import bro.id.siagaplus.Activity.CheckListActivity;
import bro.id.siagaplus.Adapter.AgendaAdapter;
import bro.id.siagaplus.Adapter.ArtikelAdapter;
import bro.id.siagaplus.Adapter.ChecklistAdapter;
import bro.id.siagaplus.Model.Agenda;
import bro.id.siagaplus.Model.Artikel;
import bro.id.siagaplus.Model.Checklist;
import bro.id.siagaplus.R;

public class FourFragment extends Fragment {

    ArrayList<Agenda> mlistAgenda;
    ArrayList<Checklist> mListChecklist;
    ArrayList<Artikel> mListArtikel;
    RecyclerView rvAgenda,rvChecklist,rvArtikel;

    AgendaAdapter agendaAdapter;
    ChecklistAdapter checklistAdapter;
    ArtikelAdapter artikelAdapter;

    Context mContext;
    public FourFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_four, container, false);

        mContext = this.getActivity().getApplicationContext();

        mlistAgenda = new ArrayList<>();
        mListChecklist = new ArrayList<>();
        mListArtikel = new ArrayList<>();

        rvAgenda = rootView.findViewById(R.id.rv_agenda_fragmentfour);
        rvChecklist = rootView.findViewById(R.id.rv_checklist_fragmentfour);
        rvArtikel = rootView.findViewById(R.id.rv_artikel_fragmentfour);
        final Bundle myBundle = new Bundle();
        myBundle.putInt("id",3);

        LinearLayoutManager llmAgenda = new LinearLayoutManager(mContext);
        llmAgenda.setOrientation(LinearLayoutManager.VERTICAL);

        LinearLayoutManager llmChecklist = new LinearLayoutManager(mContext);
        llmChecklist.setOrientation(LinearLayoutManager.VERTICAL);

        LinearLayoutManager llmArtikel = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);

        rvAgenda.setLayoutManager(llmAgenda);
        rvChecklist.setLayoutManager(llmChecklist);
        rvArtikel.setLayoutManager(llmArtikel);

        agendaAdapter = new AgendaAdapter(mContext,mlistAgenda);
        checklistAdapter = new ChecklistAdapter(mContext,mListChecklist);
        artikelAdapter = new ArtikelAdapter(mContext, mListArtikel);

        rvAgenda.setAdapter(agendaAdapter);
        rvChecklist.setAdapter(checklistAdapter);
        rvArtikel.setAdapter(artikelAdapter);

        getAgenda();
        getChecklist();
        getArtikel();

        rootView.findViewById(R.id.link_checklist_fragmentfour).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity().getApplicationContext(), CheckListActivity.class);
                intent.putExtras(myBundle);
                startActivity(intent);
            }
        });

        rootView.findViewById(R.id.link_artikel_fragmentfour).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity().getApplicationContext(), ArtikelActivity.class);
                startActivity(intent);
            }
        });

        return rootView;
    }
    private void getAgenda(){

        Agenda agenda = new Agenda("6/08/2018","11.00","bimbingan pak ali");
        mlistAgenda.add(agenda);



        agendaAdapter.notifyDataSetChanged();
    }

    private void getChecklist(){
        Checklist checklist = new Checklist("fikkril PA",false);
        mListChecklist.add(checklist);



        checklistAdapter.notifyDataSetChanged();
    }

    private void getArtikel(){
        Artikel artikel = new Artikel("Tanda Bahaya Kehamilan dan Perilaku Perawatan Kehamilan","Trimester 3", "trimester3.jpg", "trimester3.html");
        mListArtikel.add(artikel);

        artikel = new Artikel("Perlengkapan Bayi yang Perlu Disiapkan Sebelum Lahiran","Trimester 3", "perlengkapan_bayi.jpg", "perlengkapan_bayi.html");
        mListArtikel.add(artikel);

        artikel = new Artikel("Persiapan Melahirkan","Trimester 3", "melahirkan.png", "melahirkan.html");
        mListArtikel.add(artikel);

        artikelAdapter.notifyDataSetChanged();
    }
}

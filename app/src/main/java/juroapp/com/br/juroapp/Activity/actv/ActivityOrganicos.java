package juroapp.com.br.juroapp.Activity.actv;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;

import java.util.ArrayList;

import juroapp.com.br.juroapp.Activity.VO.ItemListaOrganicosVO;
import juroapp.com.br.juroapp.Activity.adapter.CustomAdapterListaMenu;
import juroapp.com.br.juroapp.Activity.adapter.CustomAdapterOrganicos;
import juroapp.com.br.juroapp.R;

public class ActivityOrganicos extends AppCompatActivity {
    private LinearLayout linearLayout;
    Button botaoProduto;
    private RecyclerView mRecyclerView;
    private BroadcastReceiver mReceiver;
    private CustomAdapterOrganicos mdataSet;
    public static final String BROAD_CAST_LISTA_ORGANICOS = "com.lista.Organicos";
    private RecyclerView.LayoutManager mLayoutManager;
    ArrayList<ItemListaOrganicosVO> listaOrganicos = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_organicos);

//        enviar = (Button) findViewById(R.id.btnEntrar);


        mRecyclerView = (RecyclerView) findViewById(R.id.listaMenu);

        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);

        mRecyclerView.setLayoutManager(mLayoutManager);
        IntentFilter intentFilter = new IntentFilter(BROAD_CAST_LISTA_ORGANICOS);

        mReceiver = new BroadcastReceiver() {

            @Override
            public void onReceive(Context context, Intent intent) {

                if (listaOrganicos != null) {


                    mdataSet = new CustomAdapterOrganicos(ActivityOrganicos.this, listaOrganicos);
                } else {
                    mdataSet = new CustomAdapterOrganicos(ActivityOrganicos.this, null);
                }
                mRecyclerView.setAdapter(mdataSet);
            }


        };
        registerReceiver(mReceiver, intentFilter);

        listaFirebase();

        botaoProduto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("", "");
            }
        });


    }

    private void listaFirebase() {

        //LISTAFIREBASE

        Intent intent = new Intent(BROAD_CAST_LISTA_ORGANICOS);
        getApplicationContext().sendBroadcast(intent);
    }


}
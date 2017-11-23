package juroapp.com.br.juroapp.Activity.actv;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.app.Activity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

import juroapp.com.br.juroapp.Activity.VO.ItemListaMenuVO;
import juroapp.com.br.juroapp.Activity.adapter.CustomAdapterListaMenu;
import juroapp.com.br.juroapp.R;

public class ActivityPrincipal extends Activity {
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private CustomAdapterListaMenu mdataSet;
    private BroadcastReceiver mReceiver;
    public static final String BROAD_CAST_LISTA_MENU = "com.lista.menu";
    private ArrayList<String> nomeListas;
    private ArrayList<ItemListaMenuVO> arrayNomesLista = new ArrayList<>();
    private ActivityPrincipal padrao ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        mRecyclerView = (RecyclerView) findViewById(R.id.listaMenu);

        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);

        mRecyclerView.setLayoutManager(mLayoutManager);

        IntentFilter intentFilter = new IntentFilter(BROAD_CAST_LISTA_MENU);

         padrao =  this;

        mReceiver = new BroadcastReceiver() {

            @Override
            public void onReceive(Context context, Intent intent) {

                if (arrayNomesLista != null) {


                    mdataSet = new CustomAdapterListaMenu(padrao , arrayNomesLista);
                } else {
                    mdataSet = new CustomAdapterListaMenu(padrao , null);
                }
                mRecyclerView.setAdapter(mdataSet);
            }
        };

        registerReceiver(mReceiver, intentFilter);
        consultaFirebaseListaMenus();


    }

    private void consultaFirebaseListaMenus() {

        String[] listaNomesTemp = getResources().getStringArray(R.array.listaNomes);


        for (int i = 0; i < listaNomesTemp.length; i++) {
            ItemListaMenuVO itemListaMenuVO = new ItemListaMenuVO();
            itemListaMenuVO.setNome(listaNomesTemp[i]);
            arrayNomesLista.add(itemListaMenuVO);

        }

        Intent intent = new Intent(BROAD_CAST_LISTA_MENU);
        getApplicationContext().sendBroadcast(intent);
    }


}











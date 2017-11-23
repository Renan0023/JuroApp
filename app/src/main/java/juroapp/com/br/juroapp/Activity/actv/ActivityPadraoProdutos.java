package juroapp.com.br.juroapp.Activity.actv;

import android.content.Intent;
import android.nfc.Tag;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Spinner;

import java.util.ArrayList;

import juroapp.com.br.juroapp.R;

public class ActivityPadraoProdutos extends AppCompatActivity {
    private Integer posicao;
    private Spinner spinner1;
    private Spinner spinner2;
    private Spinner spinner3;
    private Spinner spinner4;
    private Spinner spinner5;
    private Spinner spinner6;
    private Spinner spinner7;
    private Spinner spinner8;
    private Spinner spinner9;
    private Spinner spinner10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_padrao_produtos);
//        carreharStrings(this);

        spinner1 = (Spinner) findViewById(R.id.spinnerProduto1);
        spinner2 = (Spinner) findViewById(R.id.spinnerProduto2);
        spinner3 = (Spinner) findViewById(R.id.spinnerProduto3);
        spinner4 = (Spinner) findViewById(R.id.spinnerProduto4);
        spinner5 = (Spinner) findViewById(R.id.spinnerProduto5);
        spinner6 = (Spinner) findViewById(R.id.spinnerProduto6);
        spinner7 = (Spinner) findViewById(R.id.spinnerProduto7);
        spinner8 = (Spinner) findViewById(R.id.spinnerProduto8);
        spinner9 = (Spinner) findViewById(R.id.spinnerProduto9);
        spinner10 = (Spinner) findViewById(R.id.spinnerProduto10);


        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();

            posicao = extras.getInt("TipoProduto");

            switch (posicao) {
                case 0:
                    setContentView(R.layout.activity_padrao_produtos);
                    listaItensOcultar(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
                    break;
                case 1:
                    setContentView(R.layout.activity_padrao_produtos);
                    listaItensOcultar(1, 2, 3, 4, 5, 6, null, null, null, null);
                    break;


            }


        }

    }

    private void carreharStrings(ActivityPadraoProdutos view) {
        spinner1 = (Spinner) view.findViewById(R.id.spinnerProduto1);
        spinner2 = (Spinner) view.findViewById(R.id.spinnerProduto2);
        spinner3 = (Spinner) view.findViewById(R.id.spinnerProduto3);
        spinner4 = (Spinner) view.findViewById(R.id.spinnerProduto4);
        spinner5 = (Spinner) view.findViewById(R.id.spinnerProduto5);
        spinner6 = (Spinner) view.findViewById(R.id.spinnerProduto6);
        spinner7 = (Spinner) view.findViewById(R.id.spinnerProduto7);
        spinner8 = (Spinner) view.findViewById(R.id.spinnerProduto8);
        spinner9 = (Spinner) view.findViewById(R.id.spinnerProduto9);
        spinner10 = (Spinner) view.findViewById(R.id.spinnerProduto10);
    }

    private void listaItensOcultar(Integer a1, Integer a2, Integer a3, Integer a4, Integer a5, Integer a6, Integer a7, Integer a8, Integer a9, Integer a10) {
        ArrayList<Integer> numeroSpinners = new ArrayList<>();
        numeroSpinners.add(a1);
        numeroSpinners.add(a2);
        numeroSpinners.add(a3);
        numeroSpinners.add(a4);
        numeroSpinners.add(a5);
        numeroSpinners.add(a6);
        numeroSpinners.add(a7);
        numeroSpinners.add(a8);
        numeroSpinners.add(a9);
        numeroSpinners.add(a10);


        for (int i = 0; i < numeroSpinners.size(); i++) {
            if (numeroSpinners.get(i) == null) {
                ocultarSpinner(i);
            }
        }

    }

    private void ocultarSpinner(int i) {
        switch (i) {
            case 0:
                break;
            case 1:
                break;
            case 2:
                break;
            case 3:
                spinner4.setVisibility(View.GONE);
                break;
            case 4:
                spinner5.setVisibility(View.GONE);
                break;
            case 5:
                spinner6.setVisibility(View.GONE);
                break;
            case 6:
                spinner7.setVisibility(View.GONE);
                break;
            case 7:
                spinner8.setVisibility(View.GONE);
                break;
            case 8:
                spinner9.setVisibility(View.GONE);
                break;
            case 9:
                spinner10.setVisibility(View.GONE);
                break;


        }
    }


}
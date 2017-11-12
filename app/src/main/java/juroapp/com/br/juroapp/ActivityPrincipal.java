package juroapp.com.br.juroapp;

import android.os.Bundle;
import android.app.Activity;
import android.view.View;

public class ActivityPrincipal extends Activity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnBebidas:

                break;
            case R.id.organicos:

                break;
            case R.id.naoOrganicos:

                break;
            case R.id.eletronicos:

                break;
            case R.id.eletroDomesticos:

                break;
            case R.id.higiene:

                break;
            case R.id.vestuario:

                break;
            case R.id.carnes:

                break;
            case R.id.padaria:

                break;
            case R.id.graos:

                break;
            case R.id.temperos:

                break;
            case R.id.brinquedos:

                break;
        }
    }
}

package juroapp.com.br.juroapp.Activity.componentes;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import juroapp.com.br.juroapp.R;

/**
 * Created by Renan Dutra on 14/11/2017.
 */

public class ViewHolderItemProduto extends RecyclerView.ViewHolder {

    public CardView mCardView;
    public TextView mNome;
    public TextView mPreco;


    public ViewHolderItemProduto(View v) {
        super(v);
        mCardView = (CardView) v.findViewById(R.id.cell_item_cardView_produto);
        mNome = (TextView) v.findViewById(R.id.txtnome);
        mPreco = (TextView) v.findViewById(R.id.txtpreco);

    }

}




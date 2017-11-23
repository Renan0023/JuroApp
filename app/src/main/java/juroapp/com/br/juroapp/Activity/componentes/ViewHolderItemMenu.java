package juroapp.com.br.juroapp.Activity.componentes;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import juroapp.com.br.juroapp.R;

/**
 * Created by Renan Dutra on 14/11/2017.
 */

public class ViewHolderItemMenu extends RecyclerView.ViewHolder {

    public CardView mCardView;
    public TextView mTextViewTitulo;


    public ViewHolderItemMenu(View v) {
        super(v);
        mCardView = (CardView) v.findViewById(R.id.cell_item_cardView_Main);
        mTextViewTitulo = (TextView) v.findViewById(R.id.txtTituloMenu);

    }

}




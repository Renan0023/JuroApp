package juroapp.com.br.juroapp.Activity.componentes;

import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import juroapp.com.br.juroapp.R;

/**
 * Created by Renan Dutra on 14/11/2017.
 */

public class ViewHolderEmptyState extends RecyclerView.ViewHolder {

    public CardView mCardView;
    public TextView mTextViewTitulo;
    public ImageView mImageview;

    public ViewHolderEmptyState(View v) {
        super(v);

        mCardView = (CardView) v.findViewById(R.id.card_empty_state_cardview);

        mTextViewTitulo = (AppCompatTextView) v.findViewById(R.id.card_empty_state_textview_title);

        mImageview = (ImageView) v.findViewById(R.id.card_empty_state_imageview);
    }

}

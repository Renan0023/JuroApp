package juroapp.com.br.juroapp.Activity.adapter;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import java.util.ArrayList;

import juroapp.com.br.juroapp.Activity.VO.ItemListaMenuVO;
import juroapp.com.br.juroapp.Activity.actv.ActivityPadraoProdutos;
import juroapp.com.br.juroapp.Activity.actv.ActivityPrincipal;
import juroapp.com.br.juroapp.Activity.actv.MainActivity;
import juroapp.com.br.juroapp.Activity.componentes.ViewHolderEmptyState;
import juroapp.com.br.juroapp.Activity.componentes.ViewHolderItemMenu;
import juroapp.com.br.juroapp.R;

/**
 * Created by Renan Dutra on 14/11/2017.
 */

public class CustomAdapterListaMenu extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ActivityPrincipal mActivity;
    private ArrayList<ItemListaMenuVO> mDataset;

    // Animation
    private int lastPosition = -1;
    private boolean isAnamitionEnded = true;

    //CONSTANTES
    private static final int VIEW_TYPE_EMPTY = 0;
    private static final int VIEW_TYPE_OBJECT = 1;

    // Provide a suitable constructor (depends on the kind of dataset)
    public CustomAdapterListaMenu(ActivityPrincipal activity, ArrayList<ItemListaMenuVO> myDataset) {

        this.mActivity = activity;
        this.mDataset = myDataset;
    }



    /**
     * Interface para notificação do click nos layouts
     *
     * @author Erick Brittes
     */
    public static interface onViewHolderClickNotificacaoAdapter {
        /**
         * @param itemNotificacaoVO
         */
        public void onCellClickContent(ItemListaMenuVO itemNotificacaoVO);
    }


    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {

        if (mDataset != null) {
            return mDataset.size();
        }
        return 1;
    }

    @Override
    public int getItemViewType(int position) {

        if (mDataset == null) {
            return VIEW_TYPE_EMPTY;
        } else {
            return VIEW_TYPE_OBJECT;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        switch (viewType) {

            case VIEW_TYPE_EMPTY:
                ViewHolderEmptyState viewHolderEmptyState = new ViewHolderEmptyState(LayoutInflater.from(parent.getContext()).inflate(R.layout.card_empty_state, parent, false));
                return viewHolderEmptyState;
            case VIEW_TYPE_OBJECT:
                ViewHolderItemMenu viewHolderItemMenu = new ViewHolderItemMenu(LayoutInflater.from(parent.getContext()).inflate(R.layout.cell_item_menu, parent, false));
                return viewHolderItemMenu;
        }

        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {


        switch (holder.getItemViewType()) {

            case VIEW_TYPE_EMPTY:

                ViewHolderEmptyState viewHolderEmptyState = (ViewHolderEmptyState) holder;

                viewHolderEmptyState.mTextViewTitulo.setText(R.string.empty_state_text_title);

                break;

            case VIEW_TYPE_OBJECT:


                ViewHolderItemMenu viewHolderItemMenu = (ViewHolderItemMenu) holder;
                final ItemListaMenuVO item = mDataset.get(position);

                viewHolderItemMenu.mTextViewTitulo.setText(item.getNome());
                viewHolderItemMenu.mCardView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent abrirTelaPrincipal = new Intent(mActivity, ActivityPadraoProdutos.class);
                        abrirTelaPrincipal.putExtra("TipoProduto", position);
                        mActivity.startActivity(abrirTelaPrincipal);
                    }
                });
                break;
        }

    }

    /**
     * @param position
     * @return
     */
    public ItemListaMenuVO getItemMenuVO(int position) {

        if (mDataset != null) {

            ItemListaMenuVO itemListaMenuVO = mDataset.get(position);

            return itemListaMenuVO;
        }
        return null;
    }


}
package juroapp.com.br.juroapp.Activity.adapter;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;

import juroapp.com.br.juroapp.Activity.VO.ItemListaOrganicosVO;
import juroapp.com.br.juroapp.Activity.actv.ActivityOrganicos;

import juroapp.com.br.juroapp.Activity.actv.MainActivity;
import juroapp.com.br.juroapp.Activity.componentes.ViewHolderEmptyState;
import juroapp.com.br.juroapp.Activity.componentes.ViewHolderItemMenu;
import juroapp.com.br.juroapp.Activity.componentes.ViewHolderItemProduto;
import juroapp.com.br.juroapp.R;

/**
 * Created by Renan Dutra on 14/11/2017.
 */

public class CustomAdapterOrganicos extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ActivityOrganicos mActivity;
    private ArrayList<ItemListaOrganicosVO> mDataset;

    // Animation
    private int lastPosition = -1;
    private boolean isAnamitionEnded = true;

    //CONSTANTES
    private static final int VIEW_TYPE_EMPTY = 0;
    private static final int VIEW_TYPE_OBJECT = 1;

    // Provide a suitable constructor (depends on the kind of dataset)
    public CustomAdapterOrganicos(ActivityOrganicos activity, ArrayList<ItemListaOrganicosVO> myDataset) {

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
        public void onCellClickContent(ItemListaOrganicosVO itemNotificacaoVO);
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
                ViewHolderItemProduto viewHolderItemProduto = new ViewHolderItemProduto(LayoutInflater.from(parent.getContext()).inflate(R.layout.cell_item_produto, parent, false));
                return viewHolderItemProduto;
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


                ViewHolderItemProduto viewHolderItemProduto = (ViewHolderItemProduto) holder;
                final ItemListaOrganicosVO item = mDataset.get(position);

                viewHolderItemProduto.mNome.setText(item.getNomeProduto());
                viewHolderItemProduto.mPreco.setText(item.getPrecoProduto());
                viewHolderItemProduto.mCardView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(mActivity, mDataset.get(position).getNomeProduto(), Toast.LENGTH_LONG).show();
                    }
                });
                break;
        }

    }

    private void escolherTela(int position) {
        switch (position) {
            case 0:
                Intent abrirTelaPrincipal = new Intent(mActivity, ActivityOrganicos.class);
                mActivity.startActivity(abrirTelaPrincipal);
                break;
            case 1:
//                Intent abrirTelaPrincipal = new Intent(mActivity, ActivityOrganicos.class);
//                mActivity.startActivity(abrirTelaPrincipal);
                break;

        }
    }

    /**
     * @param position
     * @return
     */
    public ItemListaOrganicosVO getItemMenuVO(int position) {

        if (mDataset != null) {

            ItemListaOrganicosVO itemListaMenuVO = mDataset.get(position);

            return itemListaMenuVO;
        }
        return null;
    }


}
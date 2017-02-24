package br.com.asynctask.http.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import br.com.asynctask.http.R;
import br.com.asynctask.http.model.AndroidVersao;

/**
 * Created by helena on 23/02/2017.
 */

public class ListaAndroidAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private LayoutInflater inflater;
    private List<AndroidVersao> versoes;

    public ListaAndroidAdapter(Context context, List<AndroidVersao> versoes) {
        this.context = context;
        this.inflater = LayoutInflater.from(context);
        this.versoes = versoes;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item, parent, false);

        return new AndroidItemHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        AndroidItemHolder androidItemHolder = (AndroidItemHolder) holder;
        androidItemHolder.tvApi.setText(versoes.get(position).getApi());
        androidItemHolder.tvNome.setText(versoes.get(position).getNome());
        androidItemHolder.tvVersao.setText(versoes.get(position).getVersao());

        Glide.with(context)
                .load(versoes.get(position).getUrlImagem())
                // enquanto a imagem não é baixada, deixa uma imagem padrão para o usuário
                .placeholder(R.mipmap.ic_launcher)
                // e caso de erro , aparece essa imagem
                .error(R.mipmap.ic_launcher)
                .into(androidItemHolder.ivIcone);
    }

    @Override
    public int getItemCount() {
        return versoes.size();
    }

    private class AndroidItemHolder extends RecyclerView.ViewHolder {

        ImageView ivIcone;
        TextView tvNome, tvApi, tvVersao;

        public AndroidItemHolder(View itemView) {
            super(itemView);

            ivIcone = (ImageView) itemView.findViewById(R.id.ivIcone);
            tvNome = (TextView) itemView.findViewById(R.id.tvNome);
            tvApi = (TextView) itemView.findViewById(R.id.tvApi);
            tvVersao = (TextView) itemView.findViewById(R.id.tvVersao);

        }
    }
}

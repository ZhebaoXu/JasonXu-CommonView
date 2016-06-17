package ViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.jasonxu.recyclerview.R;

/**
 * Created by t_xuz on 6/17/16.
 */
public class ItemClickViewHolder extends RecyclerView.ViewHolder{

    public TextView name;
    public ImageView image;

    public ItemClickViewHolder(View itemView) {
        super(itemView);

        name = (TextView)itemView.findViewById(R.id.name);
        image = (ImageView) itemView.findViewById(R.id.image);
    }
}

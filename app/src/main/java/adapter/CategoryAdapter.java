package adapter;

import android.app.Activity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.example.mappsupport.caronrent.R;
import java.util.List;
import model.CategoryModel;
import util.CustomVolleyRequest;

/**
 * Created by mappsupport on 04-04-2018.
 */

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder>
{
    List<CategoryModel> list;
    Activity activity;
    private ImageLoader imageLoader;

    public CategoryAdapter(Activity activity,List<CategoryModel> list) {
        this.list = list;
        this.activity=activity;
    }

    @Override
    public CategoryAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.car_category_layout, viewGroup, false);
        return new CategoryAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CategoryAdapter.ViewHolder viewHolder, int i) {
        final CategoryModel dataModel=list.get(i);
        viewHolder.tv_carname.setText(dataModel.getName());

        imageLoader = CustomVolleyRequest.getInstance(activity).getImageLoader();
        imageLoader.get(dataModel.getImage(), ImageLoader.getImageListener(viewHolder.iv_carimage, R.mipmap.ic_launcher, android.R.drawable.ic_dialog_alert));
        viewHolder.iv_carimage.setImageUrl(dataModel.getImage(), imageLoader);
        System.out.println("---adapter--"+dataModel.getImage());

//        Glide.with(activity)
//                .load(dataModel.getImage())
//                .asBitmap()
//                .error(R.drawable.ic_menu_camera)
//                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
//                .into(viewHolder.iv_carimage);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView tv_carname;
        NetworkImageView iv_carimage;
        CardView cardView;

        public ViewHolder(View carView) {
            super(carView);
            tv_carname=(TextView)carView.findViewById(R.id.tv_carName);
            iv_carimage=(NetworkImageView) carView.findViewById(R.id.iv_carimage);
            cardView=(CardView)carView.findViewById(R.id.card_view);
        }
    }
}

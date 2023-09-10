package group.assignment.a1.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import group.assignment.a1.Object.TargetItem;
import group.assignment.a1.Page.TargetDetail;
import group.assignment.a1.R;

public class RecycleAdapter extends RecyclerView.Adapter<RecycleAdapter.ViewHolder> {

    Context context;
    ArrayList<TargetItem> items;
    private static int selectedPosition = RecyclerView.NO_POSITION;

    public RecycleAdapter(Context context, ArrayList<TargetItem> items) {
        this.context = context;
        this.items = items;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_view, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        viewHolder.imageView.setImageResource(items.get(position).getIconResource());
        viewHolder.title.setText(items.get(position).getName());
        viewHolder.day.setText(String.valueOf(items.get(position).getDay()) + "");

        viewHolder.itemView.setSelected(selectedPosition == position);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView title, day;

        //Constructor
        public ViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageview);
            title = itemView.findViewById(R.id.title);
            day = itemView.findViewById(R.id.day);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int previousSelectedPosition = selectedPosition;
                    selectedPosition = getAdapterPosition();
                    notifyItemChanged(previousSelectedPosition); // Refresh previousSelectedPosition
                    notifyItemChanged(selectedPosition); // Refresh current position

                    //Do something
                    Intent intent = new Intent(itemView.getContext(),TargetDetail.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.putExtra("id", items.get(selectedPosition).getTargetId());
                    itemView.getContext().startActivity(intent);

                }
            });

        }
    }
}

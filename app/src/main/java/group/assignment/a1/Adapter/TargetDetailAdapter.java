package group.assignment.a1.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import group.assignment.a1.Object.TargetDetailItem;
import group.assignment.a1.R;

public class TargetDetailAdapter extends RecyclerView.Adapter<TargetDetailAdapter.ViewHolder_T> {

    Context context;
    ArrayList<TargetDetailItem> targetDetails;

    public TargetDetailAdapter(Context context, ArrayList<TargetDetailItem> targetDetails) {
        this.context = context;
        this.targetDetails = targetDetails;
    }

    @NonNull
    @Override
    public TargetDetailAdapter.ViewHolder_T onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new TargetDetailAdapter.ViewHolder_T(LayoutInflater.from(context).inflate(R.layout.item_target, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull TargetDetailAdapter.ViewHolder_T viewHolder, int position) {
        try {
            viewHolder.tv_targetDetailDate.setText(targetDetails.get(position).getDate());
            viewHolder.tv_targetDetailDescription.setText(String.valueOf(targetDetails.get(position).getDescription()));
        }catch (Exception e){
            Toast.makeText(context.getApplicationContext(), "Fill in all the blank",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public int getItemCount() {
        return targetDetails.size();
    }

    public class ViewHolder_T extends RecyclerView.ViewHolder {

        TextView tv_targetDetailDate,tv_targetDetailDescription;

        public ViewHolder_T(@NonNull View itemView) {
            super(itemView);
            tv_targetDetailDate = itemView.findViewById(R.id.tv_targetDetailDate);
            tv_targetDetailDescription = itemView.findViewById(R.id.tv_targetDetailDescription);
        }
    }
}

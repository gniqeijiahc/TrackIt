package group.assignment.a1.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import group.assignment.a1.Page.AddTarget;
import group.assignment.a1.Object.TargetItem;
import group.assignment.a1.R;

public class TargetListAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<TargetItem> targetList;

    public TargetListAdapter(Context context, ArrayList<TargetItem> targetList) {
        this.context = context;
        this.targetList = targetList;
    }

    @Override
    public int getCount() {
        return targetList.size();
    }

    @Override
    public Object getItem(int position) {
        return targetList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.list_target_layout, parent, false);
        }

        TargetItem targetItem = targetList.get(position);

        ImageView iconImageView = convertView.findViewById(R.id.iconImageView);
        TextView nameTextView = convertView.findViewById(R.id.nameTextView);
        TextView dayTextView = convertView.findViewById(R.id.dayTextView);

        iconImageView.setImageResource(targetItem.getIconResource());
        nameTextView.setText(targetItem.getName());
        dayTextView.setText("Day " + targetItem.getDay());

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, AddTarget.class);
                intent.putExtra("id", targetItem.getTargetId());
                context.startActivity(intent);
            }
        });

        return convertView;
    }
}



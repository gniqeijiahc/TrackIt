package group.assignment.a1.Adapter;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

import group.assignment.a1.Object.TargetDate;
import group.assignment.a1.R;

public class CountDownAdapter extends RecyclerView.Adapter<CountDownAdapter.ViewHolder_C> {

    Context context;
    ArrayList<TargetDate> countdown;

    public ArrayList<TargetDate> getCountdown() {
        return countdown;
    }

    public CountDownAdapter(Context context, ArrayList<TargetDate> countdown) {
        this.context = context;
        this.countdown = countdown;
    }

    @Override
    public ViewHolder_C onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ViewHolder_C(LayoutInflater.from(context).inflate(R.layout.item_calendar, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder_C viewHolder, int position) {
        try {
            viewHolder.tv_calendarTitle.setText(countdown.get(position).getTargetDate_title());
            viewHolder.tv_calendarLeftDay.setText(String.valueOf(countdown.get(position).getTargetDate_day()));
        }catch (Exception e){
            Toast.makeText(context.getApplicationContext(), "Fill in all the blank",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public int getItemCount() {
        return countdown.size();
    }

    public static class ViewHolder_C extends RecyclerView.ViewHolder{

        TextView tv_calendarTitle,tv_calendarLeftDay,tv_calendarText;

        public ViewHolder_C(View itemView) {
            super(itemView);
            tv_calendarTitle = itemView.findViewById(R.id.tv_calendarTitle);
            tv_calendarLeftDay = itemView.findViewById(R.id.tv_calendarLeftDay);
            tv_calendarText = itemView.findViewById(R.id.tv_calendarText);
        }
    }
}

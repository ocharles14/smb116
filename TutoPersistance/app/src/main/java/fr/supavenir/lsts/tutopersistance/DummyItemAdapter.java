package fr.supavenir.lsts.tutopersistance;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;



public class DummyItemAdapter extends ArrayAdapter<DummyItem> {
    final static String TAG="DummyItemAdapter";
    Context context;
    LayoutInflater layoutInflater=null;

    public DummyItemAdapter(Context context, int resource, List<DummyItem> objects) {
        super(context, resource, objects);
        this.context = context;
    }

    public LayoutInflater getLayoutInflater() {
        if(layoutInflater==null)
            layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        return layoutInflater;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View vue;
        if(convertView==null) {

            vue = getLayoutInflater().inflate(R.layout.listitem_dummyitem,
                    parent, false);
        }
        else
            vue = convertView;
        TextView titre = (TextView)vue.findViewById(R.id.listitem_Titre);
        TextView description = (TextView)vue.findViewById(R.id.listitem_Description);

        DummyItem dummyItem= (DummyItem)getItem(position);

        vue.setTag(dummyItem);
        titre.setText(dummyItem.title);
        description.setText(dummyItem.description);

        return vue;

    }
}


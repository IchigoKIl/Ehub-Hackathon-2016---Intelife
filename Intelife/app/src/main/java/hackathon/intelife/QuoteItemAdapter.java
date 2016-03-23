package hackathon.intelife;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class QuoteItemAdapter extends ArrayAdapter {

    private Context context;
    private QuoteBuilder quotes;

    QuoteItemAdapter(Context context, QuoteBuilder quotes) {
        super(context, R.layout.quote_item, quotes);

        this.context = context;
        this.quotes = quotes;
    }

    public View getView(final int position, View convertView, ViewGroup parent) {
        // Inflate new quote item
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View view = inflater.inflate(R.layout.quote_item, parent, false);

        // Obtain item at the position
        QuoteItem item = quotes.get(position);

        // Find views
        TextView quantity = (TextView) view.findViewById(R.id.quantity);
        TextView description = (TextView) view.findViewById(R.id.description);
        TextView price = (TextView) view.findViewById(R.id.price);
        Button delete = (Button) view.findViewById(R.id.delete);

        // Update views
        quantity.setText(String.valueOf(item.getQuantity()));
        description.setText(item.getDescription());
        price.setText(String.valueOf(item.getPrice()));

        // Add listeners to views
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Remove item and notify the view of the change
                QuoteItemAdapter.this.quotes.remove(getItem(position));
                QuoteItemAdapter.this.quotes.update();
                notifyDataSetChanged();
            }
        });

        return view;
    }
}

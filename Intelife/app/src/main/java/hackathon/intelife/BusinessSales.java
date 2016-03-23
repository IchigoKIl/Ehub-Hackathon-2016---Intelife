package hackathon.intelife;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class BusinessSales extends Activity {

    private EditText businessName;
    private EditText address;
    private EditText contactPosition;
    private EditText contactEmail;
    private EditText contactPhone;
    private EditText contactName;
    private EditText businessInfo;
    private EditText analysisQuestions;
    private Button add;
    private TextView subtotal;
    private TextView discountRate;
    private TextView discount;
    private TextView total;
    private EditText notes;
    private QuoteBuilder quotes = new QuoteBuilder() {
        @Override
        public void update() {
            double sub = this.getSubtotal();
            double dis = this.getDiscountRate()*sub;
            double tot = sub - dis;
            subtotal.setText(String.valueOf(sub));
            discount.setText(String.valueOf(dis));
            total.setText(String.valueOf(tot));
        }
    };
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        for (int i = 0; i < 10; i++) {
            quotes.add(new QuoteItem(i, "item " + String.valueOf(i), i + 0.99));

        }

        super.onCreate(savedInstanceState);
        setContentView(hackathon.intelife.R.layout.activity_business_sales);

        // Find views
        Button submit = (Button) findViewById(R.id.submit);
        businessName = (EditText) findViewById(R.id.businessNameInput);
        address = (EditText) findViewById(R.id.addressInput);
        contactName = (EditText) findViewById(R.id.contactNameInput);
        contactPosition = (EditText) findViewById(R.id.contactPositionInput);
        contactEmail = (EditText) findViewById(R.id.contactEmailInput);
        contactPhone = (EditText) findViewById(R.id.contactPhoneInput);
        businessInfo = (EditText) findViewById(R.id.businessInfoInput);
        analysisQuestions = (EditText) findViewById(R.id.analysisQuestionsInput);
        add = (Button) findViewById(R.id.add);
        subtotal = (TextView) findViewById(R.id.subtotal);
        discountRate = (TextView) findViewById(R.id.discountRate);
        discount = (TextView) findViewById(R.id.discount);
        total = (TextView) findViewById(R.id.total);
        notes = (EditText) findViewById(R.id.notesInput);

        final ListAdapter listAdapter = new QuoteItemAdapter(this, quotes);
        final ListView quoteBuilder = (ListView) findViewById(R.id.quoteBuilder);
        quoteBuilder.setAdapter(listAdapter);

        quotes.update();

        // Add listeners to views
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Check that all required data was given
                if (checkData()) {
                    // Creates dialog
                    ArrayList missing = checkOptionalData();
                    if (missing.size() == 0) {
                        // Everything was filled in, submit and close activity
                        finish();
                    } else {
                        // Missing optional data, ask for second confirmation
                        submitConfirmation(missing);
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Missing Information", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private boolean checkData() {
        boolean bool = true;

        if (businessName.getText().toString().trim().matches(getString(R.string.blank))) {
            // Business name is blank
            bool = false;
        }
        if (address.getText().toString().trim().matches(getString(R.string.blank))) {
            // Address is blank
            bool = false;
        }
        if (contactName.getText().toString().trim().matches(getString(R.string.blank))) {
            // Contact name is blank
            bool = false;
        }
        if (contactPosition.getText().toString().trim().matches(getString(R.string.blank))) {
            // Contact position is blank
            bool = false;
        }
        if (contactPhone.getText().toString().trim().matches(getString(R.string.blank))) {
            // Contact phone number is blank
            bool = false;
        }
        if (contactEmail.getText().toString().trim().matches(getString(R.string.blank))) {
            // Contact email address is blank
            bool = false;
        }

        if (quotes.size() == 0) {
            // Note items in quote builder
            bool = false;
        }

        return bool;
    }

    private ArrayList checkOptionalData() {
        ArrayList missing = new ArrayList();

        if (businessInfo.getText().toString().trim().matches(getString(R.string.blank))) {
            // Business info is blank
            missing.add(getResources().getString(R.string.businessInfo).toLowerCase());
        }
        if (analysisQuestions.getText().toString().trim().matches(getString(R.string.blank))) {
            // Analysis questions are blank
            missing.add(getResources().getString(R.string.analysisQuestions).toLowerCase());
        }
        if (notes.getText().toString().trim().matches(getString(R.string.blank))) {
            // Notes is blank
            missing.add(getResources().getString(R.string.notes).toLowerCase());
        }

        return missing;
    }

    private void submitConfirmation(ArrayList missing) {
        // Instantiate a cancelable dialog builder
        AlertDialog.Builder builder = new AlertDialog.Builder(BusinessSales.this);
        builder.setCancelable(true);

        // Setup
        builder.setTitle(getResources().getString(R.string.assessment));

        // Build message with string builder
        StringBuilder sb = new StringBuilder();
        sb.append(getResources().getString(R.string.submitConfirmation));
        for (int i = 0; i < missing.size(); i++) {
            sb.append(missing.get(i));

            if (i < missing.size()-2) {
                sb.append(getResources().getString(R.string.listSeparator));
            } else if (i < missing.size()-1) {
                sb.append(getResources().getString(R.string.conjunction));
            }
        }
        sb.append(getResources().getString(R.string.questionMark));
        builder.setMessage(sb.toString());

        builder.setPositiveButton(getResources().getString(R.string.submit), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                finish();
            }
        });

        builder.setNegativeButton(getResources().getString(R.string.cancel), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        });

        // Create and show the alert
        AlertDialog alert = builder.create();
        alert.show();
    }
}

package hackathon.intelife;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(hackathon.intelife.R.layout.activity_main);

        // Find views
        ImageButton tasks = (ImageButton) findViewById(hackathon.intelife.R.id.tasks);
        ImageButton news = (ImageButton) findViewById(hackathon.intelife.R.id.news);
        ImageButton performance = (ImageButton) findViewById(hackathon.intelife.R.id.performance);
        ImageButton compensation = (ImageButton) findViewById(hackathon.intelife.R.id.compensation);
        ImageButton business = (ImageButton) findViewById(hackathon.intelife.R.id.business);
        ImageButton home = (ImageButton) findViewById(hackathon.intelife.R.id.home);
        ImageButton recruiter = (ImageButton) findViewById(hackathon.intelife.R.id.recruiter);
        ImageButton trainer = (ImageButton) findViewById(hackathon.intelife.R.id.trainer);

        // Add listeners to views
        addListenerOnIncomplete(tasks);
        addListenerOnIncomplete(news);
        addListenerOnIncomplete(performance);
        addListenerOnIncomplete(compensation);
        addListenerOnBusiness(business);
        addListenerOnIncomplete(home);
        addListenerOnIncomplete(recruiter);
        addListenerOnIncomplete(trainer);
    }

    private void addListenerOnBusiness(ImageButton imageButton) {
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start the BusinessSales activity
                Intent intent = new Intent(getBaseContext(), BusinessSales.class);
                startActivity(intent);
            }
        });
    }

    private void addListenerOnIncomplete(ImageButton imageButton) {
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Display a toast for incomplete options
                Toast.makeText(getApplicationContext(), "Under Construction!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}

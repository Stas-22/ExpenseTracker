package ua.joreikarr.expensetracker;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private EditText editTextAmount;
    private Spinner spinnerCategory;
    private ListView listViewExpenses;
    private List<String> expenses;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextAmount = findViewById(R.id.editTextAmount);
        spinnerCategory = findViewById(R.id.spinnerCategory);
        listViewExpenses = findViewById(R.id.listViewExpenses);
        Button buttonAdd = findViewById(R.id.buttonAdd);

        expenses = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, expenses);
        listViewExpenses.setAdapter(adapter);

        ArrayAdapter<CharSequence> categoryAdapter = ArrayAdapter.createFromResource(this,
                R.array.category_array, android.R.layout.simple_spinner_item);
        categoryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCategory.setAdapter(categoryAdapter);

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String amount = editTextAmount.getText().toString();
                String category = spinnerCategory.getSelectedItem().toString();
                if (!amount.isEmpty()) {
                    expenses.add(category + ": $" + amount);
                    adapter.notifyDataSetChanged();
                    editTextAmount.setText("");
                }
            }
        });
    }
}

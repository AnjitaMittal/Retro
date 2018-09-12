package science.wookup.retro;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import science.wookup.retro.api.RepoAdapter;
import science.wookup.retro.api.model.GitHubRepo;
import science.wookup.retro.api.service.GitHubClient;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    Button button;
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        button = findViewById(R.id.button);
        editText = findViewById(R.id.editText);

        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create());

        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        final Retrofit retrofit = builder.build();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GitHubClient client = retrofit.create(GitHubClient.class);
                Call<List<GitHubRepo>> call = client.reposForUser(editText.getText().toString());

                call.enqueue(new Callback<List<GitHubRepo>>() {
                    @Override
                    public void onResponse(Call<List<GitHubRepo>> call, Response<List<GitHubRepo>> response) {
                        List<GitHubRepo> repos = response.body();

                        recyclerView.setAdapter(new RepoAdapter(repos));
                    }

                    @Override
                    public void onFailure(Call<List<GitHubRepo>> call, Throwable t) {
                        Toast.makeText(MainActivity.this, "error :(", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}

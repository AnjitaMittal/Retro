package science.wookup.retro.api;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import science.wookup.retro.R;
import science.wookup.retro.api.model.GitHubRepo;

public class RepoAdapter extends RecyclerView.Adapter<RepoAdapter.RepoHolder> {
    List<GitHubRepo> gitHubRepoList;

    public RepoAdapter(List<GitHubRepo> gitHubReposList) {
        this.gitHubRepoList = gitHubReposList;
    }

    @NonNull
    @Override
    public RepoHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_repo, parent, false);
        return new RepoHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RepoHolder holder, int position) {
        holder.textView.setText(gitHubRepoList.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return gitHubRepoList.size();
    }

    public class RepoHolder extends RecyclerView.ViewHolder {
        TextView textView;

        public RepoHolder(View itemView) {
            super(itemView);

            textView = itemView.findViewById(R.id.textView);
        }
    }
}

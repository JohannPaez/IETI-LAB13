package co.edu.eci.ieti.android.ui.viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import co.edu.eci.ieti.android.model.Task;
import co.edu.eci.ieti.android.repository.TaskRepository;

public class TaskViewModel extends AndroidViewModel {
    private TaskRepository taskRepository;
    private MutableLiveData<List<Task>> tasks;
    private Executor executor = Executors.newFixedThreadPool(1);

    public TaskViewModel (Application application) {
        super(application);
    }

    public MutableLiveData<List<Task>> getAllTasks() {
        if (tasks == null) {
            tasks = new MutableLiveData<>();
            loadTasks();
        }
        return tasks;
    }

    public void insert(Task task) {
        taskRepository.addTask(task);
    }

    public void setToken(String token) {
        taskRepository = new TaskRepository(token, getApplication().getApplicationContext());
    }

    public void loadTasks() {
        executor.execute(() -> tasks.postValue(taskRepository.getTasks()));
    }
}

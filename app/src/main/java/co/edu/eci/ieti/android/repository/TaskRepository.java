package co.edu.eci.ieti.android.repository;

import android.content.Context;

import java.io.IOException;
import java.util.List;

import co.edu.eci.ieti.android.dao.TaskDAO;
import co.edu.eci.ieti.android.database.TaskRoomDatabase;
import co.edu.eci.ieti.android.model.Task;
import co.edu.eci.ieti.android.network.RetrofitNetwork;
import co.edu.eci.ieti.android.network.service.TaskService;

public class TaskRepository {
    private TaskService taskService;
    private TaskDAO taskDAO;

    public TaskRepository(String token, Context context) {
        taskService = new RetrofitNetwork(token).getTaskService();
        taskDAO = TaskRoomDatabase.getDatabase(context).taskDAO();
    }

    public List<Task> getTasks() {
        try {
            List<Task> tasks = taskService.getTasks().execute().body();
            taskDAO.deleteAll();
            for (Task task: tasks) {
                taskDAO.insert(task);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return taskDAO.getAllTask();
    }

    public void addTask(Task task) {
        try {
            taskDAO.insert(task);
            taskService.addTask(task).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

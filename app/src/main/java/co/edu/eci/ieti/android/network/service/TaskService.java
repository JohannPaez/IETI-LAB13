package co.edu.eci.ieti.android.network.service;

import java.util.List;

import co.edu.eci.ieti.android.model.Task;
import co.edu.eci.ieti.android.network.data.LoginWrapper;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface TaskService {

    @GET( "api/task" )
    Call<List<Task>> getTasks();
    @POST( "api/task" )
    Call<Void> addTask(@Body Task task);
}

import java.util.ArrayList;
//it has operations to add/delete tasks in the list
public class TaskList {

    private ArrayList <Task> tasks;

    public TaskList(){}
    public TaskList (ArrayList<Task> tasks){ this.tasks = tasks;}
    public void add(Task task){ this.tasks.add(task);}
    public void remove(int index){
        tasks.remove(index);
    }
    public Task get(int index){
        return tasks.get(index);
    }
    public int sizeOfList(){
        return tasks.size();
    }
}





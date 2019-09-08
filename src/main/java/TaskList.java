import java.util.ArrayList;

/**
 * Represents an array of tasks with operations that can
 * add or remove tasks from the list
 */
public class TaskList {
    /*ArrayList of type Task*/
    private ArrayList <Task> tasks;

    /**
     * A default constructor to TaskList
     */
    public TaskList(){}

    /**
     * Creates a new TaskList that is an array of different types of tasks
     * @param tasks an ArrayList of type tasks
     */
    public TaskList (ArrayList<Task> tasks){ this.tasks = tasks;}

    /**
     * Add Task to the array
     * @param task an ArrayList of type tasks
     */
    public void add(Task task){ this.tasks.add(task);}

    /**
     * Remove task from the array
     * @param index index of the task to be removed from the task
     */
    public void remove(int index){
        tasks.remove(index);
    }

    /**
     * Retrieve the index of a certain task
     * @param index index of the task
     * @return an integer of the index of the task
     */
    public Task get(int index){
        return tasks.get(index);
    }

    /**
     * Retrieve the number of elements in the array list
     * @return an integer of the number of elements in the array list
     */
    public int sizeOfList(){
        return tasks.size();
    }
}





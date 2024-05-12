package com.example.taskapp

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.taskapp.R

class TasksAdapter (private var tasks: List<Task> ,context:Context) : RecyclerView.Adapter<TasksAdapter.TaskViewHolder>(){

   private val db:TasksDatabaseHelper= TasksDatabaseHelper(context)
    //for recycler view  we need adapter
    class TaskViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleTextView: TextView = itemView.findViewById(R.id.titleTextView)
        val contentTextView: TextView =itemView.findViewById(R.id.contentTextView)
        val updateButton: ImageView=itemView.findViewById(R.id.updateButton)
        val deleteButton: ImageView=itemView.findViewById(R.id.deleteButton)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val view= LayoutInflater.from(parent.context).inflate(R.layout.task_item,parent, false)
        return TaskViewHolder(view)
    }

    override fun getItemCount(): Int = tasks.size

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val task= tasks[position]
        holder.titleTextView.text=task.title
        holder.contentTextView.text=task.content

        holder.updateButton.setOnClickListener{
            val intent = Intent(holder.itemView.context,UpdateTaskActivity::class.java).apply{
                 putExtra("task_id",task.Id)

            }
            holder.itemView.context.startActivity(intent)
        }
        holder.deleteButton.setOnClickListener{
            db.deleteTask(task.Id)
            refreshData(db.getAllTasks())
            Toast.makeText(holder.itemView.context,"Task deleted",Toast.LENGTH_SHORT).show()
            }



    }
    fun refreshData (newTasks: List<Task>){
        tasks = newTasks
        notifyDataSetChanged()
    }
}

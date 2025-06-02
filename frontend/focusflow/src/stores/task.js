import { ref, computed } from 'vue'
import { defineStore } from 'pinia'

export const useTaskStore = defineStore('task', {
  state: () => ({
    tasks: [],
    task:{
      title:'',
      shortDescription:'',
      longDescription:'',
      dueDate:'',
    },
    saveTaskError: null,
  }),
  actions: {
    async getTasks() {
      const response = await fetch('http://localhost:8082/tasks/list')
      this.tasks = await response.json()
    },

    initNewTask(){
      this.task = {
        title:'',
        shortDescription:'',
        longDescription:'',
        dueDate:'',
      }
    },
    async saveTask(){
      this.saveTaskError = null

        const response = await fetch('http://localhost:8082/tasks/create', {
          method: 'POST',
          headers: {
            'Content-Type': 'application/json',
          },
          body: JSON.stringify(this.task),
        })

     if (!response.ok) {
       this.saveTaskError = await response.json()
            }

      },
    async deleteTask(id){
      const response = await fetch(`http://localhost:8082/tasks/delete/${id}`, {
        method: 'DELETE',
        headers: {
          'Content-Type': 'application/json',
        },
      })
    }
  },
})
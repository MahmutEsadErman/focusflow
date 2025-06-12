import { ref, computed } from 'vue'
import { defineStore } from 'pinia'

export const useTaskStore = defineStore('task', {
  state: () => ({
    tasks: [],
    taskIdForUpdate:null,
    task:{
      title:'',
      shortDescription:'',
      longDescription:'',
      dueDate:'',
      status:'',
    },
    saveTaskError: null,
  }),
  actions: {
    async getTasks() {
      const response = await fetch('http://localhost:8082/tasks/list')
      this.tasks = await response.json()
    },

    initNewTask(_title='',_shortDescription='',_longDescription='',_dueDate='',_status='OPEN'){
      console.log(_title)
      this.task = {
        title:_title,
        shortDescription:_shortDescription,
        longDescription:_longDescription,
        dueDate:_dueDate,
        status:_status,
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
    },
    async updateTask(){
      this.saveTaskError = null
      const response = await fetch(`http://localhost:8082/tasks/update/${this.taskIdForUpdate}`, {
        method: 'PUT',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(this.task),
      })

      if (!response.ok) {
        this.saveTaskError = await response.json()
      }

    }
  },
})
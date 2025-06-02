<script setup>
import ListTasks from "@/components/ListTasks.vue";
import {useTaskStore} from "@/stores/task.js";
import {ref} from "vue";
import UpsertTask from "@/components/UpsertTask.vue";
const showUpsertWindow=ref()
const taskStore=useTaskStore();
taskStore.getTasks();
</script>

<template>
  <Button @click="showUpsertWindow=!showUpsertWindow">New Task</Button>
  <Dialog modal v-model:visible="showUpsertWindow">
    <template #header>
      <h2>Create Task</h2>
    </template>
    <UpsertTask @afterSave="taskStore.getTasks();showUpsertWindow=!showUpsertWindow"></UpsertTask>
  </Dialog>
  <ListTasks :tasks="taskStore.tasks" @afterDeleteTask="taskStore.getTasks()"></ListTasks>
</template>

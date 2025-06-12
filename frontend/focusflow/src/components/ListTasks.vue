<script setup>
import {useTaskStore} from "@/stores/task.js";
import UpsertTask from "./UpsertTask.vue";
import {ref} from "vue";

defineProps({tasks: Array})
const showUpsertTask=ref(false);
const taskStore=useTaskStore();
const emit = defineEmits(['afterDeleteTask','afterSave']);

const deleteTask = async (id) => {
  await taskStore.deleteTask(id);
  emit("afterDeleteTask")
}
const openTaskForUpdate=(task)=>{
  showUpsertTask.value=true;
  taskStore.taskIdForUpdate=task.id;
  taskStore.initNewTask(task.title,task.shortDescription ,task.longDescription,task.dueDate,task.status)
}

const afterSave=()=>{
  showUpsertTask.value=false;
  taskStore.taskIdForUpdate=null;
  taskStore.initNewTask();
  emit("afterSave");
}
const severity={
  OPEN:"success",
  PENDING:"warn",
  IN_REVIEW:"info",
  CLOSED:"contrast"
}
</script>

<template>
  <DataTable :value="tasks" style="width: max-content">
    <Column field="title" header="Title"></Column>
    <Column field="shortDescription" header="Short Description"></Column>
    <Column field="dueDate" header="Duedate"></Column>
    <Column header="Status">
      <template #body="slotProps">
        <Tag :severity=severity[slotProps.data.status] :value="slotProps.data.status"></Tag>
      </template>
    </Column>
    <template #empty>
      No tasks yet.
    </template>
    <Column>
      <template #body="slotProps">
        <Button severity="danger" @click="deleteTask(slotProps.data.id)">delete</Button>
      </template>
    </Column>
    <Column >
      <template #body="slotProps">
        <Button severity="info" @click="openTaskForUpdate(slotProps.data)">Update</Button>
      </template>
    </Column>
  </DataTable>
  <Dialog v-model:visible="showUpsertTask" modal>
    <UpsertTask @afterSave="afterSave"></UpsertTask>
  </Dialog>
</template>

<style scoped>

</style>
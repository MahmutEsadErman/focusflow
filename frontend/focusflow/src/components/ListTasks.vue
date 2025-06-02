<script setup>
import {useTaskStore} from "@/stores/task.js";

defineProps({tasks: Array})
const taskStore=useTaskStore();
const emit = defineEmits(['afterDeleteTask']);

const deleteTask = async (id) => {
  await taskStore.deleteTask(id);
  emit("afterDeleteTask")
}
</script>

<template>
  <DataTable :value="tasks">
    <Column field="title" header="Title"></Column>
    <Column field="shortDescription" header="Short Description"></Column>
    <Column field="dueDate" header="Duedate"></Column>
    <template #empty>
      No tasks yet.
    </template>
    <Column>
      <template #body="slotProps">
        <Button severity="danger" @click="deleteTask(slotProps.data.id)">delete</Button>
      </template>
    </Column>
  </DataTable>
</template>

<style scoped>

</style>
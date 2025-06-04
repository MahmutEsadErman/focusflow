<script setup>
import {useTaskStore} from "@/stores/task.js";
import { useToast } from "primevue/usetoast";

const taskStore=useTaskStore();
taskStore.initNewTask();
const toast = useToast();
const emit = defineEmits(['afterSave']);
const saveTask = async () => {
  await taskStore.saveTask();
  if (taskStore.saveTaskError) {
    toast.add({severity: 'error', summary: 'Error', detail: taskStore.saveTaskError.errors, life: 5000});
  }
  else{
    emit('afterSave');
  }


}
const formatDateTime=(value)=> {
  if (!value) return;
  taskStore.task.dueDate = value.toISOString().slice(0, 19); // "2025-06-02T14:32:10W
}

</script>

<template>
<Card>
  <template #content>
    <div class="cardContent">
<FloatLabel variant="on">
  <InputText v-model="taskStore.task.title"
  data-testid="task-title-input" placeholder="Task Title"></InputText>
  <label for="title">Title</label>
</FloatLabel>
    <FloatLabel  variant="on">
      <InputText v-model="taskStore.task.shortDescription"
      data-testid="task-desc-input" placeholder="Short Description"></InputText>
      <label for="short">Short Description</label>
    </FloatLabel>
    <FloatLabel variant="on">
      <Textarea v-model="taskStore.task.longDescription" cols="23" auto-resize
      data-testid="task-long-desc-input" placeholder="Long Description"></Textarea>
      <label for="long">Long Description</label>
    </FloatLabel>
      <FloatLabel  variant="on">
        <DatePicker
            v-model="taskStore.task.dueDate"
            :modelValue="taskStore.task.dueDate"
            @update:modelValue="formatDateTime"
            hourFormat="24"
            showTime
            showSeconds
            data-testid="task-due-input"
            type="date"
        />
        <label for="dueDate">Due Date</label>
      </FloatLabel>
      <Button @click="saveTask"
      data-testid="submit-task-button" type="submit">Save Task</Button>
    </div>
  </template>
</Card>
  <Toast />
</template>

<style scoped>

.cardContent>*{
margin-bottom: 10px;
}
</style>
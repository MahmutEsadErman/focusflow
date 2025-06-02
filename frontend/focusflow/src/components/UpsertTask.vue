<script setup>
import {useTaskStore} from "@/stores/task.js";

const taskStore=useTaskStore();
taskStore.initNewTask();

const emit = defineEmits(['afterSave']);
const saveTask = async () => {
  await taskStore.saveTask();
  emit('afterSave');

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
  <InputText v-model="taskStore.task.title"></InputText>
  <label for="title">Title</label>
</FloatLabel>
    <FloatLabel  variant="on">
      <InputText v-model="taskStore.task.shortDescription"></InputText>
      <label for="short">Short Description</label>
    </FloatLabel>
    <FloatLabel variant="on">
      <Textarea v-model="taskStore.task.longDescription" cols="23" auto-resize></Textarea>
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
        />
        <label for="dueDate">Due Date</label>
      </FloatLabel>
      <Button @click="saveTask">Save Task</Button>
    </div>
  </template>
</Card>
</template>

<style scoped>

.cardContent>*{
margin-bottom: 10px;
}
</style>
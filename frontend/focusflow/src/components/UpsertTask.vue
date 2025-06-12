<script setup>
import {useTaskStore} from "@/stores/task.js";
import { useToast } from "primevue/usetoast";
const severity={
  OPEN:"success",
  PENDING:"warn",
  IN_REVIEW:"info",
  CLOSED:"contrast"
}
const status=["OPEN","PENDING","IN_REVIEW","CLOSED"]
const taskStore=useTaskStore();

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
const updateTask=async ()=>{
  await taskStore.updateTask()
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
  data-testid="task-title-input" ></InputText>
  <label for="title">Title</label>
</FloatLabel>
    <FloatLabel  variant="on">
      <InputText v-model="taskStore.task.shortDescription"
      data-testid="task-desc-input" ></InputText>
      <label for="short">Short Description</label>
    </FloatLabel>
    <FloatLabel variant="on">
      <Textarea v-model="taskStore.task.longDescription" cols="23" auto-resize
      data-testid="task-long-desc-input"></Textarea>
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
            showButtonBar
        />
        <label for="dueDate">Due Date</label>
      </FloatLabel>
      <FloatLabel  variant="on">
      <Select  :options="status" v-model="taskStore.task.status">
        <template #value>
          <Tag :value="taskStore.task.status" :severity="severity[taskStore.task.status]"></Tag>

        </template>
        <template #option="slotProps">

            <Tag :value="slotProps.option" :severity="severity[slotProps.option]"></Tag>

        </template>
      </Select>
        <label for="status">Status</label>
      </FloatLabel>

      <Button v-if="taskStore.taskIdForUpdate==null" @click="saveTask"
      data-testid="submit-task-button" type="submit">Save Task</Button>
      <Button v-if="taskStore.taskIdForUpdate" @click="updateTask"
              data-testid="update-task-button" type="submit">Update Task</Button>
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
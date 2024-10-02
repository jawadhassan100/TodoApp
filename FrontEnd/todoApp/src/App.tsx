import React, { useEffect, useState } from 'react';
import TodoForm from './components/TodoForm';
import TodoList from './components/TodoList';
import axios from 'axios';

interface Todo {
  id: string;
  title: string;
  description: string;
}

const App: React.FC = () => {
  const [todos, setTodos] = useState<Todo[]>([]);
  const [editingTodo, setEditingTodo] = useState<Todo | null>(null);

  // Fetch to-dos from the backend
  useEffect(() => {
    axios.get('http://localhost:8080/api/todos')
      .then((response) => {
        setTodos(response.data);
      })
      .catch((error) => {
        console.error('There was an error fetching the todos!', error);
      });
  }, []);

  // Handle creating a new to-do
  const handleCreate = (title: string, description: string) => {
    axios.post('http://localhost:8080/api/todos', { title, description })
      .then((response) => {
        setTodos([...todos, response.data]);
      })
      .catch((error) => {
        console.error('There was an error creating the todo!', error);
      });
  };

  // Handle editing a to-do
  const handleEdit = (id: string) => {
    const todoToEdit = todos.find(todo => todo.id === id);
    setEditingTodo(todoToEdit || null);
  };

  // Handle updating a to-do
  const handleUpdate = (title: string, description: string) => {
    if (editingTodo) {
      axios.put(`http://localhost:8080/api/todos/${editingTodo.id}`, { title, description })
        .then((response) => {
          setTodos(todos.map(todo => todo.id === editingTodo.id ? response.data : todo));
          setEditingTodo(null);
        })
        .catch((error) => {
          console.error('There was an error updating the todo!', error);
        });
    }
  };

  // Handle deleting a to-do
  const handleDelete = (id: string) => {
    axios.delete(`http://localhost:8080/api/todos/${id}`)
      .then(() => {
        setTodos(todos.filter(todo => todo.id !== id));
      })
      .catch((error) => {
        console.error('There was an error deleting the todo!', error);
      });
  };

  return (
    <div className="max-w-lg mx-auto py-10">
      <h1 className="text-2xl font-bold mb-4">To-Do App</h1>

      {editingTodo ? (
        <TodoForm
          initialTitle={editingTodo.title}
          initialDescription={editingTodo.description}
          onSubmit={handleUpdate}
          submitLabel="Update"
        />
      ) : (
        <TodoForm onSubmit={handleCreate} />
      )}

      <div className="mt-8">
        <TodoList todos={todos} onEdit={handleEdit} onDelete={handleDelete} />
      </div>
    </div>
  );
};

export default App;

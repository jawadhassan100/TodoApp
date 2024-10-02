import React from 'react';

interface Todo {
  id: string;
  title: string;
  description: string;
}

interface TodoListProps {
  todos: Todo[];
  onEdit: (id: string) => void;
  onDelete: (id: string) => void;
}

const TodoList: React.FC<TodoListProps> = ({ todos, onEdit, onDelete }) => {
  return (
    <div className="space-y-4">
      {todos.map((todo) => (
        <div key={todo.id} className="border p-4 rounded-md shadow-sm">
          <h2 className="font-bold text-lg">{todo.title}</h2>
          <p>{todo.description}</p>
          <div className="mt-2 space-x-2">
            <button
              onClick={() => onEdit(todo.id)}
              className="bg-yellow-500 text-white py-1 px-3 rounded-md"
            >
              Edit
            </button>
            <button
              onClick={() => onDelete(todo.id)}
              className="bg-red-500 text-white py-1 px-3 rounded-md"
            >
              Delete
            </button>
          </div>
        </div>
      ))}
    </div>
  );
};

export default TodoList;

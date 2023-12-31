package com.ingeneo.logistica.service.impl;

import com.ingeneo.logistica.eception.GeneralException;
import com.ingeneo.logistica.entity.Todo;
import com.ingeneo.logistica.repository.TodoRepository;
import com.ingeneo.logistica.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TodoServiceImpl implements TodoService {

    private final TodoRepository todoRepository;

    @Autowired
    public TodoServiceImpl(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    public List<Todo> listarTodos() {
        return todoRepository.findAll();
    }

    public Todo guardarTodo(Todo cliente) throws GeneralException {
        return todoRepository.save(cliente);
    }

    public Todo actualizarTodo(Integer id, Todo cliente) throws GeneralException {
        Optional<Todo> clienteOptional = todoRepository.findById(id);
        if (!clienteOptional.isPresent()) {
            throw new GeneralException("cliente " + id + " No encontradob",HttpStatus.NOT_FOUND);
        }
        cliente.setId(id);
        return todoRepository.save(cliente);    }

    public boolean eliminarTodo(Integer id) throws GeneralException {
        Optional<Todo> clienteOptional = todoRepository.findById(id);
        if (!clienteOptional.isPresent()) {
            throw new GeneralException("cliente " + id + " No encontrado ",HttpStatus.NOT_FOUND);

        }
        todoRepository.delete(clienteOptional.get());
        return true;
    }

    public Todo obtenerTodoPorId(Integer id) throws GeneralException {
        Optional<Todo> clienteOptional = todoRepository.findById(id);
        if (!clienteOptional.isPresent()) {
            throw new GeneralException("cliente " + id + " No encontrado ",HttpStatus.NOT_FOUND);

        }
        return clienteOptional.get();
    }
}

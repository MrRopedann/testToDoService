package com.example.todoSevice.controller;

import com.example.todoSevice.domain.Message;
import com.example.todoSevice.domain.User;
import com.example.todoSevice.repos.MessageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class MainController {
    @Autowired
    private MessageRepo messageRepo; // Тут у нас лежит список всех дел которые есть в базе данных(Храниться он в виде списка )
                                    // Отвечает за это файл MessageRepo

    @Autowired
    /* Корневая страница */
    @GetMapping("/")
    /* Возвращаем просто страницу greeting.mustache */
    public String greeting(Map<String, Object> model) {
        return "greeting";
    }

    /* Основная страница */
    @GetMapping("/main")
    public String main(Map<String, Object> model) {
        Iterable<Message> messages = messageRepo.findAll();
        model.put("messages", messages);
        return "main";
    }

    /* Передаю параметры которые указываю в форме в файле "main.mustache"  */
    @PostMapping("/main") // обработка формы с добавлением дел
    public String add(
            @AuthenticationPrincipal User user,
            @RequestParam String text,
            @RequestParam String tag, Map<String, Object> model) {
        Message message = new Message(text, tag, user); // ложим в message (text, tag и user)
        messageRepo.save(message); // Сохраняю запись в бд
        /* Вытаскиваем из репозитория и отдаем юзеру */
        Iterable<Message> messages = messageRepo.findAll();
        model.put("messages", messages);
        return "main"; // Корневая страница
    }

    /* Добавляю фильтр */
    @PostMapping("filter") // точто в скобках это мэпинг (action="filter")
    public String filter(@RequestParam String filter, Map<String, Object> model) {
        Iterable<Message> messages;

        if (filter != null && !filter.isEmpty()) {
            messages = messageRepo.findByTag(filter);
        } else {
            messages = messageRepo.findAll();
        }
        model.put("messages", messages);

        return "main";
    }

}

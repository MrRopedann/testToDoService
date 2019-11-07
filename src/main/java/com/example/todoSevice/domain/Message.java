package com.example.todoSevice.domain;

import javax.persistence.*;

@Entity // Дает знать спрингу, что это не просто кусок кода, а  сущьность которую нам необходимо сохронять в БД
public class Message {
    /* Говарит о том что поле id будет идентификатором */
    @Id
    /* Говарим фраемворку и БД что бы они сами разобрались кому какой ИД присвоить */
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id; // ИД дела

    private  String text; // Текст дела
    private  String tag; // Метка дела

    @ManyToOne(fetch = FetchType.EAGER) //
    @JoinColumn(name = "user_id")
    private User author;


    /* Создаю пустой конструктор (Без него не работает) */
    public Message() {
    }

    /* Создаю конструктор с двумя полями  */
    public Message(String text, String tag, User user) {
        this.author = user;
        this.text = text;
        this.tag = tag;
    }

    /* Это недает сломаться приложению. И проверяет если у запеси нет автора, то мы присвоим ему <none> */
    public String getAuthorName() {
        return author != null ? author.getUsername() : "<none>";
    }

    /* Создаю гетторы и сетторы для полей */
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public User getAuthor() { return author; }

    public void setAuthor(User author) { this.author = author; }

}

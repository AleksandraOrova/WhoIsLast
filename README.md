# Who Is Last
Веб-сервис для регулирования очередей студентов к преподавателям со следующим функционалом:

1. Регистрация на веб-сервисе, необходимо указать почту, пароль и имя;
2. Авторизация;
3. Авторизованный пользователь может: создать группу, создавать в ней очереди, добавлять в группу других, также он может быть добавлен в другую группу другим пользователем (внутри другой группы он может только вставать в очередь и выходить из нее).

Планируется также разработать телеграм-бота для удобства пользования. 

## Правила комментирования коммитов

1. Комментарий пишется, используя глагол прошедшем времени
2. Комментарий пишется с заглавной буквы
3. Точка в конце комментария не ставится
4. В комментарии указывается то, что было сделано, а не как

## Стандарт кодирования
Для программирования на языке Java используется стандартная нотация Java, версия 1.8 и старше.     

## Инструкция по установке проекта

1. Установить на ПК Gradle по инструкции указанной на сайте https://gradle.org/;
2. Настроить MySQL server;
3. Скачать workbench https://dev.mysql.com/downloads/workbench/, следовать инструкции на картинках:
![workbench1](https://github.com/AleksandraOrova/WhoIsLast/raw/master/InsturctionsForDB/Screenshot_4.png) 

Название для схемы может быть любым, главное использовать его, как название БД в п. №5.

![workbench2](https://github.com/AleksandraOrova/WhoIsLast/raw/master/InsturctionsForDB/Screenshot_5.png)
![workbench3](https://github.com/AleksandraOrova/WhoIsLast/raw/master/InsturctionsForDB/Screenshot_6.png)

4. Подгрузить репозиторий из CVS Git внутри IntelliJ IDEA (версия Ultimate) - VCS - Checkout from version control - Github (ветка master), в настройках проекта указать путь к Gradle;

5. В файле resources/application.properties.jdk надо поменять логин и пароль и название бд на свои;

6. Запустить проект.

## Использовались

* [Gradle](https://gradle.org/) - система автоматической сборки
* [MySQL](https://dev.mysql.com/downloads/mysql/) - система управления базами данных
* [IntelliJ IDEA](https://www.jetbrains.com/idea/) - среда разработки программного обеспечения

## Лицензия

Этот проект лицецнзирован в соответствии с [MIT License](https://github.com/AleksandraOrova/WhoIsLast/blob/master/LICENSE)

## Команда разработки

Студенты Санкт-Петербургского Политехнического университета Петра Великого кафедры прикладной математики и информатики.
* **Адаев Кирилл** 
* **Баринов Фёдор**
* **Картавченко Александр** 
* **Карауш Владлен** - *Техлид* 
* **Кудашев Евгений** 
* **Орова Александра** - *Тимлид* 

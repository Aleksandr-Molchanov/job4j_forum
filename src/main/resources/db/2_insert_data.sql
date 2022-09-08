insert into posts (name) values ('О чем этот форум?');
insert into posts (name) values ('Правила форума.');

insert into authorities (authority) values ('ROLE_USER');
insert into authorities (authority) values ('ROLE_ADMIN');

insert into users (username, enabled, password, authority_id)
values ('root', true, '$2a$10$mxGd004Eyi6mc0h3LwpkLO0uc9cE3.D7dIgZf6.7O0Z6hgtcL0J4m',
        (select id from authorities where authority = 'ROLE_ADMIN'));
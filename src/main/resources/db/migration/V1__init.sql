create table if not exists products(id bigserial primary key, title varchar(255), cost int);

insert into products(title, cost)
values
('Potato', 2),
('Carrot', 4),
('Pepper', 8),
('Tomato', 16);
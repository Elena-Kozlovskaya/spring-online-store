create table if not exists products
(
    id bigserial primary key,
    title varchar(255),
    cost int
);

insert into products(title, cost)
values
('Potato', 2),
('Carrot', 4),
('Pepper', 8),
('Tomato', 16),
('Asparagus', 75),
('Beans', 23),
('Beet', 12),
('Broccoli', 45),
('Cabbage ', 21),
('Cauliflower ', 47),
('Celery ', 38),
('Corn', 33),
('Cucumber ', 145),
('Daikon', 97),
('Eggplant', 123),
('Garlic', 67),
('Kale ', 77),
('Lettuce ', 68),
('Onion ', 25),
('Parsley', 27);


create table if not exists users
(
    id       bigserial primary key,
    username varchar(30) not null unique,
    password varchar(80) not null,
    email    varchar(50) unique,
    created_at timestamp default current_timestamp,
    updated_at timestamp default current_timestamp
);

create table if not exists roles
(
    id         bigserial primary key,
    name       varchar(50) not null,
    created_at timestamp default current_timestamp,
    updated_at timestamp default current_timestamp
);

create table if not exists users_roles
(
    user_id bigint not null,
    role_id bigint not null,
    primary key (user_id, role_id),
    foreign key (user_id) references users (id),
    foreign key (role_id) references roles (id),
    created_at timestamp default current_timestamp,
    updated_at timestamp default current_timestamp
);

insert into roles (name)
values ('ROLE_USER'),
       ('ROLE_ADMIN');

insert into users (username, password, email)
values ('MyUser', '$2a$12$QcqtrvJhZNtmUI5u/QRrfOpGfaMubI5ZiXahUYk5TUppxIHT8Vt4C', 'myuser@gmail.com'),
       ('MyAdmin', '$2a$12$QcqtrvJhZNtmUI5u/QRrfOpGfaMubI5ZiXahUYk5TUppxIHT8Vt4C', 'myadmin@gmail.com');

insert into users_roles (user_id, role_id)
values (1, 1),
       (2, 2);

/*create table if not exists permissions (
    id serial,
    name varchar(50) not null,
    primary key (id)
);*/

/*create table if not exists roles_permissions (
    role_id int not null,
    permission_id int not null,
    primary key (role_id, permission_id),
    foreign key (role_id) references roles (id),
    foreign key (permission_id) references permissions (id)
);*/

/*insert into permissions (name)
values ('PERMISSION_WRITE'),
       ('PERMISSION_READ');

insert into roles_permissions (role_id, permission_id)
values (1, 2),
       (2, 1),
       (2, 2);*/

create table if not exists orders
(
    id              bigserial primary key,
    user_id         bigint not null references users (id),
    total_price     int not null,
    address         varchar(255),
    phone           varchar(255),
    created_at timestamp default current_timestamp,
    updated_at timestamp default current_timestamp
);

create table if not exists order_items
(
    id                      bigserial primary key,
    product_id              bigint not null references products (id),
    order_id                bigint not null references orders (id),
    quantity                int not null,
    price_per_product       int not null,
    price                   int not null,
    created_at timestamp default current_timestamp,
    updated_at timestamp default current_timestamp
);
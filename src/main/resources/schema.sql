create table users
(
    id INT not null primary key auto_increment,
    username VARCHAR(20) not null ,
    password VARCHAR(20) not null ,
    email VARCHAR(100) not null ,
    full_name VARCHAR(40) not null ,
    birth_date DATE not null ,
    gender VARCHAR(10) not null ,
    postal_code VARCHAR(7) not null ,
    prefecture VARCHAR(50) not null ,
    city VARCHAR(100) not null ,
    address_detail VARCHAR(255) not null ,
    phone_number VARCHAR(11) not null
);

create table accounts
(
    id INT not null primary key auto_increment,
    user_id INT not null ,
    number VARCHAR(12) not null ,
    balance INT not null default 100000,
    password VARCHAR(20) not null ,
    purpose VARCHAR(255) not null ,
    create_date TIMESTAMP not null default CURRENT_DATE,
    FOREIGN KEY (user_id) REFERENCES users(id)
);

create table transactions
(
    id INT not null PRIMARY KEY AUTO_INCREMENT,
    from_account_id INT not null ,
    to_account_id INT not null ,
    type VARCHAR(2) not null ,
    amount INT not null ,
    date TIMESTAMP not null default CURRENT_DATE,
    note VARCHAR(255)
);
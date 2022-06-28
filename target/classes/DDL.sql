CREATE TABLE cart_tb (
    user_id  VARCHAR2(200) NOT NULL,
    isbn     VARCHAR2(60) NOT NULL,
    quantity NUMBER(5) NOT NULL
);

CREATE TABLE order_tb (
    order_id   NUMBER(10) NOT NULL,
    user_id    VARCHAR2(200) NOT NULL,
    zipcode    VARCHAR2(6) NOT NULL,
    address    VARCHAR2(300) NOT NULL,
    payment    NUMBER(10) NOT NULL,
    order_date DATE NOT NULL
);

ALTER TABLE order_tb ADD CONSTRAINT order_tb_pk PRIMARY KEY ( order_id );

CREATE TABLE orderlist_tb (
    order_id NUMBER(10) NOT NULL,
    isbn     VARCHAR2(60) NOT NULL,
    quantity NUMBER(5) NOT NULL
);

CREATE TABLE user_tb (
    user_id   VARCHAR2(200) NOT NULL,
    user_name VARCHAR2(30),
    email     VARCHAR2(200),
    mobile    VARCHAR2(30),
    gender    VARCHAR2(10),
    age       VARCHAR2(10),
    birthyear VARCHAR2(60)
);

ALTER TABLE user_tb ADD CONSTRAINT user_tb_pk PRIMARY KEY ( user_id );

ALTER TABLE cart_tb
    ADD CONSTRAINT cart_tb_user_tb_fk FOREIGN KEY ( user_id )
        REFERENCES user_tb ( user_id );

ALTER TABLE order_tb
    ADD CONSTRAINT order_tb_user_tb_fk FOREIGN KEY ( user_id )
        REFERENCES user_tb ( user_id );

ALTER TABLE orderlist_tb
    ADD CONSTRAINT table_6_order_tb_fk FOREIGN KEY ( order_id )
        REFERENCES order_tb ( order_id );
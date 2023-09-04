--liquibase formatted sql

--changeset nikita.ryadnov:1
INSERT INTO unibell.clients (id, name) VALUES (1, 'Tome');
INSERT INTO unibell.clients (id, name) VALUES (2, 'Kate');
INSERT INTO unibell.clients (id, name) VALUES (3, 'Nik');
INSERT INTO unibell.clients (id, name) VALUES (4, 'Jane');
SELECT SETVAL('unibell.clients_id_seq', (SELECT MAX(id) FROM unibell.clients));
--changeset nikita.ryadnov:2
INSERT INTO unibell.phones (id, number, client_id) VALUES (1, '+79990000000', 1);
INSERT INTO unibell.phones (id, number, client_id) VALUES (2, '+79990000001', 1);
INSERT INTO unibell.phones (id, number, client_id) VALUES (3, '+79990000002', 1);
INSERT INTO unibell.phones (id, number, client_id) VALUES (4, '+79990000003', 2);
INSERT INTO unibell.phones (id, number, client_id) VALUES (5, '+79990000004', 2);
INSERT INTO unibell.phones (id, number, client_id) VALUES (6, '+79990000005', 2);
INSERT INTO unibell.phones (id, number, client_id) VALUES (7, '+79990000006', 2);
INSERT INTO unibell.phones (id, number, client_id) VALUES (8, '+79990000007', 2);
INSERT INTO unibell.phones (id, number, client_id) VALUES (9, '+79990000008', 3);
SELECT SETVAL('unibell.phones_id_seq', (SELECT MAX(id) FROM unibell.phones));
--changeset nikita.ryadnov:3
INSERT INTO unibell.emails (id, name, client_id) VALUES (1, 'tom.tom1@test.com', 1);
INSERT INTO unibell.emails (id, name, client_id) VALUES (2, 'tom.tom2@test.com', 1);
INSERT INTO unibell.emails (id, name, client_id) VALUES (3, 'tom.tom3@test.com', 1);
INSERT INTO unibell.emails (id, name, client_id) VALUES (4, 'tom.tom4@test.com', 1);
INSERT INTO unibell.emails (id, name, client_id) VALUES (5, 'kate1@test.com', 2);
INSERT INTO unibell.emails (id, name, client_id) VALUES (6, 'kate2@test.com', 2);
INSERT INTO unibell.emails (id, name, client_id) VALUES (7, 'kate3@test.com', 2);
INSERT INTO unibell.emails (id, name, client_id) VALUES (8, 'kate4@test.com', 2);
INSERT INTO unibell.emails (id, name, client_id) VALUES (9, 'nik_test@test.com', 3);
SELECT SETVAL('unibell.emails_id_seq', (SELECT MAX(id) FROM unibell.emails));

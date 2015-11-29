INSERT INTO address (ID, POSTAL_CODE, STREET_NAME, STREET_NUMBER, TOWN) VALUES ('1', '1', 'streetname1', '1', 'town 1');
INSERT INTO address (ID, POSTAL_CODE, STREET_NAME, STREET_NUMBER, TOWN) VALUES ('2', '2', 'streetname2', '2', 'town2');

INSERT INTO user_details (ID, AGE, CNP, email, FIRST_NAME, LAST_NAME, ADDRESS_ID) VALUES ('1', '19', '1234567890876', '2@yahoo.com', 'fistname1', 'lastname1', '1');
INSERT INTO user_details (ID, AGE, CNP, email, FIRST_NAME, LAST_NAME, ADDRESS_ID) VALUES ('2', '20', '21547896532541', '2@YAhoo.com', 'firstname2', 'lastname2', '2');

INSERT INTO user_role (ID, ROLE) VALUES ('1', 'ROLE_USER');
INSERT INTO user_role (ID, ROLE) VALUES ('2', 'ROLE_ADMIN');

INSERT INTO users (ID, PASSWORD, USERNAME, USER_DETAILS_ID) VALUES ('1', 'user', 'user', '1');
INSERT INTO users (ID, PASSWORD, USERNAME, USER_DETAILS_ID) VALUES ('2', 'admin', 'admin', '2');


INSERT INTO users_user_role (ROLE_ID, USER_ID) VALUES ('1', '1');
INSERT INTO users_user_role (ROLE_ID, USER_ID) VALUES ('2', '2');

INSERT INTO account (ID, ACCOUNT_NUMBER, AMOUNT) VALUES ('1', '111', '100');
INSERT INTO account (ID, ACCOUNT_NUMBER, AMOUNT) VALUES ('2', '222', '200');

INSERT INTO users_account (USER_ID, ACCOUNT_ID) VALUES ('1', '1');
INSERT INTO users_account (USER_ID, ACCOUNT_ID) VALUES ('2', '2');

INSERT INTO address (ID, STREET_NAME, STREET_NUMBER, TOWN) VALUES ('1', 'streetname1', '1', 'town1');
INSERT INTO address (ID, STREET_NAME, STREET_NUMBER, TOWN) VALUES ('2', 'streetname2', '2', 'town2');

INSERT INTO roles (ID, ROLE) VALUES ('1', 'ROLE_USER');
INSERT INTO roles (ID, ROLE) VALUES ('2', 'ROLE_ADMIN');

INSERT INTO users (DTYPE, ID, AGE, CNP, email, FIRST_NAME, LAST_NAME, ADDRESS_ID, PASSWORD, USERNAME) VALUES ('Client', '1','19', '1234567890876', '2@yahoo.com', 'fistname1', 'lastname1', '1', 'user', 'user');
INSERT INTO users (DTYPE, ID,  AGE, CNP, email, FIRST_NAME, LAST_NAME, ADDRESS_ID, PASSWORD, USERNAME) VALUES ('Admin', '2', '20', '21547896532541', '2@YAhoo.com', 'firstname2', 'lastname2', '2', 'admin', 'admin');


INSERT INTO users_roles (ROLE_ID, USER_ID) VALUES ('1', '1');
INSERT INTO users_roles (ROLE_ID, USER_ID) VALUES ('2', '2');

INSERT INTO accounts (DTYPE, ID, ACCOUNT_NUMBER, AMOUNT) VALUES ('SavingsAccount', '1', '111', '100');
INSERT INTO accounts (DTYPE, ID, ACCOUNT_NUMBER, AMOUNT) VALUES ('CreditAccount', '2', '222', '200');

INSERT INTO users_accounts (USER_ID, ACCOUNT_ID) VALUES ('1', '1');
INSERT INTO users_accounts (USER_ID, ACCOUNT_ID) VALUES ('2', '2');

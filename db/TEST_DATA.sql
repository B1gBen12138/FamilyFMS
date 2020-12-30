use 'finance';

INSERT INTO finance.family_account (name) VALUES ('Hello');
INSERT INTO finance.family_account (name) VALUES ('World');

INSERT INTO finance.account (family_id, login_name, name, pass_word, is_admin, is_super_account) VALUES (1, '623351195', 'Ben', '123', DEFAULT, 1);
INSERT INTO finance.account (family_id, login_name, name, pass_word, is_admin, is_super_account) VALUES (null, 'Xielian', 'Big', '321', DEFAULT, DEFAULT);

INSERT INTO finance.bond_account (id, account_id, name) VALUES(null, 1, '股票');
INSERT INTO finance.bond_account (id, account_id, name) VALUES(null, 1, '期货');

INSERT INTO finance.bond_list (id, bond_account_id, name) VALUES (null, 1, '中国银行');
INSERT INTO finance.bond_list (id, bond_account_id, name) VALUES (null, 1, '浦发银行');
INSERT INTO finance.bond_list (id, bond_account_id, name) VALUES (null, 2, '大豆');
INSERT INTO finance.bond_list (id, bond_account_id, name) VALUES (null, 2, '钢铁');

INSERT INTO finance.bond_io_list (bond_list_id, is_buy_in) VALUES (1, 1);
INSERT INTO finance.bond_io_list (bond_list_id, is_buy_in) VALUES (1, 0);
INSERT INTO finance.bond_io_list (bond_list_id, is_buy_in) VALUES (2, 1);
INSERT INTO finance.bond_io_list (bond_list_id, is_buy_in) VALUES (2, 1);
INSERT INTO finance.bond_io_list (bond_list_id, is_buy_in) VALUES (3, 1);
INSERT INTO finance.bond_io_list (bond_list_id, is_buy_in) VALUES (3, 0);
INSERT INTO finance.bond_io_list (bond_list_id, is_buy_in) VALUES (4, 0);
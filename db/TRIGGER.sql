CREATE TRIGGER TRI_IOList BEFORE INSERT ON io_list FOR EACH ROW
    BEGIN
        SELECT id:=a.family_id FROM account a
            WHERE a.id = NEW.account_id;
        SET new.date = date_format(now(),'%Y-%m-%d');
    end;

/*trigger_name：触发器的名称
tirgger_time：触发时机，为BEFORE或者AFTER
trigger_event：触发事件，为INSERT、DELETE或者UPDATE
tb_name：表示建立触发器的表明，就是在哪张表上建立触发器
trigger_stmt：触发器的程序体，可以是一条SQL语句或者是用BEGIN和END包含的多条语句
所以可以说MySQL创建以下六种触发器：
BEFORE INSERT,BEFORE DELETE,BEFORE UPDATE
    AFTER INSERT,AFTER DELETE,AFTER UPDATE*/
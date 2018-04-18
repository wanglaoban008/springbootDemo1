#条件语句IF-THEN-ELSE
DROP PROCEDURE IF EXISTS pro_m1;
DELIMITER $$
CREATE PROCEDURE pro_m1(IN param INT)
 BEGIN
  DECLARE in1 INT;
  SET in1 = param+2;
  IF in1 = 2 THEN
   SELECT COUNT(1) FROM aa;
  END IF;
  IF param = 0 THEN
   SET in1=param+3;
   SELECT in1;
  ELSE
   SELECT * FROM aa;
  END IF;
 END;
 $$
DELIMITER;
## 调用存储过程  这里有个问题，就是传入的值是0，第一个条件已经满足了，他会继续往下执行，然后又select in1,最终输出的是select in1。
CALL pro_m1(0)


-- CASE-WHEN-THEN-ELSE语句
DROP PROCEDURE IF EXISTS pro_m2;
DELIMITER $$
CREATE PROCEDURE pro_m2(IN param INT)
 BEGIN
  DECLARE in2 INT;
  SET in2=param+2;
  CASE in2
  WHEN 2 THEN
  SELECT COUNT(*) FROM aa;
  WHEN 3 THEN
  SELECT 1;
  WHEN 4 THEN
  SELECT * FROM USER;
  ELSE
  SELECT * FROM aa;
  END CASE;
 END;
 $$
DELIMITER;
## 调用存储过程
CALL pro_m2(2)

##################################################################################################################################

--  WHILE-DO…END-WHILE
DROP PROCEDURE IF EXISTS pro_m3;
DELIMITER $$
CREATE PROCEDURE pro_m3(IN param INT)
 BEGIN
  DECLARE a INT;
  SET a=param+1;
  WHILE a<6 DO
  INSERT INTO aa(NAME,age,LEVEL) VALUE('小黑',a,a);
  SET a=a+1;
  END WHILE;
 END;
 $$
DELIMITER;
## DO里面不能写查询吧，我试过写查询select a，只返回第一个进去的a不知道为什么返回的是1。所以我用了insert返现确实有循环插了5条记录。
CALL pro_m3(0)

##################################################################################################################################

# REPEAT...END REPEAT
# 此语句的特点是执行操作后检查结果
DROP PROCEDURE IF EXISTS pro_m5;
DELIMITER $$
CREATE PROCEDURE pro_m5()
 BEGIN
  DECLARE vv INT;
  SET vv=0;
  REPEAT
  INSERT INTO aa(NAME,age,LEVEL) VALUE('小白',vv,vv);
  SET vv=vv+1;
  UNTIL vv>2
  END REPEAT;
 END;
$$
DELIMITER;

# 调用存储过程 这个方法和上面的循环有点类似，只不过这个是知道什么条件时停止执行。
CALL pro_m5()


##################################################################################################################################

# LABLES标号
# 标号可以用在begin repeat while 或者loop 语句前，语句标号只能在合法的语句前面使用。可以跳出循环，使运行指令达到复合语句的最后一步。
DROP PROCEDURE IF EXISTS pro_m6;
DELIMITER $$
CREATE PROCEDURE pro_m6()
 BEGIN
  DECLARE cc INT;
  SET cc=10;
  loop_lable:LOOP
  INSERT INTO aa(NAME,age,LEVEL) VALUE('小红',cc,cc+1);
  SET cc=cc+1;
  IF cc>13 THEN
   LEAVE loop_lable;
  END IF;
 END LOOP;
 END;
 $$
 DELIMITER;

# 这个跟上面两个都很像，loop翻译过来是环的意思。就是一直执行在环里，当满足什么条件离开这个环。
 CALL pro_m6();


##################################################################################################################################

## 这里还是LOOP语句加了个ITERATE，iterate相当于什么呢？相当于while语句中的continue，跳出本次循环，下面的继续循环直到满足条件退出
DROP PROCEDURE IF EXISTS pro_m7;
DELIMITER $$
CREATE PROCEDURE pro_m7()
 BEGIN
 DECLARE cc INT;
 SET cc=1;
 ww_lable:LOOP
 IF cc=3 THEN
  SET cc=cc+1;
  ITERATE ww_lable;
  END IF;
  INSERT INTO aa(NAME,age,LEVEL) VALUE('小刚',cc,cc);
  SET cc=cc+1;
  IF cc>5 THEN
   LEAVE ww_lable;
  END IF;
 END LOOP;
 END;
 $$
DELIMITER;
# ITERATE相当于while语句中的continue，跳出本次循环，下面的继续循环直到满足条件退出
CALL pro_m7();

## 总结下，以上我们遇到了三种循环：while do，REPEAT...END REPEAT 和LOOP

##################################################################################################################################


## PREPARE EXECUTE是准备预执行sql，DEALLOCATE 这个是释放资源。具体我也不太懂。concat()里面有多少个参数就拼接多少个。明天来研究
## 对了上面我用的变量全部都是局部变量，这里我用了@+变量名 是用户变量，用户变量声明赋值的两种方式。还有系统变量可以了解下。
DROP PROCEDURE IF EXISTS pro_m0;
DELIMITER $$
CREATE PROCEDURE pro_m0()
BEGIN

 SELECT @zhang:='san';
 SET @mo='mo';
 SET @xiao = CONCAT('select ','@',@mo);
 PREPARE mt2 FROM @xiao;
	EXECUTE mt2;
	DEALLOCATE PREPARE mt2;
END;
$$
DELIMITER;

CALL pro_m0();


##################################################################################################################################
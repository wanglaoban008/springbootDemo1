## 使用 CASE WHEN 和 GROUP BY 一起用。这里要注意 CASE WHEN 外面要有 SUM() 或者 MAX()等函数
SELECT trans_date,SUM(trans_money) totol_money,
    IFNULL(SUM(CASE WHEN TYPE=1 THEN trans_money END),0) type1_money ,
    IFNULL(SUM(CASE WHEN TYPE=2 THEN trans_money END),0) type2_money,
    CASE
    WHEN
    (IFNULL(SUM(CASE WHEN TYPE=1 THEN trans_money END),0)=0 AND IFNULL(SUM(CASE WHEN TYPE=2 THEN trans_money END),0) =0) THEN 0
    WHEN
    IFNULL(SUM(CASE WHEN TYPE=1 THEN trans_money END),0)=0  THEN 100
    WHEN
    IFNULL(SUM(CASE WHEN TYPE=2 THEN trans_money END),0)=0 THEN 0
    ELSE TRUNCATE(SUM( CASE WHEN TYPE=2 THEN trans_money END )/SUM(CASE WHEN TYPE=1 THEN trans_money END)*100,2)
    END interest_rate
      FROM pay_trans_statistics WHERE del=0
   AND TYPE !='3'
 AND trans_date BETWEEN '2018-04-15' AND  '2018-04-17'
  GROUP BY trans_date
  ORDER BY trans_date DESC
  LIMIT 0,10
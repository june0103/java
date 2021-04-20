create table salary(name varchar2(10) not null primary key,
pay number
);

INSERT INTo salary VALUES ('Kim',1000);
INSERT INTo salary VALUES ('Lee',2000);
INSERT INTo salary VALUES ('Park',3000);
INSERT INTo salary VALUES ();
INSERT INTo salary VALUES ();
INSERT INTo salary VALUES ();
INSERT INTo salary VALUES ();


create or replace procedure adjust(n in varchar2, rate in float)
is
	newpay float;
begin
	SELECT pay INTO newpay  FROM salary WHERE name = n;
	newpay := newpay + newpay*rate;
	
	UPDATE salary SET pay = newpay WHERE name = n;
	COMMIT;
	
	exception when others then
		dbms_output.put_line('error');
		ROLLBACK;
end ;



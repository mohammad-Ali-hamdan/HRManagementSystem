-- expenseType 

Drop procedure if exists MaxId;
Delimiter @@
Create procedure MaxId(Out max INT)
Begin
Select Max(id) into max From managementsystem.expensetype;
END @@
Delimiter ;

Drop procedure if exists CheckExist;
Delimiter @@
Create procedure CheckExist(IN TypeName Varchar(45))
Begin
Select * From managementsystem.expensetype
Where name = TypeName;
END @@
Delimiter ;






-- All procedures for employeesDepartment

Drop Procedure if exists getMaxdepID ;
Delimiter @@
Create Procedure getMaxdepID(Out max INT)
Begin
SELECT MAX(id) INTO max FROM managementsystem.department;
End @@
Delimiter ;

Delimiter ##
Create Procedure ListEmployeesEntityInDepartment(IN depname Varchar(45) )
Begin
Select e.id , e.name , e.email , e.address , e.department_id From employee e
join department d ON e.department_id = d.id
Where d.name = depname ; 
End ##
Delimiter ##



Drop Procedure if exists employeesByDepartmentID;
Delimiter ##
Create Procedure employeesByDepartmentID(IN depId int)
Begin
Select * From employee 
Where department_id = depId ; 
End ##
Delimiter ##

Delimiter @@
Create Procedure getAllDepartments()
Begin
Select name From managementsystem.department;
End @@
Delimiter ;

Delimiter @@
Create procedure departmentNames()
Begin 
Select name From managementsystem.department;
End @@
Delimiter ; 


Drop Procedure if exists ListEmployeesInDepartment ;
Delimiter ##
Create Procedure ListEmployeesInDepartment(IN depname Varchar(45) )
Begin
Select e.id From employee e
join department d ON e.department_id = d.id
Where d.name = depname ; 
End ##
Delimiter ##
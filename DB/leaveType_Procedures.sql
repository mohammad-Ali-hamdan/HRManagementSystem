-- All procedures for leaveType 

Drop Procedure if exists leaveTypeMaxID ;
Delimiter @@
Create Procedure leaveTypeMaxID(Out max INT)
Begin
SELECT MAX(id) INTO max FROM managementsystem.leavetype;
End @@
Delimiter ;

Drop Procedure if exists CheckTypeExist ;
Delimiter @@
Create Procedure CheckTypeExist(IN idi INT)
Begin
SELECT id 
FROM managementsystem.leavetype
Where id = idi ;
End @@
Delimiter ;

Drop Procedure if exists CheckLeaveTypeNameExist ;
Delimiter @@
Create Procedure CheckLeaveTypeNameExist(IN nameLeave VARCHAR(255))
Begin
SELECT * 
FROM managementsystem.leavetype
Where name = nameLeave ;
End @@
Delimiter ;

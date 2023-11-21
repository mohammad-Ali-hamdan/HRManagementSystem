-- leavedetails 

Drop Procedure if exists CheckLeaveExist ;
Delimiter @@
Create Procedure CheckLeaveExist(IN idi INT , Out checkid INT)
Begin
SELECT id 
into checkid
FROM managementsystem.leavedetails
Where id = idi ;
End @@
Delimiter ;

drop procedure if exists SearchforleavesFromTo;
Delimiter ##
Create Procedure SearchforleavesFromTo(IN datefrom Date , In dateto Date  , IN employee_id  INT)
Begin
Select * From managementsystem.leavedetails
Where (fromdate >= datefrom && Todate<= dateto && employee = employee_id ) ;
End ##
Delimiter ;


Drop Procedure if exists getMaxleavedetailsID ;
Delimiter @@
Create Procedure getMaxleavedetailsID(Out max INT)
Begin
SELECT MAX(id) INTO max FROM managementsystem.leavedetails;
End @@
Delimiter ;



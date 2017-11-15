/*insert into flightcompany values (1,"SN","Brussels Airlines");
insert into country values(1,"Belgium",4);
insert into country values(2,"USA",6);
insert into city values(1,"Brussel",1);
insert into city values(2,"New York",2);
insert into airport values (1,"BRU","Brussels Airport",1);
insert into airport values (2,"JFK","JFK",2);
insert into flight values (1,"2017-11-08 13:31:24",120,9410,100,0,1,1,2);
insert into flight values (2,"2017-11-08 13:31:23",120,9415,100,0,1,1,2);
insert into seat values(1,true,"21B",200,"ECONOMY",0,1);
insert into seat values(2,true,"21A",210,"ECONOMY",0,1);*/

USE realdolmen;
insert into FlightCompany values (1,"SN","Brussels Airlines");
insert into Country values(1,"Belgiun",4);
insert into Country values(2,"USA",6);
insert into City values(1,"Brussel",1);
insert into City values(2,"New York",2);
insert into Airport values (1,"BRU","Brussels Airport",1);
insert into Airport values (2,"JFK","JFK",2);
insert into Flight values (1,"2017-11-17 13:31:24",120,9144,100,0,1,1,2);
insert into Flight values (2,"2017-11-17 13:31:23",120,9144,100,0,1,1,2);
insert into Seat values(1,true,"mid",200,"ECONOMY",0,1);
insert into Seat values(2,true,"21A",210,"ECONOMY",0,2);
insert into Seat values(3,true,"lo",100,"ECONOMY",0,1);
insert into Seat values(4,true,"hi",300,"ECONOMY",0,1);

insert into Flight values (3,"2017-11-17 13:31:24",120,9144,100,0,1,1,2);
insert into Flight values (4,"2017-11-17 13:31:23",120,9144,100,0,1,1,2);
insert into Seat values(5,true,"mid",200,"ECONOMY",0,3);
insert into Seat values(6,true,"21A",210,"ECONOMY",0,4);
insert into Seat values(7,true,"lo",100,"ECONOMY",0,3);
insert into Seat values(8,true,"hi",300,"ECONOMY",0,3);